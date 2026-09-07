import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = """        // 1. Coincidencia directa por mapa de nombres canónicos
        KNOWN_CHAMPIONS_MAP[clean]?.let { id ->
            val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
            if (found != null && DraftValidationLayer.isValidChampionToken(trimmed, found.id)) return found
        }"""

replacement = """        // 1. Coincidencia directa por mapa de nombres canónicos
        KNOWN_CHAMPIONS_MAP[clean]?.let { id ->
            val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
            // IGNORAMOS isValidChampionToken PORQUE EN ALGUNOS CASOS EL OCR FALLA EN CARACTERES
            // SI ESTA EN EL KNOWN_CHAMPIONS_MAP, LO TOMAMOS.
            if (found != null) return found
        }"""

content = content.replace(target, replacement)

target2 = """        // 2. Coincidencia por ID de campeón directamente
        allChampions.find { normalize(it.id) == clean }?.let { found ->
            if (DraftValidationLayer.isValidChampionToken(trimmed, found.id)) return found
        }"""

replacement2 = """        // 2. Coincidencia por ID de campeón directamente
        allChampions.find { normalize(it.id) == clean }?.let { found ->
            return found
        }"""
        
content = content.replace(target2, replacement2)

target3 = """        // 3. Tokenización y búsqueda de palabras sueltas (para nombres compuestos que el OCR juntó mal o separó)
        val tokens = trimmed.split(Regex("[^a-zA-Z0-9'áéíóúÁÉÍÓÚñÑ]+")).filter { it.isNotBlank() }
        for (token in tokens) {
            val tokenClean = normalize(token)
            if (UI_IGNORE_WORDS.contains(tokenClean)) continue

            KNOWN_CHAMPIONS_MAP[tokenClean]?.let { id ->
                val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
                if (found != null && DraftValidationLayer.isValidChampionToken(token, found.id)) return found
            }

            allChampions.find { normalize(it.id) == tokenClean }?.let { found ->
                if (DraftValidationLayer.isValidChampionToken(token, found.id)) return found
            }
        }"""
        
replacement3 = """        // 3. Tokenización y búsqueda de palabras sueltas (para nombres compuestos que el OCR juntó mal o separó)
        val tokens = trimmed.split(Regex("[^a-zA-Z0-9'áéíóúÁÉÍÓÚñÑ]+")).filter { it.isNotBlank() }
        for (token in tokens) {
            val tokenClean = normalize(token)
            if (UI_IGNORE_WORDS.contains(tokenClean)) continue

            KNOWN_CHAMPIONS_MAP[tokenClean]?.let { id ->
                val found = allChampions.find { it.id.equals(id, ignoreCase = true) }
                if (found != null) return found
            }

            allChampions.find { normalize(it.id) == tokenClean }?.let { found ->
                return found
            }
        }"""
        
content = content.replace(target3, replacement3)

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
    f.write(content)
print("Resolver logic patched to remove strict token validation that was blocking good OCR")

