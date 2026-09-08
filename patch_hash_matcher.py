import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

content = content.replace("val cropColorHist = FloatArray(64)", "val cropColorHist = FloatArray(512)")
content = content.replace("val rBin = (r / 64).coerceIn(0, 3)", "val rBin = (r / 32).coerceIn(0, 7)")
content = content.replace("val gBin = (g / 64).coerceIn(0, 3)", "val gBin = (g / 32).coerceIn(0, 7)")
content = content.replace("val bBin = (b / 64).coerceIn(0, 3)", "val bBin = (b / 32).coerceIn(0, 7)")
content = content.replace("val bin = rBin * 16 + gBin * 4 + bBin", "val bin = rBin * 64 + gBin * 8 + bBin")
content = content.replace("val totalScore = (0.60f * structuralScore) + (0.40f * colorScore)", "val totalScore = (0.25f * structuralScore) + (0.75f * colorScore)")

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
    f.write(content)
print("Patched ImageHashMatcher.kt")
