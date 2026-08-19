import re

file_path = "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

if "import androidx.compose.ui.text.style.TextOverflow" not in content:
    content = content.replace("import androidx.compose.ui.text.font.FontWeight", "import androidx.compose.ui.text.font.FontWeight\nimport androidx.compose.ui.text.style.TextOverflow")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
