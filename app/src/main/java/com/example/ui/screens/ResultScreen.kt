package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuizRepository
import com.example.model.QuizRank
import com.example.ui.components.SavannahSunsetBanner
import com.example.ui.components.WildlifeExpertBanner
import com.example.ui.components.ExpertReaction
import com.example.ui.components.getAnimalEmoji
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCardBg
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDarkBg
import com.example.ui.theme.SavannahGold
import com.example.ui.theme.SavannahSuccessGreen
import com.example.viewmodel.QuizUiState

enum class ReviewFilter {
    ALL,
    CORRECT,
    MISSED
}

@Composable
fun ResultScreen(
    uiState: QuizUiState,
    onRetakeQuiz: () -> Unit,
    onReturnToCamp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val percentage = uiState.finalPercentage
    val rank = uiState.finalRank
    val correctCount = uiState.answersHistory.count { it.isCorrect }
    val totalCount = uiState.totalQuestions
    var reviewFilter by remember { mutableStateOf(ReviewFilter.ALL) }
    var expandedQuestionId by remember { mutableStateOf<Int?>(null) }

    val percentageProgress by animateFloatAsState(
        targetValue = percentage / 100f,
        label = "percentage_progress"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SavannahDarkBg)
    ) {
        // Sunset Canvas Top Header
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                SavannahSunsetBanner(heightDp = 150)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "EXPEDITION COMPLETE",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = SavannahGold,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Official Wildlife Debrief",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SavannahCream
                    )
                }
            }
        }

        // Score & Rank Main Hero Card
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("result_summary_card"),
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFF22160C),
                    border = BorderStroke(2.dp, SavannahAmber),
                    tonalElevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Circular percentage gauge
                        Box(
                            modifier = Modifier.size(130.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                progress = { percentageProgress },
                                modifier = Modifier.size(130.dp),
                                color = when {
                                    percentage >= 71 -> SavannahGold
                                    percentage >= 31 -> SavannahAmber
                                    else -> Color(0xFFFF7043)
                                },
                                trackColor = Color(0xFF3E2818),
                                strokeWidth = 10.dp
                            )

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$percentage%",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = SavannahCream
                                )
                                Text(
                                    text = "${uiState.score} / 100 pts",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SavannahGold,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Assigned Rank Badge
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFF332012),
                            border = BorderStroke(1.5.dp, SavannahGold)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = rank.badgeEmoji, fontSize = 28.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "EARNED RANK",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = SavannahGold,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp
                                    )
                                    Text(
                                        text = rank.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = SavannahCream
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = rank.subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = SavannahAmber
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = rank.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = SavannahCream.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Quick Statistics Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF190E07))
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatBlock(title = "Correct", value = "$correctCount / $totalCount")
                            StatBlock(title = "Accuracy", value = "${(correctCount * 100) / totalCount}%")
                            StatBlock(title = "Points", value = "${uiState.score}")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Ranger Kofi Reaction
                WildlifeExpertBanner(
                    hostName = "Ranger Kofi",
                    reaction = if (percentage >= 71) ExpertReaction.EXCITED_CORRECT else ExpertReaction.NORMAL,
                    message = when {
                        percentage >= 71 -> "Magnificent work, explorer! You identified the dangerous habits of Africa's deadliest creatures with master precision. You're ready to guide a patrol!"
                        percentage >= 31 -> "Well done, trainee! You have solid instincts and respected the big beasts. Review the questions you missed below to sharpen your edge!"
                        else -> "Phew, that was a close shave with the predators! Don't fret—the savannah is a harsh teacher. Review the facts below and take another run at the expedition!"
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onRetakeQuiz,
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .testTag("retake_quiz_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SavannahAmber,
                            contentColor = Color(0xFF221100)
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Retake Quiz", fontWeight = FontWeight.ExtraBold)
                    }

                    OutlinedButton(
                        onClick = onReturnToCamp,
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .testTag("return_to_camp_button"),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, SavannahAmber),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = SavannahAmber)
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Base Camp", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Field Guide / Question Review Section Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Field Guide Review (20 Questions)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SavannahGold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Filters: All, Correct, Missed
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = reviewFilter == ReviewFilter.ALL,
                        onClick = { reviewFilter = ReviewFilter.ALL },
                        label = { Text("All ($totalCount)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SavannahAmber,
                            selectedLabelColor = Color(0xFF221100),
                            containerColor = Color(0xFF25170E),
                            labelColor = SavannahCream
                        )
                    )
                    FilterChip(
                        selected = reviewFilter == ReviewFilter.CORRECT,
                        onClick = { reviewFilter = ReviewFilter.CORRECT },
                        label = { Text("Correct ($correctCount)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SavannahSuccessGreen,
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFF25170E),
                            labelColor = SavannahCream
                        )
                    )
                    FilterChip(
                        selected = reviewFilter == ReviewFilter.MISSED,
                        onClick = { reviewFilter = ReviewFilter.MISSED },
                        label = { Text("Missed (${totalCount - correctCount})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFFEF5350),
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFF25170E),
                            labelColor = SavannahCream
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // List of Reviewed Questions
        val filteredQuestions = QuizRepository.questions.filterIndexed { index, _ ->
            val record = uiState.answersHistory.getOrNull(index)
            val isCorrect = record?.isCorrect == true
            when (reviewFilter) {
                ReviewFilter.ALL -> true
                ReviewFilter.CORRECT -> isCorrect
                ReviewFilter.MISSED -> !isCorrect
            }
        }

        itemsIndexed(filteredQuestions) { _, question ->
            val historyRecord = uiState.answersHistory.find { it.questionId == question.id }
            val isCorrect = historyRecord?.isCorrect == true
            val isExpanded = expandedQuestionId == question.id

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .clickable {
                        expandedQuestionId = if (isExpanded) null else question.id
                    },
                shape = RoundedCornerShape(14.dp),
                color = SavannahCardBg,
                border = BorderStroke(
                    1.dp,
                    if (isCorrect) Color(0xFF2E7D32) else Color(0xFF8B2B2B)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Correct/Incorrect badge
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(if (isCorrect) SavannahSuccessGreen else Color(0xFFEF5350)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = getAnimalEmoji(question.iconType),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Q${question.id}: ${question.animalName}",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SavannahCream
                                )
                            }
                            Text(
                                text = if (isCorrect) "+5 pts" else "+0 pts",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isCorrect) Color(0xFFA5D6A7) else Color(0xFFFF8A80)
                            )
                        }

                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Expand",
                            tint = SavannahAmber
                        )
                    }

                    // Question prompt preview
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.bodySmall,
                        color = SavannahCream.copy(alpha = 0.8f),
                        lineHeight = 17.sp
                    )

                    // Expanded details
                    AnimatedVisibility(visible = isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            // User Answer
                            val selectedIdx = historyRecord?.selectedIndex ?: -1
                            val selectedText = if (selectedIdx in question.options.indices) {
                                question.options[selectedIdx]
                            } else {
                                "Time expired (No answer)"
                            }

                            Row {
                                Text(
                                    text = "Your Answer: ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = SavannahCream.copy(alpha = 0.7f)
                                )
                                Text(
                                    text = selectedText,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCorrect) Color(0xFFA5D6A7) else Color(0xFFFF8A80)
                                )
                            }

                            if (!isCorrect) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Row {
                                    Text(
                                        text = "Correct Answer: ",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = SavannahCream.copy(alpha = 0.7f)
                                    )
                                    Text(
                                        text = question.options[question.correctAnswerIndex],
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = SavannahGold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Fascinating Fact Box
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF1E130A)
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "Fascinating Fact:",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = SavannahAmber
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = question.fascinatingFact,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = SavannahCream,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun StatBlock(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = SavannahCream
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = SavannahGold.copy(alpha = 0.8f)
        )
    }
}
