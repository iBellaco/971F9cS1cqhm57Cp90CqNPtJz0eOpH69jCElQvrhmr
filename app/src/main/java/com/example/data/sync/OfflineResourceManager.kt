package com.example.data.sync

import android.content.Context
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class DownloadState {
    IDLE, DOWNLOADING, PAUSED, COMPLETED, ERROR
}

object OfflineResourceManager {
    private const val PREFS_NAME = "wr_offline_resources_prefs"
    private const val KEY_COMPLETED = "resources_download_completed_v1"

    private val _downloadState = MutableStateFlow(DownloadState.IDLE)
    val downloadState: StateFlow<DownloadState> = _downloadState.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _downloadedCount = MutableStateFlow(0)
    val downloadedCount: StateFlow<Int> = _downloadedCount.asStateFlow()

    private val _totalCount = MutableStateFlow(0)
    val totalCount: StateFlow<Int> = _totalCount.asStateFlow()

    private var currentJob: Job? = null
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var isPaused = false

    private val urlsToDownload = mutableListOf<String>()

    fun isCompleted(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_COMPLETED, false)
    }

    fun markCompleted(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_COMPLETED, true).apply()
        _downloadState.value = DownloadState.COMPLETED
    }

    fun initUrls() {
        if (urlsToDownload.isNotEmpty()) return
        val urls = mutableSetOf<String>()
        
        // Champions avatars and skills
        WildRiftRepository.champions.forEach { champ ->
            if (champ.avatarUrl.isNotEmpty()) urls.add(champ.avatarUrl)
            champ.skills.forEach { skill ->
                if (skill.iconUrl.isNotEmpty()) urls.add(skill.iconUrl)
            }
        }
        
        // Items
        WildRiftItemsData.list.forEach { item ->
            if (item.iconUrl.isNotEmpty()) urls.add(item.iconUrl)
        }
        
        // Spells and Runes
        WildRiftSpellsAndRunes.summonerSpells.forEach { spell ->
            if (spell.iconUrl.isNotEmpty()) urls.add(spell.iconUrl)
        }
        WildRiftSpellsAndRunes.runes.forEach { rune ->
            if (rune.iconUrl.isNotEmpty()) urls.add(rune.iconUrl)
        }
        
        urlsToDownload.clear()
        urlsToDownload.addAll(urls.filter { it.isNotBlank() })
        _totalCount.value = urlsToDownload.size
    }

    fun startDownload(context: Context) {
        if (_downloadState.value == DownloadState.DOWNLOADING) return
        
        initUrls()
        
        // If it was paused, we don't reset count.
        // If it was completed or idle or error, we reset.
        if (_downloadState.value != DownloadState.PAUSED) {
            _downloadedCount.value = 0
            _progress.value = 0f
        }
        
        _downloadState.value = DownloadState.DOWNLOADING
        isPaused = false

        val imageLoader = context.imageLoader
        
        currentJob = scope.launch {
            try {
                var currentIdx = _downloadedCount.value
                while (currentIdx < urlsToDownload.size) {
                    if (isPaused) {
                        _downloadState.value = DownloadState.PAUSED
                        return@launch
                    }
                    
                    val url = urlsToDownload[currentIdx]
                    val request = ImageRequest.Builder(context)
                        .data(url)
                        .memoryCachePolicy(CachePolicy.DISABLED)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build()
                    
                    imageLoader.execute(request)
                    
                    currentIdx++
                    _downloadedCount.value = currentIdx
                    _progress.value = currentIdx.toFloat() / urlsToDownload.size.toFloat()
                    
                    // Small delay to prevent blocking network completely and allow cancellation checking
                    delay(25)
                }
                
                markCompleted(context)
                _downloadState.value = DownloadState.COMPLETED
            } catch (e: Exception) {
                _downloadState.value = DownloadState.ERROR
            }
        }
    }

    fun pauseDownload() {
        if (_downloadState.value == DownloadState.DOWNLOADING) {
            isPaused = true
            currentJob?.cancel()
            _downloadState.value = DownloadState.PAUSED
        }
    }

    fun resumeDownload(context: Context) {
        if (_downloadState.value == DownloadState.PAUSED) {
            startDownload(context)
        }
    }

    fun cancelDownload() {
        isPaused = true
        currentJob?.cancel()
        _downloadState.value = DownloadState.IDLE
        _downloadedCount.value = 0
        _progress.value = 0f
    }
}
