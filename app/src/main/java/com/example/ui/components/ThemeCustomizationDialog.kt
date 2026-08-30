package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*
import com.example.util.tr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeCustomizationBottomSheet(
    isPremium: Boolean = false,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var activeTab by remember { mutableIntStateOf(0) }

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
                .padding(horizontal = 16.dp)
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
                            text = tr("Elige tu estética visual y barra de navegación"),
                            color = TextSecondary,
                            fontSize = 11.5.sp
                        )
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tab Selector
            TabRow(
                selectedTabIndex = activeTab,
                containerColor = HextechSurface,
                contentColor = HextechGold,
                indicator = { tabPositions ->
                    if (activeTab in tabPositions.indices) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[activeTab]),
                            color = HextechGold
                        )
                    }
                },
                divider = {}
            ) {
                Tab(
                    selected = activeTab == 0,
                    onClick = { activeTab = 0 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ColorLens, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("${tr("Temas")} (${AppTheme.entries.size})", fontWeight = if (activeTab == 0) FontWeight.Bold else FontWeight.Normal, fontSize = 13.sp)
                        }
                    }
                )
                Tab(
                    selected = activeTab == 1,
                    onClick = { activeTab = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(tr("Barra Inferior"), fontWeight = if (activeTab == 1) FontWeight.Bold else FontWeight.Normal, fontSize = 13.sp)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            when (activeTab) {
                0 -> ThemesListTab(context = context, isPremium = isPremium)
                1 -> NavBarCustomizationTab(context = context, isPremium = isPremium)
            }
        }
    }
}

