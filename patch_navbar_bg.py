import re

with open('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace("currentTheme.surface", "currentTheme.surface.copy(alpha = 0.65f)")

with open('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'w', encoding='utf-8') as f:
    f.write(content)

