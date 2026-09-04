import re

file_path = 'app/src/main/java/com/example/ui/components/ChampionAvatar.kt'
with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

# Replace first block
content = content.replace(
    'val modelData: Any = if (parsedUrl.startsWith("file://")) {',
    'val modelData: Any = if (parsedUrl.startsWith("file:///android_asset/")) {\n                    parsedUrl\n                } else if (parsedUrl.startsWith("file://")) {'
)

# Replace second block
content = content.replace(
    'val modelData: Any? = if (parsedUrl.startsWith("file://")) {',
    'val modelData: Any? = if (parsedUrl.startsWith("file:///android_asset/")) {\n        parsedUrl\n    } else if (parsedUrl.startsWith("file://")) {'
)

with open(file_path, 'w', encoding='utf-8') as f:
    f.write(content)
