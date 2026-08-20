package com.example

import android.app.Application
import com.example.util.AppLogger

class WildRiftApp : Application() {
    override fun onCreate() {
        super.onCreate()
        


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
