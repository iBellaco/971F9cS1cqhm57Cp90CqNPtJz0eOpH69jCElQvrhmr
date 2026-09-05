import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

start_marker = "        Spacer(modifier = Modifier.height(6.dp))\n        // 2. SELECTOR DE MI ROL / LÍNEA"
end_marker = "    }\n}\n"

start_idx = text.find(start_marker)

# We want to find the first \n}\n} after the start_idx
# There might be multiple \n}\n} in the file, but we specifically want the one that ends FloatingDraftCoachView.
# A safe way is to find the next @Composable, which is after FloatingDraftCoachView, or just search backwards from the next composable.
next_comp_idx = text.find("@Composable", start_idx)

if next_comp_idx == -1:
    end_idx = text.rfind(end_marker)
else:
    end_idx = text.rfind(end_marker, start_idx, next_comp_idx)

extracted_chunk = text[start_idx:end_idx + len("    }\n")]

# Define CoachContent
coach_content_wrapper = """@Composable
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
""" + extracted_chunk.replace("        Spacer(modifier", "        Spacer(modifier") + """    }
}
"""

# Replace the chunk in the original text with a call to CoachContent
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
    }
}"""

# Actually, the original text had `    }\n}` which closed the main Column and FloatingDraftCoachView.
# So I should replace `extracted_chunk + "}\n"` with `replacement`.

text = text[:start_idx] + replacement + "\n" + coach_content_wrapper + text[end_idx + len("    }\n"):]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

