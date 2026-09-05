package com.example.util

import android.content.Context
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.util.Date

object CrashLogger {
    private var isInitialized = false

    fun init(context: Context) {
        if (isInitialized) return
        isInitialized = true
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                val file = File(context.filesDir, "crash_log.txt")
                val writer = PrintWriter(FileWriter(file, true))
                writer.println("---- CRASH at ${Date()} ----")
                writer.println("Thread: ${thread.name}")
                throwable.printStackTrace(writer)
                writer.println("--------------------------------")
                writer.close()
            } catch (e: Exception) {
                // Ignore
            }
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }
}
