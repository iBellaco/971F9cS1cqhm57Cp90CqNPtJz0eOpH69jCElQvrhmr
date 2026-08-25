package com.example.util

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppLogger {
    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs.asStateFlow()

    private val dateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    fun d(tag: String, message: String) {
        Log.d(tag, message)
        addLog("DEBUG", tag, message)
    }

    fun i(tag: String, message: String) {
        Log.i(tag, message)
        addLog("INFO", tag, message)
    }

    fun w(tag: String, message: String) {
        Log.w(tag, message)
        addLog("WARN", tag, message)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
        val errorMessage = if (throwable != null) "$message: ${throwable.message}" else message
        addLog("ERROR", tag, errorMessage)
    }

    private fun addLog(level: String, tag: String, message: String) {
        val timestamp = dateFormat.format(Date())
        val logEntry = "[$timestamp] $level/$tag: $message"
        _logs.update { currentLogs -> 
            val newLogs = currentLogs.toMutableList()
            newLogs.add(0, logEntry)
            if (newLogs.size > 200) {
                newLogs.removeAt(newLogs.lastIndex)
            }
            newLogs
        }
    }

    fun clear() {
        _logs.value = emptyList()
    }
}
