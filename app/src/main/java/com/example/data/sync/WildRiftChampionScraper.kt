package com.example.data.sync

import android.content.Context
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
    val id: String,
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
        .build()

    private val _scraperState = MutableStateFlow(
        ScraperResultSummary(
            isRunning = false,
            progressText = "Listo para iniciar scraping de avatares (141 campeones)...",
            totalFound = 0,
            processedCount = 0,
            errorCount = 0,
            logs = emptyList(),
            isFinished = false,
            datasetPath = null
        )
    )
    val scraperState: StateFlow<ScraperResultSummary> = _scraperState.asStateFlow()

    fun safeName(name: String): String {
        val allowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_"
        val result = name.map { if (allowed.indexOf(it) >= 0) it else '_' }.joinToString("")
        return result.trim('_').ifBlank { "unknown" }
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

        // Cambiar tamaño de miniatura a 120px (ej: /40px-Aatrox... -> /120px-Aatrox...)
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

    private fun cleanChampionName(name: String): String {
        return name
            .replace(Regex("\\s+"), " ")
            .replace(" (Wild Rift)", "")
            .replace(" OriginalSquare WR", "")
            .trim()
    }

    private fun parseChampionsFromWiki(html: String): List<ChampionScraperItem> {
        val doc = Jsoup.parse(html)
        val champions = mutableListOf<ChampionScraperItem>()
        val seen = mutableSetOf<String>()

        // Buscar todas las imágenes de avatares que contengan OriginalSquare_WR o Square
        val imgs = doc.select("img")
        for (img in imgs) {
            val srcAttr = img.attr("src") + " " + img.attr("data-src") + " " + img.attr("srcset")
            if (!srcAttr.contains("Square", ignoreCase = true)) continue

            val imageUrl = extractImageUrl(img) ?: continue

            // Obtener nombre desde alt o desde el enlace/fila circundante
            var name = img.attr("alt").trim()
            if (name.isBlank() || name.contains("File:") || name.contains("Special:")) {
                val parentLink = img.parent()?.selectFirst("a[title]") ?: img.selectFirst("a[title]")
                name = parentLink?.attr("title") ?: ""
            }

            if (name.isBlank()) {
                // Intentar extraer del nombre de archivo en la URL (ej: Aatrox_OriginalSquare_WR.png)
                val match = Regex("([^/]+)_OriginalSquare").find(imageUrl)
                if (match != null) {
                    name = match.groups[1]?.value?.replace("_", " ") ?: ""
                }
            }

            name = cleanChampionName(name)
            if (name.isBlank() || name.lowercase(Locale.ROOT) in setOf("champion", "name", "icon", "role")) continue

            val id = safeName(name).lowercase(Locale.ROOT)
            if (!seen.contains(id) && name.length in 2..40) {
                seen.add(id)
                champions.add(ChampionScraperItem(id, name, imageUrl))
            }
        }

        // Fallback: si faltan campeones, buscar por filas de tablas
        if (champions.size < EXPECTED_CHAMPIONS) {
            val rows = doc.select("table tr")
            for (row in rows) {
                val imgTag = row.selectFirst("img") ?: continue
                val imageUrl = extractImageUrl(imgTag) ?: continue
                if (!imageUrl.contains("Square", ignoreCase = true)) continue

                val link = row.selectFirst("a[title]") ?: row.selectFirst("a")
                var name = link?.attr("title") ?: imgTag.attr("alt")
                name = cleanChampionName(name)

                if (name.isNotBlank() && !name.contains("File:") && name.lowercase(Locale.ROOT) !in setOf("champion", "name")) {
                    val id = safeName(name).lowercase(Locale.ROOT)
                    if (!seen.contains(id)) {
                        seen.add(id)
                        champions.add(ChampionScraperItem(id, name, imageUrl))
                    }
                }
            }
        }

        return champions
    }

    suspend fun runScraper(context: Context) {
        withContext(Dispatchers.IO) {
            val datasetDir = File(context.filesDir, "dataset")
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
                val html = response.body?.string() ?: throw IOException("HTML vacío de la Wiki")

                addLog("Analizando HTML de la Wiki oficial...")
                val championsList = parseChampionsFromWiki(html)

                addLog("Roster detectado: ${championsList.size} campeones (Esperados: $EXPECTED_CHAMPIONS).")
                _scraperState.value = _scraperState.value.copy(totalFound = championsList.size)

                var processed = 0
                var errors = 0

                for ((index, champ) in championsList.withIndex()) {
                    try {
                        addLog("[${index + 1}/${championsList.size}] Descargando 120px para ${champ.name}...")
                        val imgReq = Request.Builder()
                            .url(champ.imageUrl)
                            .header("User-Agent", "Mozilla/5.0")
                            .build()
                        val imgResp = client.newCall(imgReq).execute()
                        val imgBytes = imgResp.body?.bytes() ?: throw IOException("Imagen vacía")

                        // Carpeta exacta con el nombre del campeón en archivos internos
                        val champFolder = File(datasetDir, champ.name)
                        if (!champFolder.exists()) champFolder.mkdirs()

                        val avatarFile = File(champFolder, "avatar.png")
                        FileOutputStream(avatarFile).use { out ->
                            out.write(imgBytes)
                        }

                        processed++
                        _scraperState.value = _scraperState.value.copy(
                            processedCount = processed,
                            progressText = "Guardado: ${champ.name} (${index + 1}/${championsList.size})"
                        )
                        delay(40)
                    } catch (e: Exception) {
                        errors++
                        addLog("[ERROR] ${champ.name}: ${e.message}")
                        _scraperState.value = _scraperState.value.copy(errorCount = errors)
                    }
                }

                if (championsList.size != EXPECTED_CHAMPIONS) {
                    addLog("[AVISO] Se detectaron ${championsList.size} campeones (se esperaban $EXPECTED_CHAMPIONS).")
                }

                addLog("Scraping completado. Archivos guardados en: ${datasetDir.absolutePath}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Scraping finalizado. $processed campeones descargados (120px) en archivos.",
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
