import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r') as f:
    content = f.read()

# Replace any occurrence of `add(WildRiftItem("id", "Name (EnglishName)", ` 
# with `add(WildRiftItem("id", "Name", `
# Only matches if the Name part doesn't contain a parenthesis itself.

pattern = re.compile(r'add\(WildRiftItem\("([^"]+)", "([^"(]+) \([^)]+\)",')
new_content = re.sub(pattern, r'add(WildRiftItem("\1", "\2",', content)

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w') as f:
    f.write(new_content)
