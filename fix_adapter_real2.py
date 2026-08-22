import re

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    content = f.read()

# Add emptyList for itemSwaps where situationalItemsIcons exist in alt profiles
content = re.sub(r'(situationalItemsIcons = listOf\([^)]+\)\s*,\n)', r'\1                        itemSwaps = emptyList(),\n', content)

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(content)
