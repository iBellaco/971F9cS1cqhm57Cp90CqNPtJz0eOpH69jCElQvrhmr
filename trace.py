import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

lines = content.split('\n')
depth = 0
for i, line in enumerate(lines):
    delta = line.count('{') - line.count('}')
    old_depth = depth
    depth += delta
    if delta != 0 or 'if' in line or 'else' in line:
        print(f"{i+1:3d}: {old_depth}->{depth} {line.strip()}")
