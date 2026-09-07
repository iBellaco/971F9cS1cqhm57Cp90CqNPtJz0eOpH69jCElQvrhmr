import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        var userDetectedLane: LaneRole? = null"""
replacement = """        var userDetectedLane: LaneRole? = null
        var userSlotIndex: Int? = null
        val centerTexts = mutableListOf<String>()"""
content = content.replace(target, replacement)

target2 = """                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.66f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""
replacement2 = """                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.66f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }
                    // 1.3 CENTRO
                    else {
                        centerTexts.add(text)
                    }"""
content = content.replace(target2, replacement2)

target3 = """                if (allySlotRolesCache[i] != null && !low.contains("jugador") && !low.contains("player")) {
                    userDetectedLane = allySlotRolesCache[i]
                }"""
replacement3 = """                if (allySlotRolesCache[i] != null && !low.contains("jugador") && !low.contains("player")) {
                    userDetectedLane = allySlotRolesCache[i]
                    userSlotIndex = i
                }"""
if target3 not in content:
    target3 = """                if (allySlotRolesCache[i] != null && !low.contains("jugador")) {
                    userDetectedLane = allySlotRolesCache[i]
                }"""
    replacement3 = """                if (allySlotRolesCache[i] != null && !low.contains("jugador")) {
                    userDetectedLane = allySlotRolesCache[i]
                    userSlotIndex = i
                }"""
    if target3 not in content:
        print("COULD NOT FIND TARGET 3")
        sys.exit(1)
content = content.replace(target3, replacement3)

target4 = """        for (i in 0..4) {
            val lines = allySlotTexts[i]
            for (line in lines) {
                if (allyOcrChampions[i] == null) {
                    val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                    if (matched != null) {
                        allyOcrChampions[i] = matched
                    }
                }
            }"""
replacement4 = """        for (i in 0..4) {
            val lines = allySlotTexts[i]
            for (line in lines) {
                if (allyOcrChampions[i] == null) {
                    val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                    if (matched != null) {
                        allyOcrChampions[i] = matched
                    }
                }
            }
            // Si este es el slot del usuario y no tiene campeón, buscar en los textos centrales
            if (allyOcrChampions[i] == null && userSlotIndex == i) {
                for (centerLine in centerTexts) {
                    val matched = ChampionNameResolver.findChampionInText(centerLine, allChamps)
                    if (matched != null) {
                        allyOcrChampions[i] = matched
                        break
                    }
                }
            }"""
content = content.replace(target4, replacement4)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Patched DraftVisionScanner.kt for center texts")

