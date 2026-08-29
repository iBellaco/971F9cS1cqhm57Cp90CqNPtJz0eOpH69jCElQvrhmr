import re

html = open("wrf.html").read()
blocks = re.findall(r'<div class="wf-tier-list__tiers__block">(.*?)<span class="title', html, re.DOTALL)
print(f"Blocks: {len(blocks)}")

champ_tiers = {}

for block in blocks:
    # Find tier
    tier_match = re.search(r'<div class="tier (.*?)">', block)
    if not tier_match: continue
    t_class = tier_match.group(1).strip()
    
    mapped_tier = None
    if "splus" in t_class: mapped_tier = "S+"
    elif "s" in t_class: mapped_tier = "S"
    elif "a" in t_class: mapped_tier = "A"
    elif "b" in t_class: mapped_tier = "B"
    elif "c" in t_class: mapped_tier = "C"
    
    if not mapped_tier: continue
    
    # Find champions
    champ_links = re.findall(r'<a href="[^"]*".*?>.*?<span>(.*?)</span>.*?</a>', block, re.DOTALL)
    for name in champ_links:
        name = name.strip().upper().replace("'", "").replace(" ", "").replace(".", "")
        if name not in champ_tiers or mapped_tier > champ_tiers[name]: # WRONG comparison but just for printing
            champ_tiers[name] = mapped_tier

print(list(champ_tiers.items())[:20])
