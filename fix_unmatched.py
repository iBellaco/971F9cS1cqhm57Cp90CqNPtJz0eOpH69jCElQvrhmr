import re

def extract_names(filepath, pattern, group=1):
    names = []
    with open(filepath, 'r', encoding='utf-8') as f:
        for m in re.finditer(pattern, f.read()):
            names.append(m.group(group))
    return names

def main():
    items_cat = extract_names("app/src/main/java/com/example/data/WildRiftItemsData.kt", r'name\s*=\s*"([^"]+)"')
    print("ALL CATALOG ITEMS:")
    for i in sorted(items_cat):
        print(f" - {i}")

if __name__ == '__main__':
    main()
