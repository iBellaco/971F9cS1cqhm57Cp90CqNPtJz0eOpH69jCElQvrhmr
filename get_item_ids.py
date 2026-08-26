import re

items = [
    "Anillo de revelación",
    "Tomo amplificador",
    "Grieta salvaje del aguijón",
    "Grieta salvaje de la garra de Nashor",
    "Ídolo prohibido Wild Rift",
    "Alternador hextech Wild Rift",
    "Amatista vacía",
    "Brazalete de la buscadora",
    "Orbe del olvido Wild Rift",
    "Brillo",
    "Catalizador de eones",
    "Disfraz inquietante Wild Rift",
    "Vara innecesariamente grande Wild rift",
    "Varita explosiva Wild Rift",
    "Códice diabólico Wild Rift",
    "Capítulo perdido Wild Rift",
    "Wisp de éter Wild Rift",
    "Rift salvaje del ladrón de almas de Mejai",
    "Cenizas predestinadas",
    "Barrera frondosa"
]

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt", "r", encoding="utf-8") as f:
    content = f.read()

for item in items:
    # First try same line or short distance
    pattern = r'id\s*=\s*"([^"]+)"(?:.(?!id\s*=))*?name\s*=\s*"' + re.escape(item) + r'"'
    match = re.search(pattern, content, re.DOTALL)
    
    if match:
        print(f"{item} : {match.group(1)}")
    else:
        # Try reversed order (name then id)
        pattern2 = r'name\s*=\s*"' + re.escape(item) + r'"(?:.(?!name\s*=))*?id\s*=\s*"([^"]+)"'
        match2 = re.search(pattern2, content, re.DOTALL)
        if match2:
            print(f"{item} : {match2.group(1)}")
        else:
            # Let's try matching name with anything inside the Item block
            pattern3 = r'Item\s*\((?:.(?!Item\s*\())*?id\s*=\s*"([^"]+)"(?:.(?!Item\s*\())*?name\s*=\s*"' + re.escape(item) + r'"'
            match3 = re.search(pattern3, content, re.DOTALL)
            if match3:
                print(f"{item} : {match3.group(1)}")
            else:
                pattern4 = r'Item\s*\((?:.(?!Item\s*\())*?name\s*=\s*"' + re.escape(item) + r'"(?:.(?!Item\s*\())*?id\s*=\s*"([^"]+)"'
                match4 = re.search(pattern4, content, re.DOTALL)
                if match4:
                    print(f"{item} : {match4.group(1)}")
                else:
                    print(f"NOT FOUND: {item}")
