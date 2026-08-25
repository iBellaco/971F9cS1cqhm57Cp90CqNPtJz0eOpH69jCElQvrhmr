package com.example.util

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
    
    suspend fun prefetchAllImages(context: Context) {
        if (_isDownloading.value) return
        _isDownloading.value = true
        _downloadProgress.value = 0f
        
        withContext(Dispatchers.IO) {
            val urlsToPrefetch = mutableListOf<String>()
            
            // Champions
            WildRiftRepository.champions.forEach { champ ->
                urlsToPrefetch.add(champ.avatarUrl)
                champ.skills.forEach { skill ->
                    urlsToPrefetch.add(skill.iconUrl)
                }
            }
            
            // Items
            WildRiftItemsData.list.forEach { item ->
                urlsToPrefetch.add(item.iconUrl)
            }
            
            // Runes
            WildRiftSpellsAndRunes.runes.forEach { rune ->
                urlsToPrefetch.add(rune.iconUrl)
            }
            
            // Spells
            WildRiftSpellsAndRunes.summonerSpells.forEach { spell ->
                urlsToPrefetch.add(spell.iconUrl)
            }
            
            val uniqueUrls = urlsToPrefetch.filter { it.isNotBlank() && !it.startsWith("file://") }.distinct()
            val total = uniqueUrls.size
            
            uniqueUrls.forEachIndexed { index, url ->
                try {
                    val request = ImageRequest.Builder(context)
                        .data(url)
                        .memoryCachePolicy(coil.request.CachePolicy.DISABLED)
                        .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                        .build()
                    context.imageLoader.execute(request)
                } catch (e: Exception) {
                    AppLogger.w("ImagePrefetcher", "Error downloading $url: ${e.message}")
                }
                
                _downloadProgress.value = (index + 1).toFloat() / total.toFloat()
            }
            
            _isDownloading.value = false
        }
    }
}
