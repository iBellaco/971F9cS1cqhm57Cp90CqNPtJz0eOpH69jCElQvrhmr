package com.example.data.sync

import android.content.Context
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit

data class ChampionScraperItem(
    val name: String,
    val imageUrl: String
)

data class ScraperResultSummary(
    val isRunning: Boolean,
    val progressText: String,
    val totalFound: Int,
    val processedCount: Int,
    val errorCount: Int,
    val logs: List<String>,
    val isFinished: Boolean,
    val datasetPath: String?
)

object WildRiftChampionScraper {
    private const val TAG = "WildRiftChampionScraper"
    private const val WIKI_URL = "https://wiki.leagueoflegends.com/en-us/WR:Champion"
    private const val EXPECTED_CHAMPIONS = 141

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()

    private val _scraperState = MutableStateFlow(
        ScraperResultSummary(
            isRunning = false,
            progressText = "Listo para iniciar scraping de los 141 campeones...",
            totalFound = 0,
            processedCount = 0,
            errorCount = 0,
            logs = emptyList(),
            isFinished = false,
            datasetPath = null
        )
    )
    val scraperState: StateFlow<ScraperResultSummary> = _scraperState.asStateFlow()

    private fun cleanFolderName(name: String): String {
        val cleaned = name.replace(Regex("[\\\\/:*?\"<>|]"), "").trim()
        return cleaned.ifBlank { "unknown" }
    }

    private fun normalizeImageUrl(url: String?): String? {
        if (url.isNullOrBlank()) return null
        var trimmed = url.trim()
        if (trimmed.startsWith("//")) {
            trimmed = "https:$trimmed"
        } else if (trimmed.startsWith("/")) {
            trimmed = "https://wiki.leagueoflegends.com$trimmed"
        } else if (!trimmed.startsWith("http")) {
            trimmed = "https://wiki.leagueoflegends.com/en-us/$trimmed"
        }
        // Cambiar tamaño de miniatura a 120px
        return trimmed.replace(Regex("/\\d+px-"), "/120px-")
    }

    private fun extractImageUrl(imgTag: Element): String? {
        val attributes = listOf("data-src", "data-original", "data-image-url", "data-lazy-src", "src")
        val candidates = mutableListOf<String>()

        for (attr in attributes) {
            val value = imgTag.attr(attr)
            if (value.isNotBlank()) {
                candidates.add(value)
            }
        }

        for (attr in listOf("srcset", "data-srcset")) {
            val value = imgTag.attr(attr)
            if (value.isNotBlank()) {
                for (item in value.split(",")) {
                    val candidate = item.trim().split(" ").firstOrNull()
                    if (!candidate.isNullOrBlank()) {
                        candidates.add(candidate)
                    }
                }
            }
        }

        for (candidate in candidates) {
            val normalized = normalizeImageUrl(candidate)
            if (!normalized.isNullOrBlank() && !normalized.startsWith("data:")) {
                return normalized
            }
        }
        return null
    }

    private fun parseChampionsFromWiki(html: String): List<ChampionScraperItem> {
        val doc = Jsoup.parse(html)
        val champions = mutableListOf<ChampionScraperItem>()
        val seen = mutableSetOf<String>()

        // Buscar todas las imágenes de avatares que contengan _OriginalSquare o Square en su URL o src
        val imgs = doc.select("img")
        for (img in imgs) {
            val imageUrl = extractImageUrl(img) ?: continue
            if (!imageUrl.contains("Square", ignoreCase = true)) continue

            // Extraer nombre limpio directamente del nombre de archivo en la URL (ej: Aatrox_OriginalSquare_WR.png -> Aatrox)
            var championName = ""
            val match = Regex("([^/]+)_(?:Original)?Square").find(imageUrl)
            if (match != null) {
                championName = match.groups[1]?.value?.replace("_", " ")?.trim() ?: ""
            }

            // Fallback si la URL no tiene el patrón
            if (championName.isBlank()) {
                val alt = img.attr("alt")
                if (alt.isNotBlank() && !alt.contains("icon", ignoreCase = true) && !alt.contains("representing", ignoreCase = true)) {
                    championName = alt.replace(" (Wild Rift)", "").trim()
                }
            }

            val cleanedName = cleanFolderName(championName)
            if (cleanedName.isBlank() || cleanedName.length < 2 || cleanedName.length > 40) continue
            val lowerKey = cleanedName.lowercase(Locale.ROOT)

            if (!seen.contains(lowerKey)) {
                seen.add(lowerKey)
                champions.add(ChampionScraperItem(name = cleanedName, imageUrl = imageUrl))
            }
        }

        // Si faltan por alguna razón, buscar en celdas o filas de tablas
        if (champions.size < EXPECTED_CHAMPIONS) {
            val rows = doc.select("table tr, div.champion-card, li")
            for (row in rows) {
                val imgTag = row.selectFirst("img") ?: continue
                val imageUrl = extractImageUrl(imgTag) ?: continue
                if (!imageUrl.contains("Square", ignoreCase = true)) continue

                var championName = ""
                val match = Regex("([^/]+)_(?:Original)?Square").find(imageUrl)
                if (match != null) {
                    championName = match.groups[1]?.value?.replace("_", " ")?.trim() ?: ""
                }

                if (championName.isBlank()) {
                    val link = row.selectFirst("a[title]") ?: row.selectFirst("a")
                    val title = link?.attr("title") ?: ""
                    if (title.isNotBlank() && !title.contains("File:")) {
                        championName = title.replace(" (Wild Rift)", "").trim()
                    }
                }

                val cleanedName = cleanFolderName(championName)
                if (cleanedName.isNotBlank() && cleanedName.length in 2..40) {
                    val lowerKey = cleanedName.lowercase(Locale.ROOT)
                    if (!seen.contains(lowerKey)) {
                        seen.add(lowerKey)
                        champions.add(ChampionScraperItem(name = cleanedName, imageUrl = imageUrl))
                    }
                }
            }
        }

        return champions
    }

