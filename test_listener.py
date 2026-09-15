import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

print("deletedIds used:")
lines = content.split('\n')
for i, line in enumerate(lines):
    if 'deletedIds' in line:
        print(f"{i+1}: {line.strip()}")
