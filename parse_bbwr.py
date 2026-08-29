import json

with open('bbwr.json', 'r') as f:
    data = json.load(f)

# The structure seems to have a list of objects with "label" and "tiers"
def find_tierlists(obj):
    results = []
    if isinstance(obj, dict):
        if "tiers" in obj and "label" in obj:
            results.append(obj)
        for v in obj.values():
            results.extend(find_tierlists(v))
    elif isinstance(obj, list):
        for item in obj:
            results.extend(find_tierlists(item))
    return results

tierlists = find_tierlists(data)
for t in tierlists:
    print(f"\n--- {t.get('label')} ---")
    tiers = t.get('tiers', {})
    for tier_key, items in tiers.items():
        names = [item.get('name') for item in items]
        print(f"  Tier {tier_key}: {len(names)} items (e.g. {names[:3]})")
