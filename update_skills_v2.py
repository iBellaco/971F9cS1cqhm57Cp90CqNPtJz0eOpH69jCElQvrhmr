import re
import urllib.request
import json
import concurrent.futures

try:
    req = urllib.request.Request('https://wildrift.leagueoflegends.com/en-sg/champions/', headers={'User-Agent': 'Mozilla/5.0'})
    html = urllib.request.urlopen(req).read().decode('utf-8')
    m = re.search(r'"buildId":"([^"]+)"', html)
    build_id = m.group(1)
    print(f"Build ID: {build_id}")
except Exception as e:
    print(e)
    build_id = "PPbHttIxHXREsEKJJv66m"

files = [
    "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/JungleChampions.kt",
    "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/SupportChampions.kt"
]

def clean_html(raw_html):
    cleanr = re.compile('<.*?>')
    cleantext = re.sub(cleanr, '', raw_html)
    return cleantext.replace('"', '\\"').replace('\n', ' ')

def get_champion_skills(champ_name):
    slugs = {
        "Dr. Mundo": "dr-mundo", "Jarvan IV": "jarvan-iv", "Lee Sin": "lee-sin",
        "Master Yi": "master-yi", "Maestro Yi": "master-yi", "Miss Fortune": "miss-fortune",
        "Xin Zhao": "xin-zhao", "Aurelion Sol": "aurelion-sol", "Twisted Fate": "twisted-fate",
        "Nunu y Willump": "nunu-willump", "Wukong": "wukong", "Kha'Zix": "khazix",
        "Kai'Sa": "kaisa", "Cho'Gath": "chogath", "Bel'Veth": "belveth", "Kog'Maw": "kogmaw",
        "Rek'Sai": "reksai", "Vel'Koz": "velkoz"
    }
    slug = slugs.get(champ_name)
    if not slug:
        slug = champ_name.lower().replace(" ", "-").replace("'", "").replace(".", "")
    
    url = f"https://wildrift.leagueoflegends.com/_next/data/{build_id}/es-mx/champions/{slug}.json"
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        data = json.loads(urllib.request.urlopen(req).read().decode('utf-8'))
        
        blades = data["pageProps"]["page"]["blades"]
        skills_blade = next((b for b in blades if b.get("header", {}).get("title") == "ABILITIES"), None)
        if not skills_blade: return None
        
        groups = skills_blade["groups"]
        
        skills_str = "listOf(\n"
        slots = ["P", "1", "2", "3", "4"]
        slot_names = ["Pasiva", "Habilidad 1", "Habilidad 2", "Habilidad 3", "Definitiva"]
        
        for i, group in enumerate(groups[:5]):
            slot = slots[i] if i < len(slots) else str(i)
            slotName = slot_names[i] if i < len(slot_names) else f"Habilidad {i}"
            content = group.get("content", {})
            name = content.get("title", "Desconocido").replace('"', '\\"')
            desc = clean_html(content.get("description", {}).get("body", ""))
            thumb_url = group.get("thumbnail", {}).get("url", "")
            
            skills_str += f"""                ChampionSkill(
                    slot = "{slot}",
                    slotName = "{slotName}",
                    name = "{name}",
                    iconUrl = "{thumb_url}",
                    description = "{desc}",
                    cooldown = ""
                ),
"""
        skills_str = skills_str.rstrip().removesuffix(",") + "\n            )"
        return skills_str
    except Exception as e:
        return None

def process_file(fpath):
    with open(fpath, "r", encoding="utf-8") as f:
        content = f.read()

    # Find blocks using regex
    # Champion( ... )
    
    # We will search for:
    # name = "CHAMP_NAME",
    # ...
    # skills = listOf(
    #     ... ChampionSkill( ... )
    # )
    
    # A simple way to replace is finding `name = "(.*?)"` and then `skills = listOf\((?:.|\n)*?\n            \)`
    # But since champions are sequential, we can just find all matches of skills list.
    
    def replacer(match):
        champ_name = match.group(1)
        skills_block = match.group(2)
        
        new_skills = get_champion_skills(champ_name)
        if new_skills:
            print(f" -> Updated {champ_name}!")
            # Replace the old skills_block with the new one
            return match.group(0).replace(skills_block, new_skills)
        else:
            print(f" -> Failed {champ_name}.")
            return match.group(0)

    # Match name = "...", then anything until skills = listOf(... )
    # We use negative lookahead to ensure we don't cross another 'Champion('
    pattern = r'name\s*=\s*"([^"]+)",(?:(?!\bChampion\().)*?(skills\s*=\s*listOf\((?:[^)(]+|\((?:[^)(]+|\([^)(]*\))*\))*\))'
    
    new_content = re.sub(pattern, replacer, content, flags=re.DOTALL)

    with open(fpath, "w", encoding="utf-8") as f:
        f.write(new_content)

for f in files:
    print(f"Processing {f}...")
    process_file(f)

print("Done!")
