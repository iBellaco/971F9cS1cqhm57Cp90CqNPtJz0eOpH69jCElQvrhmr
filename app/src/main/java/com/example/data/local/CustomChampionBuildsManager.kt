package com.example.data.local

import android.content.Context
import com.example.model.ChampionBuild
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

@Serializable
data class ItemBuildEntry(
    val itemName: String,
    val description: String
)

@Serializable
data class RuneBuildEntry(
    val runeName: String,
    val iconUrl: String,
    val description: String
)

@Serializable
data class SpellBuildEntry(
    val spellName: String,
    val iconUrl: String,
    val description: String
)

@Serializable
data class CustomChampionBuildRecord(
    val id: String = UUID.randomUUID().toString(),
    val championId: String,
    val championName: String,
    val buildTitle: String,
    val role: String,
    val coreItems: List<String> = emptyList(),
    val situationalItems: List<String> = emptyList(),
    val runes: String = "",
    val spells: List<String> = emptyList(),
    val coreItemsWithDesc: List<ItemBuildEntry> = emptyList(),
    val situationalItemsWithDesc: List<ItemBuildEntry> = emptyList(),
    val coreRunes: List<RuneBuildEntry> = emptyList(),
    val situationalRunes: List<RuneBuildEntry> = emptyList(),
    val coreSpells: List<SpellBuildEntry> = emptyList(),
    val situationalSpells: List<SpellBuildEntry> = emptyList(),
    val gameplayVideoUri: String? = null,
    val creatorName: String,
    val timestamp: Long = System.currentTimeMillis()
)

object CustomChampionBuildsManager {
    private const val PREFS_NAME = "wr_custom_champion_builds_prefs"
    private const val KEY_BUILDS_JSON = "custom_champion_builds_json"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    private val _customBuilds = MutableStateFlow<List<CustomChampionBuildRecord>>(emptyList())
    val customBuilds: StateFlow<List<CustomChampionBuildRecord>> = _customBuilds.asStateFlow()

    private var initialized = false

    fun init(context: Context) {
        if (initialized) return
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val rawJson = prefs.getString(KEY_BUILDS_JSON, null)
        if (!rawJson.isNullOrBlank()) {
            try {
                val list = json.decodeFromString<List<CustomChampionBuildRecord>>(rawJson)
                _customBuilds.value = list
            } catch (_: Exception) {
                _customBuilds.value = emptyList()
            }
        }
        initialized = true
    }

    fun addBuild(context: Context, record: CustomChampionBuildRecord) {
        init(context)
        val current = _customBuilds.value.toMutableList()
        current.add(0, record)
        _customBuilds.value = current
        saveToPrefs(context, current)
    }

    fun updateBuild(context: Context, record: CustomChampionBuildRecord) {
        init(context)
        val current = _customBuilds.value.toMutableList()
        val index = current.indexOfFirst { it.id == record.id }
        if (index != -1) {
            current[index] = record
        } else {
            current.add(0, record)
        }
        _customBuilds.value = current
        saveToPrefs(context, current)
    }

    fun deleteBuild(context: Context, id: String) {
        init(context)
        val current = _customBuilds.value.filter { it.id != id }
        _customBuilds.value = current
        saveToPrefs(context, current)
    }

    fun getBuildsForChampion(championId: String): List<CustomChampionBuildRecord> {
        return _customBuilds.value.filter { it.championId.equals(championId, ignoreCase = true) }
    }

    private fun saveToPrefs(context: Context, list: List<CustomChampionBuildRecord>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        try {
            val encoded = json.encodeToString(list)
            prefs.edit().putString(KEY_BUILDS_JSON, encoded).apply()
        } catch (_: Exception) {}
    }
}
