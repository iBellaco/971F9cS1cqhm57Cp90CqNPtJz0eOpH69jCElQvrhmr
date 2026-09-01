with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

bad_str = """            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
        }"""

good_str = """            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
            else -> {}
        }"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
