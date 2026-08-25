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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ImagePrefetcher {
    private const val PREFS_NAME = "wildrift_assets_prefs"
    private const val KEY_DOWNLOADED = "assets_fully_downloaded"

    private val prefetchScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _downloadProgress = MutableStateFlow(0f)
    val downloadProgress: StateFlow<Float> = _downloadProgress
    
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
        prefetchScope.launch {
            prefetchAllImages(appContext)
        }
    }
    
    private suspend fun prefetchAllImages(context: Context) {
        _isDownloading.value = true
        showProgressUi.value = true
        isUiMinimized.value = false
        _downloadProgress.value = 0f
        _downloadLogs.value = emptyList()
        addLog("Iniciando descarga de recursos en segundo plano...")
        
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
            
            addLog("Total de elementos: $total recursos")
            
            val diskCache = context.imageLoader.diskCache
            
            var skipped = 0
            var downloaded = 0
            
            var startSize = diskCache?.size ?: 0L
            
            uniqueItems.forEachIndexed { index, item ->
                try {
                    val inCache = diskCache?.openSnapshot(item.url)?.use { true } ?: false
                    
                    if (inCache) {
                        skipped++
                        if (index % 12 == 0) {
                            addLog("En caché: ${item.name}")
                        }
                    } else {
                        addLog("Descargando: ${item.name}")
                        val request = ImageRequest.Builder(context)
                            .data(item.url)
                            .memoryCachePolicy(coil.request.CachePolicy.DISABLED)
                            .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                            .build()
                        context.imageLoader.execute(request)
                        downloaded++
                        
                        val newSize = diskCache?.size ?: 0L
                        val diff = newSize - startSize
                        if (diff > 0) {
                            val diffMb = diff / (1024.0 * 1024.0)
                            if (index % 4 == 0) {
                                addLog("Descargado: ${String.format("%.1f", diffMb)} MB")
                            }
                        }
                    }
                } catch (e: Exception) {
                    AppLogger.w("ImagePrefetcher", "Error downloading ${item.name}: ${e.message}")
                    addLog("Reintentando: ${item.name}")
                }
                
                _downloadProgress.value = (index + 1).toFloat() / total.toFloat()
            }
            
            val endSize = diskCache?.size ?: 0L
            val totalDownloadedMb = (endSize - startSize) / (1024.0 * 1024.0)
            
            if (totalDownloadedMb > 0) {
                addLog("Total descargado: ${String.format("%.2f", totalDownloadedMb)} MB")
            }
            addLog("✅ Descarga completada con éxito. (${downloaded} nuevos, ${skipped} en caché)")
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
