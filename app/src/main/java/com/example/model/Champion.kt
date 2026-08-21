package com.example.model

import kotlinx.serialization.Serializable

@Serializable
enum class LaneRole(val displayName: String, val shortName: String) {
    TOP("Línea de Barón", "Top"),
    JUNGLE("Jungla", "Jungla"),
    MID("Línea Central", "Mid"),
    ADC("Línea de Dragón", "Dúo"),
    SUPPORT("Soporte", "Soporte")
}

@Serializable
enum class DamageType(val displayName: String) {
    PHYSICAL("Físico"),
    MAGIC("Mágico"),
    TRUE_HYBRID("Híbrido / Verdadero")
}

@Serializable
enum class ItemCategory(val displayName: String, val sectionTitle: String, val iconEmoji: String) {
    BASIC("Básicos", "ÍTEMS BÁSICOS (BASIC ITEMS)", "🔰"),
    MID_TIER("Nivel Medio", "ÍTEMS DE NIVEL MEDIO (MID TIER)", "⚔️"),
    PHYSICAL("Daño Físico", "DAÑO FÍSICO (PHYSICAL DAMAGE)", "🗡️"),
    MAGIC("Daño Mágico", "DAÑO MÁGICO (MAGIC DAMAGE)", "🔮"),
    DEFENSE("Defensa", "DEFENSA (DEFENSIVE ITEMS)", "🛡️"),
    SUPPORT("Soporte", "SOPORTE (SUPPORT ITEMS)", "💚"),
    BOOTS_T2("Botas N2", "BOTAS NIVEL 2 (BOOTS TIER 2)", "👢"),
    BOOTS_T3("Botas N3", "BOTAS NIVEL 3 (BOOTS TIER 3)", "⚡"),
    ACTIVE("Encantamientos", "ENCANTAMIENTOS Y ACTIVOS (ACTIVE ITEMS)", "✨")
}


@Serializable
data class ChampionSkill(
    val slot: String = "", // "P", "1", "2", "3", "4"
    val slotName: String = "", // "Pasiva", "Habilidad 1", "Habilidad 2", "Habilidad 3", "Definitiva"
    val name: String = "",
    val iconUrl: String = "",
    val description: String = "",
    val cooldown: String = ""
)

@Serializable
data class Champion(
    val id: String = "",
    val name: String = "",
    val title: String = "",
    val ddragonId: String = "",
    val avatarUrl: String = "",
    val primaryRole: LaneRole = LaneRole.MID,
    val secondaryRoles: List<LaneRole> = emptyList(),
    val tier: String = "B", // "S+", "S", "A+", "A", "B"
    val winrate: Double = 50.0, // e.g. 53.8
    val pickRate: Double = 0.0,
    val banRate: Double = 0.0,
    val damageType: DamageType = DamageType.PHYSICAL,
    val summary: String = "",
    val advantageAgainst: List<String> = emptyList(),
    val counteredBy: List<String> = emptyList(),
    val synergies: List<String> = emptyList(),
    val tacticalAdvice: String = "",
    val recommendedRunes: String = "",
    val runeTreeDetails: String = "",
    val primaryRuneIconUrl: String = "",
    val recommendedSpells: List<String> = emptyList(),
    val spellsIcons: List<String> = emptyList(),
    val coreItems: List<String> = emptyList(),
    val coreItemsIcons: List<String> = emptyList(),
    val situationalItems: List<String> = emptyList(),
    val situationalItemsIcons: List<String> = emptyList(),
    val skillOrder: String = "",
    val skills: List<ChampionSkill> = emptyList(),
    val isRanged: Boolean = false,
    val isFrontline: Boolean = false,
    val wildRiftFireUrl: String = "https://www.wildriftfire.com/tier-list",
    val wrMetaUrl: String = "https://wr-meta.com/",
    val wildRiftCoreUrl: String = "https://wildriftcore.com/es/",
    val bestBuildWrUrl: String = "https://bestbuildwr.com/"
)

@Serializable
data class WildRiftItem(
    val id: String,
    val name: String,
    val category: ItemCategory,
    val goldCost: Int,
    val stats: String,
    val passive: String,
    val iconUrl: String
)

@Serializable
data class SummonerSpellItem(
    val id: String,
    val name: String,
    val cooldown: String,
    val iconUrl: String,
    val description: String
)

@Serializable
data class RuneItem(
    val id: String,
    val name: String,
    val category: String, // "Clave", "Dominación", "Precisión", "Valor", "Inspiración"
    val iconUrl: String,
    val description: String
)

@Serializable
data class MapObjectiveItem(
    val id: String,
    val name: String,
    val spawnTime: String,
    val respawnTime: String,
    val iconUrl: String,
    val buffDescription: String,
    val tactics: String
)

@Serializable
data class DraftAnalysisResult(
    val physicalDamagePercent: Int,
    val magicDamagePercent: Int,
    val trueDamagePercent: Int,
    val frontlineStatus: String,
    val directMatchupWarning: String?,
    val directCounterBestPick: String?,
    val isFirstPickMode: Boolean = false,
    val bestOverallPick: DraftRecommendation? = null,
    val recommendations: List<DraftRecommendation>
)

@Serializable
data class DraftRecommendation(
    val champion: Champion,
    val estimatedWinrate: Double,
    val advantageBadge: String,
    val tacticalReason: String,
    val runes: String,
    val synergyDetails: String = "",
    val counterDetails: String = ""
)

@Serializable
data class MetaDataSource(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val focusArea: String,
    val badge: String = "Sincronizado"
)
