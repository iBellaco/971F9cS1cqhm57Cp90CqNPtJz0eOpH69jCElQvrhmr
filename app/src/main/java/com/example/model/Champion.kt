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

data class Champion(
    val id: String,
    val name: String,
    val primaryRole: LaneRole,
    val secondaryRoles: List<LaneRole> = emptyList(),
    val tier: String, // "S+", "S", "A"
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
    val recommendedSpells: List<String>,
    val coreItems: List<String>,
    val skillOrder: String,
    val isRanged: Boolean = false,
    val isFrontline: Boolean = false
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
