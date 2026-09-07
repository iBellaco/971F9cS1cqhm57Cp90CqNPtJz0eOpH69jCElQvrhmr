import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                margin = eval.margin,
                ocrChampion = ocrChamp,
                status = diagStatus,
                reason = diagReason
            )"""
replacement = """                margin = eval.margin,
                ocrChampion = ocrChamp,
                finalChampion = finalChamp,
                status = diagStatus,
                reason = diagReason
            )"""
content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
print("Patched DraftVisionScanner.kt diagnostic usage")

