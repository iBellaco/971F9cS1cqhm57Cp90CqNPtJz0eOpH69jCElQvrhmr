import re

content = open('app/src/main/java/com/example/ui/components/DraftWomboSynergyCard.kt', 'r').read()

print("Number of combos:", content.count('WomboCombo('))
