package com.example.service.screen

import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import java.text.Normalizer
import java.util.Locale

object ChampionNameResolver {

    // Palabras reservadas de la UI de Wild Rift que nunca deben confundirse con campeones
    private val UI_IGNORE_WORDS = setOf(
        "jugador", "player", "jogador", "draft", "coach", "tier", "tiers", "champs", "campeon", "campeones",
        "campeao", "campeões", "champion", "champions",
        "equipo", "aliado", "aliados", "enemigo", "enemigos", "rival", "rivales", "victoria", "derrota", "carril", "baron", "barón", "central", "medio",
        "jungla", "duo", "dúo", "soporte", "apoyo", "tirador", "marca", "estelar", "eterna", "maestria", "maestría",
        "nivel", "combatanos", "combatamos", "juntos", "bloquear", "elegir", "seleccion", "selección", "eleccion", "elección", "fase",
        "preseleccion", "preselección", "pre-seleccion", "eligiendo", "esperando", "seleccionando", "escolhendo", "aguardando",
        "selecting", "choosing", "waiting", "bloqueando", "banning", "swap", "cambiar", "trocar", "orden", "turno",
        "ban", "bans", "auto", "scan", "activo", "asistente", "detener", "ajustes", "ajuste", "versus", "vs",
        "tarjeta", "aumento", "usó", "uso", "excelente", "composicion", "composición", "oponentes", "buscando",
        "primera", "segunda", "escolha", "selecao", "seleção", "pick", "picks",
        "destello", "flash", "castigo", "smite", "ignicion", "ignición", "ignite", "prender", "incendiar",
        "curacion", "curación", "cura", "heal", "barrera", "barrier", "extenuacion", "extenuación", "exaustao",
        "exaustão", "exhaust", "fantasmal", "fantasma", "ghost", "claridad", "teletransporte", "teleport",
        "hechizo", "hechizos", "feitiço", "feitiços", "spells", "spell", "tu", "tú", "you", "voce", "você", "yo", "eu"
    )

