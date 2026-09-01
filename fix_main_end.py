import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

# Fix the end of the when block
bad_str = """                )
            }
            AppScreen.META -> {}) }
            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
            else -> {}
        }
    }
}
}"""

good_str = """                )
            }
            AppScreen.META -> {}
            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
        }
    }
}"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
