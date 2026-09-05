import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# I want to read from line 720 to 800
lines = text.split('\n')
for i in range(718, min(785, len(lines))):
    print(f"{i+1}: {lines[i]}")
