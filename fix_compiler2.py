import re

# 1. CooldownTrackerPanel.kt
with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r') as f:
    content = f.read()
# Find all @OptIn(ExperimentalMaterial3Api::class) and remove duplicates
content = re.sub(r'(@OptIn\(ExperimentalMaterial3Api::class\)\s*)+', '@OptIn(ExperimentalMaterial3Api::class)\n', content)
with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w') as f:
    f.write(content)

# 2. FloatingAssistantOverlay.kt
with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

# Fix Icons.Default.Info to Icons.Default.Star
content = content.replace("Icons.Default.Info", "Icons.Default.Star")

# Add branches to when(selectedTab)
target = """                                    OverlayTab.SPELLS -> {
                                        OverlaySpellsTabContent(
                                            lockedChampion = lockedChampion,
                                            searchQuery = runeSearchQuery,
                                            onSearchChange = { runeSearchQuery = it },
                                            onSelectChampion = { lockedChampion = it },
                                            onClearChampion = { lockedChampion = null }
                                        )
                                    }
                                }
                            }"""
replacement = """                                    OverlayTab.SPELLS -> {
                                        OverlaySpellsTabContent(
                                            lockedChampion = lockedChampion,
                                            searchQuery = runeSearchQuery,
                                            onSearchChange = { runeSearchQuery = it },
                                            onSelectChampion = { lockedChampion = it },
                                            onClearChampion = { lockedChampion = null }
                                        )
                                    }
                                    OverlayTab.CD_TRACKER -> {
                                        CooldownTrackerPanel(modifier = Modifier.fillMaxSize(), isCompactOverlay = true)
                                    }
                                    OverlayTab.DAMAGE_MATH -> {
                                        DamagePenetrationCalculator(modifier = Modifier.fillMaxSize())
                                    }
                                }
                            }"""
content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)
