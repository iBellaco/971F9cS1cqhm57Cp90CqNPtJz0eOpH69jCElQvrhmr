import re

with open('app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt', 'r') as f:
    text = f.read()

# Replace .size(if (isOverlay) 34.dp else 44.dp) with .fillMaxWidth(0.85f).aspectRatio(1f)
text = text.replace(
    ".size(if (isOverlay) 34.dp else 44.dp)",
    ".fillMaxWidth(0.85f)\n                                .aspectRatio(1f)"
)

# Also let's shrink the Role Icon in overlay mode so it doesn't cause overflow.
# The icon is currently .size(28.dp). Let's make it adaptive or smaller.
text = text.replace(
    ".size(28.dp)",
    ".size(if (isOverlay) 20.dp else 28.dp)"
)

with open('app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt', 'w') as f:
    f.write(text)

