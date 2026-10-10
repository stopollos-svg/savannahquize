package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.QuizSettings
import com.example.ui.components.SavannahSunsetBanner
import com.example.ui.components.WildlifeExpertBanner
import com.example.ui.components.ExpertReaction
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCardBg
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDarkBg
import com.example.ui.theme.SavannahGold
import com.example.ui.theme.SavannahSunset

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IntroScreen(
    settings: QuizSettings,
    onStartQuiz: () -> Unit,
    onToggleSound: () -> Unit,
    onToggleHaptics: () -> Unit,
    onSetTimerDuration: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SavannahDarkBg)
            .verticalScroll(scrollState)
    ) {
        // Hero Savannah Sunset Canvas Header
        Box(modifier = Modifier.fillMaxWidth()) {
            SavannahSunsetBanner(heightDp = 180)

            // Header Overlay Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // African Safari Badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xCC1E1005),
                    border = BorderStroke(1.dp, SavannahGold)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🦁 AFRICAN WILDLIFE EXPEDITION", color = SavannahGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Scariest Animals\nof the Savannah",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = SavannahCream,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Ranger Kofi Welcome Card
            WildlifeExpertBanner(
                hostName = "Ranger Kofi",
                reaction = ExpertReaction.NORMAL,
                message = "Jambo! Welcome to the wildest challenge in Africa! I'll test whether you have the survival instincts to outsmart our 20 deadliest creatures—from stealthy mambas to 2-ton river giants. Are you ready?"
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Quiz Rules Overview Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = SavannahCardBg,
                border = BorderStroke(1.dp, Color(0xFF4E3725))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Expedition Guidelines & Rules",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SavannahGold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    RuleItem(
                        icon = Icons.Default.Psychology,
                        title = "20 Deadly Questions",
                        description = "One question at a time across African megafauna, venomous serpents, pack hunters, and stealth predators."
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    RuleItem(
                        icon = Icons.Default.EmojiEvents,
                        title = "5 Points Per Correct Answer",
                        description = "Score up to 100 points maximum. No partial credit—every survival choice counts!"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    RuleItem(
                        icon = Icons.Default.Lightbulb,
                        title = "Fascinating Wildlife Facts",
                        description = "Learn verified biology facts and ranger commentary after every single answer."
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    RuleItem(
                        icon = Icons.Default.Alarm,
                        title = "Countdown Timer",
                        description = "Answer before time expires or the creature slips into the bush!"
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Ranks Preview Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = Color(0xFF1E130A),
                border = BorderStroke(1.dp, Color(0xFF442D1C))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Expedition Ranks to Earn",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = SavannahAmber
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RankPill(range = "0 - 30%", rank = "Tourist", emoji = "🏕️")
                        RankPill(range = "31 - 70%", rank = "Ranger Trainee", emoji = "🧭")
                        RankPill(range = "71 - 100%", rank = "Apex Predator", emoji = "🦁")
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Timer & Audio Settings
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = SavannahCardBg,
                border = BorderStroke(1.dp, Color(0xFF4A3423))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Expedition Settings",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SavannahGold
                        )

                        Row {
                            IconButton(onClick = onToggleSound) {
                                Icon(
                                    imageVector = if (settings.soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                    contentDescription = "Toggle Sound",
                                    tint = if (settings.soundEnabled) SavannahAmber else Color.Gray
                                )
                            }
                            IconButton(onClick = onToggleHaptics) {
                                Icon(
                                    imageVector = Icons.Default.Vibration,
                                    contentDescription = "Toggle Haptics",
                                    tint = if (settings.hapticsEnabled) SavannahAmber else Color.Gray
                                )
                            }
                        }
                    }

                    Text(
                        text = "Timer per question:",
                        style = MaterialTheme.typography.bodySmall,
                        color = SavannahCream.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            15 to "15s (Blitz)",
                            25 to "25s (Standard)",
                            40 to "40s (Relaxed)",
                            0 to "Untimed"
                        ).forEach { (seconds, label) ->
                            FilterChip(
                                selected = settings.timerSeconds == seconds,
                                onClick = { onSetTimerDuration(seconds) },
                                label = { Text(label, fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = SavannahAmber,
                                    selectedLabelColor = Color(0xFF221100),
                                    containerColor = Color(0xFF25170E),
                                    labelColor = SavannahCream
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    borderColor = Color(0xFF4A3423),
                                    selectedBorderColor = SavannahAmber,
                                    enabled = true,
                                    selected = settings.timerSeconds == seconds
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Primary Start Button
            Button(
                onClick = onStartQuiz,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .testTag("start_quiz_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SavannahAmber,
                    contentColor = Color(0xFF231202)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Begin Savannah Expedition",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun RuleItem(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFF382313)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SavannahAmber,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = SavannahCream
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = SavannahCream.copy(alpha = 0.75f),
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun RankPill(range: String, rank: String, emoji: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFF28190F),
        border = BorderStroke(1.dp, Color(0xFF483222))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = rank,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = SavannahCream,
                fontSize = 11.sp
            )
            Text(
                text = range,
                style = MaterialTheme.typography.labelSmall,
                color = SavannahGold.copy(alpha = 0.8f),
                fontSize = 10.sp
            )
        }
    }
}
