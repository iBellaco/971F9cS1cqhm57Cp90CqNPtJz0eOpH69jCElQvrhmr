import re

with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

# We need to find `CompositionLocalProvider(LocalLanguage provides selectedLanguage) {`
# and move it to be higher up, OR we just wrap the dialogs in `CompositionLocalProvider`.

# Let's wrap AppUpdateDialog and WelcomePatchDialog in their own CompositionLocalProvider if needed,
# or just move the global one.
# It's easier to just replace:
#    activeUpdateInfo?.let { update ->
#        AppUpdateDialog(...)
#    }
#
# with:
#    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
#        activeUpdateInfo?.let { update -> ... }
#        if (isLanguageSet && currentScreen == AppScreen.MAIN) { ... }
#    }

target_block = """
    // Modal de Alerta de Actualización Disponible con opción de descarga directa
    activeUpdateInfo?.let { update ->
        AppUpdateDialog(
            updateInfo = update,
            onDismiss = { AppUpdateManager.dismissAlert() }
        )
    }

    if (isLanguageSet && currentScreen == AppScreen.MAIN) {
        com.example.ui.components.WelcomePatchDialog(
            onDismiss = { /* do nothing, handles its own state */ }
        )
    }
"""

replacement_block = """
    var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }
    LaunchedEffect(selectedLanguage) {
        // Just trigger recompose
    }

    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
        // Modal de Alerta de Actualización Disponible con opción de descarga directa
        activeUpdateInfo?.let { update ->
            AppUpdateDialog(
                updateInfo = update,
                onDismiss = { AppUpdateManager.dismissAlert() }
            )
        }

        if (isLanguageSet && currentScreen == AppScreen.MAIN) {
            com.example.ui.components.WelcomePatchDialog(
                onDismiss = { /* do nothing, handles its own state */ }
            )
        }
"""

# And remove the original selectedLanguage declaration
content = content.replace(target_block, replacement_block)

original_selected = """
    var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }
    LaunchedEffect(selectedLanguage) {
        // Just trigger recompose
    }
    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
"""

content = content.replace(original_selected, "\n")
content = content.replace("    } // Fin de setContent", "        }\n    } // Fin de setContent")

with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
    f.write(content)
