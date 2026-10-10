package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val SavannahDarkColorScheme = darkColorScheme(
    primary = SavannahAmber,
    onPrimary = Color(0xFF3E2000),
    primaryContainer = SavannahSunset,
    onPrimaryContainer = SavannahCream,
    secondary = SavannahGold,
    onSecondary = Color(0xFF332000),
    secondaryContainer = SavannahSurfaceVariant,
    onSecondaryContainer = SavannahCream,
    tertiary = SavannahSuccessGreen,
    onTertiary = Color.White,
    background = SavannahDarkBg,
    onBackground = SavannahTextPrimary,
    surface = SavannahSurface,
    onSurface = SavannahTextPrimary,
    surfaceVariant = SavannahSurfaceVariant,
    onSurfaceVariant = SavannahTextSecondary,
    outline = SavannahBorder,
    error = SavannahDangerRed,
    onError = Color.White,
    errorContainer = SavannahDangerContainer,
    onErrorContainer = Color(0xFFFFDAD6)
)

private val SavannahLightColorScheme = lightColorScheme(
    primary = SavannahSunset,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDBCF),
    onPrimaryContainer = Color(0xFF390C00),
    secondary = SavannahAmberDark,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE082),
    onSecondaryContainer = Color(0xFF261900),
    tertiary = SavannahSuccessGreen,
    onTertiary = Color.White,
    background = Color(0xFFFFF8F2),
    onBackground = Color(0xFF241A14),
    surface = Color(0xFFFFF8F2),
    onSurface = Color(0xFF241A14),
    surfaceVariant = Color(0xFFF4DDD2),
    onSurfaceVariant = Color(0xFF52433C),
    outline = Color(0xFF85736B),
    error = SavannahDangerRed,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to rich Savannah dark safari theme
    dynamicColor: Boolean = false, // Keep distinctive Savannah branding
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> SavannahDarkColorScheme
        else -> SavannahLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
