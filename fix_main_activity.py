with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

content = content.replace('    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {                    AnimatedContent(', '''    var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }
    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
        AnimatedContent(''')

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
