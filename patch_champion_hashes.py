import sys

with open("app/src/main/java/com/example/util/ChampionHashes.kt", "r") as f:
    content = f.read()

content = content.replace("val colorHistogram: FloatArray, // 64 bins", "val colorHistogram: FloatArray, // 512 bins")
content = content.replace("val colorHist = FloatArray(64)", "val colorHist = FloatArray(512)")
content = content.replace("val rBin = (r / 64).coerceIn(0, 3)", "val rBin = (r / 32).coerceIn(0, 7)")
content = content.replace("val gBin = (g / 64).coerceIn(0, 3)", "val gBin = (g / 32).coerceIn(0, 7)")
content = content.replace("val bBin = (b / 64).coerceIn(0, 3)", "val bBin = (b / 32).coerceIn(0, 7)")
content = content.replace("val bin = rBin * 16 + gBin * 4 + bBin", "val bin = rBin * 64 + gBin * 8 + bBin")

with open("app/src/main/java/com/example/util/ChampionHashes.kt", "w") as f:
    f.write(content)
print("Patched ChampionHashes.kt")
