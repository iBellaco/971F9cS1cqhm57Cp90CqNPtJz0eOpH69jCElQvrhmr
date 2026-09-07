import sys

# 1. Fix DraftVisionScanner (Geometry + OCR Bounds)
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

geom_target = """        val allyAvatarCenterX = if (isUltraWide) (height * 0.165f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.075f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y): El pitch actual es excelente, bajamos todos apenas 1 pixel relativo
        val slotYRatios = floatArrayOf(0.201f, 0.333f, 0.468f, 0.601f, 0.738f)"""
geom_replacement = """        // Ajustes del usuario (V9):
        // Izquierda (Aliados): un pelín más a la izquierda.
        // Derecha (Enemigos): un poco más a la izquierda.
        val allyAvatarCenterX = if (isUltraWide) (height * 0.162f).toInt() else (height * 0.155f).toInt()
        val enemyAvatarCenterX = if (isUltraWide) (width - (height * 0.088f)).toInt() else (width - (height * 0.160f)).toInt()

        // Ratios verticales (eje Y): Subimos un poco (~4 pixeles relativos)
        val slotYRatios = floatArrayOf(0.196f, 0.328f, 0.463f, 0.596f, 0.733f)"""

ocr_target = """                    // 1.1 COLUMNA ALIADA (Extremo Izquierdo estricto: X entre 0.01 y 0.22)
                    // Evitamos > 0.22 porque podríamos leer el overlay de nuestra propia app
                    if (xRatio in 0.01f..0.22f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (Extremo Derecho estricto: X entre 0.78 y 0.99)
                    else if (xRatio in 0.78f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""
ocr_replacement = """                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.34)
                    if (xRatio in 0.01f..0.34f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.66f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""

if geom_target in content and ocr_target in content:
    content = content.replace(geom_target, geom_replacement).replace(ocr_target, ocr_replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Scanner geometry patched")
else:
    print("Scanner target not found")


# 2. Fix ImageHashMatcher (Color weights)
with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    matcher_content = f.read()

weight_target = """                // C) Puntuación visual pura (sin sesgo de rol)
                val totalScore = if (isAlly) {
                    (0.55f * structuralScore) + (0.45f * colorScore)
                } else {
                    (0.50f * structuralScore) + (0.50f * colorScore)
                }"""
weight_replacement = """                // C) Puntuación visual pura
                // Damos un 85% de peso a la ESTRUCTURA y solo 15% al COLOR.
                // Esto es crítico porque durante los turnos de selección, Wild Rift aplica un tinte ROJO
                // fuerte a la pantalla enemiga y AZUL a la aliada, destruyendo los histogramas de color.
                val totalScore = (0.85f * structuralScore) + (0.15f * colorScore)"""

if weight_target in matcher_content:
    matcher_content = matcher_content.replace(weight_target, weight_replacement)
    with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
        f.write(matcher_content)
    print("Matcher weights patched")
else:
    print("Matcher target not found")

