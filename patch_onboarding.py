import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

# Add the import for OnboardingScreen
if 'import com.example.ui.screens.OnboardingScreen' not in text:
    text = text.replace('import com.example.ui.screens.MainDraftingScreen', 'import com.example.ui.screens.MainDraftingScreen\nimport com.example.ui.screens.OnboardingScreen')

# Update the state initialization logic
init_logic_old = """    var isLanguageSet by remember { mutableStateOf(sharedPrefs.getBoolean("is_language_set", false)) }
    var currentScreen by remember { 
        mutableStateOf(if (isLanguageSet) AppScreen.MAIN else AppScreen.LANGUAGE_SELECTION) 
    }"""

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

text = text.replace(init_logic_old, init_logic_new)

# Update the onLanguageSelected logic to go to Onboarding instead of Main
on_lang_sel_old = """                            .apply()
                        isLanguageSet = true
                        selectedLanguage = langCode
                        currentScreen = AppScreen.MAIN"""

on_lang_sel_new = """                            .apply()
                        isLanguageSet = true
                        selectedLanguage = langCode
                        if (!hasSeenOnboarding) {
                            currentScreen = AppScreen.ONBOARDING
                        } else {
                            currentScreen = AppScreen.MAIN
                        }"""

text = text.replace(on_lang_sel_old, on_lang_sel_new)

# Add the ONBOARDING case to the when block
when_block = """            AppScreen.ONBOARDING -> {
                OnboardingScreen(
                    onFinish = {
                        sharedPrefs.edit().putBoolean("has_seen_onboarding", true).apply()
                        hasSeenOnboarding = true
                        currentScreen = AppScreen.MAIN
                    }
                )
            }"""

text = re.sub(r'AppScreen\.LANGUAGE_SELECTION -> \{', when_block + '\n            AppScreen.LANGUAGE_SELECTION -> {', text)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