    // Mapa exhaustivo de nombres de Wild Rift a sus IDs canónicos
    private val KNOWN_CHAMPIONS_MAP = mapOf(
        "aatrox" to "aatrox",
        "ahri" to "ahri",
        "akali" to "akali",
        "akshan" to "akshan",
        "alistar" to "alistar",
        "ambessa" to "ambessa",
        "amumu" to "amumu",
        "annie" to "annie",
        "ashe" to "ashe",
        "aurelion sol" to "aurelion_sol",
        "aurelionsol" to "aurelion_sol",
        "aurora" to "aurora",
        "bard" to "bard",
        "bardo" to "bard",
        "blitzcrank" to "blitzcrank",
        "brand" to "brand",
        "braum" to "braum",
        "caitlyn" to "caitlyn",
        "camille" to "camille",
        "cho'gath" to "cho_gath",
        "chogath" to "cho_gath",
        "corki" to "corki",
        "darius" to "darius",
        "diana" to "diana",
        "dr. mundo" to "dr_mundo",
        "dr mundo" to "dr_mundo",
        "drmundo" to "dr_mundo",
        "mundo" to "dr_mundo",
        "draven" to "draven",
        "ekko" to "ekko",
        "evelynn" to "evelynn",
        "ezreal" to "ezreal",
        "fiddlesticks" to "fiddlesticks",
        "fiora" to "fiora",
        "fizz" to "fizz",
        "galio" to "galio",
        "garen" to "garen",
        "gnar" to "gnar",
        "gragas" to "gragas",
        "graves" to "graves",
        "gwen" to "gwen",
        "hecarim" to "hecarim",
        "heimerdinger" to "heimerdinger",
        "irelia" to "irelia",
        "janna" to "janna",
        "jarvan iv" to "jarvan_iv",
        "jarvan 4" to "jarvan_iv",
        "jarvan" to "jarvan_iv",
        "jax" to "jax",
        "jack" to "jax",
        "jayce" to "jayce",
        "jhin" to "jhin",
        "jinx" to "jinx",
        "k'sante" to "k_sante",
        "ksante" to "k_sante",
        "kai'sa" to "kai_sa",
        "kaisa" to "kai_sa",
        "kalista" to "kalista",
        "karma" to "karma",
        "kassadin" to "kassadin",
        "katarina" to "katarina",
        "kayle" to "kayle",
        "kayn" to "kayn",
        "kennen" to "kennen",
        "kha'zix" to "kha_zix",
        "khazix" to "kha_zix",
        "kindred" to "kindred",
        "kog'maw" to "kog_maw",
        "kogmaw" to "kog_maw",
        "lee sin" to "lee_sin",
        "leesin" to "lee_sin",
        "leona" to "leona",
        "lillia" to "lillia",
        "lissandra" to "lissandra",
        "lucian" to "lucian",
        "lulu" to "lulu",
        "lux" to "lux",
        "malphite" to "malphite",
        "malzahar" to "malzahar",
        "maokai" to "maokai",
        "master yi" to "master_yi",
        "masteryi" to "master_yi",
        "mel" to "mel",
        "milio" to "milio",
        "miss fortune" to "miss_fortune",
        "missfortune" to "miss_fortune",
        "mordekaiser" to "mordekaiser",
        "morgana" to "morgana",
        "nami" to "nami",
        "nasus" to "nasus",
        "nautilus" to "nautilus",
        "nilah" to "nilah",
        "nocturne" to "nocturne",
        "nunu & willump" to "nunu_and_willump",
        "nunu y willump" to "nunu_and_willump",
        "nunu" to "nunu_and_willump",
        "olaf" to "olaf",
        "orianna" to "orianna",
        "ornn" to "ornn",
        "pantheon" to "pantheon",
        "poppy" to "poppy",
        "pyke" to "pyke",
        "qiyana" to "qiyana",
        "rakan" to "rakan",
        "rammus" to "rammus",
        "renata" to "renata_glasc",
        "renata glasc" to "renata_glasc",
        "renekton" to "renekton",
        "rengar" to "rengar",
        "riven" to "riven",
        "rumble" to "rumble",
        "samira" to "samira",
        "sejuani" to "sejuani",
        "senna" to "senna",
        "seraphine" to "seraphine",
        "sett" to "sett",
        "shaco" to "shaco",
        "shen" to "shen",
        "shyvana" to "shyvana",
        "singed" to "singed",
        "sion" to "sion",
        "sivir" to "sivir",
        "smolder" to "smolder",
        "sona" to "sona",
        "soraka" to "soraka",
        "swain" to "swain",
        "syndra" to "syndra",
        "tahm kench" to "tahm_kench",
        "tahm" to "tahm_kench",
        "taliyah" to "taliyah",
        "talon" to "talon",
        "taric" to "taric",
        "teemo" to "teemo",
        "thresh" to "thresh",
        "tristana" to "tristana",
        "trundle" to "trundle",
        "tryndamere" to "tryndamere",
        "twisted fate" to "twisted_fate",
        "twistedfate" to "twisted_fate",
        "twitch" to "twitch",
        "urgot" to "urgot",
        "varus" to "varus",
        "vayne" to "vayne",
        "veigar" to "veigar",
        "vel'koz" to "vel_koz",
        "velkoz" to "vel_koz",
        "vex" to "vex",
        "vi" to "vi",
        "viego" to "viego",
        "viktor" to "viktor",
        "vladimir" to "vladimir",
        "volibear" to "volibear",
        "warwick" to "warwick",
        "wukong" to "wukong",
        "xayah" to "xayah",
        "xerath" to "xerath",
        "xin zhao" to "xin_zhao",
        "xinzhao" to "xin_zhao",
        "yasuo" to "yasuo",
        "yone" to "yone",
        "yorick" to "yorick",
        "yuumi" to "yuumi",
        "zac" to "zac",
        "zed" to "zed",
        "zeri" to "zeri",
        "ziggs" to "ziggs",
        "zilean" to "zilean",
        "zoe" to "zoe",
        "zyra" to "zyra"
    )

    fun normalize(input: String): String {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .replace("[^a-zA-Z0-9 ]".toRegex(), " ")
            .trim()
            .lowercase(Locale.ROOT)
            .replace("\\s+".toRegex(), " ")
    }

    private fun normalizeCompact(input: String): String {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .replace("[^a-zA-Z0-9]".toRegex(), "")
            .lowercase(Locale.ROOT)
    }

