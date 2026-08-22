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
    val tacticalAdvice: String
)

object ChampionRoleAdapter {

    fun getProfile(champion: Champion, targetRole: LaneRole): ChampionRoleProfile {
        val isPrimary = targetRole == champion.primaryRole

        if (isPrimary) {
            return buildPrimaryProfile(champion)
        }

        return buildFlexRoleProfile(champion, targetRole)
    }

    private fun buildPrimaryProfile(champ: Champion): ChampionRoleProfile {
        val completedCoreItems = ensureSixItems(champ.coreItems, champ.damageType, champ.isFrontline, champ.isRanged, champ.primaryRole)
        val coreIcons = completedCoreItems.map { WildRiftItemsData.getItemIconByName(it) }

        val situationalIcons = champ.situationalItems.map { WildRiftItemsData.getItemIconByName(it) }

        val resolvedSpellsIcons = if (champ.recommendedSpells.isNotEmpty()) {
            champ.recommendedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }
        } else {
            listOf(WildRiftSpellsAndRunes.SPELL_FLASH, WildRiftSpellsAndRunes.SPELL_IGNITE)
        }

        val primaryRuneIcon = if (champ.primaryRuneIconUrl.isNotBlank() && !champ.primaryRuneIconUrl.contains("item/")) {
            champ.primaryRuneIconUrl
        } else {
            WildRiftSpellsAndRunes.getRuneIconByName(extractMainRune(champ.recommendedRunes))
        }

