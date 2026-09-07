import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = """        // 1. Coincidencia directa por mapa de nombres canónicos
        KNOWN_CHAMPIONS_MAP[clean]?.let { id ->
            val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
            if (found != null && DraftValidationLayer.isValidChampionToken(clean, found.id)) return found
        }
        KNOWN_CHAMPIONS_MAP[compact]?.let { id ->
            val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
            if (found != null && DraftValidationLayer.isValidChampionToken(compact, found.id)) return found
        }

        // 2. Coincidencia exacta por lista de campeones en memoria
        for (champ in allChampions) {
            val champNorm = normalize(champ.name)
            val champCompact = normalizeCompact(champ.name)
            val champIdCompact = normalizeCompact(champ.id)
            if (clean == champNorm || compact == champCompact || compact == champIdCompact) {
                return champ
            }
        }

        // 3. Coincidencia por palabra contenida (ej: "WUKONG XCS Alee22" -> detecta "WUKONG")
        val words = clean.split(" ").filter { it.length >= 3 && !UI_IGNORE_WORDS.contains(it) }
        for (word in words) {
            KNOWN_CHAMPIONS_MAP[word]?.let { id ->
                val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
                if (found != null && DraftValidationLayer.isValidChampionToken(word, found.id)) {
                    return found
                }
            }
            for (champ in allChampions) {
                val champNorm = normalize(champ.name)
                val champCompact = normalizeCompact(champ.name)
                if (champNorm == word || champCompact == word) {
                    if (DraftValidationLayer.isValidChampionToken(word, champ.id)) {
                        return champ
                    }
                }
            }
        }"""

replacement = """        // Anti falsos positivos: si el texto es obviamente el nombre de un jugador, salir rápido.
        if (DraftValidationLayer.isLikelySummonerName(trimmed)) return null

        // 1. Coincidencia directa por mapa de nombres canónicos
        KNOWN_CHAMPIONS_MAP[clean]?.let { id ->
            val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
            if (found != null) return found
        }
        KNOWN_CHAMPIONS_MAP[compact]?.let { id ->
            val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
            if (found != null) return found
        }

        // 2. Coincidencia exacta por lista de campeones en memoria
        for (champ in allChampions) {
            val champNorm = normalize(champ.name)
            val champCompact = normalizeCompact(champ.name)
            val champIdCompact = normalizeCompact(champ.id)
            if (clean == champNorm || compact == champCompact || compact == champIdCompact) {
                return champ
            }
        }

        // 3. Coincidencia por palabra contenida (ej: "WUKONG XCS Alee22" -> detecta "WUKONG")
        val words = clean.split(" ").filter { it.length >= 3 && !UI_IGNORE_WORDS.contains(it) }
        for (word in words) {
            KNOWN_CHAMPIONS_MAP[word]?.let { id ->
                val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
                if (found != null) return found
            }
            for (champ in allChampions) {
                val champNorm = normalize(champ.name)
                val champCompact = normalizeCompact(champ.name)
                if (champNorm == word || champCompact == word) {
                    return champ
                }
            }
        }"""

content = content.replace(target, replacement)

target4 = """        if (clean.length in 5..12 && words.size == 1 && !DraftValidationLayer.isLikelySummonerName(trimmed)) {
            for (champ in allChampions) {
                val champNorm = normalize(champ.name)
                if (champNorm.length >= 5 && Math.abs(champNorm.length - clean.length) <= 1) {
                    if (levenshteinDistance(clean, champNorm) <= 1) {
                        if (DraftValidationLayer.isValidChampionToken(clean, champ.id)) {
                            return champ
                        }
                    }
                }
            }
        }"""
        
replacement4 = """        if (clean.length in 5..12 && words.size == 1 && !DraftValidationLayer.isLikelySummonerName(trimmed)) {
            for (champ in allChampions) {
                val champNorm = normalize(champ.name)
                if (champNorm.length >= 5 && Math.abs(champNorm.length - clean.length) <= 1) {
                    if (levenshteinDistance(clean, champNorm) <= 1) {
                        return champ
                    }
                }
            }
        }"""

content = content.replace(target4, replacement4)

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
    f.write(content)
print("Resolver real fix applied")

