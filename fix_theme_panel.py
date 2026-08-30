import re
with open('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'r') as f:
    text = f.read()

target = """            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        if (isPremium) {
                            AppThemeManager.setTheme(theme, context)
                        } else {
                            android.widget.Toast.makeText(context, "Requiere Suscripción Premium para aplicar el tema.", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = theme.surface),
                border = BorderStroke(if (isSelected) 2.dp else 1.dp, animatedBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Region tag badge
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(theme.primary.copy(alpha = 0.15f))
                                    .border(1.dp, theme.primary.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = tr(theme.regionTag),
                                    color = theme.primary,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = tr(theme.titleKey),
                                color = theme.textPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = theme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = tr(theme.descKey),
                        color = theme.textSecondary,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ColorChip(color = theme.background, label = "Fondo", textColor = theme.textPrimary)
                        ColorChip(color = theme.primary, label = "Primario", textColor = theme.background)
                        ColorChip(color = theme.secondary, label = "Acento", textColor = theme.background)
                    }
                }
            }"""

replacement = """            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        if (isPremium) {
                            AppThemeManager.setTheme(theme, context)
                        } else {
                            android.widget.Toast.makeText(context, "Requiere Suscripción Premium para aplicar el tema.", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    }
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                theme.background,
                                theme.surfaceVariant,
                                theme.surface
                            )
                        )
                    )
                    .border(
                        BorderStroke(if (isSelected) 2.dp else 1.dp, animatedBorder),
                        RoundedCornerShape(16.dp)
                    )
            ) {
                // Decorational glow
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(theme.primary.copy(alpha = 0.15f), Color.Transparent),
                                radius = 400f
                            )
                        )
                )

                Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Region tag badge resembling Universe label
                                Box(
                                    modifier = Modifier
                                        .background(theme.primary.copy(alpha = 0.1f))
                                        .border(0.5.dp, theme.primary.copy(alpha = 0.5f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = tr(theme.regionTag).uppercase(),
                                        color = theme.primaryLight,
                                        fontSize = 8.sp,
                                        letterSpacing = 1.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = theme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tr(theme.titleKey).uppercase(),
                                color = theme.textPrimary,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.sp,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = tr(theme.descKey),
                                color = theme.textSecondary,
                                fontSize = 11.sp,
                                lineHeight = 14.sp,
                                maxLines = 2,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(modifier = Modifier.size(16.dp).background(theme.background).border(1.dp, theme.cardBorder))
                        Box(modifier = Modifier.size(16.dp).background(theme.primary).border(1.dp, theme.cardBorder))
                        Box(modifier = Modifier.size(16.dp).background(theme.secondary).border(1.dp, theme.cardBorder))
                        Box(modifier = Modifier.size(16.dp).background(theme.primaryGlow).border(1.dp, theme.cardBorder))
                    }
                }
            }"""

text = text.replace(target, replacement)

# Add necessary imports
if 'import androidx.compose.ui.graphics.Brush' not in text:
    text = text.replace('import androidx.compose.ui.graphics.Color', 'import androidx.compose.ui.graphics.Color\nimport androidx.compose.ui.graphics.Brush\nimport androidx.compose.foundation.layout.fillMaxSize')

with open('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'w') as f:
    f.write(text)
print("Updated ThemeCustomizationDialog to Universe Theme")
