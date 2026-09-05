import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

text = text.replace('var bubbleSizePx = (46 * density).toInt()', 'var bubbleSizePx = (46 * density).toInt() // local')
text = text.replace('var isOverlayExpanded = false', 'isOverlayExpanded = false')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
