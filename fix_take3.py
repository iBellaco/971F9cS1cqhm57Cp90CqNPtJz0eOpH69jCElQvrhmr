import re

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    content = f.read()

content = content.replace('val advantageList = roleProfile.advantageAgainst.take(5)', 'val advantageList = roleProfile.advantageAgainst.take(3)')
content = content.replace('val counteredList = roleProfile.counteredBy.take(5)', 'val counteredList = roleProfile.counteredBy.take(3)')
content = content.replace('val synergyList = roleProfile.synergies.take(5)', 'val synergyList = roleProfile.synergies.take(3)')

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(content)
