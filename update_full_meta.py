import json, re

# 1. Load Item Icons and Names
with open('app/src/main/java/com/example/data/WildRiftItemsData.kt') as f:
    item_text = f.read()

item_matches = re.findall(r'WildRiftItem\s*\(\s*id\s*=\s*\"([^\"]+)\",\s*name\s*=\s*\"([^\"]+)\",\s*nameEn\s*=\s*\"([^\"]+)\",.*?iconUrl\s*=\s*\"([^\"]+)\"', item_text, re.DOTALL)

item_icon_map = {}
for mid, name, nameEn, icon in item_matches:
    item_icon_map[name.strip().lower()] = icon
    item_icon_map[nameEn.strip().lower()] = icon
    item_icon_map[mid.strip().lower()] = icon

def get_item_icon(name):
    clean = name.strip().lower()
    if clean in item_icon_map:
        return item_icon_map[clean]
    for k, v in item_icon_map.items():
        if k in clean or clean in k:
            return v
    return "https://ddragon.leagueoflegends.com/cdn/14.16.1/img/item/3078.png"

SPELL_ICONS = {
    "Destello": "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es",
    "Prender": "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345",
    "Curación": "https://static.wikia.nocookie.net/leagueoflegends/images/6/6e/Heal.png/revision/latest?cb=20180514003344",
    "Extenuación": "https://static.wikia.nocookie.net/leagueoflegends/images/4/4a/Exhaust.png/revision/latest?cb=20180514003344",
    "Fantasmal": "https://static.wikia.nocookie.net/leagueoflegends/images/a/ab/Ghost.png/revision/latest?cb=20180514003345",
    "Barrera": "https://static.wikia.nocookie.net/leagueoflegends/images/c/cc/Barrier.png/revision/latest?cb=20180514003344",
    "Castigo": "https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003346"
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
    "Aumento Glacial": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png"
}

# Archetype classification for accurate builds and synergies
ENCHANTERS = {"janna", "karma", "lulu", "milio", "nami", "seraphine", "sona", "soraka", "yuumi", "bard"}
TANK_SUPPORTS = {"alistar", "blitzcrank", "braum", "leona", "maokai", "nautilus", "poppy", "rell", "shen", "thresh"}
MAGE_SUPPORTS = {"brand", "lux", "morgana", "vel_koz", "zyra"}
AD_ASSASSIN_SUPPORTS = {"pyke", "senna"}

ADC_CRIT = {"caitlyn", "draven", "jinx", "samira", "sivir", "tristana", "xayah", "yunara", "nilah", "smolder"}
ADC_ONHIT = {"ashe", "kai_sa", "kalista", "kog_maw", "varus", "vayne", "zeri", "twitch", "lucian", "ezreal"}

MID_MAGES = {"ahri", "annie", "aurelion_sol", "aurora", "kassadin", "lissandra", "mel", "norra", "orianna", "ryze", "swain", "syndra", "veigar", "viktor", "vladimir", "vex", "ziggs", "zoe"}
MID_ASSASSINS = {"akali", "fizz", "katarina", "talon", "zed", "akshan", "corki", "twisted_fate", "yasuo", "yone"}

BRUISERS_TOP = {"aatrox", "ambessa", "camille", "darius", "fiora", "garen", "gnar", "gwen", "irelia", "jax", "jayce", "kennen", "mordekaiser", "nasus", "olaf", "pantheon", "renekton", "riven", "rumble", "sett", "teemo", "tryndamere", "urgot", "volibear", "warwick", "wukong"}
TANKS_TOP = {"cho_gath", "dr_mundo", "k_sante", "malphite", "ornn", "singed", "sion"}

JUNGLE_ASSASSIN = {"evelynn", "kayn", "kha_zix", "kindred", "nocturne", "rengar", "nidalee"}
JUNGLE_BRUISER = {"hecarim", "jarvan_iv", "lee_sin", "master_yi", "shyvana", "vi", "viego", "xin_zhao", "warwick"}
JUNGLE_TANK_AP = {"amumu", "diana", "ekko", "fiddlesticks", "gragas", "graves", "lillia", "nunu_willump", "rammus", "skarner", "taliyah"}

print("Classifier ready.")
