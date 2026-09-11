package com.example.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import com.example.ui.components.PrivacyPolicyDialog
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
    onNavigateToFAQ: () -> Unit,
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
    var showPlansDialog by remember { mutableStateOf(false) }
    var showPrivacyPolicyDialog by remember { mutableStateOf(false) }
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

    LaunchedEffect(Unit) {
        val activity = context as? Activity
        if (activity?.intent?.getBooleanExtra("EXTRA_REQUEST_CAPTURE", false) == true) {
            activity.intent.removeExtra("EXTRA_REQUEST_CAPTURE")
            val mediaProjectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            mediaProjectionLauncher.launch(mediaProjectionManager.createScreenCaptureIntent())
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                val activity = context as? Activity
                if (activity?.intent?.getBooleanExtra("EXTRA_REQUEST_CAPTURE", false) == true) {
                    activity.intent.removeExtra("EXTRA_REQUEST_CAPTURE")
                    val mediaProjectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
                    mediaProjectionLauncher.launch(mediaProjectionManager.createScreenCaptureIntent())
                }
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
            val notices by com.example.data.AppNoticeManager.notices.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(6.dp))

                // 1. Panel de Noticias / Avisos configurados desde el Admin
                val activeNotices = notices.filter { it.isEnabled && (it.content.isNotBlank() || it.title.isNotBlank()) }
                val streamerNotices = activeNotices.filter { it.tag.equals("Streamer", true) }
                val adNotices = activeNotices.filter { it.tag.equals("Publicidad", true) }
                val otherNotices = activeNotices.filter { !it.tag.equals("Streamer", true) && !it.tag.equals("Publicidad", true) }

                val streamerIntervalValue by com.example.data.AppNoticeManager.streamerIntervalValue.collectAsState()
                val streamerIntervalUnit by com.example.data.AppNoticeManager.streamerIntervalUnit.collectAsState()

                val intervalMillis = remember(streamerIntervalValue, streamerIntervalUnit) {
                    val value = streamerIntervalValue.coerceAtLeast(1)
                    when (streamerIntervalUnit) {
                        "minutes" -> value * 60 * 1000L
                        "hours" -> value * 60 * 60 * 1000L
                        else -> value * 1000L
                    }
                }
                var streamerIndex by remember(streamerNotices.size) { mutableStateOf(0) }
                var adIndex by remember(adNotices.size) { mutableStateOf(0) }

                LaunchedEffect(streamerNotices.size, intervalMillis) {
                    if (streamerNotices.size > 1) {
                        while (true) {
                            kotlinx.coroutines.delay(intervalMillis)
                            streamerIndex = (streamerIndex + 1) % streamerNotices.size
                        }
                    } else {
                        streamerIndex = 0
                    }
                }

                LaunchedEffect(adNotices.size, intervalMillis) {
                    if (adNotices.size > 1) {
                        while (true) {
                            kotlinx.coroutines.delay(intervalMillis)
                            adIndex = (adIndex + 1) % adNotices.size
                        }
                    } else {
                        adIndex = 0
                    }
                }

                if (activeNotices.isNotEmpty()) {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        // 1. Streamer Notice Card (Rotating if > 1, static if == 1)
                        if (streamerNotices.isNotEmpty()) {
                            val currentStreamer = streamerNotices[streamerIndex.coerceIn(0, streamerNotices.size - 1)]
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .border(1.2.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(14.dp)),
                                colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Canal de Streamer Destacado", color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                        }
                                        if (streamerNotices.size > 1) {
                                            Surface(
                                                color = HextechCyan.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(4.dp),
                                                border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.4f))
                                            ) {
                                                Text(
                                                    text = "🔄 ${streamerIndex + 1}/${streamerNotices.size}",
                                                    color = HextechCyan,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(14.dp))

                                    var isFullscreenMedia by remember(currentStreamer.id) { mutableStateOf(false) }

                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        Surface(
                                            color = HextechGold.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(4.dp),
                                            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                                        ) {
                                            Text(
                                                text = currentStreamer.tag.uppercase(),
                                                color = HextechGold,
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = currentStreamer.title,
                                            color = TextPrimary,
                                            fontSize = 13.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        if (currentStreamer.content.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = currentStreamer.content,
                                                color = TextSecondary,
                                                fontSize = 12.sp,
                                                lineHeight = 16.sp
                                            )
                                        }

                                        if (currentStreamer.videoUrl.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                            com.example.ui.components.NoticeMediaViewer(
                                                mediaUrl = currentStreamer.videoUrl,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .aspectRatio(985f / 425f),
                                                onExpand = { isFullscreenMedia = true }
                                            )
                                        }

                                        if (isFullscreenMedia) {
                                            val mediaToExpand = if (currentStreamer.expandedImageUrl.isNotBlank()) currentStreamer.expandedImageUrl else currentStreamer.videoUrl
                                            com.example.ui.components.NoticeMediaFullscreenDialog(
                                                mediaUrl = mediaToExpand,
                                                onDismiss = { isFullscreenMedia = false }
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // 2. Publicidad Card (Rotating if > 1, static if == 1)
                        if (adNotices.isNotEmpty()) {
                            val currentAd = adNotices[adIndex.coerceIn(0, adNotices.size - 1)]
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .border(1.2.dp, HextechCyan.copy(alpha = 0.8f), RoundedCornerShape(14.dp)),
                                colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Campaign, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Publicidad y Patrocinadores", color = HextechCyan, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                        }
                                        if (adNotices.size > 1) {
                                            Surface(
                                                color = HextechGold.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(4.dp),
                                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                                            ) {
                                                Text(
                                                    text = "🔄 ${adIndex + 1}/${adNotices.size}",
                                                    color = HextechGold,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(14.dp))

                                    var isFullscreenAdMedia by remember(currentAd.id) { mutableStateOf(false) }

                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        Surface(
                                            color = HextechCyan.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(4.dp),
                                            border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.4f))
                                        ) {
                                            Text(
                                                text = "PUBLICIDAD",
                                                color = HextechCyan,
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = currentAd.title,
                                            color = TextPrimary,
                                            fontSize = 13.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        if (currentAd.content.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = currentAd.content,
                                                color = TextSecondary,
                                                fontSize = 12.sp,
                                                lineHeight = 16.sp
                                            )
                                        }

                                        if (currentAd.videoUrl.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                            com.example.ui.components.NoticeMediaViewer(
                                                mediaUrl = currentAd.videoUrl,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .aspectRatio(985f / 425f),
                                                onExpand = { isFullscreenAdMedia = true }
                                            )
                                        }

                                        if (isFullscreenAdMedia) {
                                            val mediaToExpand = if (currentAd.expandedImageUrl.isNotBlank()) currentAd.expandedImageUrl else currentAd.videoUrl
                                            com.example.ui.components.NoticeMediaFullscreenDialog(
                                                mediaUrl = mediaToExpand,
                                                onDismiss = { isFullscreenAdMedia = false }
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // 3. Other Notices Card
                        if (otherNotices.isNotEmpty()) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .border(1.2.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(14.dp)),
                                colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Campaign, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Avisos y Novedades Oficiales", color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.height(14.dp))

                                    otherNotices.forEachIndexed { idx, notice ->
                                        var isMediaVisible by remember(notice.id, notice.videoUrl) { mutableStateOf(true) }
                                        var isFullscreenMedia by remember(notice.id) { mutableStateOf(false) }

                                        Column(modifier = Modifier.fillMaxWidth()) {
                                            Surface(
                                                color = HextechGold.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(4.dp),
                                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                                            ) {
                                                Text(
                                                    text = notice.tag.uppercase(),
                                                    color = HextechGold,
                                                    fontSize = 9.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = notice.title,
                                                color = TextPrimary,
                                                fontSize = 13.5.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            if (notice.content.isNotBlank()) {
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = notice.content,
                                                    color = TextSecondary,
                                                    fontSize = 12.sp,
                                                    lineHeight = 16.sp
                                                )
                                            }

                                            if (notice.videoUrl.isNotBlank()) {
                                                Spacer(modifier = Modifier.height(8.dp))
                                                com.example.ui.components.NoticeMediaViewer(
                                                    mediaUrl = notice.videoUrl,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .aspectRatio(985f / 425f),
                                                    onExpand = { isFullscreenMedia = true }
                                                )
                                            }

                                            if (isFullscreenMedia) {
                                                val mediaToExpand = if (notice.expandedImageUrl.isNotBlank()) notice.expandedImageUrl else notice.videoUrl
                                                com.example.ui.components.NoticeMediaFullscreenDialog(
                                                    mediaUrl = mediaToExpand,
                                                    onDismiss = { isFullscreenMedia = false }
                                                )
                                            }

                                            if (idx < otherNotices.size - 1) {
                                                Spacer(modifier = Modifier.height(12.dp))
                                                Divider(color = HextechCardBorder.copy(alpha = 0.5f))
                                                Spacer(modifier = Modifier.height(12.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }


                
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
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Activar Botón y descripción abajo de las recomendaciones
                HextechOrbButton(
                    isActive = isAssistantActive,
                    onToggle = toggleAssistant,
                    enabled = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isAssistantActive) tr("Asistente Hextech Activo • Toca la cámara flotante")
                           else tr("Presiona ACTIVAR para iniciar el Asistente Flotante"),
                    color = if (isAssistantActive) HextechCyan else TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.weight(1f))

                // Botones de Información, Preguntas Frecuentes y Política de Privacidad apegados al final de la pantalla de inicio
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        androidx.compose.material3.OutlinedButton(
                            onClick = onNavigateToInfo,
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("btn_about_bottom"),
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
                                text = tr("Información"),
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp
                            )
                        }

                        androidx.compose.material3.OutlinedButton(
                            onClick = onNavigateToFAQ,
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("btn_faq_bottom"),
                            shape = RoundedCornerShape(12.dp),
                            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                                containerColor = HextechSurface.copy(alpha = 0.9f),
                                contentColor = HextechCyan
                            ),
                            border = BorderStroke(1.2.dp, HextechCyan.copy(alpha = 0.7f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = HextechCyan,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = tr("Preguntas Frecuentes"),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                letterSpacing = 0.3.sp
                            )
                        }
                    }

                    androidx.compose.material3.OutlinedButton(
                        onClick = { showPrivacyPolicyDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .testTag("btn_privacy_bottom"),
                        shape = RoundedCornerShape(12.dp),
                        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                            containerColor = HextechSurface.copy(alpha = 0.9f),
                            contentColor = HextechGoldLight
                        ),
                        border = BorderStroke(1.2.dp, HextechGold.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = tr("Políticas de Privacidad, Términos y Terceros"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp,
                            letterSpacing = 0.3.sp
                        )
                    }

                    // Barra de Redes Sociales del Creador (Instagram, Facebook, WhatsApp, Discord)
                    com.example.ui.components.CreatorSocialMediaBar(
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Derechos de autor y créditos de creador
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(HextechSurface.copy(alpha = 0.5f))
                            .border(1.dp, HextechCardBorder.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .padding(horizontal = 14.dp, vertical = 8.dp),
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
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Alfa v${com.example.BuildConfig.VERSION_NAME} (${com.example.BuildConfig.VERSION_CODE})",
                            color = TextMuted.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
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
                onOpenPremiumPlans = {
                    showThemeDialog = false
                    showPlansDialog = true
                },
                onDismiss = { showThemeDialog = false }
            )
        }

        if (showPlansDialog) {
            com.example.ui.components.SubscriptionPlansBottomSheet(
                onDismiss = { showPlansDialog = false }
            )
        }

        if (showPrivacyPolicyDialog) {
            PrivacyPolicyDialog(onDismiss = { showPrivacyPolicyDialog = false })
        }
    }



}
