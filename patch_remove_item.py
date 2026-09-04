import re

file_path = 'app/src/main/java/com/example/data/WildRiftItemsData.kt'

with open(file_path, 'r') as f:
    content = f.read()

# Pattern to remove the specific WildRiftItem
pattern = re.compile(r'\s*WildRiftItem\(\s*id = "crown_of_the_shattered_queen".*?iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388710_crown-of-the-shattered-queen\.webp"\s*\),', re.DOTALL)

content = pattern.sub('', content)

# Remove any aliases in nameToIdMap related to it
content = re.sub(r'\s*"corona de la reina ahogada" to "crown_of_the_shattered_queen",', '', content)
content = re.sub(r'\s*"crown of the shattered queen" to "crown_of_the_shattered_queen",', '', content)

with open(file_path, 'w') as f:
    f.write(content)
