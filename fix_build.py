import os
import re

# Fix 1: Import ItemSwap in Champions
directories = ['app/src/main/java/com/example/data/champions']
for d in directories:
    for filename in os.listdir(d):
        if filename.endswith("Champions.kt"):
            filepath = os.path.join(d, filename)
            with open(filepath, 'r') as f:
                content = f.read()
            if 'import com.example.model.ItemSwap' not in content:
                content = content.replace('import com.example.model.LaneRole', 'import com.example.model.LaneRole\nimport com.example.model.ItemSwap')
            with open(filepath, 'w') as f:
                f.write(content)

# Fix 2: TextAlign in ChampionDetailSheet.kt
with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    content = f.read()
if 'import androidx.compose.ui.text.style.TextAlign' not in content:
    content = content.replace('import androidx.compose.ui.text.font.FontWeight', 'import androidx.compose.ui.text.font.FontWeight\nimport androidx.compose.ui.text.style.TextAlign')
with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(content)

# Fix 3: ChampionRoleAdapter.kt
with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    content = f.read()
    
# It seems there is a compile error: "Argument already passed for this parameter." around line 53
# And missing itemSwaps in lots of lines.
# Let's fix ChampionRoleAdapter entirely by doing a smart regex replace.
content = re.sub(r'situationalItemsIcons = (.*?)situationalItemsIcons,?\s*itemSwaps = (.*?)itemSwaps,?', r'situationalItemsIcons = \1situationalItemsIcons,', content)

content = re.sub(r'situationalItemsIcons = (.*?)situationalItemsIcons,?', r'situationalItemsIcons = \1situationalItemsIcons,\n                itemSwaps = \1itemSwaps,', content)

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(content)
