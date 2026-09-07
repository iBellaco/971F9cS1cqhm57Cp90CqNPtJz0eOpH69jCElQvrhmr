import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

old_coach_sig = """    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    isPremium: Boolean
) {"""

new_coach_sig = """    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    isPremium: Boolean,
    onToggleDebug: () -> Unit
) {"""

content = content.replace(old_coach_sig, new_coach_sig)

old_buttons = """            Button(
                onClick = onClearAll,
                modifier = Modifier.weight(0.9f).height(28.dp),"""

new_buttons = """            Button(
                onClick = onToggleDebug,
                modifier = Modifier.weight(0.7f).height(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.HextechSurfaceVariant),
                border = BorderStroke(1.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.5f)),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
            ) {
                Text("🐛", fontSize = 10.sp)
            }
            
            Button(
                onClick = onClearAll,
                modifier = Modifier.weight(0.9f).height(28.dp),"""

content = content.replace(old_buttons, new_buttons)

old_view_sig = """    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    onManualEdit: () -> Unit
) {"""

new_view_sig = """    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    onManualEdit: () -> Unit,
    onToggleDebug: () -> Unit
) {"""

content = content.replace(old_view_sig, new_view_sig)

old_call = """            isSavedRecently = isSavedRecently,
            onClearAll = onClearAll,
            onGoToTierList = onGoToTierList,
            isPremium = isPremium
        )
    }"""

new_call = """            isSavedRecently = isSavedRecently,
            onClearAll = onClearAll,
            onGoToTierList = onGoToTierList,
            isPremium = isPremium,
            onToggleDebug = onToggleDebug
        )
    }"""

content = content.replace(old_call, new_call)

old_hub_call = """                                            onClearAll = { 
                                                for (i in 0 until 5) {
                                                    allies[i] = null
                                                    enemies[i] = null
                                                }
                                                state.enemyConfidences.clear()
                                                DraftVisionScanner.resetSlotMemory()
                                                android.widget.Toast.makeText(context, "Equipos vaciados", android.widget.Toast.LENGTH_SHORT).show()
                                            },
                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST }, onManualEdit = { autoScanEnabled = false }
                                        )"""

new_hub_call = """                                            onClearAll = { 
                                                for (i in 0 until 5) {
                                                    allies[i] = null
                                                    enemies[i] = null
                                                }
                                                state.enemyConfidences.clear()
                                                DraftVisionScanner.resetSlotMemory()
                                                android.widget.Toast.makeText(context, "Equipos vaciados", android.widget.Toast.LENGTH_SHORT).show()
                                            },
                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST }, onManualEdit = { autoScanEnabled = false },
                                            onToggleDebug = { showVisionDebugger.value = !showVisionDebugger.value }
                                        )"""

content = content.replace(old_hub_call, new_hub_call)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
