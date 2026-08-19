with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    c = f.read()
c = c.replace('CompositionLocalProvider(LocalLanguage provides selectedLanguage) {', 
'''var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }
    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {''')
with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(c)
