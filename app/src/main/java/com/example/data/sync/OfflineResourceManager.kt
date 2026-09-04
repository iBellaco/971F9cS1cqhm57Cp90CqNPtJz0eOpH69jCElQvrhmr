package com.example.data.sync

import android.content.Context
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.data.AvatarCatalog
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import java.util.Locale

enum class DownloadState {
    IDLE, DOWNLOADING, PAUSED, COMPLETED, ERROR
}

object OfflineResourceManager {
    private const val PREFS_NAME = "wr_offline_resources_prefs"
    private const val KEY_COMPLETED = "resources_download_completed_v1"
    private const val KEY_INDEX = "resources_download_index"
    private const val ESTIMATED_BYTES_PER_RESOURCE = 46080L // ~45 KB promedio por recurso visual

    private val _downloadState = MutableStateFlow(DownloadState.IDLE)
    val downloadState: StateFlow<DownloadState> = _downloadState.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _downloadedCount = MutableStateFlow(0)
    val downloadedCount: StateFlow<Int> = _downloadedCount.asStateFlow()

    private val _totalCount = MutableStateFlow(0)
    val totalCount: StateFlow<Int> = _totalCount.asStateFlow()

    private val _downloadedMB = MutableStateFlow(0.0f)
    val downloadedMB: StateFlow<Float> = _downloadedMB.asStateFlow()

    private val _totalMB = MutableStateFlow(0.0f)
    val totalMB: StateFlow<Float> = _totalMB.asStateFlow()

    private var currentJob: Job? = null
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    @Volatile
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

    fun initUrls(context: Context? = null) {
        val urls = mutableSetOf<String>()
        
        // 1. Champions avatars & ability icons
        WildRiftRepository.champions.forEach { champ ->
            if (champ.avatarUrl.isNotBlank()) urls.add(champ.avatarUrl.trim())
            champ.skills.forEach { skill ->
                if (skill.iconUrl.isNotBlank()) urls.add(skill.iconUrl.trim())
            }
        }
        
        // 2. Items icons
        WildRiftItemsData.list.forEach { item ->
            if (item.iconUrl.isNotBlank()) urls.add(item.iconUrl.trim())
        }
        
        // 3. Spells & Runes
        WildRiftSpellsAndRunes.summonerSpells.forEach { spell ->
            if (spell.iconUrl.isNotBlank()) urls.add(spell.iconUrl.trim())
        }
        WildRiftSpellsAndRunes.runes.forEach { rune ->
            if (rune.iconUrl.isNotBlank()) urls.add(rune.iconUrl.trim())
        }

        // 4. Avatars del catálogo
        AvatarCatalog.avatars.forEach { avatar ->
            if (avatar.imageUrl.isNotBlank()) urls.add(avatar.imageUrl.trim())
        }
        
        urlsToDownload.clear()
        urlsToDownload.addAll(urls.filter { it.isNotBlank() && (it.startsWith("http://") || it.startsWith("https://")) })
        _totalCount.value = urlsToDownload.size
        _totalMB.value = (urlsToDownload.size * ESTIMATED_BYTES_PER_RESOURCE) / (1024f * 1024f)

        context?.let { ctx ->
            if (_downloadedCount.value == 0) {
                val prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                val savedIdx = prefs.getInt(KEY_INDEX, 0)
                if (savedIdx in 1..urlsToDownload.size) {
                    _downloadedCount.value = savedIdx
                    _progress.value = savedIdx.toFloat() / urlsToDownload.size.toFloat()
                    _downloadedMB.value = (savedIdx * ESTIMATED_BYTES_PER_RESOURCE) / (1024f * 1024f)
                }
            }
        }
    }

    fun startDownload(context: Context) {
        if (_downloadState.value == DownloadState.DOWNLOADING) return
        
        initUrls(context)
        
        // Si no estaba pausado ni teníamos progreso previo, reseteamos
        if (_downloadState.value != DownloadState.PAUSED && _downloadState.value != DownloadState.IDLE) {
            _downloadedCount.value = 0
            _progress.value = 0f
            _downloadedMB.value = 0f
        }
        
        _downloadState.value = DownloadState.DOWNLOADING
        isPaused = false

        val imageLoader = context.imageLoader
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        
        currentJob?.cancel()
        currentJob = scope.launch {
            try {
                var currentIdx = _downloadedCount.value
                val totalSize = urlsToDownload.size
                if (totalSize == 0) {
                    markCompleted(context)
                    _downloadState.value = DownloadState.COMPLETED
                    return@launch
                }

                
                val concurrency = 50 // Descargar de a 50 a la vez para mayor velocidad
                while (currentIdx < totalSize) {
                    if (isPaused) {
                        _downloadState.value = DownloadState.PAUSED
                        prefs.edit().putInt(KEY_INDEX, currentIdx).apply()
                        return@launch
                    }
                    
                    val endIndex = minOf(currentIdx + concurrency, totalSize)
                    val batch = urlsToDownload.subList(currentIdx, endIndex)
                    
                    batch.map { url ->
                        async {
                            try {
                                val request = ImageRequest.Builder(context)
                                    .data(url)
                                    .memoryCachePolicy(CachePolicy.DISABLED)
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .build()
                                imageLoader.execute(request)
                            } catch (e: Exception) {
                                if (e is CancellationException) throw e
                            }
                        }
                    }.awaitAll()
                    
                    currentIdx = endIndex
                    _downloadedCount.value = currentIdx
                    _progress.value = currentIdx.toFloat() / totalSize.toFloat()
                    _downloadedMB.value = (currentIdx * ESTIMATED_BYTES_PER_RESOURCE) / (1024f * 1024f)
                    
                    prefs.edit().putInt(KEY_INDEX, currentIdx).apply()
                }
                
                prefs.edit().putInt(KEY_INDEX, totalSize).apply()
                markCompleted(context)
                _downloadState.value = DownloadState.COMPLETED
            } catch (e: Exception) {
                if (e is CancellationException || isPaused) {
                    _downloadState.value = DownloadState.PAUSED
                } else {
                    _downloadState.value = DownloadState.ERROR
                }
            }
        }
    }

    fun pauseDownload() {
        if (_downloadState.value == DownloadState.DOWNLOADING) {
            isPaused = true
            _downloadState.value = DownloadState.PAUSED
            currentJob?.cancel()
        }
    }

    fun resumeDownload(context: Context) {
        if (_downloadState.value == DownloadState.PAUSED) {
            startDownload(context)
        }
    }

    fun cancelDownload(context: Context? = null) {
        isPaused = true
        currentJob?.cancel()
        _downloadState.value = DownloadState.IDLE
        _downloadedCount.value = 0
        _progress.value = 0f
        _downloadedMB.value = 0f
        context?.let { ctx ->
            val prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putInt(KEY_INDEX, 0).apply()
        }
    }
}
