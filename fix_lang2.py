with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    c = f.read()

# Make sure English works!
c = c.replace('var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }',
'''var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }
    LaunchedEffect(selectedLanguage) {
        // Just trigger recompose
    }''')

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(c)
