import re

# 1. Fix DraftTeamPositionCard.kt
with open('app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt', 'r') as f:
    card = f.read()

if 'import androidx.compose.foundation.layout.aspectRatio' not in card:
    card = card.replace(
        'import androidx.compose.foundation.layout.width',
        'import androidx.compose.foundation.layout.width\nimport androidx.compose.foundation.layout.aspectRatio'
    )
with open('app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt', 'w') as f:
    f.write(card)

# 2. Fix FloatingAssistantService.kt
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    service = f.read()

service = service.replace('@Composable\n@Composable\nprivate fun FloatingDraftCoachView', '@Composable\nprivate fun FloatingDraftCoachView')

# Pass allies and enemies to CoachContent
service = service.replace(
    "private fun CoachContent(",
    "private fun CoachContent(\n    allies: List<com.example.model.Champion?>,\n    enemies: List<com.example.model.Champion?>,"
)

service = service.replace(
    "        CoachContent(\n            activeRole = activeRole,",
    "        CoachContent(\n            allies = allies,\n            enemies = enemies,\n            activeRole = activeRole,"
)

# And replace `allies.clear()` with `onClearAll()` inside the body of CoachContent? No wait, inside coach_view it had:
service = service.replace("allies.clear()\n                    enemies.clear()\n                    onManualEdit()", "onClearAll()")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(service)

