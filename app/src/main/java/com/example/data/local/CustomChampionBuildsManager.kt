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
    val ratingSum: Double = 0.0,
    val voteCount: Int = 0,
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
        } else {
            val defaults = getDefaultBuilds()
            _customBuilds.value = defaults
            saveToPrefs(context, defaults)
        }
        initialized = true
    }

    private fun getDefaultBuilds(): List<CustomChampionBuildRecord> {
        val dummyItems = listOf(
            ItemBuildEntry("La Sanguinaria", "Gran curación en peleas prolongadas."),
            ItemBuildEntry("Ángel custodio", "Una segunda oportunidad en teamfights.")
        )
        val dummySituational = listOf(
            ItemBuildEntry("Fuerza de la Naturaleza", "Alta resistencia contra daño mágico.")
        )
        val dummyRunes = listOf(
            RuneBuildEntry("Conquistador", "", "Acumula daño adaptable al golpear."),
            RuneBuildEntry("Triunfo", "", "Restaura vida en asesinatos o asistencias.")
        )
        val dummySpells = listOf(
            SpellBuildEntry("Destello", "", "Teletransporte instantáneo."),
            SpellBuildEntry("Prender", "", "Quema al enemigo reduciendo su curación.")
        )

        return listOf(
            CustomChampionBuildRecord(
                championId = "yasuo",
                championName = "Yasuo",
                buildTitle = "Yasuo Mid Core",
                role = "Mid Lane",
                coreItems = listOf("La Sanguinaria", "Ángel custodio"),
                situationalItems = listOf("Fuerza de la Naturaleza"),
                runes = "Conquistador",
                spells = listOf("Destello", "Prender"),
                coreItemsWithDesc = dummyItems,
                situationalItemsWithDesc = dummySituational,
                coreRunes = dummyRunes,
                coreSpells = dummySpells,
                creatorName = "Coach System",
                ratingSum = 25.0,
                voteCount = 5
            ),
            CustomChampionBuildRecord(
                championId = "ahri",
                championName = "Ahri",
                buildTitle = "Ahri Burst",
                role = "Mid Lane",
                coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon"),
                situationalItems = listOf("Reloj de Arena de Zhonya"),
                runes = "Electrocutar",
                spells = listOf("Destello", "Prender"),
                coreItemsWithDesc = listOf(
                    ItemBuildEntry("Eco de Luden", "Ráfaga de daño mágico."),
                    ItemBuildEntry("Sombrero Mortal de Rabadon", "Aumento masivo de poder de habilidad.")
                ),
                situationalItemsWithDesc = listOf(ItemBuildEntry("Reloj de Arena de Zhonya", "Estasis invulnerable.")),
                coreRunes = listOf(RuneBuildEntry("Electrocutar", "", "Daño extra por combos rápidos.")),
                coreSpells = dummySpells,
                creatorName = "Coach System",
                ratingSum = 24.0,
                voteCount = 5
            ),
            CustomChampionBuildRecord(
                championId = "jinx",
                championName = "Jinx",
                buildTitle = "Jinx Hypercarry",
                role = "Dragon Lane",
                coreItems = listOf("Huracán de Runaan", "Filo Infinito"),
                situationalItems = listOf("Ángel custodio"),
                runes = "Compás Letal",
                spells = listOf("Destello", "Curar"),
                coreItemsWithDesc = listOf(
                    ItemBuildEntry("Huracán de Runaan", "Disparos múltiples a objetivos secundarios."),
                    ItemBuildEntry("Filo Infinito", "Daño crítico devastador.")
                ),
                situationalItemsWithDesc = listOf(ItemBuildEntry("Ángel custodio", "Resurrección en peleas.")),
                coreRunes = listOf(RuneBuildEntry("Compás Letal", "", "Velocidad de ataque incrementada.")),
                coreSpells = listOf(
                    SpellBuildEntry("Destello", "", "Teletransporte instantáneo."),
                    SpellBuildEntry("Curar", "", "Cura y velocidad de movimiento de emergencia.")
                ),
                creatorName = "Coach System",
                ratingSum = 22.0,
                voteCount = 5
            ),
            CustomChampionBuildRecord(
                championId = "lee_sin",
                championName = "Lee Sin",
                buildTitle = "Lee Sin Jungle",
                role = "Jungle",
                coreItems = listOf("Cuchilla Negra", "Danza de la Muerte"),
                situationalItems = listOf("Ángel custodio"),
                runes = "Conquistador",
                spells = listOf("Destello", "Aplastar"),
                coreItemsWithDesc = listOf(
                    ItemBuildEntry("Cuchilla Negra", "Reducción de armadura y salud."),
                    ItemBuildEntry("Danza de la Muerte", "Mitigación de daño aplazado.")
                ),
                situationalItemsWithDesc = listOf(ItemBuildEntry("Ángel custodio", "Resurrección clave para iniciar.")),
                coreRunes = dummyRunes,
                coreSpells = listOf(
                    SpellBuildEntry("Destello", "", "Teletransporte instantáneo."),
                    SpellBuildEntry("Aplastar", "", "Daño verdadero a monstruos épicos.")
                ),
                creatorName = "Coach System",
                ratingSum = 20.0,
                voteCount = 4
            ),
            CustomChampionBuildRecord(
                championId = "darius",
                championName = "Darius",
                buildTitle = "Darius Bruiser",
                role = "Baron Lane",
                coreItems = listOf("Fuerza de la Trinidad", "Calibrador de Sterak"),
                situationalItems = listOf("Placa del Hombre Muerto"),
                runes = "Conquistador",
                spells = listOf("Destello", "Fantasmal"),
                coreItemsWithDesc = listOf(
                    ItemBuildEntry("Fuerza de la Trinidad", "Aumento de daño sostenido y movilidad."),
                    ItemBuildEntry("Calibrador de Sterak", "Escudo anti-burst vital.")
                ),
                situationalItemsWithDesc = listOf(ItemBuildEntry("Placa del Hombre Muerto", "Velocidad de movimiento extra para perseguir.")),
                coreRunes = dummyRunes,
                coreSpells = listOf(
                    SpellBuildEntry("Destello", "", "Teletransporte instantáneo."),
                    SpellBuildEntry("Fantasmal", "", "Gran velocidad de movimiento durante varios segundos.")
                ),
                creatorName = "Coach System",
                ratingSum = 23.0,
                voteCount = 5
            )
        )
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

    fun rateBuild(context: Context, id: String, stars: Int) {
        init(context)
        val current = _customBuilds.value.toMutableList()
        val index = current.indexOfFirst { it.id == id }
        if (index != -1) {
            val record = current[index]
            val newVoteCount = record.voteCount + 1
            val newRatingSum = record.ratingSum + stars.toDouble()
            current[index] = record.copy(
                voteCount = newVoteCount,
                ratingSum = newRatingSum
            )
            _customBuilds.value = current
            saveToPrefs(context, current)
        }
    }

    private fun saveToPrefs(context: Context, list: List<CustomChampionBuildRecord>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        try {
            val encoded = json.encodeToString(list)
            prefs.edit().putString(KEY_BUILDS_JSON, encoded).apply()
        } catch (_: Exception) {}
    }
}
