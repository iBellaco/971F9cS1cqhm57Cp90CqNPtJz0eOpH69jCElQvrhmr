import os
import re

# Additional matchups for the remaining missing champions
matchups = {
    # BARON LANE
    "ambessa": {"adv": ["Garen", "Sion", "Nasus"], "con": ["Fiora", "Jax", "Camille"], "syn": ["Amumu", "Orianna", "Yasuo"]},
    "chogath": {"adv": ["Malphite", "Sion", "Garen"], "con": ["Fiora", "Vayne", "Irelia"], "syn": ["Yasuo", "Orianna", "Lulu"]},
    "gnar": {"adv": ["Darius", "Garen", "Singed"], "con": ["Irelia", "Yasuo", "Camille"], "syn": ["Jarvan IV", "Orianna", "Amumu"]},
    "ksante": {"adv": ["Sion", "Malphite", "Garen"], "con": ["Fiora", "Vayne", "Gwen"], "syn": ["Taliyah", "Orianna", "Lee Sin"]},
    "mordekaiser": {"adv": ["Garen", "Sion", "Malphite"], "con": ["Fiora", "Vayne", "Olaf"], "syn": ["Amumu", "Sejuani", "Leona"]},
    "olaf": {"adv": ["Gwen", "Mordekaiser", "Sion"], "con": ["Fiora", "Jax", "Tryndamere"], "syn": ["Lulu", "Yuumi", "Zilean"]},
    "poppy": {"adv": ["Irelia", "Riven", "Jax"], "con": ["Darius", "Olaf", "Vayne"], "syn": ["Vayne", "Anivia", "Orianna"]},
    "rumble": {"adv": ["Malphite", "Teemo", "Singed"], "con": ["Darius", "Olaf", "Irelia"], "syn": ["Jarvan IV", "Amumu", "Leona"]},
    "urgot": {"adv": ["Garen", "Sion", "Nasus"], "con": ["Fiora", "Vayne", "Jax"], "syn": ["Orianna", "Lulu", "Thresh"]},
    "volibear": {"adv": ["Renekton", "Sett", "Irelia"], "con": ["Vayne", "Teemo", "Fiora"], "syn": ["Orianna", "Yasuo", "Yuumi"]},
    "warwick": {"adv": ["Irelia", "Yasuo", "Garen"], "con": ["Jax", "Olaf", "Teemo"], "syn": ["Ahri", "Orianna", "Thresh"]},

    # JUNGLE
    "nunuwillump": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Yasuo", "Katarina", "Kennen"]},

    # MID LANE
    "aurora": {"adv": ["Yasuo", "Zed", "Akali"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Amumu", "Malphite", "Jarvan IV"]},
    "heimerdinger": {"adv": ["Yasuo", "Katarina", "Akali"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Jhin", "Caitlyn", "Ashe"]},
    "lissandra": {"adv": ["Katarina", "Akali", "Zed"], "con": ["Syndra", "Orianna", "Cassiopeia"], "syn": ["Amumu", "Malphite", "Sejuani"]},
    "mel": {"adv": ["Yasuo", "Zed", "Katarina"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Amumu", "Malphite", "Nautilus"]},
    "norra": {"adv": ["Yasuo", "Katarina", "Akali"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Jhin", "Caitlyn", "Ashe"]},
    "ryze": {"adv": ["Katarina", "Akali", "Yasuo"], "con": ["Cassiopeia", "Syndra", "Orianna"], "syn": ["Renekton", "Pantheon", "Shen"]},
    "velkoz": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Zed", "Fizz", "Katarina"], "syn": ["Leona", "Nautilus", "Thresh"]},
    "viktor": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Zed", "Fizz", "Katarina"], "syn": ["Amumu", "Malphite", "Jarvan IV"]},

    # DRAGON LANE (ADC)
    "yunara": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Draven", "Tristana", "Samira"], "syn": ["Thresh", "Leona", "Nautilus"]},
    "kalista": {"adv": ["Ezreal", "Lucian", "Sivir"], "con": ["Draven", "Ashe", "Caitlyn"], "syn": ["Thresh", "Leona", "Alistar"]},
    "kogmaw": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Draven", "Tristana", "Samira"], "syn": ["Lulu", "Janna", "Thresh"]},
    "sivir": {"adv": ["Caitlyn", "Ashe", "Jhin"], "con": ["Vayne", "Draven", "Lucian"], "syn": ["Yuumi", "Karma", "Sona"]},
    "smolder": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Draven", "Tristana", "Samira"], "syn": ["Lulu", "Thresh", "Janna"]},

    # SUPPORT
    "bard": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Nautilus", "Blitzcrank"], "syn": ["Caitlyn", "Jhin", "Ezreal"]},
    "maokai": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Alistar", "Braum"], "syn": ["Samira", "Jhin", "Miss Fortune"]},
    "milio": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Jinx", "Lucian", "Aphelios"]},
    "rell": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Alistar", "Braum", "Janna"], "syn": ["Samira", "Yasuo", "Miss Fortune"]},
    "zilean": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Hecarim", "Olaf", "Master Yi"]},
    "zyra": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Jhin", "Ashe", "Caitlyn"]}
}

def format_list(lst):
    return 'listOf(' + ', '.join(f'"{x}"' for x in lst) + ')'

files = [
    "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/JungleChampions.kt",
    "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/SupportChampions.kt"
]

def normalize_id(cid):
    return re.sub(r'[^a-z0-9]', '', cid.lower())

normalized_matchups = {normalize_id(k): v for k, v in matchups.items()}

for file_path in files:
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()
    
    chunks = content.split('Champion(')
    res = [chunks[0]]
    for chunk in chunks[1:]:
        id_m = re.search(r'id\s*=\s*"([^"]+)"', chunk)
        if id_m:
            raw_id = id_m.group(1)
            champ_id = normalize_id(raw_id)
            
            if champ_id in normalized_matchups:
                adv = format_list(normalized_matchups[champ_id]["adv"])
                con = format_list(normalized_matchups[champ_id]["con"])
                syn = format_list(normalized_matchups[champ_id]["syn"])
                chunk = re.sub(r'advantageAgainst\s*=\s*listOf\([^)]*\)', f'advantageAgainst = {adv}', chunk)
                chunk = re.sub(r'counteredBy\s*=\s*listOf\([^)]*\)', f'counteredBy = {con}', chunk)
                chunk = re.sub(r'synergies\s*=\s*listOf\([^)]*\)', f'synergies = {syn}', chunk)
            
        res.append('Champion(' + chunk)
        
    with open(file_path, "w", encoding="utf-8") as f:
        f.write("".join(res))

print("Missing Champions updated successfully!")
