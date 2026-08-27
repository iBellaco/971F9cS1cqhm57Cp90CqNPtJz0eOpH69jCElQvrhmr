import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('remember(WildRiftRepository.champions)', 'remember(WildRiftRepository.champions.toList())')
content = content.replace('remember(selectedLane, syncState, WildRiftRepository.champions)', 'remember(selectedLane, syncState, WildRiftRepository.champions.toList())')
content = content.replace('remember(searchQuery, selectedRoleFilter, selectedTierFilter, syncState, WildRiftRepository.champions)', 'remember(searchQuery, selectedRoleFilter, selectedTierFilter, syncState, WildRiftRepository.champions.toList())')
content = content.replace('remember(search, alreadySelected, selectedRoleFilter, WildRiftRepository.champions)', 'remember(search, alreadySelected, selectedRoleFilter, WildRiftRepository.champions.toList())')

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

content = content.replace('remember(searchQuery, WildRiftRepository.champions)', 'remember(searchQuery, WildRiftRepository.champions.toList())')

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)
