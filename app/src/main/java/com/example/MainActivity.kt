package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.QuizPhase
import com.example.ui.screens.IntroScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SavannahQuizApp()
            }
        }
    }
}

@Composable
fun SavannahQuizApp(
    viewModel: QuizViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        when (uiState.phase) {
            QuizPhase.INTRO -> {
                IntroScreen(
                    settings = uiState.settings,
                    onStartQuiz = { viewModel.startQuiz() },
                    onToggleSound = { viewModel.toggleSound() },
                    onToggleHaptics = { viewModel.toggleHaptics() },
                    onSetTimerDuration = { viewModel.setTimerDuration(it) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            QuizPhase.PLAYING,
            QuizPhase.QUESTION_FEEDBACK -> {
                QuizScreen(
                    uiState = uiState,
                    onSelectOption = { viewModel.selectOption(it) },
                    onNextQuestion = { viewModel.nextQuestion() },
                    onToggleSound = { viewModel.toggleSound() },
                    onExitQuiz = { viewModel.returnToIntro() },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            QuizPhase.COMPLETED -> {
                ResultScreen(
                    uiState = uiState,
                    onRetakeQuiz = { viewModel.retakeQuiz() },
                    onReturnToCamp = { viewModel.returnToIntro() },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
