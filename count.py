import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

lines = content.split('\n')
depth = 0
for i, line in enumerate(lines):
    for char in line:
        if char == '{':
            depth += 1
        elif char == '}':
            depth -= 1
    if i >= 615 and i <= 630:
        print(f"{i+1}: {line} (depth={depth})")
