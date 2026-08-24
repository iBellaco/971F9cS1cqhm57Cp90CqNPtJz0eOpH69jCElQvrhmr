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
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[activeTab]),
                        color = HextechGold
                    )
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
                            Text(tr("Temas (${AppTheme.entries.size})"), fontWeight = if (activeTab == 0) FontWeight.Bold else FontWeight.Normal, fontSize = 13.sp)
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
                0 -> ThemesListTab(context = context)
                1 -> NavBarCustomizationTab(context = context)
            }
        }
    }
}

@Composable
private fun ThemesListTab(context: android.content.Context) {
    val currentTheme = AppThemeManager.currentTheme

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = tr("Selecciona un tema para transformar la paleta de colores, fondos y acentos de toda la aplicación."),
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        items(AppTheme.entries, key = { it.id }) { theme ->
            val isSelected = currentTheme == theme

            val animatedBorder by animateColorAsState(
                targetValue = if (isSelected) theme.primary else theme.cardBorder,
                animationSpec = tween(300),
                label = "themeBorder"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        AppThemeManager.setTheme(theme, context)
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
                                fontSize = 14.5.sp
                            )
                        }

                        // Radio check icon
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(theme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Seleccionado",
                                    tint = if (theme.isDark) Color.Black else Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .border(1.5.dp, theme.textMuted.copy(alpha = 0.5f), CircleShape)
                            )
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

                    // Color swatches preview bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ColorChip(color = theme.background, label = "Fondo", textColor = theme.textMuted)
                        ColorChip(color = theme.surface, label = "Superficie", textColor = theme.textMuted)
                        ColorChip(color = theme.primary, label = "Primario", textColor = theme.textMuted)
                        ColorChip(color = theme.secondary, label = "Acento", textColor = theme.textMuted)
                    }
                }
            }
        }
    }
}

@Composable
private fun NavBarCustomizationTab(context: android.content.Context) {
    val currentNavOption = AppThemeManager.currentNavBarOption
    val currentTheme = AppThemeManager.currentTheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Explanatory Banner (User Requirement)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = HextechSurface
            ),
            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(HextechGold.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = tr("🎨 Paleta de la Barra de Navegación"),
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = tr("Al cambiar el color en esta paleta de colores, personalizarás de inmediato el fondo, los acentos y los indicadores de la barra de navegación inferior para el estilo que tú elijas."),
                    color = TextPrimary,
                    fontSize = 12.5.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Live Interactive Preview of the Navigation Bar
        Text(
            text = tr("Vista previa en tiempo real de la barra:"),
            color = TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )

        var previewSelectedTab by remember { mutableIntStateOf(0) }
        val navBgColor = AppThemeManager.getNavBarBackgroundColor()
        val navAccentColor = AppThemeManager.getNavBarAccentColor()
        val navIconColor = AppThemeManager.getNavBarSelectedIconColor()
        val navUnselectedColor = AppThemeManager.getNavBarUnselectedColor()

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = navBgColor),
            border = BorderStroke(1.5.dp, navAccentColor.copy(alpha = 0.6f))
        ) {
            NavigationBar(
                containerColor = navBgColor,
                contentColor = navAccentColor,
                modifier = Modifier.height(64.dp)
            ) {
                NavigationBarItem(
                    selected = previewSelectedTab == 0,
                    onClick = { previewSelectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text(tr("Inicio"), fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navIconColor,
                        selectedTextColor = navAccentColor,
                        indicatorColor = navAccentColor,
                        unselectedIconColor = navUnselectedColor,
                        unselectedTextColor = navUnselectedColor
                    )
                )
                NavigationBarItem(
                    selected = previewSelectedTab == 1,
                    onClick = { previewSelectedTab = 1 },
                    icon = { Icon(Icons.Default.Groups, contentDescription = null) },
                    label = { Text(tr("Drafting"), fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navIconColor,
                        selectedTextColor = navAccentColor,
                        indicatorColor = navAccentColor,
                        unselectedIconColor = navUnselectedColor,
                        unselectedTextColor = navUnselectedColor
                    )
                )
                NavigationBarItem(
                    selected = previewSelectedTab == 2,
                    onClick = { previewSelectedTab = 2 },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = null) },
                    label = { Text(tr("Catálogo"), fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navIconColor,
                        selectedTextColor = navAccentColor,
                        indicatorColor = navAccentColor,
                        unselectedIconColor = navUnselectedColor,
                        unselectedTextColor = navUnselectedColor
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = tr("Selecciona un color para la barra inferior:"),
            color = TextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 13.5.sp
        )

        // Palette Grid of Options
        NavBarColorOption.entries.forEach { option ->
            val isSelected = currentNavOption == option
            val displayColor = if (option.isAutomatic) currentTheme.primary else option.accentColor
            val displayBg = if (option.isAutomatic) currentTheme.surface else option.containerColor

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        AppThemeManager.setNavBarOption(option, context)
                    },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = BorderStroke(
                    if (isSelected) 2.dp else 1.dp,
                    if (isSelected) displayColor else HextechCardBorder
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
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(displayColor.copy(alpha = 0.2f))
                            .border(1.dp, displayColor, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = if (isSelected) tr("ACTIVO") else tr("ELEGIR"),
                            color = displayColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
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
