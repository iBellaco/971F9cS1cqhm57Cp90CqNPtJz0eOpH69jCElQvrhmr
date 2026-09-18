package com.example.service.screen

import android.graphics.Rect
import kotlin.math.max
import kotlin.math.min

/**
 * Motor de Diseño y Calibración Geométrica Adaptativa Multipantalla para Wild Rift.
 * 
 * Garantiza que el escaneo visual y OCR funcione de forma precisa en cualquier dispositivo móvil,
 * independientemente de la resolución (HD, FHD, 2K, QHD) o relación de aspecto:
 * - Estándar 16:9 (1.777)
 * - Panorámicas 18:9 (2.000), 19.5:9 (2.167), 20:9 (2.222), 21:9 (2.333)
 * - Tablets y Plegables 4:3 (1.333), 16:10 (1.600)
 */
object AdaptiveScreenLayoutEngine {

    // Relación de aspecto canónica de referencia para la UI de Wild Rift (16:9)
    private const val BASE_ASPECT_RATIO = 16f / 9f // ~1.7778f

    data class ScreenGeometry(
        val width: Int,
        val height: Int,
        val aspectRatio: Float,
        val isUltrawide: Boolean,
        val isTabletOrFoldable: Boolean,
        val safeHorizontalInsetRatio: Float
    )

    /**
     * Analiza las dimensiones de pantalla actuales y calcula las métricas de geometría.
     */
    fun analyzeScreen(width: Int, height: Int): ScreenGeometry {
        val w = max(width, height).toFloat()
        val h = min(width, height).toFloat().coerceAtLeast(1f)
        val ratio = w / h

        val isUltrawide = ratio > 1.85f
        val isTablet = ratio < 1.65f

        // Margen seguro adaptativo para evitar cámaras/cutouts en pantallas panorámicas
        val insetRatio = when {
            ratio >= 2.2f -> 0.045f // 20:9 o 21:9
            ratio >= 2.0f -> 0.035f // 18:9 o 19.5:9
            ratio >= 1.75f -> 0.020f // 16:9
            else -> 0.010f           // Tablets / 4:3
        }

        return ScreenGeometry(
            width = w.toInt(),
            height = h.toInt(),
            aspectRatio = ratio,
            isUltrawide = isUltrawide,
            isTabletOrFoldable = isTablet,
            safeHorizontalInsetRatio = insetRatio
        )
    }

    /**
     * Genera una configuración de calibración adaptada dinámicamente a la resolución y aspect ratio
     * del frame actual.
     */
    fun computeAdaptiveConfig(width: Int, height: Int, baseConfig: VisionCalibrationConfig = VisionCalibrationConfig()): VisionCalibrationConfig {
        if (width <= 0 || height <= 0) return baseConfig

        val geometry = analyzeScreen(width, height)
        val ratio = geometry.aspectRatio

        // Escalamiento horizontal en base al aspect ratio:
        // En Wild Rift, los elementos verticales se anclan a los extremos laterales.
        // Si la pantalla es más ancha que 16:9, los slots se sitúan a una distancia relativa fija al alto (H) desde los bordes.
        val baseH = height.toFloat()
        val baseW = width.toFloat()

        // Distancia nominal del centro del slot al borde lateral medida en múltiplos de la altura
        val slotDistanceToEdgeRatioH = 0.130f 
        val slotCenterXPxLeft = slotDistanceToEdgeRatioH * baseH + (geometry.safeHorizontalInsetRatio * baseW)
        val slotCenterXPxRight = baseW - (slotDistanceToEdgeRatioH * baseH + (geometry.safeHorizontalInsetRatio * baseW))

        val adaptiveAllyCenterX = (slotCenterXPxLeft / baseW).coerceIn(0.04f, 0.15f)
        val adaptiveEnemyCenterX = (slotCenterXPxRight / baseW).coerceIn(0.85f, 0.96f)

        // Rango de búsqueda OCR adaptativo
        // El texto del nombre de campeón aparece hacia el centro respecto al avatar.
        val allyOcrMinX = (adaptiveAllyCenterX - 0.035f).coerceAtLeast(0.01f)
        val allyOcrMaxX = (adaptiveAllyCenterX + (0.42f / (ratio / BASE_ASPECT_RATIO))).coerceIn(0.24f, 0.45f)

        val enemyOcrMinX = (adaptiveEnemyCenterX - (0.42f / (ratio / BASE_ASPECT_RATIO))).coerceIn(0.55f, 0.76f)
        val enemyOcrMaxX = (adaptiveEnemyCenterX + 0.035f).coerceAtMost(0.99f)

        // Ajuste de las posiciones horizontales de la barra superior (los 10 avatares de la cabecera)
        // En tablets los avatares superiores están ligeramente más comprimidos hacia el centro; en ultrawide hacia los bordes.
        val topAllySpacing = 0.035f * (BASE_ASPECT_RATIO / ratio)
        val topEnemySpacing = 0.035f * (BASE_ASPECT_RATIO / ratio)

        val topAllyStart = (0.025f + geometry.safeHorizontalInsetRatio * 0.5f).coerceIn(0.015f, 0.08f)
        val topEnemyEnd = (0.975f - geometry.safeHorizontalInsetRatio * 0.5f).coerceIn(0.92f, 0.985f)

        val adaptiveTopAllyXRatios = List(5) { i ->
            topAllyStart + i * topAllySpacing
        }

        val adaptiveTopEnemyXRatios = List(5) { i ->
            (topEnemyEnd - (4 - i) * topEnemySpacing).coerceIn(0.70f, 0.99f)
        }

        return baseConfig.copy(
            allyAvatarCenterX = adaptiveAllyCenterX,
            enemyAvatarCenterX = adaptiveEnemyCenterX,
            allyOcrMinX = allyOcrMinX,
            allyOcrMaxX = allyOcrMaxX,
            enemyOcrMinX = enemyOcrMinX,
            enemyOcrMaxX = enemyOcrMaxX,
            topAllyXRatios = adaptiveTopAllyXRatios,
            topEnemyXRatios = adaptiveTopEnemyXRatios,
            topAlly5XRatio = adaptiveTopAllyXRatios.lastOrNull() ?: 0.168f,
            topEnemy5XRatio = adaptiveTopEnemyXRatios.lastOrNull() ?: 0.974f
        )
    }

    /**
     * Calcula una caja de recorte segura en coordenadas de píxeles para cualquier slot o región de interés.
     */
    fun calculateSlotCropRect(
        width: Int,
        height: Int,
        isAlly: Boolean,
        slotIndex: Int,
        config: VisionCalibrationConfig
    ): Rect {
        val sIdx = slotIndex.coerceIn(0, 4)
        val cx = if (isAlly) (width * config.allyAvatarCenterX).toInt() else (width * config.enemyAvatarCenterX).toInt()
        val yRatios = if (isAlly) config.allySlotYRatios else config.enemySlotYRatios
        val cy = (height * yRatios.getOrElse(sIdx) { 0.2f + sIdx * 0.13f }).toInt()
        val diam = (height * config.avatarDiameterRatio).toInt().coerceAtLeast(24)
        val radius = diam / 2

        val left = (cx - radius).coerceIn(0, width - diam)
        val top = (cy - radius).coerceIn(0, height - diam)
        return Rect(left, top, left + diam, top + diam)
    }
}
