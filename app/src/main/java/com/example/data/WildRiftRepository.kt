package com.example.data

import com.example.model.Champion
import com.example.model.ChampionSkill
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
import com.example.model.DraftRecommendation
import com.example.model.ItemCategory
import com.example.model.LaneRole
import com.example.model.MapObjectiveItem
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import com.example.model.MetaDataSource

object WildRiftRepository {

    // Versión canónica oficial de Wild Rift
    var CURRENT_PATCH_VERSION = "Parche 7.2c"
    var LAST_SYNC_STATUS = "Sincronización Automática Activa"

    // CDN base URL para avatares, habilidades, objetos y hechizos
    private const val CDN_VERSION = "16.16.1"
    private const val DDRAGON_CHAMP_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/champion"
    private const val DDRAGON_SPELL_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/spell"
    private const val DDRAGON_ITEM_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/item"
    private const val DDRAGON_PASSIVE_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/passive"


    // ==========================================


    // ==========================================
    // FUENTES DE DATOS Y META ACTUAL
    // ==========================================
    val metaSources: List<MetaDataSource> = listOf(
        MetaDataSource(
            id = "riot_games_oficial",
            name = "Wild Rift Oficial (Riot Games)",
            description = "Catálogo Oficial de Campeones y Habilidades",
            url = "https://wildrift.leagueoflegends.com/es-es/champions/",
            focusArea = "Datos Canónicos y Oficiales"
        ),
        MetaDataSource(
            id = "wildriftcore",
            name = "WildRiftCore (ES)",
            description = "Runas en Español, Parches y Novedades",
            url = "https://wildriftcore.com/es/",
            focusArea = "Runas y Novedades en Español"
        ),
        MetaDataSource(
            id = "bestbuildwr",
            name = "BestBuildWR",
            description = "Builds Óptimas e Ítems Situacionales",
            url = "https://bestbuildwr.com/",
            focusArea = "Armado de Objetos Profundo"
        ),
        MetaDataSource(
            id = "wildriftfire",
            name = "WildRiftFire",
            description = "Tier Lists Globales y Sinergias",
            url = "https://www.wildriftfire.com/",
            focusArea = "Tier List General (S+ a C)"
        ),
        MetaDataSource(
            id = "wr_meta",
            name = "WR-Meta",
            description = "Estadísticas en Tiempo Real y Counters",
            url = "https://wr-meta.com/",
            focusArea = "Winrates y Counters Dinámicos"
        )
    )

    // CATÁLOGO DE HECHIZOS DE INVOCADOR (SUMMONER SPELLS)
    // ==========================================
    var summonerSpells: List<SummonerSpellItem> = WildRiftSpellsAndRunes.summonerSpells

    // ==========================================
    // CATÁLOGO DE RUNAS DE WILD RIFT
    // ==========================================
    var runes: List<RuneItem> = WildRiftSpellsAndRunes.runes

    // ==========================================
    // CATÁLOGO DE OBJETOS DE WILD RIFT
    // ==========================================
    var items: List<WildRiftItem> = WildRiftItemsData.list

