package com.example

import android.app.Application
import android.graphics.Bitmap
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.google.firebase.FirebaseApp
import android.util.Log

class WildRiftApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        try {
            FirebaseApp.initializeApp(this)
            Log.d("WildRiftApplication", "FirebaseApp successfully initialized in Application class.")
        } catch (e: Exception) {
            Log.e("WildRiftApplication", "Failed to initialize FirebaseApp", e)
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
                    .maxSizePercent(0.02)
                    .build()
            }
            .allowHardware(true)
            .crossfade(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .build()
    }
}
