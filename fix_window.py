with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

import re

# Find the Card in FloatingOverlayContent
old_card = """                Card(
                    modifier = Modifier
                        .widthIn(min = 300.dp, max = 340.dp)
                        .height(530.dp)
                        .clip(RoundedCornerShape(16.dp)),"""

new_card = """                Card(
                    modifier = Modifier
                        .widthIn(min = if (isLandscapeMode) 520.dp else 300.dp, max = if (isLandscapeMode) 560.dp else 340.dp)
                        .height(if (isLandscapeMode) 380.dp else 530.dp)
                        .clip(RoundedCornerShape(16.dp)),"""

if old_card in text:
    text = text.replace(old_card, new_card)
    print("Replaced Card modifier")
else:
    print("Could not find Card modifier")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
