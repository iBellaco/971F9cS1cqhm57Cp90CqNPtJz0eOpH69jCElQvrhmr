import re

file_path = "app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

content = content.replace(
    'com.example.ui.components.FormattedWildRiftText(\n                                        text = stat.parseHtmlColorToAnnotatedString(),',
    'Text(\n                                        text = stat.parseHtmlColorToAnnotatedString(),'
)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
