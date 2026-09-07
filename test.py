import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()
if "lastDebugBitmap.value" in content:
    print("Yes")
