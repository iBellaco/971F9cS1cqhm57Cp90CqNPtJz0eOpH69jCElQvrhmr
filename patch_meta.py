import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace("Color(0xFF141926)", "HextechSurfaceVariant")
content = content.replace("Color(0xFF0C1322)", "HextechSurface")

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w', encoding='utf-8') as f:
    f.write(content)

