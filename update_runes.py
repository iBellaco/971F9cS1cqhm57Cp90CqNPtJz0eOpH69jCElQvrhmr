import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    content = f.read()

updates = {
    "aery": "https://i.postimg.cc/prFyChdH/aery-hanu.png",
    "grasp_undying": "https://i.postimg.cc/hvdhszGq/desgarrador-hanu.png",
    "fleet_footwork": "https://i.postimg.cc/Znd0HBqt/pies-veloces-hanu.png",
    "first_strike": "https://i.postimg.cc/hhRv5DXw/8369.png",
    "arcane_comet": "https://i.postimg.cc/t7GJMqsm/cometa-hanu-bbwr.png",
    "conqueror": "https://i.postimg.cc/HnyjzcL1/conqueror-hanu.png",
    "electrocute": "https://i.postimg.cc/zXhRSMyc/electrocutar-hanu.png",
    "dark_harvest": "https://i.postimg.cc/VvC5grkT/dark-harvest.png",
    "lethal_tempo": "https://i.postimg.cc/kGbDs65r/compas-letal-hanu.png",
    "phase_rush": "https://i.postimg.cc/Hj1nhYrN/324314341234123-hanu-wr-bb.png"
}

for rune_id, url in updates.items():
    pattern = r'(id = "' + rune_id + r'".*?iconUrl = )"[^"]*"'
    replacement = r'\1"' + url + r'"'
    content = re.sub(pattern, replacement, content, flags=re.DOTALL)

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(content)
