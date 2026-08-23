import re

files_to_check = [
    "app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt",
    "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
]

def format_rate(content):
    # This will replace things like ${champ.winrate} with ${String.format(java.util.Locale.US, "%.2f", champ.winrate)}
    # and ${champ.pickRate}% etc.
    
    # regex for variables inside string interpolation
    content = re.sub(r'\$\{((?:champ|champion|roleProfile)\.(?:winrate|pickRate|banRate))\}', 
                     r'${String.format(java.util.Locale.US, "%.2f", \1)}', content)
                     
    # Also for direct variables inside Text(), if any, but they are usually inside strings.
    return content

for file in files_to_check:
    with open(file, "r") as f:
        content = f.read()
        
    content = format_rate(content)
    
    with open(file, "w") as f:
        f.write(content)
    print(f"Formatted {file}")
