import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Add currentRankBorder import/variable
content = content.replace('val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()',
                          'val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()\n    val currentRankBorder by SubscriptionManager.currentRankBorder.collectAsState()')

# Add selectedCategory
content = content.replace('val focusManager = LocalFocusManager.current',
                          'val focusManager = LocalFocusManager.current\n    var selectedCategory by remember { mutableStateOf("Avatares") }')

# Add TabRow after Title
content = content.replace('style = MaterialTheme.typography.titleLarge\n                )',
'''style = MaterialTheme.typography.titleLarge
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.TabRow(
                    selectedTabIndex = if (selectedCategory == "Avatares") 0 else 1,
                    containerColor = Color.Transparent,
                    contentColor = HextechGold
                ) {
                    androidx.compose.material3.Tab(
                        selected = selectedCategory == "Avatares",
                        onClick = { selectedCategory = "Avatares" },
                        text = { Text("Avatares") }
                    )
                    androidx.compose.material3.Tab(
                        selected = selectedCategory == "Marcos",
                        onClick = { selectedCategory = "Marcos" },
                        text = { Text("Marcos (Premium)") }
                    )
                }
''')

# Wrap the avatar search/grid in `if (selectedCategory == "Avatares")`
content = content.replace('// Search bar\n                OutlinedTextField(',
'''if (selectedCategory == "Avatares") {
                // Search bar
                OutlinedTextField(''')

# Add the else branch and Marcos logic after the FlowRow closes
content = content.replace('} // End of forEach\n            }\n        }\n    }\n',
'''} // End of forEach
            }
                } else {
                    // MARCOS (BORDERS) SECTION
                    val borders = listOf("NONE", "MASTER", "GRANDMASTER", "CHALLENGER")
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    if (!isPremium && userRole != "admin") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(HextechGold.copy(alpha = 0.1f))
                                .border(1.dp, HextechGold, RoundedCornerShape(12.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Filled.Diamond,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Desbloquea Marcos Dinámicos",
                                    color = HextechGold,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Sube de nivel tu perfil con los impresionantes marcos animados de Retador, Gran Maestro y Maestro. Exclusivo para usuarios Premium.",
                                    color = TextSecondary,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Button(
                                    onClick = onOpenPremiumPlans,
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = Color.Black)
                                ) {
                                    Text("Ver Planes Premium", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
                        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f, fill = false)
                    ) {
                        items(borders.size) { index ->
                            val border = borders[index]
                            val isSelected = currentRankBorder == border
                            val isAvailable = isPremium || userRole == "admin" || border == "NONE"

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.85f)
                                    .clickable(enabled = isAvailable) {
                                        SubscriptionManager.changeRankBorder(
                                            borderId = border,
                                            onSuccess = {
                                                android.widget.Toast.makeText(context, "Marco actualizado", android.widget.Toast.LENGTH_SHORT).show()
                                            },
                                            onError = { err ->
                                                android.widget.Toast.makeText(context, err, android.widget.Toast.LENGTH_LONG).show()
                                            }
                                        )
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected) HextechGold.copy(alpha = 0.1f) else SurfaceDark
                                ),
                                border = BorderStroke(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) HextechGold else BorderColor
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier.size(80.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        UserAvatarView(
                                            avatarId = currentAvatarId,
                                            size = 64.dp,
                                            rankBorder = border,
                                            showBorder = false
                                        )
                                        if (!isAvailable) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(Color.Black.copy(alpha = 0.6f), CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.White)
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = if (border == "NONE") "Sin Marco" else border,
                                        color = if (isSelected) HextechGold else TextPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }
''')

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)
