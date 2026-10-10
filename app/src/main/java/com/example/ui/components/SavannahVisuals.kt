package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AnimalIconType
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahAmberDark
import com.example.ui.theme.SavannahCardBg
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDarkBg
import com.example.ui.theme.SavannahGold
import com.example.ui.theme.SavannahSunset

/**
 * Atmospheric Savannah Sunset canvas banner with acacia silhouette, glowing sun, and birds.
 */
@Composable
fun SavannahSunsetBanner(
    modifier: Modifier = Modifier,
    heightDp: Int = 140
) {
    val infiniteTransition = rememberInfiniteTransition(label = "savannah_glow")
    val sunGlow by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sun_pulse"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(heightDp.dp)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val height = size.height

            // Sunset gradient sky
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8B2500),
                        Color(0xFFE65100),
                        Color(0xFFFF9800),
                        Color(0xFFFFB74D),
                        Color(0xFF2E1505)
                    )
                )
            )

            // Sun disk with glowing halo
            val sunCenter = Offset(width * 0.75f, height * 0.55f)
            val sunRadius = 36.dp.toPx() * sunGlow

            drawCircle(
                color = Color(0x44FFE082),
                radius = sunRadius * 1.5f,
                center = sunCenter
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFF9C4), Color(0xFFFFD54F), Color(0xFFFF8F00)),
                    center = sunCenter,
                    radius = sunRadius
                ),
                radius = sunRadius,
                center = sunCenter
            )

            // Distant rolling hills
            val hillPath = Path().apply {
                moveTo(0f, height * 0.78f)
                cubicTo(
                    width * 0.25f, height * 0.72f,
                    width * 0.5f, height * 0.84f,
                    width, height * 0.74f
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }
            drawPath(path = hillPath, color = Color(0xFF331604))

            // Foreground ground
            val groundPath = Path().apply {
                moveTo(0f, height * 0.86f)
                cubicTo(
                    width * 0.35f, height * 0.88f,
                    width * 0.7f, height * 0.84f,
                    width, height * 0.89f
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }
            drawPath(path = groundPath, color = Color(0xFF190902))

            // Acacia Tree Silhouette on left
            val treeBaseX = width * 0.22f
            val treeBaseY = height * 0.88f
            val treeTopY = height * 0.28f

            // Trunk
            val trunkPath = Path().apply {
                moveTo(treeBaseX - 8f, treeBaseY)
                quadraticBezierTo(treeBaseX - 10f, height * 0.55f, treeBaseX - 3f, treeTopY + 15f)
                lineTo(treeBaseX + 3f, treeTopY + 15f)
                quadraticBezierTo(treeBaseX + 8f, height * 0.55f, treeBaseX + 8f, treeBaseY)
                close()
            }
            drawPath(path = trunkPath, color = Color(0xFF120501))

            // Acacia Flat Canopy (layered ovals)
            drawOval(
                color = Color(0xFF120501),
                topLeft = Offset(treeBaseX - 55.dp.toPx(), treeTopY - 10.dp.toPx()),
                size = Size(110.dp.toPx(), 22.dp.toPx())
            )
            drawOval(
                color = Color(0xFF120501),
                topLeft = Offset(treeBaseX - 35.dp.toPx(), treeTopY - 18.dp.toPx()),
                size = Size(75.dp.toPx(), 16.dp.toPx())
            )
            drawOval(
                color = Color(0xFF120501),
                topLeft = Offset(treeBaseX + 5.dp.toPx(), treeTopY - 4.dp.toPx()),
                size = Size(45.dp.toPx(), 14.dp.toPx())
            )

            // Distant acacia tree on right
            val smallTreeX = width * 0.86f
            val smallTreeY = height * 0.84f
            val smallTrunk = Path().apply {
                moveTo(smallTreeX - 3f, smallTreeY)
                lineTo(smallTreeX - 1f, smallTreeY - 22.dp.toPx())
                lineTo(smallTreeX + 1f, smallTreeY - 22.dp.toPx())
                lineTo(smallTreeX + 3f, smallTreeY)
                close()
            }
            drawPath(path = smallTrunk, color = Color(0xFF261004))
            drawOval(
                color = Color(0xFF261004),
                topLeft = Offset(smallTreeX - 22.dp.toPx(), smallTreeY - 28.dp.toPx()),
                size = Size(44.dp.toPx(), 10.dp.toPx())
            )

            // Birds in flight silhouette (V-formations)
            fun drawBird(x: Float, y: Float, scale: Float) {
                val birdPath = Path().apply {
                    moveTo(x - 8f * scale, y + 2f * scale)
                    quadraticBezierTo(x - 4f * scale, y - 5f * scale, x, y)
                    quadraticBezierTo(x + 4f * scale, y - 5f * scale, x + 8f * scale, y + 2f * scale)
                }
                drawPath(
                    path = birdPath,
                    color = Color(0xFF200902),
                    style = Stroke(width = 2f * scale, cap = StrokeCap.Round)
                )
            }

            drawBird(width * 0.45f, height * 0.32f, 1.0f)
            drawBird(width * 0.52f, height * 0.24f, 0.8f)
            drawBird(width * 0.58f, height * 0.35f, 0.65f)
        }
    }
}

