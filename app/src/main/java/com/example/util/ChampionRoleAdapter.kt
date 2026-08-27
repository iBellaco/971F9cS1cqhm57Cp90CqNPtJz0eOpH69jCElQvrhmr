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
                    listOf("Diente de Nashor", "Luden's Echo", "Orbe infinito", "Botas jonias de la lucidez", "Gorro de muerte del miércoles", "Bastón vacío")
                } else if (isTank) {
                    listOf("Plato del hombre muerto", "malla de espinas", "Fuerza de la naturaleza", "Botas blindadas", "Corona abrasadora", "Placa de piedra de gárgola")
                } else if (isMarksman) {
                    listOf("Fuerza trinitaria", "El coleccionista", "Borde infinito", "Grebas de berserker", "Saludos de Dominik", "Ángel custodio")
                } else {
                    listOf("Fuerza trinitaria", "Black Cleaver", "La danza de la muerte", "Botas blindadas", "Sterak's Gage", "Ángel custodio")
                }
            }
            LaneRole.SUPPORT -> {
                if (isAp && !isSupportEnchanter) {
                    listOf("Hoz espectral", "Luden's Echo", "Orbe infinito", "Botas jonias de la lucidez", "Gorro de muerte del miércoles", "Morellonomicón")
                } else if (isTank) {
                    listOf("Baluarte de la montaña", "Plato del hombre muerto", "Sudario del alba", "Botas blindadas", "La convergencia de Zeke", "Fuerza de la naturaleza")
                } else if (isSupportEnchanter) {
                    listOf("Hoz espectral", "Incensario Ardiente", "Staff of Flowing Water", "Botas jonias de la lucidez", "Redención", "Voto de caballero")
                } else {
                    listOf("Hoz espectral", "Black Cleaver", "Hoja del Ocaso de Draktharr", "Botas blindadas", "Serpent's Fang", "Ángel custodio")
                }
            }
            LaneRole.TOP -> {
                if (champ.id == "alistar" || (isTank && !champ.isRanged)) {
                    listOf("Guantelete nacido del hielo", "Plato del hombre muerto", "malla de espinas", "Botas blindadas", "Fuerza de la naturaleza", "Guardia gemela de amaranto")
                } else if (isAp) {
                    listOf("Hacedor de grietas", "Diente de Nashor", "Gorro de muerte del miércoles", "Botas jonias de la lucidez", "Bastón vacío", "El reloj de arena de Zhonya")
                } else if (isMarksman) {
                    listOf("Espada del Rey Arruinado", "Bailarina fantasma", "Borde infinito", "Grebas de berserker", "Saludos de Dominik", "Ángel custodio")
                } else {
                    listOf("Fuerza trinitaria", "Black Cleaver", "La danza de la muerte", "Botas blindadas", "Sterak's Gage", "Rompecascos")
                }
            }
            LaneRole.MID -> {
                if (isAp) {
                    listOf("Luden's Echo", "Orbe infinito", "Gorro de muerte del miércoles", "Botas jonias de la lucidez", "Bastón vacío", "El reloj de arena de Zhonya")
                } else if (isMarksman) {
                    listOf("Borde infinito", "Blaster magnético", "Saludos de Dominik", "Grebas de berserker", "sanguinario", "Ángel custodio")
                } else if (isTank) {
                    listOf("Luden's Echo", "Orbe infinito", "Gorro de muerte del miércoles", "Botas jonias de la lucidez", "Bastón vacío", "El reloj de arena de Zhonya")
                } else {
                    listOf("Hoja del Ocaso de Draktharr", "El cuchillo fantasma de Youmuu", "Serpent's Fang", "Grebas de berserker", "El rencor de Serylda", "Ángel custodio")
                }
            }
            LaneRole.ADC -> {
                if (isAp) {
                    listOf("Luden's Echo", "Orbe infinito", "Gorro de muerte del miércoles", "Botas jonias de la lucidez", "Bastón vacío", "El reloj de arena de Zhonya")
                } else {
                    listOf("Borde infinito", "Blaster magnético", "Saludos de Dominik", "Grebas de berserker", "sanguinario", "Ángel custodio")
                }
            }
        }

        val coreItemsIcons = coreItems.map { WildRiftItemsData.getItemIconByName(it) }

        // 3. Situational Items for flex role
        val situationalItems = when {
            isAp -> listOf("El reloj de arena de Zhonya", "Bastón vacío", "Morellonomicón", "Velo de alma en pena", "Tridente de Oceanida")
            isTank -> listOf("malla de espinas", "El presagio de Randuin", "Placa de piedra de gárgola", "Guardia gemela de amaranto", "Kaenic Rookern")
            isMarksman -> listOf("Recordatorio mortal", "Fajín de mercurio", "Ángel custodio", "Edge of Night", "Cimitarra mercurial")
            else -> listOf("malla de espinas", "La danza de la muerte", "Serpent's Fang", "Fuerza de la naturaleza", "Chempunk Chainsword")
        }
        val situationalItemsIcons = situationalItems.map { WildRiftItemsData.getItemIconByName(it) }

        // 4. Runes for flex role
        val (recommendedRunes, runeTreeDetails, primaryRuneName) = when (role) {
            LaneRole.JUNGLE -> {
                if (isAp) {
                    Triple("Electrocutar", "Impacto Repentino • Golpe Bajo • Colección de Globos Oculares • Cazador Incesante", "Electrocutar")
                } else if (isTank) {
                    Triple("Reverberacción", "Fuente de Vida • Condicionamiento • Sobrecrecimiento • Inquebrantable", "Reverberacción")
                } else {
                    Triple("Conquistador", "Triunfo • Leyenda: Presteza • Último Esfuerzo • Cazador Incesante", "Conquistador")
                }
            }
            LaneRole.SUPPORT -> {
                if (isTank || champ.id == "alistar") {
                    Triple("Reverberacción", "Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Goloso", "Reverberacción")
                } else if (isSupportEnchanter) {
                    Triple("Aery", "Anillo de Flujo de Maná • Trascendencia • Se avecina tormenta • Goloso", "Aery")
                } else {
                    Triple("Electrocutar", "Impacto Repentino • Golpe Bajo • Cazador Ingenioso • Goloso", "Electrocutar")
                }
            }
            LaneRole.TOP -> {
                if (champ.id == "alistar" || isTank) {
                    Triple("Garras del Inmortal", "Demolición • Coraza Ósea • Sobrecrecimiento • Goloso", "Garras del Inmortal")
                } else if (isAp) {
                    Triple("Conquistador", "Triunfo • Golpe de Gracia • Leyenda: Presteza • Coraza Ósea", "Conquistador")
                } else {
                    Triple("Conquistador", "Triunfo • Último Esfuerzo • Leyenda: Presteza • Coraza Ósea", "Conquistador")
                }
            }
            LaneRole.MID -> {
                if (isAp) {
                    Triple("Primer Golpe", "Impacto Repentino • Golpe Bajo • Cazador Ingenioso • Anillo de Flujo de Maná", "Primer Golpe")
                } else {
                    Triple("Electrocutar", "Impacto Repentino • Golpe Bajo • Colección de Globos Oculares • Cazador Incesante", "Electrocutar")
                }
            }
            LaneRole.ADC -> {
                if (isAp) {
                    Triple("Primer Golpe", "Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Anillo de Flujo de Maná", "Primer Golpe")
                } else {
                    Triple("Compás Letal", "Triunfo • Leyenda: Linaje • Golpe de Gracia • Coraza Ósea", "Compás Letal")
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
                "Plato del hombre muerto", "malla de espinas", "Fuerza de la naturaleza",
                "Botas blindadas", "Sudario del alba", "Placa de piedra de gárgola", "Guardia gemela de amaranto"
            )
            damageType == DamageType.MAGIC -> listOf(
                "Luden's Echo", "Orbe infinito", "Gorro de muerte del miércoles",
                "Botas jonias de la lucidez", "Bastón vacío", "El reloj de arena de Zhonya", "Morellonomicón"
            )
            isRanged -> listOf(
                "Borde infinito", "Blaster magnético", "Saludos de Dominik",
                "Grebas de berserker", "sanguinario", "Ángel custodio", "Bailarina fantasma"
            )
            else -> listOf(
                "Fuerza trinitaria", "Black Cleaver", "La danza de la muerte",
                "Botas blindadas", "Sterak's Gage", "Ángel custodio", "malla de espinas"
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
            damageType == DamageType.MAGIC -> listOf("El reloj de arena de Zhonya", "Morellonomicón", "Bastón vacío", "Velo de alma en pena")
            isTank -> listOf("malla de espinas", "El presagio de Randuin", "Placa de piedra de gárgola", "Guardia gemela de amaranto")
            else -> listOf("malla de espinas", "Serpent's Fang", "La danza de la muerte", "Ángel custodio")
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
            val coreTarget = coreItems.find { it.contains("Rabadon") || it.contains("Infinito") || it.contains("Luden") } ?: coreItems.firstOrNull() ?: "Luden's Echo"
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
                    altItem = "El reloj de arena de Zhonya",
                    altItemIcon = WildRiftItemsData.getItemIconByName("El reloj de arena de Zhonya"),
                    reasonTitle = "SUPERVIVENCIA & INVULNERABILIDAD",
                    reasonDesc = "Otorga éxtasis temporal de 2.5s para esquivar combos letales de asesinos.",
                    againstWho = "Zed, Talon, Fizz, Kayn, Syndra"
                )
            )
        } else if (isTank) {
            val coreTarget = coreItems.find { it.contains("Fuerza") || it.contains("Amanecer") || it.contains("Muerto") } ?: coreItems.firstOrNull() ?: "Plato del hombre muerto"
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "malla de espinas",
                    altItemIcon = WildRiftItemsData.getItemIconByName("malla de espinas"),
                    reasonTitle = "ANTI-CURACIÓN & ARMADURA",
                    reasonDesc = "Aplica Heridas Graves al recibir daño y devuelve daño mágico.",
                    againstWho = "Aatrox, Warwick, Soraka, Yuumi, Samira"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "El presagio de Randuin",
                    altItemIcon = WildRiftItemsData.getItemIconByName("El presagio de Randuin"),
                    reasonTitle = "ANTI-CRÍTICO",
                    reasonDesc = "Reduce el daño de golpes críticos y frena hipercarries de autoataques.",
                    againstWho = "Yasuo, Yone, Jinx, Tristana, Caitlyn"
                )
            )
        } else {
            val coreTarget = coreItems.find { it.contains("Danza") || it.contains("Cuchilla") || it.contains("Fuego") } ?: coreItems.firstOrNull() ?: "Black Cleaver"
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
                    altItem = "Serpent's Fang",
                    altItemIcon = WildRiftItemsData.getItemIconByName("Serpent's Fang"),
                    reasonTitle = "DESTRUCTOR DE ESCUDOS",
                    reasonDesc = "Reduce drásticamente la absorción de escudos enemigos al impactar con daño físico.",
                    againstWho = "Sett, Shen, Karma, Lulu, Sterak"
                )
            )
            swaps.add(
                ItemSwap(
                    coreItem = coreTarget,
                    coreItemIcon = WildRiftItemsData.getItemIconByName(coreTarget),
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
