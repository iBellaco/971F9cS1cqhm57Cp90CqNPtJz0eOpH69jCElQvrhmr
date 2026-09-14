package com.example.service.screen

import android.graphics.Bitmap
import com.example.model.Champion
import com.example.data.WildRiftRepository
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Adaptador de compatibilidad para análisis visual de avatares.
 * Toda la lógica se delega ahora a [LocalVisionAnalyzer], ejecutándose de forma 100%
 * local, offline y sin consumo de APIs externas.
 */
object GenerativeVisionAnalyzer {

    private const val TAG = "GenerativeVisionAnalyzer"

    suspend fun identifyLastPickAvatar(
        bitmap: Bitmap,
        isAlly: Boolean,
        targetTopEnemy: Boolean = true
    ): Champion? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        try {
            val calib = VisionCalibrationConfig()
            val allChamps = WildRiftRepository.champions
            val result = LocalVisionAnalyzer.identify10thPickLocal(
                bitmap = bitmap,
                isAlly = isAlly,
                calib = calib,
                allChamps = allChamps,
                confirmedIds = emptySet(),
                expectedRole = null
            )
            result?.first
        } catch (t: Throwable) {
            AppLogger.w(TAG, "Error en análisis visual local de avatar: ${t.message}")
            null
        }
    }
}
