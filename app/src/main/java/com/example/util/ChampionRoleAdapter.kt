package com.example.util

import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.ItemSwap
import com.example.model.LaneRole

data class ChampionBuildOption(
    val optionNumber: Int,
    val title: String,
    val subtitle: String,
    val source: String,
    val badge: String,
    val tacticalReason: String,
    val items: List<String>,
    val bootBase: String,
    val bootUpgrade: String,
    val runes: List<String>,
    val spells: List<String>,
    val spellsIcons: List<String>
)

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
    val runesOption2: List<String> = emptyList(),
    val buildOptions: List<ChampionBuildOption> = emptyList()
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

    /**
     * Determina si un objeto es puramente de daño de carry/mid (AP burst o AD crítico/letalidad puro)
     * no apto para soportes de utilidad o tanques.
     */
    fun isPureDamageCarryItem(name: String): Boolean {
        val clean = name.lowercase().trim()
        val carryDamagePatterns = listOf(
            "rabadon", "sombrero mortífero", "sombrero mortal", "gorro de muerte", "deathcap",
            "bastón del vacío", "bastón vacío", "baston del vacio", "baston vacio", "void staff",
            "orbe infinito", "infinity orb", "luden", "eco de luden", "luden's echo",
            "diente de nashor", "nashor", "impulso cósmico", "cosmic drive", "enfoque del horizonte", "horizon focus",
            "antorcha de fuego negro", "blackfire torch", "oleada de tormenta", "stormsurge",
            "borde infinito", "infinity edge", "el coleccionista", "el recaudador", "the collector",
            "saludos de dominik", "lord dominik", "sanguinario", "bloodthirster", "el huracán de runaan",
            "runaan", "cañón de fuego rápido", "blaster magnético", "magnetic blaster", "bailarina fantasma",
            "phantom dancer", "arcoescudo inmortal", "inmortal shieldbow", "filo de la noche", "edge of night",
            "hoja del ocaso de draktharr", "draktharr", "el cuchillo fantasma de youmuu", "youmuu",
            "el rencor de serylda", "serylda", "fuerza trinitaria", "fuerza de la trinidad", "trinity force", "perdición del exánime", "lich bane"
        )
        return carryDamagePatterns.any { clean.contains(it) }
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
            if (role == LaneRole.SUPPORT && (clean.contains("jonia") || clean.contains("lucidez"))) return "Botas jonias de la lucidez"
            if (clean.contains("maná") || clean.contains("mana") || clean.contains("lanzahechizos")) {
                if (role == LaneRole.SUPPORT && !isTank) return "Botas jonias de la lucidez"
                return "Botas de maná"
            }
            if (clean.contains("blindad") || clean.contains("avance") || clean.contains("steelcaps")) return "Botas blindadas"
            if (clean.contains("mercurio") || clean.contains("trituradora") || clean.contains("treads")) return "Botas de mercurio"
            if (clean.contains("berserker") || clean.contains("metal") || clean.contains("gunmetal")) {
                if (role == LaneRole.SUPPORT) return "Botas blindadas"
                return "Grebas de berserker"
            }
            if (clean.contains("jonia") || clean.contains("lucidez") || clean.contains("carmesí") || clean.contains("carmesi")) return "Botas jonias de la lucidez"
            if (clean.contains("dinámica") || clean.contains("dinamica") || clean.contains("quebrantarmadura")) {
                if (role == LaneRole.SUPPORT) return "Botas jonias de la lucidez"
                return "Botas dinámicas"
            }
            if (clean.contains("codiciosa") || clean.contains("inmortal")) return "Grebas codiciosas"
        }
        return when {
            role == LaneRole.SUPPORT -> if (isTank) "Botas blindadas" else "Botas jonias de la lucidez"
            damageType == DamageType.MAGIC -> "Botas de maná"
            isTank -> "Botas blindadas"
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
            else -> "Lucidez carmesí"
        }
    }

    fun generate8ItemBuild(
        rawCore: List<String>,
        rawSituational: List<String>,
        damageType: DamageType,
        isTank: Boolean,
        isRanged: Boolean,
        role: LaneRole,
        isAssassinsOrAdcSupport: Boolean = false
    ): Triple<List<String>, String, String> {
        val baseBoot = getBaseTier2Boot(rawCore + rawSituational, damageType, isTank, isRanged, role)
        val bootUpgrade = getTier3BootUpgrade(baseBoot)

        // 1. Extraer legendarios únicos sin botas y con filtros tácticos por rol
        val filteredRawCore = rawCore.filter { item ->
            if (isBootItem(item)) return@filter false
            // Filtro táctico de Coach: Soportes no deben recibir objetos de daño de carry
            if (role == LaneRole.SUPPORT && !isAssassinsOrAdcSupport) {
                if (isPureDamageCarryItem(item)) return@filter false
            }
            true
        }.distinct().toMutableList()

        // Pools tácticos contextuales de legendarios según Rol y Perfil
        val fallbackLegendaries = when (role) {
            LaneRole.SUPPORT -> when {
                isAssassinsOrAdcSupport -> listOf(
                    "Guadaña de la Niebla Negra", "El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr",
                    "Colmillo de serpiente", "Fauces de Malmortius", "Ángel custodio"
                )
                isTank -> listOf(
                    "Escudo de reliquia", "Voto de caballero", "La convergencia de Zeke",
                    "Sudario del alba", "Malla de espinas", "Fuerza de la naturaleza", "Relicario de los Solari de Hierro"
                )
                else -> listOf(
                    "Guadaña de la Niebla Negra", "Eco armónico", "Bastón de aguas fluidas",
                    "Incensario ardiente", "Mandato imperial", "Bendición de Mikael", "Redención"
                )
            }
            LaneRole.ADC -> listOf(
                "Borde infinito", "Blaster magnético", "Saludos de Dominik",
                "Sanguinario", "El coleccionista", "Bailarina fantasma", "Cañón de fuego rápido"
            )
            LaneRole.JUNGLE -> if (damageType == DamageType.MAGIC) {
                listOf("Eco de Luden", "Diente de Nashor", "Orbe infinito", "Sombrero mortal de Rabadon", "Bastón del vacío", "Hacedor de grietas")
            } else if (isTank) {
                listOf("Coraza del muerto", "Malla de espinas", "Fuerza de la naturaleza", "Corona abrasadora", "Guardia gemela de amaranto", "Corazón de acero")
            } else {
                listOf("Fuerza de la Trinidad", "El coleccionista", "Cuchilla negra", "La danza de la muerte", "Guantelete de Sterak", "Ángel custodio")
            }
            LaneRole.TOP -> if (damageType == DamageType.MAGIC) {
                listOf("Hacedor de grietas", "Cetro de cristal de Rylai", "Tormento de Liandry", "Vara de las edades", "Sombrero mortal de Rabadon", "Bastón del vacío")
            } else if (isTank) {
                listOf("Corazón de acero", "Égida de fuego solar", "Malla de espinas", "Fuerza de la naturaleza", "Presagio de Randuin", "Guardia gemela de amaranto")
            } else {
                listOf("Fuerza de la Trinidad", "Cuchilla negra", "La danza de la muerte", "Guantelete de Sterak", "Rompecascos", "Ángel custodio")
            }
            LaneRole.MID -> if (damageType == DamageType.MAGIC) {
                listOf("Eco de Luden", "Orbe infinito", "Sombrero mortal de Rabadon", "Bastón del vacío", "Impulso cósmico", "Tormento de Liandry")
            } else {
                listOf("El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El coleccionista", "El rencor de Serylda", "La danza de la muerte", "Filo de la noche")
            }
        }

        for (item in fallbackLegendaries) {
            if (filteredRawCore.size >= 6) break
            if (!filteredRawCore.contains(item)) {
                filteredRawCore.add(item)
            }
        }

        val l1 = filteredRawCore.getOrElse(0) { fallbackLegendaries[0] }
        val l2 = filteredRawCore.getOrElse(1) { fallbackLegendaries[1] }
        val l3 = filteredRawCore.getOrElse(2) { fallbackLegendaries[2] }
        val l4 = filteredRawCore.getOrElse(3) { fallbackLegendaries[3] }
        val l5 = filteredRawCore.getOrElse(4) { fallbackLegendaries.getOrElse(4) { "Ángel custodio" } }
        val l6 = filteredRawCore.getOrElse(5) { fallbackLegendaries.getOrElse(5) { "Reloj de arena de Zhonya" } }

        val usedCore = listOf(l1, l2, l3, l4, l5, l6)

        // 2. Extraer 2 situacionales únicos (Items 7 y 8) con filtros de rol
        val filteredRawSituational = rawSituational.filter { item ->
            if (isBootItem(item) || usedCore.contains(item)) return@filter false
            if (role == LaneRole.SUPPORT && !isAssassinsOrAdcSupport) {
                if (isPureDamageCarryItem(item)) return@filter false
            }
            true
        }.distinct().toMutableList()

        val defaultSituational = when (role) {
            LaneRole.SUPPORT -> when {
                isAssassinsOrAdcSupport -> listOf("Colmillo de serpiente", "Ángel custodio", "Fauces de Malmortius", "Fajín de mercurio")
                isTank -> listOf("Malla de espinas", "Presagio de Randuin", "Relicario de los Solari de Hierro", "Redención")
                else -> listOf("Incensario ardiente", "Redención", "Relicario de los Solari de Hierro", "Bendición de Mikael")
            }
            LaneRole.ADC -> listOf("Ángel custodio", "Fajín de mercurio", "Recordatorio mortal", "Sanguinario")
            LaneRole.JUNGLE, LaneRole.TOP, LaneRole.MID -> when {
                isTank -> listOf("Malla de espinas", "Presagio de Randuin", "Fuerza de la naturaleza", "Corona abrasadora")
                damageType == DamageType.MAGIC -> listOf("Morellonomicón", "Reloj de arena de Zhonya", "Velo de alma en pena", "Torreón de Kaenic")
                else -> listOf("Malla de espinas", "Colmillo de serpiente", "Ángel custodio", "Fajín de mercurio")
            }
        }

        for (item in defaultSituational) {
            if (filteredRawSituational.size >= 2) break
            if (!filteredRawSituational.contains(item) && !usedCore.contains(item)) {
                filteredRawSituational.add(item)
            }
        }

        val s1 = filteredRawSituational.getOrElse(0) { defaultSituational[0] }
        val s2 = filteredRawSituational.getOrElse(1) { defaultSituational[1] }

        // Build 1..8: 1-6 = Core Legendaries, 7-8 = Situational Legendaries (Zero Boots in slots!)
        val build8 = listOf(l1, l2, l3, l4, l5, l6, s1, s2)

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
        val isSpecialDamageSupport = champ.primaryRole == LaneRole.SUPPORT && (champ.name.equals("Pyke", ignoreCase = true) || champ.name.equals("Senna", ignoreCase = true))
        val (build8, baseBoot, bootUpgrade) = generate8ItemBuild(
            champ.coreItems,
            champ.situationalItems,
            champ.damageType,
            champ.isFrontline,
            champ.isRanged,
            champ.primaryRole,
            isAssassinsOrAdcSupport = isSpecialDamageSupport
        )

        val completedCoreItems = build8.take(6)
        val coreIcons = completedCoreItems.map { WildRiftItemsData.getItemIconByName(it) }

        val situationalItems = build8.drop(6).take(2)
        val situationalIcons = situationalItems.map { WildRiftItemsData.getItemIconByName(it) }

        val resolvedSpells = ensureUniqueSpells(champ.recommendedSpells, champ.primaryRole)
        val resolvedSpellsIcons = resolvedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val (opt1Runes, opt2Runes) = generateRunesOptions(champ, champ.primaryRole)
        val primaryRuneIcon = WildRiftSpellsAndRunes.getRuneIconByName(opt1Runes.firstOrNull() ?: extractMainRune(champ.recommendedRunes))

        val syncedSwaps = generateSituationalSwaps(situationalItems, champ.damageType, champ.isFrontline, champ.itemSwaps, role = champ.primaryRole)

        val buildOptions = generate4BuildOptions(
            champ = champ,
            role = champ.primaryRole,
            defaultBuild8 = build8,
            defaultBootBase = baseBoot,
            defaultBootUpgrade = bootUpgrade,
            opt1Runes = opt1Runes,
            opt2Runes = opt2Runes,
            defaultSpells = resolvedSpells,
            defaultSpellsIcons = resolvedSpellsIcons
        )

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
            recommendedSpells = resolvedSpells,
            spellsIcons = resolvedSpellsIcons,
            advantageAgainst = champ.advantageAgainst,
            counteredBy = champ.counteredBy,
            synergies = champ.synergies,
            tacticalAdvice = champ.tacticalAdvice,
            build8Items = build8,
            bootBase = baseBoot,
            bootUpgrade = bootUpgrade,
            runesOption1 = opt1Runes,
            runesOption2 = opt2Runes,
            buildOptions = buildOptions
        )
    }

    private fun buildFlexRoleProfile(champ: Champion, role: LaneRole): ChampionRoleProfile {
        val isAp = champ.damageType == DamageType.MAGIC
        val isTank = champ.isFrontline || role == LaneRole.SUPPORT || (role == LaneRole.TOP && !champ.isRanged)
        val isMarksman = champ.isRanged && champ.damageType == DamageType.PHYSICAL

        // 1. Spells for flex role (ensuring 2 distinct spells)
        val recommendedSpells = when (role) {
            LaneRole.JUNGLE -> listOf("Castigo", "Destello")
            LaneRole.SUPPORT -> listOf("Destello", "Ignición")
            LaneRole.ADC -> listOf("Destello", "Barrera")
            LaneRole.TOP -> listOf("Destello", "Teleportación")
            LaneRole.MID -> listOf("Destello", "Ignición")
        }
        val spellsIcons = recommendedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        // 2. Dynamic 8 Items for flex role (6 Core Legendaries + 2 Situational, no boots in items row)
        val rawFlexCore: List<String> = when (role) {
            LaneRole.JUNGLE -> if (isAp) {
                listOf("Diente de Nashor", "Eco de Luden", "Orbe infinito", "Sombrero mortal de Rabadon", "Bastón del vacío", "Hacedor de grietas")
            } else if (isTank) {
                listOf("Coraza del muerto", "Malla de espinas", "Fuerza de la naturaleza", "Corona abrasadora", "Corazón de acero", "Guardia gemela de amaranto")
            } else {
                listOf("Fuerza de la Trinidad", "El coleccionista", "Borde infinito", "Saludos de Dominik", "La danza de la muerte", "Ángel custodio")
            }
            LaneRole.SUPPORT -> if (isAp) {
                listOf("Guadaña de la Niebla Negra", "Eco armónico", "Incensario ardiente", "Bastón de aguas fluidas", "Mandato imperial", "Bendición de Mikael")
            } else {
                listOf("Escudo de reliquia", "La convergencia de Zeke", "Voto de caballero", "Malla de espinas", "Fuerza de la naturaleza", "Relicario de los Solari de Hierro")
            }
            LaneRole.ADC -> listOf(
                "Borde infinito", "Blaster magnético", "Saludos de Dominik", "Sanguinario", "El coleccionista", "Ángel custodio"
            )
            LaneRole.TOP -> if (isAp) {
                listOf("Hacedor de grietas", "Cetro de cristal de Rylai", "Tormento de Liandry", "Sombrero mortal de Rabadon", "Bastón del vacío", "Morellonomicón")
            } else if (isTank) {
                listOf("Corazón de acero", "Égida de fuego solar", "Malla de espinas", "Fuerza de la naturaleza", "Presagio de Randuin", "Guardia gemela de amaranto")
            } else {
                listOf("Fuerza de la Trinidad", "Cuchilla negra", "Guantelete de Sterak", "La danza de la muerte", "Rompecascos", "Ángel custodio")
            }
            LaneRole.MID -> if (isAp) {
                listOf("Eco de Luden", "Orbe infinito", "Sombrero mortal de Rabadon", "Bastón del vacío", "Impulso cósmico", "Tormento de Liandry")
            } else {
                listOf("El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El coleccionista", "El rencor de Serylda", "La danza de la muerte", "Filo de la noche")
            }
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

        val syncedSwaps = generateSituationalSwaps(situationalItems, champ.damageType, isTank, emptyList(), role = role)

        val flexWinrate = adjustRate(champ.winrate, -1.2)
        val flexPickRate = adjustRate(champ.pickRate * 0.4, 0.0)
        val flexBanRate = champ.banRate

        val flexTier = when {
            flexWinrate >= 52.0 -> "S"
            flexWinrate >= 50.0 -> "A"
            flexWinrate >= 48.5 -> "B"
            else -> "C"
        }

        val buildOptions = generate4BuildOptions(
            champ = champ,
            role = role,
            defaultBuild8 = build8,
            defaultBootBase = baseBoot,
            defaultBootUpgrade = bootUpgrade,
            opt1Runes = opt1Runes,
            opt2Runes = opt2Runes,
            defaultSpells = recommendedSpells,
            defaultSpellsIcons = spellsIcons
        )

        // 3. Dynamic Lane Matchups & Synergies for Flex Role
        val (flexAdvantages, flexCounters, flexSynergies) = getLaneMatchupsAndSynergies(champ, role)
        val flexTacticalAdvice = CoachingGenerator.generateTacticalAnalysis(champ, role, "es")

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
            advantageAgainst = flexAdvantages,
            counteredBy = flexCounters,
            synergies = flexSynergies,
            tacticalAdvice = flexTacticalAdvice,
            build8Items = build8,
            bootBase = baseBoot,
            bootUpgrade = bootUpgrade,
            runesOption1 = opt1Runes,
            runesOption2 = opt2Runes,
            buildOptions = buildOptions
        )
    }

    /**
     * Calcula ventajas, debilidades y sinergias adaptadas al rol Flex específico
     * para evitar mostrar los matchups del rol principal cuando se selecciona otra línea.
     */
    private fun getLaneMatchupsAndSynergies(champ: Champion, role: LaneRole): Triple<List<String>, List<String>, List<String>> {
        val isAp = champ.damageType == DamageType.MAGIC
        val isRanged = champ.isRanged
        val isTank = champ.isFrontline

        return when (role) {
            LaneRole.TOP -> {
                val advantages = if (isRanged) listOf("Darius", "Garen", "Sion", "Sett")
                else if (isAp) listOf("Malphite", "Dr. Mundo", "Sion", "Shen")
                else listOf("Teemo", "Kayle", "Irelia", "Yasuo")

                val counters = if (isRanged) listOf("Irelia", "Camille", "Jax", "Malphite")
                else if (isTank) listOf("Gwen", "Fiora", "Vayne", "Aatrox")
                else listOf("Renekton", "Darius", "Fiora", "Jax")

                val synergies = listOf("Lee Sin", "Jarvan IV", "Vi", "Orianna")
                Triple(advantages, counters, synergies)
            }
            LaneRole.JUNGLE -> {
                val advantages = if (isTank) listOf("Master Yi", "Kha'Zix", "Evelynn", "Kayn")
                else if (isAp) listOf("Rammus", "Amumu", "Shyvana", "Xin Zhao")
                else listOf("Shyvana", "Evelynn", "Amumu", "Gragas")

                val counters = listOf("Lee Sin", "Xin Zhao", "Olaf", "Warwick")
                val synergies = listOf("Yasuo", "Ahri", "Darius", "Nautilus")
                Triple(advantages, counters, synergies)
            }
            LaneRole.MID -> {
                val advantages = if (isAp) listOf("Yasuo", "Galio", "Kassadin", "Twisted Fate")
                else listOf("Veigar", "Lux", "Aurelion Sol", "Ziggs")

                val counters = listOf("Zed", "Akali", "Yone", "Syndra")
                val synergies = listOf("Lee Sin", "Jarvan IV", "Vi", "Nautilus")
                Triple(advantages, counters, synergies)
            }
            LaneRole.ADC -> {
                val advantages = listOf("Vayne", "Kai'Sa", "Samira", "Tristana")
                val counters = listOf("Caitlyn", "Draven", "Varus", "Miss Fortune")
                val synergies = listOf("Thresh", "Nautilus", "Leona", "Lulu")
                Triple(advantages, counters, synergies)
            }
            LaneRole.SUPPORT -> {
                val advantages = if (isTank) listOf("Sona", "Soraka", "Yuumi", "Nami")
                else listOf("Blitzcrank", "Nautilus", "Braum", "Alistar")

                val counters = if (isTank) listOf("Morgana", "Janna", "Lulu", "Zyra")
                else listOf("Pyke", "Thresh", "Leona", "Nautilus")

                val synergies = listOf("Jinx", "Kai'Sa", "Samira", "Lucian")
                Triple(advantages, counters, synergies)
            }
        }
    }

    private fun ensureUniqueSpells(spells: List<String>, role: LaneRole): List<String> {
        val unique = spells.distinct().toMutableList()
        if (unique.size >= 2) return unique.take(2)

        val defaultSecondary = when (role) {
            LaneRole.JUNGLE -> if (unique.contains("Castigo")) "Destello" else "Castigo"
            LaneRole.SUPPORT -> if (unique.contains("Destello")) "Ignición" else "Destello"
            LaneRole.ADC -> if (unique.contains("Destello")) "Barrera" else "Destello"
            LaneRole.TOP -> if (unique.contains("Destello")) "Teleportación" else "Destello"
            LaneRole.MID -> if (unique.contains("Destello")) "Ignición" else "Destello"
        }

        if (!unique.contains(defaultSecondary)) {
            unique.add(defaultSecondary)
        }
        if (unique.size < 2) {
            unique.add("Destello")
        }
        return unique.distinct().take(2)
    }

    fun generate4BuildOptions(
        champ: Champion,
        role: LaneRole,
        defaultBuild8: List<String>,
        defaultBootBase: String,
        defaultBootUpgrade: String,
        opt1Runes: List<String>,
        opt2Runes: List<String>,
        defaultSpells: List<String>,
        defaultSpellsIcons: List<String>
    ): List<ChampionBuildOption> {
        val isAp = champ.damageType == DamageType.MAGIC
        val isTank = champ.isFrontline || role == LaneRole.SUPPORT || (role == LaneRole.TOP && !champ.isRanged)
        val isMarksman = champ.isRanged && champ.damageType == DamageType.PHYSICAL
        val isSupport = role == LaneRole.SUPPORT
        val isSpecialDamageSupport = isSupport && (champ.name.equals("Pyke", ignoreCase = true) || champ.name.equals("Senna", ignoreCase = true))

        val resolvedSpells1 = ensureUniqueSpells(defaultSpells, role)
        val resolvedSpellsIcons1 = resolvedSpells1.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        // =========================================================================
        // OPCIÓN 1: META CORE ESTÁNDAR (WildRiftFire / BestBuildWR)
        // =========================================================================
        val opt1 = ChampionBuildOption(
            optionNumber = 1,
            title = "Opción 1: Meta Core Estándar",
            subtitle = "WildRiftFire • BestBuildWR",
            source = "WildRiftFire / BestBuildWR",
            badge = "ESTÁNDAR",
            tacticalReason = "Build estándar de referencia oficial con mayor tasa de victoria equilibrada en el meta actual de Wild Rift. Proporciona una transición suave entre el juego temprano y las peleas por el Dragón.",
            items = defaultBuild8,
            bootBase = defaultBootBase,
            bootUpgrade = defaultBootUpgrade,
            runes = opt1Runes,
            spells = resolvedSpells1,
            spellsIcons = resolvedSpellsIcons1
        )

        // =========================================================================
        // OPCIÓN 2: RÁFAGA / SNOWBALL OFENSIVO (WildRiftCore)
        // =========================================================================
        val opt2Items: List<String> = when {
            isSupport && !isSpecialDamageSupport -> listOf(
                "Guadaña de la Niebla Negra", "Mandato imperial", "Bastón de aguas fluidas",
                "Eco armónico", "Incensario ardiente", "Tridente de oceánida", "Redención", "Bendición de Mikael"
            )
            isAp -> listOf(
                "Eco de Luden", "Orbe infinito", "Sombrero mortal de Rabadon",
                "Bastón del vacío", "Impulso cósmico", "Tormento de Liandry", "Morellonomicón", "Reloj de arena de Zhonya"
            )
            isMarksman -> listOf(
                "El coleccionista", "Borde infinito", "Blaster magnético",
                "Saludos de Dominik", "Bailarina fantasma", "Sanguinario", "Cañón de fuego rápido", "Ángel custodio"
            )
            isTank -> listOf(
                "Égida de fuego solar", "Corona abrasadora", "Malla de espinas",
                "Corazón de acero", "Fuerza de la naturaleza", "Presagio de Randuin", "Guardia gemela de amaranto", "Relicario de los Solari de Hierro"
            )
            else -> listOf(
                "El cuchillo fantasma de Youmuu", "Cielo desgarrado", "El coleccionista",
                "El rencor de Serylda", "Filo de la noche", "La danza de la muerte", "Ángel custodio", "Fauces de Malmortius"
            )
        }

        val opt2BootBase = getBaseTier2Boot(opt2Items, champ.damageType, isTank, champ.isRanged, role)
        val opt2BootUpgrade = getTier3BootUpgrade(opt2BootBase)
        val opt2Spells = ensureUniqueSpells(
            when (role) {
                LaneRole.JUNGLE -> listOf("Castigo", "Destello")
                LaneRole.ADC -> listOf("Destello", "Fantasmal")
                LaneRole.SUPPORT -> listOf("Destello", "Ignición")
                else -> listOf("Destello", "Ignición")
            },
            role
        )
        val opt2SpellsIcons = opt2Spells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val opt2 = ChampionBuildOption(
            optionNumber = 2,
            title = "Opción 2: Ráfaga & Snowball Agresivo",
            subtitle = "WildRiftCore • High Elo Pro",
            source = "WildRiftCore",
            badge = "OFENSIVA",
            tacticalReason = "Orientada a dominar los primeros 8 minutos y conseguir ventajas decisivas de oro. Maximiza daño de ráfaga y letalidad/AP crítico para eliminar al carry enemigo al instante.",
            items = opt2Items,
            bootBase = opt2BootBase,
            bootUpgrade = opt2BootUpgrade,
            runes = opt2Runes.ifEmpty { opt1Runes },
            spells = opt2Spells,
            spellsIcons = opt2SpellsIcons
        )

        // =========================================================================
        // OPCIÓN 3: ANTI-TANQUES & COLOSOS (Coach Challenger)
        // =========================================================================
        val opt3Items: List<String> = when {
            isSupport && !isSpecialDamageSupport -> listOf(
                "Guadaña de la Niebla Negra", "Tridente de oceánida", "Mandato imperial",
                "Morellonomicón", "Incensario ardiente", "Bastón de aguas fluidas", "Redención", "Bendición de Mikael"
            )
            isAp -> listOf(
                "Tormento de Liandry", "Hacedor de grietas", "Bastón del vacío",
                "Sombrero mortal de Rabadon", "Cetro de cristal de Rylai", "Morellonomicón", "Impulso cósmico", "Reloj de arena de Zhonya"
            )
            isMarksman -> listOf(
                "Espada del Rey Arruinado", "Cuchilla negra", "Saludos de Dominik",
                "Recordatorio mortal", "Borde infinito", "Al filo de la cordura", "Sanguinario", "Ángel custodio"
            )
            isTank -> listOf(
                "Corazón de acero", "Égida de fuego solar", "Malla de espinas",
                "Corona abrasadora", "Fuerza de la naturaleza", "Presagio de Randuin", "Guardia gemela de amaranto", "Relicario de los Solari de Hierro"
            )
            else -> listOf(
                "Cuchilla negra", "Espada del Rey Arruinado", "El rencor de Serylda",
                "La danza de la muerte", "Recordatorio mortal", "Guantelete de Sterak", "Fuerza de la Trinidad", "Ángel custodio"
            )
        }

        val opt3Runes = listOf("Conquistador", "Verdugo de gigantes", "Impacto repentino", "Cazador titánico")
        val opt3BootBase = getBaseTier2Boot(opt3Items, champ.damageType, isTank, champ.isRanged, role)
        val opt3BootUpgrade = getTier3BootUpgrade(opt3BootBase)
        val opt3Spells = ensureUniqueSpells(
            when (role) {
                LaneRole.JUNGLE -> listOf("Castigo", "Destello")
                LaneRole.SUPPORT -> listOf("Destello", "Extenuación")
                else -> listOf("Destello", "Extenuación")
            },
            role
        )
        val opt3SpellsIcons = opt3Spells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val opt3 = ChampionBuildOption(
            optionNumber = 3,
            title = "Opción 3: Anti-Tanques & Colosos",
            subtitle = "Coach Táctico Challenger",
            source = "Coach Challenger",
            badge = "ANTI-TANQUE",
            tacticalReason = "¿Por qué y contra quién?: Diseñada contra composiciones con 2 o más tanques o colosos pesados (Sion, Ornn, Dr. Mundo, Nautilus, Leona, Volibear). Incorpora penetración porcentual de armadura/RM (Cuchilla negra, Saludos de Dominik, Bastón del vacío), daño porcentual de vida máxima (Espada del Rey Arruinado, Tormento de Liandry, Hacedor de grietas) y reducción de curaciones.",
            items = opt3Items,
            bootBase = opt3BootBase,
            bootUpgrade = opt3BootUpgrade,
            runes = opt3Runes,
            spells = opt3Spells,
            spellsIcons = opt3SpellsIcons
        )

        // =========================================================================
        // OPCIÓN 4: ANTI-MAGOS & SUPERVIVENCIA AP (Coach Challenger)
        // =========================================================================
        val opt4Items: List<String> = when {
            isSupport && !isSpecialDamageSupport -> listOf(
                "Guadaña de la Niebla Negra", "Bendición de Mikael", "Bastón de aguas fluidas",
                "Eco armónico", "Velo de alma en pena", "Torreón de Kaenic", "Relicario de los Solari de Hierro", "Redención"
            )
            isAp -> listOf(
                "Báculo del arcángel", "Velo de alma en pena", "Torreón de Kaenic",
                "Sombrero mortal de Rabadon", "Reloj de arena de Zhonya", "Bastón del vacío", "Abrazo del serafín", "Morellonomicón"
            )
            isMarksman -> listOf(
                "Al filo de la cordura", "Fauces de Malmortius", "Borde infinito",
                "Filo de la noche", "Sanguinario", "Saludos de Dominik", "Ángel custodio", "Fajín de mercurio"
            )
            isTank -> listOf(
                "Torreón de Kaenic", "Fuerza de la naturaleza", "Máscara abisal",
                "Corazón de acero", "Malla de espinas", "Guardia gemela de amaranto", "Presagio de Randuin", "Relicario de los Solari de Hierro"
            )
            else -> listOf(
                "Al filo de la cordura", "Fauces de Malmortius", "Guantelete de Sterak",
                "Cuchilla negra", "Torreón de Kaenic", "La danza de la muerte", "Filo de la noche", "Ángel custodio"
            )
        }

        val opt4Runes = listOf("Garras del inmortal", "Orbe anulador", "Segundo aire", "Sobrecrecimiento")
        val opt4BootBase = "Botas de mercurio"
        val opt4BootUpgrade = "Trituradoras encadenadas"
        val opt4Spells = ensureUniqueSpells(
            when (role) {
                LaneRole.JUNGLE -> listOf("Castigo", "Destello")
                LaneRole.ADC -> listOf("Destello", "Barrera")
                else -> listOf("Destello", "Barrera")
            },
            role
        )
        val opt4SpellsIcons = opt4Spells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val opt4 = ChampionBuildOption(
            optionNumber = 4,
            title = "Opción 4: Anti-Magos & Supervivencia",
            subtitle = "Coach Táctico Challenger",
            source = "Coach Challenger",
            badge = "ANTI-MAGO",
            tacticalReason = "¿Por qué y contra quién?: Diseñada para neutralizar composiciones enemigas con 3+ fuentes de daño mágico o asesinos de ráfaga AP (Akali, Evelynn, Katarina, Syndra, Ziggs, Aurelion Sol). Prioriza resistencia mágica pesada (Torreón de Kaenic, Al filo de la cordura, Fauces de Malmortius, Velo de alma en pena), escudos y tenacidad en botas.",
            items = opt4Items,
            bootBase = opt4BootBase,
            bootUpgrade = opt4BootUpgrade,
            runes = opt4Runes,
            spells = opt4Spells,
            spellsIcons = opt4SpellsIcons
        )

        return listOf(opt1, opt2, opt3, opt4)
    }

    private fun generateSituationalSwaps(
        situationalItems: List<String>,
        damageType: DamageType,
        isTank: Boolean,
        explicitSwaps: List<ItemSwap> = emptyList(),
        role: LaneRole? = null
    ): List<ItemSwap> {
        val s1 = situationalItems.getOrElse(0) { if (role == LaneRole.SUPPORT) "Relicario de los Solari de Hierro" else "Ángel custodio" }
        val s2 = situationalItems.getOrElse(1) { if (role == LaneRole.SUPPORT) "Redención" else "Morellonomicón" }

        if (explicitSwaps.isNotEmpty()) {
            return explicitSwaps.mapIndexed { idx, swap ->
                val baseSituational = if (idx == 0) s1 else s2
                val slotNumber = if (idx == 0) "7" else "8"
                swap.copy(
                    coreItem = baseSituational,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(baseSituational),
                    altItemIcon = WildRiftItemsData.getItemIconByName(swap.altItem),
                    reasonTitle = if (!swap.reasonTitle.contains("OBJETO", ignoreCase = true)) {
                        "OBJETO $slotNumber (SITUACIONAL ${idx + 1}) ➔ ${swap.reasonTitle}"
                    } else swap.reasonTitle
                )
            }
        }

        val swaps = mutableListOf<ItemSwap>()

        if (role == LaneRole.SUPPORT && !isTank) {
            val alt1 = if (s1.equals("Relicario de los Solari de Hierro", ignoreCase = true)) "Redención" else "Relicario de los Solari de Hierro"
            val alt2 = if (s2.equals("Bendición de Mikael", ignoreCase = true) || s2.equals(alt1, ignoreCase = true)) "Incensario ardiente" else "Bendición de Mikael"

            swaps.add(
                ItemSwap(
                    coreItem = s1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s1),
                    altItem = alt1,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt1),
                    reasonTitle = "OBJETO 7 (SITUACIONAL 1) ➔ PROTECCIÓN EN ÁREA & ESCUDOS",
                    reasonDesc = "Contra daño explosivo o definitivas en área del equipo enemigo, activa el Relicario o Redención para salvar a tus aliados.",
                    againstWho = "Kennen, Miss Fortune, Katarina, Brand, Diana, Fiddlesticks"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = s2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s2),
                    altItem = alt2,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt2),
                    reasonTitle = "OBJETO 8 (SITUACIONAL 2) ➔ PURIFICACIÓN & DESBLOQUEO DE CC",
                    reasonDesc = "Si el rival tiene aturdimientos o inmovilizaciones decisivas sobre tu tirador carry, equipa Bendición de Mikael.",
                    againstWho = "Ashe, Twisted Fate, Leona, Nautilus, Morgana, Sejuani"
                )
            )
        } else if (damageType == DamageType.MAGIC && role != LaneRole.SUPPORT) {
            val alt1 = if (s1.equals("Morellonomicón", ignoreCase = true)) "Reloj de arena de Zhonya" else "Morellonomicón"
            val alt2 = if (s2.equals("Reloj de arena de Zhonya", ignoreCase = true) || s2.equals(alt1, ignoreCase = true)) "Velo de alma en pena" else "Reloj de arena de Zhonya"

            swaps.add(
                ItemSwap(
                    coreItem = s1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s1),
                    altItem = alt1,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt1),
                    reasonTitle = "OBJETO 7 (SITUACIONAL 1) ➔ ANTI-CURACIÓN",
                    reasonDesc = "Si el equipo enemigo tiene alta regeneración o curanderos masivos, sustituye el objeto situacional 7 por Heridas Graves.",
                    againstWho = "Soraka, Dr. Mundo, Aatrox, Warwick, Vladimir, Yuumi"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = s2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s2),
                    altItem = alt2,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt2),
                    reasonTitle = "OBJETO 8 (SITUACIONAL 2) ➔ SUPERVIVENCIA & ÉSTASIS",
                    reasonDesc = "Si sufres de emboscadas o burst explosivo enemigo en peleas de equipo, sustituye el objeto situacional 8 por estasis.",
                    againstWho = "Zed, Talon, Fizz, Kayn, Syndra, Rengar"
                )
            )
        } else if (isTank) {
            val alt1 = if (s1.equals("Malla de espinas", ignoreCase = true)) "Presagio de Randuin" else "Malla de espinas"
            val alt2 = if (s2.equals("Fuerza de la naturaleza", ignoreCase = true) || s2.equals(alt1, ignoreCase = true)) "Corona abrasadora" else "Fuerza de la naturaleza"

            swaps.add(
                ItemSwap(
                    coreItem = s1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s1),
                    altItem = alt1,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt1),
                    reasonTitle = "OBJETO 7 (SITUACIONAL 1) ➔ ANTI-CURACIÓN & ARMADURA",
                    reasonDesc = "Si los rivales dependen de vampirismo y robo de vida físico, adapta tu objeto situacional 7 con Malla de Espinas.",
                    againstWho = "Aatrox, Warwick, Maestro Yi, Samira, Olaf"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = s2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s2),
                    altItem = alt2,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt2),
                    reasonTitle = "OBJETO 8 (SITUACIONAL 2) ➔ RESISTENCIA MÁGICA & MOVILIDAD",
                    reasonDesc = "Contra daño mágico sostenido o múltiple fuente AP en composiciones enemigas, adapta el objeto 8.",
                    againstWho = "Evelynn, Teemo, Brand, Aurelion Sol, Gwen"
                )
            )
        } else {
            val alt1 = if (s1.equals("Colmillo de serpiente", ignoreCase = true)) "Malla de espinas" else "Colmillo de serpiente"
            val alt2 = if (s2.equals("Ángel custodio", ignoreCase = true) || s2.equals(alt1, ignoreCase = true)) "Fajín de mercurio" else "Ángel custodio"

            swaps.add(
                ItemSwap(
                    coreItem = s1,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s1),
                    altItem = alt1,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt1),
                    reasonTitle = "OBJETO 7 (SITUACIONAL 1) ➔ ANTI-ESCUDOS",
                    reasonDesc = "Si la composición rival cuenta con escudos masivos (escudos de área o habilidades), cambia el objeto situacional 7 por Colmillo de Serpiente.",
                    againstWho = "Sett, Shen, Karma, Lulu, Sterak, Janna"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = s2,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(s2),
                    altItem = alt2,
                    altItemIcon = WildRiftItemsData.getItemIconByName(alt2),
                    reasonTitle = "OBJETO 8 (SITUACIONAL 2) ➔ SEGUNDA VIDA / RESURRECCIÓN",
                    reasonDesc = "Para peleas decisivas de Baron o Dragón Anciano donde una eliminación temprana costaría la partida, adapta tu objeto 8.",
                    againstWho = "Asesinos letales, composiciones de engage y wombo-combos"
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
