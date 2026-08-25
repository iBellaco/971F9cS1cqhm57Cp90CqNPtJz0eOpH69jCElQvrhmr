import re

with open('app/src/main/java/com/example/util/ImagePrefetcher.kt', 'w', encoding='utf-8') as f:
    f.write('''package com.example.util

import android.content.Context
import coil.imageLoader
import coil.request.ImageRequest
import com.example.data.WildRiftRepository
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftSpellsAndRunes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

object ImagePrefetcher {
    private val _downloadProgress = MutableStateFlow(0f)
    val downloadProgress: StateFlow<Float> = _downloadProgress
    
    private val _isDownloading = MutableStateFlow(false)
    val isDownloading: StateFlow<Boolean> = _isDownloading
    
    private val _downloadLogs = MutableStateFlow<List<String>>(emptyList())
    val downloadLogs: StateFlow<List<String>> = _downloadLogs
    
    val isUiMinimized = MutableStateFlow(false)
    val showProgressUi = MutableStateFlow(false)

    private fun addLog(message: String) {
        val currentLogs = _downloadLogs.value.toMutableList()
        currentLogs.add(message)
        if (currentLogs.size > 6) {
            currentLogs.removeAt(0)
        }
        _downloadLogs.value = currentLogs
    }
    
    suspend fun prefetchAllImages(context: Context) {
        if (_isDownloading.value) {
            showProgressUi.value = true
            isUiMinimized.value = false
            return
        }
        
        _isDownloading.value = true
        showProgressUi.value = true
        isUiMinimized.value = false
        _downloadProgress.value = 0f
        _downloadLogs.value = emptyList()
        addLog("Iniciando escaneo de recursos...")
        
        withContext(Dispatchers.IO) {
            val urlsToPrefetch = mutableListOf<String>()
            
            // Collect URLs
            WildRiftRepository.champions.forEach { champ ->
                urlsToPrefetch.add(champ.avatarUrl)
                champ.skills.forEach { skill -> urlsToPrefetch.add(skill.iconUrl) }
            }
            WildRiftItemsData.list.forEach { item -> urlsToPrefetch.add(item.iconUrl) }
            WildRiftSpellsAndRunes.runes.forEach { rune -> urlsToPrefetch.add(rune.iconUrl) }
            WildRiftSpellsAndRunes.summonerSpells.forEach { spell -> urlsToPrefetch.add(spell.iconUrl) }
            
            val uniqueUrls = urlsToPrefetch.filter { it.isNotBlank() && !it.startsWith("file://") }.distinct()
            val total = uniqueUrls.size
            
            addLog("Encontrados $total recursos requeridos.")
            
            val diskCache = context.imageLoader.diskCache
            
            var skipped = 0
            var downloaded = 0
            
            uniqueUrls.forEachIndexed { index, url ->
                try {
                    // In Coil 2.x diskCache key is usually the url, let's just attempt to open snapshot
                    val inCache = diskCache?.openSnapshot(url)?.use { true } ?: false
                    
                    if (inCache) {
                        skipped++
                        if (index % 15 == 0) addLog("Omitiendo (en caché): ...${url.takeLast(15)}")
                    } else {
                        addLog("Descargando: ...${url.takeLast(15)}")
                        val request = ImageRequest.Builder(context)
                            .data(url)
                            .memoryCachePolicy(coil.request.CachePolicy.DISABLED)
                            .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                            .build()
                        context.imageLoader.execute(request)
                        downloaded++
                    }
                } catch (e: Exception) {
                    AppLogger.w("ImagePrefetcher", "Error downloading $url: ${e.message}")
                    addLog("Error: ...${url.takeLast(15)}")
                }
                
                _downloadProgress.value = (index + 1).toFloat() / total.toFloat()
            }
            
            addLog("✅ Completado! Descargados: $downloaded | Omitidos: $skipped")
            _isDownloading.value = false
        }
    }
}
''')
