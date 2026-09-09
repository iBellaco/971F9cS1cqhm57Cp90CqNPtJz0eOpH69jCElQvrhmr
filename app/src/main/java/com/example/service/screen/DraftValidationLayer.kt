package com.example.service.screen

import com.example.model.Champion
import com.example.model.LaneRole
import java.text.Normalizer
import java.util.Locale

/**
 * Capa de Validación y Filtrado Inteligente para el Escáner de Selección de Campeones (Draft).
 * Elimina falsos positivos (como apodos de invocador estilo "XCS Lucianito"),
 * clasifica roles en múltiples idiomas (ES, EN, PT), y resuelve la asignación
 * óptima de roles garantizando consistencia (Zero-Guessing).
 */
object DraftValidationLayer {

    // Palabras de ruido de interfaz que deben descartarse inmediatamente
    private val NOISE_WORDS = setOf(
        "draft", "coach", "tiers", "tier", "champs", "campeon", "campeones",
        "aliado", "aliados", "rival", "rivales", "enemigo", "enemigos", "vs", "versus",
        "marca", "estelar", "eterna", "beta", "primera", "segunda", "seleccion", "selección",
        "auto-scan", "autoscan", "activo", "detener", "asistente", "ajustes",
        "bloquear", "elegir", "jugador", "player", "tarjeta", "aumento", "usó", "uso",
        "combatamos", "juntos", "excelente", "composicion", "composición", "oponentes",
        "eligiendo", "equipo", "buscando", "emparejamiento", "listo", "esperando",
        "fijar", "preseleccion", "preselección", "fase", "bloqueando", "maestria", "maestría",
        "nivel", "lvl", "lv", "puntos", "pts", "pnt", "rango", "insignia", "emblema",
        // Hechizos de Invocador (ES, EN, PT) para evitar que aparezcan como nombres o texto en el Hub
        "destello", "flash", "castigo", "smite", "golpe", "ignicion", "ignición", "ignite", "incendiar",
        "fantasmal", "ghost", "fantasma", "barrera", "barrier", "curacion", "curación", "heal", "curar", "cura",
        "extenuacion", "extenuación", "exhaust", "exaustao", "exaustão", "claridad", "clarity",
        "purificacion", "purificación", "cleanse", "purificar", "teleport", "teletransporte", "teleportacion",
        "teleportación", "nieve", "mark", "dash", "snowball", "golpear", "aplastamiento", "hechizo", "hechizos",
        "spells", "spell", "feitiço", "feitiços"
    )

    fun isNoiseText(text: String): Boolean {
        val norm = normalize(text).trim()
        if (norm.length < 2) return true
        // Descartar números puros o temporizadores de draft (ej: "18", "25", "30", "0:15", "100%")
        if (norm.matches(Regex("^[0-9\\s:.,%#-]+$"))) return true
        if (norm.startsWith("jugador ") || norm.startsWith("player ") || norm.startsWith("jogador ") ||
            norm.matches(Regex("^(jugador|player|jogador)\\s*\\d+$"))) return true
        if (NOISE_WORDS.contains(norm)) return true
        val words = norm.split(Regex("\\s+"))
        return words.size == 1 && NOISE_WORDS.contains(words[0])
    }

    // Prefijos o etiquetas comunes de clanes/equipos en nombres de invocador
    private val SUMMONER_PREFIX_REGEX = Regex("^(xcs|tag|fnc|t1|g2|wr|clan|team|pro|tv|ttv|yt|god)\\s+", RegexOption.IGNORE_CASE)

    // Sufijos diminutivos o de apodos que distinguen a un invocador de un campeón real (ej: Lucianito != Lucian)
    private val SUMMONER_DIMINUTIVES = listOf(
        "ito", "ita", "itos", "itas", "cito", "cita", "citos", "citas",
        "inho", "inha", "zinho", "zinha", "ano", "ana", "zera", "god",
        "king", "pro", "boy", "girl", "99", "69", "777", "123", "01"
    )

