package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppNotice(
    val title: String = "Anuncio Importante",
    val content: String = "Bienvenido a Wild Rift Coach. ¡Consulta las últimas guías del parche y optimiza tu drafting!",
    val videoUrl: String = "",
    val isEnabled: Boolean = true
)

object AppNoticeManager {
    private const val PREFS_NAME = "wild_rift_app_notice_prefs"
    private const val KEY_TITLE = "notice_title"
    private const val KEY_CONTENT = "notice_content"
    private const val KEY_VIDEO_URL = "notice_video_url"
    private const val KEY_ENABLED = "notice_enabled"

    private val _notice = MutableStateFlow(AppNotice())
    val notice: StateFlow<AppNotice> = _notice.asStateFlow()

    fun init(context: Context) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val title = prefs.getString(KEY_TITLE, "Anuncio Importante") ?: "Anuncio Importante"
            val content = prefs.getString(KEY_CONTENT, "Bienvenido a Wild Rift Coach. ¡Consulta las últimas guías del parche y optimiza tu drafting!") ?: ""
            val videoUrl = prefs.getString(KEY_VIDEO_URL, "") ?: ""
            val isEnabled = prefs.getBoolean(KEY_ENABLED, true)
            _notice.value = AppNotice(title, content, videoUrl, isEnabled)
        } catch (e: Exception) {
            // Fallback
        }
    }

    fun updateNotice(context: Context, notice: AppNotice) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit()
                .putString(KEY_TITLE, notice.title)
                .putString(KEY_CONTENT, notice.content)
                .putString(KEY_VIDEO_URL, notice.videoUrl)
                .putBoolean(KEY_ENABLED, notice.isEnabled)
                .apply()
            _notice.value = notice
        } catch (e: Exception) {
            // Ignore
        }
    }
}
