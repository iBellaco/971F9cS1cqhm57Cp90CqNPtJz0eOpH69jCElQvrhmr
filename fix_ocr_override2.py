import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """            if (ocrChamp != null && !slot.isLikelyUnpicked) {
                // OCR es la fuente de la verdad para el campeón si está presente
                finalChamp = ocrChamp
                if (eval.isConfirmed && eval.candidate1?.id == ocrChamp.id) {
                    finalConfidence = 100
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Confirmado 100% (Visual y OCR coinciden: ${ocrChamp.name})"
                } else {
                    finalConfidence = 90
                    diagStatus = DiagnosticStatus.CONFIRMADO
                    diagReason = "Asignado por OCR exacto (${ocrChamp.name}), visual no coincidió o estaba vacío."
                }
            } else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

replacement = """            if (ocrChamp != null && !slot.isLikelyUnpicked && (eval.isConfirmed || eval.score1 >= 0.55f)) {
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

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success OCR Override 2")
else:
    print("Target 2 not found")
