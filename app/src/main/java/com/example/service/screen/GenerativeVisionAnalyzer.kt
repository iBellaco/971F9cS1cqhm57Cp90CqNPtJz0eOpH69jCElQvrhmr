package com.example.service.screen

import android.graphics.Bitmap
import com.example.model.Champion
import com.example.data.WildRiftRepository
import com.example.util.AppLogger
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GenerativeVisionAnalyzer {

    private const val TAG = "GenerativeVisionAnalyzer"
    
    // Configura la API key
    var apiKey: String = com.example.BuildConfig.GEMINI_API_KEY
    
    private var isCallInProgress = false

    suspend fun identifyLastPickAvatar(
        bitmap: Bitmap,
        isAlly: Boolean
    ): Champion? = withContext(Dispatchers.IO) {
        if (bitmap.isRecycled || apiKey.isEmpty() || isCallInProgress) return@withContext null
        isCallInProgress = true

        var crop: Bitmap? = null
        try {
            val generativeModel = GenerativeModel(
                modelName = "gemini-1.5-flash",
                apiKey = apiKey
            )

            // Cortar solo la franja superior de avatares para no saturar memoria ni red
            val cropWidth = (bitmap.width * 0.5f).toInt().coerceAtLeast(1)
            val cropHeight = (bitmap.height * 0.22f).toInt().coerceAtLeast(1)
            val cropLeft = if (isAlly) 0 else (bitmap.width * 0.5f).toInt()
            
            crop = if (!bitmap.isRecycled && cropLeft + cropWidth <= bitmap.width && cropHeight <= bitmap.height) {
                Bitmap.createBitmap(bitmap, cropLeft, 0, cropWidth, cropHeight)
            } else null

            val imageToSend = crop ?: bitmap
            if (imageToSend.isRecycled) return@withContext null

            val prompt = """
                Esta es la parte superior de la pantalla de 'FASE DE PREPARACIÓN' de League of Legends: Wild Rift.
                En esta franja hay avatares circulares de los campeones.
                Identifica el campeón seleccionado en el último avatar de este grupo.
                Responde SÓLO con el nombre oficial del campeón de Wild Rift, nada más. Si no estás seguro, responde "UNKNOWN".
            """.trimIndent()

            val response = generativeModel.generateContent(
                content {
                    image(imageToSend)
                    text(prompt)
                }
            )

            val text = response.text?.trim() ?: ""
            if (text.uppercase() == "UNKNOWN" || text.isEmpty()) return@withContext null

            // Buscar coincidencia de nombre
            return@withContext WildRiftRepository.champions.find { it.name.equals(text, ignoreCase = true) } 
                ?: WildRiftRepository.champions.find { text.lowercase().contains(it.name.lowercase()) }
        } catch (t: Throwable) {
            AppLogger.w(TAG, "Error seguro en análisis visual generativo: ${t.message}")
            null
        } finally {
            try { crop?.recycle() } catch (_: Throwable) {}
            isCallInProgress = false
        }
    }
}
