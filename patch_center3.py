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

target3 = """                        // En Wild Rift, solo el jugador local tiene su carril escrito explícitamente en el HUD
                        userDetectedLane = role"""
replacement3 = """                        // En Wild Rift, solo el jugador local tiene su carril escrito explícitamente en el HUD
                        userDetectedLane = role
                        userSlotIndex = i"""
content = content.replace(target3, replacement3)

target4 = """                    if (allyOcrChampions[i] == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            allyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto detectado: ${matched.name}")
                        }
                    }
                }
            }
        }"""
replacement4 = """                    if (allyOcrChampions[i] == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            allyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto detectado: ${matched.name}")
                        }
                    }
                }
            }
            if (allyOcrChampions[i] == null && userSlotIndex == i) {
                for (centerLine in centerTexts) {
                    val matched = ChampionNameResolver.findChampionInText(centerLine, allChamps)
                    if (matched != null) {
                        allyOcrChampions[i] = matched
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto CENTRAL detectado: ${matched.name}")
                        break
                    }
                }
            }
        }"""
content = content.replace(target4, replacement4)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Patched DraftVisionScanner.kt for center texts")

