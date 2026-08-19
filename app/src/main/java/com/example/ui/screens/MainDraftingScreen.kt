package com.example.ui.screens

import android.app.Activity
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
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.util.tr
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.model.LaneRole
import com.example.ui.components.BugReportFeedbackDialog
import com.example.ui.components.HextechOrbButton
import com.example.ui.components.LaneDisplaySettingCard
import com.example.ui.components.WildRiftVersionBanner
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.util.SystemPermissionHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDraftingScreen(
    onNavigateToInfo: () -> Unit,
    onNavigateToMeta: () -> Unit,
    onNavigateToLogin: () -> Unit,
    mainRole: LaneRole,
    onMainRoleChange: (LaneRole) -> Unit,
    secondRole: LaneRole,
    onSecondRoleChange: (LaneRole) -> Unit,
    autofillRole: LaneRole,
    onAutofillRoleChange: (LaneRole) -> Unit,
    currentLanguage: String = "es",
    onLanguageChange: (String) -> Unit = {}
) {
    val context = LocalContext.current
    
    val lifecycleOwner = LocalLifecycleOwner.current
    var isAssistantActive by remember { mutableStateOf(SystemPermissionHelper.isServiceRunning(context)) }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var showBugReportDialog by remember { mutableStateOf(false) }

    // Sincronizar estado del servicio cuando la app pasa a primer plano
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                isAssistantActive = SystemPermissionHelper.isServiceRunning(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val toggleAssistant: () -> Unit = {
        if (isAssistantActive) {
            SystemPermissionHelper.stopFloatingService(context)
            isAssistantActive = false
        } else {
            if (!SystemPermissionHelper.hasOverlayPermission(context)) {
                showPermissionDialog = true
            } else {
                SystemPermissionHelper.startFloatingService(context)
                isAssistantActive = true
                // Enviar inmediatamente la aplicación a segundo plano para flotar sobre Wild Rift
                (context as? Activity)?.moveTaskToBack(true)
            }
        }
    }

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
                        Row {
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
                        }
                    },
                    actions = {
                        var expandedLang by remember { mutableStateOf(false) }
                        val currentFlag = when(currentLanguage) {
                            "en" -> "🇺🇸/🇬🇧"
                            "pt" -> "🇧🇷/🇵🇹"
                            else -> "🇪🇸"
                        }
                        Box {
                            TextButton(
                                onClick = { expandedLang = true },
                                modifier = Modifier
                                    .padding(end = 6.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                            ) {
                                Text(currentFlag, fontSize = 16.sp)
                            }
                            androidx.compose.material3.DropdownMenu(
                                expanded = expandedLang,
                                onDismissRequest = { expandedLang = false },
                                modifier = Modifier.background(HextechSurface)
                            ) {
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text("🇪🇸 Español", color = TextPrimary) },
                                    onClick = { 
                                        onLanguageChange("es")
                                        expandedLang = false 
                                    }
                                )
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text("🇺🇸/🇬🇧 English", color = TextPrimary) },
                                    onClick = { 
                                        onLanguageChange("en")
                                        expandedLang = false 
                                    }
                                )
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text("🇧🇷/🇵🇹 Português", color = TextPrimary) },
                                    onClick = { 
                                        onLanguageChange("pt")
                                        expandedLang = false 
                                    }
                                )
                            }
                        }

                        IconButton(
                            onClick = { showBugReportDialog = true },
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .clip(CircleShape)
                                .background(HextechSurface)
                                .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                .size(38.dp)
                                .testTag("nav_bug_report_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.BugReport,
                                contentDescription = "Reportar Bugs o Sugerencias",
                                tint = HextechGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
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

                // Panel oficial de Ajustes de Posición / Línea (LANE DISPLAY SETTING)
                LaneDisplaySettingCard(
                    mainRole = mainRole,
                    onMainRoleChange = onMainRoleChange,
                    secondRole = secondRole,
                    onSecondRoleChange = onSecondRoleChange
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
                                    text = tr("Meta & Catálogo de Campeones"),
                                    color = TextPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = tr("Tier list, counters, sinergias, runas y objetos"),
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
                    onToggle = toggleAssistant
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (isAssistantActive) tr("Asistente Hextech Activo • Toca la cámara flotante") else tr("Presiona ACTIVAR para iniciar el Asistente Flotante"),
                    color = if (isAssistantActive) HextechCyan else TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        // Diálogo para conceder el permiso de superposición (Aparecer sobre otras apps)
        if (showPermissionDialog) {
            AlertDialog(
                onDismissRequest = { showPermissionDialog = false },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Layers, contentDescription = null, tint = HextechGold, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(tr("Permiso de Superposición"), color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                },
                text = {
                    Column {
                        Text(
                            text = tr("Para que el asistente inteligente funcione en segundo plano sobre Wild Rift, Android requiere habilitar 'Aparecer encima' (Superposición)."),
                            color = TextPrimary,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = tr("PermisoSuperposicionTexto"),
                            color = HextechCyan,
                            fontSize = 12.sp
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showPermissionDialog = false
                            SystemPermissionHelper.openOverlaySettings(context)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                    ) {
                        Text(tr("Conceder Permiso"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showPermissionDialog = false }) {
                        Text(tr("Cancelar"), color = TextMuted)
                    }
                },
                containerColor = HextechSurface,
                shape = RoundedCornerShape(16.dp)
            )
        }

        if (showBugReportDialog) {
            BugReportFeedbackDialog(
                onDismiss = { showBugReportDialog = false }
            )
        }
    }
}
