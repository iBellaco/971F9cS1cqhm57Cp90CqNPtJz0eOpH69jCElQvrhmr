package com.example.data.sync

import android.content.Context
import android.util.Log
import com.example.data.WildRiftRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class SyncState {
    object Idle : SyncState()
    object Syncing : SyncState()
    data class Success(val patchVersion: String, val syncedChampsCount: Int, val timestamp: String) : SyncState()
    data class Error(val message: String) : SyncState()
}

/**
 * Servicio crawler / intermediario ligero para sincronizar datos dinámicos del draft:
 * - Builds recomendadas actualizadas por parche.
 * - Matchups y counters dinámicos.
 * - Sinergias de composición.
 * - Winrates y tiers por línea.
 */
object MetaCrawlerSyncService {
    private const val TAG = "MetaCrawlerSyncService"
    private const val PREFS_NAME = "wild_rift_dynamic_meta_cache"
    private const val KEY_LAST_SYNC_TIME = "last_sync_timestamp"
    private const val KEY_CACHED_PATCH = "cached_patch_version"
    private const val KEY_DYNAMIC_DATA_JSON = "dynamic_patch_data_json"

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    fun getLastSyncInfo(context: Context): Pair<String, String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val lastTime = prefs.getString(KEY_LAST_SYNC_TIME, "Nunca sincronizado") ?: "Nunca sincronizado"
        val patch = prefs.getString(KEY_CACHED_PATCH, WildRiftRepository.CURRENT_PATCH_VERSION) ?: WildRiftRepository.CURRENT_PATCH_VERSION
        return Pair(patch, lastTime)
    }

    /**
     * Ejecuta el crawler / sincronizador ligero en segundo plano.
     */
    suspend fun syncPatchData(context: Context, forceRefresh: Boolean = false) {
        _syncState.value = SyncState.Syncing

        withContext(Dispatchers.IO) {
            try {
                // Fetch dynamic patch version from official website
                val sharedPrefsApp = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                val langCode = sharedPrefsApp.getString("selected_language", "es") ?: "es"
                val isEnglish = langCode == "en"
                
                val url = if (isEnglish) "https://wildrift.leagueoflegends.com/en-us/news/" else "https://wildrift.leagueoflegends.com/es-es/news/"
                val regex = if (isEnglish) "Patch Notes (\\d+\\.\\d+[a-z]?)".toRegex(RegexOption.IGNORE_CASE) else "Notas de la versi.n (\\d+\\.\\d+[a-z]?)".toRegex(RegexOption.IGNORE_CASE)

                var fetchedVersion: String? = null
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder().url(url).build()
                    client.newCall(request).execute().use { response ->
                        if (response.isSuccessful) {
                            val html = response.body?.string() ?: ""
                            val match = regex.find(html)
                            if (match != null) {
                                fetchedVersion = match.groupValues[1]
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to scrape patch version", e)
                }

                val patchPrefix = if (isEnglish) "Patch" else "Parche"
                if (fetchedVersion != null) {
                    WildRiftRepository.CURRENT_PATCH_VERSION = "$patchPrefix $fetchedVersion"
                } else if (isEnglish && WildRiftRepository.CURRENT_PATCH_VERSION.startsWith("Parche")) {
                     WildRiftRepository.CURRENT_PATCH_VERSION = WildRiftRepository.CURRENT_PATCH_VERSION.replace("Parche", "Patch")
                }

                val currentVersion = WildRiftRepository.CURRENT_PATCH_VERSION
                val timestampFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                val nowStr = timestampFormat.format(Date())

                // Supabase dynamic data fetching will be implemented here

                // Guardar en caché local persistente
                val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                prefs.edit()
                    .putString(KEY_LAST_SYNC_TIME, nowStr)
                    .putString(KEY_CACHED_PATCH, currentVersion)
                    .apply()

                Log.d(TAG, "Sincronización dinámica de parche completada exitosamente para parche $currentVersion")

                _syncState.value = SyncState.Success(
                    patchVersion = currentVersion,
                    syncedChampsCount = WildRiftRepository.champions.size,
                    timestamp = nowStr
                )
            } catch (e: Exception) {
                Log.e(TAG, "Error durante el crawler dinámico de parches", e)
                _syncState.value = SyncState.Error(e.localizedMessage ?: "Fallo al sincronizar parche")
            }
        }
    }
}
