package com.example.util

import android.content.Context
import com.example.BuildConfig
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class UpdateHistoryItem(
    val versionName: String,
    val versionCode: Int,
    val date: String,
    val description: String
)

object UpdateHistoryManager {
    private const val PREF_NAME = "update_history_prefs"
    private const val KEY_HISTORY = "update_history_list"
    private const val KEY_LAST_VERSION_CODE = "last_recorded_version_code"

    fun getUpdateHistory(context: Context): List<UpdateHistoryItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val jsonStr = prefs.getString(KEY_HISTORY, null)
        
        val list = mutableListOf<UpdateHistoryItem>()
        if (!jsonStr.isNullOrBlank()) {
            try {
                val jsonArray = JSONArray(jsonStr)
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    list.add(
                        UpdateHistoryItem(
                            versionName = obj.optString("versionName", "1.0"),
                            versionCode = obj.optInt("versionCode", 1),
                            date = obj.optString("date", ""),
                            description = obj.optString("description", "")
                        )
                    )
                }
            } catch (_: Exception) {}
        }

        if (list.isEmpty()) {
            val defaultUpdates = listOf(
                UpdateHistoryItem("1.1.1.65", 573, "17/09/2026", "Actualizacion general del historial de cambios y optimizacion del sistema de registro de versiones."),
                UpdateHistoryItem("1.1.1.64", 572, "17/09/2026", "Integracion de historial de actualizaciones en la pantalla de informacion y registro automatico de versiones."),
                UpdateHistoryItem("1.1.1.63", 571, "17/09/2026", "Reubicacion de la tarjeta de recordatorio de notificaciones debajo del rendimiento en segundo plano."),
                UpdateHistoryItem("1.1.1.62", 570, "17/09/2026", "Mejoras generales en el buzon de reportes e ideas de la comunidad."),
                UpdateHistoryItem("1.1.1.61", 569, "17/09/2026", "Correccion de escala y proporcion vertical en elementos visuales con enlace web."),
                UpdateHistoryItem("1.1.1.60", 568, "17/09/2026", "Optimizacion de visualizacion multimedia y detalles de analisis del motor de draft."),
                UpdateHistoryItem("1.1.1.59", 567, "16/09/2026", "Mejoras de rendimiento en el overlay flotante y optimizacion de consumo de bateria."),
                UpdateHistoryItem("1.1.1.58", 566, "15/09/2026", "Integracion del gestor de notificaciones y alertas en tiempo real."),
                UpdateHistoryItem("1.1.1.57", 565, "14/09/2026", "Actualizacion de bases de datos de campeones, objetos y runas del parche actual de Wild Rift."),
                UpdateHistoryItem("1.1.1.56", 564, "13/09/2026", "Mejoras en el sistema de gestion de reportes y solicitudes de soporte.")
            )
            saveHistory(context, defaultUpdates)
            return defaultUpdates
        }

        return list.take(10)
    }

    fun checkAndRecordCurrentVersion(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val lastCode = prefs.getInt(KEY_LAST_VERSION_CODE, -1)
        val currentCode = BuildConfig.VERSION_CODE
        val currentName = BuildConfig.VERSION_NAME

        val currentList = getUpdateHistory(context).toMutableList()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateStr = dateFormat.format(Date())

        val existingIndex = currentList.indexOfFirst { it.versionCode == currentCode }
        val description = when (currentCode) {
            573 -> "Actualizacion general del historial de cambios y optimizacion del sistema de registro de versiones."
            572 -> "Integracion de historial de actualizaciones en la pantalla de informacion y registro automatico de versiones."
            571 -> "Reubicacion de la tarjeta de recordatorio de notificaciones debajo del rendimiento en segundo plano."
            570 -> "Mejoras generales en el buzon de reportes e ideas de la comunidad."
            569 -> "Correccion de escala y proporcion vertical en elementos visuales con enlace web."
            568 -> "Optimizacion de visualizacion multimedia y detalles de analisis del motor de draft."
            else -> "Actualizacion del sistema y optimizaciones generales del motor tactico."
        }

        if (existingIndex != -1) {
            currentList[existingIndex] = UpdateHistoryItem(currentName, currentCode, currentList[existingIndex].date, description)
        } else {
            currentList.add(0, UpdateHistoryItem(currentName, currentCode, dateStr, description))
        }

        if (lastCode != currentCode) {
            prefs.edit().putInt(KEY_LAST_VERSION_CODE, currentCode).apply()
        }

        saveHistory(context, currentList.take(10))
    }

    private fun saveHistory(context: Context, list: List<UpdateHistoryItem>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val jsonArray = JSONArray()
        for (item in list) {
            val obj = JSONObject().apply {
                put("versionName", item.versionName)
                put("versionCode", item.versionCode)
                put("date", item.date)
                put("description", item.description)
            }
            jsonArray.put(obj)
        }
        prefs.edit().putString(KEY_HISTORY, jsonArray.toString()).apply()
    }
}
