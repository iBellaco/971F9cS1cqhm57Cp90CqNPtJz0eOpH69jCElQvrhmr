import re

file_path = 'app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt'

with open(file_path, 'r') as f:
    content = f.read()

replacements = {
    # Keystones
    r'"https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png"': '"https://i.postimg.cc/HnyjzcL1/conqueror-hanu.png"',
    r'"https://wr-meta.com/uploads/posts/2025-07/1753444444_aery.webp"': '"https://i.postimg.cc/prFyChdH/aery-hanu.png"',
    r'"https://wr-meta.com/uploads/posts/2025-07/1753444445_phase-rush.webp"': '"https://i.postimg.cc/Hj1nhYrN/324314341234123-hanu-wr-bb.png"',
    # I don't know the old URLs for all keystones, so I'll regex replace them by id if possible.
}

for old, new in replacements.items():
    content = re.sub(old, new, content)

with open(file_path, 'w') as f:
    f.write(content)

