import re

file_path = "app/src/main/java/com/example/data/WildRiftItemsData.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace Ludens Echo URL
# Instead of hardcoding 3285.png, we can hardcode the URL from lolwildriftbuild for this specific one
content = re.sub(r'iconUrl = "\$ITEM_IMG/3285\.png"', 'iconUrl = "https://lolwildriftbuild.com/wp-content/uploads/2020/10/Luden_sEcho.jpg"', content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Ludens Echo patched!")
