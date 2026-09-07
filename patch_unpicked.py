import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") || low.contains("buscando")
                }
                if (isGeneric) {
                    slot.isLikelyUnpicked = true
                }"""

replacement = """                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") || low.contains("buscando")
                }
                if (isGeneric && enemyOcrChampions[i] == null) {
                    slot.isLikelyUnpicked = true
                } else if (enemyOcrChampions[i] != null) {
                    slot.isLikelyUnpicked = false
                }"""

target_ally = """                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") ||
                    low.contains("buscando")
                }
                if (isGeneric) {
                    slot.isLikelyUnpicked = true
                }"""
                
replacement_ally = """                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(java.util.Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo") ||
                    low.contains("buscando")
                }
                if (isGeneric && allyOcrChampions[i] == null) {
                    slot.isLikelyUnpicked = true
                } else if (allyOcrChampions[i] != null) {
                    slot.isLikelyUnpicked = false
                }"""

content = content.replace(target, replacement)
content = content.replace(target_ally, replacement_ally)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Unpicked logic patched to respect OCR")
