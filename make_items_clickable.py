with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    content = f.read()

content = content.replace('''
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(60.dp)) {
                                val itemName = champion.situationalItems.getOrNull(index) ?: "Item"
''', '''
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally, 
                                modifier = Modifier
                                    .width(60.dp)
                                    .clickable {
                                        matchupExplanationTarget = champion.situationalItems.getOrNull(index) ?: "Item"
                                        matchupExplanationType = "Situacional"
                                    }
                            ) {
                                val itemName = champion.situationalItems.getOrNull(index) ?: "Item"
''')

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(content)
