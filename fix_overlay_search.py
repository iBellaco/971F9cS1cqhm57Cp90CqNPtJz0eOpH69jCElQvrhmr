import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Replace the search query text field entirely
regex = r'var searchChampQuery by remember \{ mutableStateOf\(""\) \}.*?LazyColumn'
replacement = r'''
        // Removed text-based search to avoid using keyboard on mobile
        val sortedList = remember {
            WildRiftRepository.champions.sortedBy { it.name }
        }

        Box(
            modifier = Modifier
                .widthIn(min = 300.dp, max = 340.dp)
                .heightIn(min = 400.dp, max = 530.dp)
                .padding(4.dp)
                .pointerInput(Unit) { },
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, if (isAllySlot) AllyBlue else DangerRed)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isAllySlot) "Seleccionar Aliado" else "Seleccionar Enemigo",
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        IconButton(onClick = { showChampionPickerForSlot = null }, modifier = Modifier.size(24.dp)) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
                        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(4),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(sortedList.size) { index ->
                            val champ = sortedList[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable {
                                    if (isAllySlot) {
                                        if (allies.none { it.id == champ.id } && allies.size < 5) allies.add(champ)
                                    } else {
                                        if (enemies.none { it.id == champ.id } && enemies.size < 5) enemies.add(champ)
                                    }
                                    showChampionPickerForSlot = null
                                }
                            ) {
                                ChampionAvatar(champion = champ, size = 44.dp)
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

content = re.sub(r'var searchChampQuery by remember \{ mutableStateOf\(""\) \}.*?LazyColumn\(\s*modifier = Modifier.weight\(1f\),', replacement, content, flags=re.DOTALL)

# Because we replaced the LazyColumn up to the start, we need to remove the rest of the old LazyColumn body which was:
#                       contentPadding = PaddingValues(bottom = 8.dp)
#                   ) {
#                       items(filteredList) { champ -> ... }
#                   }
# But wait, using regex is tricky, let's just do an exact match of the block to be safe.
