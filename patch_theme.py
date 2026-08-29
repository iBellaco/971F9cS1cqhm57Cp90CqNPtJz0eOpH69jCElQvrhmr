import re

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'r') as f:
    text = f.read()

theme_logic_old = """    val theme = AppThemeManager.currentTheme
    val colorScheme = if (theme.isDark) {"""

theme_logic_new = """    val isPremium by com.example.util.SubscriptionManager.isPremium.androidx.lifecycle.compose.collectAsStateWithLifecycle(initialValue = false)
    val isSystemDark = isSystemInDarkTheme()
    
    // Si no es premium, forzamos sincronización con sistema y sobreescribimos el tema elegido
    val effectiveTheme = if (!isPremium) {
        if (isSystemDark) AppTheme.HEXTECH else AppTheme.LIGHT_HEXTECH
    } else {
        AppThemeManager.currentTheme
    }

    val colorScheme = if (effectiveTheme.isDark) {"""

text = text.replace(theme_logic_old, theme_logic_new)

# Add imports for collectAsStateWithLifecycle
if 'import androidx.lifecycle.compose.collectAsStateWithLifecycle' not in text:
    text = text.replace('import androidx.compose.runtime.Composable', 'import androidx.compose.runtime.Composable\nimport androidx.compose.runtime.getValue\nimport androidx.lifecycle.compose.collectAsStateWithLifecycle')

# Fix the collectAsStateWithLifecycle package
text = text.replace('.androidx.lifecycle.compose.collectAsStateWithLifecycle', '.collectAsStateWithLifecycle')

# Fix surface logic to use effectiveTheme
text = text.replace('theme.isDark', 'effectiveTheme.isDark')

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'w') as f:
    f.write(text)
