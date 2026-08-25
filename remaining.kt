                            Text(tr(item.stats), color = HextechCyan, fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(tr(item.passive), color = TextPrimary.copy(alpha = 0.85f), fontSize = 10.sp, lineHeight = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 4: RUNAS META (EXCLUSIVO RUNAS)
// ====================================================================
@Composable
private fun OverlayRunesTabContent(
    lockedChampion: Champion?,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSelectChampion: (Champion) -> Unit,
    onClearChampion: () -> Unit
) {
    if (lockedChampion == null) {
        val matchingChampions = remember(searchQuery) {
            WildRiftRepository.champions.filter { champ ->
                searchQuery.isBlank() || champ.name.contains(searchQuery, ignoreCase = true)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tr("Selecciona un campeón para ver su página de runas óptima"),
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(tr("Buscar campeón fijado..."), color = TextMuted, fontSize = 11.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HextechCyan,
                    unfocusedBorderColor = HextechCardBorder,
                    focusedContainerColor = HextechSurface,
                    unfocusedContainerColor = HextechSurface
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(matchingChampions) { champ ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .clickable { onSelectChampion(champ) }
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champ, size = 32.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(champ.name, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text("${tr(champ.primaryRole.shortName)} • ${tr("Runas")}: ${champ.recommendedRunes}", color = HextechGold, fontSize = 10.sp)
                            }
                        }
                        Icon(Icons.Default.Check, contentDescription = "Seleccionar", tint = HextechCyan, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Selected Champion Banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechCyan, RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = lockedChampion, size = 36.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "${lockedChampion.name} (${tr("Fijado")})",
                            color = HextechCyan,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${tr(lockedChampion.primaryRole.displayName)} • ${tr("Página de Runas")}",
                            color = HextechGoldLight,
                            fontSize = 10.sp
                        )
                    }
                }

                IconButton(
                    onClick = onClearChampion,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cambiar campeón", tint = TextMuted, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Runes Breakdown Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "🔮 " + tr("Runa Clave Recomendada"),
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = lockedChampion.recommendedRunes,
                        color = HextechGoldLight,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                    if (lockedChampion.runeTreeDetails.isNotBlank()) {
                        val parsedRunes = lockedChampion.runeTreeDetails
                            .replace(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+:\\s*"), "")
                            .split("•")
                            .map { it.trim() }
                            .filter { it.isNotEmpty() }
                        
                        if (parsedRunes.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            @OptIn(ExperimentalLayoutApi::class)
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                parsedRunes.forEach { rName ->
                                    val allRunes = com.example.data.WildRiftSpellsAndRunes.runes
                                    val foundRune = allRunes.find { r -> r.name.equals(rName, ignoreCase = true) || rName.contains(r.name) }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (foundRune != null) {
                                            AppAssetImage(
                                                url = foundRune.iconUrl,
                                                contentDescription = foundRune.name,
                                                fallbackText = "",
                                                modifier = Modifier.size(18.dp),
                                                shape = CircleShape
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                        } else {
                                            Box(modifier = Modifier.size(4.dp).background(HextechCyan, CircleShape))
                                            Spacer(modifier = Modifier.width(4.dp))
                                        }
                                        Text(rName, color = TextPrimary.copy(alpha = 0.9f), fontSize = 11.sp)
                                    }
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = lockedChampion.runeTreeDetails,
                                color = TextPrimary.copy(alpha = 0.9f),
                                fontSize = 11.sp,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// ====================================================================
// OVERLAY SUB-TAB 5: HECHIZOS DE INVOCADOR & HABILIDADES (EXCLUSIVO)
// ====================================================================
@Composable
private fun OverlaySpellsTabContent(
    lockedChampion: Champion?,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSelectChampion: (Champion) -> Unit,
    onClearChampion: () -> Unit
) {
    if (lockedChampion == null) {
        val matchingChampions = remember(searchQuery) {
            WildRiftRepository.champions.filter { champ ->
                searchQuery.isBlank() || champ.name.contains(searchQuery, ignoreCase = true)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tr("Selecciona un campeón para ver sus hechizos de invocador recomendados"),
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(tr("Buscar campeón..."), color = TextMuted, fontSize = 11.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HextechGold,
                    unfocusedBorderColor = HextechCardBorder,
                    focusedContainerColor = HextechSurface,
                    unfocusedContainerColor = HextechSurface
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(matchingChampions) { champ ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .clickable { onSelectChampion(champ) }
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ChampionAvatar(champion = champ, size = 32.dp, showTierBadge = false)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(champ.name, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Text("${tr(champ.primaryRole.shortName)} • ${tr("Hechizos")}: ${champ.recommendedSpells.joinToString("+")}", color = HextechGold, fontSize = 10.sp)
                            }
                        }
                        Icon(Icons.Default.Check, contentDescription = "Seleccionar", tint = HextechGold, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Selected Champion Banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechGold, RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = lockedChampion, size = 36.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "${lockedChampion.name} (${tr("Fijado")})",
                            color = HextechGold,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${tr(lockedChampion.primaryRole.displayName)} • ${tr("Hechizos & Orden de Habilidades")}",
                            color = HextechCyan,
                            fontSize = 10.sp
                        )
                    }
                }

                IconButton(
                    onClick = onClearChampion,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cambiar campeón", tint = TextMuted, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Recommended Spells & Skill Order Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "⚡ " + tr("Hechizos de Invocador Recomendados"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        lockedChampion.recommendedSpells.forEach { spell ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = tr(spell),
                                    color = HextechGoldLight,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = HextechCardBorder, thickness = 0.5.dp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "🎯 " + tr("Prioridad de Habilidades"),
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = tr("Maxeo:") + " ${lockedChampion.skillOrder}",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
