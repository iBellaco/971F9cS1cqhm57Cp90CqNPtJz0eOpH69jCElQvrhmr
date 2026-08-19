import os

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

new_compose_content = """            setContent {
                val sharedPrefs = remember { getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
                // Use a mutable state and update it by observing SharedPreferences
                var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }

                androidx.compose.runtime.DisposableEffect(sharedPrefs) {
                    val listener = android.content.SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
                        if (key == "selected_language") {
                            selectedLanguage = prefs.getString(key, "es") ?: "es"
                        }
                    }
                    sharedPrefs.registerOnSharedPreferenceChangeListener(listener)
                    onDispose {
                        sharedPrefs.unregisterOnSharedPreferenceChangeListener(listener)
                    }
                }

                androidx.compose.runtime.CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) {"""

content = content.replace("""            setContent {
                val sharedPrefs = remember { getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
                val selectedLanguage = remember { sharedPrefs.getString("selected_language", "es") ?: "es" }

                androidx.compose.runtime.CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) {""", new_compose_content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
print("Service patched!")
