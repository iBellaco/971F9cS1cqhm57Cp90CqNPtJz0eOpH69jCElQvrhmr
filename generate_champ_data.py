import json, re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt') as f:
    item_text = f.read()

item_matches = re.findall(r'WildRiftItem\s*\(\s*id\s*=\s*\"([^\"]+)\",\s*name\s*=\s*\"([^\"]+)\",\s*nameEn\s*=\s*\"([^\"]+)\",.*?iconUrl\s*=\s*\"([^\"]+)\"', item_text, re.DOTALL)

item_icon_map = {}
for mid, name, nameEn, icon in item_matches:
    item_icon_map[name.strip()] = icon
    item_icon_map[nameEn.strip()] = icon
    item_icon_map[mid.strip()] = icon

def get_item_icon(name):
    if name in item_icon_map:
        return item_icon_map[name]
    for k, v in item_icon_map.items():
        if k.lower() == name.lower():
            return v
    # fallback
    return "https://ddragon.leagueoflegends.com/cdn/14.16.1/img/item/3078.png"

# Spell icons
SPELL_ICONS = {
    "Destello": "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es",
    "Prender": "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345",
    "Curación": "https://static.wikia.nocookie.net/leagueoflegends/images/6/6e/Heal.png/revision/latest?cb=20180514003344",
    "Extenuación": "https://static.wikia.nocookie.net/leagueoflegends/images/4/4a/Exhaust.png/revision/latest?cb=20180514003344",
    "Fantasmal": "https://static.wikia.nocookie.net/leagueoflegends/images/a/ab/Ghost.png/revision/latest?cb=20180514003345",
    "Barrera": "https://static.wikia.nocookie.net/leagueoflegends/images/c/cc/Barrier.png/revision/latest?cb=20180514003344",
    "Castigo": "https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003346",
    "Teleportación": "https://static.wikia.nocookie.net/leagueoflegends/images/d/da/Teleport.png/revision/latest?cb=20180514003346"
}

RUNE_ICONS = {
    "Conquistador": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
    "Electrocutar": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
    "Aery": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
    "Cometa Arcano": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
    "Garras del Inmortal": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
    "Réplica": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/aftershock/aftershock.png",
    "Compás Letal": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempo.png",
    "Primer Golpe": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
    "Fase Veloz": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/phaserush/phaserush.png",
    "Estrategia Ofensiva": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
    "Cosecha Oscura": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/darkharvest/darkharvest.png",
    "Pies Veloces": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
    "Aumento Glacial": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png"
}

print("Base setup ready.")
