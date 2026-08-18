package com.example.data

import com.example.model.Champion
import com.example.model.ChampionSkill
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
import com.example.model.DraftRecommendation
import com.example.model.ItemCategory
import com.example.model.LaneRole
import com.example.model.MapObjectiveItem
import com.example.model.MetaDataSource
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem

object WildRiftRepository {

    // Versión canónica oficial de Wild Rift
    const val CURRENT_PATCH_VERSION = "Parche 5.3c (Wild Rift)"
    const val LAST_SYNC_STATUS = "Sincronización Automática Activa"

    // CDN base URL para avatares, habilidades, objetos y hechizos
    private const val CDN_VERSION = "14.20.1"
    private const val DDRAGON_CHAMP_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/champion"
    private const val DDRAGON_SPELL_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/spell"
    private const val DDRAGON_ITEM_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/item"
    private const val DDRAGON_PASSIVE_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/passive"

    val metaSources: List<MetaDataSource> = listOf(
        MetaDataSource(
            id = "riot_wildrift_official",
            name = "Wild Rift Oficial (Riot Games)",
            url = "https://wildrift.leagueoflegends.com/es-es/champions/",
            badge = "Sitio Oficial",
            description = "Portal oficial de League of Legends: Wild Rift en español con el catálogo completo de campeones, biografías, roles y habilidades oficiales.",
            focusArea = "Catálogo Oficial de Campeones y Habilidades"
        ),
        MetaDataSource(
            id = "wildriftcore",
            name = "WildRiftCore (ES)",
            url = "https://wildriftcore.com/es/",
            badge = "Español & Parches",
            description = "Portal líder en español con notas de parches, árboles completos de runas y análisis de cambios de balance de Wild Rift.",
            focusArea = "Runas en Español, Parches y Novedades"
        ),
        MetaDataSource(
            id = "bestbuildwr",
            name = "BestBuildWR",
            url = "https://bestbuildwr.com/",
            badge = "Pro Builds",
            description = "Optimización de builds de jugadores Grandmaster/Challenger en Wild Rift, rutas de ítems y órdenes de habilidades prioritarias.",
            focusArea = "Builds Óptimas e Ítems Situacionales"
        ),
        MetaDataSource(
            id = "wildriftfire",
            name = "WildRiftFire",
            url = "https://www.wildriftfire.com/",
            badge = "Tier List Global",
            description = "Referencia global de tier lists exclusivas de Wild Rift, guías maestras de campeones, sinergias de carril y runas meta.",
            focusArea = "Tier Lists Globales y Sinergias"
        ),
        MetaDataSource(
            id = "wrmeta",
            name = "WR-Meta",
            url = "https://wr-meta.com/",
            badge = "Estadísticas & Counters",
            description = "Analítica masiva con winrates, pickrates, banrates y enfrentamientos directos de los servidores globales de Wild Rift.",
            focusArea = "Estadísticas en Tiempo Real y Counters"
        )
    )

    // ==========================================
    // CATÁLOGO DE HECHIZOS DE INVOCADOR (SUMMONER SPELLS)
    // ==========================================
    val summonerSpells: List<SummonerSpellItem> = WildRiftSpellsAndRunes.summonerSpells

    // ==========================================
    // CATÁLOGO DE RUNAS DE WILD RIFT
    // ==========================================
    val runes: List<RuneItem> = WildRiftSpellsAndRunes.runes

    // ==========================================
    // CATÁLOGO DE OBJETOS DE WILD RIFT
    // ==========================================
    val items: List<WildRiftItem> = WildRiftItemsData.list

