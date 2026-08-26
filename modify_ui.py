import re

files_to_modify = [
    {
        "path": "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt",
        "replacements": [
            ("text = stat,", "text = stat.parseHtmlColorToAnnotatedString(),"),
            ("package com.example.ui.screens", "package com.example.ui.screens\n\nimport com.example.utils.parseHtmlColorToAnnotatedString")
        ]
    },
    {
        "path": "app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt",
        "replacements": [
            ("text = stat,", "text = stat.parseHtmlColorToAnnotatedString(),"),
            ("package com.example.ui.screens", "package com.example.ui.screens\n\nimport com.example.utils.parseHtmlColorToAnnotatedString")
        ]
    },
    {
        "path": "app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt",
        "replacements": [
            ("Text(localizedStats,", "Text(localizedStats.parseHtmlColorToAnnotatedString(),"),
            ("package com.example.ui.components", "package com.example.ui.components\n\nimport com.example.utils.parseHtmlColorToAnnotatedString")
        ]
    }
]

for item in files_to_modify:
    path = item["path"]
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    
    for old_str, new_str in item["replacements"]:
        content = content.replace(old_str, new_str)
        
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)

print("UI files modified.")
