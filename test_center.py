import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("val enemyOcrChampions = Array<Champion?>(5) { null }")
end = content.find("if (isGeneric) {", start)
print(content[start:end])

