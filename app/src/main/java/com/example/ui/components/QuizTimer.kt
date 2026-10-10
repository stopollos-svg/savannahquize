package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDangerRed
import com.example.ui.theme.SavannahSuccessGreen

@Composable
fun QuizTimerPill(
    remainingSeconds: Int,
    totalSeconds: Int,
    isTimerRunning: Boolean,
    modifier: Modifier = Modifier
) {
    if (totalSeconds <= 0) {
        // Untimed mode
        Surface(
            modifier = modifier.testTag("quiz_timer_untimed"),
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFF2C1D12),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF5D4037))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.HourglassEmpty,
                    contentDescription = "Untimed Mode",
                    tint = SavannahAmber,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Untimed",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = SavannahCream
                )
            }
        }
        return
    }

    val progress = (remainingSeconds.toFloat() / totalSeconds.toFloat()).coerceIn(0f, 1f)
    val isCritical = remainingSeconds in 1..5

    val infiniteTransition = rememberInfiniteTransition(label = "timer_pulse")
    val pulseScale by if (isCritical) {
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.12f,
            animationSpec = infiniteRepeatable(
                animation = tween(400, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse_scale"
        )
    } else {
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(1000)),
            label = "idle_scale"
        )
    }

    val timerColor by animateColorAsState(
        targetValue = when {
            remainingSeconds == 0 -> Color(0xFFB71C1C)
            isCritical -> SavannahDangerRed
            progress < 0.4f -> Color(0xFFFF9800)
            else -> SavannahSuccessGreen
        },
        label = "timer_color"
    )

    Surface(
        modifier = modifier
            .testTag("quiz_countdown_timer")
            .scale(pulseScale),
        shape = RoundedCornerShape(20.dp),
        color = if (isCritical) Color(0xFF381210) else Color(0xFF2A1C11),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isCritical) 1.5.dp else 1.dp,
            color = timerColor
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(20.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(20.dp),
                    color = timerColor,
                    trackColor = Color(0xFF3E2723),
                    strokeWidth = 2.5.dp
                )
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null,
                    tint = timerColor,
                    modifier = Modifier.size(11.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${remainingSeconds}s",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.ExtraBold,
                color = if (isCritical) Color(0xFFFF8A80) else SavannahCream,
                fontSize = 14.sp
            )
        }
    }
}
