import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank()
                }
                if (isGeneric) {
                    slot.isLikelyUnpicked = true
                }"""

replacement = """                val isGeneric = lines.isEmpty() || lines.all { l ->
                    val low = l.lowercase(Locale.ROOT)
                    low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || low.isBlank() ||
                    low.contains("carril") || low.contains("jungla") || low.contains("central") || low.contains("dúo") || low.contains("soporte") ||
                    low.contains("top") || low.contains("jug") || low.contains("mid") || low.contains("adc") || low.contains("sup") || low.contains("eligiendo")
                }
                if (isGeneric) {
                    slot.isLikelyUnpicked = true
                }"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