@Composable
private fun ThemesListTab(context: android.content.Context, isPremium: Boolean) {
    val currentTheme = AppThemeManager.currentTheme
    val isOledMode = AppThemeManager.isOledMode
    var previewTheme by remember { mutableStateOf(currentTheme) }

    // Keep preview synced if currentTheme changes externally
    LaunchedEffect(currentTheme) {
        previewTheme = currentTheme
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF1B5E20))
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = tr("0% Batería / #000000"),
                                    color = Color(0xFF81C784),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
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
            contentPadding = PaddingValues(horizontal = 2.dp)
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
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        theme.primary.copy(alpha = 0.25f),
                                        theme.surface
                                    )
                                )
                            )
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(theme.primary)
                                )
                                if (isSelected) {
                                    Text(
                                        text = "ACTIVO",
                                        color = HextechGold,
                                        fontSize = 8.5.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            }
                            Column {
                                Text(
                                    text = tr(theme.titleKey),
                                    color = theme.textPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = tr(theme.regionTag),
                                    color = theme.primaryLight,
                                    fontSize = 9.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tr("Catálogo Completo (${AppTheme.entries.size})"),
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Text(
                text = tr("Toca para previsualizar"),
                color = TextCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // 2. Lista Desplazable de Temas Regionales
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(AppTheme.entries, key = { it.id }) { theme ->
            val isSelected = currentTheme == theme
            val isInspected = previewTheme == theme

            val animatedBorder by animateColorAsState(
                targetValue = when {
                    isSelected -> HextechGold
                    isInspected -> theme.primary
                    else -> theme.cardBorder.copy(alpha = 0.7f)
                },
                animationSpec = tween(300),
                label = "themeBorder"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        previewTheme = theme
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = theme.surface),
                border = BorderStroke(if (isSelected || isInspected) 2.dp else 1.dp, animatedBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            // Region tag badge
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(theme.primary.copy(alpha = 0.18f))
                                    .border(1.dp, theme.primary.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.5.dp)
                            ) {
                                Text(
                                    text = tr(theme.regionTag),
                                    color = theme.primaryLight,
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

                        // State Badge / Selection Indicator
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(HextechGold.copy(alpha = 0.2f))
                                        .border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = tr("ACTIVO"),
                                        color = HextechGold,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            } else if (isInspected) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(theme.primary.copy(alpha = 0.2f))
                                        .border(1.dp, theme.primary, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = tr("EN VISTA PREVIA"),
                                        color = theme.primary,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = tr(theme.descKey),
                        color = theme.textSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Color swatches preview bar with Primary & Secondary highlighted
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PrimarySecondaryColorChip(
                            color = theme.primary,
                            label = "Primario",
                            isAccent = true
                        )
                        PrimarySecondaryColorChip(
                            color = theme.secondary,
                            label = "Secundario",
                            isAccent = true
                        )
                        ColorChip(color = theme.primaryGlow, label = "Glow", textColor = theme.textMuted)
                        ColorChip(color = theme.surface, label = "Base", textColor = theme.textMuted)

                        Spacer(modifier = Modifier.weight(1f))

                        if (!isSelected) {
                            TextButton(
                                onClick = {
                                    if (isPremium) {
                                        AppThemeManager.setTheme(theme, context)
                                    } else {
                                        android.widget.Toast.makeText(context, "Requiere Suscripción Premium para aplicar el tema.", android.widget.Toast.LENGTH_SHORT).show()
                                    }
                                },
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                                modifier = Modifier.height(28.dp)
                            ) {
                                Text(
                                    text = tr("Aplicar"),
                                    color = if (isPremium) theme.primary else HextechGold,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
}

@Composable
private fun RegionVisualPreviewGridCard(
    inspectedTheme: AppTheme,
    isApplied: Boolean,
    isPremium: Boolean,
    onApply: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = inspectedTheme.surface),
        border = BorderStroke(
            1.5.dp,
            Brush.horizontalGradient(
                listOf(
                    inspectedTheme.primary,
                    inspectedTheme.secondary,
                    inspectedTheme.primaryGlow
                )
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            inspectedTheme.background.copy(alpha = 0.85f),
                            inspectedTheme.surface.copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(14.dp)
        ) {
            // Header: Lore Title + Tag + Live Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(inspectedTheme.primary, inspectedTheme.primaryDark)
                                )
                            )
                            .border(1.5.dp, inspectedTheme.secondary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = inspectedTheme.textPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = tr(inspectedTheme.titleKey),
                                color = inspectedTheme.textPrimary,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                letterSpacing = 0.3.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(inspectedTheme.primary.copy(alpha = 0.2f))
                                    .border(0.8.dp, inspectedTheme.primary.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 1.5.dp)
                            ) {
                                Text(
                                    text = tr(inspectedTheme.regionTag),
                                    color = inspectedTheme.primaryLight,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = tr("Vista previa en tiempo real de paleta y elementos"),
                            color = inspectedTheme.textSecondary,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Lore Snippet
            Text(
                text = tr(inspectedTheme.descKey),
                color = inspectedTheme.textSecondary,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Section: Visual Swatch Grid (Primary & Secondary Focus)
            Text(
                text = tr("Muestrario de Colores Oficiales:"),
                color = inspectedTheme.textPrimary,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 4-Column Color Swatch Grid with Hex codes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ColorSwatchGridItem(
                    modifier = Modifier.weight(1f),
                    color = inspectedTheme.primary,
                    glowColor = inspectedTheme.primaryGlow,
                    title = "Primario",
                    subtitle = "Acciones / Draft",
                    textColor = inspectedTheme.textPrimary,
                    isFeatured = true
                )
                ColorSwatchGridItem(
                    modifier = Modifier.weight(1f),
                    color = inspectedTheme.secondary,
                    glowColor = inspectedTheme.secondaryGlow,
                    title = "Secundario",
                    subtitle = "Acentos / Oro",
                    textColor = inspectedTheme.textPrimary,
                    isFeatured = true
                )
                ColorSwatchGridItem(
                    modifier = Modifier.weight(1f),
                    color = inspectedTheme.primaryGlow,
                    glowColor = inspectedTheme.primaryLight,
                    title = "Resplandor",
                    subtitle = "Aura Rúnica",
                    textColor = inspectedTheme.textPrimary,
                    isFeatured = false
                )
                ColorSwatchGridItem(
                    modifier = Modifier.weight(1f),
                    color = inspectedTheme.surface,
                    glowColor = inspectedTheme.cardBorder,
                    title = "Superficie",
                    subtitle = "Contenedor",
                    textColor = inspectedTheme.textPrimary,
                    isFeatured = false
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Live Mini Mockup UI Preview Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(inspectedTheme.background)
                    .border(1.dp, inspectedTheme.cardBorder, RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Mini mock icon
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(inspectedTheme.primary.copy(alpha = 0.25f))
                                .border(1.dp, inspectedTheme.primary, RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "WR",
                                color = inspectedTheme.primary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Wild Rift Coach Live UI",
                                color = inspectedTheme.textPrimary,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(inspectedTheme.secondary)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Sinergia S+ / Tier Challenger",
                                    color = inspectedTheme.secondary,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // Apply Action Button inside Preview
                    Button(
                        onClick = onApply,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isApplied) inspectedTheme.surfaceVariant else inspectedTheme.primary
                        ),
                        border = BorderStroke(
                            1.dp,
                            if (isApplied) inspectedTheme.secondary else inspectedTheme.primaryGlow
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        if (isApplied) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = inspectedTheme.secondary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = tr("Aplicado"),
                                color = inspectedTheme.secondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Icon(
                                Icons.Default.FlashOn,
                                contentDescription = null,
                                tint = inspectedTheme.background,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = tr("Aplicar"),
                                color = inspectedTheme.background,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorSwatchGridItem(
    modifier: Modifier,
    color: Color,
    glowColor: Color,
    title: String,
    subtitle: String,
    textColor: Color,
    isFeatured: Boolean
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(HextechDarkBg.copy(alpha = 0.6f))
            .border(
                if (isFeatured) 1.2.dp else 0.5.dp,
                if (isFeatured) color.copy(alpha = 0.8f) else HextechCardBorder.copy(alpha = 0.5f),
                RoundedCornerShape(8.dp)
            )
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Swatch Pill with Glow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(color, glowColor)
                    )
                )
                .border(0.5.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = subtitle,
            color = TextMuted,
            fontSize = 8.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun PrimarySecondaryColorChip(
    color: Color,
    label: String,
    isAccent: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(HextechDarkBg.copy(alpha = 0.7f))
            .border(1.dp, color.copy(alpha = 0.8f), RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
                .border(0.8.dp, Color.White.copy(alpha = 0.6f), CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = tr(label),
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 9.5.sp
        )
    }
}

@Composable
private fun NavBarCustomizationTab(context: android.content.Context, isPremium: Boolean) {
    val currentNavOption = AppThemeManager.currentNavBarOption
    val currentTheme = AppThemeManager.currentTheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Sticky / Fixed Header with Live Interactive Preview at the top
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = HextechSurface
            ),
            border = BorderStroke(1.5.dp, HextechGold.copy(alpha = 0.6f))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
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
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = tr("Vista previa en tiempo real"),
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Text(
                        text = tr(currentNavOption.titleKey),
                        color = TextCyan,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                var previewSelectedTab by remember { mutableIntStateOf(0) }
                val navBgColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarBackgroundColor(),
                    animationSpec = tween(350),
                    label = "navBgColorAnim"
                )
                val navAccentColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarAccentColor(),
                    animationSpec = tween(350),
                    label = "navAccentColorAnim"
                )
                val navIconColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarSelectedIconColor(),
                    animationSpec = tween(350),
                    label = "navIconColorAnim"
                )
                val navUnselectedColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarUnselectedColor(),
                    animationSpec = tween(350),
                    label = "navUnselectedColorAnim"
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = navBgColor),
                    border = BorderStroke(1.dp, navAccentColor.copy(alpha = 0.6f))
                ) {
                    NavigationBar(
                        containerColor = navBgColor,
                        contentColor = navAccentColor,
                        modifier = Modifier.height(58.dp)
                    ) {
                        // 1. Inicio
                        NavigationBarItem(
                            selected = previewSelectedTab == 0,
                            onClick = { previewSelectedTab = 0 },
                            icon = { Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(20.dp)) },
                            label = { Text(tr("Inicio"), fontSize = 9.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = navIconColor,
                                selectedTextColor = navAccentColor,
                                indicatorColor = navAccentColor.copy(alpha = 0.22f),
                                unselectedIconColor = navUnselectedColor,
                                unselectedTextColor = navUnselectedColor
                            )
                        )
                        // 2. Selección
                        NavigationBarItem(
                            selected = previewSelectedTab == 1,
                            onClick = { previewSelectedTab = 1 },
                            icon = { Icon(Icons.Default.Groups, contentDescription = null, modifier = Modifier.size(20.dp)) },
                            label = { Text(tr("Selección"), fontSize = 9.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = navIconColor,
                                selectedTextColor = navAccentColor,
                                indicatorColor = navAccentColor.copy(alpha = 0.22f),
                                unselectedIconColor = navUnselectedColor,
                                unselectedTextColor = navUnselectedColor
                            )
                        )
                        // 3. Tier List
                        NavigationBarItem(
                            selected = previewSelectedTab == 2,
                            onClick = { previewSelectedTab = 2 },
                            icon = { Icon(Icons.Default.TrendingUp, contentDescription = null, modifier = Modifier.size(20.dp)) },
                            label = { Text(tr("Tier List"), fontSize = 9.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = navIconColor,
                                selectedTextColor = navAccentColor,
                                indicatorColor = navAccentColor.copy(alpha = 0.22f),
                                unselectedIconColor = navUnselectedColor,
                                unselectedTextColor = navUnselectedColor
                            )
                        )
                        // 4. Catálogo
                        NavigationBarItem(
                            selected = previewSelectedTab == 3,
                            onClick = { previewSelectedTab = 3 },
                            icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, modifier = Modifier.size(20.dp)) },
                            label = { Text(tr("Catálogo"), fontSize = 9.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = navIconColor,
                                selectedTextColor = navAccentColor,
                                indicatorColor = navAccentColor.copy(alpha = 0.22f),
                                unselectedIconColor = navUnselectedColor,
                                unselectedTextColor = navUnselectedColor
                            )
                        )
                    }
                }
            }
        }

        Text(
            text = tr("Selecciona un color para la barra inferior:"),
            color = TextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 2.dp)
        )

        // Scrollable Options List below the pinned preview
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(NavBarColorOption.entries) { option ->
                val isSelected = currentNavOption == option
                val displayColor = if (option.isAutomatic) currentTheme.primary else option.accentColor
                val displayBg = if (option.isAutomatic) currentTheme.surface else option.containerColor

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            if (isPremium) {
                                AppThemeManager.setNavBarOption(option, context)
                            } else {
                                android.widget.Toast.makeText(context, "Requiere Suscripción Premium para aplicar estilo.", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(
                        if (isSelected) 2.dp else 1.dp,
                        if (isSelected) (if (option.isAutomatic) HextechGold else displayColor) else HextechCardBorder
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            // Color Swatch Circle
                            if (option.isAutomatic) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(
                                            Brush.sweepGradient(
                                                listOf(
                                                    HextechGold,
                                                    HextechCyan,
                                                    Color(0xFF818CF8),
                                                    Color(0xFFFF2A42),
                                                    HextechGold
                                                )
                                            )
                                        )
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(HextechDarkBg),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) Icons.Default.Check else Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = if (isSelected) HextechGold else HextechCyan,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(displayBg)
                                        .border(2.dp, displayColor, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = displayColor,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = tr(option.titleKey),
                                    color = TextPrimary,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 13.5.sp
                                )
                                Text(
                                    text = tr(option.descKey),
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        // Preview Swatch
                        val badgeColor = if (option.isAutomatic) HextechGold else displayColor
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(badgeColor.copy(alpha = 0.2f))
                                .border(1.dp, badgeColor, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = if (isSelected) tr("ACTIVO") else if (option.isAutomatic) tr("AUTO") else tr("ELEGIR"),
                                color = badgeColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorChip(
    color: Color,
    label: String,
    textColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(HextechDarkBg.copy(alpha = 0.5f))
            .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
                .border(0.5.dp, Color.White.copy(alpha = 0.4f), CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = tr(label),
            color = textColor,
            fontSize = 9.5.sp
        )
    }
}
