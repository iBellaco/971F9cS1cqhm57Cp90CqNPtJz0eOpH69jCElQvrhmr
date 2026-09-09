package com.example.service.screen

/**
 * Modelo de calibración geométrica de las áreas de escaneo de selección de campeones (Draft).
 * Los valores están expresados en ratios normalizados (0.0f a 1.0f) respecto a la pantalla/captura.
 */
data class VisionCalibrationConfig(
    // Posición horizontal X central de avatares (0..1)
    val allyAvatarCenterX: Float = 0.0729999989271164f,
    val enemyAvatarCenterX: Float = 0.9270000000000000f,

    // Diámetro del avatar relativo al alto de pantalla (0..1)
    val avatarDiameterRatio: Float = 0.12600000202655792f,

    // Ratios verticales Y para los 5 slots aliados (0..4)
    val allySlotYRatios: List<Float> = listOf(
        0.19499999284744263f,
        0.3310000002384186f,
        0.4650000035762787f,
        0.5979999899864197f,
        0.734000027179718f
    ),

    // Ratios verticales Y para los 5 slots enemigos (0..4)
    val enemySlotYRatios: List<Float> = listOf(
        0.19600005447864532f,
        0.32899990677833557f,
        0.4649999141693115f,
        0.603000283241272f,
        0.7390003204345703f
    ),

    // Hechizos de invocador aliados (solo aliados)
    val spellLeftRatio: Float = 0.020999999716877937f,
    val spellSizeRatio: Float = 0.04100000113248825f,
    val spellYOffsetRatio: Float = 0.0f,

    // Rango horizontal OCR para detección de nombres/roles
    val allyOcrMinX: Float = 0.05f,
    val allyOcrMaxX: Float = 0.33f,
    val enemyOcrMinX: Float = 0.67f,
    val enemyOcrMaxX: Float = 0.95f
)

