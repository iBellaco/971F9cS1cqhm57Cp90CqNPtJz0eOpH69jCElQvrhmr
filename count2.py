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
    if "if (selectedCategory" in line or "} else {" in line or "LazyVerticalGrid" in line or "items(" in line:
        print(f"{i+1}: {line.strip()} (depth={depth})")
