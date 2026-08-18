package com.example.model

enum class LaneRole(val displayName: String, val shortName: String) {
    TOP("Línea de Barón (Top)", "Top"),
    JUNGLE("Jungla", "Jungla"),
    MID("Línea Central (Mid)", "Mid"),
    ADC("Línea de Dragón (ADC)", "ADC"),
    SUPPORT("Soporte (Apoyo)", "Apoyo")
}

enum class DamageType(val displayName: String) {
    PHYSICAL("Físico"),
    MAGIC("Mágico"),
    TRUE_HYBRID("Híbrido / Verdadero")
}

enum class ItemCategory(val displayName: String) {
    PHYSICAL("Físico (AD)"),
    MAGIC("Mágico (AP)"),
    DEFENSE("Defensa / Tanque"),
    SUPPORT("Soporte"),
    BOOTS_ENCHANTMENT("Botas & Encantamientos")
}

data class MetaDataSource(
    val id: String,
    val name: String,
    val url: String,
    val badge: String,
    val description: String,
    val focusArea: String
)

data class ChampionSkill(
    val slot: String, // "P", "1", "2", "3", "4"
    val slotName: String, // "Pasiva", "Habilidad 1", "Habilidad 2", "Habilidad 3", "Definitiva"
    val name: String,
    val iconUrl: String,
    val description: String,
    val cooldown: String = ""
)

data class Champion(
    val id: String,
    val name: String,
    val title: String = "",
    val ddragonId: String = "",
    val avatarUrl: String = "",
    val primaryRole: LaneRole,
    val secondaryRoles: List<LaneRole> = emptyList(),
    val tier: String, // "S+", "S", "A+", "A", "B"
    val winrate: Double, // e.g. 53.8
    val pickRate: Double,
    val banRate: Double,
    val damageType: DamageType,
    val summary: String,
    val advantageAgainst: List<String>,
    val counteredBy: List<String>,
    val synergies: List<String>,
    val tacticalAdvice: String,
    val recommendedRunes: String,
    val runeTreeDetails: String = "",
    val primaryRuneIconUrl: String = "",
    val recommendedSpells: List<String>,
    val spellsIcons: List<String> = emptyList(),
    val coreItems: List<String>,
    val coreItemsIcons: List<String> = emptyList(),
    val situationalItems: List<String> = emptyList(),
    val situationalItemsIcons: List<String> = emptyList(),
    val skillOrder: String,
    val skills: List<ChampionSkill> = emptyList(),
    val isRanged: Boolean = false,
    val isFrontline: Boolean = false,
    val wildRiftFireUrl: String = "https://www.wildriftfire.com/tier-list",
    val wrMetaUrl: String = "https://wr-meta.com/",
    val wildRiftCoreUrl: String = "https://wildriftcore.com/es/",
    val bestBuildWrUrl: String = "https://bestbuildwr.com/"
)

data class WildRiftItem(
    val id: String,
    val name: String,
    val category: ItemCategory,
    val goldCost: Int,
    val stats: String,
    val passive: String,
    val iconUrl: String
)

data class SummonerSpellItem(
    val id: String,
    val name: String,
    val cooldown: String,
    val iconUrl: String,
    val description: String
)

data class RuneItem(
    val id: String,
    val name: String,
    val category: String, // "Clave", "Dominación", "Precisión", "Valor", "Inspiración"
    val iconUrl: String,
    val description: String
)

data class MapObjectiveItem(
    val id: String,
    val name: String,
    val spawnTime: String,
    val respawnTime: String,
    val iconUrl: String,
    val buffDescription: String,
    val tactics: String
)

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

data class DraftRecommendation(
    val champion: Champion,
    val estimatedWinrate: Double,
    val advantageBadge: String,
    val tacticalReason: String,
    val runes: String,
    val synergyDetails: String = "",
    val counterDetails: String = ""
)
