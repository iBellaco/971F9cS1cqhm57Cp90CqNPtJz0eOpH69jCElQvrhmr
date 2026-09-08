import re

def parseRoleFromText(rawText):
    lower = re.sub(r'[^a-zA-Z0-9\s]', ' ', rawText).lower()
    
    if "calle de baron" in lower or "carril de baron" in lower or "baron lane" in lower or "solo lane" in lower:
        return "TOP"
    if "calle central" in lower or "carril central" in lower or "mid lane" in lower or "middle lane" in lower:
        return "MID"
    if "calle del dragon" in lower or "duo lane" in lower or "bot lane" in lower:
        return "ADC"
        
    tokens = [t for t in re.split(r'[\s,.:;\-_/()]+', lower) if t]
    for token in tokens:
        if token in ["baron", "barao", "barão", "top", "solo"]: return "TOP"
        if token in ["jungla", "jungle", "cacador", "caçador", "selva", "jg"]: return "JUNGLE"
        if token in ["mid", "medio", "meio", "central"]: return "MID"
        if token in ["adc", "duo", "dúo", "dragon", "dragón", "dragao", "dragão", "tirador", "atirador", "bot"]: return "ADC"
        if token in ["soporte", "support", "suporte", "sup", "supp", "apoyo"]: return "SUPPORT"
    return None

print(parseRoleFromText("MISS FORTUNE"))
print(parseRoleFromText("Kimmy Babadei"))
