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

data class MetaDataSource(
    val id: String,
    val name: String,
    val url: String,
    val badge: String,
    val description: String,
    val focusArea: String
)

data class Champion(
    val id: String,
    val name: String,
    val primaryRole: LaneRole,
    val secondaryRoles: List<LaneRole> = emptyList(),
    val tier: String, // "S+", "S", "A+", "A"
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
    val recommendedSpells: List<String>,
    val coreItems: List<String>,
    val situationalItems: List<String> = emptyList(),
    val skillOrder: String,
    val isRanged: Boolean = false,
    val isFrontline: Boolean = false,
    val wildRiftFireUrl: String = "https://www.wildriftfire.com/tier-list",
    val wrMetaUrl: String = "https://wr-meta.com/",
    val wildRiftCoreUrl: String = "https://wildriftcore.com/es/",
    val bestBuildWrUrl: String = "https://bestbuildwr.com/"
)

data class DraftAnalysisResult(
    val physicalDamagePercent: Int,
    val magicDamagePercent: Int,
    val trueDamagePercent: Int,
    val frontlineStatus: String,
    val directMatchupWarning: String?,
    val directCounterBestPick: String?,
    val recommendations: List<DraftRecommendation>
)

data class DraftRecommendation(
    val champion: Champion,
    val estimatedWinrate: Double,
    val advantageBadge: String,
    val tacticalReason: String,
    val runes: String
)
