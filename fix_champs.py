import re

with open("app/src/main/java/com/example/data/WildRiftRepository.kt", "r") as f:
    content = f.read()

def repl(match):
    filename = match.group(1)
    return f"https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/{filename}"

content = re.sub(r'file:///android_asset/offline_images/([a-zA-Z0-9_-]+\.(?:png|jpg))', repl, content)

with open("app/src/main/java/com/example/data/WildRiftRepository.kt", "w") as f:
    f.write(content)
print("Champions updated.")
