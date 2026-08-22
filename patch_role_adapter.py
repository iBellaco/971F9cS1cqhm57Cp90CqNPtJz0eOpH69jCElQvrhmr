import re

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    content = f.read()

content = content.replace('situationalItemsIcons = base.situationalItemsIcons', 'situationalItemsIcons = base.situationalItemsIcons, itemSwaps = base.itemSwaps')
content = content.replace('situationalItemsIcons = alt.situationalItemsIcons', 'situationalItemsIcons = alt.situationalItemsIcons, itemSwaps = alt.itemSwaps')

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(content)
