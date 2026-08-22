import re

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r') as f:
    content = f.read()

picker_sheet_target = """        if (showChampionPicker) {
            // Need a simple picker sheet here
            // But ChampionPickerSheet might be defined elsewhere, let's just use a dialog or something simple.
        }"""
picker_sheet_replacement = """        if (showChampionPicker) {
            AlertDialog(
                onDismissRequest = { showChampionPicker = false },
                title = { Text(tr("Seleccionar Campeón"), color = HextechCyan, fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                text = {
                    var search by remember { mutableStateOf("") }
                    Column {
                        OutlinedTextField(
                            value = search,
                            onValueChange = { search = it },
                            placeholder = { Text(tr("Buscar..."), fontSize = 12.sp) },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyColumn(modifier = Modifier.weight(1f, fill = false).heightIn(max = 300.dp)) {
                            items(WildRiftRepository.champions.filter { it.name.contains(search, ignoreCase = true) }) { champ ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().clickable {
                                        CooldownTrackerStateHolder.enemyChampions[selectedRole.name] = champ
                                        showChampionPicker = false
                                    }.padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ChampionAvatar(champion = champ, size = 32.dp, showTierBadge = false)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(champ.name, color = TextPrimary, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showChampionPicker = false }) { Text(tr("Cerrar"), color = HextechGold) }
                },
                containerColor = HextechDarkBg,
                titleContentColor = HextechCyan
            )
        }"""
content = content.replace(picker_sheet_target, picker_sheet_replacement)

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w') as f:
    f.write(content)
