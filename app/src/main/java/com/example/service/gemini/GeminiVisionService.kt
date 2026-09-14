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

    /**
     * En Wild Rift, la barra superior contiene los BANEOS de ambos equipos, NO los campeones seleccionados.
     * Los 10 campeones del draft se leen exclusivamente a través de los slots verticales:
     * - Selecciones 1 a 9: Nombre textual por OCR.
     * - Selección 10: Retrato del slot vertical por comparación visual local con assets de avatares.
     */
    suspend fun scanFullPreparationScreen(bitmap: Bitmap): PreparationScanResult? = withContext(Dispatchers.Default) {
        // Retornar null para evitar que los baneos de la barra superior contaminen los slots de juego.
        AppLogger.d(TAG, "scanFullPreparationScreen omitido: la barra superior corresponde a baneos, no a picks.")
        null
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
