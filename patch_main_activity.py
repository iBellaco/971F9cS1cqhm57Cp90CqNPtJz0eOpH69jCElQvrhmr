import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

if 'import com.example.util.LocalLanguage' not in content:
    content = content.replace('import androidx.compose.ui.Modifier', 'import androidx.compose.ui.Modifier\nimport com.example.util.LocalLanguage\nimport androidx.compose.runtime.CompositionLocalProvider')

# Find the AnimatedContent and wrap it
if 'CompositionLocalProvider(LocalLanguage provides currentLang)' not in content:
    # First, let's derive currentLang from sharedPrefs
    if 'val currentScreen by' not in content:
        # need to inject state
        pass
    
    # Actually we can just read the language from sharedPrefs during compose
    content = content.replace('val sharedPrefs = context.getSharedPreferences', 
'''val sharedPrefs = context.getSharedPreferences("wildrift_prefs", Context.MODE_PRIVATE)
            
            var selectedLanguage by remember { 
                mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") 
            }
            // Remove the duplicate val sharedPrefs declaration
''')
    
    content = content.replace('val sharedPrefs = context.getSharedPreferences("wildrift_prefs", Context.MODE_PRIVATE)', '')
    content = content.replace('// Remove the duplicate val sharedPrefs declaration', 'val sharedPrefs = context.getSharedPreferences("wildrift_prefs", Context.MODE_PRIVATE)')
    
    content = content.replace('AnimatedContent(', '''CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
                    AnimatedContent(''')
                    
    content = content.replace('                        currentScreen = AppScreen.MAIN\n                    }\n                )', '''                        selectedLanguage = langCode
                        currentScreen = AppScreen.MAIN
                    }
                )''')

    # Close the CompositionLocalProvider block
    content = content.replace('        }\n    }\n}', '''        }\n    }\n}\n}''')

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
