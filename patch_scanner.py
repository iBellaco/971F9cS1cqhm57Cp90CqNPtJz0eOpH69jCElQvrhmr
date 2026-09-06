import sys

# 1. Patch DraftVisionScanner.kt
file_path = "app/src/main/java/com/example/service/screen/DraftVisionScanner.kt"
with open(file_path, "r") as f:
    content = f.read()

# Fix Step 2
old_step2 = """        // -----------------------------------------------------------------------------------------
        // PASO 2: ASIGNACIÓN DE ROLES ESTÁNDAR POR SLOT (TOP=0, JUNGLE=1, MID=2, ADC=3, SUP=4)
        // -----------------------------------------------------------------------------------------
        for (i in 0..4) {
            val slot = allySlots[i]
            if (slot.explicitRole == null) {
                slot.explicitRole = allySlotRolesCache[i] ?: when (i) {
                    0 -> LaneRole.TOP
                    1 -> LaneRole.JUNGLE
                    2 -> LaneRole.MID
                    3 -> LaneRole.ADC
                    4 -> LaneRole.SUPPORT
                    else -> null
                }
            }
        }
        for (i in 0..4) {
            val slot = enemySlots[i]
            if (slot.explicitRole == null) {
                slot.explicitRole = when (i) {
                    0 -> LaneRole.TOP
                    1 -> LaneRole.JUNGLE
                    2 -> LaneRole.MID
                    3 -> LaneRole.ADC
                    4 -> LaneRole.SUPPORT
                    else -> null
                }
            }
        }"""
new_step2 = """        // -----------------------------------------------------------------------------------------
        // PASO 2: ASIGNACIÓN DE ROLES EXPLÍCITOS (Solo cuando hay texto comprobado)
        // -----------------------------------------------------------------------------------------
        for (i in 0..4) {
            val slot = allySlots[i]
            if (slot.explicitRole == null) {
                slot.explicitRole = allySlotRolesCache[i]
            }
        }
        // No forzamos roles naturales aquí, DraftValidationLayer se encarga de usar el índice del slot si hace falta."""
content = content.replace(old_step2, new_step2)

# Fix Conflict Resolution
old_conflict = """                } else {
                    // Conflicto visual vs OCR
                    if (eval.score1 >= 0.85f) {
                        finalChamp = eval.candidate1
                        finalConfidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Visual contundente (${eval.candidate1.name} score ${"%.2f".format(java.util.Locale.US, eval.score1)}) supera texto OCR (${ocrChamp.name})"
                    } else if (eval.score1 < 0.68f) {
                        finalChamp = ocrChamp
                        finalConfidence = 80
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Conflicto resuelto por OCR (${ocrChamp.name}) ante baja certeza visual (${eval.candidate1.name} score ${"%.2f".format(java.util.Locale.US, eval.score1)})"
                    } else {
                        finalChamp = null
                        finalConfidence = 0
                        diagStatus = DiagnosticStatus.AMBIGUO
                        diagReason = "Conflicto irreconciliable: Visual=${eval.candidate1.name} (${"%.2f".format(java.util.Locale.US, eval.score1)}) vs OCR=${ocrChamp.name}. NO ASIGNAR."
                    }
                }
            } else {
                if (ocrChamp != null) {
                    finalChamp = ocrChamp
                    finalConfidence = 80"""

new_conflict = """                } else {
                    // Conflicto visual vs OCR
                    if (eval.score1 >= 0.95f) {
                        finalChamp = eval.candidate1
                        finalConfidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Visual perfecto (${eval.candidate1.name} score ${"%.2f".format(java.util.Locale.US, eval.score1)}) supera texto OCR (${ocrChamp.name})"
                    } else {
                        finalChamp = ocrChamp
                        finalConfidence = 90
                        diagStatus = DiagnosticStatus.CONFIRMADO
                        diagReason = "Priorizando OCR (${ocrChamp.name}) ante conflicto visual menor (${eval.candidate1.name} score ${"%.2f".format(java.util.Locale.US, eval.score1)})"
                    }
                }
            } else {
                if (ocrChamp != null) {
                    finalChamp = ocrChamp
                    finalConfidence = 90"""
content = content.replace(old_conflict, new_conflict)

# Fix Avatar Center (optional, but 111 px in 695p is ~ 15.9%, maybe let's adjust it slightly to 0.155)
content = content.replace("val allyAvatarCenterX = (height * 0.160f).toInt()", "val allyAvatarCenterX = (height * 0.155f).toInt()")

with open(file_path, "w") as f:
    f.write(content)

# 2. Patch ImageHashMatcher.kt
hash_path = "app/src/main/java/com/example/util/ImageHashMatcher.kt"
with open(hash_path, "r") as f:
    content = f.read()

content = content.replace("getInnerCrop(bitmap, 0.85f)", "getInnerCrop(bitmap, 0.60f)")

with open(hash_path, "w") as f:
    f.write(content)

