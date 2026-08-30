import re
with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'r') as f:
    text = f.read()

target = """        if (!isPremium) {
            val forcedTheme = if (isSystemDark) AppTheme.HEXTECH else AppTheme.LIGHT_HEXTECH
            if (AppThemeManager.currentTheme != forcedTheme) {"""

replacement = """        if (!isPremium) {
            val forcedTheme = AppTheme.HEXTECH
            if (AppThemeManager.currentTheme != forcedTheme) {"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'w') as f:
    f.write(text)
print("Updated Theme.kt")
