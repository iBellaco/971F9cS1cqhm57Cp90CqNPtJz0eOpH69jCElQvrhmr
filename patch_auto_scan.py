import re
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# 1. Add onManualEdit to FloatingDraftCoachView signature
text = re.sub(
    r'    onClearAll: \(\) -> Unit,\n    onGoToTierList: \(\) -> Unit',
    r'    onClearAll: () -> Unit,\n    onGoToTierList: () -> Unit,\n    onManualEdit: () -> Unit',
    text
)

# 2. Call onManualEdit inside onRemoveChampionForRole (allies)
text = re.sub(
    r'            onRemoveChampionForRole = \{ role ->\n                val roleIndex = defaultRoles\.indexOf\(role\)\n                if \(roleIndex in 0 until 5\) \{\n                    allies\[roleIndex\] = null\n                \}\n            \},',
    r'            onRemoveChampionForRole = { role ->\n                val roleIndex = defaultRoles.indexOf(role)\n                if (roleIndex in 0 until 5) {\n                    allies[roleIndex] = null\n                    onManualEdit()\n                }\n            },',
    text
)

# 3. Call onManualEdit inside onRemoveChampionForRole (enemies)
text = re.sub(
    r'            onRemoveChampionForRole = \{ role ->\n                val roleIndex = defaultRoles\.indexOf\(role\)\n                if \(roleIndex in 0 until 5\) \{\n                    enemies\[roleIndex\] = null\n                \}\n            \},',
    r'            onRemoveChampionForRole = { role ->\n                val roleIndex = defaultRoles.indexOf(role)\n                if (roleIndex in 0 until 5) {\n                    enemies[roleIndex] = null\n                    onManualEdit()\n                }\n            },',
    text
)

# 4. Pass onManualEdit in FloatingOverlayContent and disable auto scan
text = re.sub(
    r'                                            onClearAll = \{\n                                                 for \(i in 0 until 5\) \{\n                                                    allies\[i\] = null\n                                                    enemies\[i\] = null\n                                                \}\n                                                android\.widget\.Toast\.makeText\(context, "Equipos vaciados", android\.widget\.Toast\.LENGTH_SHORT\)\.show\(\)\n                                            \},\n                                            onGoToTierList = \{ overlayHubTab = OverlayHubTab\.TIER_LIST \}\n                                        \)',
    r'                                            onClearAll = {\n                                                 for (i in 0 until 5) {\n                                                    allies[i] = null\n                                                    enemies[i] = null\n                                                }\n                                                autoScanEnabled = false\n                                                android.widget.Toast.makeText(context, "Equipos vaciados", android.widget.Toast.LENGTH_SHORT).show()\n                                            },\n                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST },\n                                            onManualEdit = { autoScanEnabled = false }\n                                        )',
    text
)

# 5. Disable auto scan when opening champion picker
text = re.sub(
    r'onOpenChampionPicker = \{ isAlly, idx -> showChampionPickerForSlot = Pair\(isAlly, idx\) \},',
    r'onOpenChampionPicker = { isAlly, idx -> \n                                                autoScanEnabled = false\n                                                showChampionPickerForSlot = Pair(isAlly, idx) \n                                            },',
    text
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
