package com.example.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.data.sync.OfflineResourceManager
import com.example.data.sync.DownloadState
import com.example.ui.theme.DangerRed



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

import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.util.ImagePrefetcher
import com.example.ui.components.DownloadProgressWidget
import com.example.ui.components.UserAvatarView
import com.example.util.SubscriptionManager
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp

import com.example.util.tr
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.ui.theme.TierAColor
import com.example.model.LaneRole
import com.example.ui.components.BugReportFeedbackDialog
import com.example.ui.components.AdminFeedbackBottomSheet
import com.example.ui.components.HextechOrbButton
import com.example.ui.components.LaneDisplaySettingCard
import com.example.ui.components.WildRiftVersionBanner
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.isLightAppTheme
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import android.content.Intent
import android.net.Uri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.TextMuted

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Favorite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.util.SystemPermissionHelper
import android.media.projection.MediaProjectionManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.service.screen.ScreenCaptureManager
import android.content.Context

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainDraftingScreen(
    onNavigateToInfo: () -> Unit,
    onNavigateToTutorial: () -> Unit,
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
    var showDonationDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()
    val currentRankBorder by SubscriptionManager.currentRankBorder.collectAsState()

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

    val mediaProjectionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            ScreenCaptureManager.pendingMediaProjectionResultCode = result.resultCode
            ScreenCaptureManager.pendingMediaProjectionData = result.data
            SystemPermissionHelper.startFloatingService(context)
            isAssistantActive = true
        } else {
            // Permiso de captura denegado
            isAssistantActive = false
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
                val mediaProjectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
                mediaProjectionLauncher.launch(mediaProjectionManager.createScreenCaptureIntent())
            }
        }
    }

    // Si hay un diálogo o modal abierto en la pantalla de inicio, el botón atrás lo cierra primero
    BackHandler(enabled = showPermissionDialog || showBugReportDialog) {
        if (showPermissionDialog) showPermissionDialog = false
        if (showBugReportDialog) showBugReportDialog = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "« Wild Rift Coach »",
                                color = TextPrimary,
                                fontSize = 17.5.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 0.5.sp,
                                modifier = Modifier.testTag("app_title_centered")
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(HextechCyan)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = tr(com.example.data.WildRiftRepository.CURRENT_PATCH_VERSION),
                                    color = HextechCyan,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.2.sp
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 6.dp)
                        ) {
                            IconButton(
                                onClick = { showDonationDialog = true },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                    .size(38.dp)
                                    .testTag("nav_donation_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = tr("Donaciones"),
                                    tint = HextechGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            IconButton(
                                onClick = { showThemeDialog = true },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape)
                                    .size(38.dp)
                                    .testTag("nav_theme_button")
                            ) {
                                Text("🎨", fontSize = 18.sp)
                            }
                        }
                    },
                    actions = {
                        var expandedLang by remember { mutableStateOf(false) }
                        val activeFlag = when (currentLanguage.lowercase()) {
                            "en" -> "🇺🇸"
                            "pt" -> "🇧🇷"
                            else -> "🇪🇸"
                        }
                        Box {
                            TextButton(
                                onClick = { expandedLang = true },
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                    .testTag("nav_language_button")
                            ) {
                                Text(activeFlag, fontSize = 16.sp)
                            }
                            androidx.compose.material3.DropdownMenu(
                                expanded = expandedLang,
                                onDismissRequest = { expandedLang = false },
                                modifier = Modifier.background(HextechSurface)
                            ) {
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { 
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("🇪🇸 Español", color = if (currentLanguage == "es") HextechGold else TextPrimary, fontWeight = if (currentLanguage == "es") FontWeight.Bold else FontWeight.Normal)
                                            if (currentLanguage == "es") {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text("(Activo)", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    },
                                    onClick = { 
                                        onLanguageChange("es")
                                        expandedLang = false 
                                    }
                                )
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { 
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("🇧🇷 Português", color = if (currentLanguage == "pt") HextechGold else TextPrimary, fontWeight = if (currentLanguage == "pt") FontWeight.Bold else FontWeight.Normal)
                                            if (currentLanguage == "pt") {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text("(Ativo)", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    },
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
                                .padding(end = 4.dp)
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

                        // User Avatar Profile button
                        val authUser = com.example.util.AuthManager.getAuth()?.currentUser
                        if (authUser != null) {
                            IconButton(
                                onClick = onNavigateToLogin,
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .size(38.dp)
                                    .testTag("nav_profile_avatar_button")
                            ) {
                                UserAvatarView(
                                    avatarId = currentAvatarId,
                                    rankBorder = currentRankBorder,
                                    size = 36.dp,
                                    fallbackInitial = authUser.displayName ?: authUser.email ?: "U"
                                )
                            }
                        }


                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                
                // Hide buttons if user is already logged in
                val isLoggedIn = com.example.util.AuthManager.getAuth()?.currentUser != null
                // Botones de inicio de sesión eliminados del panel principal

                val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
                var isIgnoringBatteryOpt by remember { mutableStateOf(SystemPermissionHelper.isIgnoringBatteryOptimizations(context)) }
                var hasOverlayPermission by remember { mutableStateOf(SystemPermissionHelper.hasOverlayPermission(context)) }
                var hasStoragePermission by remember { mutableStateOf(SystemPermissionHelper.hasStoragePermission(context)) }
                
                val requestStoragePermissionLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
                    contract = androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    hasStoragePermission = SystemPermissionHelper.hasStoragePermission(context)
                }

                DisposableEffect(lifecycleOwner) {
                    val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
                        if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                            isIgnoringBatteryOpt = SystemPermissionHelper.isIgnoringBatteryOptimizations(context)
                            hasOverlayPermission = SystemPermissionHelper.hasOverlayPermission(context)
                            hasStoragePermission = SystemPermissionHelper.hasStoragePermission(context)
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                val allOptimizationsGranted = hasOverlayPermission && isIgnoringBatteryOpt

                // Botones Superiores
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    androidx.compose.material3.OutlinedButton(
                        onClick = onNavigateToInfo,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("btn_about_top"),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            containerColor = HextechSurface.copy(alpha = 0.9f),
                            contentColor = HextechGold
                        ),
                        border = BorderStroke(1.2.dp, HextechGold.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Info"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    
                    androidx.compose.material3.OutlinedButton(
                        onClick = onNavigateToTutorial,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("btn_tutorial_top"),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            containerColor = HextechSurface.copy(alpha = 0.9f),
                            contentColor = HextechCyan
                        ),
                        border = BorderStroke(1.2.dp, HextechCyan.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Tutorial"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Card de Rendimiento en Segundo Plano (Se oculta automáticamente si todo está activo)
                if (!allOptimizationsGranted) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.BatteryChargingFull, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = tr("Rendimiento de Segundo Plano"),
                                            color = HextechGold,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = tr("Configuración necesaria para que el asistente no se cierre"),
                                            color = TextMuted,
                                            fontSize = 10.5.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // 1. Superposición
                            if (!hasOverlayPermission) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = tr("Ventana Flotante (Overlay)"),
                                            color = TextPrimary,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = tr("Requerido para mostrar recomendaciones sobre el juego"),
                                            color = DangerRed,
                                            fontSize = 10.5.sp
                                        )
                                    }
                                    OutlinedButton(
                                        onClick = { SystemPermissionHelper.openOverlaySettings(context) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                                        border = BorderStroke(1.dp, HextechCyan),
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                        modifier = Modifier.height(30.dp)
                                    ) {
                                        Text(tr("Activar"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }

                            // 2. Batería / Segundo plano
                            if (!isIgnoringBatteryOpt) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = tr("Ahorro de Batería"),
                                            color = TextPrimary,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = tr("Pon en 'Sin Restricciones' para no cerrarse"),
                                            color = TierAColor,
                                            fontSize = 10.5.sp
                                        )
                                    }
                                    OutlinedButton(
                                        onClick = { SystemPermissionHelper.requestIgnoreBatteryOptimization(context) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                                        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.8f)),
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                        modifier = Modifier.height(30.dp)
                                    ) {
                                        Text(tr("Ajustes"), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Orbe Hextech 3D Central de Activación Inmediata
                HextechOrbButton(
                    isActive = isAssistantActive,
                    onToggle = toggleAssistant,
                    enabled = true
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (isAssistantActive) tr("Asistente Hextech Activo • Toca la cámara flotante")
                           else tr("Presiona ACTIVAR para iniciar el Asistente Flotante"),
                    color = if (isAssistantActive) HextechCyan else TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                
                OfflineResourceDownloadCard()
                Spacer(modifier = Modifier.height(16.dp))

                // Barra de Redes Sociales del Creador (Instagram, Facebook, WhatsApp, Discord)
                com.example.ui.components.CreatorSocialMediaBar(
                    modifier = Modifier.fillMaxWidth(0.92f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Derechos de autor y créditos de creador
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(HextechSurface.copy(alpha = 0.5f))
                        .border(1.dp, HextechCardBorder.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "© 2026 Diego Barba Chavez",
                        color = HextechGoldLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = tr("Creador & Desarrollador Principal • Todos los derechos reservados"),
                        color = TextMuted,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Alfa v${com.example.BuildConfig.VERSION_NAME} (${com.example.BuildConfig.VERSION_CODE})",
                        color = TextMuted.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
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

        if (showDonationDialog) {
            com.example.ui.components.DonationDialog(
                onDismiss = { showDonationDialog = false }
            )
        }

        if (showThemeDialog) {
            com.example.ui.components.ThemeCustomizationBottomSheet(
                isPremium = com.example.util.SubscriptionManager.isPremium.collectAsState().value,
                onDismiss = { showThemeDialog = false }
            )
        }
    }
}


@Composable
fun OfflineResourceDownloadCard() {
    val context = LocalContext.current
    val downloadState by OfflineResourceManager.downloadState.collectAsState()
    val progress by OfflineResourceManager.progress.collectAsState()
    val downloaded by OfflineResourceManager.downloadedCount.collectAsState()
    val total by OfflineResourceManager.totalCount.collectAsState()

    val isAlreadyCompleted = remember(context, downloadState) {
        OfflineResourceManager.isCompleted(context) || downloadState == DownloadState.COMPLETED
    }

    if (isAlreadyCompleted) {
        return
    }

    var isMinimized by remember { mutableStateOf(false) }

    if (isMinimized) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 4.dp)
                .clickable { isMinimized = false },
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.8f)),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.CloudDownload,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(18.dp)
                    )
                    Column {
                        Text(
                            text = tr("Descarga de Recursos"),
                            color = TextPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (downloadState == DownloadState.DOWNLOADING || downloadState == DownloadState.PAUSED) {
                            Text(
                                text = "${(progress * 100).toInt()}% ($downloaded / $total)",
                                color = if (downloadState == DownloadState.PAUSED) HextechGold else HextechCyan,
                                fontSize = 10.5.sp
                            )
                        }
                    }
                }
                IconButton(onClick = { isMinimized = false }, modifier = Modifier.size(28.dp)) {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = tr("Expandir"),
                        tint = HextechGold
                    )
                }
            }
        }
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.CloudDownload, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                        Text(
                            text = tr("Descarga de Recursos Offline"),
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (downloadState == DownloadState.DOWNLOADING || downloadState == DownloadState.PAUSED) {
                        IconButton(
                            onClick = { isMinimized = true },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = tr("Minimizar"),
                                tint = HextechGold
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(10.dp))
                
                Text(
                    text = tr("Imágenes (campeones, habilidades, objetos, runas y hechizos) para usar sin conexión y carga más rápida."),
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
                
                Spacer(modifier = Modifier.height(12.dp))

                when (downloadState) {
                    DownloadState.IDLE -> {
                        Button(
                            onClick = { OfflineResourceManager.startDownload(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(tr("Descargar Recursos"), fontWeight = FontWeight.Bold)
                        }
                    }
                    DownloadState.DOWNLOADING -> {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.fillMaxWidth().height(6.dp),
                            color = HextechCyan,
                            trackColor = HextechSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("$downloaded / $total (${(progress * 100).toInt()}%)", color = TextPrimary, fontSize = 12.sp)
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(
                                    onClick = { OfflineResourceManager.pauseDownload() },
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechGold),
                                    border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                                ) {
                                    Text(tr("Pausar"), fontSize = 12.sp)
                                }
                            }
                        }
                    }
                    DownloadState.PAUSED -> {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.fillMaxWidth().height(6.dp),
                            color = HextechGold,
                            trackColor = HextechSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(tr("Pausado") + " - $downloaded / $total", color = HextechGold, fontSize = 12.sp)
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(
                                    onClick = { OfflineResourceManager.cancelDownload() },
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DangerRed),
                                    border = BorderStroke(1.dp, DangerRed.copy(alpha = 0.6f)),
                                    contentPadding = PaddingValues(horizontal = 12.dp)
                                ) {
                                    Text(tr("Cancelar"), fontSize = 12.sp)
                                }
                                Button(
                                    onClick = { OfflineResourceManager.resumeDownload(context) },
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan, contentColor = HextechDarkBg),
                                    contentPadding = PaddingValues(horizontal = 12.dp)
                                ) {
                                    Text(tr("Reanudar"), fontSize = 12.sp)
                                }
                            }
                        }
                    }
                    DownloadState.COMPLETED -> {
                        // Will not be shown because isAlreadyCompleted handles it
                    }
                    DownloadState.ERROR -> {
                        Text(tr("Error en la descarga. Comprueba tu conexión."), color = DangerRed, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { OfflineResourceManager.startDownload(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(tr("Reintentar"), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
