import re

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    content = f.read()

# Fix the duplicate in isPrimary block
content = content.replace(
'''                itemSwaps = champion.itemSwaps,
                itemSwaps = champion.itemSwaps,''', 
'''                itemSwaps = champion.itemSwaps,''')

# For all the alternate role blocks, they have:
# situationalItemsIcons = listOf( ... ),
# We need to insert `itemSwaps = emptyList(),` after `),`

# regex to find `situationalItemsIcons = listOf( ... ),`
# Note that they span multiple lines.
content = re.sub(r'(situationalItemsIcons = listOf\([^)]*\)\s*,)', r'\1\n                        itemSwaps = emptyList(),', content)

# I should also fix the one that uses `base.situationalItemsIcons,` or `alt.situationalItemsIcons,` if they exist.
# Wait, I didn't see base or alt in the output, maybe I overwrote it.

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(content)
