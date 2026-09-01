import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Replace the search query logic and the text field with a sorted list and no text field
old_block = '''        var searchChampQuery by remember { mutableStateOf("") }
        val filteredList = remember(searchChampQuery) {
            WildRiftRepository.champions.filter {
                searchChampQuery.isBlank() || it.name.contains(searchChampQuery, ignoreCase = true)
            }
        }'''
new_block = '''        val filteredList = remember {
            WildRiftRepository.champions.sortedBy { it.name }
        }'''
content = content.replace(old_block, new_block)

# Remove the OutlinedTextField block
old_textfield = '''                    OutlinedTextField(
                        value = searchChampQuery,
                        onValueChange = { searchChampQuery = it },
                        placeholder = { Text(tr("Buscar campeón..."), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth().height(46.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 11.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        ),
                        singleLine = true
                    )'''
content = content.replace(old_textfield, '')

# Change LazyColumn to LazyVerticalGrid
old_lazy = '''                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(filteredList) { champ ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .clickable {
                                        if (isAllySlot) {
                                            if (allies.none { it.id == champ.id } && allies.size < 5) {
                                                allies.add(champ)
                                            }
                                        } else {
                                            if (enemies.none { it.id == champ.id } && enemies.size < 5) {
                                                enemies.add(champ)
                                            }
                                        }
                                        showChampionPickerForSlot = null
                                    }
                                    .padding(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ChampionAvatar(champion = champ, size = 30.dp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(champ.name, color = TextPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }'''

new_grid = '''                    androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
                        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(4),
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(filteredList.size) { index ->
                            val champ = filteredList[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .clickable {
                                        if (isAllySlot) {
                                            if (allies.none { it.id == champ.id } && allies.size < 5) allies.add(champ)
                                        } else {
                                            if (enemies.none { it.id == champ.id } && enemies.size < 5) enemies.add(champ)
                                        }
                                        showChampionPickerForSlot = null
                                    }
                                    .padding(4.dp)
                            ) {
                                ChampionAvatar(champion = champ, size = 36.dp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = champ.name, 
                                    color = TextPrimary, 
                                    fontSize = 9.sp, 
                                    maxLines = 1, 
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }'''

content = content.replace(old_lazy, new_grid)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
