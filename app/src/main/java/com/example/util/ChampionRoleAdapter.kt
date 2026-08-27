package com.example.util

import com.example.data.WildRiftItemsData
import com.example.data.WildRiftSpellsAndRunes
import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.ItemSwap
import com.example.model.LaneRole

data class ChampionRoleProfile(
    val role: LaneRole,
    val winrate: Double,
    val pickRate: Double,
    val banRate: Double,
    val winrateDelta: Double = 0.0,
    val pickRateDelta: Double = 0.0,
    val banRateDelta: Double = 0.0,
    val tier: String,
    val coreItems: List<String>,
    val coreItemsIcons: List<String>,
    val situationalItems: List<String>,
    val situationalItemsIcons: List<String>,
    val itemSwaps: List<ItemSwap>,
    val recommendedRunes: String,
    val runeTreeDetails: String,
    val primaryRuneIconUrl: String,
    val recommendedSpells: List<String>,
    val spellsIcons: List<String>,
    val advantageAgainst: List<String>,
    val counteredBy: List<String>,
    val synergies: List<String>,
    val tacticalAdvice: String,
    val build8Items: List<String> = emptyList(),
    val bootBase: String = "",
    val bootUpgrade: String = "",
    val runesOption1: List<String> = emptyList(),
    val runesOption2: List<String> = emptyList()
)

object ChampionRoleAdapter {

    fun isBootItem(name: String): Boolean {
        val clean = name.lowercase().trim()
        return clean.contains("bota") || clean.contains("greba") ||
                clean.contains("lanzahechizos") || clean.contains("avance blindado") ||
                clean.contains("trituradora") || clean.contains("lucidez carmesí") ||
                clean.contains("lucidez carmesi") || clean.contains("quebrantarmadura") ||
                clean.contains("inmortal") || clean.contains("steelcaps") ||
                clean.contains("treads") || clean.contains("swiftness")
    }

    fun getBaseTier2Boot(
        rawItems: List<String>,
        damageType: DamageType,
        isTank: Boolean,
        isRanged: Boolean,
        role: LaneRole
    ): String {
        for (item in rawItems) {
            val clean = item.lowercase()
            if (clean.contains("maná") || clean.contains("mana") || clean.contains("lanzahechizos")) return "Botas de maná"
            if (clean.contains("blindad") || clean.contains("avance") || clean.contains("steelcaps")) return "Botas blindadas"
            if (clean.contains("mercurio") || clean.contains("trituradora") || clean.contains("treads")) return "Botas de mercurio"
            if (clean.contains("berserker") || clean.contains("metal") || clean.contains("gunmetal")) return "Grebas de berserker"
            if (clean.contains("jonia") || clean.contains("lucidez") || clean.contains("carmesí") || clean.contains("carmesi")) return "Botas jonias de la lucidez"
            if (clean.contains("dinámica") || clean.contains("dinamica") || clean.contains("quebrantarmadura")) return "Botas dinámicas"
            if (clean.contains("codiciosa") || clean.contains("inmortal")) return "Grebas codiciosas"
        }
        return when {
            damageType == DamageType.MAGIC -> "Botas de maná"
            isTank || role == LaneRole.SUPPORT -> "Botas blindadas"
            isRanged && damageType == DamageType.PHYSICAL -> "Grebas de berserker"
            role == LaneRole.JUNGLE && damageType == DamageType.PHYSICAL -> "Botas dinámicas"
            else -> "Botas blindadas"
        }
    }

    fun getTier3BootUpgrade(tier2Boot: String): String {
        val clean = tier2Boot.lowercase()
        return when {
            clean.contains("maná") || clean.contains("mana") || clean.contains("lanzahechizos") -> "Botas del lanzahechizos"
            clean.contains("blindad") || clean.contains("avance") || clean.contains("steelcaps") -> "Avance blindado"
            clean.contains("mercurio") || clean.contains("trituradora") || clean.contains("treads") -> "Trituradoras encadenadas"
            clean.contains("berserker") || clean.contains("metal") || clean.contains("gunmetal") -> "Grebas de metal"
            clean.contains("jonia") || clean.contains("lucidez") || clean.contains("carmesí") || clean.contains("carmesi") -> "Lucidez carmesí"
            clean.contains("dinámica") || clean.contains("dinamica") || clean.contains("quebrantarmadura") -> "Botas quebrantarmaduras"
            clean.contains("codiciosa") || clean.contains("inmortal") -> "Botas inmortales"
            else -> "Botas del lanzahechizos"
        }
    }

