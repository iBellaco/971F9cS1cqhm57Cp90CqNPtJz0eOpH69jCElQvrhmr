import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    if (allyOcrChampions[i] == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            allyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto detectado: ${matched.name}")
                        }
                    }
                }
            }"""
            
replacement = """                    if (allyOcrChampions[i] == null) {
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            allyOcrChampions[i] = matched
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Texto detectado: ${matched.name}")
                        }
                    }
                }
                
                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") ||
                    low.contains("buscando")
                }
                if (isGeneric) {
                    slot.isLikelyUnpicked = true
                }
            }"""
            
if "val isGeneric = lines.isEmpty() || lines.all" not in content[:content.find("Procesar textos enemigos")]:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Ally unpicked patched")
else:
    print("Already patched")
