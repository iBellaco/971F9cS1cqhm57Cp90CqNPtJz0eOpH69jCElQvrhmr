import urllib.request
import re

req = urllib.request.Request("https://www.wildriftfire.com/tier-list", headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode('utf-8')
        with open('wrf.html', 'w') as f:
            f.write(html)
        print("Saved to wrf.html")
except Exception as e:
    print(e)
