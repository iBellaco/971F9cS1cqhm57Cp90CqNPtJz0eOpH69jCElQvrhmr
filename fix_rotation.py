import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """                // Recrear dinámicamente la ventana al girar para evitar corrupciones de Compose
                createFloatingOverlay()"""
replacement = """                // Ya no recreamos toda la ventana para evitar crashes (BadTokenException/WindowManager)
                // updateViewLayout será suficiente porque el ComposeView es responsive
                // createFloatingOverlay()"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Success Rotation Fix")
else:
    print("Target not found")
