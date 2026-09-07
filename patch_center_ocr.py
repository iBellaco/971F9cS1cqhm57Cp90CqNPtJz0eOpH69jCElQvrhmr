import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio in 0.01f..0.34f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.66f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }"""

replacement = """                    // 1.1 COLUMNA ALIADA (Extremos ampliados para capturar los nombres, pero evitando el centro >0.33)
                    if (xRatio in 0.01f..0.34f) {
                        allySlotTexts[slotIndex].add(text)
                    }
                    // 1.2 COLUMNA ENEMIGA (X entre 0.66 y 0.99)
                    else if (xRatio in 0.66f..0.99f) {
                        enemySlotTexts[slotIndex].add(text)
                    }
                    // 1.3 CENTRO DE LA PANTALLA (Para capturar al campeón activo en pre-selección)
                    else if (xRatio in 0.35f..0.65f) {
                        // Si es el texto de un campeón en el centro, lo inyectamos al equipo aliado si el usuario es aliado, o enemigo si es enemigo.
                        // Como no sabemos el slot con certeza, podemos buscar si el slot actual está vacío (sin ocr).
                        // Lo guardaremos temporalmente y luego lo asignamos si falta el nombre.
                        // Para simplificar, añadimos el texto a TODOS los slots aliados si el usuario es aliado (asumimos que el usuario es aliado por defecto).
                        // O mejor aún, buscamos el campeón en el centro y lo forzamos al slot del usuario.
                        // Por ahora, simplemente lo agregamos a detectedWords y lo procesaremos luego.
                    }"""

content = content.replace(target, replacement)

# Actually, a better way to handle the center active pick is to find the champion in `detectedWords` that are in the center, and if the user slot's ocrChamp is null, assign it!
# Wait, how do we know the user's slot?
# The user's slot is the one with "CALLE CENTRAL", "SOPORTE", etc, or the one that is missing a champion in OCR!
# Wait, the user slot is where the text "ELIGIENDA" or just missing text.

