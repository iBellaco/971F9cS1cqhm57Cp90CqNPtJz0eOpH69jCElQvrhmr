package com.example.util

import android.content.Context
import android.content.SharedPreferences
import coil.imageLoader
import coil.request.ImageRequest
import com.example.data.WildRiftRepository
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftSpellsAndRunes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

object ImagePrefetcher {
    private const val PREFS_NAME = "wildrift_assets_prefs"
    private const val KEY_DOWNLOADED = "assets_fully_downloaded"
    private const val CONCURRENT_DOWNLOAD_LIMIT = 16

    private val prefetchScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var activeJob: kotlinx.coroutines.Job? = null

    private val _downloadProgress = MutableStateFlow(0f)
    val downloadProgress: StateFlow<Float> = _downloadProgress
    
    private val _downloadedMb = MutableStateFlow(0f)
    val downloadedMb: StateFlow<Float> = _downloadedMb
    
    private val _isDownloading = MutableStateFlow(false)
    val isDownloading: StateFlow<Boolean> = _isDownloading
    
    private val _downloadLogs = MutableStateFlow<List<String>>(emptyList())
    val downloadLogs: StateFlow<List<String>> = _downloadLogs

    private val _isFullyDownloaded = MutableStateFlow(false)
    val isFullyDownloaded: StateFlow<Boolean> = _isFullyDownloaded
    
    val isUiMinimized = MutableStateFlow(false)
    val showProgressUi = MutableStateFlow(false)

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun init(context: Context) {
        val prefs = getPrefs(context)
        _isFullyDownloaded.value = prefs.getBoolean(KEY_DOWNLOADED, false)
    }

    private fun addLog(message: String) {
        val currentLogs = _downloadLogs.value.toMutableList()
        currentLogs.add(message)
        if (currentLogs.size > 8) {
            currentLogs.removeAt(0)
        }
        _downloadLogs.value = currentLogs
    }

    fun startPrefetch(context: Context) {
        if (_isDownloading.value) {
            showProgressUi.value = true
            isUiMinimized.value = false
            return
        }

        val appContext = context.applicationContext
        activeJob = prefetchScope.launch {
            prefetchAllImages(appContext)
        }
    }
    
    fun cancelPrefetch() {
        if (_isDownloading.value) {
            activeJob?.cancel()
            addLog("⏸️ Descarga pausada por el usuario.")
            _isDownloading.value = false
        }
    }

    private suspend fun prefetchAllImages(context: Context) {
        _isDownloading.value = true
        showProgressUi.value = true
        isUiMinimized.value = false
        _downloadLogs.value = emptyList()
        addLog("⚡ Iniciando descarga acelerada ($CONCURRENT_DOWNLOAD_LIMIT hilos paralelos)...")
        
        withContext(Dispatchers.IO) {
            data class PrefetchItem(val name: String, val category: String, val url: String)
            val itemsToPrefetch = mutableListOf<PrefetchItem>()
            
            // Collect Champion and Skill images
            WildRiftRepository.champions.forEach { champ ->
                if (champ.avatarUrl.isNotBlank()) {
                    itemsToPrefetch.add(PrefetchItem(champ.name, "Campeón", champ.avatarUrl))
                }
                champ.skills.forEach { skill ->
                    if (skill.iconUrl.isNotBlank()) {
                        itemsToPrefetch.add(PrefetchItem("${champ.name} (${skill.name})", "Habilidad", skill.iconUrl))
                    }
                }
            }

            // Collect Item images
            WildRiftItemsData.list.forEach { item ->
                if (item.iconUrl.isNotBlank()) {
                    itemsToPrefetch.add(PrefetchItem(item.name, "Objeto", item.iconUrl))
                }
            }

            // Collect Runes
            WildRiftSpellsAndRunes.runes.forEach { rune ->
                if (rune.iconUrl.isNotBlank()) {
                    itemsToPrefetch.add(PrefetchItem(rune.name, "Runa", rune.iconUrl))
                }
            }

            // Collect Summoner Spells
            WildRiftSpellsAndRunes.summonerSpells.forEach { spell ->
                if (spell.iconUrl.isNotBlank()) {
                    itemsToPrefetch.add(PrefetchItem(spell.name, "Hechizo", spell.iconUrl))
                }
            }
            
            val uniqueItems = itemsToPrefetch.filter { it.url.isNotBlank() && !it.url.startsWith("file://") }
                .distinctBy { it.url }
            val total = uniqueItems.size
            
            if (total == 0) {
                addLog("No hay recursos pendientes para descargar.")
                _isDownloading.value = false
                return@withContext
            }

            addLog("Total de elementos: $total recursos")
            
            val diskCache = context.imageLoader.diskCache
            val startSize = diskCache?.size ?: 0L
            
            val processedCounter = AtomicInteger(0)
            val downloadedCounter = AtomicInteger(0)
            val skippedCounter = AtomicInteger(0)
            val semaphore = Semaphore(CONCURRENT_DOWNLOAD_LIMIT)

            coroutineScope {
                uniqueItems.map { item ->
                    async {
                        semaphore.withPermit {
                            try {
                                val inCache = diskCache?.openSnapshot(item.url)?.use { true } ?: false
                                
                                if (inCache) {
                                    skippedCounter.incrementAndGet()
                                } else {
                                    val request = ImageRequest.Builder(context)
                                        .data(item.url)
                                        .memoryCachePolicy(coil.request.CachePolicy.DISABLED)
                                        .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                                        .build()
                                    context.imageLoader.execute(request)
                                    downloadedCounter.incrementAndGet()
                                }
                            } catch (e: kotlinx.coroutines.CancellationException) {
                                throw e
                            } catch (e: Exception) {
                                AppLogger.w("ImagePrefetcher", "Error downloading ${item.name}: ${e.message}")
                            } finally {
                                val currentProcessed = processedCounter.incrementAndGet()
                                _downloadProgress.value = currentProcessed.toFloat() / total.toFloat()

                                if (currentProcessed % 10 == 0 || currentProcessed == total) {
                                    val newSize = diskCache?.size ?: 0L
                                    val diff = (newSize - startSize).coerceAtLeast(0L)
                                    val diffMb = diff / (1024.0 * 1024.0)
                                    _downloadedMb.value = diffMb.toFloat()

                                    if (currentProcessed % 25 == 0 || currentProcessed == total) {
                                        addLog("⚡ Progreso: $currentProcessed/$total (${String.format("%.1f", diffMb)} MB)")
                                    }
                                }
                            }
                        }
                    }
                }.awaitAll()
            }
            
            val endSize = diskCache?.size ?: 0L
            val totalDownloadedMb = ((endSize - startSize).coerceAtLeast(0L)) / (1024.0 * 1024.0)
            
            val totalDownloaded = downloadedCounter.get()
            val totalSkipped = skippedCounter.get()

            if (totalDownloadedMb > 0) {
                addLog("Total descargado: ${String.format("%.2f", totalDownloadedMb)} MB")
            }
            addLog("✅ Descarga completada con éxito. ($totalDownloaded nuevos, $totalSkipped en caché)")
            _isDownloading.value = false
            _isFullyDownloaded.value = true

            try {
                getPrefs(context).edit().putBoolean(KEY_DOWNLOADED, true).apply()
            } catch (e: Exception) {
                // ignore
            }
        }
    }
}
