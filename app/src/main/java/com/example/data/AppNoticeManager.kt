package com.example.data

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

data class AppNotice(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "Anuncio Importante",
    val content: String = "Bienvenido a Wild Rift Coach. ¡Consulta las últimas guías del parche y optimiza tu drafting!",
    val videoUrl: String = "", // Multimedia horizontal para panel de inicio
    val expandedImageUrl: String = "", // Imagen vertical para vista ampliada
    val tag: String = "Anuncios importantes", // "Anuncios importantes", "Ofertas", "Mantenimiento", "Noticia"
    val isEnabled: Boolean = true
)

object AppNoticeManager {
    private const val PREFS_NAME = "wild_rift_app_notices_prefs"
    private const val KEY_NOTICES_JSON = "notices_json_list"
    private const val KEY_INTERVAL_VALUE = "streamer_interval_value"
    private const val KEY_INTERVAL_UNIT = "streamer_interval_unit"

    private val defaultNotices = listOf(
        AppNotice(
            title = "¡Nueva Actualización de Parche disponible!",
            content = "Consulta las tier lists actualizadas y optimiza tus picks para subir a Challenger.",
            tag = "Anuncios importantes",
            isEnabled = true
        ),
        AppNotice(
            title = "Oferta Especial en Esencias Azules",
            content = "¡Desbloquea avatares legendarios y bordes exclusivos con descuento por tiempo limitado!",
            tag = "Ofertas",
            isEnabled = true
        )
    )

    private val _notices = MutableStateFlow(defaultNotices)
    val notices: StateFlow<List<AppNotice>> = _notices.asStateFlow()

    private val _streamerIntervalValue = MutableStateFlow(10)
    val streamerIntervalValue: StateFlow<Int> = _streamerIntervalValue.asStateFlow()

    private val _streamerIntervalUnit = MutableStateFlow("seconds")
    val streamerIntervalUnit: StateFlow<String> = _streamerIntervalUnit.asStateFlow()

    fun init(context: Context) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val jsonStr = prefs.getString(KEY_NOTICES_JSON, null)
            val intVal = prefs.getInt(KEY_INTERVAL_VALUE, 10)
            val intUnit = prefs.getString(KEY_INTERVAL_UNIT, "seconds") ?: "seconds"
            _streamerIntervalValue.value = intVal
            _streamerIntervalUnit.value = intUnit

            if (!jsonStr.isNullOrBlank()) {
                val arr = JSONArray(jsonStr)
                val list = mutableListOf<AppNotice>()
                for (i in 0 until arr.length()) {
                    val obj = arr.getJSONObject(i)
                    list.add(
                        AppNotice(
                            id = obj.optString("id", UUID.randomUUID().toString()),
                            title = obj.optString("title", "Aviso"),
                            content = obj.optString("content", ""),
                            videoUrl = obj.optString("videoUrl", ""),
                            expandedImageUrl = obj.optString("expandedImageUrl", ""),
                            tag = obj.optString("tag", "Anuncios importantes"),
                            isEnabled = obj.optBoolean("isEnabled", true)
                        )
                    )
                }
                if (list.isNotEmpty()) {
                    _notices.value = list
                }
            }
        } catch (e: Exception) {
            _notices.value = defaultNotices
        }
    }

    fun saveStreamerInterval(context: Context, value: Int, unit: String) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit()
                .putInt(KEY_INTERVAL_VALUE, value)
                .putString(KEY_INTERVAL_UNIT, unit)
                .apply()
            _streamerIntervalValue.value = value
            _streamerIntervalUnit.value = unit
        } catch (_: Exception) {}
    }

    fun getStreamerIntervalMillis(context: Context): Long {
        val value = _streamerIntervalValue.value.coerceAtLeast(1)
        return when (_streamerIntervalUnit.value) {
            "minutes" -> value * 60 * 1000L
            "hours" -> value * 60 * 60 * 1000L
            else -> value * 1000L
        }
    }

    fun saveNotices(context: Context, newNotices: List<AppNotice>) {
        try {
            val arr = JSONArray()
            for (n in newNotices) {
                val obj = JSONObject().apply {
                    put("id", n.id)
                    put("title", n.title)
                    put("content", n.content)
                    put("videoUrl", n.videoUrl)
                    put("expandedImageUrl", n.expandedImageUrl)
                    put("tag", n.tag)
                    put("isEnabled", n.isEnabled)
                }
                arr.put(obj)
            }
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_NOTICES_JSON, arr.toString()).apply()
            _notices.value = newNotices
        } catch (e: Exception) {
            // Ignore
        }
    }

    // For backward compatibility if single update is called
    fun updateNotice(context: Context, notice: AppNotice) {
        val current = _notices.value.toMutableList()
        val index = current.indexOfFirst { it.id == notice.id }
        if (index >= 0) {
            current[index] = notice
        } else {
            current.add(notice)
        }
        saveNotices(context, current)
    }
}
