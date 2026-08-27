import json
import urllib.request
import re
import time

replacements = {
    "Apariencia Espiritual": "Fuerza de la naturaleza",
    "Cañón de Fuego Rápido": "Bailarina fantasma",
    "Corona de la Reina Quebrantada": "Velo de alma en pena",
    "Cota de Espinas": "malla de espinas",
    "Creador de Grietas": "Hacedor de grietas",
    "Filo de la Noche": "Edge of Night",
    "Juramento del Protector": "Voto de caballero",
    "Promesa de Caballero": "Voto de caballero",
    "Ángel Guardián": "Ángel custodio"
}

with open('wr_next_data.json') as f:
    d = json.load(f)

# Extract champion mapping from the main page
champs = d['props']['pageProps']['page']['blades'][2]['items']
url_mapping = {}
for c in champs:
    name = c.get('title', '')
    url = c.get('action', {}).get('payload', {}).get('url', '')
    if name and url:
        url_name = url.strip('/').split('/')[-1]
        url_mapping[name.lower()] = url_name

def fix_file(filename):
    with open(filename, 'r') as f:
        data = json.load(f)
        
    for champ in data:
        # Fix Items
        for i, item in enumerate(champ.get('coreItems', [])):
            if item in replacements:
                champ['coreItems'][i] = replacements[item]
        for i, item in enumerate(champ.get('situationalItems', [])):
            if item in replacements:
                champ['situationalItems'][i] = replacements[item]
                
        # Fix Skills
        cname = champ['name'].lower()
        if cname == "wukong":
            url_name = "wukong" # Sometimes it's monkeyking in api but wukong in url
        elif cname == "nunu y willump":
            url_name = "nunu-and-willump"
        elif cname == "renata glasc":
            url_name = "renata-glasc"
        else:
            url_name = cname.replace(" ", "-").replace("'", "").replace(".", "")
            
        # Try to find exactly in url_mapping
        found = None
        for k, v in url_mapping.items():
            if k == cname:
                found = v
                break
        if found:
            url_name = found

        print(f"Fetching abilities for {champ['name']} -> {url_name}")
        url = f"https://wildrift.leagueoflegends.com/es-es/champions/{url_name}/"
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        try:
            with urllib.request.urlopen(req) as response:
                html = response.read().decode('utf-8')
                match = re.search(r'<script id="__NEXT_DATA__" type="application/json">(.*?)</script>', html)
                if match:
                    cdata = json.loads(match.group(1))
                    
                    # Try blades[2] or blades[3]
                    groups = None
                    for blade in cdata['props']['pageProps']['page']['blades']:
                        if 'groups' in blade and len(blade['groups']) >= 4:
                            # Verify it has skills
                            if blade['groups'][0].get('content', {}).get('subtitle') in ["PASIVA", "PASSIVE"]:
                                groups = blade['groups']
                                break
                    if groups:
                        for i, skill in enumerate(champ.get('skills', [])):
                            if i < len(groups):
                                s_data = groups[i].get('content', {})
                                desc = s_data.get('description', {}).get('body', '')
                                # strip html tags
                                desc = re.sub(r'<[^>]+>', '', desc)
                                champ['skills'][i]['name'] = s_data.get('title', '')
                                champ['skills'][i]['description'] = desc
        except Exception as e:
            print(f"Error fetching {champ['name']}: {e}")
            
    with open(filename, 'w') as f:
        json.dump(data, f, indent=2)

fix_file('app/src/main/res/raw/champions_part1.json')
fix_file('app/src/main/res/raw/champions_part2.json')
