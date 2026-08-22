import re
with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    content = f.read()

content = content.replace(
'''                        itemSwaps = emptyList(),
                        itemSwaps = emptyList(),''', 
'''                        itemSwaps = emptyList(),''')

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(content)
