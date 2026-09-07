import sys

def isLikelySummonerName(trimmed):
    isMixedCase = any(c.isupper() for c in trimmed) and any(c.islower() for c in trimmed)
    hasSpaces = " " in trimmed
    lower = trimmed.lower()
    allowed = ["dr", "jarvan", "twisted", "xin", "aurelion", "lee", "miss", "master", "tahm", "nunu", "kha"]
    for a in allowed:
        if lower.startswith(a):
            return False
    if isMixedCase and hasSpaces:
        return True
    return False

print("RENEKTON matador de de:", isLikelySummonerName("RENEKTON matador de de"))
print("MISS FORTUNE Kimmy Babadei:", isLikelySummonerName("MISS FORTUNE Kimmy Babadei"))

