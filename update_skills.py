import re
import urllib.request
import json
import concurrent.futures

# Get buildId
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
    # Some hardcoded slug mappings just in case
    slugs = {
        "Dr. Mundo": "dr-mundo",
        "Jarvan IV": "jarvan-iv",
        "Lee Sin": "lee-sin",
        "Master Yi": "master-yi",
        "Maestro Yi": "master-yi",
        "Miss Fortune": "miss-fortune",
        "Xin Zhao": "xin-zhao",
        "Aurelion Sol": "aurelion-sol",
        "Twisted Fate": "twisted-fate",
        "Nunu y Willump": "nunu-willump",
        "Wukong": "wukong",
        "Kha'Zix": "khazix",
        "Kai'Sa": "kaisa",
        "Cho'Gath": "chogath",
        "Bel'Veth": "belveth",
        "Kog'Maw": "kogmaw",
        "Rek'Sai": "reksai",
        "Vel'Koz": "velkoz"
    }
    
    # Generate slug
    slug = slugs.get(champ_name)
    if not slug:
        slug = champ_name.lower().replace(" ", "-").replace("'", "").replace(".", "")
    
    url = f"https://wildrift.leagueoflegends.com/_next/data/{build_id}/es-mx/champions/{slug}.json"
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        data = json.loads(urllib.request.urlopen(req).read().decode('utf-8'))
        
        # The skills are usually in pageProps.page.blades array, type "characterMasthead" or "arNativeBordered"
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
            desc_html = content.get("description", {}).get("body", "")
            desc = clean_html(desc_html)
            
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
        # print(f"Failed {champ_name} ({slug}): {e}")
        return None

def process_file(fpath):
    with open(fpath, "r", encoding="utf-8") as f:
        content = f.read()

    # Find each champion block
    # We will regex to find the `name = "Champ",` and then find its `skills = listOf(...)` block.
    # Because Kotlin blocks can be nested, we can use a simpler approach:
    # find `name = "...",` ... `skills = listOf(` ... `)`
    
    # We'll split the file by `name = "`
    parts = content.split('name = "')
    new_content = parts[0]
    
    for part in parts[1:]:
        # Find the end of the name
        idx = part.find('"')
        if idx == -1:
            new_content += 'name = "' + part
            continue
        champ_name = part[:idx]
        
        # Now find the skills block
        skills_start = part.find("skills = listOf(")
        if skills_start == -1:
            new_content += 'name = "' + part
            continue
            
        # We need to find the matching closing brace for listOf(
        brace_count = 0
        skills_end = -1
        in_string = False
        
        for i in range(skills_start + 16, len(part)):
            c = part[i]
            if c == '"' and part[i-1] != '\\':
                in_string = not in_string
            if not in_string:
                if c == '(': brace_count += 1
                elif c == ')':
                    if brace_count == 0:
                        skills_end = i
                        break
                    brace_count -= 1
        
        if skills_end != -1:
            # Generate new skills
            print(f"Fetching {champ_name}...")
            new_skills = get_champion_skills(champ_name)
            if new_skills:
                part = part[:skills_start] + "skills = " + new_skills + part[skills_end+1:]
                print(f" -> Updated {champ_name}!")
            else:
                print(f" -> Failed {champ_name}.")
                
        new_content += 'name = "' + part

    with open(fpath, "w", encoding="utf-8") as f:
        f.write(new_content)

for f in files:
    print(f"Processing {f}...")
    process_file(f)

print("Done!")
