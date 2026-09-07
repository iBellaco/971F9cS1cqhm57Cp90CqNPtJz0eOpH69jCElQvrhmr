import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """            var diagStatus = when (eval.status) {
                "CONFIRMADO" -> DiagnosticStatus.CONFIRMADO
                "AMBIGUO" -> DiagnosticStatus.AMBIGUO
                "VACIO" -> DiagnosticStatus.VACIO
                else -> DiagnosticStatus.RECHAZADO
            }
            var diagReason = eval.reason

            if (eval.status == "VACIO") {
                finalChamp = null
                finalConfidence = 0
                diagStatus = DiagnosticStatus.VACIO
            } else if (eval.isConfirmed && eval.candidate1 != null) {"""

replacement = """            var diagStatus = when (eval.status) {
                "CONFIRMADO" -> DiagnosticStatus.CONFIRMADO
                "AMBIGUO" -> DiagnosticStatus.AMBIGUO
                "VACIO" -> DiagnosticStatus.VACIO
                else -> DiagnosticStatus.RECHAZADO
            }
            var diagReason = eval.reason

            if (ocrChamp != null && !slot.isLikelyUnpicked) {
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

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
