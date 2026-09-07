import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

target = """            margin < 0.018f -> {
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = false,
                    status = "AMBIGUO",
                    reason = "Coincidencia ambigua entre ${bestChamp.name} (${"%.2f".format(java.util.Locale.US, bestScore)}) y ${secondChamp?.name ?: "segundo"} (${"%.2f".format(java.util.Locale.US, secondScore)}) margen ${"%.2f".format(java.util.Locale.US, margin)} < 0.02"
                )
            }
            else -> {"""
            
replacement = """            margin < 0.018f -> {
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = false,
                    status = "AMBIGUO",
                    reason = "Coincidencia ambigua entre ${bestChamp.name} (${"%.2f".format(java.util.Locale.US, bestScore)}) y ${secondChamp?.name ?: "segundo"} (${"%.2f".format(java.util.Locale.US, secondScore)}) margen ${"%.2f".format(java.util.Locale.US, margin)} < 0.02"
                )
            }
            bestScore < 0.65f -> {
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = false,
                    status = "DUDOSO",
                    reason = "Puntuación muy baja para confirmar visualmente sin OCR (score ${"%.2f".format(java.util.Locale.US, bestScore)})"
                )
            }
            else -> {"""

if "bestScore < 0.65f" not in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
        f.write(content)
    print("Matcher thresholds patched")
