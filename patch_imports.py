import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Add import
if "import com.example.util.tr" not in content:
    content = content.replace("import com.example.ui.components.ChampionAvatar", "import com.example.ui.components.ChampionAvatar\nimport com.example.util.tr")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Imports patched!")
