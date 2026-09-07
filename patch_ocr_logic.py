import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

# 1. Aliados
target_ally = """            if (ocrChamp != null && !slot.isLikelyUnpicked && (eval.isConfirmed || eval.score1 >= 0.55f)) {
                // OCR ayuda a desempatar o confirmar si la imagen tiene un score mínimamente decente (>0.55)
                // Evitamos que OCR fuerce un campeón basándose en leer nuestro propio overlay (alucinación)
                finalChamp = ocrChamp
                if (eval.candidate1?.id == ocrChamp.id) {
                    finalConfidence = 100
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    finalConfidence = 85
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Asignado por OCR (${ocrChamp.name}), con base visual aceptable."
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

replacement_ally = """            // NUEVA LÓGICA V13: TEXTO > IMAGEN SIEMPRE.
            // Si el OCR leyó un nombre, es LEY (porque el nombre solo sale cuando el campeón está seleccionado/preseleccionado).
            // Ignoramos la puntuación de la imagen porque los tintes rojos/azules la arruinan.
            if (ocrChamp != null && !slot.isLikelyUnpicked) {
                finalChamp = ocrChamp
                finalConfidence = 100
                diagStatus = DiagnosticStatus.CONFIRMADO
                if (eval.candidate1?.id == ocrChamp.id) {
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    diagReason = "Asignado por TEXTO OCR (${ocrChamp.name}) ignorando visión errónea (${eval.candidate1?.name ?: "Nada"})"
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

if "NUEVA LÓGICA V13: TEXTO > IMAGEN SIEMPRE" not in content:
    content = content.replace(target_ally, replacement_ally)

# 2. Enemigos
target_enemy = """            if (ocrChamp != null && !slot.isLikelyUnpicked && (eval.isConfirmed || eval.score1 >= 0.55f)) {
                // OCR ayuda a desempatar o confirmar si la imagen tiene un score mínimamente decente (>0.55)
                // Evitamos que OCR fuerce un campeón basándose en leer nuestro propio overlay (alucinación)
                finalChamp = ocrChamp
                if (eval.candidate1?.id == ocrChamp.id) {
                    finalConfidence = 100
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    finalConfidence = 85
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Asignado por OCR (${ocrChamp.name}), con base visual aceptable."
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

replacement_enemy = """            if (ocrChamp != null && !slot.isLikelyUnpicked) {
                finalChamp = ocrChamp
                finalConfidence = 100
                diagStatus = DiagnosticStatus.CONFIRMADO
                if (eval.candidate1?.id == ocrChamp.id) {
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    diagReason = "Asignado por TEXTO OCR (${ocrChamp.name}) ignorando visión errónea (${eval.candidate1?.name ?: "Nada"})"
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

content = content.replace(target_enemy, replacement_enemy)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("OCR Logic Patched")
