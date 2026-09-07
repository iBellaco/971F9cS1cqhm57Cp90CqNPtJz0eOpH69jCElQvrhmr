import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("val allyOcrChampions =")
end = content.find("for (i in 0 until 5)", start)
if start != -1 and end != -1:
    print(content[start:end])
else:
    print("Not found")
