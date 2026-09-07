import sys

file_path = "app/src/main/java/com/example/service/screen/DraftVisionScanner.kt"
with open(file_path, "r") as f:
    content = f.read()

# Fix Ally Block
old_ally_block = """            var diagReason = eval.reason

            if (eval.isConfirmed && eval.candidate1 != null) {"""

new_ally_block = """            var diagReason = eval.reason

            if (eval.status == "VACIO") {
                finalChamp = null
                finalConfidence = 0
                diagStatus = DiagnosticStatus.VACIO
            } else if (eval.isConfirmed && eval.candidate1 != null) {"""

content = content.replace(old_ally_block, new_ally_block)

# Fix Enemy Block
old_enemy_block = """            var diagReason = eval.reason

            if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

new_enemy_block = """            var diagReason = eval.reason

            if (eval.status == "VACIO") {
                finalChamp = null
                finalConfidence = 0
                diagStatus = DiagnosticStatus.VACIO
            } else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"""

content = content.replace(old_enemy_block, new_enemy_block)

with open(file_path, "w") as f:
    f.write(content)

