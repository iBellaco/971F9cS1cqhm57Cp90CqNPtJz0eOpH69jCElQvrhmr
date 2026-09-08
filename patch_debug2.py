import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """                    Box(modifier = Modifier.fillMaxWidth().weight(1f)) {"""

if target in content:
    print("Found single line target")
else:
    print("Not found single line target")
    
