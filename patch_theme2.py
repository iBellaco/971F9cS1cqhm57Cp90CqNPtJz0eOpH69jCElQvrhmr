import re

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'r') as f:
    text = f.read()

# Let's revert my previous Theme.kt patch and add LaunchedEffect
theme_logic_old = """    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle(initialValue = false)
    val isSystemDark = isSystemInDarkTheme()
    
    // Si no es premium, forzamos sincronización con sistema y sobreescribimos el tema elegido
    val effectiveTheme = if (!isPremium) {
        if (isSystemDark) AppTheme.HEXTECH else AppTheme.LIGHT_HEXTECH
    } else {
        AppThemeManager.currentTheme
    }

    val colorScheme = if (effectiveTheme.isDark) {"""

theme_logic_new = """    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle(initialValue = false)
    val isSystemDark = isSystemInDarkTheme()
    
    androidx.compose.runtime.LaunchedEffect(isSystemDark, isPremium) {
        if (!isPremium) {
            val forcedTheme = if (isSystemDark) AppTheme.HEXTECH else AppTheme.LIGHT_HEXTECH
            if (AppThemeManager.currentTheme != forcedTheme) {
                AppThemeManager.setTheme(forcedTheme, null)
            }
            AppThemeManager.setNavBarOption(NavBarColorOption.THEME_AUTO, null)
        }
    }

    val theme = AppThemeManager.currentTheme
    val colorScheme = if (theme.isDark) {"""

text = text.replace(theme_logic_old, theme_logic_new)
text = text.replace('effectiveTheme.isDark', 'theme.isDark')

with open('app/src/main/java/com/example/ui/theme/Theme.kt', 'w') as f:
    f.write(text)
