with open('app/src/main/java/com/example/ui/components/AdminFeedbackPanel.kt', 'r') as f:
    text = f.read()

target = """            // Header del Panel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HextechSurface.copy(alpha = 0.9f))
                    .border(0.5.dp, HextechCardBorder.copy(alpha = 0.5f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(HextechGold.copy(alpha = 0.18f))
                            .border(1.2.dp, HextechGold, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AdminPanelSettings,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = tr("Panel de Reportes & Sugerencias"),
                                color = HextechGold,
                                fontSize = 15.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechCyan.copy(alpha = 0.2f))
                                    .border(0.8.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "$totalCount",
                                    color = HextechCyan,
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = tr("Gestión, revisión de bugs y evaluación de ideas"),
                            color = TextMuted,
                            fontSize = 11.5.sp
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    IconButton(
                        onClick = { loadReports() },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = tr("Recargar"),
                            tint = HextechCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = tr("Cerrar"),
                            tint = TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }"""

replacement = """            // Header del Panel con partículas rúnicas resplandecientes
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HextechSurface.copy(alpha = 0.9f))
                    .border(0.5.dp, HextechCardBorder.copy(alpha = 0.5f))
            ) {
                // Glowing runic particle animation in header background
                RunicHeaderParticleAnimation(
                    modifier = Modifier.matchParentSize(),
                    particleCount = 14
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(HextechGold.copy(alpha = 0.18f))
                                .border(1.2.dp, HextechGold, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AdminPanelSettings,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = tr("Panel de Reportes & Sugerencias"),
                                    color = HextechGold,
                                    fontSize = 15.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(HextechCyan.copy(alpha = 0.2f))
                                        .border(0.8.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                                        .padding(horizontal = 6.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "$totalCount",
                                        color = HextechCyan,
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Text(
                                text = tr("Gestión, revisión de bugs y evaluación de ideas"),
                                color = TextMuted,
                                fontSize = 11.5.sp
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        IconButton(
                            onClick = { loadReports() },
                            modifier = Modifier.size(34.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = tr("Recargar"),
                                tint = HextechCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.size(34.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = tr("Cerrar"),
                                tint = TextMuted,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }"""

if target in text:
    text = text.replace(target, replacement)
    with open('app/src/main/java/com/example/ui/components/AdminFeedbackPanel.kt', 'w') as f:
        f.write(text)
    print("Successfully replaced header in AdminFeedbackPanel.kt")
else:
    print("Failed to find target in AdminFeedbackPanel.kt")
