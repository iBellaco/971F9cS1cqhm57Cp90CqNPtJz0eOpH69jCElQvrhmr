import re
import sys

with open('items.html', 'r', encoding='utf-8') as f:
    content = f.read()

# Split by <h2>
sections = re.split(r'<h2.*?>', content)
phys_section = ""
for s in sections:
    if s.strip().startswith('PHYSICAL DAMAGE ITEMS'):
        phys_section = s
        break

if not phys_section:
    print("Could not find PHYSICAL DAMAGE ITEMS section")
    sys.exit(1)

# Stop at the next major section (which might be the end of the div or another h2... wait, we split by h2, so phys_section is only up to the next h2!
# Let's extract items from phys_section
names_from_alt = re.findall(r'alt="Wild Rift Items&#58; ([^"]+)"', phys_section)
names_from_iname = re.findall(r'<b class="iname">([^<]+)</b>', phys_section)

# Unescape HTML entities in names
import html
all_names = set(html.unescape(n).strip() for n in names_from_alt + names_from_iname)

print(f"Total physical items parsed from HTML: {len(all_names)}")
for name in sorted(all_names):
    print(name)
