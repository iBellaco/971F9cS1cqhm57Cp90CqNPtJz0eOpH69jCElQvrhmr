package com.example.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Global theme toggle
var isLightAppTheme by mutableStateOf(false)

val HextechDarkBg: Color get() = if (isLightAppTheme) Color(0xFFF8F9FA) else Color(0xFF000000)
val HextechSurface: Color get() = if (isLightAppTheme) Color(0xFFFFFFFF) else Color(0xFF0A0A0A)
val HextechSurfaceVariant: Color get() = if (isLightAppTheme) Color(0xFFF1F3F5) else Color(0xFF141414)
val HextechCardBorder: Color get() = if (isLightAppTheme) Color(0xFFDEE2E6) else Color(0xFF222222)

val HextechGold = Color(0xFFC8AA6E)
val HextechGoldLight = Color(0xFFF0E6D2)
val HextechGoldDark = Color(0xFF785A28)
val HextechGoldGlow = Color(0xFFFFD700)

val HextechCyan = Color(0xFF0AC8B9)
val HextechCyanLight = Color(0xFF00F2FE)
val HextechBlue = Color(0xFF005A82)
val HextechBlueGlow = Color(0xFF1D8CF8)

val TierSPlusColor = Color(0xFF00E5FF)
val TierSColor = Color(0xFF10B981)
val TierAColor = Color(0xFFF59E0B)
val DangerRed = Color(0xFFFF4655)
val DangerRedSurface: Color get() = if (isLightAppTheme) Color(0xFFFFE3E6) else Color(0xFF33141B)
val HextechGreen = Color(0xFF22C55E)
val AllyBlue = Color(0xFF00D2D3)
val AllyBlueSurface: Color get() = if (isLightAppTheme) Color(0xFFE3F9F9) else Color(0xFF0D253A)

val TextPrimary: Color get() = if (isLightAppTheme) Color(0xFF212529) else Color(0xFFF0E6D2)
val TextSecondary: Color get() = if (isLightAppTheme) Color(0xFF495057) else Color(0xFF94A3B8)
val TextMuted: Color get() = if (isLightAppTheme) Color(0xFF868E96) else Color(0xFF64748B)
val TextCyan = Color(0xFF0AC8B9)
