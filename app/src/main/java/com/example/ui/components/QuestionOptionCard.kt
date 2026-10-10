package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDangerRed
import com.example.ui.theme.SavannahSuccessGreen

enum class OptionCardState {
    DEFAULT,
    SELECTED,
    CORRECT,
    INCORRECT,
    DISABLED
}

@Composable
fun QuestionOptionCard(
    index: Int,
    text: String,
    state: OptionCardState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val letter = when (index) {
        0 -> "A"
        1 -> "B"
        2 -> "C"
        3 -> "D"
        else -> "${index + 1}"
    }

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            OptionCardState.CORRECT -> Color(0xFF1E4620)
            OptionCardState.INCORRECT -> Color(0xFF4A1A18)
            OptionCardState.SELECTED -> Color(0xFF3E2817)
            OptionCardState.DISABLED -> Color(0xFF1C130B)
            OptionCardState.DEFAULT -> Color(0xFF24170E)
        },
        label = "card_bg"
    )

    val borderColor by animateColorAsState(
        targetValue = when (state) {
            OptionCardState.CORRECT -> SavannahSuccessGreen
            OptionCardState.INCORRECT -> SavannahDangerRed
            OptionCardState.SELECTED -> SavannahAmber
            OptionCardState.DISABLED -> Color(0xFF3E2C1F)
            OptionCardState.DEFAULT -> Color(0xFF4A3423)
        },
        label = "card_border"
    )

    val letterBgColor = when (state) {
        OptionCardState.CORRECT -> SavannahSuccessGreen
        OptionCardState.INCORRECT -> SavannahDangerRed
        OptionCardState.SELECTED -> SavannahAmber
        OptionCardState.DISABLED -> Color(0xFF332015)
        OptionCardState.DEFAULT -> Color(0xFF3E2818)
    }

    val letterTextColor = when (state) {
        OptionCardState.CORRECT, OptionCardState.INCORRECT -> Color.White
        OptionCardState.SELECTED -> Color(0xFF221100)
        else -> SavannahCream
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .testTag("option_$index")
            .clip(RoundedCornerShape(14.dp))
            .clickable(enabled = state == OptionCardState.DEFAULT || state == OptionCardState.SELECTED) {
                onClick()
            },
        shape = RoundedCornerShape(14.dp),
        color = backgroundColor,
        border = BorderStroke(1.5.dp, borderColor),
        tonalElevation = if (state == OptionCardState.SELECTED) 4.dp else 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Option letter pill (A, B, C, D)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(letterBgColor),
                contentAlignment = Alignment.Center
            ) {
                if (state == OptionCardState.CORRECT) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Correct Answer",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                } else if (state == OptionCardState.INCORRECT) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Incorrect Answer",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        text = letter,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = letterTextColor,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (state == OptionCardState.CORRECT || state == OptionCardState.SELECTED) {
                    FontWeight.Bold
                } else {
                    FontWeight.Medium
                },
                color = when (state) {
                    OptionCardState.CORRECT -> Color(0xFFA5D6A7)
                    OptionCardState.INCORRECT -> Color(0xFFFFCDD2)
                    OptionCardState.DISABLED -> SavannahCream.copy(alpha = 0.5f)
                    else -> SavannahCream
                },
                modifier = Modifier.weight(1f),
                lineHeight = 20.sp
            )
        }
    }
}
