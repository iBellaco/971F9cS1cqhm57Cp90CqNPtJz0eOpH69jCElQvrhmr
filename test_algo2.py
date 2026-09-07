import re

KNOWN_CHAMPIONS_MAP = {
    "viego": "viego",
    "renekton": "renekton",
    "wukong": "wukong",
    "galio": "galio",
    "veigar": "veigar"
}

UI_IGNORE_WORDS = ["calle", "central", "del", "dragon", "jungla", "jugador"]

def normalize(input_str):
    s = re.sub(r'[^a-zA-Z0-9 ]', ' ', input_str)
    s = s.lower().strip()
    return re.sub(r'\s+', ' ', s)

def normalizeCompact(input_str):
    s = re.sub(r'[^a-zA-Z0-9]', '', input_str)
    return s.lower()

def isValidChampionToken(token, championId):
    cleanToken = normalizeCompact(token)
    cleanChamp = normalizeCompact(championId)
    if cleanToken == cleanChamp: return True
    return cleanToken == cleanChamp

def findChampionInText(text):
    trimmed = text.strip()
    if not trimmed: return None
    
    clean = normalize(trimmed)
    if not clean: return None
    
    compact = normalizeCompact(trimmed)
    
    if clean in KNOWN_CHAMPIONS_MAP:
        champId = KNOWN_CHAMPIONS_MAP[clean]
        if isValidChampionToken(clean, champId): return champId
        
    if compact in KNOWN_CHAMPIONS_MAP:
        champId = KNOWN_CHAMPIONS_MAP[compact]
        if isValidChampionToken(compact, champId): return champId
        
    for champId in KNOWN_CHAMPIONS_MAP.values():
        champNorm = normalize(champId)
        champCompact = normalizeCompact(champId)
        if clean == champNorm or compact == champCompact or compact == champCompact:
            return champId
            
    words = [w for w in clean.split(" ") if len(w) >= 3 and w not in UI_IGNORE_WORDS]
    for word in words:
        if word in KNOWN_CHAMPIONS_MAP:
            champId = KNOWN_CHAMPIONS_MAP[word]
            if isValidChampionToken(word, champId): return champId
            
        for champId in KNOWN_CHAMPIONS_MAP.values():
            champNorm = normalize(champId)
            champCompact = normalizeCompact(champId)
            if champNorm == word or champCompact == word:
                if isValidChampionToken(word, champId): return champId
                
    if 5 <= len(clean) <= 12 and len(words) == 1:
        # fuzzy
        for champId in KNOWN_CHAMPIONS_MAP.values():
            champNorm = normalize(champId)
            if len(champNorm) >= 5 and abs(len(champNorm) - len(clean)) <= 1:
                # mock fuzzy distance
                return champId
                
    return None

print(findChampionInText("WUKONG XCS Alee22"))
print(findChampionInText("GALIO XCS Elchicho7"))
print(findChampionInText("VEIGAR Gustavo GG"))

