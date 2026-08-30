import sys

with open('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'r') as f:
    old = f.read()

header = old.split('@OptIn(ExperimentalMaterial3Api::class)')[0]
region_card_code = '@Composable\nprivate fun RegionVisualPreviewGridCard(' + old.split('@Composable\nprivate fun RegionVisualPreviewGridCard(')[1]
if '@Composable\nprivate fun NavBarCustomizationTab' in region_card_code:
    region_card_code = region_card_code.split('@Composable\nprivate fun NavBarCustomizationTab')[0]

body = """
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeCustomizationBottomSheet(
    isPremium: Boolean = false,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    
    val currentTheme = AppThemeManager.currentTheme
    val isOledMode = AppThemeManager.isOledMode
    val isParticlesEnabled = AppThemeManager.isParticlesEnabled
    var previewTheme by remember { mutableStateOf(currentTheme) }

    LaunchedEffect(currentTheme) {
        previewTheme = currentTheme
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechDarkBg,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = HextechGold.copy(alpha = 0.6f)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = null,
                        tint = HextechGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = tr("Personalización de Temas"),
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = tr("Elige tu estética visual"),
                            color = TextSecondary,
                            fontSize = 11.5.sp
                        )
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // OLED Ultra-Black Switch Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = if (isOledMode) Color(0xFF000000) else HextechSurface),
                border = BorderStroke(1.2.dp, if (isOledMode) HextechGold else HextechCardBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Surface(
                            shape = CircleShape,
                            color = if (isOledMode) HextechGold.copy(alpha = 0.2f) else HextechSurfaceVariant,
                            border = BorderStroke(1.dp, if (isOledMode) HextechGold else HextechCardBorder),
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isOledMode) Icons.Default.DarkMode else Icons.Default.Brightness4,
                                    contentDescription = null,
                                    tint = if (isOledMode) HextechGold else HextechCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = tr("Modo OLED Ultra-Black"),
                                    color = TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                            Text(
                                text = tr("Contraste puro para pantallas AMOLED/OLED"),
                                color = TextSecondary,
                                fontSize = 10.5.sp
                            )
                        }
                    }
                    Switch(
                        checked = isOledMode,
                        onCheckedChange = { enabled ->
                            AppThemeManager.setOledMode(enabled, context)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = HextechGold,
                            checkedTrackColor = HextechGold.copy(alpha = 0.4f),
                            checkedBorderColor = HextechGold
                        ),
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
            
            // Particles Toggle Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = BorderStroke(1.2.dp, if (isParticlesEnabled) HextechGold else HextechCardBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Surface(
                            shape = CircleShape,
                            color = HextechSurfaceVariant,
                            border = BorderStroke(1.dp, if (isParticlesEnabled) HextechGold else HextechCardBorder),
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = if (isParticlesEnabled) HextechGold else HextechCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = tr("Partículas Mágicas"),
                                    color = TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                            Text(
                                text = tr("Efecto de partículas en la barra de navegación"),
                                color = TextSecondary,
                                fontSize = 10.5.sp
                            )
                        }
                    }
                    Switch(
                        checked = isParticlesEnabled,
                        onCheckedChange = { enabled ->
                            AppThemeManager.setParticlesEnabled(enabled, context)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = HextechGold,
                            checkedTrackColor = HextechGold.copy(alpha = 0.4f),
                            checkedBorderColor = HextechGold
                        ),
                        modifier = Modifier.size(34.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            
            // Guide text
            Text(
                text = tr("💡 Desliza horizontalmente en el explorador de regiones para descubrir más temas. Presiona 'Aplicar' para usar el tema seleccionado."),
                color = TextCyan,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
            )

            // 1. Tarjeta Fija de Vista Previa Interactiva en Vivo (Siempre visible al scrollear)
            RegionVisualPreviewGridCard(
                inspectedTheme = previewTheme,
                isApplied = currentTheme == previewTheme,
                isPremium = isPremium,
                onApply = {
                    if (isPremium) {
                        AppThemeManager.setTheme(previewTheme, context)
                    } else {
                        android.widget.Toast.makeText(context, "Requiere Suscripción Premium para aplicar el tema.", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            )

            // Carrusel Horizontal de Regiones Estilo Póster
            Text(
                text = tr("Explorador Visual de Regiones"),
                color = HextechGold,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 2.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 2.dp, bottom = 24.dp)
            ) {
                items(AppTheme.entries, key = { "carousel_${it.id}" }) { theme ->
                    val isSelected = currentTheme == theme
                    val isInspected = previewTheme == theme
                    Card(
                        modifier = Modifier
                            .width(130.dp)
                            .height(82.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { previewTheme = theme },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = theme.surface),
                        border = BorderStroke(
                            if (isSelected) 2.dp else if (isInspected) 1.5.dp else 0.8.dp,
                            if (isSelected) HextechGold else if (isInspected) theme.primary else theme.cardBorder
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            // Placeholder for region splash
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                theme.primary.copy(alpha = 0.2f),
                                                theme.surface
                                            )
                                        )
                                    )
                            )
                            
                            // Glowing overlay if selected
                            if (isSelected || isInspected) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.radialGradient(
                                                listOf(
                                                    theme.primary.copy(alpha = if (isSelected) 0.3f else 0.15f),
                                                    Color.Transparent
                                                )
                                            )
                                        )
                                )
                            }
                            
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = tr(theme.regionTag),
                                    color = theme.primaryLight,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = tr(theme.titleKey),
                                    color = theme.textPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(6.dp)
                                        .size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
"""

with open('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'w') as f:
    f.write(header + body + "\n" + region_card_code)

print("Generated safely with Python")
