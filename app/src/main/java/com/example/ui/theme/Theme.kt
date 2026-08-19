package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.example.util.tr
import androidx.compose.ui.graphics.Color

private val HextechColorScheme = darkColorScheme(
    primary = HextechCyan,
    onPrimary = HextechDarkBg,
    primaryContainer = HextechBlue,
    onPrimaryContainer = HextechCyanLight,
    secondary = HextechGold,
    onSecondary = HextechDarkBg,
    secondaryContainer = HextechSurfaceVariant,
    onSecondaryContainer = HextechGoldLight,
    tertiary = TierSPlusColor,
    onTertiary = HextechDarkBg,
    background = HextechDarkBg,
    onBackground = TextPrimary,
    surface = HextechSurface,
    onSurface = TextPrimary,
    surfaceVariant = HextechSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    error = DangerRed,
    onError = Color.White,
    outline = HextechCardBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use intentional Hextech branding
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = HextechColorScheme,
        typography = Typography,
        content = content
    )
}
