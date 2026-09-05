import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# I need to wrap everything after `Spacer(modifier = Modifier.height(6.dp))\n        // 2. SELECTOR DE MI ROL / LÍNEA` 
# up to the end of FloatingDraftCoachView into CoachContent.
# And replace it in the original string.

# Wait, the current file contains CoachContent() call inside LandscapeVerticalTeamList, but it's not defined!
# Also, FloatingDraftCoachView is totally broken now because it still has all the old code.

