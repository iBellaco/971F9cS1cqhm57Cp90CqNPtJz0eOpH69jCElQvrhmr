import re

with open("app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt", "r", encoding="utf-8") as f:
    content = f.read()

# I need to wrap the CASILLA DE SELECCIÓN DE CAMPEÓN Box with a parent Box, 
# and move the `TÚ` badge out of the inner box to the parent box.

old_box = """                        // CASILLA DE SELECCIÓN DE CAMPEÓN (Sustituye la casilla 1 y 2)
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    when {
                                        isMyRole -> HextechCyan.copy(alpha = 0.22f)
                                        isOccupied -> if (isEnemy) DangerRed.copy(alpha = 0.25f) else HextechGold.copy(alpha = 0.25f)
                                        else -> if (isLightAppTheme) Color(0xFFE2E8F0) else Color(0xFF070D15)
                                    }
                                )
                                .border(
                                    width = if (isOccupied || isMyRole) 1.5.dp else 1.dp,
                                    color = when {
                                        isMyRole -> HextechCyan
                                        isOccupied -> if (isEnemy) DangerRed else HextechGold
                                        else -> HextechCardBorder
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (champ != null) {
                                // Imagen del Campeón Seleccionado
                                AppAssetImage(
                                    url = champ.avatarUrl,
                                    contentDescription = champ.name,
                                    fallbackText = champ.name,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            onChampionClick(champ)
                                        }
                                )
                                // Botón pequeño 'X' en la esquina superior para deseleccionar
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .size(16.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black.copy(alpha = 0.75f))
                                        .clickable {
                                            onRemoveChampionForRole(role)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = tr("Quitar"),
                                        tint = Color.White,
                                        modifier = Modifier.size(10.dp)
                                    )
                                }
                                // Badge de "TÚ" si es la posición activa del jugador
                                if (isMyRole) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            .fillMaxWidth()
                                            .background(HextechCyan.copy(alpha = 0.9f))
                                            .padding(vertical = 1.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = tr("TÚ"),
                                            color = Color.Black,
                                            fontSize = 7.5.sp,
                                            fontWeight = FontWeight.Black
                                        )
                                    }
                                }
                            } else {
                                // Casilla vacía con botón "+" para añadir
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = tr("Seleccionar Campeón"),
                                        tint = if (isMyRole) HextechCyan else TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    if (isMyRole) {
                                        Text(
                                            text = tr("TÚ"),
                                            color = HextechCyan,
                                            fontSize = 7.5.sp,
                                            fontWeight = FontWeight.Black
                                        )
                                    }
                                }
                            }
                        }"""

new_box = """                        // CASILLA DE SELECCIÓN DE CAMPEÓN
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when {
                                            isMyRole -> HextechCyan.copy(alpha = 0.22f)
                                            isOccupied -> if (isEnemy) DangerRed.copy(alpha = 0.25f) else HextechGold.copy(alpha = 0.25f)
                                            else -> if (isLightAppTheme) Color(0xFFE2E8F0) else Color(0xFF070D15)
                                        }
                                    )
                                    .border(
                                        width = if (isOccupied || isMyRole) 1.5.dp else 1.dp,
                                        color = when {
                                            isMyRole -> HextechCyan
                                            isOccupied -> if (isEnemy) DangerRed else HextechGold
                                            else -> HextechCardBorder
                                        },
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (champ != null) {
                                    // Imagen del Campeón Seleccionado
                                    AppAssetImage(
                                        url = champ.avatarUrl,
                                        contentDescription = champ.name,
                                        fallbackText = champ.name,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                onChampionClick(champ)
                                            }
                                    )
                                    // Botón pequeño 'X' en la esquina superior para deseleccionar
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(16.dp)
                                            .clip(CircleShape)
                                            .background(Color.Black.copy(alpha = 0.75f))
                                            .clickable {
                                                onRemoveChampionForRole(role)
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = tr("Quitar"),
                                            tint = Color.White,
                                            modifier = Modifier.size(10.dp)
                                        )
                                    }
                                } else {
                                    // Casilla vacía con botón "+" para añadir
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = tr("Seleccionar Campeón"),
                                            tint = if (isMyRole) HextechCyan else TextMuted,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                            
                            // Badge de "TÚ" si es la posición activa del jugador flotando sobre el borde inferior
                            if (isMyRole) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .offset(y = 6.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(HextechCyan)
                                        .padding(horizontal = 6.dp, vertical = 2.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = tr("TÚ"),
                                        color = Color.Black,
                                        fontSize = 7.5.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            }
                        }"""

content = content.replace(old_box, new_box)

with open("app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt", "w", encoding="utf-8") as f:
    f.write(content)

