package com.example

import android.app.Application
import android.graphics.Bitmap
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import java.util.concurrent.TimeUnit
import com.example.service.MetaScrapingWorker
import com.example.data.sync.ChineseMetaSyncService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WildRiftApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        
        setupInstantAndPeriodicScraping()
    }
    
    private fun setupInstantAndPeriodicScraping() {
        // 1. Ejecutar sincronización instantánea inmediata en segundo plano al iniciar
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ChineseMetaSyncService.syncChineseMeta(this@WildRiftApplication, forceRefresh = true)
            } catch (e: Exception) {
                Log.e("WildRiftApp", "Error en auto-sincronización instantánea", e)
            }
        }

        // 2. Encolar tarea de scraping WorkManager inmediata
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val instantWorkRequest = OneTimeWorkRequestBuilder<MetaScrapingWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "InstantMetaScrape",
            ExistingWorkPolicy.REPLACE,
            instantWorkRequest
        )
            
        // 3. Mantener worker periódico activo
        val periodicWorkRequest = PeriodicWorkRequestBuilder<MetaScrapingWorker>(
            1, TimeUnit.HOURS,
            15, TimeUnit.MINUTES
        )
        .setConstraints(constraints)
        .build()
        
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "PeriodicMetaScrape",
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWorkRequest
        )
    }
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .allowHardware(true)
            .crossfade(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .build()
    }
}
