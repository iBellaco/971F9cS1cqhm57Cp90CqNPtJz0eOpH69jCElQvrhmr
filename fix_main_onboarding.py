import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

init_logic_old = r'    var isLanguageSet by remember \{ mutableStateOf\(sharedPrefs\.getBoolean\("is_language_set", false\)\) \}\s+var currentScreen by remember \{\s+mutableStateOf\(if \(isLanguageSet\) AppScreen\.MAIN else AppScreen\.LANGUAGE_SELECTION\) \s+\}'

init_logic_new = """    var isLanguageSet by remember { mutableStateOf(sharedPrefs.getBoolean("is_language_set", false)) }
    var hasSeenOnboarding by remember { mutableStateOf(sharedPrefs.getBoolean("has_seen_onboarding", false)) }
    var currentScreen by remember { 
        mutableStateOf(
            when {
                !isLanguageSet -> AppScreen.LANGUAGE_SELECTION
                !hasSeenOnboarding -> AppScreen.ONBOARDING
                else -> AppScreen.MAIN
            }
        ) 
    }"""

text = re.sub(init_logic_old, init_logic_new, text)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
