import urllib.request
from bs4 import BeautifulSoup
import json
import re

url = "https://wr-meta.com/items/"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
html = urllib.request.urlopen(req).read()

soup = BeautifulSoup(html, 'html.parser')

items = []
categories = ["PHYSICAL DAMAGE ITEMS", "MAGIC DAMAGE ITEMS", "DEFENSIVE ITEMS", "BOOTS", "ENCHANTMENTS"]

for category_div in soup.find_all('div', class_='equip-col-in'):
    for item_div in category_div.find_all('div', class_='imgstyle'):
        img = item_div.find('img')
        if not img: continue
        alt = img.get('alt', '')
        name = alt.replace('Wild Rift Items:', '').strip()
        data_src = img.get('data-src', '')
        
        # Try to parse cDragonId from the image url like 1733876753_3072.webp -> maybe 3072?
        # or from data-src
        c_id = 0
        m = re.search(r'_(\d+)\.webp', data_src)
        if m:
            c_id = int(m.group(1))
            
        iconUrl = f"https://wr-meta.com{data_src}"
        id_str = name.lower().replace(' ', '_').replace("'", "").replace("-", "_")
        
        items.append({
            "id": id_str,
            "cDragonId": c_id,
            "name": name,
            "category": "FÍSICO", # I will need to map categories properly
            "goldCost": 0,
            "stats": "",
            "passive": "",
            "iconUrl": iconUrl
        })

print(f"Found {len(items)} items")
