package com.example.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Global theme toggle (compatibility bridge)
var isLightAppTheme: Boolean
    get() = AppThemeManager.currentTheme == AppTheme.LIGHT_HEXTECH
    set(value) {
        if (value) {
            AppThemeManager.setTheme(AppTheme.LIGHT_HEXTECH)
        } else if (AppThemeManager.currentTheme == AppTheme.LIGHT_HEXTECH) {
            AppThemeManager.setTheme(AppTheme.HEXTECH)
        }
    }

val HextechDarkBg: Color get() = AppThemeManager.currentTheme.background
val HextechSurface: Color get() = AppThemeManager.currentTheme.surface
val HextechSurfaceVariant: Color get() = AppThemeManager.currentTheme.surfaceVariant
val HextechCardBorder: Color get() = AppThemeManager.currentTheme.cardBorder

val HextechGold: Color get() = AppThemeManager.currentTheme.secondary
val HextechGoldLight: Color get() = AppThemeManager.currentTheme.secondaryLight
val HextechGoldDark: Color get() = AppThemeManager.currentTheme.secondaryDark
val HextechGoldGlow: Color get() = AppThemeManager.currentTheme.secondaryGlow

val HextechCyan: Color get() = AppThemeManager.currentTheme.primary
val HextechCyanLight: Color get() = AppThemeManager.currentTheme.primaryLight
val HextechBlue: Color get() = AppThemeManager.currentTheme.primaryDark
val HextechBlueGlow: Color get() = AppThemeManager.currentTheme.primaryGlow

val TierSPlusColor = Color(0xFF00E5FF)
val TierSColor = Color(0xFF10B981)
val TierAColor = Color(0xFFF59E0B)
val DangerRed = Color(0xFFFF4655)
val DangerRedSurface: Color get() = if (isLightAppTheme) Color(0xFFFFE3E6) else Color(0xFF33141B)
val HextechGreen = Color(0xFF22C55E)
val AllyBlue = Color(0xFF00D2D3)
val AllyBlueSurface: Color get() = if (isLightAppTheme) Color(0xFFE3F9F9) else Color(0xFF0D253A)

val TextPrimary: Color get() = AppThemeManager.currentTheme.textPrimary
val TextSecondary: Color get() = AppThemeManager.currentTheme.textSecondary
val TextMuted: Color get() = AppThemeManager.currentTheme.textMuted
val TextCyan: Color get() = AppThemeManager.currentTheme.primary