    // ==========================================
    // CATÁLOGO DE OBJETIVOS DE MAPA (MONSTRUOS ÉPICOS DE WILD RIFT)
    // ==========================================
    var mapObjectives: List<MapObjectiveItem> = listOf(
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
    var champions: List<Champion> = (
        com.example.data.champions.BaronLaneChampions.list +
        com.example.data.champions.JungleChampions.list +
        com.example.data.champions.MidLaneChampions.list +
        com.example.data.champions.DragonLaneChampions.list +
        com.example.data.champions.SupportChampions.list
    ).distinctBy { it.id }

    fun getChampionByName(name: String): Champion? {
        return champions.find { it.name.equals(name, ignoreCase = true) }
    }

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

    private fun t(lang: String, en: String, pt: String, es: String): String {
        return when (lang) {
            "en" -> en
            "pt" -> pt
            else -> es
        }
    }

    fun evaluateChampion(
        champ: Champion,
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>,
        lang: String = "es"
    ): DraftRecommendation {
        val otherAllies = allies.filter { it.id != champ.id }
        val allyPhysCount = otherAllies.count { it.damageType == DamageType.PHYSICAL }
        val allyMagicCount = otherAllies.count { it.damageType == DamageType.MAGIC }
        val isAllyFullAd = otherAllies.isNotEmpty() && allyPhysCount >= 3 && allyMagicCount == 0
        val isAllyFullAp = otherAllies.isNotEmpty() && allyMagicCount >= 3 && allyPhysCount == 0
        val frontlineAllies = otherAllies.count { it.isFrontline }
        var score = champ.winrate
        var badge = ""
        var reasonParts = mutableListOf<String>()
        var synergyText = ""
        var counterText = ""
        val directCounters = champ.advantageAgainst.filter { adv ->
            enemies.any { it.name.equals(adv, ignoreCase = true) || it.id.equals(adv, ignoreCase = true) }
        }
        val directWeaknesses = champ.counteredBy.filter { weak ->
            enemies.any { it.name.equals(weak, ignoreCase = true) || it.id.equals(weak, ignoreCase = true) }
        }
        val directSynergies = champ.synergies.filter { syn ->
            otherAllies.any { it.name.equals(syn, ignoreCase = true) || it.id.equals(syn, ignoreCase = true) }
        }
        score += (directCounters.size * 2.8)
        score -= (directWeaknesses.size * 2.2)
        score += (directSynergies.size * 2.2)
        if (isAllyFullAd && champ.damageType == DamageType.MAGIC) { score += 3.5 }
        else if (isAllyFullAp && champ.damageType == DamageType.PHYSICAL) { score += 3.5 }
        if (frontlineAllies == 0 && champ.isFrontline) { score += 2.8 }
        if (directCounters.isNotEmpty() && directWeaknesses.isEmpty()) {
            badge = "⚡ COUNTER FUERTE (+" + directCounters.size + ")"
            reasonParts.add("Tienes ventaja sobre " + directCounters.joinToString(", ") + ".")
        } else if (directWeaknesses.isNotEmpty()) {
            badge = "⚠️ PELIGRO MATCHUP (-" + directWeaknesses.size + ")"
            reasonParts.add("Cuidado: Sufres contra " + directWeaknesses.joinToString(", ") + ".")
        } else if (directSynergies.isNotEmpty()) {
            badge = "⚡ SINERGIA CON EQUIPO (+" + directSynergies.size + ")"
            reasonParts.add("Sinergia óptima con " + directSynergies.joinToString(", ") + ".")
        } else if (isAllyFullAd && champ.damageType == DamageType.MAGIC) {
            badge = "🔮 APERTURA MÁGICA"
            reasonParts.add("Aportas daño mágico necesario.")
        } else if (isAllyFullAp && champ.damageType == DamageType.PHYSICAL) {
            badge = "🗡️ APERTURA FÍSICA"
            reasonParts.add("Aportas daño físico necesario.")
        } else if (frontlineAllies == 0 && champ.isFrontline) {
            badge = "🛡️ SALVADOR FRONTLINE"
            reasonParts.add("Cubres la falta de tanques.")
        } else {
            badge = "⚖️ SELECCIÓN ESTÁNDAR"
            reasonParts.add("Opción neutral en este escenario.")
        }
        synergyText = if (directSynergies.isNotEmpty()) "Buena combinación con: " + directSynergies.joinToString(", ") else "Autosuficiente."
        counterText = if (directCounters.isNotEmpty()) "Anula a: " + directCounters.joinToString(", ") else if (directWeaknesses.isNotEmpty()) "Juega seguro contra: " + directWeaknesses.joinToString(", ") else "Enfrentamiento parejo."
        return DraftRecommendation(
            champion = champ,
            estimatedWinrate = ((score * 10).toInt() / 10.0).coerceAtMost(70.0),
            advantageBadge = badge,
            tacticalReason = champ.tacticalAdvice + " " + reasonParts.joinToString(" "),
            runes = champ.recommendedRunes,
            synergyDetails = synergyText,
            counterDetails = counterText
        )
    }

    fun analyzeDraft(
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>,
        isFirstPick: Boolean = false,
        lang: String = "es"
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
            directMatchupWarning = com.example.util.trStr(lang, "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad.")
            directCounterBestPick = "Morgana o Janna"
        } else if (enemyCaitlyn != null) {
            directMatchupWarning = com.example.util.trStr(lang, "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank.")
            directCounterBestPick = "Nautilus o Viego"
        } else if (enemyZed != null) {
            directMatchupWarning = com.example.util.trStr(lang, "Peligro de asesinos de burst") + " (${enemyZed.name}). " + com.example.util.trStr(lang, "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus).")
            directCounterBestPick = "Lulu, Nautilus o Zhonya"
        } else if (enemyTanks.size >= 2) {
            directMatchupWarning = com.example.util.trStr(lang, "Composición rival pesada") + " (${enemyTanks.joinToString { it.name }}). " + com.example.util.trStr(lang, "Requiere daño verdadero y % vida máxima.")
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

            val isFlex = champ.primaryRole != myRole
            val dynamicAdvice = com.example.util.CoachingGenerator.generateTacticalAdvice(champ, myRole, lang)
            val roleContextAdvice = if (isFlex) {
                t(lang,
                    "Flex in ${myRole.displayName}: Surprise factor advantage. Cons: May struggle against natural dominant picks in this lane. Play safe early. Tips: $dynamicAdvice",
                    "Flex no ${myRole.displayName}: Vantagem de fator surpresa. Desvantagem: Pode sofrer contra escolhas dominantes naturais desta rota. Jogue seguro no início. Dicas: $dynamicAdvice",
                    "Flex en ${myRole.displayName}: Ventaja de factor sorpresa. Desventaja: Puede sufrir contra picks dominantes naturales de la línea. Juega seguro al inicio. Consejos: $dynamicAdvice"
                )
            } else {
                dynamicAdvice
            }

            if (isFirstPickEffective) {
                // FIRST PICK / BLIND PICK CALCULATION
                val roleBlindList = safeBlindPicks[myRole] ?: emptyList()
                val isSafeBlind = roleBlindList.contains(champ.id)
                if (isSafeBlind) {
                    score += 4.5
                }

                val badge = when {
                    isSafeBlind && champ.tier == "S+" -> t(lang, "★ BEST 1ST PICK (Safe Blind Pick)", "★ MELHOR 1º PICK (Blind Pick Seguro)", "★ MEJOR PRIMER PICK (Blind Pick Seguro)")
                    isSafeBlind -> t(lang, "★ VERSATILE BLIND PICK", "★ BLIND PICK VERSÁTIL", "★ BLIND PICK VERSÁTIL")
                    champ.tier == "S+" -> t(lang, "★ TIER S+ META", "★ TIER S+ META", "★ TIER S+ META")
                    else -> t(lang, "General Pick in ${myRole.shortName}", "Opção Geral no ${myRole.shortName}", "Opción General en ${myRole.shortName}")
                }

                val reason = when {
                    isSafeBlind && champ.tier == "S+" ->
                        t(lang,
                            "#1 Priority First Pick in ${myRole.displayName}: ${champ.name} is an ultra safe S+ tier blind pick. Dominates laning phase, minimal counter vulnerability. " + roleContextAdvice,
                            "Prioridade #1 de Primeiro Pick no ${myRole.displayName}: ${champ.name} é um blind pick ultra seguro de Tier S+. Domina a fase de rotas, mínima vulnerabilidade a counters. " + roleContextAdvice,
                            "Prioridad #1 de Primer Pick en ${myRole.displayName}: ${champ.name} es un pick ciego ultra seguro de Tier S+. Domina la fase de líneas, mínima vulnerabilidad a counters. " + roleContextAdvice
                        )
                    isSafeBlind ->
                        t(lang,
                            "Excellent blind pick: ${champ.name} cannot be easily countered and guarantees stable presence. " + roleContextAdvice,
                            "Excelente blind pick: ${champ.name} não pode ser facilmente counterado e garante presença estável. " + roleContextAdvice,
                            "Excelente selección a ciegas: ${champ.name} no puede ser contrarrestado fácilmente y garantiza presencia estable. " + roleContextAdvice
                        )
                    else -> roleContextAdvice
                }

                synergyText = t(lang, "High self-sufficiency and wave control.", "Alta autossuficiência e controle de rotas.", "Alta autosuficiencia, control de oleadas y flexibilidad táctica.")
                counterText = t(lang, "Low vulnerability to ganks in ${myRole.shortName}.", "Baixa vulnerabilidade a emboscadas no ${myRole.shortName}.", "Baja vulnerabilidad a emboscadas y sin counters abusivos en ${myRole.shortName}.")

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
                    directCounters.isNotEmpty() && directSynergies.isNotEmpty() -> t(lang, "★ #1 BEST OPTION (Synergy + Counter)", "★ #1 MELHOR OPÇÃO (Sinergia + Counter)", "★ #1 MEJOR OPCIÓN (Sinergia + Counter)")
                    directCounters.isNotEmpty() && champ.tier == "S+" -> t(lang, "★ S+ TIER COUNTER (+${directCounters.size})", "★ COUNTER TIER S+ (+${directCounters.size})", "★ COUNTER TIER S+ (+${directCounters.size})")
                    directCounters.isNotEmpty() -> t(lang, "✔ DIRECT COUNTER (+${directCounters.size} Enemy)", "✔ COUNTER DIRETO (+${directCounters.size} Inimigo)", "✔ COUNTER DIRECTO (+${directCounters.size} Rival)")
                    directSynergies.isNotEmpty() -> t(lang, "⚡ TEAM SYNERGY (+${directSynergies.size})", "⚡ SINERGIA DE EQUIPE (+${directSynergies.size})", "⚡ SINERGIA CON EQUIPO (+${directSynergies.size})")
                    champ.tier == "S+" -> t(lang, "★ S+ TIER (High Priority)", "★ TIER S+ (Alta Prioridade)", "★ TIER S+ (Alta Prioridad)")
                    else -> t(lang, "Balanced Recommendation", "Recomendação Balanceada", "Recomendación Balanceada")
                }

                val reasonParts = mutableListOf<String>()
                if (directCounters.isNotEmpty()) {
                    reasonParts.add(t(lang, "Direct advantage against ${directCounters.joinToString(", ")}.", "Vantagem direta contra ${directCounters.joinToString(", ")}.", "Ventaja directa contra ${directCounters.joinToString(", ")}."))
                }
                if (directSynergies.isNotEmpty()) {
                    reasonParts.add(t(lang, "Optimal synergy with ${directSynergies.joinToString(", ")}.", "Sinergia ideal com ${directSynergies.joinToString(", ")}.", "Sinergia óptima con ${directSynergies.joinToString(", ")}."))
                }
                if (isAllyFullAd && champ.damageType == DamageType.MAGIC) {
                    reasonParts.add(t(lang, "Provides the crucial magic damage your team lacks.", "Fornece o dano mágico crucial que falta à sua equipe.", "Aporta el daño mágico crucial que le falta a tu equipo."))
                }
                if (frontlineAllies == 0 && champ.isFrontline) {
                    reasonParts.add(t(lang, "Covers your squad's lack of initiation and frontline.", "Cobre a falta de iniciação e linha de frente do seu esquadrão.", "Cubre la falta de iniciación y aguante de tu escuadrón."))
                }
                if (reasonParts.isEmpty()) {
                    reasonParts.add(roleContextAdvice)
                } else {
                    reasonParts.add(0, roleContextAdvice) // Put role context first
                }

                synergyText = if (directSynergies.isNotEmpty()) {
                    t(lang, "Combines with: ${directSynergies.joinToString(", ")}", "Combina com: ${directSynergies.joinToString(", ")}", "Combina con: ${directSynergies.joinToString(", ")}")
                } else {
                    t(lang, "Standard team composition", "Composição de equipe padrão", "Alineación estándar de equipo")
                }

                counterText = if (directCounters.isNotEmpty()) {
                    t(lang, "Strong against: ${directCounters.joinToString(", ")}", "Forte contra: ${directCounters.joinToString(", ")}", "Fuerte contra: ${directCounters.joinToString(", ")}")
                } else if (directWeaknesses.isNotEmpty()) {
                    t(lang, "Careful with: ${directWeaknesses.joinToString(", ")}", "Cuidado com: ${directWeaknesses.joinToString(", ")}", "Cuidado con: ${directWeaknesses.joinToString(", ")}")
                } else {
                    t(lang, "Neutral matchup", "Confronto neutro", "Enfrentamiento neutral")
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
