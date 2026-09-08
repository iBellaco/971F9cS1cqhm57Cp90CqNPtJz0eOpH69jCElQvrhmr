import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """                    val text = line.text.trim()
                    if (text.isBlank()) continue
                    
                    val box = line.boundingBox"""

replacement = """                    val text = line.text.trim()
                    if (text.isBlank()) continue
                    if (text.contains("[OCR]") || text.contains("VIS:") || text.contains("[VISUAL]")) continue
                    
                    val box = line.boundingBox"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(content)
    print("Patched OCR filtering")
else:
    print("Target not found")
