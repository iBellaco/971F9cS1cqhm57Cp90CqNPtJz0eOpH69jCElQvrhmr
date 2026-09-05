                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                var isDraggingPanel by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .widthIn(min = 300.dp, max = 340.dp)
                        .height(530.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, HextechGold)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                        // Header con barra de arrastre para reposicionar el Hub cómodamente
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = {
                                            isDraggingPanel = true
                                            dragAccumulatedY = 0f
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            dragAccumulatedY += dragAmount.y
                                            onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                                        },
                                        onDragEnd = {
                                            isDraggingPanel = false
                                            if (dragAccumulatedY > 120f) {
                                                onClose()
                                            } else {
                                                onDragDelta(0, 0, false, false)
                                            }
                                            dragAccumulatedY = 0f
                                        },
                                        onDragCancel = {
                                            isDraggingPanel = false
                                            dragAccumulatedY = 0f
                                            onDragDelta(0, 0, false, false)
                                        }
                                    )
                                }
                                .padding(bottom = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Shield, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text("DRAFTING COACH", color = HextechGold, fontWeight = FontWeight.Black, fontSize = 12.5.sp)
                                    val isCaptureReady = screenCaptureManager?.isReady() == true
                                    val indicatorColor = when {
                                        !isCaptureReady -> Color(0xFFFFB300)
                                        autoScanEnabled -> Color(0xFF00FF7F)
                                        else -> HextechGold
                                    }
                                    val indicatorText = when {
                                        !isCaptureReady -> tr("Sin permiso de pantalla")
                                        autoScanEnabled -> tr("Auto-Scan Activo")
                                        else -> tr("Escaneo Manual")
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.clickable {
                                            if (!isCaptureReady) {
                                                try {
                                                    val reqIntent = Intent(context, com.example.MainActivity::class.java).apply {
                                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                                        putExtra("EXTRA_REQUEST_CAPTURE", true)
                                                    }
                                                    context.startActivity(reqIntent)
                                                } catch (_: Exception) {}
                                            }
                                        }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(indicatorColor)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = indicatorText,
                                            color = indicatorColor,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Botón Minimizar/Mostrar Pestañas del Hub
                                IconButton(
                                    onClick = { isOverlayTabsMinimized = !isOverlayTabsMinimized },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isOverlayTabsMinimized) Icons.Default.UnfoldMore else Icons.Default.UnfoldLess,
                                        contentDescription = if (isOverlayTabsMinimized) "Mostrar pestañas del Hub" else "Minimizar pestañas del Hub",
                                        tint = if (isOverlayTabsMinimized) HextechGold else TextMuted,
                                        modifier = Modifier.size(17.dp)
                                    )
                                }

                                // Botón Escaneo Manual
                                IconButton(
                                    onClick = { triggerManualScan() },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    if (isScanning) {
                                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = HextechCyan, strokeWidth = 2.dp)
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.FlashOn,
                                            contentDescription = "Escanear selección",
                                            tint = HextechCyan,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }

                                // Botón Limpiar Draft
                                IconButton(
                                    onClick = {
                                        for (i in 0 until 5) {
                                            allies[i] = null
                                            enemies[i] = null
                                        }
                                        selectedChampionDetail = null
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.DeleteSweep, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(18.dp))
                                }

                                // Botón Tamaño de Burbuja (Compact/Expanded)
                                IconButton(
                                    onClick = {
                                        isCompactBubble = !isCompactBubble
                                        onCompactModeChange(isCompactBubble)
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isCompactBubble) Icons.Default.UnfoldMore else Icons.Default.UnfoldLess,
                                        contentDescription = "Cambiar tamaño de burbuja",
                                        tint = TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }

                                // Botón Minimizar
                                IconButton(
                                    onClick = {
                                        isExpanded = false
                                        onExpandedChange(false)
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minimizar", tint = TextPrimary, modifier = Modifier.size(20.dp))
                                }
                            }
                        }

                        // Sub-Header con Pestañas de Navegación del Hub
                        AnimatedVisibility(
                            visible = !isOverlayTabsMinimized
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                            // Pestaña 1: Draft Coach
                            val isDraftActive = overlayHubTab == OverlayHubTab.DRAFT
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isDraftActive) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isDraftActive) HextechCyan else HextechCardBorder.copy(alpha = 0.5f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable { overlayHubTab = OverlayHubTab.DRAFT }
                                    .padding(vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Shield,
                                        contentDescription = null,
                                        tint = if (isDraftActive) HextechCyan else TextMuted,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "Draft",
                                        color = if (isDraftActive) HextechCyan else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isDraftActive) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }

                            // Pestaña 2: Tier & Builds
                            val isTierActive = overlayHubTab == OverlayHubTab.TIER_LIST
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isTierActive) HextechGold.copy(alpha = 0.2f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isTierActive) HextechGold else HextechCardBorder.copy(alpha = 0.5f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable { overlayHubTab = OverlayHubTab.TIER_LIST }
                                    .padding(vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        Icons.Default.EmojiEvents,
                                        contentDescription = null,
                                        tint = if (isTierActive) HextechGold else TextMuted,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "Tiers",
                                        color = if (isTierActive) HextechGold else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isTierActive) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }

                            // Pestaña 3: Campeones
                            val isChampsActive = overlayHubTab == OverlayHubTab.CHAMPIONS
                            Box(
                                modifier = Modifier
                                    .weight(1.1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isChampsActive) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isChampsActive) HextechCyan else HextechCardBorder.copy(alpha = 0.5f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable { overlayHubTab = OverlayHubTab.CHAMPIONS }
                                    .padding(vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Shield,
                                        contentDescription = null,
                                        tint = if (isChampsActive) HextechCyan else TextMuted,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "Champs",
                                        color = if (isChampsActive) HextechCyan else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isChampsActive) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }

                            // Pestaña 4: Historial
                            if (isLoggedInAndPremium) {
                                val isHistoryActive = overlayHubTab == OverlayHubTab.HISTORY
                                Box(
                                    modifier = Modifier
                                        .weight(0.9f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isHistoryActive) Color(0xFF00FF7F).copy(alpha = 0.15f) else HextechSurface)
                                        .border(
                                            1.dp,
