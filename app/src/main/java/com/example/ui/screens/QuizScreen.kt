package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AnimalVisualCard
import com.example.ui.components.OptionCardState
import com.example.ui.components.QuestionFeedbackCard
import com.example.ui.components.QuestionOptionCard
import com.example.ui.components.QuizTimerPill
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCardBg
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDarkBg
import com.example.ui.theme.SavannahGold
import com.example.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    uiState: QuizUiState,
    onSelectOption: (Int) -> Unit,
    onNextQuestion: () -> Unit,
    onToggleSound: () -> Unit,
    onExitQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = {
                Text(
                    text = "Abandon Expedition?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SavannahCream
                )
            },
            text = {
                Text(
                    text = "If you head back to camp now, your current score of ${uiState.score} points will be lost.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SavannahCream.copy(alpha = 0.85f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showExitDialog = false
                        onExitQuiz()
                    }
                ) {
                    Text("Quit to Camp", color = Color(0xFFEF5350), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("Stay in Bush", color = SavannahAmber)
                }
            },
            containerColor = Color(0xFF2B1B10),
            tonalElevation = 6.dp
        )
    }

    val scrollState = rememberScrollState()
    val currentQuestion = uiState.currentQuestion
    val progressAnim by animateFloatAsState(
        targetValue = (uiState.currentQuestionIndex + 1).toFloat() / uiState.totalQuestions.toFloat(),
        label = "quiz_progress"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SavannahDarkBg)
    ) {
        // Sticky Header with Progress, Timer and Score
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF1E130A),
            tonalElevation = 4.dp,
            border = BorderStroke(1.dp, Color(0xFF3E2818))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Question index counter
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { showExitDialog = true },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Exit Expedition",
                                tint = SavannahCream.copy(alpha = 0.7f),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Column {
                            Text(
                                text = "Question ${uiState.currentQuestionIndex + 1} of ${uiState.totalQuestions}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = SavannahCream
                            )
                            Text(
                                text = "Score: ${uiState.score} pts",
                                style = MaterialTheme.typography.labelSmall,
                                color = SavannahGold,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Center/Right: Timer & Audio Toggle
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuizTimerPill(
                            remainingSeconds = uiState.remainingSeconds,
                            totalSeconds = uiState.settings.timerSeconds,
                            isTimerRunning = uiState.timerJobRunning
                        )

                        IconButton(
                            onClick = onToggleSound,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = if (uiState.settings.soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                contentDescription = "Toggle Audio",
                                tint = if (uiState.settings.soundEnabled) SavannahAmber else Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Bar
                LinearProgressIndicator(
                    progress = { progressAnim },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = SavannahAmber,
                    trackColor = Color(0xFF332014)
                )
            }
        }

        // Scrollable Quiz Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Animal subject visual card
            AnimalVisualCard(
                animalName = currentQuestion.animalName,
                scientificName = currentQuestion.scientificName,
                category = currentQuestion.category,
                dangerLevel = currentQuestion.dangerLevel,
                iconType = currentQuestion.iconType
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Main Question Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("question_prompt_card"),
                shape = RoundedCornerShape(18.dp),
                color = SavannahCardBg,
                border = BorderStroke(1.2.dp, Color(0xFF4E3725)),
                tonalElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = currentQuestion.question,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = SavannahCream,
                        lineHeight = 28.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Multiple Choice Options List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                currentQuestion.options.forEachIndexed { index, optionText ->
                    val optionState = when {
                        !uiState.isAnswerSubmitted -> {
                            if (uiState.selectedOptionIndex == index) OptionCardState.SELECTED
                            else OptionCardState.DEFAULT
                        }
                        index == currentQuestion.correctAnswerIndex -> OptionCardState.CORRECT
                        index == uiState.selectedOptionIndex -> OptionCardState.INCORRECT
                        else -> OptionCardState.DISABLED
                    }

                    QuestionOptionCard(
                        index = index,
                        text = optionText,
                        state = optionState,
                        onClick = { onSelectOption(index) }
                    )
                }
            }

            // Feedback Card shown immediately after submission or time expiration
            if (uiState.isAnswerSubmitted) {
                Spacer(modifier = Modifier.height(20.dp))

                QuestionFeedbackCard(
                    question = currentQuestion,
                    isCorrect = uiState.isCorrect,
                    isTimeExpired = uiState.isTimeExpired,
                    runningScore = uiState.score,
                    isLastQuestion = uiState.isLastQuestion,
                    onNextClicked = onNextQuestion
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
