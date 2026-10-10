package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.SoundManager
import com.example.data.QuizRepository
import com.example.model.QuizPhase
import com.example.model.QuizQuestion
import com.example.model.QuizRank
import com.example.model.QuizSettings
import com.example.model.UserAnswerRecord
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class QuizUiState(
    val phase: QuizPhase = QuizPhase.INTRO,
    val currentQuestionIndex: Int = 0,
    val score: Int = 0, // Exactly 5 points per correct answer
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isCorrect: Boolean = false,
    val isTimeExpired: Boolean = false,
    val remainingSeconds: Int = 25,
    val timerJobRunning: Boolean = false,
    val answersHistory: List<UserAnswerRecord> = emptyList(),
    val totalTimeTakenSeconds: Int = 0,
    val settings: QuizSettings = QuizSettings()
) {
    val totalQuestions: Int = QuizRepository.questions.size
    val currentQuestion: QuizQuestion
        get() = QuizRepository.questions.getOrElse(currentQuestionIndex) { QuizRepository.questions.first() }
    val isLastQuestion: Boolean
        get() = currentQuestionIndex >= totalQuestions - 1
    val finalPercentage: Int
        get() = if (totalQuestions > 0) ((score.toFloat() / (totalQuestions * 5f)) * 100f).toInt() else 0
    val finalRank: QuizRank
        get() = QuizRank.fromPercentage(finalPercentage)
}

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val soundManager = SoundManager(application)

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var questionStartTimeMs: Long = 0L

    fun startQuiz() {
        _uiState.update {
            it.copy(
                phase = QuizPhase.PLAYING,
                currentQuestionIndex = 0,
                score = 0,
                selectedOptionIndex = null,
                isAnswerSubmitted = false,
                isCorrect = false,
                isTimeExpired = false,
                remainingSeconds = it.settings.timerSeconds,
                answersHistory = emptyList(),
                totalTimeTakenSeconds = 0
            )
        }
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        val timerLimit = _uiState.value.settings.timerSeconds
        questionStartTimeMs = System.currentTimeMillis()

        if (timerLimit <= 0) {
            // Untimed mode
            _uiState.update { it.copy(remainingSeconds = 0, timerJobRunning = false) }
            return
        }

        _uiState.update { it.copy(remainingSeconds = timerLimit, timerJobRunning = true) }

        timerJob = viewModelScope.launch {
            var currentSec = timerLimit
            while (currentSec > 0) {
                delay(1000L)
                currentSec--
                _uiState.update { it.copy(remainingSeconds = currentSec) }

                // Play subtle tick sound when 5 seconds or fewer remain
                if (currentSec in 1..5) {
                    val settings = _uiState.value.settings
                    soundManager.playTimerTick(
                        soundEnabled = settings.soundEnabled,
                        hapticsEnabled = settings.hapticsEnabled
                    )
                }
            }

            // Time expired!
            onTimeExpired()
        }
    }

    private fun onTimeExpired() {
        if (_uiState.value.isAnswerSubmitted) return

        timerJob?.cancel()
        val currentQ = _uiState.value.currentQuestion
        val timeSpent = _uiState.value.settings.timerSeconds

        val record = UserAnswerRecord(
            questionId = currentQ.id,
            selectedIndex = -1, // No option picked
            isCorrect = false,
            timeSpentSeconds = timeSpent
        )

        val settings = _uiState.value.settings
        soundManager.playTimeExpired(
            soundEnabled = settings.soundEnabled,
            hapticsEnabled = settings.hapticsEnabled
        )

        _uiState.update {
            it.copy(
                phase = QuizPhase.QUESTION_FEEDBACK,
                isAnswerSubmitted = true,
                isCorrect = false,
                isTimeExpired = true,
                selectedOptionIndex = null,
                timerJobRunning = false,
                answersHistory = it.answersHistory + record,
                totalTimeTakenSeconds = it.totalTimeTakenSeconds + timeSpent
            )
        }
    }

    fun selectOption(index: Int) {
        if (_uiState.value.isAnswerSubmitted) return

        timerJob?.cancel()
        val currentQ = _uiState.value.currentQuestion
        val isCorrect = index == currentQ.correctAnswerIndex
        val pointsToAdd = if (isCorrect) 5 else 0
        val timeSpent = ((System.currentTimeMillis() - questionStartTimeMs) / 1000).toInt()
            .coerceAtLeast(1)

        val record = UserAnswerRecord(
            questionId = currentQ.id,
            selectedIndex = index,
            isCorrect = isCorrect,
            timeSpentSeconds = timeSpent
        )

        val settings = _uiState.value.settings
        if (isCorrect) {
            soundManager.playCorrect(
                soundEnabled = settings.soundEnabled,
                hapticsEnabled = settings.hapticsEnabled
            )
        } else {
            soundManager.playIncorrect(
                soundEnabled = settings.soundEnabled,
                hapticsEnabled = settings.hapticsEnabled
            )
        }

        _uiState.update {
            it.copy(
                phase = QuizPhase.QUESTION_FEEDBACK,
                selectedOptionIndex = index,
                isAnswerSubmitted = true,
                isCorrect = isCorrect,
                isTimeExpired = false,
                score = it.score + pointsToAdd,
                timerJobRunning = false,
                answersHistory = it.answersHistory + record,
                totalTimeTakenSeconds = it.totalTimeTakenSeconds + timeSpent
            )
        }
    }

    fun nextQuestion() {
        val state = _uiState.value
        if (state.isLastQuestion) {
            // Quiz completed!
            soundManager.playFanfare(
                soundEnabled = state.settings.soundEnabled,
                hapticsEnabled = state.settings.hapticsEnabled
            )
            _uiState.update {
                it.copy(
                    phase = QuizPhase.COMPLETED,
                    timerJobRunning = false
                )
            }
        } else {
            // Next question
            val nextIdx = state.currentQuestionIndex + 1
            _uiState.update {
                it.copy(
                    phase = QuizPhase.PLAYING,
                    currentQuestionIndex = nextIdx,
                    selectedOptionIndex = null,
                    isAnswerSubmitted = false,
                    isCorrect = false,
                    isTimeExpired = false,
                    remainingSeconds = it.settings.timerSeconds
                )
            }
            startTimer()
        }
    }

    fun toggleSound() {
        _uiState.update {
            val updated = it.settings.copy(soundEnabled = !it.settings.soundEnabled)
            it.copy(settings = updated)
        }
    }

    fun toggleHaptics() {
        _uiState.update {
            val updated = it.settings.copy(hapticsEnabled = !it.settings.hapticsEnabled)
            it.copy(settings = updated)
        }
    }

    fun setTimerDuration(seconds: Int) {
        _uiState.update {
            val updated = it.settings.copy(timerSeconds = seconds)
            it.copy(settings = updated, remainingSeconds = seconds)
        }
    }

    fun retakeQuiz() {
        startQuiz()
    }

    fun returnToIntro() {
        timerJob?.cancel()
        _uiState.update {
            it.copy(
                phase = QuizPhase.INTRO,
                timerJobRunning = false
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        soundManager.release()
    }
}
