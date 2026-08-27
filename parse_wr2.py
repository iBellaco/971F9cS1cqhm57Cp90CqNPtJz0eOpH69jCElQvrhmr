import urllib.request
import re
import json

url = "https://wildrift.leagueoflegends.com/es-es/champions/"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode()
        match = re.search(r'<script id="__NEXT_DATA__" type="application/json">(.*?)</script>', html)
        if match:
            data = json.loads(match.group(1))
            print("Found NEXT_DATA. Keys:", data.keys())
            # Dump the data to a file for inspection
            with open('next_data.json', 'w') as f:
                json.dump(data, f)
        else:
            print("NEXT_DATA not found.")
except Exception as e:
    print(f"Error: {e}")
