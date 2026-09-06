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
        "combatamos", "juntos", "excelente", "composicion", "composición"
    )

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
        if (trimmed.isBlank()) return false

        // Si contiene prefijo de clan conocido
        if (SUMMONER_PREFIX_REGEX.containsMatchIn(trimmed)) {
            return true
        }

        // Si contiene números en medio o al final (típico de invocadores como Gaby11anos, martincho137)
        if (Regex("[a-zA-Z]+[0-9]+").containsMatchIn(trimmed)) {
            return true
        }

        // Si es CamelCase mixto (ej: CacauVegannah, RedPoint, Lucianito) y no es todo mayúsculas
        val isMixedCase = trimmed.any { it.isUpperCase() } && trimmed.any { it.isLowerCase() }
        val hasSpaces = trimmed.contains(" ")

        // Excluir nombres canónicos de campeones con espacios
        val lower = trimmed.lowercase(Locale.ROOT)
        if (lower.startsWith("dr") || lower.startsWith("jarvan") || lower.startsWith("twisted") ||
            lower.startsWith("xin") || lower.startsWith("aurelion") || lower.startsWith("lee") ||
            lower.startsWith("miss") || lower.startsWith("master") || lower.startsWith("tahm") ||
            lower.startsWith("nunu")) {
            return false
        }

        if (isMixedCase && hasSpaces) {
            return true
        }

        return false
    }

    /**
     * Valida si una palabra específica es un nombre de campeón estricto,
     * descartando variaciones o diminutivos de invocadores (ej: "lucianito" NO es "lucian").
     */
    fun isValidChampionToken(token: String, championId: String): Boolean {
        val cleanToken = normalize(token)
        val cleanChamp = normalize(championId).replace("_", "")

        // Coincidencia exacta estricta
        if (cleanToken == cleanChamp) return true

        // Si el token termina con un sufijo de apodo, rechazar inmediatamente
        for (dim in SUMMONER_DIMINUTIVES) {
            if (cleanToken.endsWith(dim) && cleanToken.length > cleanChamp.length) {
                return false
            }
        }

        return cleanToken == cleanChamp
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
        if (lower.contains("calle de baron") || lower.contains("calle de baron") ||
            lower.contains("carril de baron") || lower.contains("carril de barón") ||
            lower.contains("carril superior") || lower.contains("calle superior") ||
            lower.contains("baron lane") || lower.contains("rota de barao") ||
            lower.contains("rota de barão") || lower.contains("solo lane")) {
            return LaneRole.TOP
        }

        // MID
        if (lower.contains("calle central") || lower.contains("carril central") ||
            lower.contains("calle medio") || lower.contains("carril medio") ||
            lower.contains("mid lane") || lower.contains("rota do meio") ||
            lower.contains("middle lane")) {
            return LaneRole.MID
        }

        // ADC / DÚO
        if (lower.contains("calle del dragon") || lower.contains("calle del dragón") ||
            lower.contains("carril del dragon") || lower.contains("carril del dragón") ||
            lower.contains("calle duo") || lower.contains("calle dúo") ||
            lower.contains("carril duo") || lower.contains("carril dúo") ||
            lower.contains("duo lane") || lower.contains("dragon lane") ||
            lower.contains("rota do dragao") || lower.contains("rota do dragão") ||
            lower.contains("carril bot") || lower.contains("calle bot") ||
            lower.contains("bot lane")) {
            return LaneRole.ADC
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
        auditList: MutableList<String>
    ): ResolvedTeam {
        val standardRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val finalMap = mutableMapOf<LaneRole, Champion>()
        val confidences = mutableMapOf<LaneRole, Int>()
        val availableRoles = standardRoles.toMutableList()
        val assignedChampionIds = mutableSetOf<String>()

        val validSlots = scannedSlots.filter { it.champion != null }
        val primaryRoleCounts = validSlots.groupBy { it.champion!!.primaryRole }.mapValues { it.value.size }

        // 1. ASIGNACIÓN POR ROL EXPLÍCITO DETECTADO EN EL SLOT (Certeza 100%)
        for (slot in scannedSlots) {
            val champ = slot.champion ?: continue
            val expRole = slot.explicitRole

            if (expRole != null && availableRoles.contains(expRole) && !assignedChampionIds.contains(champ.id)) {
                finalMap[expRole] = champ
                confidences[expRole] = 100
                availableRoles.remove(expRole)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = expRole
                if (champ.primaryRole != expRole) {
                    auditList.add("Rol explícito: ${champ.name} -> ${expRole.shortName} (100% certeza)")
                }
            }
        }

        // 2. ASIGNACIÓN POR ROL PRIMARIO DEL CAMPEÓN
        for (slot in scannedSlots) {
            val champ = slot.champion ?: continue
            if (assignedChampionIds.contains(champ.id)) continue

            val primary = champ.primaryRole
            if (availableRoles.contains(primary)) {
                finalMap[primary] = champ
                // Si otro campeón del equipo también tiene este rol primario (ej. Riven y Sett en TOP),
                // la probabilidad baja a 85% para reflejar la ambigüedad flex.
                val sharedPrimary = (primaryRoleCounts[primary] ?: 1) > 1
                confidences[primary] = if (sharedPrimary) 85 else 100

                availableRoles.remove(primary)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = primary
            }
        }

        // 3. ASIGNACIÓN POR ROLES SECUNDARIOS (FLEX PICKS: Certeza 80%)
        for (slot in scannedSlots) {
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

        // 4. ASIGNACIÓN DE ROLES RESTANTES PARA CAMPEONES SIN ROL ASIGNADO (Fallback: Certeza 65%)
        for (slot in scannedSlots) {
            val champ = slot.champion ?: continue
            if (assignedChampionIds.contains(champ.id)) continue

            val fallback = availableRoles.firstOrNull() ?: continue
            finalMap[fallback] = champ
            confidences[fallback] = 65
            availableRoles.remove(fallback)
            assignedChampionIds.add(champ.id)
            slot.assignedRole = fallback
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