    fun generate8ItemBuild(
        rawCore: List<String>,
        rawSituational: List<String>,
        damageType: DamageType,
        isTank: Boolean,
        isRanged: Boolean,
        role: LaneRole
    ): Triple<List<String>, String, String> {
        val baseBoot = getBaseTier2Boot(rawCore + rawSituational, damageType, isTank, isRanged, role)
        val bootUpgrade = getTier3BootUpgrade(baseBoot)

        // 1. Extraer legendarios únicos sin botas
        val nonBootCore = rawCore.filter { !isBootItem(it) }.distinct().toMutableList()

        // Rellenar legendarios faltantes si son menos de 4
        val fallbackLegendaries = when {
            damageType == DamageType.MAGIC -> listOf(
                "Orbe infinito", "Gorro de muerte del miércoles", "Impulso Cósmico", "Bastón vacío",
                "Luden's Echo", "Antorcha de fuego negro", "Cetro de cristal de Rylai", "Hacedor de grietas"
            )
            isTank || role == LaneRole.SUPPORT -> listOf(
                "Plato del hombre muerto", "malla de espinas", "Fuerza de la naturaleza",
                "Sudario del alba", "Corona abrasadora", "Guardia gemela de amaranto", "Armadura de Warmog"
            )
            isRanged && damageType == DamageType.PHYSICAL -> listOf(
                "Borde infinito", "Blaster magnético", "Saludos de Dominik",
                "sanguinario", "El coleccionista", "Bailarina fantasma", "Cañón de fuego rápido"
            )
            else -> listOf(
                "Fuerza trinitaria", "Black Cleaver", "La danza de la muerte",
                "Sterak's Gage", "Ángel custodio", "Rompegalope", "El rencor de Serylda"
            )
        }

        for (item in fallbackLegendaries) {
            if (nonBootCore.size >= 4) break
            if (!nonBootCore.contains(item)) {
                nonBootCore.add(item)
            }
        }

        val l1 = nonBootCore.getOrElse(0) { fallbackLegendaries[0] }
        val l2 = nonBootCore.getOrElse(1) { fallbackLegendaries[1] }
        val l3 = nonBootCore.getOrElse(2) { fallbackLegendaries[2] }
        val l4 = nonBootCore.getOrElse(3) { fallbackLegendaries[3] }

        // 2. Extraer 2 situacionales únicos (Items 7 y 8)
        val nonBootSituational = rawSituational.filter { !isBootItem(it) && it != l1 && it != l2 && it != l3 && it != l4 }.distinct().toMutableList()
        val defaultSituational = when {
            damageType == DamageType.MAGIC -> listOf("Morellonomicón", "El reloj de arena de Zhonya", "Velo de alma en pena", "Malignance")
            isTank || role == LaneRole.SUPPORT -> listOf("malla de espinas", "El presagio de Randuin", "Placa de piedra de gárgola", "Redención")
            else -> listOf("malla de espinas", "Colmillo de serpiente", "Ángel custodio", "Fajín de mercurio")
        }

        for (item in defaultSituational) {
            if (nonBootSituational.size >= 2) break
            if (!nonBootSituational.contains(item) && item != l1 && item != l2 && item != l3 && item != l4) {
                nonBootSituational.add(item)
            }
        }

        val s1 = nonBootSituational.getOrElse(0) { defaultSituational[0] }
        val s2 = nonBootSituational.getOrElse(1) { defaultSituational[1] }

        // Build 1..8 exactamente como la referencia de Wild Rift:
        // 1: Bota Tier 2
        // 2: Core 1
        // 3: Core 2
        // 4: Bota Tier 3 (Mejora)
        // 5: Core 3
        // 6: Core 4
        // 7: Situacional 1
        // 8: Situacional 2
        val build8 = listOf(baseBoot, l1, l2, bootUpgrade, l3, l4, s1, s2)

        return Triple(build8, baseBoot, bootUpgrade)
    }

    fun generateRunesOptions(
        champ: Champion,
        role: LaneRole
    ): Pair<List<String>, List<String>> {
        return WildRiftChampionRunesMeta.resolveRunes(champ, role)
    }

    fun getProfile(champion: Champion, targetRole: LaneRole): ChampionRoleProfile {
        val isPrimary = targetRole == champion.primaryRole

        if (isPrimary) {
            return buildPrimaryProfile(champion)
        }

        return buildFlexRoleProfile(champion, targetRole)
    }

