package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LaneRole
import com.example.ui.components.BatteryAndOverlayNoticeCard
import com.example.ui.components.FloatingAssistantOverlay
import com.example.ui.components.HextechOrbButton
import com.example.ui.components.RoleIconType
import com.example.ui.components.RoleSelectorCard
import com.example.ui.components.WildRiftVersionBanner
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDraftingScreen(
    onNavigateToInfo: () -> Unit,
    onNavigateToMeta: () -> Unit,
    mainRole: LaneRole,
    onMainRoleChange: (LaneRole) -> Unit,
    secondRole: LaneRole,
    onSecondRoleChange: (LaneRole) -> Unit,
    autofillRole: LaneRole,
    onAutofillRoleChange: (LaneRole) -> Unit
) {
    var isAssistantActive by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Wild Rift Drafting",
                            color = TextPrimary,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    },
                    actions = {
                        Box {
                            IconButton(
                                onClick = { showMenu = true },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold, CircleShape)
                                    .size(38.dp)
                                    .testTag("main_menu_options_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menú de Opciones",
                                    tint = HextechGold,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            // Hextech Consolidated Options Menu
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false },
                                modifier = Modifier
                                    .background(HextechDarkBg)
                                    .border(1.2.dp, HextechGold, RoundedCornerShape(12.dp))
                                    .padding(vertical = 4.dp)
                            ) {
                                // 1. Meta & Draft
                                DropdownMenuItem(
                                    text = {
                                        Column {
                                            Text("Meta & Catálogo", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("Tier List, Sinergias, Runas y Objetos", color = TextMuted, fontSize = 11.sp)
                                        }
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.GridView,
                                            contentDescription = null,
                                            tint = HextechGold,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    onClick = {
                                        showMenu = false
                                        onNavigateToMeta()
                                    },
                                    colors = MenuDefaults.itemColors(textColor = TextPrimary)
                                )

                                HorizontalDivider(color = HextechCardBorder.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 4.dp))

                                // 2. Acerca De
                                DropdownMenuItem(
                                    text = {
                                        Column {
                                            Text("Acerca De", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("Guía de uso, parches y fuentes", color = TextMuted, fontSize = 11.sp)
                                        }
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Info,
                                            contentDescription = null,
                                            tint = HextechCyan,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    onClick = {
                                        showMenu = false
                                        onNavigateToInfo()
                                    },
                                    colors = MenuDefaults.itemColors(textColor = TextPrimary)
                                )

                                HorizontalDivider(color = HextechCardBorder.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 4.dp))

                                // 3. Asistente Flotante
                                DropdownMenuItem(
                                    text = {
                                        Column {
                                            Text(
                                                if (isAssistantActive) "Desactivar Asistente" else "Activar Asistente",
                                                color = if (isAssistantActive) HextechCyan else TextPrimary,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp
                                            )
                                            Text(
                                                if (isAssistantActive) "Asistente activo en pantalla" else "Abrir HUD flotante",
                                                color = TextMuted,
                                                fontSize = 11.sp
                                            )
                                        }
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Videocam,
                                            contentDescription = null,
                                            tint = if (isAssistantActive) HextechCyan else HextechGold,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    onClick = {
                                        showMenu = false
                                        isAssistantActive = !isAssistantActive
                                    },
                                    colors = MenuDefaults.itemColors(textColor = TextPrimary)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = HextechDarkBg)
                )
            },
            containerColor = HextechDarkBg
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                // Wild Rift Version Banner (Live from iTunes Lookup API)
                WildRiftVersionBanner()

                Spacer(modifier = Modifier.height(14.dp))

                // 1. Línea Main Selector Card
                RoleSelectorCard(
                    label = "Línea Main",
                    selectedRole = mainRole,
                    onRoleSelected = onMainRoleChange,
                    iconType = RoleIconType.STAR
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 2. Segunda Línea Selector Card
                RoleSelectorCard(
                    label = "Segunda Línea",
                    selectedRole = secondRole,
                    onRoleSelected = onSecondRoleChange,
                    iconType = RoleIconType.SWAP
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Rol Autofill Selector Card
                RoleSelectorCard(
                    label = "Rol Autofill",
                    selectedRole = autofillRole,
                    onRoleSelected = onAutofillRoleChange,
                    iconType = RoleIconType.SHIELD
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Card de Configuración para Segundo Plano y Ahorro de Batería
                BatteryAndOverlayNoticeCard()

                Spacer(modifier = Modifier.height(28.dp))

                // Hextech 3D Glowing Orb Button
                HextechOrbButton(
                    isActive = isAssistantActive,
                    onToggle = {
                        isAssistantActive = !isAssistantActive
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isAssistantActive) "Asistente Hextech Activo • Toca la cámara flotante" else "Toca para Activar Asistente Externo",
                    color = if (isAssistantActive) HextechCyan else TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Live Floating Overlay HUD Simulator (Appears when isAssistantActive is true)
        FloatingAssistantOverlay(
            isVisible = isAssistantActive,
            initialRole = mainRole,
            onDismiss = { isAssistantActive = false }
        )
    }
}
