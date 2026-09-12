package com.example.ui.screens

import android.app.Activity
import android.widget.Toast
import java.util.Locale
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
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
        // Pausar y silenciar inmediatamente cualquier video en reproducción
        com.example.ui.components.NoticeMediaPlaybackController.pauseAndMuteAll()
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

                // 1. Panel de Noticias / Avisos con agrupación dinámica por etiqueta y rotación automática
                val activeNotices = notices.filter { it.isEnabled && (it.content.isNotBlank() || it.title.isNotBlank()) }

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

                fun getTagColor(tag: String): Color {
                    val l = tag.lowercase(Locale.ROOT)
                    return when {
                        l.contains("importante") -> HextechGold
                        l.contains("publicidad") -> Color(0xFF00FF66)
                        l.contains("oferta") -> HextechCyan
                        l.contains("mantenimiento") -> Color(0xFFFF3333)
                        l.contains("noticia") -> Color(0xFFCC66FF)
                        l.contains("streamer") -> Color(0xFFFF66CC)
                        else -> HextechCyan
                    }
                }

                val sortedTags = remember(activeNotices) {
                    val grouped = activeNotices.groupBy { it.tag.trim() }
                    grouped.keys.sortedWith(Comparator { a, b ->
                        val aLower = a.lowercase(Locale.ROOT)
                        val bLower = b.lowercase(Locale.ROOT)
                        val aIsImportant = aLower.contains("importante") || aLower.contains("avisos importantes")
                        val bIsImportant = bLower.contains("importante") || bLower.contains("avisos importantes")
                        val aIsPub = aLower.contains("publicidad")
                        val bIsPub = bLower.contains("publicidad")

                        when {
                            aIsImportant && !bIsImportant -> -1
                            !aIsImportant && bIsImportant -> 1
                            aIsPub && !bIsPub -> -1
                            !aIsPub && bIsPub -> 1
                            else -> a.compareTo(b)
                        }
                    })
                }

                if (activeNotices.isNotEmpty()) {
                    val groupedNotices = remember(activeNotices) { activeNotices.groupBy { it.tag.trim() } }
                    val pinnedMap = remember { androidx.compose.runtime.mutableStateMapOf<String, Boolean>() }

                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        sortedTags.forEach { tag ->
                            val tagNotices = groupedNotices[tag] ?: emptyList()
                            if (tagNotices.isEmpty()) return@forEach
                            val tagColor = getTagColor(tag)
                            var currentIndex by remember(tag, tagNotices.size) { mutableStateOf(0) }
                            val isPinned = pinnedMap[tag] ?: false
                            var isFullscreenMedia by remember { mutableStateOf(false) }
                            var slideDirection by remember { mutableStateOf(1) } // 1 for next (right to left), -1 for prev (left to right)
                            var autoTimerTrigger by remember { mutableStateOf(0) }

                            // Auto-rotation timer: pauses completely when isFullscreenMedia or isPinned is active
                            LaunchedEffect(tag, tagNotices.size, intervalMillis, isPinned, isFullscreenMedia, autoTimerTrigger) {
                                if (tagNotices.size > 1 && !isPinned && !isFullscreenMedia) {
                                    while (true) {
                                        kotlinx.coroutines.delay(intervalMillis)
                                        if (!isFullscreenMedia && !isPinned) {
                                            slideDirection = 1
                                            currentIndex = (currentIndex + 1) % tagNotices.size
                                        }
                                    }
                                }
                            }

                            val currentNotice = tagNotices[currentIndex.coerceIn(0, tagNotices.size - 1)]

                            LaunchedEffect(currentNotice.id) {
                                com.example.data.AppNoticeAnalyticsManager.recordImpression(context, currentNotice.id, currentNotice.tag)
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .border(1.2.dp, tagColor.copy(alpha = 0.8f), RoundedCornerShape(14.dp)),
                                colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.95f))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Campaign, contentDescription = null, tint = tagColor, modifier = Modifier.size(20.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(tag, color = tagColor, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                        }
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            // Pin / Unpin button (Fijación de la publicación)
                                            IconButton(
                                                onClick = {
                                                    val newPinnedState = !isPinned
                                                    pinnedMap[tag] = newPinnedState
                                                    if (newPinnedState) {
                                                        Toast.makeText(context, "📌 Publicación fijada. No cambiará automáticamente.", Toast.LENGTH_SHORT).show()
                                                    } else {
                                                        Toast.makeText(context, "Fijación desactivada.", Toast.LENGTH_SHORT).show()
                                                    }
                                                },
                                                modifier = Modifier.size(28.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.PushPin,
                                                    contentDescription = if (isPinned) "Desfijar publicación" else "Fijar publicación",
                                                    tint = if (isPinned) tagColor else tagColor.copy(alpha = 0.4f),
                                                    modifier = Modifier.size(17.dp)
                                                )
                                            }

                                            if (tagNotices.size > 1) {
                                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    // Manual previous notice
                                                    IconButton(
                                                        onClick = {
                                                            if (isPinned) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "La publicación está fijada. Desactiva la fijación para cambiar de anuncio.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            } else {
                                                                slideDirection = -1
                                                                currentIndex = if (currentIndex > 0) currentIndex - 1 else tagNotices.size - 1
                                                                autoTimerTrigger++
                                                            }
                                                        },
                                                        modifier = Modifier.size(28.dp)
                                                    ) {
                                                        Text(
                                                            text = "<",
                                                            color = if (isPinned) tagColor.copy(alpha = 0.35f) else tagColor,
                                                            fontSize = 13.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    }
                                                    Surface(
                                                        color = tagColor.copy(alpha = if (isPinned) 0.3f else 0.2f),
                                                        shape = RoundedCornerShape(4.dp),
                                                        border = BorderStroke(1.dp, tagColor.copy(alpha = if (isPinned) 0.8f else 0.4f)),
                                                        modifier = Modifier.clickable {
                                                            if (isPinned) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "La publicación está fijada. Desactiva la fijación para cambiar de anuncio.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                        }
                                                    ) {
                                                        Text(
                                                            text = "${if (isPinned) "📌 " else "🔄 "}${currentIndex + 1}/${tagNotices.size}",
                                                            color = tagColor,
                                                            fontSize = 9.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                        )
                                                    }
                                                    // Manual next notice
                                                    IconButton(
                                                        onClick = {
                                                            if (isPinned) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "La publicación está fijada. Desactiva la fijación para cambiar de anuncio.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            } else {
                                                                slideDirection = 1
                                                                currentIndex = (currentIndex + 1) % tagNotices.size
                                                                autoTimerTrigger++
                                                            }
                                                        },
                                                        modifier = Modifier.size(28.dp)
                                                    ) {
                                                        Text(
                                                            text = ">",
                                                            color = if (isPinned) tagColor.copy(alpha = 0.35f) else tagColor,
                                                            fontSize = 13.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    }
                                                }
                                            } else if (isPinned) {
                                                Surface(
                                                    color = tagColor.copy(alpha = 0.2f),
                                                    shape = RoundedCornerShape(4.dp),
                                                    border = BorderStroke(1.dp, tagColor.copy(alpha = 0.4f))
                                                ) {
                                                    Text(
                                                        text = "📌 Fijo",
                                                        color = tagColor,
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Animated content transition between notices
                                    AnimatedContent(
                                        targetState = currentNotice,
                                        transitionSpec = {
                                            if (slideDirection >= 0) {
                                                (slideInHorizontally(animationSpec = tween(350)) { width -> width } + fadeIn(animationSpec = tween(350)))
                                                    .togetherWith(slideOutHorizontally(animationSpec = tween(350)) { width -> -width } + fadeOut(animationSpec = tween(350)))
                                            } else {
                                                (slideInHorizontally(animationSpec = tween(350)) { width -> -width } + fadeIn(animationSpec = tween(350)))
                                                    .togetherWith(slideOutHorizontally(animationSpec = tween(350)) { width -> width } + fadeOut(animationSpec = tween(350)))
                                            }.using(
                                                SizeTransform(clip = false)
                                            )
                                        },
                                        label = "NoticeAnimatedContent"
                                    ) { noticeItem ->
                                        Column(modifier = Modifier.fillMaxWidth()) {
                                            Surface(
                                                color = tagColor.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(4.dp),
                                                border = BorderStroke(1.dp, tagColor.copy(alpha = 0.4f))
                                            ) {
                                                Text(
                                                    text = noticeItem.tag.uppercase(),
                                                    color = tagColor,
                                                    fontSize = 9.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = noticeItem.title,
                                                color = try { Color(android.graphics.Color.parseColor(noticeItem.titleColor)) } catch (_: Exception) { HextechGold },
                                                fontSize = 13.5.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            if (noticeItem.content.isNotBlank()) {
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = noticeItem.content,
                                                    color = try { Color(android.graphics.Color.parseColor(noticeItem.contentColor)) } catch (_: Exception) { TextSecondary },
                                                    fontSize = 12.sp,
                                                    lineHeight = 16.sp
                                                )
                                            }

                                            if (noticeItem.videoUrl.isNotBlank()) {
                                                Spacer(modifier = Modifier.height(8.dp))
                                                com.example.ui.components.NoticeMediaViewer(
                                                    mediaUrl = noticeItem.videoUrl,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .aspectRatio(985f / 425f),
                                                    onExpand = {
                                                        com.example.data.AppNoticeAnalyticsManager.recordFullscreen(context, currentNotice.id)
                                                        isFullscreenMedia = true
                                                    }
                                                )
                                            }
                                        }
                                    }

                                    // Fullscreen Dialog (freezes background and provides clean dedicated experience)
                                    if (isFullscreenMedia) {
                                        val mediaToExpand = if (currentNotice.expandedImageUrl.isNotBlank()) currentNotice.expandedImageUrl else currentNotice.videoUrl
                                        com.example.ui.components.NoticeMediaFullscreenDialog(
                                            mediaUrl = mediaToExpand,
                                            externalUrl = currentNotice.externalUrl,
                                            noticeId = currentNotice.id,
                                            onDismiss = { isFullscreenMedia = false }
                                        )
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

                    // Derechos de autor y créditos
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
                            text = tr("Desarrollador Principal • Todos los derechos reservados"),
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
