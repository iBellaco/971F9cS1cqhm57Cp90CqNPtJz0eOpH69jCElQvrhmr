import re

with open('app/src/main/java/com/example/ui/theme/Color.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Replace direct references with semi-transparent ones
content = re.sub(
    r'val HextechSurface: Color get\(\) = AppThemeManager\.currentTheme\.surface',
    r'val HextechSurface: Color get() = AppThemeManager.currentTheme.surface.copy(alpha = if (AppThemeManager.currentTheme.isDark) 0.65f else 0.85f)',
    content
)

content = re.sub(
    r'val HextechSurfaceVariant: Color get\(\) = AppThemeManager\.currentTheme\.surfaceVariant',
    r'val HextechSurfaceVariant: Color get() = AppThemeManager.currentTheme.surfaceVariant.copy(alpha = if (AppThemeManager.currentTheme.isDark) 0.65f else 0.85f)',
    content
)

# Also let's find `Color(0xFF0C1322)` in MetaAndDraftScreen and make it use HextechSurface
with open('app/src/main/java/com/example/ui/theme/Color.kt', 'w', encoding='utf-8') as f:
    f.write(content)

