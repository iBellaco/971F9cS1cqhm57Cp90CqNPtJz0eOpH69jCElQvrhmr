import re
from urllib.parse import urlparse
from collections import Counter

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

urls = re.findall(r'iconUrl\s*=\s*"([^"]+)"', content)
domains = [urlparse(u).netloc for u in urls]
print(Counter(domains))

