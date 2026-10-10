package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.QuizQuestion
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDangerRed
import com.example.ui.theme.SavannahGold
import com.example.ui.theme.SavannahSuccessGreen

@Composable
fun QuestionFeedbackCard(
    question: QuizQuestion,
    isCorrect: Boolean,
    isTimeExpired: Boolean,
    runningScore: Int,
    isLastQuestion: Boolean,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val correctAnswerText = question.options[question.correctAnswerIndex]

    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 3 })
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .testTag("question_feedback_card"),
            shape = RoundedCornerShape(20.dp),
            color = if (isCorrect) Color(0xFF1B381D) else Color(0xFF381514),
            border = BorderStroke(
                width = 2.dp,
                color = if (isCorrect) SavannahSuccessGreen else SavannahDangerRed
            ),
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                // Top Status Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(if (isCorrect) SavannahSuccessGreen else SavannahDangerRed),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when {
                                isCorrect -> Icons.Default.CheckCircle
                                isTimeExpired -> Icons.Default.TimerOff
                                else -> Icons.Default.Warning
                            },
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = when {
                                isCorrect -> "CORRECT! +5 POINTS"
                                isTimeExpired -> "TIME EXPIRED! +0 POINTS"
                                else -> "INCORRECT! +0 POINTS"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (isCorrect) Color(0xFFA5D6A7) else Color(0xFFFF8A80),
                            fontSize = 16.sp
                        )
                        Text(
                            text = if (isCorrect) "Excellent animal instinct!" else "Watch your back in the bush!",
                            style = MaterialTheme.typography.bodySmall,
                            color = SavannahCream.copy(alpha = 0.8f)
                        )
                    }

                    // Running Score Pill
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF26180D),
                        border = BorderStroke(1.dp, SavannahGold)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Score: ",
                                style = MaterialTheme.typography.labelSmall,
                                color = SavannahCream.copy(alpha = 0.8f)
                            )
                            Text(
                                text = "$runningScore",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = SavannahGold
                            )
                            Text(
                                text = " / 100",
                                style = MaterialTheme.typography.labelSmall,
                                color = SavannahCream.copy(alpha = 0.6f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Answer reveal if incorrect
                if (!isCorrect) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF230E0D),
                        border = BorderStroke(1.dp, Color(0xFF702E28))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Correct Answer: ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFCDD2)
                            )
                            Text(
                                text = correctAnswerText,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = SavannahGold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Fascinating Animal Fact Box
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFF22160C),
                    border = BorderStroke(1.dp, Color(0xFF4E3827))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = SavannahAmber,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Fascinating Fact (${question.animalName})",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = SavannahAmber
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = question.fascinatingFact,
                            style = MaterialTheme.typography.bodyMedium,
                            color = SavannahCream,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Wildlife Expert Commentary
                WildlifeExpertBanner(
                    hostName = "Ranger Kofi",
                    reaction = if (isCorrect) ExpertReaction.EXCITED_CORRECT else ExpertReaction.CONCERNED_INCORRECT,
                    message = if (isCorrect) question.expertCommentaryCorrect else question.expertCommentaryIncorrect
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Next Question Button
                Button(
                    onClick = onNextClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("next_question_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SavannahAmber,
                        contentColor = Color(0xFF241300)
                    )
                ) {
                    Text(
                        text = if (isLastQuestion) "Complete Expedition & View Rank" else "Next Question",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
