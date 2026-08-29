import json
with open('bbwr.json') as f:
    data = json.load(f)
championsArray = data.get("props", {}).get("pageProps", {}).get("tierData", {}).get("champions", [])
for category in championsArray:
    label = category.get("label")
    tiers = category.get("tiers", {})
    for tier_key, champs in tiers.items():
        for champ in champs:
            if champ.get("name", "").upper() == "NILAH":
                print(f"BBWR: NILAH is in {label} at tier {tier_key}")
