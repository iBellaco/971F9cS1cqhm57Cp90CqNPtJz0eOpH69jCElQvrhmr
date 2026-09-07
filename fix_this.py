import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

content = content.replace("updateOverlayRect(params, isOverlayExpanded)", "this@FloatingAssistantService.updateOverlayRect(params, isOverlayExpanded)")
content = content.replace("updateOverlayRect(floatingParams!!, isOverlayExpanded)", "updateOverlayRect(floatingParams!!, isOverlayExpanded)") # wait 651 is where?

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
print("Patched this")
