import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Define the CoachContent function
coach_content_header = """@Composable
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
"""

coach_content_footer = """    }
}
"""

# We need to extract the code starting from `Spacer(modifier = Modifier.height(6.dp))\n        // 2. SELECTOR DE MI ROL / LÍNEA`
start_marker = "        Spacer(modifier = Modifier.height(6.dp))\n        // 2. SELECTOR DE MI ROL / LÍNEA"
# End marker is the end of FloatingDraftCoachView (two closing braces). Let's use string find.
end_marker = "    }\n}\n"

start_idx = text.find(start_marker)
# Find the end of FloatingDraftCoachView. 
# Let's find the string `    }\n}\n@Composable\nprivate fun TierListTab` or just manually extract it.

# I will use a simple split and replace to remove the chunk and create the new composable.
# Let's do it using Python properly.
