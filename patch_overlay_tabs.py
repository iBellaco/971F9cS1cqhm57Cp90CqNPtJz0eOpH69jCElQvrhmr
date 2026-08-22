import re

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

tabs_target = """enum class OverlayTab(val title: String, val icon: @Composable () -> Unit) {
    DRAFT("Draft", { Icon(Icons.Default.FlashOn, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    OBJECTIVES("Objetivos", { Icon(Icons.Default.Alarm, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    ITEMS("Objetos", { Icon(Icons.Default.Shield, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    RUNES("Runas", { Icon(Icons.Default.AutoFixHigh, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    SPELLS("Hechizos", { Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp)) })
}"""
tabs_replacement = """enum class OverlayTab(val title: String, val icon: @Composable () -> Unit) {
    DRAFT("Draft", { Icon(Icons.Default.FlashOn, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    OBJECTIVES("Objetivos", { Icon(Icons.Default.Alarm, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    ITEMS("Objetos", { Icon(Icons.Default.Shield, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    RUNES("Runas", { Icon(Icons.Default.AutoFixHigh, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    SPELLS("Hechizos", { Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    CD_TRACKER("CD Tracker", { Icon(Icons.Default.Timer, contentDescription = null, modifier = Modifier.size(16.dp)) }),
    DAMAGE_MATH("Daño", { Icon(Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(16.dp)) })
}"""
content = content.replace(tabs_target, tabs_replacement)

# Also we need to make sure the scrollable row of tabs can handle it.
# It uses TabRow with edgePadding, but let's check how it's rendered.
# "ScrollableTabRow" is used. Let's see:

switch_target = """                                    OverlayTab.SPELLS -> {
                                        OverlaySpellsTabContent(
                                            onDismiss = onDismiss
                                        )
                                    }
                                }
                            }"""
switch_replacement = """                                    OverlayTab.SPELLS -> {
                                        OverlaySpellsTabContent(
                                            onDismiss = onDismiss
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
content = content.replace(switch_target, switch_replacement)

# Also update the myChampion logic in OverlayDraftTabContent if we have it.
with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)
