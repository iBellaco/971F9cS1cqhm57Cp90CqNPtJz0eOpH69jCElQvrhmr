package com.example.data.sync

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

    private val client = OkHttpClient.Builder()
        .connectTimeout(25, TimeUnit.SECONDS)
        .readTimeout(25, TimeUnit.SECONDS)
        .build()

    private val _scraperState = MutableStateFlow(
        ScraperResultSummary(
            isRunning = false,
            progressText = "Listo para iniciar scraping de avatares...",
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
        val trimmed = url.trim()
        if (trimmed.startsWith("//")) {
            return "https:$trimmed"
        }
        if (trimmed.startsWith("/")) {
            return "https://wiki.leagueoflegends.com$trimmed"
        }
        if (!trimmed.startsWith("http")) {
            return "https://wiki.leagueoflegends.com/en-us/$trimmed"
        }
        return trimmed
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
        return name.replace(Regex("\\s+"), " ").trim()
    }

    private fun parseChampionsFromWiki(html: String): List<ChampionScraperItem> {
        val doc = Jsoup.parse(html)
        val champions = mutableListOf<ChampionScraperItem>()
        val seen = mutableSetOf<String>()

        // Buscar filas en tablas de la wiki
        val rows = doc.select("table tr")
        for (row in rows) {
            val imgTag = row.selectFirst("img") ?: continue
            val imageUrl = extractImageUrl(imgTag) ?: continue

            val link = row.selectFirst("a[title]") ?: row.selectFirst("a")
            var name = link?.attr("title") ?: imgTag.attr("alt")
            if (name.isBlank() || name.contains("File:") || name.contains("Special:")) {
                name = imgTag.attr("alt")
            }
            if (name.isBlank()) continue

            val lower = name.lowercase(Locale.ROOT)
            if (lower in setOf("champion", "name", "icon", "role", "difficulty", "release date")) continue

            name = name.replace(" (Wild Rift)", "").trim()
            if (name.isNotBlank()) {
                val id = safeName(name).lowercase(Locale.ROOT)
                if (!seen.contains(id)) {
                    seen.add(id)
                    champions.add(ChampionScraperItem(id, name, imageUrl))
                }
            }
        }

        // Si la tabla no bastó, buscar todas las imágenes con alt descriptivo
        if (champions.size < 10) {
            val imgs = doc.select("img")
            for (img in imgs) {
                val alt = img.attr("alt").trim()
                val imageUrl = extractImageUrl(img) ?: continue
                if (alt.isNotBlank() && !alt.endsWith(".png") && !alt.endsWith(".jpg") && !alt.contains("Logo") && !alt.contains("Icon")) {
                    val name = alt.replace(" (Wild Rift)", "").trim()
                    val id = safeName(name).lowercase(Locale.ROOT)
                    if (name.length in 2..40 && !seen.contains(id)) {
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
                if (logs.size > 120) logs.removeAt(0)
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

                addLog("Analizando página oficial de campeones...")
                val championsList = parseChampionsFromWiki(html)

                addLog("Roster detectado: ${championsList.size} campeones.")
                _scraperState.value = _scraperState.value.copy(totalFound = championsList.size)

                var processed = 0
                var errors = 0

                for ((index, champ) in championsList.withIndex()) {
                    try {
                        addLog("[${index + 1}/${championsList.size}] Descargando ${champ.name}...")
                        val imgReq = Request.Builder()
                            .url(champ.imageUrl)
                            .header("User-Agent", "Mozilla/5.0")
                            .build()
                        val imgResp = client.newCall(imgReq).execute()
                        val imgBytes = imgResp.body?.bytes() ?: throw IOException("Imagen vacía")

                        // Carpeta con el nombre exacto del campeón
                        val champFolder = File(datasetDir, champ.name)
                        if (!champFolder.exists()) champFolder.mkdirs()

                        val avatarFile = File(champFolder, "avatar.png")
                        FileOutputStream(avatarFile).use { out ->
                            out.write(imgBytes)
                        }

                        processed++
                        _scraperState.value = _scraperState.value.copy(
                            processedCount = processed,
                            progressText = "Procesado: ${champ.name} (${index + 1}/${championsList.size})"
                        )
                        delay(50)
                    } catch (e: Exception) {
                        errors++
                        addLog("[ERROR] ${champ.name}: ${e.message}")
                        _scraperState.value = _scraperState.value.copy(errorCount = errors)
                    }
                }

                addLog("Scraping completado. Carpetas de campeones creadas en: ${datasetDir.absolutePath}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Scraping finalizado. $processed campeones organizados por carpeta.",
                    datasetPath = datasetDir.absolutePath
                )

            } catch (e: Exception) {
                addLog("[FATAL] Error al conectar con la wiki: ${e.message}")
                _scraperState.value = _scraperState.value.copy(
                    isRunning = false,
                    isFinished = true,
                    progressText = "Error en scraping: ${e.message}"
                )
            }
        }
    }
}
