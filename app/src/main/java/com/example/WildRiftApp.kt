package com.example

import android.app.Application
import com.example.util.AppLogger
import com.google.firebase.FirebaseApp

class WildRiftApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase if not already initialized
        try {
            FirebaseApp.initializeApp(this)
            AppLogger.d("APP", "Firebase initialized successfully in Application class")
        } catch (e: Exception) {
            AppLogger.e("APP", "Failed to initialize Firebase", e)
        }

        // Global Exception Handler
        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            AppLogger.e("CRASH", "Uncaught exception on thread ${thread.name}", exception)
            val isGmsBrokerSecurityException = exception is SecurityException &&
                    (exception.message?.contains("com.google.android.gms") == true ||
                     exception.message?.contains("Unknown calling package") == true)
            if (!isGmsBrokerSecurityException) {
                // Still call the default handler for actual application crashes
                defaultExceptionHandler?.uncaughtException(thread, exception)
            } else {
                AppLogger.w("APP", "Suppressed non-fatal GMS broker security exception in background thread")
            }
        }
        
        AppLogger.d("APP", "Application started successfully.")
    }
}
