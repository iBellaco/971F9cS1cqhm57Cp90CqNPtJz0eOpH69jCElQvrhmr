package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Gestor centralizado para los campeones favoritos / mains del usuario.
 * Persiste los IDs de los campeones en SharedPreferences y expone un StateFlow reactivo.
 */
object FavoriteChampionsManager {

    private const val PREFS_NAME = "wildrift_favorite_champions"
    private const val KEY_FAVORITES = "favorite_champ_ids"

    private val _favoritesFlow = MutableStateFlow<Set<String>>(emptySet())
    val favoritesFlow: StateFlow<Set<String>> = _favoritesFlow.asStateFlow()

    private var initialized = false

    fun init(context: Context) {
        if (initialized) return
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val saved = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        _favoritesFlow.value = saved.toSet()
        initialized = true
    }

    fun isFavorite(champId: String): Boolean {
        return _favoritesFlow.value.contains(champId.lowercase())
    }

    fun toggleFavorite(context: Context, champId: String): Boolean {
        init(context)
        val id = champId.lowercase()
        val current = _favoritesFlow.value.toMutableSet()
        val isNowFav = if (current.contains(id)) {
            current.remove(id)
            false
        } else {
            current.add(id)
            true
        }
        _favoritesFlow.value = current
        saveToPrefs(context, current)
        return isNowFav
    }

    fun setFavorite(context: Context, champId: String, isFavorite: Boolean) {
        init(context)
        val id = champId.lowercase()
        val current = _favoritesFlow.value.toMutableSet()
        if (isFavorite) {
            current.add(id)
        } else {
            current.remove(id)
        }
        _favoritesFlow.value = current
        saveToPrefs(context, current)
    }

    private fun saveToPrefs(context: Context, set: Set<String>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putStringSet(KEY_FAVORITES, set).apply()
    }
}
