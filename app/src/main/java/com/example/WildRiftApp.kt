package com.example

import android.app.Application
import android.widget.Toast
import android.os.Handler
import android.os.Looper
import android.graphics.Bitmap
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.example.data.sync.ChineseMetaSyncService
import com.example.service.MetaScrapingWorker
import com.example.util.AppLogger
import com.example.util.DynamicTranslations
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient


class WildRiftApp : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        // Global Exception Handler to guard against unexpected background thread crashes
        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            AppLogger.e("CRASH", "Uncaught exception on thread ${thread.name}", exception)
            val isGmsBrokerSecurityException = exception is SecurityException &&
                    (exception.message?.contains("com.google.android.gms") == true ||
                     exception.message?.contains("Unknown calling package") == true)
            if (!isGmsBrokerSecurityException) {
                defaultExceptionHandler?.uncaughtException(thread, exception)
            } else {
                AppLogger.w("APP", "Suppressed non-fatal GMS broker security exception in background thread")
            }
        }

        try {
            com.example.data.local.WildRiftLocalCache.loadFromLocalCache(this)
        } catch (e: Exception) {
            AppLogger.e("WildRiftApp", "Error cargando caché inicial", e)
        }
        
        // Garantizar que los campeones siempre estén en memoria (si la caché estaba vacía o corrupta)
        if (com.example.data.WildRiftRepository.champions.isEmpty()) {
            com.example.data.WildRiftRepository.initChampions(this)
            AppLogger.d("WildRiftApp", "Campeones inicializados desde JSON de emergencia.")
        }

        try {
            DynamicTranslations.loadSync(this)
        } catch (e: Exception) {
            AppLogger.e("WildRiftApp", "Error cargando traducciones dinámicas", e)
        }

        try {
            com.example.util.ImagePrefetcher.init(this)
        } catch (e: Exception) {
            AppLogger.e("WildRiftApp", "Error inicializando ImagePrefetcher", e)
        }

        setupInstantAndPeriodicScraping()
        AppLogger.d("APP", "Application started successfully.")
    }

    private fun setupInstantAndPeriodicScraping() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            AppLogger.e("WildRiftApp", "Unhandled background exception caught safely", throwable)
        }
        CoroutineScope(Dispatchers.IO + handler).launch {
                        try {
                // com.example.data.supabase.SupabaseClientManager.fetchCurrentPatchVersion()
                AppLogger.d("WildRiftApp", "Parche sincronizado desde Supabase.")
            } catch (e: Exception) {
                AppLogger.e("WildRiftApp", "Error sincronizando parche desde Supabase", e)
            }
            try {
                ChineseMetaSyncService.loadRegion(this@WildRiftApp)
                if (ChineseMetaSyncService.currentRegion.value == "CN") {
                    ChineseMetaSyncService.syncChineseMeta(this@WildRiftApp, forceRefresh = true)
                }
            } catch (e: Exception) {
                AppLogger.e("WildRiftApp", "Error en auto-sincronización instantánea", e)
            }
        }

        try {
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
        } catch (e: Exception) {
            AppLogger.e("WildRiftApp", "Error inicializando WorkManager", e)
        }
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
                    .maxSizePercent(0.05)
                    .build()
            }
            .allowHardware(true)

            .okHttpClient {
                OkHttpClient.Builder()
                    .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                    .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                            .header("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }


            

            .crossfade(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .build()
    }
}