    private fun buildPrimaryProfile(champ: Champion): ChampionRoleProfile {
        val (build8, baseBoot, bootUpgrade) = generate8ItemBuild(
            champ.coreItems,
            champ.situationalItems,
            champ.damageType,
            champ.isFrontline,
            champ.isRanged,
            champ.primaryRole
        )

        val completedCoreItems = build8.take(6)
        val coreIcons = completedCoreItems.map { WildRiftItemsData.getItemIconByName(it) }

        val situationalItems = build8.drop(6).take(2)
        val situationalIcons = situationalItems.map { WildRiftItemsData.getItemIconByName(it) }

        val resolvedSpellsIcons = if (champ.recommendedSpells.isNotEmpty()) {
            champ.recommendedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }
        } else {
            listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_IGNITE)
        }

        val (opt1Runes, opt2Runes) = generateRunesOptions(champ, champ.primaryRole)
        val primaryRuneIcon = WildRiftSpellsAndRunes.getRuneIconByName(opt1Runes.firstOrNull() ?: extractMainRune(champ.recommendedRunes))

        val syncedSwaps = if (champ.itemSwaps.isNotEmpty()) {
            champ.itemSwaps.map { swap ->
                swap.copy(
                    coreItemIcon = WildRiftItemsData.getItemIconByName(swap.coreItem),
                    altItemIcon = WildRiftItemsData.getItemIconByName(swap.altItem)
                )
            }
        } else {
            generateDefaultSwaps(completedCoreItems, situationalItems, champ.damageType, champ.isFrontline)
        }

        return ChampionRoleProfile(
            role = champ.primaryRole,
            winrate = champ.winrate,
            pickRate = champ.pickRate,
            banRate = champ.banRate,
            winrateDelta = champ.winrateDelta,
            pickRateDelta = champ.pickRateDelta,
            banRateDelta = champ.banRateDelta,
            tier = champ.tier,
            coreItems = completedCoreItems,
            coreItemsIcons = coreIcons,
            situationalItems = situationalItems,
            situationalItemsIcons = situationalIcons,
            itemSwaps = syncedSwaps,
            recommendedRunes = opt1Runes.firstOrNull() ?: champ.recommendedRunes,
            runeTreeDetails = opt1Runes.drop(1).joinToString(" • "),
            primaryRuneIconUrl = primaryRuneIcon,
            recommendedSpells = champ.recommendedSpells.ifEmpty { listOf("Destello", "Ignición") },
            spellsIcons = resolvedSpellsIcons,
            advantageAgainst = champ.advantageAgainst,
            counteredBy = champ.counteredBy,
            synergies = champ.synergies,
            tacticalAdvice = champ.tacticalAdvice,
            build8Items = build8,
            bootBase = baseBoot,
            bootUpgrade = bootUpgrade,
            runesOption1 = opt1Runes,
            runesOption2 = opt2Runes
        )
    }

    private fun buildFlexRoleProfile(champ: Champion, role: LaneRole): ChampionRoleProfile {
        val isAp = champ.damageType == DamageType.MAGIC
        val isTank = champ.isFrontline || champ.primaryRole == LaneRole.SUPPORT || (champ.primaryRole == LaneRole.TOP && !champ.isRanged)
        val isMarksman = champ.isRanged && champ.damageType == DamageType.PHYSICAL

        // 1. Spells for flex role
        val (recommendedSpells, spellsIcons) = when (role) {
            LaneRole.JUNGLE -> Pair(
                listOf("Castigo", "Destello"),
                listOf(WildRiftSpellsAndRunes.SPELL_SMITE, WildRiftSpellsAndRunes.SPELL_FLASH)
            )
            LaneRole.SUPPORT -> Pair(
                listOf("Destello", "Ignición"),
                listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_IGNITE)
            )
            LaneRole.ADC -> Pair(
                listOf("Destello", "Barrera"),
                listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_BARRIER)
            )
            LaneRole.TOP -> Pair(
                listOf("Destello", "Teleportación"),
                listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_TELEPORT)
            )
            LaneRole.MID -> Pair(
                listOf("Destello", "Ignición"),
                listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_IGNITE)
            )
        }

        // 2. Dynamic 8 Items for flex role
        val rawFlexCore: List<String> = when (role) {
            LaneRole.JUNGLE -> if (isAp) listOf("Botas de maná", "Diente de Nashor", "Luden's Echo", "Botas del lanzahechizos", "Orbe infinito", "Gorro de muerte del miércoles")
                else if (isTank) listOf("Botas blindadas", "Plato del hombre muerto", "malla de espinas", "Avance blindado", "Fuerza de la naturaleza", "Corona abrasadora")
                else listOf("Botas dinámicas", "Fuerza trinitaria", "El coleccionista", "Botas quebrantarmaduras", "Borde infinito", "Saludos de Dominik")
            LaneRole.SUPPORT -> if (isAp) listOf("Botas jonias de la lucidez", "Eco armónico", "Incensario Ardiente", "Lucidez carmesí", "Velo de alma en pena", "La bendición de Michael")
                else listOf("Botas blindadas", "Escudo de reliquia", "La convergencia de Zeke", "Avance blindado", "Voto de caballero", "malla de espinas")
            LaneRole.ADC -> listOf("Grebas de berserker", "Borde infinito", "Blaster magnético", "Grebas de metal", "Saludos de Dominik", "sanguinario")
            LaneRole.TOP -> if (isAp) listOf("Botas de maná", "Hacedor de grietas", "Cetro de cristal de Rylai", "Botas del lanzahechizos", "El tormento de Liandry", "Gorro de muerte del miércoles")
                else if (isTank) listOf("Botas blindadas", "corazón de acero", "Égida del fuego solar", "Avance blindado", "malla de espinas", "Fuerza de la naturaleza")
                else listOf("Botas blindadas", "Fuerza trinitaria", "Black Cleaver", "Avance blindado", "Sterak's Gage", "La danza de la muerte")
            LaneRole.MID -> if (isAp) listOf("Botas de maná", "Luden's Echo", "Orbe infinito", "Botas del lanzahechizos", "Gorro de muerte del miércoles", "Bastón vacío")
                else listOf("Botas dinámicas", "El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "Botas quebrantarmaduras", "El coleccionista", "El rencor de Serylda")
        }

        val (build8, baseBoot, bootUpgrade) = generate8ItemBuild(
            rawFlexCore,
            champ.situationalItems,
            champ.damageType,
            isTank,
            isMarksman,
            role
        )

        val completedCoreItems = build8.take(6)
        val coreIcons = completedCoreItems.map { WildRiftItemsData.getItemIconByName(it) }
        val situationalItems = build8.drop(6).take(2)
        val situationalIcons = situationalItems.map { WildRiftItemsData.getItemIconByName(it) }

        val (opt1Runes, opt2Runes) = generateRunesOptions(champ, role)
        val primaryRuneIcon = WildRiftSpellsAndRunes.getRuneIconByName(opt1Runes.firstOrNull() ?: extractMainRune(champ.recommendedRunes))

        val syncedSwaps = generateDefaultSwaps(completedCoreItems, situationalItems, champ.damageType, isTank)

        val flexWinrate = adjustRate(champ.winrate, -1.2)
        val flexPickRate = adjustRate(champ.pickRate * 0.4, 0.0)
        val flexBanRate = champ.banRate

        val flexTier = when {
            flexWinrate >= 52.0 -> "S"
            flexWinrate >= 50.0 -> "A"
            flexWinrate >= 48.5 -> "B"
            else -> "C"
        }

        return ChampionRoleProfile(
            role = role,
            winrate = flexWinrate,
            pickRate = flexPickRate,
            banRate = flexBanRate,
            winrateDelta = -1.2,
            pickRateDelta = 0.0,
            banRateDelta = 0.0,
            tier = flexTier,
            coreItems = completedCoreItems,
            coreItemsIcons = coreIcons,
            situationalItems = situationalItems,
            situationalItemsIcons = situationalIcons,
            itemSwaps = syncedSwaps,
            recommendedRunes = opt1Runes.firstOrNull() ?: champ.recommendedRunes,
            runeTreeDetails = opt1Runes.drop(1).joinToString(" • "),
            primaryRuneIconUrl = primaryRuneIcon,
            recommendedSpells = recommendedSpells,
            spellsIcons = spellsIcons,
            advantageAgainst = champ.advantageAgainst,
            counteredBy = champ.counteredBy,
            synergies = champ.synergies,
            tacticalAdvice = champ.tacticalAdvice,
            build8Items = build8,
            bootBase = baseBoot,
            bootUpgrade = bootUpgrade,
            runesOption1 = opt1Runes,
            runesOption2 = opt2Runes
        )
    }

    private fun generateDefaultSwaps(
        coreItems: List<String>,
        situationalItems: List<String>,
        damageType: DamageType,
        isTank: Boolean
    ): List<ItemSwap> {
        val swaps = mutableListOf<ItemSwap>()

        if (damageType == DamageType.MAGIC) {
            val validCoreItems = coreItems.filter { !isBootItem(it) }.distinct()
            val coreTarget1 = validCoreItems.find { it.contains("Rabadon") || it.contains("Infinito") || it.contains("Luden") } ?: validCoreItems.firstOrNull() ?: "Luden's Echo"
            val coreTarget2 = validCoreItems.find { it != coreTarget1 } ?: validCoreItems.getOrNull(1) ?: coreTarget1
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget1),
                    altItem = "Morellonomicón",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Morellonomicón"),
                    reasonTitle = "ANTI-CURACIÓN (HERIDAS GRAVES)",
                    reasonDesc = "Reduce las curaciones y regeneraciones masivas de campeones enemigos.",
                    againstWho = "Soraka, Dr. Mundo, Aatrox, Warwick, Vladimir"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget2),
                    altItem = "El reloj de arena de Zhonya",
                    altItemIcon = WildRiftItemsData.getItemIconByName("El reloj de arena de Zhonya"),
                    reasonTitle = "SUPERVIVENCIA & INVULNERABILIDAD",
                    reasonDesc = "Otorga éxtasis temporal de 2.5s para esquivar combos letales de asesinos.",
                    againstWho = "Zed, Talon, Fizz, Kayn, Syndra"
                )
            )
        } else if (isTank) {
            val validCoreItems = coreItems.filter { !isBootItem(it) }.distinct()
            val coreTarget1 = validCoreItems.find { it.contains("Fuerza") || it.contains("Amanecer") || it.contains("Muerto") } ?: validCoreItems.firstOrNull() ?: "Plato del hombre muerto"
            val coreTarget2 = validCoreItems.find { it != coreTarget1 } ?: validCoreItems.getOrNull(1) ?: coreTarget1
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget1),
                    altItem = "malla de espinas",
                    altItemIcon = WildRiftItemsData.getItemIconByName("malla de espinas"),
                    reasonTitle = "ANTI-CURACIÓN & ARMADURA",
                    reasonDesc = "Aplica Heridas Graves al recibir daño y devuelve daño mágico.",
                    againstWho = "Aatrox, Warwick, Soraka, Yuumi, Samira"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget2),
                    altItem = "El presagio de Randuin",
                    altItemIcon = WildRiftItemsData.getItemIconByName("El presagio de Randuin"),
                    reasonTitle = "ANTI-CRÍTICO",
                    reasonDesc = "Reduce el daño de golpes críticos y frena hipercarries de autoataques.",
                    againstWho = "Yasuo, Yone, Jinx, Tristana, Caitlyn"
                )
            )
        } else {
            val validCoreItems = coreItems.filter { !isBootItem(it) }.distinct()
            val coreTarget1 = validCoreItems.find { it.contains("Danza") || it.contains("Cuchilla") || it.contains("Fuego") } ?: validCoreItems.firstOrNull() ?: "Black Cleaver"
            val coreTarget2 = validCoreItems.find { it != coreTarget1 } ?: validCoreItems.getOrNull(1) ?: coreTarget1
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget1),
                    altItem = "Colmillo de serpiente",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Colmillo de serpiente"),
                    reasonTitle = "DESTRUCTOR DE ESCUDOS",
                    reasonDesc = "Reduce drásticamente la absorción de escudos enemigos al impactar con daño físico.",
                    againstWho = "Sett, Shen, Karma, Lulu, Sterak"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget2),
                    altItem = "malla de espinas",
                    altItemIcon = WildRiftItemsData.getItemIconByName("malla de espinas"),
                    reasonTitle = "ARMADURA & ANTI-CURACIÓN",
                    reasonDesc = "Corta el sustain enemigo y resiste composiciones de alto daño físico.",
                    againstWho = "Aatrox, Warwick, Maestro Yi, Samira"
                )
            )
        }
        return swaps
    }

    private fun extractMainRune(runeText: String): String {
        return runeText.substringBefore("(").substringBefore("•").trim()
    }

    private fun adjustRate(base: Double, delta: Double): Double {
        val result = base + delta
        return (Math.round(result * 100.0) / 100.0).coerceIn(40.0, 65.0)
    }
}
