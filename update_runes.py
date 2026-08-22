import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

runes_tab_start = content.find('private fun RunesTab()')
runes_tab_end = content.find('private fun SpellsTab()', runes_tab_start)
runes_tab_content = content[runes_tab_start:runes_tab_end]

# 1. Add `isGridView` state
if 'var isGridView by remember' not in runes_tab_content:
    runes_tab_content = runes_tab_content.replace(
        'var selectedFilter by remember { mutableStateOf("TODOS") }',
        'var selectedFilter by remember { mutableStateOf("TODOS") }\n    var isGridView by remember { mutableStateOf(true) }'
    )

# 2. Update Banner
banner_pattern = re.compile(r"        // WR-Meta Database Status Banner\n        Row\([\s\S]*?            Text\(\n                text = \"\$\{filteredRunes\.size\} \" \+ tr\(\"Runas\"\),\n                color = HextechCyan,\n                fontSize = 11\.sp,\n                fontWeight = FontWeight\.SemiBold\n            \)\n        \}")

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
                    text = "${filteredRunes.size} " + tr("Runas"),
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
runes_tab_content = re.sub(banner_pattern, new_banner, runes_tab_content)

# 3. Update the list to support grid view
list_pattern = re.compile(r"        // Runes List\n        LazyColumn\(\n            modifier = Modifier\.fillMaxSize\(\),\n            verticalArrangement = Arrangement\.spacedBy\(10\.dp\)\n        \) \{[\s\S]*?            \}\n        \}\n        Spacer\(modifier = Modifier\.height\(30\.dp\)\)\n    \}")

new_list = """        // Runes List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isGridView) {
                val chunkedItems = filteredRunes.chunked(3)
                items(chunkedItems) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (rune in rowItems) {
                            Box(modifier = Modifier.weight(1f)) {
                                RuneGridCard(rune = rune, onClick = { /* TODO if detail needed */ })
                            }
                        }
                        for (i in 0 until (3 - rowItems.size)) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            } else {
                items(filteredRunes) { rune ->
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
                                url = rune.iconUrl,
                                contentDescription = rune.name,
                                fallbackText = rune.name,
                                modifier = Modifier.size(48.dp),
                                shape = CircleShape,
                                borderColor = HextechGold.copy(alpha = 0.5f)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = tr(rune.name),
                                    color = HextechGoldLight,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = tr(rune.category),
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = tr(rune.description),
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

runes_tab_content = re.sub(list_pattern, new_list, runes_tab_content)
content = content[:runes_tab_start] + runes_tab_content + content[runes_tab_end:]

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)

