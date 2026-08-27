import urllib.request
import re

url = "https://wildrift.leagueoflegends.com/es-es/champions/"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode()
        # look for something that contains champion data
        # Gatsby usually has window.__WEB_CONTEXT__ or similar, or page-data.json links
        matches = re.findall(r'/page-data/es-es/champions/[a-zA-Z0-9_-]+/page-data.json', html)
        if not matches:
            matches = re.findall(r'/page-data/sq/d/[0-9]+.json', html)
        print("Matches found:", matches[:5])
except Exception as e:
    print(f"Error: {e}")
