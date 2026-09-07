import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    lines = f.readlines()

for i, line in enumerate(lines[2850:2920]):
    print(f"{i+2850+1}: {line}", end="")
