import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """    val ocrChampion: Champion?,
    val status: DiagnosticStatus,
    val reason: String"""
replacement = """    val ocrChampion: Champion?,
    val finalChampion: Champion?,
    val status: DiagnosticStatus,
    val reason: String"""
content = content.replace(target, replacement)

target2 = """            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = true,
                roiRect = roiRect,
                candidate1 = eval.candidate1,
                score1 = eval.score1,
                candidate2 = eval.candidate2,                score2 = eval.score2,
                margin = eval.margin,
                ocrChampion = ocrChamp,
                status = diagStatus,
                reason = diagReason
            )"""
replacement2 = """            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = true,
                roiRect = roiRect,
                candidate1 = eval.candidate1,
                score1 = eval.score1,
                candidate2 = eval.candidate2,                score2 = eval.score2,
                margin = eval.margin,
                ocrChampion = ocrChamp,
                finalChampion = finalChamp,
                status = diagStatus,
                reason = diagReason
            )"""
content = content.replace(target2, replacement2)

target3 = """            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = false,
                roiRect = roiRect,
                candidate1 = eval.candidate1,
                score1 = eval.score1,
                candidate2 = eval.candidate2,                score2 = eval.score2,
                margin = eval.margin,
                ocrChampion = ocrChamp,
                status = diagStatus,
                reason = diagReason
            )"""
replacement3 = """            val diagnostic = SlotDiagnostic(
                slotIndex = i,
                isAlly = false,
                roiRect = roiRect,
                candidate1 = eval.candidate1,
                score1 = eval.score1,
                candidate2 = eval.candidate2,                score2 = eval.score2,
                margin = eval.margin,
                ocrChampion = ocrChamp,
                finalChampion = finalChamp,
                status = diagStatus,
                reason = diagReason
            )"""
content = content.replace(target3, replacement3)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Patched SlotDiagnostic")

