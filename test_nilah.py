import re
html = open("wrf.html").read()
blocks = re.findall(r'<div class="wf-tier-list__tiers__block">(.*?)<span class="title', html, re.DOTALL)
for block in blocks:
    if "nilah" in block.lower():
        tier_match = re.search(r'<div class="tier (.*?)">', block)
        if tier_match:
            print("Nilah in WRF is in tier:", tier_match.group(1))
