import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target1 = "} else if (eval.isConfirmed && eval.candidate1 != null && !slot.isLikelyUnpicked) {"
replacement1 = "} else if (eval.isConfirmed && eval.candidate1 != null && (!slot.isLikelyUnpicked || eval.score1 >= 0.75f)) {"

if target1 in content:
    content = content.replace(target1, replacement1)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Patched isLikelyUnpicked bypass")
else:
    print("Could not find target")

