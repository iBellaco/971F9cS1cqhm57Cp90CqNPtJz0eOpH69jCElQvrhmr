import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

spells_tab_start = content.find('private fun SpellsTab()')
spells_tab_end = content.find('private fun MapObjectivesTab()', spells_tab_start)
spells_tab_content = content[spells_tab_start:spells_tab_end]

# 1. Add `isGridView` state
if 'var isGridView by remember' not in spells_tab_content:
    spells_tab_content = spells_tab_content.replace(
        'var searchQuery by remember { mutableStateOf("") }',
        'var searchQuery by remember { mutableStateOf("") }\n    var isGridView by remember { mutableStateOf(true) }'
    )

# 2. Update Banner
banner_pattern = re.compile(r"        // WR-Meta Database Status Banner\n        Row\([\s\S]*?            Text\(\n                text = \"\$\{filteredSpells\.size\} \" \+ tr\(\"Hechizos\"\),\n                color = HextechCyan,\n                fontSize = 11\.sp,\n                fontWeight = FontWeight\.SemiBold\n            \)\n        \}")

new_banner = """        // Database Status Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Left side empty
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredSpells.size} " + tr("Hechizos"),
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                
                // View mode toggle
                Row(
                    modifier = Modifier
                        .background(HextechSurface, RoundedCornerShape(6.dp))
                        .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isGridView) HextechCyan else Color.Transparent)
                            .clickable { isGridView = true }
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = tr("Cuadrícula"),
                            color = if (isGridView) HextechDarkBg else TextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (!isGridView) HextechCyan else Color.Transparent)
                            .clickable { isGridView = false }
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = tr("Detallado"),
                            color = if (!isGridView) HextechDarkBg else TextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }"""
spells_tab_content = re.sub(banner_pattern, new_banner, spells_tab_content)

# 3. Update the list to support grid view
list_pattern = re.compile(r"        // Spells List\n        LazyColumn\(\n            modifier = Modifier\.fillMaxSize\(\),\n            verticalArrangement = Arrangement\.spacedBy\(10\.dp\)\n        \) \{[\s\S]*?            \}\n        \}\n        Spacer\(modifier = Modifier\.height\(30\.dp\)\)\n    \}")

new_list = """        // Spells List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isGridView) {
                val chunkedItems = filteredSpells.chunked(3)
                items(chunkedItems) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (spell in rowItems) {
                            Box(modifier = Modifier.weight(1f)) {
                                SpellGridCard(spell = spell, onClick = { /* TODO if detail needed */ })
                            }
                        }
                        for (i in 0 until (3 - rowItems.size)) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            } else {
                items(filteredSpells) { spell ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AppAssetImage(
                                url = spell.iconUrl,
                                contentDescription = spell.name,
                                fallbackText = spell.name,
                                modifier = Modifier.size(52.dp),
                                shape = RoundedCornerShape(8.dp),
                                borderColor = HextechGold.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = tr(spell.name),
                                        color = HextechGoldLight,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = spell.cooldown,
                                        color = HextechGold,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = tr(spell.description),
                                    color = TextMuted,
                                    fontSize = 11.sp,
                                    lineHeight = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }"""

spells_tab_content = re.sub(list_pattern, new_list, spells_tab_content)
content = content[:spells_tab_start] + spells_tab_content + content[spells_tab_end:]

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)

