package com.example.data.local

import android.content.Context
import android.util.Log
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.MapObjectiveItem
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object WildRiftLocalCache {

    private const val TAG = "WildRiftLocalCache"
    private const val PREFS_NAME = "wildrift_local_cache"
    private const val KEY_ITEMS = "cached_items"
    private const val KEY_CHAMPIONS = "cached_champions"
    private const val KEY_RUNES = "cached_runes"
    private const val KEY_SPELLS = "cached_spells"
    private const val KEY_OBJECTIVES = "cached_objectives"
    private const val KEY_PATCH_VERSION = "cached_patch_version"
    private const val KEY_LAST_SYNC_TIME = "cached_last_sync_time"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    fun saveToLocalCache(
        context: Context,
        items: List<WildRiftItem>? = null,
        champions: List<Champion>? = null,
        runes: List<RuneItem>? = null,
        spells: List<SummonerSpellItem>? = null,
        objectives: List<MapObjectiveItem>? = null,
        patchVersion: String? = null
    ) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()

            items?.let {
                editor.putString(KEY_ITEMS, json.encodeToString(it))
            }
            champions?.let {
                editor.putString(KEY_CHAMPIONS, json.encodeToString(it))
            }
            runes?.let {
                editor.putString(KEY_RUNES, json.encodeToString(it))
            }
            spells?.let {
                editor.putString(KEY_SPELLS, json.encodeToString(it))
            }
            objectives?.let {
                editor.putString(KEY_OBJECTIVES, json.encodeToString(it))
            }
            patchVersion?.let {
                editor.putString(KEY_PATCH_VERSION, it)
            }
            editor.putLong(KEY_LAST_SYNC_TIME, System.currentTimeMillis())
            editor.apply()
            Log.d(TAG, "Datos de Wild Rift cacheados localmente con éxito.")
        } catch (e: Exception) {
            Log.e(TAG, "Error guardando caché local de Wild Rift: ${e.message}", e)
        }
    }

    fun loadFromLocalCache(context: Context): Boolean {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            var hasLoadedAny = false

            val patch = prefs.getString(KEY_PATCH_VERSION, null)
            if (!patch.isNullOrBlank()) {
                WildRiftRepository.CURRENT_PATCH_VERSION = patch
                hasLoadedAny = true
            }

            val itemsJson = prefs.getString(KEY_ITEMS, null)
            if (!itemsJson.isNullOrBlank()) {
                val loadedItems = json.decodeFromString<List<WildRiftItem>>(itemsJson)
                if (loadedItems.isNotEmpty()) {
                    WildRiftRepository.items = loadedItems
                    hasLoadedAny = true
                }
            }

            val championsJson = prefs.getString(KEY_CHAMPIONS, null)
            if (!championsJson.isNullOrBlank()) {
                val loadedChamps = json.decodeFromString<List<Champion>>(championsJson)
                if (loadedChamps.isNotEmpty()) {
                    WildRiftRepository.champions = loadedChamps
                    hasLoadedAny = true
                }
            }

            val runesJson = prefs.getString(KEY_RUNES, null)
            if (!runesJson.isNullOrBlank()) {
                val loadedRunes = json.decodeFromString<List<RuneItem>>(runesJson)
                if (loadedRunes.isNotEmpty()) {
                    WildRiftRepository.runes = loadedRunes
                    hasLoadedAny = true
                }
            }

            val spellsJson = prefs.getString(KEY_SPELLS, null)
            if (!spellsJson.isNullOrBlank()) {
                val loadedSpells = json.decodeFromString<List<SummonerSpellItem>>(spellsJson)
                if (loadedSpells.isNotEmpty()) {
                    WildRiftRepository.summonerSpells = loadedSpells
                    hasLoadedAny = true
                }
            }

            val objectivesJson = prefs.getString(KEY_OBJECTIVES, null)
            if (!objectivesJson.isNullOrBlank()) {
                val loadedObjectives = json.decodeFromString<List<MapObjectiveItem>>(objectivesJson)
                if (loadedObjectives.isNotEmpty()) {
                    WildRiftRepository.mapObjectives = loadedObjectives
                    hasLoadedAny = true
                }
            }

            if (hasLoadedAny) {
                WildRiftRepository.LAST_SYNC_STATUS = "Datos locales cargados desde caché offline"
            }
            return hasLoadedAny
        } catch (e: Exception) {
            Log.e(TAG, "Error cargando caché local: ${e.message}", e)
            return false
        }
    }

    fun getLastSyncTime(context: Context): Long {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(KEY_LAST_SYNC_TIME, 0L)
    }

    fun clearCache(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
