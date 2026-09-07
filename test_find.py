import sys

KNOWN_CHAMPIONS_MAP = {
    "miss fortune": "miss_fortune",
    "renekton": "renekton",
    "thresh": "thresh",
    "pantheon": "pantheon",
    "akshan": "akshan",
    "annie": "annie",
    "volibear": "volibear",
    "kha zix": "kha_zix",
    "seraphine": "seraphine",
    "ashe": "ashe"
}

def normalize(s):
    return "".join(c if c.isalnum() else " " for c in s).lower().strip()

def findChampionInText(text):
    trimmed = text.strip()
    clean = normalize(trimmed)
    
    if clean in KNOWN_CHAMPIONS_MAP:
        return KNOWN_CHAMPIONS_MAP[clean]
        
    words = [w for w in clean.split() if len(w) >= 3]
    for w in words:
        if w in KNOWN_CHAMPIONS_MAP:
            return KNOWN_CHAMPIONS_MAP[w]
            
    return None

print("MISS FORTUNE:", findChampionInText("MISS FORTUNE"))
print("MISSFORTUNE:", findChampionInText("MISSFORTUNE"))
print("RENEKTON:", findChampionInText("RENEKTON"))
print("THRESH:", findChampionInText("THRESH"))
print("CALLE CENTRAL:", findChampionInText("CALLE CENTRAL"))
print("D I E G O:", findChampionInText("D I E G O"))
