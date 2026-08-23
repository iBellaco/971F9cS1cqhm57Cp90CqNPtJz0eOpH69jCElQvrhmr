with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    lines = f.readlines()

new_lines = []
skip = False
for i, line in enumerate(lines):
    if 'id = "pathfinder"' in line:
        # We want to skip from the line before (RuneItem() down to the end of future market
        # Actually it's easier to just skip while "Inspiración" is the category or just delete the 3 elements
        pass

import re
with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    text = f.read()

# Match from RuneItem(id="pathfinder") up to the last parenthesis of future_market
text = re.sub(r'RuneItem\(\s*id = "pathfinder".*?Mercado del Futuro".*?\)\s*,?', '', text, flags=re.DOTALL)
text = text.replace('        \n    )', '    )')

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(text)
