import urllib.request
import json
import re

req = urllib.request.Request("https://bestbuildwr.com/tierlist", headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode('utf-8')
        match = re.search(r'<script id="__NEXT_DATA__" type="application/json">(.*?)</script>', html)
        if match:
            data = json.loads(match.group(1))
            
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
            champ_tiers = {}
            for t in tierlists:
                label = t.get('label')
                if label not in ["Top", "Jungla", "Mid", "ADC", "Support"]:
                    continue
                tiers = t.get('tiers', {})
                for tier_key, items in tiers.items():
                    for item in items:
                        name = item.get('name', '').upper()
                        # Some names might have differences, e.g., "KAI'SA" vs "Kaisa"
                        name = name.replace("'", "").replace(" ", "").replace(".", "")
                        
                        if name not in champ_tiers:
                            champ_tiers[name] = {}
                        champ_tiers[name][label] = tier_key
            print(json.dumps(champ_tiers, indent=2))
except Exception as e:
    print(e)
