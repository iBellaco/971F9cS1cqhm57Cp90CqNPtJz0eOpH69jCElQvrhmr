import json
import re

def main():
    with open('app/src/main/assets/translations_en.json', 'r', encoding='utf-8') as f:
        en = json.load(f)

    with open('app/src/main/assets/translations_pt.json', 'r', encoding='utf-8') as f:
        pt = json.load(f)

    # Let's inspect WildRiftRepository.kt for mapObjectives
    with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r', encoding='utf-8') as f:
        repo_code = f.read()

    # Find mapObjectives list
    obj_matches = re.findall(r'MapObjective\((.*?)\)', repo_code, re.DOTALL)
    print(f"Found {len(obj_matches)} map objectives")

    missing_en = []
    missing_pt = []

    for obj_str in obj_matches:
        name = re.search(r'name\s*=\s*\"([^\"]+)\"', obj_str)
        spawn = re.search(r'spawnTime\s*=\s*\"([^\"]+)\"', obj_str)
        respawn = re.search(r'respawnTime\s*=\s*\"([^\"]+)\"', obj_str)
        buff = re.search(r'buffDescription\s*=\s*\"([^\"]+)\"', obj_str)
        tactics = re.search(r'tactics\s*=\s*\"([^\"]+)\"', obj_str)

        for field in [name, spawn, respawn, buff, tactics]:
            if field:
                val = field.group(1).strip()
                if val not in en:
                    missing_en.append(val)
                if val not in pt:
                    missing_pt.append(val)

    print(f"Missing map objectives EN: {len(missing_en)}, PT: {len(missing_pt)}")
    if missing_en:
        print("Missing EN items:", missing_en[:10])

if __name__ == '__main__':
    main()
