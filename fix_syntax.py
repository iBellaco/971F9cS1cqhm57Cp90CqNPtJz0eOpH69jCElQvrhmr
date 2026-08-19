with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    c = f.read()

# I accidentally added CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) { Surface to the imports
c = c.replace('import androidx.compose.material3.CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) { Surface', 'import androidx.compose.material3.Surface')

# And I duplicated it in the theme
c = c.replace('''MyApplicationTheme {
                CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) { Surface(''', '''MyApplicationTheme {
                Surface(''')

# We can just rely on the CompositionLocalProvider down below!
with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(c)