    /**
     * Determina si un texto corresponde a un nombre de invocador y NO a un campeón.
     * Ejemplo: "XCS Lucianito", "Gaby11anos", "martincho137", "CacauVegannah" -> true (Invocador)
     * Ejemplo: "LUCIAN", "URGOT", "CAITLYN", "THRESH" -> false (Campeón real)
     */
    fun isLikelySummonerName(rawText: String): Boolean {
        val trimmed = rawText.trim()
        if (trimmed.isBlank() || trimmed.length < 2) return false
        if (isNoiseText(trimmed)) return false
        if (parseRoleFromText(trimmed) != null) return false

        // Si contiene prefijo de clan conocido (ej: "XCS Faker", "T1 Gumayusi")
        if (SUMMONER_PREFIX_REGEX.containsMatchIn(trimmed)) {
            return true
        }

        // Si contiene números mezclados con letras (típico de invocadores como Gaby11anos, martincho137)
        if (Regex("[a-zA-Z]{2,}[0-9]+").containsMatchIn(trimmed)) {
            return true
        }

        // Si contiene sufijos de diminutivo claramente de apodo (ej: Lucianito != Lucian)
        val lower = trimmed.lowercase(Locale.ROOT)
        for (dim in SUMMONER_DIMINUTIVES) {
            if (lower.endsWith(dim) && lower.length >= 6) {
                return true
            }
        }

        return false
    }

    /**
     * Valida si una palabra específica es un nombre o alias válido de campeón,
     * descartando diminutivos o apodos de invocador (ej: "lucianito" NO es "lucian").
     */
    fun isValidChampionToken(token: String, championId: String): Boolean {
        val cleanToken = normalize(token).replace(Regex("[^a-z0-9]"), "")
        val cleanChamp = normalize(championId).replace(Regex("[^a-z0-9]"), "")

        // Coincidencia exacta
        if (cleanToken == cleanChamp) return true

        // Si el token termina con un sufijo de apodo, rechazar inmediatamente
        for (dim in SUMMONER_DIMINUTIVES) {
            if (cleanToken.endsWith(dim) && cleanToken.length > cleanChamp.length) {
                return false
            }
        }

        // Aliases oficiales compuestos reconocidos
        val validMultiPartAliases = when (cleanChamp) {
            "drmundo", "dr_mundo" -> setOf("mundo", "drmundo")
            "jarvaniv", "jarvan_iv" -> setOf("jarvan", "j4", "jarvaniv")
            "missfortune", "miss_fortune" -> setOf("mf", "missfortune")
            "twistedfate", "twisted_fate" -> setOf("tf", "twistedfate")
            "nunuandwillump", "nunu_and_willump" -> setOf("nunu", "willump")
            "aurelionsol", "aurelion_sol" -> setOf("asol", "aurelion")
            "leesin", "lee_sin" -> setOf("lee", "leesin")
            "masteryi", "master_yi" -> setOf("yi", "masteryi")
            "tahmkench", "tahm_kench" -> setOf("tahm", "kench")
            "xinzhao", "xin_zhao" -> setOf("xin", "xinzhao")
            else -> emptySet()
        }

        if (validMultiPartAliases.contains(cleanToken)) {
            return true
        }

        return false
    }

    /**
     * Normaliza un texto eliminando tildes y caracteres especiales.
     */
    fun normalize(text: String): String {
        val decomposed = Normalizer.normalize(text, Normalizer.Form.NFD)
        return decomposed.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .lowercase(Locale.ROOT)
            .trim()
    }

