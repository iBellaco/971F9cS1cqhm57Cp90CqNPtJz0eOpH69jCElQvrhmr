import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

target = """                        onLanguageChange = onLanguageChange
                    )
                }
            }
        }
    }
}"""
replacement = """                        onLanguageChange = onLanguageChange
                    )
                }
            }
            } // HorizontalPager
        }
    }
}"""
text = text.replace(target, replacement)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
