import re

files_to_check = [
    "app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt",
    "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
]

def format_rate(content):
    content = re.sub(r'champ\.winrate(?!%)', r'String.format(java.util.Locale.US, "%.2f", champ.winrate)', content)
    content = re.sub(r'champ\.pickRate(?!%)', r'String.format(java.util.Locale.US, "%.2f", champ.pickRate)', content)
    content = re.sub(r'champ\.banRate(?!%)', r'String.format(java.util.Locale.US, "%.2f", champ.banRate)', content)
    
    # same for champion.
    content = re.sub(r'champion\.winrate(?!%)', r'String.format(java.util.Locale.US, "%.2f", champion.winrate)', content)
    content = re.sub(r'champion\.pickRate(?!%)', r'String.format(java.util.Locale.US, "%.2f", champion.pickRate)', content)
    content = re.sub(r'champion\.banRate(?!%)', r'String.format(java.util.Locale.US, "%.2f", champion.banRate)', content)
    
    # roleProfile
    content = re.sub(r'roleProfile\.winrate(?!%)', r'String.format(java.util.Locale.US, "%.2f", roleProfile.winrate)', content)
    content = re.sub(r'roleProfile\.pickRate(?!%)', r'String.format(java.util.Locale.US, "%.2f", roleProfile.pickRate)', content)
    content = re.sub(r'roleProfile\.banRate(?!%)', r'String.format(java.util.Locale.US, "%.2f", roleProfile.banRate)', content)
    return content

for file in files_to_check:
    with open(file, "r") as f:
        content = f.read()
        
    content = format_rate(content)
    
    with open(file, "w") as f:
        f.write(content)
    print(f"Formatted {file}")
