import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

replacement = '''        if (womboCombos.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                womboCombos.forEach { wombo ->
                    DraftWomboSynergyCard(
                        wombo = wombo,
                        onChampionClick = onSelectChampion
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Enemy Wombo Combos
        val enemyChampsForWombo = remember(enemySlots) {
            enemySlots.filterNotNull().distinctBy { it.id }
        }
        val enemyWomboCombos = remember(enemyChampsForWombo) {
            WomboComboSynergyDetector.detectWombos(enemyChampsForWombo)
        }
        if (enemyWomboCombos.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                enemyWomboCombos.forEach { wombo ->
                    DraftWomboSynergyCard(
                        wombo = wombo,
                        onChampionClick = onSelectChampion,
                        isEnemy = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }'''

content = content.replace('''        if (womboCombos.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                womboCombos.forEach { wombo ->
                    DraftWomboSynergyCard(
                        wombo = wombo,
                        onChampionClick = onSelectChampion
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }''', replacement)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
