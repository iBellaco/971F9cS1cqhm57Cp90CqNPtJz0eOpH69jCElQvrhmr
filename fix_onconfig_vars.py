import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

text = text.replace("if (isCompactBubbleMode)", "if (overlayState.isCompactBubble)")
text = text.replace("if (isOverlayExpanded)", "if (overlayState.isExpanded)")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

