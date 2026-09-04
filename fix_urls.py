import os
import re

# We need to change "file:///android_asset/offline_images/NAME.png" 
# back to the community dragon or ddragon URLs based on the file content.

# 1. WildRiftItemsData.kt -> Mostly items (ddragon or raw.communitydragon)
with open("app/src/main/java/com/example/data/WildRiftItemsData.kt", "r") as f:
    items_content = f.read()
items_content = re.sub(r'file:///android_asset/offline_images/(\d+)\.png', r'https://ddragon.leagueoflegends.com/cdn/14.4.1/img/item/\1.png', items_content)
# some items might have names instead of numbers but in WR mostly numbers. 
# We'll just generic replace anything left to communitydragon
items_content = re.sub(r'file:///android_asset/offline_images/([a-zA-Z0-9_-]+)\.png', r'https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/assets/items/icons2d/\1.png', items_content)

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt", "w") as f:
    f.write(items_content)

# 2. WildRiftSpellsAndRunes.kt -> Spells and Runes
with open("app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt", "r") as f:
    spells_content = f.read()

# For spells: file:///android_asset/offline_images/SummonerFlash.png -> http://ddragon...
# Actually we can just point all spells to ddragon spell folder and all runes to perk-images
def spell_rune_repl(match):
    filename = match.group(1)
    if "Summoner" in filename:
        return f"https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/{filename}"
    else:
        # Runes: we need a generic path, or just use the generic community dragon perk images path
        # It's safer to use raw.communitydragon for runes
        # But wait, earlier they were all specific paths. Let's just use a generic fallback for now
        # Actually, let's just point them to community dragon perks
        return f"https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/{filename}"

spells_content = re.sub(r'file:///android_asset/offline_images/([a-zA-Z0-9_-]+\.(?:png|jpg))', spell_rune_repl, spells_content)

with open("app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt", "w") as f:
    f.write(spells_content)
    
# 3. AvatarCatalog.kt
with open("app/src/main/java/com/example/data/AvatarCatalog.kt", "r") as f:
    avatar_content = f.read()
avatar_content = re.sub(r'file:///android_asset/offline_images/(\d+)\.jpg', r'https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/\1.jpg', avatar_content)
with open("app/src/main/java/com/example/data/AvatarCatalog.kt", "w") as f:
    f.write(avatar_content)

print("URL replacement applied heuristically.")
