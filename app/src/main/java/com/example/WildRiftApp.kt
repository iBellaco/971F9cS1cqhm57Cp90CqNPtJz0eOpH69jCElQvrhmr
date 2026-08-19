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
            AppLogger.e("CRASH", "Uncaught exception on thread \${thread.name}", exception)
            // Still call the default handler so the app crashes as expected
            defaultExceptionHandler?.uncaughtException(thread, exception)
        }
        
        AppLogger.d("APP", "Application started successfully.")
    }
}
