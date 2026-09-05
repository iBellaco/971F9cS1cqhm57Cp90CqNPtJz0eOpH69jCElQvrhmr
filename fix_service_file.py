import os

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    service_content = f.read()

# Strip any trailing `}` or empty lines, we will close FloatingDraftCoachView properly.
service_content = service_content.rstrip()
if service_content.endswith('}'):
    service_content = service_content[:-1].rstrip()
# Actually, let's just find where CoachContent is called.
coach_call_idx = service_content.rfind("        CoachContent(")
if coach_call_idx != -1:
    end_of_call = service_content.find(")", coach_call_idx) + 1
    service_content = service_content[:end_of_call]
    service_content += "\n    }\n}\n"

with open('/tmp/coach_view.kt', 'r') as f:
    lines = f.readlines()

start_idx = 0
for i, line in enumerate(lines):
    if "SELECTOR DE MI ROL" in line:
        start_idx = i
        break
if start_idx > 0 and "Spacer" in lines[start_idx-1]:
    start_idx -= 1

body = "".join(lines[start_idx:])
# body has trailing `    }\n}` which closed the original FloatingDraftCoachView.
# We will use that to close the `Column` and `CoachContent`.

signature = """
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
    Column(modifier = Modifier.fillMaxWidth()) {
"""

service_content += signature + body

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(service_content)