    suspend fun runScraper(context: Context) {
        withContext(Dispatchers.IO) {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val datasetDir = File(downloadsDir, "WildRiftChampions")
            if (!datasetDir.exists()) datasetDir.mkdirs()

            val logs = mutableListOf<String>()
            fun addLog(msg: String) {
                logs.add(msg)
                if (logs.size > 150) logs.removeAt(0)
                _scraperState.value = _scraperState.value.copy(logs = logs.toList())
            }

            _scraperState.value = ScraperResultSummary(
                isRunning = true,
                progressText = "Conectando con la Wiki oficial de Wild Rift...",
                totalFound = 0,
                processedCount = 0,
                errorCount = 0,
                logs = logs,
                isFinished = false,
                datasetPath = null
            )

            try {
                val request = Request.Builder()
                    .url(WIKI_URL)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .build()

                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    throw IOException("Error HTTP al conectar con la Wiki: ${response.code}")
                }
                val html = response.body?.string() ?: throw IOException("HTML vacío de la Wiki")

                addLog("Analizando HTML de la Wiki oficial...")
                val championsList = parseChampionsFromWiki(html)

                addLog("Roster detectado: ${championsList.size} campeones (Esperados: $EXPECTED_CHAMPIONS sí o sí).")
                _scraperState.value = _scraperState.value.copy(totalFound = championsList.size)

                var processed = 0
                var errors = 0

                for ((index, champ) in championsList.withIndex()) {
                    try {
                        addLog("[${index + 1}/${championsList.size}] Descargando 120px para ${champ.name}...")
                        val imgReq = Request.Builder()
                            .url(champ.imageUrl)
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .build()
                        val imgResp = client.newCall(imgReq).execute()
                        if (!imgResp.isSuccessful) {
                            throw IOException("HTTP ${imgResp.code} al descargar ${champ.imageUrl}")
                        }
                        val imgBytes = imgResp.body?.bytes() ?: throw IOException("Cuerpo de imagen vacío")
                        if (imgBytes.isEmpty()) {
                            throw IOException("Bytes de imagen vacíos")
                        }

                        // Carpeta en Descargas con el nombre exacto y limpio del campeón (ej: Downloads/WildRiftChampions/Aatrox/avatar.png)
                        val champFolder = File(datasetDir, champ.name)
                        if (!champFolder.exists()) {
                            champFolder.mkdirs()
                        }

                        val avatarFile = File(champFolder, "avatar.png")
                        FileOutputStream(avatarFile).use { out ->
                            out.write(imgBytes)
                        }

                        processed++
                        _scraperState.value = _scraperState.value.copy(
                            processedCount = processed,
                            progressText = "Guardado: ${champ.name} (${index + 1}/${championsList.size})"
                        )
                        delay(25)
                    } catch (e: Exception) {
                        errors++
                        addLog("[ERROR] ${champ.name}: ${e.message}")
                        _scraperState.value = _scraperState.value.copy(errorCount = errors)
                    }
                }

                if (championsList.size != EXPECTED_CHAMPIONS) {
                    addLog("[AVISO] Se detectaron ${championsList.size} campeones (se esperaban $EXPECTED_CHAMPIONS sí o sí).")
                }

                addLog("Scraping completado. Guardado en Descargas: ${datasetDir.absolutePath}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Scraping finalizado. $processed campeones guardados en Descargas.",
                    datasetPath = datasetDir.absolutePath
                )

            } catch (e: Exception) {
                addLog("[FATAL] Error en scraping: ${e.message}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Error en scraping: ${e.message}"
                )
            }
        }
    }
}