    // ==========================================
    // CATÁLOGO DE OBJETIVOS DE MAPA (MONSTRUOS ÉPICOS DE WILD RIFT)
    // ==========================================
    val mapObjectives: List<MapObjectiveItem> = listOf(
        MapObjectiveItem(
            id = "infernal_dragon",
            name = "Dragón Infernal (Fuego)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/1598.png",
            buffDescription = "Otorga a todo el equipo +3% de daño de ataque y +3% de poder de habilidad acumulable.",
            tactics = "Prioriza asegurar la línea de dragón empujando oleadas 30s antes de su aparición. Ideal para composiciones de daño explosivo."
        ),
        MapObjectiveItem(
            id = "mountain_dragon",
            name = "Dragón de Montaña (Tierra)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/1599.png",
            buffDescription = "Otorga a todo el equipo +6% de armadura y resistencia mágica adicionales.",
            tactics = "Refuerza la línea frontal de los tanques, facilitando asedios prolongados bajo torre enemiga."
        ),
        MapObjectiveItem(
            id = "ocean_dragon",
            name = "Dragón de los Océanos (Agua)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/1600.png",
            buffDescription = "Restaura un 2.5% de la vida faltante cada 5 segundos a todos los miembros del equipo.",
            tactics = "Otorga sustain inagotable en el mapa para desgastar al rival sin necesidad de volver a base."
        ),
        MapObjectiveItem(
            id = "ice_dragon",
            name = "Dragón de Hielo (Glacial)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/1601.png",
            buffDescription = "Otorga +7 de aceleración de habilidad a todo el equipo y crea zonas de escarcha.",
            tactics = "Permite rotar habilidades mucho más rápido en escaramuzas y peleas por el Barón."
        ),
        MapObjectiveItem(
            id = "elder_dragon",
            name = "Dragón Anciano (Elder Dragon)",
            spawnTime = "Minuto 12:00",
            respawnTime = "Reaparece cada 5:00",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/1602.png",
            buffDescription = "Ataques y habilidades queman a los rivales. Si la vida del rival cae por debajo del 15%, es ejecutado de inmediato.",
            tactics = "El buff más decisivo de Wild Rift en el juego tardío. Asegura visión perimetral con centinelas antes de iniciar."
        ),
        MapObjectiveItem(
            id = "rift_herald",
            name = "Heraldo de la Grieta (Rift Herald)",
            spawnTime = "Minuto 5:00",
            respawnTime = "Solo aparece 1 por partida",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/507.png",
            buffDescription = "Al recoger el Ojo del Heraldo, permite invocar al Heraldo para embestir y destruir placas de torretas enemigas.",
            tactics = "Úsalo en la línea de Barón o Mid para derribar la primera torreta y desbloquear rotaciones tempranas."
        ),
        MapObjectiveItem(
            id = "baron_nashor",
            name = "Barón Nashor",
            spawnTime = "Minuto 12:00",
            respawnTime = "Reaparece cada 5:00",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/658.png",
            buffDescription = "Otorga Mano del Barón: potencia el daño de los súbditos aliados cercanos y reduce el tiempo de Retirada a 4 segundos.",
            tactics = "Aprovecha el buff para asediar las tres líneas simultáneamente y forzar la caída de inhibidores."
        ),
        MapObjectiveItem(
            id = "scuttle_crab",
            name = "Cangrejo Escurridizo",
            spawnTime = "Minuto 1:25",
            respawnTime = "Reaparece cada 2:30",
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/14.24.1/img/profileicon/3379.png",
            buffDescription = "Genera un Santuario de Velocidad y visión inquebrantable en el río frente al Dragón o Barón.",
            tactics = "Aplica control de masas duro para romper su escudo de inmediato y acelerar la limpieza del río."
        ),
        MapObjectiveItem(
            id = "red_buff",
            name = "Ancestro Ígneo (Buff Rojo)",
            spawnTime = "Minuto 0:20",
            respawnTime = "Reaparece cada 2:30",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/538.png",
            buffDescription = "Otorga Escudo de Cenizas: ataques básicos queman causando daño verdadero periódico y ralentizan.",
            tactics = "Esencial para tiradores y junglas físicos para aumentar el potencial de persecución y hostigamiento."
        ),
        MapObjectiveItem(
            id = "blue_buff",
            name = "Coloso Celeste (Buff Azul)",
            spawnTime = "Minuto 0:20",
            respawnTime = "Reaparece cada 2:30",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/537.png",
            buffDescription = "Otorga Perspicacia Espiritual: regeneración masiva de maná/energía y aceleración de habilidad adicional.",
            tactics = "Cédelo a tu carrilero central mágico para asegurar empuje continuo de oleadas antes de los objetivos."
        )
    )

