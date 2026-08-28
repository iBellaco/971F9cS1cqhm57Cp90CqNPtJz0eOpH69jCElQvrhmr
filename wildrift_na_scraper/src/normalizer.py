def normalize_position(pos_raw: str) -> str:
    if not pos_raw:
        return "UNKNOWN"
    pos = pos_raw.upper().strip()
    mapping = {
        "TOP": "BARON",
        "BARON": "BARON",
        "JUG": "JUNGLE",
        "JUNGLE": "JUNGLE",
        "JG": "JUNGLE",
        "MID": "MID",
        "MIDDLE": "MID",
        "BOT": "DRAGON",
        "BOTTOM": "DRAGON",
        "ADC": "DRAGON",
        "DRAGON": "DRAGON",
        "SUP": "SUPPORT",
        "SUPPORT": "SUPPORT"
    }
    return mapping.get(pos, "UNKNOWN")

def normalize_rank(rank_raw: str) -> str:
    if not rank_raw:
        return "UNRANKED"
    rank = rank_raw.upper().strip()
    valid_ranks = ["IRON", "BRONZE", "SILVER", "GOLD", "PLATINUM", "EMERALD", "DIAMOND", "MASTER", "GRANDMASTER", "CHALLENGER", "SOVEREIGN"]
    for valid in valid_ranks:
        if valid in rank:
            return valid
    return "UNRANKED"
