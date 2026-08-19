with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    c = f.read()

import re
c = re.sub(
    r'MainDraftingScreen\([\s\S]*?onAutofillRoleChange = \{ autofillRole = it \}\s*\)',
    '''MainDraftingScreen(
                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
                    onNavigateToMeta = { currentScreen = AppScreen.META },
                    onNavigateToLogin = { currentScreen = AppScreen.LOGIN },
                    mainRole = mainRole,
                    onMainRoleChange = { mainRole = it },
                    secondRole = secondRole,
                    onSecondRoleChange = { secondRole = it },
                    autofillRole = autofillRole,
                    onAutofillRoleChange = { autofillRole = it },
                    currentLanguage = selectedLanguage,
                    onLanguageChange = { newLang ->
                        sharedPrefs.edit().putString("selected_language", newLang).apply()
                        selectedLanguage = newLang
                    }
                )''',
    c
)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(c)
