import re
import html

with open('/tmp/wr_items.html', 'r', encoding='utf-8') as f:
    raw = f.read()

sections = [
    ('PHYSICAL DAMAGE ITEMS', 'PHYSICAL', 40),
    ('Magic Damage Items', 'MAGIC', 28),
    ('Defense Items', 'DEFENSE', 32),
    ('Support Items', 'SUPPORT', 17),
    ('ACTIVE SPELL ITEMS', 'ACTIVE', 13),
    ('Boots tier 2', 'BOOTS_T2', 7),
    ('Boots tier 3', 'BOOTS_T3', 7),
    ('Mid Tier Items', 'MID_TIER', 44),
    ('Basic Items', 'BASIC', 13),
]

total = 0
for i, (sec_title, cat_enum, exp_count) in enumerate(sections):
    pos1 = raw.find(f'<h2>{sec_title}</h2>')
    if pos1 == -1:
        # Case insensitive find
        m = re.search(r'<h2>\s*' + re.escape(sec_title) + r'\s*</h2>', raw, re.IGNORECASE)
        pos1 = m.start() if m else -1
    
    if pos1 == -1:
        print(f'Header {sec_title} not found!')
        continue
    
    # Find next section start
    if i + 1 < len(sections):
        next_title = sections[i+1][0]
        pos2 = raw.lower().find(f'<h2>{next_title.lower()}</h2>', pos1 + len(sec_title) + 5)
        if pos2 == -1:
            m = re.search(r'<h2>\s*' + re.escape(next_title) + r'\s*</h2>', raw[pos1+10:], re.IGNORECASE)
            pos2 = pos1 + 10 + m.start() if m else -1
    else:
        # After basic items, next is Keystone or similar
        pos2 = raw.lower().find('<h2', pos1 + len(sec_title) + 5)
        
    if pos2 == -1:
        pos2 = len(raw)
        
    sec_html = raw[pos1:pos2]
    parts = re.split(r'<div class=[\"\']bild-img-short[\"\']', sec_html)
    item_parts = parts[1:]
    print(f'{cat_enum} ({sec_title}): found {len(item_parts)} items (expected {exp_count})')
    total += len(item_parts)

print(f'TOTAL ITEMS FOUND: {total}')
