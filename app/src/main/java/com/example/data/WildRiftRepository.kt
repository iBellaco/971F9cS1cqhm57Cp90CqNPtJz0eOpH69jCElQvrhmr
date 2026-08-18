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
    const val CURRENT_PATCH_VERSION = "Patch 5.3 (Wild Rift)"
    const val LAST_SYNC_STATUS = "Sincronización Automática Activa"

    // CDN base URL para avatares, habilidades, objetos y hechizos
    private const val CDN_VERSION = "14.20.1"
    private const val DDRAGON_CHAMP_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/champion"
    private const val DDRAGON_SPELL_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/spell"
    private const val DDRAGON_ITEM_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/item"
    private const val DDRAGON_PASSIVE_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/passive"

    val metaSources: List<MetaDataSource> = listOf(
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
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/infernal.png",
            buffDescription = "Otorga a todo el equipo +3% de daño de ataque y +3% de poder de habilidad acumulable.",
            tactics = "Prioriza asegurar la línea de dragón empujando oleadas 30s antes de su aparición. Ideal para composiciones de daño explosivo."
        ),
        MapObjectiveItem(
            id = "mountain_dragon",
            name = "Dragón de Montaña (Tierra)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/mountain.png",
            buffDescription = "Otorga a todo el equipo +6% de armadura y resistencia mágica adicionales.",
            tactics = "Refuerza la línea frontal de los tanques, facilitando asedios prolongados bajo torre enemiga."
        ),
        MapObjectiveItem(
            id = "ocean_dragon",
            name = "Dragón de los Océanos (Agua)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/ocean.png",
            buffDescription = "Restaura un 2.5% de la vida faltante cada 5 segundos a todos los miembros del equipo.",
            tactics = "Otorga sustain inagotable en el mapa para desgastar al rival sin necesidad de volver a base."
        ),
        MapObjectiveItem(
            id = "ice_dragon",
            name = "Dragón de Hielo (Glacial)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/chemtech.png",
            buffDescription = "Otorga +7 de aceleración de habilidad a todo el equipo y crea zonas de escarcha.",
            tactics = "Permite rotar habilidades mucho más rápido en escaramuzas y peleas por el Barón."
        ),
        MapObjectiveItem(
            id = "elder_dragon",
            name = "Dragón Anciano (Elder Dragon)",
            spawnTime = "Minuto 12:00",
            respawnTime = "Reaparece cada 5:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/elder.png",
            buffDescription = "Ataques y habilidades queman a los rivales. Si la vida del rival cae por debajo del 15%, es ejecutado de inmediato.",
            tactics = "El buff más decisivo de Wild Rift en el juego tardío. Asegura visión perimetral con centinelas antes de iniciar."
        ),
        MapObjectiveItem(
            id = "rift_herald",
            name = "Heraldo de la Grieta (Rift Herald)",
            spawnTime = "Minuto 5:00",
            respawnTime = "Solo aparece 1 por partida",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/507.png",
            buffDescription = "Al recoger el Ojo del Heraldo, permite invocar al Heraldo para embestir y destruir placas de torretas enemigas.",
            tactics = "Úsalo en la línea de Barón o Mid para derribar la primera torreta y desbloquear rotaciones tempranas."
        ),
        MapObjectiveItem(
            id = "baron_nashor",
            name = "Barón Nashor",
            spawnTime = "Minuto 12:00",
            respawnTime = "Reaparece cada 5:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/508.png",
            buffDescription = "Otorga Mano del Barón: potencia el daño de los súbditos aliados cercanos y reduce el tiempo de Retirada a 4 segundos.",
            tactics = "Aprovecha el buff para asediar las tres líneas simultáneamente y forzar la caída de inhibidores."
        ),
        MapObjectiveItem(
            id = "scuttle_crab",
            name = "Cangrejo Escurridizo",
            spawnTime = "Minuto 1:25",
            respawnTime = "Reaparece cada 2:30",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/3379.png",
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
        return champions.filter { it.primaryRole == role || it.secondaryRoles.contains(role) }
    }

    fun analyzeDraft(
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>
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
        val truePct = 100 - (physPct + magicPct)

        val frontlineAllies = allies.count { it.isFrontline }
        val frontlineStatus = when {
            frontlineAllies >= 2 -> "Frontline Sólida (${frontlineAllies} Tanques/Luchadores)"
            frontlineAllies == 1 -> "Frontline Moderada (1 Tanque)"
            else -> "¡Cuidado! Falta Frontline / Iniciación en el equipo aliado"
        }

        var directMatchupWarning: String? = null
        var directCounterBestPick: String? = null

        val enemySett = enemies.find { it.id == "sett" }
        val enemyVi = enemies.find { it.id == "vi" }
        val enemyCaitlyn = enemies.find { it.id == "caitlyn" }

        if (enemySett != null && enemyVi != null) {
            directMatchupWarning = "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage o escudos antimagia."
            directCounterBestPick = "Morgana o Janna"
        } else if (enemyCaitlyn != null) {
            directMatchupWarning = "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus."
            directCounterBestPick = "Nautilus o Viego"
        }

        val availableChampions = champions.filter { champ ->
            !allies.any { it.id == champ.id } && !enemies.any { it.id == champ.id }
        }

        val candidates = availableChampions.filter { champ ->
            champ.primaryRole == myRole || champ.secondaryRoles.contains(myRole)
        }.ifEmpty { availableChampions }

        val recommendations = candidates.map { champ ->
            var score = champ.winrate

            val countersFound = champ.advantageAgainst.count { adv ->
                enemies.any { it.name.equals(adv, ignoreCase = true) || it.id.equals(adv, ignoreCase = true) }
            }
            score += (countersFound * 1.5)

            val synergiesFound = champ.synergies.count { syn ->
                allies.any { it.name.equals(syn, ignoreCase = true) || it.id.equals(syn, ignoreCase = true) }
            }
            score += (synergiesFound * 1.0)

            val badge = when {
                champ.tier == "S+" && countersFound >= 2 -> "★ ELECCIÓN ÓPTIMA (Counter & Meta)"
                champ.tier == "S+" -> "★ TIER S+ (Alta Prioridad)"
                countersFound >= 1 -> "✔ COUNTER DIRECTO (+${countersFound})"
                else -> "Recomendación Balanceada"
            }

            val reason = if (champ.tacticalAdvice.isNotBlank()) {
                champ.tacticalAdvice
            } else {
                "${champ.name}: Excelente selección en ${myRole.displayName}. Complementa la composición y ofrece ${champ.damageType.displayName}."
            }

            DraftRecommendation(
                champion = champ,
                estimatedWinrate = (score * 10).toInt() / 10.0,
                advantageBadge = badge,
                tacticalReason = reason,
                runes = champ.recommendedRunes
            )
        }.sortedByDescending { it.estimatedWinrate }

        return DraftAnalysisResult(
            physicalDamagePercent = physPct,
            magicDamagePercent = magicPct,
            trueDamagePercent = truePct,
            frontlineStatus = frontlineStatus,
            directMatchupWarning = directMatchupWarning,
            directCounterBestPick = directCounterBestPick,
            recommendations = recommendations
        )
    }
}
