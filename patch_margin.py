import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """            val avatarDiameter = (height * 0.115f).toInt().coerceAtLeast(32)"""
replacement = """            val avatarDiameter = (height * 0.125f).toInt().coerceAtLeast(32) // Un pelin más grande para que quepa bien el campeón"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Success")
else:
    print("Target not found")
