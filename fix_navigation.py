with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

# Replace the specific block of AppScreen.MAIN -> { ... } and AppScreen.META -> { ... }
start_main = content.find("AppScreen.MAIN -> {")
start_info = content.find("AppScreen.INFO -> {")

if start_main != -1 and start_info != -1:
    main_block = content[start_main:start_info]
    
    new_main_block = '''AppScreen.MAIN -> {
                DashboardScreen(
                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
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
                )
            }
            '''
    content = content.replace(main_block, new_main_block)

start_meta = content.find("AppScreen.META -> {")
if start_meta != -1:
    # Find the end of the meta block. It ends with a closing brace for the when.
    end_meta = content.find("}\n        }\n    }", start_meta)
    meta_block = content[start_meta:end_meta]
    content = content.replace(meta_block, "")

with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
    f.write(content)

