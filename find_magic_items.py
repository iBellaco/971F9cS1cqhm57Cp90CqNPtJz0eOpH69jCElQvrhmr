import re

items = [
    "Anillo de la revelación",
    "Tomo amplificador",
    "Aguijón",
    "Garra de Nashor",
    "Ídolo prohibido",
    "Alternador hextech",
    "Amatista del Vacío",
    "Brazalete de la buscadora",
    "Orbe del olvido",
    "Brillo",
    "Catalizador de eones",
    "Disfraz encantado",
    "Vara innecesariamente grande",
    "Vara explosiva",
    "Códice diabólico",
    "Capítulo perdido",
    "Brisa de éter",
    "Robaalmas de Mejai",
    "Cenizas predestinadas",
    "Barrera frondosa"
]

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt", "r", encoding="utf-8") as f:
    content = f.read()

for item in items:
    match = re.search(r'id\s*=\s*"([^"]+)",\s*name\s*=\s*"' + item + '"', content)
    if not match:
        match = re.search(r'id\s*=\s*"([^"]+)".*?name\s*=\s*"' + item + '"', content, re.DOTALL)
    if match:
        print(f"Found {item}: {match.group(1)}")
    else:
        print(f"NOT FOUND: {item}")
