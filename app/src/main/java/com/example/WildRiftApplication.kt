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
import androidx.work.WorkManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.ExistingPeriodicWorkPolicy
import java.util.concurrent.TimeUnit
import com.example.service.MetaScrapingWorker

class WildRiftApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        
        setupDailyScraping()
    }
    
    private fun setupDailyScraping() {
        // Ejecutar diariamente solo si hay red (ahorra batería)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
            
        val dailyWorkRequest = PeriodicWorkRequestBuilder<MetaScrapingWorker>(
            24, TimeUnit.HOURS, 
            2, TimeUnit.HOURS
        )
        .setConstraints(constraints)
        .build()
        
        // Política KEEP mantiene la solicitud actual si ya existe
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DailyMetaScrape",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyWorkRequest
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