    /**
     * Analiza una línea o bloque de texto y detecta si contiene una indicación explícita de carril (ES/EN/PT).
     */
    fun parseRoleFromText(rawText: String): LaneRole? {
        val lower = normalize(rawText)
        if (lower.isBlank()) return null

        // Ignorar identificadores de jugador genéricos como "Jugador 1", "Player 2", etc.
        if (lower.startsWith("jugador") || lower.startsWith("player") || lower.startsWith("jogador")) {
            return null
        }

        // 1. Frases completas de carril (Español, Inglés, Portugués)
        // TOP
        if (lower.contains("calle del baron") || lower.contains("calle del barón") ||
            lower.contains("calle de baron") || lower.contains("calle de barón") ||
            lower.contains("calle baron") || lower.contains("calle barón") ||
            lower.contains("carril del baron") || lower.contains("carril del barón") ||
            lower.contains("carril de baron") || lower.contains("carril de barón") ||
            lower.contains("carril baron") || lower.contains("carril barón") ||
            lower.contains("carril superior") || lower.contains("calle superior") ||
            lower.contains("baron lane") || lower.contains("rota de barao") ||
            lower.contains("rota de barão") || lower.contains("solo lane")) {
            return LaneRole.TOP
        }

        // MID
        if (lower.contains("calle central") || lower.contains("carril central") ||
            lower.contains("calle medio") || lower.contains("carril medio") ||
            lower.contains("calle de enmedio") || lower.contains("calle de en medio") ||
            lower.contains("mid lane") || lower.contains("rota do meio") ||
            lower.contains("middle lane")) {
            return LaneRole.MID
        }

        // ADC / DÚO
        if (lower.contains("calle del dragon") || lower.contains("calle del dragón") ||
            lower.contains("calle de dragon") || lower.contains("calle de dragón") ||
            lower.contains("calle dragon") || lower.contains("calle dragón") ||
            lower.contains("carril del dragon") || lower.contains("carril del dragón") ||
            lower.contains("carril de dragon") || lower.contains("carril de dragón") ||
            lower.contains("carril dragon") || lower.contains("carril dragón") ||
            lower.contains("calle duo") || lower.contains("calle dúo") ||
            lower.contains("carril duo") || lower.contains("carril dúo") ||
            lower.contains("duo lane") || lower.contains("dragon lane") ||
            lower.contains("rota do dragao") || lower.contains("rota do dragão") ||
            lower.contains("carril bot") || lower.contains("calle bot") ||
            lower.contains("bot lane")) {
            return LaneRole.ADC
        }

        // SUPPORT / APOYO
        if (lower.contains("apoyo") || lower.contains("soporte") ||
            lower.contains("suporte") || lower.contains("support")) {
            return LaneRole.SUPPORT
        }

        // JUNGLA
        if (lower.contains("jungla") || lower.contains("jungle") ||
            lower.contains("cacador") || lower.contains("caçador") ||
            lower.contains("selva")) {
            return LaneRole.JUNGLE
        }

        // 2. Tokens individuales delimitados exactamente
        val tokens = lower.split(Regex("[\\s,.:;\\-_/()]+")).filter { it.isNotBlank() }
        for (token in tokens) {
            when (token) {
                "baron", "barao", "barão", "top", "solo" -> return LaneRole.TOP
                "jungla", "jungle", "cacador", "caçador", "selva", "jg" -> return LaneRole.JUNGLE
                "mid", "medio", "meio", "central" -> return LaneRole.MID
                "adc", "duo", "dúo", "dragon", "dragón", "dragao", "dragão", "tirador", "atirador", "bot" -> return LaneRole.ADC
                "soporte", "support", "suporte", "sup", "supp", "apoyo" -> return LaneRole.SUPPORT
            }
        }

        return null
    }

    data class ResolvedTeam(
        val assignments: Map<LaneRole, Champion>,
        val confidences: Map<LaneRole, Int>
    )

