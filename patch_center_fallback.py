import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    }
                }
                
                val isGeneric = lines.isEmpty() || lines.all { l ->"""

replacement = """                    }
                }
                
                // C) Fallback al texto central si es el slot del jugador y no tiene campeón (ej: Pre-selección)
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
                
                val isGeneric = lines.isEmpty() || lines.all { l ->"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Patched fallback")
