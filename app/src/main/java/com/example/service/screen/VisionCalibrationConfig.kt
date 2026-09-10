package com.example.service.screen

import android.content.Context
import android.content.SharedPreferences

/**
 * Modelo de calibración geométrica de las áreas de escaneo de selección de campeones (Draft).
 * Los valores están expresados en ratios normalizados (0.0f a 1.0f) respecto a la pantalla/captura.
 */
data class VisionCalibrationConfig(
    // Posición horizontal X central de avatares (0..1)
    val allyAvatarCenterX: Float = 0.073f,
    val enemyAvatarCenterX: Float = 0.957f,

    // Diámetro del avatar relativo al alto de pantalla (0..1)
    val avatarDiameterRatio: Float = 0.106f,

    // Ratios verticales Y para los 5 slots aliados (0..4)
    val allySlotYRatios: List<Float> = listOf(
        0.196f,
        0.329f,
        0.465f,
        0.603f,
        0.739f
    ),

    // Ratios verticales Y para los 5 slots enemigos (0..4)
    val enemySlotYRatios: List<Float> = listOf(
        0.196f,
        0.329f,
        0.465f,
        0.603f,
        0.739f
    ),

    // Hechizos de invocador aliados (solo aliados)
    val spellLeftRatio: Float = 0.021f,
    val spellSizeRatio: Float = 0.041f,
    val spellYOffsetRatio: Float = 0.0f,

    // Rango horizontal OCR para detección de nombres/roles
    val allyOcrMinX: Float = 0.05f,
    val allyOcrMaxX: Float = 0.33f,
    val enemyOcrMinX: Float = 0.67f,
    val enemyOcrMaxX: Float = 0.95f
) {
    fun toFormattedCoordinatesString(): String {
        val sb = StringBuilder()
        sb.append("=== COORDENADAS DE CALIBRACIÓN VISION DRAFT ===\n")
        sb.append("• Diámetro Avatar (⌀): ${(avatarDiameterRatio * 100).format(2)}% (${avatarDiameterRatio}f)\n")
        sb.append("• Centro X Aliados: ${(allyAvatarCenterX * 100).format(2)}% (${allyAvatarCenterX}f)\n")
        sb.append("• Centro X Rivales: ${(enemyAvatarCenterX * 100).format(2)}% (${enemyAvatarCenterX}f)\n\n")
        sb.append("• Slots Aliados Y:\n")
        val roles = listOf("TOP", "JUNGLE", "MID", "ADC", "SUPPORT")
        allySlotYRatios.forEachIndexed { i, y ->
            val roleName = roles.getOrElse(i) { "Slot $i" }
            sb.append("  - Slot ${i + 1} ($roleName): ${(y * 100).format(2)}% (${y}f)\n")
        }
        sb.append("\n• Slots Rivales Y:\n")
        enemySlotYRatios.forEachIndexed { i, y ->
            val roleName = roles.getOrElse(i) { "Slot $i" }
            sb.append("  - Slot ${i + 1} ($roleName): ${(y * 100).format(2)}% (${y}f)\n")
        }
        sb.append("\n• Rango OCR Aliado: ${(allyOcrMinX * 100).format(1)}% - ${(allyOcrMaxX * 100).format(1)}%\n")
        sb.append("• Rango OCR Rival: ${(enemyOcrMinX * 100).format(1)}% - ${(enemyOcrMaxX * 100).format(1)}%\n")
        sb.append("===============================================")
        return sb.toString()
    }

    fun toKotlinCode(): String {
        return """
VisionCalibrationConfig(
    allyAvatarCenterX = ${allyAvatarCenterX}f,
    enemyAvatarCenterX = ${enemyAvatarCenterX}f,
    avatarDiameterRatio = ${avatarDiameterRatio}f,
    allySlotYRatios = listOf(${allySlotYRatios.joinToString(", ") { "${it}f" }}),
    enemySlotYRatios = listOf(${enemySlotYRatios.joinToString(", ") { "${it}f" }}),
    allyOcrMinX = ${allyOcrMinX}f,
    allyOcrMaxX = ${allyOcrMaxX}f,
    enemyOcrMinX = ${enemyOcrMinX}f,
    enemyOcrMaxX = ${enemyOcrMaxX}f
)
        """.trimIndent()
    }

    fun saveToPrefs(context: Context) {
        val prefs = context.getSharedPreferences("vision_calibration_prefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putFloat("allyAvatarCenterX", allyAvatarCenterX)
            putFloat("enemyAvatarCenterX", enemyAvatarCenterX)
            putFloat("avatarDiameterRatio", avatarDiameterRatio)
            allySlotYRatios.forEachIndexed { idx, v -> putFloat("ally_slot_y_$idx", v) }
            enemySlotYRatios.forEachIndexed { idx, v -> putFloat("enemy_slot_y_$idx", v) }
            putFloat("allyOcrMinX", allyOcrMinX)
            putFloat("allyOcrMaxX", allyOcrMaxX)
            putFloat("enemyOcrMinX", enemyOcrMinX)
            putFloat("enemyOcrMaxX", enemyOcrMaxX)
            apply()
        }
    }

    companion object {
        fun loadFromPrefs(context: Context): VisionCalibrationConfig {
            val prefs = context.getSharedPreferences("vision_calibration_prefs", Context.MODE_PRIVATE)
            if (!prefs.contains("avatarDiameterRatio")) return VisionCalibrationConfig()

            val default = VisionCalibrationConfig()
            val allyY = (0..4).map { idx -> prefs.getFloat("ally_slot_y_$idx", default.allySlotYRatios[idx]) }
            val enemyY = (0..4).map { idx -> prefs.getFloat("enemy_slot_y_$idx", default.enemySlotYRatios[idx]) }

            return VisionCalibrationConfig(
                allyAvatarCenterX = prefs.getFloat("allyAvatarCenterX", default.allyAvatarCenterX),
                enemyAvatarCenterX = prefs.getFloat("enemyAvatarCenterX", default.enemyAvatarCenterX),
                avatarDiameterRatio = prefs.getFloat("avatarDiameterRatio", default.avatarDiameterRatio),
                allySlotYRatios = allyY,
                enemySlotYRatios = enemyY,
                allyOcrMinX = prefs.getFloat("allyOcrMinX", default.allyOcrMinX),
                allyOcrMaxX = prefs.getFloat("allyOcrMaxX", default.allyOcrMaxX),
                enemyOcrMinX = prefs.getFloat("enemyOcrMinX", default.enemyOcrMinX),
                enemyOcrMaxX = prefs.getFloat("enemyOcrMaxX", default.enemyOcrMaxX)
            )
        }

        private fun Float.format(digits: Int): String = "%.${digits}f".format(java.util.Locale.US, this)
    }
}


