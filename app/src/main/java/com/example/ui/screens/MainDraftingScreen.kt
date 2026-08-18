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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LaneRole
import com.example.ui.components.FloatingAssistantOverlay
import com.example.ui.components.HextechOrbButton
import com.example.ui.components.RoleIconType
import com.example.ui.components.RoleSelectorCard
import com.example.ui.components.WildRiftVersionBanner
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
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

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Wild Rift Drafting",
                            color = TextPrimary,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp,
                            modifier = Modifier.testTag("app_title_centered")
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onNavigateToInfo,
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .clip(CircleShape)
                                .background(HextechSurface)
                                .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                .size(38.dp)
                                .testTag("nav_about_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Acerca De",
                                tint = HextechGold,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    actions = {
                        // Lado superior derecho completamente limpio según solicitud
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = HextechDarkBg)
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
                Spacer(modifier = Modifier.height(8.dp))

                // Banner de estado del Meta de Wild Rift
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

                // Acceso a Meta & Catálogo de Campeones
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onNavigateToMeta() }
                        .testTag("open_meta_catalog_card"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechCyan.copy(alpha = 0.15f))
                                    .border(1.dp, HextechCyan, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.GridView,
                                    contentDescription = null,
                                    tint = HextechCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Meta & Catálogo de Campeones",
                                    color = TextPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Tier list, counters, sinergias, runas y objetos",
                                    color = TextMuted,
                                    fontSize = 11.5.sp
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Ver",
                            tint = HextechGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Orbe Hextech 3D Central de Activación Inmediata
                HextechOrbButton(
                    isActive = isAssistantActive,
                    onToggle = {
                        isAssistantActive = !isAssistantActive
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (isAssistantActive) "Asistente Hextech Activo • Toca la cámara flotante" else "Presiona ACTIVAR para iniciar el Asistente",
                    color = if (isAssistantActive) HextechCyan else TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        // Live Floating Overlay HUD Simulator (Se activa inmediatamente al presionar ACTIVAR)
        FloatingAssistantOverlay(
            isVisible = isAssistantActive,
            initialRole = mainRole,
            onDismiss = { isAssistantActive = false }
        )
    }
}
