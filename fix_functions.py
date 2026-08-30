import re
with open('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'r') as f:
    text = f.read()

replacement = """    fun toggleLightDark(context: Context? = null) {
        // Now a no-op, since light mode is removed
    }

    fun getNavBarBackgroundColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.surface.copy(alpha = 0.65f)
        } else {
            currentNavBarOption.containerColor
        }
    }

    fun getNavBarAccentColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.primary
        } else {
            currentNavBarOption.accentColor
        }
    }
"""

text = re.sub(r'fun toggleLightDark\(context: Context\? = null\) \{\n\s*// Now a no-op, since light mode is removed\n\s*\}', replacement, text)

with open('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'w') as f:
    f.write(text)
print("Restored missing functions")
