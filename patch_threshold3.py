import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = "(!slot.isLikelyUnpicked || eval.score1 >= 0.60f)"
replacement = "(!slot.isLikelyUnpicked || eval.score1 >= 0.40f)"

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Lowered DraftVisionScanner threshold to 0.40f")
else:
    print("Could not find target in DraftVisionScanner")

