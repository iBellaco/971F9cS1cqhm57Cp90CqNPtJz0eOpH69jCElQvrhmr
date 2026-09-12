package com.example.service.screen

import android.graphics.Bitmap
import com.example.model.Champion
import com.example.data.WildRiftRepository
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
        if (apiKey.isEmpty() || isCallInProgress) return@withContext null
        isCallInProgress = true

        try {
            val generativeModel = GenerativeModel(
                modelName = "gemini-1.5-flash",
                apiKey = apiKey
            )

            val prompt = """
                Esta es la pantalla de 'FASE DE PREPARACIÓN' de League of Legends: Wild Rift.
                En la parte superior, hay avatares circulares de los campeones.
                Necesito que identifiques el campeonato seleccionado en el último avatar (de izquierda a derecha) 
                en el grupo superior de la ${if (isAlly) "IZQUIERDA (Aliados)" else "DERECHA (Enemigos)"}.
                Identifica el campeón (puede tener un skin).
                Responde SÓLO con el nombre del campeón de Wild Rift, nada más. Si no estás seguro o no es una pantalla válida, responde "UNKNOWN".
            """.trimIndent()

            val response = generativeModel.generateContent(
                content {
                    image(bitmap)
                    text(prompt)
                }
            )

            val text = response.text?.trim() ?: ""
            if (text.uppercase() == "UNKNOWN" || text.isEmpty()) return@withContext null

            // Buscar coincidencia de nombre
            return@withContext WildRiftRepository.champions.find { it.name.equals(text, ignoreCase = true) } 
                ?: WildRiftRepository.champions.find { text.lowercase().contains(it.name.lowercase()) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            isCallInProgress = false
        }
    }
}
