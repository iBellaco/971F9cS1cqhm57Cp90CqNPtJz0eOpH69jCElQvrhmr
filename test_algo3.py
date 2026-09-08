import re
import sys

def normalize(s):
    # simplfied normalize
    return re.sub(r'[^a-zA-Z0-9]', ' ', s.lower()).strip()

def normalizeCompact(s):
    return re.sub(r'[^a-zA-Z0-9]', '', s.lower())

def levenshteinDistance(s1, s2):
    if len(s1) > len(s2):
        s1, s2 = s2, s1
    distances = range(len(s1) + 1)
    for i2, c2 in enumerate(s2):
        distances_ = [i2+1]
        for i1, c1 in enumerate(s1):
            if c1 == c2:
                distances_.append(distances[i1])
            else:
                distances_.append(1 + min((distances[i1], distances[i1 + 1], distances_[-1])))
        distances = distances_
    return distances[-1]

allChampions = [
    {"id": "viego", "name": "Viego"}, 
    {"id": "ashe", "name": "Ashe"}, 
    {"id": "renekton", "name": "Renekton"},
    {"id": "miss_fortune", "name": "Miss Fortune"},
    {"id": "ekko", "name": "Ekko"},
    {"id": "thresh", "name": "Thresh"},
    {"id": "pantheon", "name": "Pantheon"},
    {"id": "wukong", "name": "Wukong"},
    {"id": "akshan", "name": "Akshan"}
]

KNOWN_MAP = {
    "viego": "viego", "ashe": "ashe", "renekton": "renekton", "miss fortune": "miss_fortune",
    "missfortune": "miss_fortune", "ekko": "ekko", "thresh": "thresh", "pantheon": "pantheon",
    "wukong": "wukong", "akshan": "akshan", "diego": "diego"
}

IGNORE_WORDS = ["calle", "central", "del", "dragon", "jungla", "jugador", "peace"]
SUMMONER_DIMINUTIVES = ["ito", "ita", "xd", "lol", "pro", "gamer"]

def isValidToken(token, ch_id):
    c_t = normalizeCompact(token)
    c_c = normalizeCompact(ch_id)
    if c_t == c_c: return True
    for dim in SUMMONER_DIMINUTIVES:
        if c_t.endswith(dim) and len(c_t) > len(c_c):
            return False
    return c_t == c_c

def findChampion(text):
    trimmed = text.strip()
    clean = normalize(trimmed)
    clean = re.sub(r'\s+', ' ', clean)
    compact = normalizeCompact(trimmed)
    
    if clean in KNOWN_MAP: return KNOWN_MAP[clean]
    if compact in KNOWN_MAP: return KNOWN_MAP[compact]
    
    for champ in allChampions:
        cN = normalize(champ["name"])
        cC = normalizeCompact(champ["name"])
        cI = normalizeCompact(champ["id"])
        if clean == cN or compact == cC or compact == cI:
            return champ["name"]
            
    words = [w for w in clean.split(" ") if len(w)>=2 and w not in IGNORE_WORDS]
    for w in words:
        if w in KNOWN_MAP and isValidToken(w, KNOWN_MAP[w]): return KNOWN_MAP[w]
        for c in allChampions:
            if normalize(c["name"]) == w or normalizeCompact(c["name"]) == w:
                if isValidToken(w, c["id"]): return c["name"]
                
    if 5 <= len(clean) <= 12 and len(words) == 1:
        for c in allChampions:
            cN = normalize(c["name"])
            if len(cN) >= 5 and abs(len(cN) - len(clean)) <= 1:
                dist = levenshteinDistance(clean, cN)
                if dist <= 1: return c["name"]
    return None

lines = [
    "MISS FORTUNE",
    "Kimmy Babadei",
    "RENEKTON",
    "matador de de",
    "THRESH",
    "WuKOng Baba",
    "PANTHEON",
    "GamesAllcruz",
    "CALLE CENTRAL",
    "D I E G O",
    "Marca estelar eterna"
]
print("--- Ally Lines ---")
for l in lines:
    print(f"'{l}' -> {findChampion(l)}")
