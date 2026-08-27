import urllib.request
import json
import re

url = "https://wildrift.leagueoflegends.com/es-es/champions/aatrox/"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode('utf-8')
        match = re.search(r'<script id="__NEXT_DATA__" type="application/json">(.*?)</script>', html)
        if match:
            data = json.loads(match.group(1))
            with open("aatrox_data.json", "w") as f:
                json.dump(data, f, indent=2)
            print("Saved aatrox_data.json")
except Exception as e:
    print(e)
