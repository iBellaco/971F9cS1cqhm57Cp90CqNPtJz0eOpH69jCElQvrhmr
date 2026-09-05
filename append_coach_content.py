import re

with open('/tmp/coach_view.kt', 'r') as f:
    lines = f.readlines()

# Extract from "        // 2. SELECTOR DE MI ROL / LÍNEA" to the end.
start_idx = 0
for i, line in enumerate(lines):
    if "SELECTOR DE MI ROL" in line:
        start_idx = i
        break

# The previous line might be a Spacer.
if start_idx > 0 and "Spacer" in lines[start_idx-1]:
    start_idx -= 1

body_lines = lines[start_idx:]
# The last 2 lines in coach_view.kt are probably `    }\n}` which closes FloatingDraftCoachView.
# We want to close CoachContent, so we'll just use body_lines, but we might need to adjust the very end.
# Actually, the last closing brackets correspond to the if (isPremium) block or the LazyColumn block. Let's just use it and check the compiler syntax errors.

# Write it out to a file, wrap it in CoachContent signature.
signature = """
}

@Composable
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
"""

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'a') as f:
    f.write(signature)
    f.writelines(body_lines)
    # The body_lines has `    }\n}` at the end which originally closed FloatingDraftCoachView.
    # Because we opened CoachContent with `{`, those two brackets will close the Column (if we add one) or just close CoachContent.
    # Wait, in the new signature, we don't have a Column. Let's add it.
    # Actually, extracted_body wasn't wrapped in a Column.
    # Let's wrap it.
    
