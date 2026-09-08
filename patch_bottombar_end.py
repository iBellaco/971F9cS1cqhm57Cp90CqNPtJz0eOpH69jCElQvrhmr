import sys

with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

target = """                if (AppThemeManager.isParticlesEnabled && isPremium) {
                    RunicNavBarParticleAnimation(
                        modifier = Modifier
                            .matchParentSize()
                            .clipToBounds(),
                        particleCount = 14,
                        accentColor = navAccent
                    )
                }
            }
        }
    ) { paddingValues ->"""

replacement = """                if (AppThemeManager.isParticlesEnabled && isPremium) {
                    RunicNavBarParticleAnimation(
                        modifier = Modifier
                            .matchParentSize()
                            .clipToBounds(),
                        particleCount = 14,
                        accentColor = navAccent
                    )
                }
            }
            }
        }
    ) { paddingValues ->"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
        f.write(content)
    print("Patched end of bottomBar in MainActivity.kt")
else:
    print("Target not found in MainActivity.kt (end)")

