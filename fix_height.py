import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

text = text.replace('val cardHeightPx = ((if (isLandscape) 360 else 520) * density).toInt()', 
                    'val cardHeightPx = ((if (isLandscape) 390 else 520) * density).toInt()')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
