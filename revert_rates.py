import re

files_to_check = [
    "app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt",
    "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
]

def revert_rate(content):
    content = content.replace('String.format(java.util.Locale.US, "%.2f", roleProfile.winrate)Delta', 'roleProfile.winrateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", roleProfile.pickRate)Delta', 'roleProfile.pickRateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", roleProfile.banRate)Delta', 'roleProfile.banRateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champion.winrate)Delta', 'champion.winrateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champion.pickRate)Delta', 'champion.pickRateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champion.banRate)Delta', 'champion.banRateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champ.winrate)Delta', 'champ.winrateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champ.pickRate)Delta', 'champ.pickRateDelta')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champ.banRate)Delta', 'champ.banRateDelta')
    
    content = content.replace('String.format(java.util.Locale.US, "%.2f", roleProfile.winrate)', 'roleProfile.winrate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", roleProfile.pickRate)', 'roleProfile.pickRate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", roleProfile.banRate)', 'roleProfile.banRate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champion.winrate)', 'champion.winrate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champion.pickRate)', 'champion.pickRate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champion.banRate)', 'champion.banRate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champ.winrate)', 'champ.winrate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champ.pickRate)', 'champ.pickRate')
    content = content.replace('String.format(java.util.Locale.US, "%.2f", champ.banRate)', 'champ.banRate')
    return content

for file in files_to_check:
    with open(file, "r") as f:
        content = f.read()
        
    content = revert_rate(content)
    
    with open(file, "w") as f:
        f.write(content)
    print(f"Reverted {file}")