    /**
     * Valida y filtra el resultado de un escaneo de equipo para evitar duplicaciones,
     * roles colisionados y campeones inválidos, calculando probabilidades dinámicas de rol.
     */
    fun resolveTeamRolesDetailed(
        scannedSlots: List<ScannedSlotInfo>,
        allChampions: List<Champion>,
        auditList: MutableList<String>,
        isAllyTeam: Boolean = true
    ): ResolvedTeam {
        val standardRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val finalMap = mutableMapOf<LaneRole, Champion>()
        val confidences = mutableMapOf<LaneRole, Int>()
        val availableRoles = standardRoles.toMutableList()
        val assignedChampionIds = mutableSetOf<String>()

        val validSlots = scannedSlots.filter { it.champion != null }
        val primaryRoleCounts = validSlots.groupBy { it.champion!!.primaryRole }.mapValues { it.value.size }
        val explicitlyAssignedRoles = mutableSetOf<LaneRole>()

        // 0. RESERVA DE ROL JUNGLA POR HECHIZO CASTIGO (SMITE):
        // Si un slot aliado tiene Castigo (Smite), dicho slot es indiscutiblemente el Jungla del equipo.
        val allySlotWithSmite = if (isAllyTeam) {
            scannedSlots.find { s ->
                s.summonerSpells.any { it.equals("Castigo", ignoreCase = true) || it.equals("Smite", ignoreCase = true) }
            }
        } else null

        if (allySlotWithSmite != null && allySlotWithSmite.champion == null && availableRoles.contains(LaneRole.JUNGLE)) {
            // Reservar el rol de jungla para este slot, impidiendo que otro campeón lo tome
            allySlotWithSmite.explicitRole = LaneRole.JUNGLE
            allySlotWithSmite.assignedRole = LaneRole.JUNGLE
            availableRoles.remove(LaneRole.JUNGLE)
            explicitlyAssignedRoles.add(LaneRole.JUNGLE)
        }

        // 1. ASIGNACIÓN POR ROL EXPLÍCITO DETECTADO EN EL SLOT (Certeza 100%)
        for (slot in validSlots) {
            val champ = slot.champion ?: continue
            val expRole = slot.explicitRole

            if (expRole != null && availableRoles.contains(expRole) && !assignedChampionIds.contains(champ.id)) {
                finalMap[expRole] = champ
                confidences[expRole] = 100
                availableRoles.remove(expRole)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = expRole
                explicitlyAssignedRoles.add(expRole)
                if (champ.primaryRole != expRole) {
                    auditList.add("Rol explícito: ${champ.name} -> ${expRole.shortName} (100% certeza)")
                }
            }
        }

        // 2. ASIGNACIÓN POR HECHIZO CASTIGO (SMITE) -> ROL JUNGLA INEQUÍVOCO (Certeza 100%)
        if (isAllyTeam && availableRoles.contains(LaneRole.JUNGLE)) {
            for (slot in validSlots) {
                val champ = slot.champion ?: continue
                if (assignedChampionIds.contains(champ.id)) continue

                val hasSmite = slot.summonerSpells.any {
                    it.equals("Castigo", ignoreCase = true) || it.equals("Smite", ignoreCase = true)
                }
                if (hasSmite) {
                    finalMap[LaneRole.JUNGLE] = champ
                    confidences[LaneRole.JUNGLE] = 100
                    availableRoles.remove(LaneRole.JUNGLE)
                    assignedChampionIds.add(champ.id)
                    slot.assignedRole = LaneRole.JUNGLE
                    explicitlyAssignedRoles.add(LaneRole.JUNGLE)
                    auditList.add("Hechizo Castigo detectado: ${champ.name} -> JUNGLA (100% certeza)")
                    break
                }
            }
        }

        // 3. ASIGNACIÓN POR ROL PRIMARIO DEL CAMPEÓN
        for (slot in validSlots) {
            val champ = slot.champion ?: continue
            if (assignedChampionIds.contains(champ.id)) continue

            val primary = champ.primaryRole
            if (availableRoles.contains(primary)) {
                finalMap[primary] = champ
                val sharedPrimary = (primaryRoleCounts[primary] ?: 1) > 1
                confidences[primary] = if (sharedPrimary) 85 else 95

                availableRoles.remove(primary)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = primary
            }
        }

        // 4. ASIGNACIÓN POR ROLES SECUNDARIOS (FLEX PICKS: Certeza 80%)
        for (slot in validSlots) {
            val champ = slot.champion ?: continue
            if (assignedChampionIds.contains(champ.id)) continue

            val secMatch = champ.secondaryRoles.firstOrNull { availableRoles.contains(it) }
            if (secMatch != null) {
                finalMap[secMatch] = champ
                confidences[secMatch] = 80
                availableRoles.remove(secMatch)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = secMatch
                auditList.add("Flex pick: ${champ.name} adaptado a ${secMatch.shortName} (80% certeza)")
            }
        }

        // 5. ASIGNACIÓN DE ROLES RESTANTES POR MEJOR AFINIDAD
        for (slot in validSlots) {
            val champ = slot.champion ?: continue
            if (assignedChampionIds.contains(champ.id)) continue

            // Si el rol primario o alguno secundario ya fue tomado pero hay uno compatible disponible
            val bestRole = availableRoles.firstOrNull { r -> champ.primaryRole == r || champ.secondaryRoles.contains(r) }
                ?: availableRoles.firstOrNull()

            if (bestRole != null) {
                finalMap[bestRole] = champ
                confidences[bestRole] = 65
                availableRoles.remove(bestRole)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = bestRole
            }
        }

        // 6. OPTIMIZACIÓN POST-ASIGNACIÓN (Maximizar afinidad sin tocar roles explícitos ni Castigo)
        var changed = true
        while (changed) {
            changed = false
            val assignedRoles = finalMap.keys.toList()
            for (i in 0 until assignedRoles.size) {
                for (j in i + 1 until assignedRoles.size) {
                    val roleA = assignedRoles[i]
                    val roleB = assignedRoles[j]

                    // No intercambiar roles que fueron detectados explícitamente por texto o Castigo
                    if (explicitlyAssignedRoles.contains(roleA) || explicitlyAssignedRoles.contains(roleB)) {
                        continue
                    }

                    val champA = finalMap[roleA]!!
                    val champB = finalMap[roleB]!!
                    
                    val slotA = validSlots.find { it.champion?.id == champA.id }
                    val slotB = validSlots.find { it.champion?.id == champB.id }
                    fun getSlotRole(idx: Int) = when (idx) {
                        0 -> LaneRole.TOP
                        1 -> LaneRole.JUNGLE
                        2 -> LaneRole.MID
                        3 -> LaneRole.ADC
                        4 -> LaneRole.SUPPORT
                        else -> null
                    }
                    val slotBonusA = if (slotA != null && getSlotRole(slotA.slotIndex) == roleA) 1 else 0
                    val slotBonusB = if (slotB != null && getSlotRole(slotB.slotIndex) == roleB) 1 else 0

                    val scoreA = (if (champA.primaryRole == roleA) 3 else if (champA.secondaryRoles.contains(roleA)) 1 else 0) + slotBonusA
                    val scoreB = (if (champB.primaryRole == roleB) 3 else if (champB.secondaryRoles.contains(roleB)) 1 else 0) + slotBonusB
                    val currentTotal = scoreA + scoreB
                    
                    val swappedSlotBonusA = if (slotA != null && getSlotRole(slotA.slotIndex) == roleB) 1 else 0
                    val swappedSlotBonusB = if (slotB != null && getSlotRole(slotB.slotIndex) == roleA) 1 else 0

                    val swappedScoreA = (if (champA.primaryRole == roleB) 3 else if (champA.secondaryRoles.contains(roleB)) 1 else 0) + swappedSlotBonusA
                    val swappedScoreB = (if (champB.primaryRole == roleA) 3 else if (champB.secondaryRoles.contains(roleA)) 1 else 0) + swappedSlotBonusB
                    val swappedTotal = swappedScoreA + swappedScoreB
                    
                    if (swappedTotal > currentTotal) {
                        finalMap[roleA] = champB
                        finalMap[roleB] = champA
                        confidences[roleA] = if (swappedScoreB >= 3) 95 else 80
                        confidences[roleB] = if (swappedScoreA >= 3) 95 else 80
                        slotA?.assignedRole = roleB
                        slotB?.assignedRole = roleA
                        changed = true
                        auditList.add("Heurística: Intercambio de $roleA (${champB.name}) y $roleB (${champA.name}) para optimizar afinidad y posición de slot.")
                    }
                }
            }
        }

        return ResolvedTeam(finalMap, confidences)
    }

    /**
     * Compatibilidad estándar que devuelve únicamente el mapa de roles asignados.
     */
    fun resolveTeamRoles(
        scannedSlots: List<ScannedSlotInfo>,
        allChampions: List<Champion>,
        auditList: MutableList<String>
    ): Map<LaneRole, Champion> {
        return resolveTeamRolesDetailed(scannedSlots, allChampions, auditList).assignments
    }
}
