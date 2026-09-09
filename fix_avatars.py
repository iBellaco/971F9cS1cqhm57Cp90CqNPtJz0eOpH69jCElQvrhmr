import re

with open("app/src/main/java/com/example/data/AvatarCatalog.kt", "r") as f:
    content = f.read()

def repl(match):
    id_val = match.group(1)
    return 'imageUrl = "file:///android_asset/champions/' + id_val + '.png"'

# Find blocks where id is set and then imageUrl is set
# Actually, let's just replace all ddragon champion URLs
# wait, each AvatarItem block has an id and an imageUrl.
# Since we just want to replace imageUrl based on id:
import sys
lines = content.split('\n')
current_id = None
for i in range(len(lines)):
    if 'id = "' in lines[i]:
        current_id = lines[i].split('id = "')[1].split('"')[0]
    if 'imageUrl = "https://ddragon.leagueoflegends.com/cdn/' in lines[i] and 'champion' in lines[i]:
        if current_id:
            lines[i] = re.sub(r'imageUrl = ".*?"', 'imageUrl = "file:///android_asset/champions/' + current_id + '.png"', lines[i])

with open("app/src/main/java/com/example/data/AvatarCatalog.kt", "w") as f:
    f.write('\n'.join(lines))
