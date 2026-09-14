package com.example.service.gemini

import android.graphics.Bitmap
import com.example.data.WildRiftRepository
import com.example.service.screen.LocalVisionAnalyzer
import com.example.service.screen.VisionCalibrationConfig
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class PreparationScanResult(
    val allies: List<String> = emptyList(),
    val enemies: List<String> = emptyList(),
    val userChampion: String? = null
)

/**
 * Servicio de Visión para Fase de Preparación.
 * Ejecución 100% LOCAL y OFFLINE mediante [LocalVisionAnalyzer].
 * No requiere claves de API, ni llamadas a internet, garantizando privacidad,
 * coste cero y latencia instantánea.
 */
object GeminiVisionService {
    private const val TAG = "GeminiVisionService"

    suspend fun scanFullPreparationScreen(bitmap: Bitmap): PreparationScanResult? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        try {
            val calib = VisionCalibrationConfig()
            val allChamps = WildRiftRepository.champions
            val width = bitmap.width
            val height = bitmap.height

            val topAvatarDiam = (height * calib.topAvatarDiameterRatio).toInt().coerceAtLeast(20)
            val topCenterY = (height * calib.topAvatarYRatio).toInt()

            val detectedAllies = mutableListOf<String>()
            val detectedEnemies = mutableListOf<String>()

            // Escanear los 5 avatares aliados superiores
            for (xRatio in calib.topAllyXRatios) {
                val cx = (width * xRatio).toInt()
                val crop = safeCrop(bitmap, cx, topCenterY, topAvatarDiam)
                if (crop != null) {
                    try {
                        val match = LocalVisionAnalyzer.matchAvatar(
                            crop = crop,
                            candidates = allChamps,
                            expectedRole = null
                        )
                        if (match != null) {
                            detectedAllies.add(match.first.name)
                        }
                    } finally {
                        try { crop.recycle() } catch (_: Throwable) {}
                    }
                }
            }

            // Escanear los 5 avatares rivales superiores
            for (xRatio in calib.topEnemyXRatios) {
                val cx = (width * xRatio).toInt()
                val crop = safeCrop(bitmap, cx, topCenterY, topAvatarDiam)
                if (crop != null) {
                    try {
                        val match = LocalVisionAnalyzer.matchAvatar(
                            crop = crop,
                            candidates = allChamps,
                            expectedRole = null
                        )
                        if (match != null) {
                            detectedEnemies.add(match.first.name)
                        }
                    } finally {
                        try { crop.recycle() } catch (_: Throwable) {}
                    }
                }
            }

            AppLogger.d(TAG, "Escaneo local de preparación finalizado: ${detectedAllies.size} aliados, ${detectedEnemies.size} rivales")

            if (detectedAllies.isEmpty() && detectedEnemies.isEmpty()) {
                null
            } else {
                PreparationScanResult(
                    allies = detectedAllies,
                    enemies = detectedEnemies,
                    userChampion = detectedAllies.firstOrNull()
                )
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error en escaneo local de pantalla de preparación: ${e.message}")
            null
        }
    }

    private fun safeCrop(src: Bitmap, cx: Int, cy: Int, diameter: Int): Bitmap? {
        if (src.isRecycled || diameter <= 8) return null
        val radius = diameter / 2
        val left = (cx - radius).coerceIn(0, src.width - diameter)
        val top = (cy - radius).coerceIn(0, src.height - diameter)
        if (left + diameter > src.width || top + diameter > src.height) return null

        return try {
            Bitmap.createBitmap(src, left, top, diameter, diameter)
        } catch (_: Throwable) {
            null
        }
    }
}
