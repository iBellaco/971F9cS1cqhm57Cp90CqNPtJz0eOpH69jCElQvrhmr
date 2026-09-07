import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("val ocrChamp = enemyOcrChampions[i]")
end = content.find("var eval = ", start)
print(content[start:end])

