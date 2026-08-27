import urllib.request
from bs4 import BeautifulSoup
import json

url = "https://wildrift.leagueoflegends.com/es-es/champions/"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode()
        soup = BeautifulSoup(html, "html.parser")
        scripts = soup.find_all("script")
        for s in scripts:
            if s.string and "window.__INITIAL_STATE__" in s.string:
                print("Found __INITIAL_STATE__")
            if s.string and "NEXT_DATA" in s.string:
                print("Found NEXT_DATA")
                
                # Try to parse NEXT_DATA
                try:
                    data = json.loads(s.string)
                    print(list(data.keys()))
                except Exception as e:
                    print("Error parsing json", e)
        print("Done")
except Exception as e:
    print(f"Error: {e}")
