with open('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'r') as f:
    text = f.read()

import re
text = re.sub(r'isDark = true\n    \),\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n    \)', 'isDark = true\n    )', text)

text = re.sub(r'isDark = true\n    \),\n    \)\n\}', 'isDark = true\n    )\n}', text)

with open('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'w') as f:
    f.write(text)
print("Fixed comma")