    // ==========================================
    // ROSTER INTEGRAL DE CAMPEONES DE WILD RIFT
    // ==========================================
    val champions: List<Champion> = (
        com.example.data.champions.BaronLaneChampions.list +
        com.example.data.champions.JungleChampions.list +
        com.example.data.champions.MidLaneChampions.list +
        com.example.data.champions.DragonLaneChampions.list +
        com.example.data.champions.SupportChampions.list
    ).distinctBy { it.id }

    fun getChampionById(id: String): Champion? {
        return champions.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getChampionsByRole(role: LaneRole): List<Champion> {
        return champions
            .filter { it.primaryRole == role || it.secondaryRoles.contains(role) }
            .sortedWith(
                compareByDescending<Champion> { it.primaryRole == role }
                    .thenByDescending { it.tier == "S+" }
                    .thenByDescending { it.tier == "S" }
                    .thenByDescending { it.winrate }
            )
    }

    fun analyzeDraft(
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>,
        isFirstPick: Boolean = false
    ): DraftAnalysisResult {
        var physCount = 0
        var magicCount = 0
        var trueCount = 0

        enemies.forEach { champ ->
            when (champ.damageType) {
                DamageType.PHYSICAL -> physCount++
                DamageType.MAGIC -> magicCount++
                DamageType.TRUE_HYBRID -> trueCount++
            }
        }

        val totalEnemies = (physCount + magicCount + trueCount).coerceAtLeast(1)
        val physPct = (physCount * 100) / totalEnemies
        val magicPct = (magicCount * 100) / totalEnemies
        val truePct = (100 - (physPct + magicPct)).coerceAtLeast(0)

        // Ally Damage Profile
        val allyPhysCount = allies.count { it.damageType == DamageType.PHYSICAL }
        val allyMagicCount = allies.count { it.damageType == DamageType.MAGIC }
        val isAllyFullAd = allies.isNotEmpty() && allyPhysCount >= 3 && allyMagicCount == 0
        val isAllyFullAp = allies.isNotEmpty() && allyMagicCount >= 3 && allyPhysCount == 0

        val frontlineAllies = allies.count { it.isFrontline }
        val frontlineStatus = when {
            frontlineAllies >= 2 -> "Frontline Sólida (${frontlineAllies} Tanques/Luchadores)"
            frontlineAllies == 1 -> "Frontline Moderada (1 Tanque)"
            else -> "¡Alerta! Falta Frontline e Iniciación aliada"
        }

        val isFirstPickEffective = isFirstPick || enemies.isEmpty()

        // Known safe blind-picks per role in Wild Rift Meta
        val safeBlindPicks = mapOf(
            LaneRole.TOP to listOf("sett", "aatrox", "darius", "renekton", "ornn", "camille", "gwen"),
            LaneRole.JUNGLE to listOf("vi", "lee_sin", "xin_zhao", "viego", "wukong", "kayn", "jarvan_iv"),
            LaneRole.MID to listOf("ahri", "orianna", "syndra", "yone", "karma", "vex", "jayce"),
            LaneRole.ADC to listOf("varus", "ezreal", "kaisa", "caitlyn", "xayah", "jinx", "lucian"),
            LaneRole.SUPPORT to listOf("thresh", "nautilus", "lulu", "nami", "karma", "morgana", "leona", "rakan")
        )

        var directMatchupWarning: String? = null
        var directCounterBestPick: String? = null

        val enemySett = enemies.find { it.id == "sett" }
        val enemyVi = enemies.find { it.id == "vi" }
        val enemyCaitlyn = enemies.find { it.id == "caitlyn" }
        val enemyZed = enemies.find { it.id == "zed" || it.id == "kayn" || it.id == "talon" || it.id == "khazix" }
        val enemyTanks = enemies.filter { it.isFrontline }

        if (enemySett != null && enemyVi != null) {
            directMatchupWarning = "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad."
            directCounterBestPick = "Morgana o Janna"
        } else if (enemyCaitlyn != null) {
            directMatchupWarning = "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank."
            directCounterBestPick = "Nautilus o Viego"
        } else if (enemyZed != null) {
            directMatchupWarning = "Peligro de asesinos de burst (${enemyZed.name}). Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus)."
            directCounterBestPick = "Lulu, Nautilus o Zhonya"
        } else if (enemyTanks.size >= 2) {
            directMatchupWarning = "Composición rival pesada (${enemyTanks.joinToString { it.name }}). Requiere daño verdadero y % vida máxima."
            directCounterBestPick = "Vayne, Sett, Gwen o Liandry"
        }

        val availableChampions = champions.filter { champ ->
            !allies.any { it.id == champ.id } && !enemies.any { it.id == champ.id }
        }

        val candidates = availableChampions.filter { champ ->
            champ.primaryRole == myRole || champ.secondaryRoles.contains(myRole)
        }.ifEmpty { availableChampions }

        val recommendations = candidates.map { champ ->
            var score = champ.winrate

            // Tier Bonus
            when (champ.tier) {
                "S+" -> score += 3.0
                "S" -> score += 2.0
                "A+" -> score += 1.0
                "A" -> score += 0.5
            }

            var synergyText = ""
            var counterText = ""

            if (isFirstPickEffective) {
                // FIRST PICK / BLIND PICK CALCULATION
                val roleBlindList = safeBlindPicks[myRole] ?: emptyList()
                val isSafeBlind = roleBlindList.contains(champ.id)
                if (isSafeBlind) {
                    score += 4.5
                }

                val badge = when {
                    isSafeBlind && champ.tier == "S+" -> "★ MEJOR PRIMER PICK (Blind Pick Seguro)"
                    isSafeBlind -> "★ BLIND PICK VERSÁTIL"
                    champ.tier == "S+" -> "★ TIER S+ META"
                    else -> "Opción General en ${myRole.shortName}"
                }

                val reason = when {
                    isSafeBlind && champ.tier == "S+" ->
                        "Prioridad #1 de Primer Pick en ${myRole.displayName}: ${champ.name} es un pick ciego ultra seguro de Tier S+. Domina la fase de líneas, tiene mínima vulnerabilidad a counters y se adapta a cualquier draft aliado o rival."
                    isSafeBlind ->
                        "Excelente selección a ciegas: ${champ.name} no puede ser contrarrestado fácilmente y garantiza presencia estable durante toda la partida."
                    else ->
                        champ.tacticalAdvice.ifBlank {
                            "${champ.name}: Opción sólida de daño ${champ.damageType.displayName} para ${myRole.displayName}."
                        }
                }

                synergyText = "Alta autosuficiencia, control de oleadas y flexibilidad táctica."
                counterText = "Baja vulnerabilidad a emboscadas y sin counters abusivos en ${myRole.shortName}."

                DraftRecommendation(
                    champion = champ,
                    estimatedWinrate = ((score * 10).toInt() / 10.0).coerceAtMost(68.5),
                    advantageBadge = badge,
                    tacticalReason = reason,
                    runes = champ.recommendedRunes,
                    synergyDetails = synergyText,
                    counterDetails = counterText
                )
            } else {
                // REACTIVE / COUNTER & SYNERGY DRAFT CALCULATION
                val directCounters = champ.advantageAgainst.filter { adv ->
                    enemies.any { it.name.equals(adv, ignoreCase = true) || it.id.equals(adv, ignoreCase = true) }
                }
                val directWeaknesses = champ.counteredBy.filter { weak ->
                    enemies.any { it.name.equals(weak, ignoreCase = true) || it.id.equals(weak, ignoreCase = true) }
                }
                val directSynergies = champ.synergies.filter { syn ->
                    allies.any { it.name.equals(syn, ignoreCase = true) || it.id.equals(syn, ignoreCase = true) }
                }

                // Enemy Counter Scoring
                score += (directCounters.size * 2.8)
                score -= (directWeaknesses.size * 2.2)

                // Ally Synergy Scoring
                score += (directSynergies.size * 2.2)

                // Damage Balance compensation
                if (isAllyFullAd && champ.damageType == DamageType.MAGIC) {
                    score += 3.5 // Prevents enemy from just building Armor
                } else if (isAllyFullAp && champ.damageType == DamageType.PHYSICAL) {
                    score += 3.5 // Prevents enemy from just building Magic Resist
                }

                // Frontline compensation
                if (frontlineAllies == 0 && champ.isFrontline) {
                    score += 2.8 // Needed frontline
                }

                // Anti-tank or anti-assassin bonus
                if (enemyTanks.size >= 2 && (champ.id == "vayne" || champ.id == "sett" || champ.id == "gwen" || champ.id == "fiora" || champ.id == "varus")) {
                    score += 3.2
                }

                val badge = when {
                    directCounters.isNotEmpty() && directSynergies.isNotEmpty() -> "★ #1 MEJOR OPCIÓN (Sinergia + Counter)"
                    directCounters.isNotEmpty() && champ.tier == "S+" -> "★ COUNTER TIER S+ (+${directCounters.size})"
                    directCounters.isNotEmpty() -> "✔ COUNTER DIRECTO (+${directCounters.size} Rival)"
                    directSynergies.isNotEmpty() -> "⚡ SINERGIA CON EQUIPO (+${directSynergies.size})"
                    champ.tier == "S+" -> "★ TIER S+ (Alta Prioridad)"
                    else -> "Recomendación Balanceada"
                }

                val reasonParts = mutableListOf<String>()
                if (directCounters.isNotEmpty()) {
                    reasonParts.add("Ventaja directa contra ${directCounters.joinToString(", ")}.")
                }
                if (directSynergies.isNotEmpty()) {
                    reasonParts.add("Sinergia óptima con ${directSynergies.joinToString(", ")}.")
                }
                if (isAllyFullAd && champ.damageType == DamageType.MAGIC) {
                    reasonParts.add("Aporta el daño mágico crucial que le falta a tu equipo.")
                }
                if (frontlineAllies == 0 && champ.isFrontline) {
                    reasonParts.add("Cubre la falta de iniciación y aguante de tu escuadrón.")
                }
                if (reasonParts.isEmpty()) {
                    reasonParts.add(champ.tacticalAdvice.ifBlank { "${champ.name}: Elección balanceada para ${myRole.displayName}." })
                }

                synergyText = if (directSynergies.isNotEmpty()) {
                    "Combina con: ${directSynergies.joinToString(", ")}"
                } else {
                    "Alineación estándar de equipo"
                }

                counterText = if (directCounters.isNotEmpty()) {
                    "Fuerte contra: ${directCounters.joinToString(", ")}"
                } else if (directWeaknesses.isNotEmpty()) {
                    "Cuidado con: ${directWeaknesses.joinToString(", ")}"
                } else {
                    "Enfrentamiento neutral"
                }

                DraftRecommendation(
                    champion = champ,
                    estimatedWinrate = ((score * 10).toInt() / 10.0).coerceAtMost(70.0),
                    advantageBadge = badge,
                    tacticalReason = reasonParts.joinToString(" "),
                    runes = champ.recommendedRunes,
                    synergyDetails = synergyText,
                    counterDetails = counterText
                )
            }
        }.sortedByDescending { it.estimatedWinrate }

        val bestOverall = recommendations.firstOrNull()

        return DraftAnalysisResult(
            physicalDamagePercent = physPct,
            magicDamagePercent = magicPct,
            trueDamagePercent = truePct,
            frontlineStatus = frontlineStatus,
            directMatchupWarning = directMatchupWarning,
            directCounterBestPick = directCounterBestPick,
            isFirstPickMode = isFirstPickEffective,
            bestOverallPick = bestOverall,
            recommendations = recommendations
        )
    }
}
