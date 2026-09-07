import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

start = content.find("val allyAvatarCenterX")
end = content.find("val slotYRatios", start)
if start != -1 and end != -1:
    print(content[start:end+150])
else:
    print("Scanner Geom Not found")

start = content.find("if (xRatio in 0.01f..")
end = content.find("enemySlotTexts[slotIndex].add(text)", start)
if start != -1 and end != -1:
    print(content[start:end+150])
else:
    print("Scanner OCR Not found")
