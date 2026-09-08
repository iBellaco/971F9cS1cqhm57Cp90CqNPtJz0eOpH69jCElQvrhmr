import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = """        "tarjeta", "aumento", "usó", "uso", "excelente", "composicion", "composición"
    )"""
replacement = """        "tarjeta", "aumento", "usó", "uso", "excelente", "composicion", "composición", "diego"
    )"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
        f.write(content)
    print("Added diego to ignore words")
else:
    print("Could not find target")