    /**
     * Colapsa caracteres individuales separados por un solo espacio producidos por el OCR con tracking amplio.
     * Ejemplo: "J I N X" -> "JINX", "S E T T" -> "SETT", "D R   M U N D O" -> "DR MUNDO", "L E E   S I N" -> "LEE SIN"
     */
    private fun collapseSpacedOutLetters(input: String): String {
        val trimmed = input.trim()
        if (trimmed.length < 3) return trimmed
        // Si la mayoría de los tokens son letras individuales separadas por espacios
        val parts = trimmed.split(" ").filter { it.isNotBlank() }
        if (parts.size >= 3 && parts.all { it.length == 1 }) {
            return parts.joinToString("")
        }
        return trimmed
    }

    /**
     * Calcula la distancia de Levenshtein para coincidencia difusa segura entre dos cadenas.
     */
    private fun levenshteinDistance(s1: String, s2: String): Int {
        if (s1 == s2) return 0
        if (s1.isEmpty()) return s2.length
        if (s2.isEmpty()) return s1.length

        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }
        for (i in 0..s1.length) dp[i][0] = i
        for (j in 0..s2.length) dp[0][j] = j

        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,      // eliminación
                    dp[i][j - 1] + 1,      // inserción
                    dp[i - 1][j - 1] + cost // sustitución
                )
            }
        }
        return dp[s1.length][s2.length]
    }

    // Encuentra el campeón correspondiente a una línea de texto OCR con validación anti-falsos positivos estricta
    fun findChampionInText(text: String, allChampions: List<Champion>): Champion? {
        val trimmed = text.trim()
        if (trimmed.isBlank() || trimmed.length < 2) return null

        // REGLA CRÍTICA: Si el texto contiene o representa una línea/rol (ej: "Calle Central", "Jungla", "Soporte", "Barón"),
        // bajo ninguna circunstancia debe interpretarse como un campeón.
        if (DraftValidationLayer.parseRoleFromText(trimmed) != null) {
            return null
        }

        // Si es ruido de interfaz o etiqueta genérica de jugador, descartar
        if (DraftValidationLayer.isNoiseText(trimmed)) {
            return null
        }

        val safeChamps = synchronized(WildRiftRepository) {
            ArrayList(allChampions)
        }

        // Si la línea contiene paréntesis (ej: "XCS Junior (Jarvan IV): ¡Combatamos!"), extraer el contenido de los paréntesis
        val parenthesisMatch = Regex("\\(([^)]+)\\)").find(trimmed)
        if (parenthesisMatch != null) {
            val insideText = parenthesisMatch.groupValues[1]
            val insideChamp = findChampionInText(insideText, safeChamps)
            if (insideChamp != null) return insideChamp
        }

        val stripped = DraftValidationLayer.stripLeadingMasteryOrRoleIcon(trimmed)
        val unspaced = collapseSpacedOutLetters(trimmed)
        val unspacedStripped = collapseSpacedOutLetters(stripped)

        val clean = normalize(trimmed)
        val cleanStripped = normalize(stripped)
        val cleanUnspaced = normalize(unspaced)
        val cleanUnspacedStripped = normalize(unspacedStripped)

        if (clean.isBlank() || UI_IGNORE_WORDS.contains(clean)) return null

        val compact = normalizeCompact(trimmed)
        val compactStripped = normalizeCompact(stripped)
        val compactUnspaced = normalizeCompact(unspaced)

        // 1. Coincidencia directa por mapa de nombres canónicos oficiales
        val candidatesList = listOf(
            cleanStripped, compactStripped, cleanUnspacedStripped,
            clean, compact, cleanUnspaced, compactUnspaced
        ).filter { it.isNotBlank() }

        for (c in candidatesList) {
            KNOWN_CHAMPIONS_MAP[c]?.let { id ->
                val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
                if (found != null && !DraftValidationLayer.isLikelySummonerName(c, championName = found.name)) {
                    return found
                }
            }
        }

        // 2. Coincidencia exacta por lista de campeones en memoria
        for (champ in safeChamps) {
            val champNorm = normalize(champ.name)
            val champCompact = normalizeCompact(champ.name)
            val champIdCompact = normalizeCompact(champ.id)

            if (candidatesList.any { it == champNorm || it == champCompact || it == champIdCompact }) {
                if (!DraftValidationLayer.isLikelySummonerName(trimmed, championName = champ.name)) {
                    return champ
                }
            }
        }

        // 3. Coincidencia tras eliminar posibles prefijos de icono o números residuales iniciales
        // (ej: "1 DARIUS", "# SETT", "• AHRI", "1DARIUS", "- JINX", "» CAITLYN", "> WUKONG", "LV7 JINX", "M7 AKALI")
        val strippedLeading = trimmed.replace(Regex("^[\\W_0-9]+"), "").trim()
        val strippedClean = normalize(strippedLeading)
        if (strippedClean.isNotBlank() && strippedClean != clean) {
            KNOWN_CHAMPIONS_MAP[strippedClean]?.let { id ->
                val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
                if (found != null && !DraftValidationLayer.isLikelySummonerName(strippedClean, championName = found.name)) {
                    return found
                }
            }
            for (champ in safeChamps) {
                val champNorm = normalize(champ.name)
                if (strippedClean == champNorm || normalizeCompact(strippedClean) == normalizeCompact(champ.name)) {
                    if (!DraftValidationLayer.isLikelySummonerName(strippedClean, championName = champ.name)) {
                        return champ
                    }
                }
            }
        }

        // 4. Coincidencia por tokens separados por espacio o símbolos (icono de elo/maestría antes del nombre del campeón)
        // Ejemplo: "V JINX", "LV7 JINX", "• JINX", "W JINX", "1 JINX", "M7 JINX", "> WUKONG", "» CAITLYN", "10 GALIO", "V VI"
        val tokensToScan = if (cleanStripped.isNotBlank() && cleanStripped != clean) {
            cleanStripped.split(" ").filter { it.isNotBlank() }
        } else {
            clean.split(" ").filter { it.isNotBlank() }
        }
        if (tokensToScan.isNotEmpty()) {
            // Revisamos cada token individualmente de derecha a izquierda (el nombre del campeón suele estar al final tras el icono)
            for (token in tokensToScan.reversed()) {
                if (token.length >= 2 && !UI_IGNORE_WORDS.contains(token) && DraftValidationLayer.parseRoleFromText(token) == null) {
                    KNOWN_CHAMPIONS_MAP[token]?.let { id ->
                        val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
                        if (found != null && !DraftValidationLayer.isLikelySummonerName(token, championName = found.name)) return found
                    }
                    for (champ in safeChamps) {
                        val champNorm = normalize(champ.name)
                        if (token == champNorm && !DraftValidationLayer.isLikelySummonerName(token, championName = champ.name)) {
                            return champ
                        }
                    }
                }
            }

            // Campeones de 2 palabras (ej: "DR MUNDO", "LEE SIN", "JARVAN IV", "MASTER YI", "AURELION SOL", "XIN ZHAO")
            for (i in 0 until tokensToScan.size - 1) {
                val pair = "${tokensToScan[i]} ${tokensToScan[i + 1]}"
                KNOWN_CHAMPIONS_MAP[pair]?.let { id ->
                    val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
                    if (found != null) return found
                }
            }
        }

        // 5. Coincidencia con prefijo pegado sin espacio (ej: "vwukong", "1caitlyn", "oyuumi", "agalio", "vpantheon", "vjinx", "1jinx", "lv7jinx", "m7jinx", "viijinx", "wsett")
        // Típico cuando el OCR concatena el icono de rango/elo/maestría con la primera letra del nombre del campeón
        for (candCompact in listOf(compactStripped, compact, compactUnspaced)) {
            if (candCompact.length in 3..25) {
                for (champ in safeChamps) {
                    val champCompact = normalizeCompact(champ.name)
                    val champIdCompact = normalizeCompact(champ.id)
                    for (target in listOf(champCompact, champIdCompact)) {
                        if (target.length >= 2 && candCompact.endsWith(target)) {
                            val prefixLen = candCompact.length - target.length
                            // Si el prefijo sobrante al inicio es de 1 a 5 caracteres (la insignia/icono/maestría)
                            if (prefixLen in 1..5) {
                                return champ
                            }
                        }
                    }
                }
            }
        }

        // 6. Coincidencia Difusa Segura (Fuzzy Matching con Levenshtein) para leves aberraciones OCR
        // Requiere longitud mínima del candidato >= 4 caracteres y distancia <= 1
        val bestCandForFuzzy = if (cleanStripped.length >= 4) cleanStripped else clean
        if (bestCandForFuzzy.length in 4..16 && !UI_IGNORE_WORDS.contains(bestCandForFuzzy)) {
            for (champ in safeChamps) {
                val champNorm = normalize(champ.name)
                val dist = levenshteinDistance(bestCandForFuzzy, champNorm)
                if (dist == 1 && !DraftValidationLayer.isLikelySummonerName(bestCandForFuzzy, championName = champ.name)) {
                    return champ
                }
            }
        }

        return null
    }
}
