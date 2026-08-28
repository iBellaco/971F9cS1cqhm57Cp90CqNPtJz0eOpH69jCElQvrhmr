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

    /**
     * Determina si un objeto es puramente de daño de carry/mid (AP burst o AD crítico/letalidad puro)
     * no apto para soportes de utilidad o tanques.
     */
    fun isPureDamageCarryItem(name: String): Boolean {
        val clean = name.lowercase().trim()
        val carryDamagePatterns = listOf(
            "rabadon", "sombrero mortífero", "gorro de muerte", "deathcap",
            "bastón del vacío", "bastón vacío", "baston del vacio", "baston vacio", "void staff",
            "orbe infinito", "infinity orb", "luden", "eco de luden", "luden's echo",
            "diente de nashor", "nashor", "impulso cósmico", "cosmic drive", "enfoque del horizonte", "horizon focus",
            "antorcha de fuego negro", "blackfire torch", "oleada de tormenta", "stormsurge",
            "borde infinito", "infinity edge", "el coleccionista", "el recaudador", "the collector",
            "saludos de dominik", "lord dominik", "sanguinario", "bloodthirster", "el huracán de runaan",
            "runaan", "cañón de fuego rápido", "blaster magnético", "magnetic blaster", "bailarina fantasma",
            "phantom dancer", "arcoescudo inmortal", "inmortal shieldbow", "filo de la noche", "edge of night",
            "hoja del ocaso de draktharr", "draktharr", "el cuchillo fantasma de youmuu", "youmuu",
            "el rencor de serylda", "serylda", "fuerza trinitaria", "trinity force", "perdición del exánime", "lich bane"
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
                    "Sudario del alba", "malla de espinas", "Fuerza de la naturaleza", "Relicario de los Solari de Hierro"
                )
                else -> listOf(
                    "Guadaña de la Niebla Negra", "Eco armónico", "Staff of Flowing Water",
                    "Incensario Ardiente", "Mandato imperial", "La bendición de Michael", "Redención"
                )
            }
            LaneRole.ADC -> listOf(
                "Borde infinito", "Blaster magnético", "Saludos de Dominik",
                "sanguinario", "El coleccionista", "Bailarina fantasma", "Cañón de fuego rápido"
            )
            LaneRole.JUNGLE -> if (damageType == DamageType.MAGIC) {
                listOf("Luden's Echo", "Diente de Nashor", "Orbe infinito", "Gorro de muerte del miércoles", "Bastón vacío", "Hacedor de grietas")
            } else if (isTank) {
                listOf("Plato del hombre muerto", "malla de espinas", "Fuerza de la naturaleza", "Corona abrasadora", "Guardia gemela de amaranto")
            } else {
                listOf("Fuerza trinitaria", "El coleccionista", "Black Cleaver", "La danza de la muerte", "Sterak's Gage", "Ángel custodio")
            }
            LaneRole.TOP -> if (damageType == DamageType.MAGIC) {
                listOf("Hacedor de grietas", "Cetro de cristal de Rylai", "El tormento de Liandry", "Vara de las edades", "Gorro de muerte del miércoles")
            } else if (isTank) {
                listOf("corazón de acero", "Égida del fuego solar", "malla de espinas", "Fuerza de la naturaleza", "El presagio de Randuin")
            } else {
                listOf("Fuerza trinitaria", "Black Cleaver", "La danza de la muerte", "Sterak's Gage", "Ángel custodio", "Rompegalope")
            }
            LaneRole.MID -> if (damageType == DamageType.MAGIC) {
                listOf("Luden's Echo", "Orbe infinito", "Gorro de muerte del miércoles", "Bastón vacío", "Impulso Cósmico", "El tormento de Liandry")
            } else {
                listOf("El cuchillo fantasma de Youmuu", "Hoja del Ocaso de Draktharr", "El coleccionista", "El rencor de Serylda", "La danza de la muerte")
            }
        }

        for (item in fallbackLegendaries) {
            if (filteredRawCore.size >= 4) break
            if (!filteredRawCore.contains(item)) {
                filteredRawCore.add(item)
            }
        }

        val l1 = filteredRawCore.getOrElse(0) { fallbackLegendaries[0] }
        val l2 = filteredRawCore.getOrElse(1) { fallbackLegendaries[1] }
        val l3 = filteredRawCore.getOrElse(2) { fallbackLegendaries[2] }
        val l4 = filteredRawCore.getOrElse(3) { fallbackLegendaries[3] }

        // 2. Extraer 2 situacionales únicos (Items 7 y 8) con filtros de rol
        val filteredRawSituational = rawSituational.filter { item ->
            if (isBootItem(item) || item == l1 || item == l2 || item == l3 || item == l4) return@filter false
            if (role == LaneRole.SUPPORT && !isAssassinsOrAdcSupport) {
                if (isPureDamageCarryItem(item)) return@filter false
            }
            true
        }.distinct().toMutableList()

        val defaultSituational = when (role) {
            LaneRole.SUPPORT -> when {
                isAssassinsOrAdcSupport -> listOf("Colmillo de serpiente", "Ángel custodio", "Fauces de Malmortius", "Fajín de mercurio")
                isTank -> listOf("malla de espinas", "El presagio de Randuin", "Relicario de los Solari de Hierro", "Redención")
                else -> listOf("Incensario Ardiente", "Redención", "Relicario de los Solari de Hierro", "La bendición de Michael")
            }
            LaneRole.ADC -> listOf("Ángel custodio", "Fajín de mercurio", "Recordatorio mortal", "sanguinario")
            LaneRole.JUNGLE, LaneRole.TOP, LaneRole.MID -> when {
                isTank -> listOf("malla de espinas", "El presagio de Randuin", "Fuerza de la naturaleza", "Corona abrasadora")
                damageType == DamageType.MAGIC -> listOf("Morellonomicón", "El reloj de arena de Zhonya", "Velo de alma en pena", "Malignance")
                else -> listOf("malla de espinas", "Colmillo de serpiente", "Ángel custodio", "Fajín de mercurio")
            }
        }

        for (item in defaultSituational) {
            if (filteredRawSituational.size >= 2) break
            if (!filteredRawSituational.contains(item) && item != l1 && item != l2 && item != l3 && item != l4) {
                filteredRawSituational.add(item)
            }
        }

        val s1 = filteredRawSituational.getOrElse(0) { defaultSituational[0] }
        val s2 = filteredRawSituational.getOrElse(1) { defaultSituational[1] }

        // Build 1..8: 1=Bota T2, 2=Core 1, 3=Core 2, 4=Bota T3, 5=Core 3, 6=Core 4, 7=Sit 1, 8=Sit 2
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

        val resolvedSpellsIcons = if (champ.recommendedSpells.isNotEmpty()) {
            champ.recommendedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }
        } else {
            listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_IGNITE)
        }

        val (opt1Runes, opt2Runes) = generateRunesOptions(champ, champ.primaryRole)
        val primaryRuneIcon = WildRiftSpellsAndRunes.getRuneIconByName(opt1Runes.firstOrNull() ?: extractMainRune(champ.recommendedRunes))

        val syncedSwaps = generateSituationalSwaps(situationalItems, champ.damageType, champ.isFrontline, champ.itemSwaps, role = champ.primaryRole)

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
            val alt2 = if (s2.equals("La bendición de Michael", ignoreCase = true) || s2.equals(alt1, ignoreCase = true)) "Incensario Ardiente" else "La bendición de Michael"

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
            val alt1 = if (s1.equals("Morellonomicón", ignoreCase = true)) "El reloj de arena de Zhonya" else "Morellonomicón"
            val alt2 = if (s2.equals("El reloj de arena de Zhonya", ignoreCase = true) || s2.equals(alt1, ignoreCase = true)) "Velo de alma en pena" else "El reloj de arena de Zhonya"

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
                    reasonTitle = "OBJETO 8 (SITUACIONAL 2) ➔ SUPERVIVENCIA & ÉXTASIS",
                    reasonDesc = "Si sufres de emboscadas o burst explosivo enemigo en peleas de equipo, sustituye el objeto situacional 8 por estasis.",
                    againstWho = "Zed, Talon, Fizz, Kayn, Syndra, Rengar"
                )
            )
        } else if (isTank) {
            val alt1 = if (s1.equals("malla de espinas", ignoreCase = true)) "El presagio de Randuin" else "malla de espinas"
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
            val alt1 = if (s1.equals("Colmillo de serpiente", ignoreCase = true)) "malla de espinas" else "Colmillo de serpiente"
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
