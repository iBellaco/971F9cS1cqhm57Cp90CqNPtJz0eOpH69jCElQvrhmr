import re

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "r") as f:
    content = f.read()

old_block = """        if (isGridView) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        filteredSpells.forEach { spell ->
                            Card(
                                modifier = Modifier
                                    .width(105.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedSpell = spell },
                                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    AppAssetImage(
                                        url = spell.iconUrl,
                                        contentDescription = spell.name,
                                        fallbackText = spell.name,
                                        modifier = Modifier.size(50.dp),
                                        borderColor = HextechCyan,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = tr(spell.name),
                                        color = TextPrimary,
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                        maxLines = 1
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "CD ${spell.cooldown}",
                                        color = HextechCyan,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        } else {"""

new_block = """        if (isGridView) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 105.dp),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredSpells) { spell ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { selectedSpell = spell },
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                        ) {
                            AppAssetImage(
                                url = spell.iconUrl,
                                contentDescription = spell.name,
                                fallbackText = spell.name,
                                modifier = Modifier.size(50.dp),
                                borderColor = HextechCyan,
                                shape = RoundedCornerShape(10.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = tr(spell.name),
                                color = TextPrimary,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "CD ${spell.cooldown}",
                                color = HextechCyan,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        } else {"""

if old_block in content:
    with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "w") as f:
        f.write(content.replace(old_block, new_block))
    print("Replaced Spells grid successfully")
else:
    print("Could not find Spells grid old block")
