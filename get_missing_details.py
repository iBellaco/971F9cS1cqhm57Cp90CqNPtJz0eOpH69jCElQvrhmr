import re
import json

missing = [
    "kraken slayer",
    "stridebreaker",
    "goredrinker",
    "guinsoo's rageblade",
    "overlord's bloodmail",
    "mercurial scimitar",
    "galeforce"
]

with open('items.html', 'r', encoding='utf-8') as f:
    content = f.read()

# For each missing item, find the <div> or block that describes it.
# The structure is usually <div class="imgstyle ..."><img data-src="..." alt="Wild Rift Items&#58; Item Name"> ... </div>
# Or similar. Let's just find the block from <div class="imgstyle" to the next <div class="imgstyle"
blocks = content.split('<div class="imgstyle')

for item in missing:
    found = False
    for block in blocks:
        if item.lower() in block.lower() and 'alt="Wild Rift Items' in block:
            print(f"--- {item.upper()} ---")
            
            # Icon URL
            icon_match = re.search(r'data-src="([^"]+)"', block)
            icon = "https://wr-meta.com" + icon_match.group(1) if icon_match else ""
            print(f"Icon: {icon}")
            
            # Gold cost
            gold_match = re.search(r'<b class="goldt">([^<]+)</b>', block)
            gold = gold_match.group(1) if gold_match else "0"
            print(f"Gold: {gold}")
            
            # Stats (using the <b class="istats">)
            stats = re.findall(r'<b class="istats">.*?\+([^<]+)</b>', block)
            print(f"Stats: {' • '.join(stats)}")
            
            # Passives/Actives
            passives = re.findall(r'<b class="istats2">([^<]+)</b>:?\s*(.*?)(?=<br|<div|$)', block)
            for p_name, p_desc in passives:
                # strip html tags from desc
                p_desc = re.sub(r'<[^>]+>', '', p_desc).strip()
                print(f"Passive [{p_name}]: {p_desc}")
                
            # Coach tip (using <b class="cdr">Item Name TIPS:</b>)
            tip_match = re.search(r'<b class="cdr">[^<]+TIPS:</b>(.*?)(?=<div|$)', block, re.S)
            if tip_match:
                tip = re.sub(r'<[^>]+>', '', tip_match.group(1)).strip()
                print(f"Tip: {tip}")
                
            found = True
            break
            
    if not found:
        print(f"--- {item.upper()} NOT FOUND in blocks ---")
