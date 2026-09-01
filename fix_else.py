with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

bad_str = """            AppScreen.META -> {}
            else -> {}
            AppScreen.TUTORIAL -> { TutorialScreen(onFinish = { currentScreen = AppScreen.MAIN }) }
            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
        }"""

good_str = """            AppScreen.META -> {}
            AppScreen.TUTORIAL -> { TutorialScreen(onFinish = { currentScreen = AppScreen.MAIN }) }
            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
        }"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
