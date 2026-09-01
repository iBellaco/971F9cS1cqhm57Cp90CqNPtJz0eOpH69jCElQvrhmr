import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

enemy_wombos = '''
        val enemyWombos = remember(enemySlots.toList()) {
            WomboComboSynergyDetector.detectWombos(enemySlots)
        }
        if (enemyWombos.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                enemyWombos.forEach { wombo ->
                    DraftWomboSynergyCard(
                        wombo = wombo,
                        onChampionClick = onSelectChampion,
                        isEnemy = true
                    )
                }
            }
        }
'''

content = content.replace(
    '''            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Damage distribution''',
    '''            }
        }''' + enemy_wombos + '''
        Spacer(modifier = Modifier.height(16.dp))

        // Damage distribution'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
