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

allChampions = [{"id": "viego", "name": "Viego"}, {"id": "ashe", "name": "Ashe"}, {"id": "renekton", "name": "Renekton"}]
KNOWN_MAP = {"viego": "viego", "ashe": "ashe", "renekton": "renekton"}
IGNORE_WORDS = ["calle", "central", "del", "dragon", "jungla", "jugador", "peace"]

def isValidToken(token, ch_id):
    c_t = normalizeCompact(token)
    c_c = normalizeCompact(ch_id)
    return c_t == c_c

def findChampion(text):
    trimmed = text.strip()
    clean = normalize(trimmed)
    clean = re.sub(r'\s+', ' ', clean)
    compact = normalizeCompact(trimmed)
    print(f"DEBUG: '{text}' -> clean='{clean}' compact='{compact}'")
    
    if clean in KNOWN_MAP: return KNOWN_MAP[clean]
    if compact in KNOWN_MAP: return KNOWN_MAP[compact]
    
    for champ in allChampions:
        cN = normalize(champ["name"])
        cC = normalizeCompact(champ["name"])
        cI = normalizeCompact(champ["id"])
        if clean == cN or compact == cC or compact == cI:
            return champ["name"]
            
    words = [w for w in clean.split(" ") if len(w)>=3 and w not in IGNORE_WORDS]
    for w in words:
        if w in KNOWN_MAP and isValidToken(w, KNOWN_MAP[w]): return KNOWN_MAP[w]
        for c in allChampions:
            if normalize(c["name"]) == w or normalizeCompact(c["name"]) == w:
                if isValidToken(w, c["id"]): return c["name"]
                
    if 5 <= len(clean) <= 12 and len(words) == 1:
        print("TRYING FUZZY for", clean)
        for c in allChampions:
            cN = normalize(c["name"])
            if len(cN) >= 5 and abs(len(cN) - len(clean)) <= 1:
                dist = levenshteinDistance(clean, cN)
                print(f"   fuzzy {clean} vs {cN} = {dist}")
                if dist <= 1: return c["name"]
    return None

print(findChampion("CALLE CENTRAL D I E G O"))
print(findChampion("GALIO XCS Elchicho7"))
print(findChampion("VEIGAR Gustavo GG"))
print(findChampion("Jugador 4"))