/**
 * Wildlife Expert Host Card with Ranger Kofi portrait and personalized commentary.
 */
enum class ExpertReaction {
    NORMAL,
    EXCITED_CORRECT,
    CONCERNED_INCORRECT,
    WARNING_TIME
}

@Composable
fun WildlifeExpertBanner(
    hostName: String = "Ranger Kofi",
    reaction: ExpertReaction = ExpertReaction.NORMAL,
    message: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.5.dp,
                color = when (reaction) {
                    ExpertReaction.EXCITED_CORRECT -> Color(0xFF66BB6A)
                    ExpertReaction.CONCERNED_INCORRECT -> Color(0xFFEF5350)
                    ExpertReaction.WARNING_TIME -> SavannahAmber
                    ExpertReaction.NORMAL -> Color(0xFF5D4037)
                },
                shape = RoundedCornerShape(16.dp)
            ),
        color = SavannahCardBg,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Illustrated Ranger Avatar Badge
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(
                                Color(0xFFFFD54F),
                                Color(0xFFBF360C)
                            )
                        )
                    )
                    .border(2.dp, SavannahGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Ranger Emoji Avatar with Safari Hat
                Text(
                    text = when (reaction) {
                        ExpertReaction.EXCITED_CORRECT -> "🤠🎉"
                        ExpertReaction.CONCERNED_INCORRECT -> "🧐⚠️"
                        ExpertReaction.WARNING_TIME -> "⏱️⚡"
                        ExpertReaction.NORMAL -> "🤠🧭"
                    },
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = hostName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = SavannahGold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "• Safari Wildlife Expert",
                        style = MaterialTheme.typography.bodySmall,
                        color = SavannahCream.copy(alpha = 0.7f),
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SavannahCream,
                    lineHeight = 19.sp
                )
            }
        }
    }
}

/**
 * Animal Visual Card: Displays stylized badge, danger level, scientific name and category.
 */
@Composable
fun AnimalVisualCard(
    animalName: String,
    scientificName: String,
    category: String,
    dangerLevel: String,
    iconType: AnimalIconType,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, Color(0xFF5D4037), RoundedCornerShape(18.dp)),
        color = Color(0xFF22160C),
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Animal Icon Avatar Box
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF3E2723),
                                    Color(0xFF1E1007)
                                )
                            )
                        )
                        .border(1.5.dp, SavannahAmberDark, RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getAnimalEmoji(iconType),
                        fontSize = 32.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = animalName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SavannahCream
                    )
                    Text(
                        text = scientificName,
                        style = MaterialTheme.typography.bodySmall,
                        color = SavannahGold.copy(alpha = 0.85f),
                        fontSize = 12.sp
                    )
                }

                // Danger Tag
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF4A1914),
                    border = Stroke(1f).let {
                        androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEF5350))
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Danger",
                            tint = Color(0xFFFF8A80),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = dangerLevel,
                            color = Color(0xFFFFCDD2),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Category ribbon
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFF2C1E12)
                ) {
                    Text(
                        text = "Habitat: $category",
                        color = SavannahAmber,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}

fun getAnimalEmoji(iconType: AnimalIconType): String {
    return when (iconType) {
        AnimalIconType.HIPPO -> "🦛"
        AnimalIconType.MOSQUITO -> "🦟"
        AnimalIconType.CAPE_BUFFALO -> "🐃"
        AnimalIconType.BLACK_MAMBA -> "🐍"
        AnimalIconType.LEOPARD -> "🐆"
        AnimalIconType.PUFF_ADDER -> "🐍"
        AnimalIconType.CROCODILE -> "🐊"
        AnimalIconType.HYENA -> "🐺"
        AnimalIconType.BLACK_RHINO -> "🦏"
        AnimalIconType.HONEY_BADGER -> "🦡"
        AnimalIconType.TSETSE_FLY -> "🪰"
        AnimalIconType.ELEPHANT -> "🐘"
        AnimalIconType.SCORPION -> "🦂"
        AnimalIconType.OSTRICH -> "🦤"
        AnimalIconType.WILD_DOG -> "🐕"
        AnimalIconType.BOOMSLANG -> "🌿"
        AnimalIconType.BABOON -> "🐒"
        AnimalIconType.CHEETAH -> "🐆"
        AnimalIconType.GIRAFFE -> "🦒"
    }
}
