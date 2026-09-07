import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target1 = """    onGoToTierList: () -> Unit,
    isPremium: Boolean,
    onToggleDebug: () -> Unit
) {"""

replacement1 = """    onGoToTierList: () -> Unit,
    isPremium: Boolean
) {"""

content = content.replace(target1, replacement1)

target2 = """    onGoToTierList: () -> Unit,
    onManualEdit: () -> Unit,
    onToggleDebug: () -> Unit
) {"""

replacement2 = """    onGoToTierList: () -> Unit,
    onManualEdit: () -> Unit
) {"""

content = content.replace(target2, replacement2)

target3 = """            onClearAll = onClearAll,
            onGoToTierList = onGoToTierList,
            isPremium = isPremium,
            onToggleDebug = onToggleDebug
        )"""

replacement3 = """            onClearAll = onClearAll,
            onGoToTierList = onGoToTierList,
            isPremium = isPremium
        )"""

content = content.replace(target3, replacement3)

target4 = """                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST }, onManualEdit = { autoScanEnabled = false },
                                            onToggleDebug = { showVisionDebugger.value = !showVisionDebugger.value }
                                        )"""

replacement4 = """                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST }, onManualEdit = { autoScanEnabled = false }
                                        )"""

content = content.replace(target4, replacement4)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