        val syncedSwaps = if (champ.itemSwaps.isNotEmpty()) {
            champ.itemSwaps.map { swap ->
                swap.copy(
                    coreItemIcon = WildRiftItemsData.getItemIconByName(swap.coreItem),
                    altItemIcon = WildRiftItemsData.getItemIconByName(swap.altItem)
                )
            }
        } else {
            generateDefaultSwaps(completedCoreItems, champ.situationalItems, champ.damageType, champ.isFrontline)
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
            situationalItems = champ.situationalItems.ifEmpty { getDefaultSituationalItems(champ.damageType, champ.isFrontline) },
            situationalItemsIcons = situationalIcons.ifEmpty { getDefaultSituationalItems(champ.damageType, champ.isFrontline).map { WildRiftItemsData.getItemIconByName(it) } },
            itemSwaps = syncedSwaps,
            recommendedRunes = champ.recommendedRunes,
            runeTreeDetails = champ.runeTreeDetails,
            primaryRuneIconUrl = primaryRuneIcon,
            recommendedSpells = champ.recommendedSpells.ifEmpty { listOf("Destello", "Ignición") },
            spellsIcons = resolvedSpellsIcons,
            advantageAgainst = champ.advantageAgainst,
            counteredBy = champ.counteredBy,
            synergies = champ.synergies,
            tacticalAdvice = champ.tacticalAdvice
        )
    }

    private fun buildFlexRoleProfile(champ: Champion, role: LaneRole): ChampionRoleProfile {
        val isAp = champ.damageType == DamageType.MAGIC
        val isTank = champ.isFrontline || champ.primaryRole == LaneRole.SUPPORT || (champ.primaryRole == LaneRole.TOP && !champ.isRanged)
        val isMarksman = champ.isRanged && champ.damageType == DamageType.PHYSICAL
        val isSupportEnchanter = champ.primaryRole == LaneRole.SUPPORT && !champ.isFrontline

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

        // 2. Dynamic 6 Core Items for flex role
        val coreItems: List<String> = when (role) {
            LaneRole.JUNGLE -> {
                if (isAp) {
                    listOf("Diente de Nashor", "Eco de Luden", "Orbe del Infinito", "Botas Jonias de la Lucidez", "Sombrero Mortal de Rabadon", "Báculo del Vacío")
                } else if (isTank) {
                    listOf("Coraza del Muerto", "Malla de Espinas", "Fuerza de la Naturaleza", "Punteras Revestidas", "Corona Abrasadora", "Protector Pétreo")
                } else if (isMarksman) {
                    listOf("Fuerza de la Trinidad", "Recaudadora", "Filo del Infinito", "Grebas Berserker", "Recuerdos de Lord Dominik", "Ángel Guardián")
                } else {
                    listOf("Fuerza de la Trinidad", "Cuchilla Negra", "Danza de la Muerte", "Punteras Revestidas", "Calibrador de Sterak", "Ángel Guardián")
                }
            }
            LaneRole.SUPPORT -> {
                if (isAp && !isSupportEnchanter) {
                    listOf("Hoz Espectral", "Eco de Luden", "Orbe del Infinito", "Botas Jonias de la Lucidez", "Sombrero Mortal de Rabadon", "Morellonomicón")
                } else if (isTank) {
                    listOf("Baluarte de la Montaña", "Coraza del Muerto", "Manto del Amanecer", "Punteras Revestidas", "Convergencia de Zeke", "Fuerza de la Naturaleza")
                } else if (isSupportEnchanter) {
                    listOf("Hoz Espectral", "Incensario Ardiente", "Bastón de Aguas Fluidas", "Botas Jonias de la Lucidez", "Redención", "Promesa del Caballero")
                } else {
                    listOf("Hoz Espectral", "Cuchilla Negra", "Filoscuro de Draktharr", "Punteras Revestidas", "Colmillo de Serpiente", "Ángel Guardián")
                }
            }
            LaneRole.TOP -> {
                if (champ.id == "alistar" || (isTank && !champ.isRanged)) {
                    listOf("Guantelete de Hielo", "Coraza del Muerto", "Malla de Espinas", "Punteras Revestidas", "Fuerza de la Naturaleza", "Protección Gemela de Amaranth")
                } else if (isAp) {
                    listOf("Creagrietas", "Diente de Nashor", "Sombrero Mortal de Rabadon", "Botas Jonias de la Lucidez", "Báculo del Vacío", "Reloj de Arena de Zhonya")
                } else if (isMarksman) {
                    listOf("Hoja del Rey Arruinado", "Bailarín Espectral", "Filo del Infinito", "Grebas Berserker", "Recuerdos de Lord Dominik", "Ángel Guardián")
                } else {
                    listOf("Fuerza de la Trinidad", "Cuchilla Negra", "Danza de la Muerte", "Punteras Revestidas", "Calibrador de Sterak", "Rompecascos")
                }
            }
            LaneRole.MID -> {
                if (isAp) {
                    listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon", "Botas Jonias de la Lucidez", "Báculo del Vacío", "Reloj de Arena de Zhonya")
                } else if (isMarksman) {
                    listOf("Filo del Infinito", "Cañón de Fuego Rápido", "Recuerdos de Lord Dominik", "Grebas Berserker", "Sanguinaria", "Ángel Guardián")
                } else if (isTank) {
                    listOf("Corona de la Reina Ahogada", "Orbe del Infinito", "Sombrero Mortal de Rabadon", "Botas Jonias de la Lucidez", "Báculo del Vacío", "Reloj de Arena de Zhonya")
                } else {
                    listOf("Filoscuro de Draktharr", "Filo Fantasma de Youmuu", "Colmillo de Serpiente", "Grebas Berserker", "Rencor de Serylda", "Ángel Guardián")
                }
            }
            LaneRole.ADC -> {
                if (isAp) {
                    listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon", "Botas Jonias de la Lucidez", "Báculo del Vacío", "Reloj de Arena de Zhonya")
                } else {
                    listOf("Filo del Infinito", "Cañón de Fuego Rápido", "Recuerdos de Lord Dominik", "Grebas Berserker", "Sanguinaria", "Ángel Guardián")
                }
            }
        }

        val coreItemsIcons = coreItems.map { WildRiftItemsData.getItemIconByName(it) }

        // 3. Situational Items for flex role
        val situationalItems = when {
            isAp -> listOf("Reloj de Arena de Zhonya", "Báculo del Vacío", "Morellonomicón", "Velo de la Banshee", "Tridente de Oceánida")
            isTank -> listOf("Malla de Espinas", "Presagio de Randuin", "Protector Pétreo", "Protección Gemela de Amaranth", "Rookern Kaénico")
            isMarksman -> listOf("Recordatorio Mortal", "Fajín de Mercurio", "Ángel Guardián", "Filo de la Noche", "Cimitarra Mercurial")
            else -> listOf("Malla de Espinas", "Danza de la Muerte", "Colmillo de Serpiente", "Fuerza de la Naturaleza", "Espada Sierra Quimopunk")
        }
        val situationalItemsIcons = situationalItems.map { WildRiftItemsData.getItemIconByName(it) }

        // 4. Runes for flex role
        val (recommendedRunes, runeTreeDetails, primaryRuneName) = when (role) {
            LaneRole.JUNGLE -> {
                if (isAp) {
                    Triple("Electrocutar (Dominación)", "Dominación: Impacto Repentino • Marca del Verdugo • Colección de Ojos • Pionero", "Electrocutar")
                } else if (isTank) {
                    Triple("Réplica (Valor)", "Valor: Fuente de Vida • Acondicionamiento • Sobrecrecimiento • Pionero", "Réplica")
                } else {
                    Triple("Conquistador (Precisión)", "Precisión: Triunfo • Leyenda: Presteza • Cazador Titánico • Pionero", "Conquistador")
                }
            }
            LaneRole.SUPPORT -> {
                if (isTank || champ.id == "alistar") {
                    Triple("Réplica (Valor)", "Valor: Fuente de Vida • Revestimiento de Huesos • Sobrecrecimiento • Dulces Frutos", "Réplica")
                } else if (isSupportEnchanter) {
                    Triple("Invocar a Aery (Brujería)", "Brujería: Banda de Maná • Trascendencia • Tormenta Creciente • Dulces Frutos", "Invocar a Aery")
                } else {
                    Triple("Electrocutar (Dominación)", "Dominación: Impacto Repentino • Marca del Verdugo • Cazador Ingenioso • Dulces Frutos", "Electrocutar")
                }
            }
            LaneRole.TOP -> {
                if (champ.id == "alistar" || isTank) {
                    Triple("Agarre del Perpetuo (Valor)", "Valor: Demolición • Revestimiento de Huesos • Sobrecrecimiento • Dulces Frutos", "Agarre del Perpetuo")
                } else if (isAp) {
                    Triple("Conquistador (Precisión)", "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Revestimiento de Huesos", "Conquistador")
                } else {
                    Triple("Conquistador (Precisión)", "Precisión: Triunfo • Último Esfuerzo • Leyenda: Presteza • Revestimiento de Huesos", "Conquistador")
                }
            }
            LaneRole.MID -> {
                if (isAp) {
                    Triple("Primer Golpe (Inspiración)", "Dominación: Impacto Repentino • Marca del Verdugo • Cazador Ingenioso • Banda de Maná", "Primer Golpe")
                } else {
                    Triple("Electrocutar (Dominación)", "Dominación: Impacto Repentino • Marca del Verdugo • Colección de Ojos • Cazador Voraz", "Electrocutar")
                }
            }
            LaneRole.ADC -> {
                if (isAp) {
                    Triple("Primer Golpe (Inspiración)", "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Banda de Maná", "Primer Golpe")
                } else {
                    Triple("Cadencia Letal (Precisión)", "Precisión: Triunfo • Leyenda: Linaje • Golpe de Gracia • Revestimiento de Huesos", "Cadencia Letal")
                }
            }
        }

        val primaryRuneIconUrl = WildRiftSpellsAndRunes.getRuneIconByName(primaryRuneName)

        // 5. Dynamic Swaps for flex role
        val itemSwaps = generateDefaultSwaps(coreItems, situationalItems, champ.damageType, isTank)

        // 6. Matchups & synergies
        val flexAdvantage = when (role) {
            LaneRole.JUNGLE -> listOf("Maestro Yi", "Amumu", "Shyvana", "Evelynn")
            LaneRole.SUPPORT -> listOf("Leona", "Nautilus", "Blitzcrank", "Pyke")
            LaneRole.TOP -> listOf("Sion", "Nasus", "Malphite", "Garen")
            LaneRole.MID -> listOf("Kassadin", "Veigar", "Ziggs", "Twisted Fate")
            LaneRole.ADC -> listOf("Jinx", "Ashe", "Miss Fortune", "Sivir")
        }
        val flexCountered = when (role) {
            LaneRole.JUNGLE -> listOf("Lee Sin", "Kha'Zix", "Olaf", "Xin Zhao")
            LaneRole.SUPPORT -> listOf("Morgana", "Janna", "Lulu", "Karma")
            LaneRole.TOP -> listOf("Fiora", "Darius", "Gwen", "Vayne")
            LaneRole.MID -> listOf("Zed", "Ahri", "Syndra", "Akali")
            LaneRole.ADC -> listOf("Draven", "Lucian", "Samira", "Caitlyn")
        }
        val flexSynergies = when (role) {
            LaneRole.JUNGLE -> listOf("Yasuo", "Orianna", "Galio", "Malphite")
            LaneRole.SUPPORT -> listOf("Samira", "Kai'Sa", "Tristana", "Yasuo")
            LaneRole.TOP -> listOf("Jarvan IV", "Vi", "Orianna", "Amumu")
            LaneRole.MID -> listOf("Lee Sin", "Malphite", "Wukong", "Amumu")
            LaneRole.ADC -> listOf("Nautilus", "Thresh", "Lulu", "Leona")
        }

        val tacticalAdvice = when (role) {
            LaneRole.JUNGLE -> "Aprovecha la limpieza de campamentos con ${champ.skills.firstOrNull()?.name ?: "habilidades"} y busca ganks en líneas con control de masas aliado."
            LaneRole.SUPPORT -> "Protege a tu tirador en fase de líneas y aprovecha tu utilidad en peleas por Dragón y Heraldo."
            LaneRole.TOP -> "Controla la oleada cerca de tu torre y escala hacia el juego medio para actuar como pilar en peleas de equipo."
            LaneRole.MID -> "Gana prioridad con empuje de oleada y rota rápidamente para apoyar las invasiones de tu jungla en el río."
            LaneRole.ADC -> "Mantén una posición segura en retaguardia, farmea de forma constante y maximiza tu daño sostenido."
        }

        return ChampionRoleProfile(
            role = role,
            winrate = adjustRate(champ.winrate, -0.7),
            pickRate = adjustRate(champ.pickRate * 0.45, 0.5),
            banRate = champ.banRate,
            winrateDelta = champ.winrateDelta,
            pickRateDelta = champ.pickRateDelta,
            banRateDelta = champ.banRateDelta,
            tier = if (champ.tier == "S+") "S" else if (champ.tier == "S") "A" else champ.tier,
            coreItems = coreItems,
            coreItemsIcons = coreItemsIcons,
            situationalItems = situationalItems,
            situationalItemsIcons = situationalItemsIcons,
            itemSwaps = itemSwaps,
            recommendedRunes = recommendedRunes,
            runeTreeDetails = runeTreeDetails,
            primaryRuneIconUrl = primaryRuneIconUrl,
            recommendedSpells = recommendedSpells,
            spellsIcons = spellsIcons,
            advantageAgainst = flexAdvantage,
            counteredBy = flexCountered,
            synergies = flexSynergies,
            tacticalAdvice = tacticalAdvice
        )
    }

    private fun ensureSixItems(
        currentItems: List<String>,
        damageType: DamageType,
        isTank: Boolean,
        isRanged: Boolean,
        role: LaneRole
    ): List<String> {
        val result = currentItems.toMutableList()
        val defaultFillers = when {
            isTank || role == LaneRole.SUPPORT -> listOf(
                "Coraza del Muerto", "Malla de Espinas", "Fuerza de la Naturaleza",
                "Punteras Revestidas", "Manto del Amanecer", "Protector Pétreo", "Protección Gemela de Amaranth"
            )
            damageType == DamageType.MAGIC -> listOf(
                "Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon",
                "Botas Jonias de la Lucidez", "Báculo del Vacío", "Reloj de Arena de Zhonya", "Morellonomicón"
            )
            isRanged -> listOf(
                "Filo del Infinito", "Cañón de Fuego Rápido", "Recuerdos de Lord Dominik",
                "Grebas Berserker", "Sanguinaria", "Ángel Guardián", "Bailarín Espectral"
            )
            else -> listOf(
                "Fuerza de la Trinidad", "Cuchilla Negra", "Danza de la Muerte",
                "Punteras Revestidas", "Calibrador de Sterak", "Ángel Guardián", "Malla de Espinas"
            )
        }

        for (item in defaultFillers) {
            if (result.size >= 6) break
            if (!result.contains(item)) {
                result.add(item)
            }
        }
        return result.take(6)
    }

    private fun getDefaultSituationalItems(damageType: DamageType, isTank: Boolean): List<String> {
        return when {
            damageType == DamageType.MAGIC -> listOf("Reloj de Arena de Zhonya", "Morellonomicón", "Báculo del Vacío", "Velo de la Banshee")
            isTank -> listOf("Malla de Espinas", "Presagio de Randuin", "Protector Pétreo", "Protección Gemela de Amaranth")
            else -> listOf("Malla de Espinas", "Colmillo de Serpiente", "Danza de la Muerte", "Ángel Guardián")
        }
    }

    private fun generateDefaultSwaps(
        coreItems: List<String>,
        situationalItems: List<String>,
        damageType: DamageType,
        isTank: Boolean
    ): List<ItemSwap> {
        val swaps = mutableListOf<ItemSwap>()

        if (damageType == DamageType.MAGIC) {
            val coreTarget = coreItems.find { it.contains("Rabadon") || it.contains("Infinito") || it.contains("Luden") } ?: coreItems.firstOrNull() ?: "Eco de Luden"
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Morellonomicón",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Morellonomicón"),
                    reasonTitle = "ANTI-CURACIÓN (HERIDAS GRAVES)",
                    reasonDesc = "Reduce las curaciones y regeneraciones masivas de campeones enemigos.",
                    againstWho = "Soraka, Dr. Mundo, Aatrox, Warwick, Vladimir"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Reloj de Arena de Zhonya",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Reloj de Arena de Zhonya"),
                    reasonTitle = "SUPERVIVENCIA & INVULNERABILIDAD",
                    reasonDesc = "Otorga éxtasis temporal de 2.5s para esquivar combos letales de asesinos.",
                    againstWho = "Zed, Talon, Fizz, Kayn, Syndra"
                )
            )
        } else if (isTank) {
            val coreTarget = coreItems.find { it.contains("Fuerza") || it.contains("Amanecer") || it.contains("Muerto") } ?: coreItems.firstOrNull() ?: "Coraza del Muerto"
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Malla de Espinas",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Malla de Espinas"),
                    reasonTitle = "ANTI-CURACIÓN & ARMADURA",
                    reasonDesc = "Aplica Heridas Graves al recibir daño y devuelve daño mágico.",
                    againstWho = "Aatrox, Warwick, Soraka, Yuumi, Samira"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Presagio de Randuin",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Presagio de Randuin"),
                    reasonTitle = "ANTI-CRÍTICO",
                    reasonDesc = "Reduce el daño de golpes críticos y frena hipercarries de autoataques.",
                    againstWho = "Yasuo, Yone, Jinx, Tristana, Caitlyn"
                )
            )
        } else {
            val coreTarget = coreItems.find { it.contains("Danza") || it.contains("Cuchilla") || it.contains("Fuego") } ?: coreItems.firstOrNull() ?: "Cuchilla Negra"
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Colmillo de Serpiente",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Colmillo de Serpiente"),
                    reasonTitle = "DESTRUCTOR DE ESCUDOS",
                    reasonDesc = "Reduce drásticamente la absorción de escudos enemigos al impactar con daño físico.",
                    againstWho = "Sett, Shen, Karma, Lulu, Sterak"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Malla de Espinas",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Malla de Espinas"),
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
