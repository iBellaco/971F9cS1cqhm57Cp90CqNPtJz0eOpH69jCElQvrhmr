import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

import re
match = re.search(r'updateOverlayRect.*', content)
print("updateOverlayRect called?", "updateOverlayRect" in content)
