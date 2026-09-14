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

    // Encuentra el campeón correspondiente a una línea de texto OCR con validación anti-falsos positivos
    fun findChampionInText(text: String, allChampions: List<Champion>): Champion? {
        val trimmed = text.trim()
        if (trimmed.isBlank() || trimmed.length < 2) return null
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

        val clean = normalize(trimmed)
        if (clean.isBlank()) return null
        if (UI_IGNORE_WORDS.contains(clean)) return null

        val compact = normalizeCompact(trimmed)

        // 1. Coincidencia directa por mapa de nombres canónicos
        KNOWN_CHAMPIONS_MAP[clean]?.let { id ->
            val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
            if (found != null && !DraftValidationLayer.isLikelySummonerName(trimmed)) return found
        }
        KNOWN_CHAMPIONS_MAP[compact]?.let { id ->
            val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
            if (found != null && !DraftValidationLayer.isLikelySummonerName(trimmed)) return found
        }

        // 2. Coincidencia exacta por lista de campeones en memoria
        for (champ in safeChamps) {
            val champNorm = normalize(champ.name)
            val champCompact = normalizeCompact(champ.name)
            val champIdCompact = normalizeCompact(champ.id)

            if (clean == champNorm || compact == champCompact || compact == champIdCompact) {
                if (!DraftValidationLayer.isLikelySummonerName(trimmed)) {
                    return champ
                }
            }
        }

        // 3. Coincidencia por palabra contenida (ej: "WUKONG" en "WUKONG XCS Alee22")
        val words = clean.split(" ").filter { it.length >= 2 && !UI_IGNORE_WORDS.contains(it) }
        for (word in words) {
            // Para nombres ultracortos de 2 letras (ej: "VI"), exigir que la línea completa sea sólo esa palabra
            if (word.length == 2 && words.size > 1) {
                continue
            }

            KNOWN_CHAMPIONS_MAP[word]?.let { id ->
                val found = safeChamps.find { it.id.equals(id, ignoreCase = true) }
                if (found != null && DraftValidationLayer.isValidChampionToken(word, found.id)) {
                    return found
                }
            }
            for (champ in safeChamps) {
                val champNorm = normalize(champ.name)
                val champCompact = normalizeCompact(champ.name)
                if (champNorm == word || champCompact == word) {
                    if (DraftValidationLayer.isValidChampionToken(word, champ.id)) {
                        return champ
                    }
                }
                // Si el icono de carril recortó la 1era letra (ej: GALIO -> ALIO, VEIGAR -> EIGAR)
                if (word.length >= 4 && champNorm.length == word.length + 1 && champNorm.endsWith(word)) {
                    if (DraftValidationLayer.isValidChampionToken(word, champ.id)) {
                        return champ
                    }
                }
            }
        }

        return null
    }
}
