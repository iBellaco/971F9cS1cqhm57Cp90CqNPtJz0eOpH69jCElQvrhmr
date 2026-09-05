import re
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Let's find the start of the rogue coach content
start_marker = "        Spacer(modifier = Modifier.height(6.dp))\n        // 2. SELECTOR DE MI ROL / LÍNEA"
start_idx = text.find(start_marker)

# We want to move everything from start_idx up to the `    }\n}` that ends FloatingDraftCoachView.
# Since we know TierListTab comes next, we can find it.
tier_list_idx = text.find("@Composable\nprivate fun TierListTab")
# Between start_idx and tier_list_idx, we find the last `    }\n}\n`
end_idx = text.rfind("    }\n}\n", start_idx, tier_list_idx)

if start_idx != -1 and end_idx != -1:
    extracted_body = text[start_idx:end_idx]
    
    # We remove this body from FloatingDraftCoachView.
    # Instead, we just leave the CoachContent call.
    replacement = """                CoachContent(
                    activeRole = activeRole,
                    onActiveRoleChange = onActiveRoleChange,
                    isFirstPick = isFirstPick,
                    onFirstPickToggle = onFirstPickToggle,
                    analysis = analysis,
                    explicitEnemyOpponent = explicitEnemyOpponent,
                    onSelectChampion = onSelectChampion,
                    onSaveDraftClick = onSaveDraftClick,
                    isSavedRecently = isSavedRecently,
                    onClearAll = onClearAll,
                    onGoToTierList = onGoToTierList,
                    isPremium = isPremium
                )
"""
    
    text = text[:start_idx] + replacement + text[end_idx:]
    
    # Now we inject the extracted body inside CoachContent.
    # We find the empty CoachContent body:
    empty_coach = """@Composable
private fun CoachContent(
    activeRole: LaneRole,
    onActiveRoleChange: (LaneRole) -> Unit,
    isFirstPick: Boolean,
    onFirstPickToggle: () -> Unit,
    analysis: com.example.model.DraftAnalysisResult,
    explicitEnemyOpponent: Champion?,
    onSelectChampion: (Champion?) -> Unit,
    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    isPremium: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
    }
}"""
    filled_coach = empty_coach.replace("    }\n}", extracted_body + "\n    }\n}")
    
    text = text.replace(empty_coach, filled_coach)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

