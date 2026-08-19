import re

file_path = "app/src/main/java/com/example/util/CoachingGenerator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

content = content.replace('champion.summary + "\n\n" + base + "\n\n" + mid', 'champion.summary + "\\n\\n" + base + "\\n\\n" + mid')

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Newlines patched!")
