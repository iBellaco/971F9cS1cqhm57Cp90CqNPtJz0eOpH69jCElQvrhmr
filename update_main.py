import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'enum class AppScreen {\n    ONBOARDING,\n    LOGIN,\n    LANGUAGE_SELECTION,\n    MAIN,\n    INFO,\n    META\n}',
    'enum class AppScreen {\n    ONBOARDING,\n    LOGIN,\n    LANGUAGE_SELECTION,\n    MAIN,\n    INFO,\n    META,\n    TUTORIAL\n}'
)

content = content.replace(
    'import com.example.ui.screens.MetaAndDraftScreen',
    'import com.example.ui.screens.MetaAndDraftScreen\nimport com.example.ui.screens.TutorialScreen'
)

# In the AnimatedContent block
content = content.replace(
'''                AppScreen.INFO -> {
                    InfoScreen(
                        onNavigateBack = { currentScreen = AppScreen.MAIN }
                    )
                }''',
'''                AppScreen.INFO -> {
                    InfoScreen(
                        onNavigateBack = { currentScreen = AppScreen.MAIN }
                    )
                }
                AppScreen.TUTORIAL -> {
                    TutorialScreen(
                        onFinish = { currentScreen = AppScreen.MAIN }
                    )
                }'''
)

# In MainDraftingScreen definition in MainActivity
content = content.replace(
'''                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
                    onNavigateToLogin = { currentScreen = AppScreen.LOGIN },''',
'''                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
                    onNavigateToLogin = { currentScreen = AppScreen.LOGIN },
                    onNavigateToTutorial = { currentScreen = AppScreen.TUTORIAL },'''
)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
