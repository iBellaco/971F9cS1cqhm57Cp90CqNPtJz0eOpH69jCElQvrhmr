import re

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Make surface and surfaceVariant semi-transparent in both dark and light schemes
dark_scheme = r'''surface = HextechSurface,
            onSurface = TextPrimary,
            surfaceVariant = HextechSurfaceVariant,'''
dark_scheme_new = r'''surface = HextechSurface.copy(alpha = 0.65f),
            onSurface = TextPrimary,
            surfaceVariant = HextechSurfaceVariant.copy(alpha = 0.65f),'''

light_scheme = r'''surface = HextechSurface,
            onSurface = TextPrimary,
            surfaceVariant = HextechSurfaceVariant,'''
light_scheme_new = r'''surface = HextechSurface.copy(alpha = 0.85f),
            onSurface = TextPrimary,
            surfaceVariant = HextechSurfaceVariant.copy(alpha = 0.85f),'''

# They are identical matches, let's just do a global replace for HextechSurface, and HextechSurfaceVariant in color scheme definitions
content = content.replace("surface = HextechSurface,", "surface = HextechSurface.copy(alpha = if (theme.isDark) 0.6f else 0.85f),")
content = content.replace("surfaceVariant = HextechSurfaceVariant,", "surfaceVariant = HextechSurfaceVariant.copy(alpha = if (theme.isDark) 0.6f else 0.85f),")

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'w', encoding='utf-8') as f:
    f.write(content)

