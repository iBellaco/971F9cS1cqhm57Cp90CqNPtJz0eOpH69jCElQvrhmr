package com.example.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.example.MainActivity
import com.example.R
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.service.screen.DraftVisionScanner
import com.example.service.screen.ScreenCaptureManager
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.theme.AllyBlue
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TierSPlusColor
import com.example.util.AppLogger
import com.example.util.LocalLanguage
import com.example.util.tr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    private var screenCaptureManager: ScreenCaptureManager? = null

    private var windowManager: WindowManager? = null
    private var floatingComposeView: ComposeView? = null
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val store = ViewModelStore()
    private val savedStateRegistryController = SavedStateRegistryController.create(this)

    override val lifecycle: Lifecycle get() = lifecycleRegistry
    override val viewModelStore: ViewModelStore get() = store
    override val savedStateRegistry: SavedStateRegistry get() = savedStateRegistryController.savedStateRegistry

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        try {
            screenCaptureManager = ScreenCaptureManager(this)
            savedStateRegistryController.performRestore(null)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

            createNotificationChannel()
            val notification = buildForegroundNotification()
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    startForeground(
                        NOTIFICATION_ID,
                        notification,
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    startForeground(
                        NOTIFICATION_ID,
                        notification,
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MANIFEST
                    )
                } else {
                    startForeground(NOTIFICATION_ID, notification)
                }
            } catch (e: Exception) {
                AppLogger.w("FloatingService", "Fallback foreground service start: ${e.message}")
                try {
                    startForeground(NOTIFICATION_ID, notification)
                } catch (_: Exception) {}
            }

            createFloatingOverlay()
        } catch (e: Exception) {
            AppLogger.e("FloatingService", "Error starting floating service", e)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            stopSelf()
            return START_NOT_STICKY
        }
        if (ScreenCaptureManager.pendingMediaProjectionData != null) {
            val success = screenCaptureManager?.initializeProjection(
                ScreenCaptureManager.pendingMediaProjectionResultCode,
                ScreenCaptureManager.pendingMediaProjectionData!!
            )
            if (success == true) {
                AppLogger.d("FloatingService", "ScreenCaptureManager initialized from pending intent.")
            }
            ScreenCaptureManager.pendingMediaProjectionData = null
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        store.clear()

        removeFloatingOverlay()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Asistente Flotante Wild Rift",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Mantiene activo el asistente en superposición sobre Wild Rift"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun buildForegroundNotification(): Notification {
        val openAppIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            openAppIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val stopIntent = Intent(this, FloatingAssistantService::class.java).apply {
            action = ACTION_STOP
        }
        val stopPendingIntent = PendingIntent.getService(
            this,
            1,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Wild Rift Coach Activo")
            .setContentText("Superposición en vivo sobre Wild Rift • Toca para abrir")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .addAction(0, "Detener", stopPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun createFloatingOverlay() {
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val density = displayMetrics.density
        val marginPx = (8 * density).toInt()
        val cardWidthPx = (330 * density).toInt()
        val cardHeightPx = (520 * density).toInt()
        val bubbleSizePx = (56 * density).toInt()

        var isOverlayExpanded = false

        val layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = (screenWidth - bubbleSizePx - marginPx * 2).coerceAtLeast(marginPx)
            y = (120 * density).toInt()
        }

        floatingComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)

            setContent {
                val sharedPrefs = remember { getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
                var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }

                DisposableEffect(sharedPrefs) {
                    val listener = android.content.SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
                        if (key == "selected_language") {
                            selectedLanguage = prefs.getString(key, "es") ?: "es"
                        }
                    }
                    sharedPrefs.registerOnSharedPreferenceChangeListener(listener)
                    onDispose {
                        sharedPrefs.unregisterOnSharedPreferenceChangeListener(listener)
                    }
                }

                androidx.compose.runtime.CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
                    MyApplicationTheme {
                        FloatingOverlayContent(
                            screenCaptureManager = screenCaptureManager,
                            onClose = { stopSelf() },
                            onDragDelta = { dx, dy, isDragging, isEnded ->
                                val currentWidth = if (isOverlayExpanded) cardWidthPx else bubbleSizePx
                                val currentHeight = if (isOverlayExpanded) cardHeightPx else bubbleSizePx
                                val maxX = (screenWidth - currentWidth - marginPx).coerceAtLeast(marginPx)
                                val maxY = (screenHeight - currentHeight - marginPx).coerceAtLeast(marginPx)
                                
                                params.x = (params.x + dx).coerceIn(marginPx, maxX)
                                params.y = (params.y + dy).coerceIn(marginPx, maxY)

                                // Zona de peligro / desactivación: cuando se encuentra en el fondo de la pantalla (últimos 130dp)
                                val isInDangerZone = params.y >= (screenHeight - currentHeight - (40 * density).toInt())

                                if (isEnded && isInDangerZone) {
                                    stopSelf()
                                } else {
                                    try {
                                        windowManager?.updateViewLayout(this@apply, params)
                                    } catch (_: Exception) {}
                                }
                            },
                            onExpandedChange = { expanded ->
                                isOverlayExpanded = expanded
                                if (expanded) {
                                    if (params.x + cardWidthPx > screenWidth - marginPx) {
                                        params.x = (screenWidth - cardWidthPx - marginPx).coerceAtLeast(marginPx)
                                    }
                                    if (params.y + cardHeightPx > screenHeight - marginPx) {
                                        params.y = (screenHeight - cardHeightPx - marginPx).coerceAtLeast(marginPx)
                                    }
                                }
                                try {
                                    windowManager?.updateViewLayout(this@apply, params)
                                } catch (_: Exception) {}
                            }
                        )
                    }
                }
            }
        }

        try {
            windowManager?.addView(floatingComposeView, params)
        } catch (_: Exception) {}
    }

    private fun removeFloatingOverlay() {
        try {
            if (floatingComposeView != null) {
                windowManager?.removeView(floatingComposeView)
                floatingComposeView = null
            }
        } catch (_: Exception) {}
    }

    companion object {
        const val ACTION_START = "ACTION_START_FLOATING_ASSISTANT"
        const val ACTION_STOP = "ACTION_STOP_FLOATING_ASSISTANT"
        const val CHANNEL_ID = "wildrift_overlay_channel"
        const val NOTIFICATION_ID = 2001
    }
}

@Composable
private fun FloatingOverlayContent(
    screenCaptureManager: ScreenCaptureManager?,
    onClose: () -> Unit,
    onDragDelta: (dx: Int, dy: Int, isDragging: Boolean, isEnded: Boolean) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(false) }
    var activeRole by remember { mutableStateOf(LaneRole.MID) }
    var isFirstPick by remember { mutableStateOf(false) }

    val allies = remember { mutableStateListOf<Champion>() }
    val enemies = remember { mutableStateListOf<Champion>() }

    var isScanning by remember { mutableStateOf(false) }
    var autoScanEnabled by remember { mutableStateOf(true) }
    var scanNoticeMessage by remember { mutableStateOf<String?>(null) }

    var isDraggingBubble by remember { mutableStateOf(false) }
    var dragAccumulatedY by remember { mutableFloatStateOf(0f) }
    var isNearCloseThreshold by remember { mutableStateOf(false) }

    var selectedChampionDetail by remember { mutableStateOf<Champion?>(null) }
    var showChampionPickerForSlot by remember { mutableStateOf<Pair<Boolean, Int>?>(null) } // Pair(isAlly, slotIndex)

    val analysis = remember(activeRole, isFirstPick, allies.toList(), enemies.toList()) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = allies,
            enemies = enemies,
            isFirstPick = isFirstPick
        )
    }

    // Auto-Scan Loop en segundo plano cada 2.5 segundos mientras esté activo
    LaunchedEffect(autoScanEnabled) {
        if (!autoScanEnabled) return@LaunchedEffect
        while (true) {
            delay(2500)
            if (screenCaptureManager != null && screenCaptureManager.isReady() && !isScanning) {
                try {
                    val bitmap = screenCaptureManager.captureCurrentFrame()
                    if (bitmap != null) {
                        val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)
                        if (result.isSuccessful) {
                            var newAlliesAdded = 0
                            var newEnemiesAdded = 0
                            
                            result.allies.forEach { champ ->
                                if (allies.none { it.id == champ.id } && allies.size < 5) {
                                    allies.add(champ)
                                    newAlliesAdded++
                                }
                            }
                            result.enemies.forEach { champ ->
                                if (enemies.none { it.id == champ.id } && enemies.size < 5) {
                                    enemies.add(champ)
                                    newEnemiesAdded++
                                }
                            }
                            if (newAlliesAdded > 0 || newEnemiesAdded > 0) {
                                scanNoticeMessage = "⚡ Auto-Scan: +${newAlliesAdded + newEnemiesAdded} picks detectados"
                                delay(3000)
                                scanNoticeMessage = null
                            }
                        }
                    }
                } catch (_: Exception) {}
            }
        }
    }

    fun triggerManualScan() {
        isScanning = true
        scanNoticeMessage = "Escaneando selección en directo..."
        coroutineScope.launch(Dispatchers.IO) {
            val bitmap = screenCaptureManager?.captureCurrentFrame()
            if (bitmap != null) {
                val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)
                withContext(Dispatchers.Main) {
                    if (result.isSuccessful) {
                        result.allies.forEach { champ ->
                            if (allies.none { it.id == champ.id } && allies.size < 5) {
                                allies.add(champ)
                            }
                        }
                        result.enemies.forEach { champ ->
                            if (enemies.none { it.id == champ.id } && enemies.size < 5) {
                                enemies.add(champ)
                            }
                        }
                        scanNoticeMessage = "✅ Escaneo exitoso (${result.allies.size + result.enemies.size} detectados)"
                    } else {
                        scanNoticeMessage = "ℹ️ No se detectaron nombres legibles. Asegúrate de estar en Selección de Campeones."
                    }
                    isScanning = false
                }
            } else {
                withContext(Dispatchers.Main) {
                    scanNoticeMessage = "⚠️ No hay frame de captura disponible"
                    isScanning = false
                }
            }
            delay(3500)
            scanNoticeMessage = null
        }
    }

    Box(modifier = Modifier.padding(2.dp)) {
        Column(horizontalAlignment = Alignment.Start) {
            if (!isExpanded) {
                // Minimized Floating Bubble
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val bubbleBorderColor by animateColorAsState(
                        targetValue = if (isNearCloseThreshold) DangerRed else (if (isScanning) HextechCyan else HextechGold),
                        animationSpec = tween(200)
                    )

                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .scale(if (isNearCloseThreshold) 0.9f else 1.0f)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = if (isNearCloseThreshold) listOf(DangerRed, Color(0xFF5A0000), HextechDarkBg)
                                    else listOf(HextechCyan, Color(0xFF005A82), HextechDarkBg)
                                )
                            )
                            .border(2.5.dp, bubbleBorderColor, CircleShape)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = {
                                        isDraggingBubble = true
                                        dragAccumulatedY = 0f
                                        isNearCloseThreshold = false
                                    },
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        dragAccumulatedY += dragAmount.y
                                        // Detecta si se arrastra hacia el borde inferior para activar alerta visual
                                        isNearCloseThreshold = dragAccumulatedY > 180f
                                        onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                                    },
                                    onDragEnd = {
                                        isDraggingBubble = false
                                        onDragDelta(0, 0, false, isNearCloseThreshold)
                                        dragAccumulatedY = 0f
                                        isNearCloseThreshold = false
                                    },
                                    onDragCancel = {
                                        isDraggingBubble = false
                                        dragAccumulatedY = 0f
                                        isNearCloseThreshold = false
                                    }
                                )
                            }
                            .clickable {
                                isExpanded = true
                                onExpandedChange(true)
                                if (allies.isEmpty() && enemies.isEmpty()) {
                                    triggerManualScan()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isScanning) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(36.dp),
                                color = HextechCyan,
                                strokeWidth = 3.dp
                            )
                        } else {
                            Icon(
                                imageVector = if (isNearCloseThreshold) Icons.Default.Close else Icons.Default.Shield,
                                contentDescription = "Wild Rift Drafting Coach",
                                tint = if (isNearCloseThreshold) DangerRed else Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        if (!isScanning && !isNearCloseThreshold) {
                            // Pulsing green auto-scan indicator
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .align(Alignment.TopEnd)
                                    .clip(CircleShape)
                                    .background(if (autoScanEnabled) Color(0xFF00FF7F) else HextechGold)
                            )
                        }
                    }

                    // Alerta o Indicador de deslizamiento
                    Spacer(modifier = Modifier.height(4.dp))
                    if (isNearCloseThreshold) {
                        Box(
                            modifier = Modifier
                                .background(DangerRed.copy(alpha = 0.95f), RoundedCornerShape(8.dp))
                                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "🔥 " + tr("SOLTAR PARA CERRAR"),
                                color = Color.White,
                                fontSize = 9.5.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    } else if (isDraggingBubble) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.75f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = tr("↓ Al fondo para desactivar"),
                                color = TextMuted,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Expanded Drafting Hub
            AnimatedVisibility(
                visible = isExpanded,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                var isDraggingPanel by remember { mutableStateOf(false) }
                var panelDragY by remember { mutableFloatStateOf(0f) }
                var isPanelNearClose by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .widthIn(min = 300.dp, max = 340.dp)
                        .heightIn(max = 530.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.98f)),
                    border = androidx.compose.foundation.BorderStroke(
                        if (isPanelNearClose) 2.5.dp else 1.5.dp,
                        if (isPanelNearClose) DangerRed else HextechGold
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        // Header con barra de arrastre y controles
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = {
                                            isDraggingPanel = true
                                            panelDragY = 0f
                                            isPanelNearClose = false
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            panelDragY += dragAmount.y
                                            isPanelNearClose = panelDragY > 160f
                                            onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                                        },
                                        onDragEnd = {
                                            isDraggingPanel = false
                                            onDragDelta(0, 0, false, isPanelNearClose)
                                            panelDragY = 0f
                                            isPanelNearClose = false
                                        },
                                        onDragCancel = {
                                            isDraggingPanel = false
                                            panelDragY = 0f
                                            isPanelNearClose = false
                                        }
                                    )
                                }
                                .padding(bottom = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Shield, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text("DRAFTING COACH", color = HextechGold, fontWeight = FontWeight.Black, fontSize = 12.5.sp)
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(if (autoScanEnabled) Color(0xFF00FF7F) else HextechGold)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = if (autoScanEnabled) tr("Auto-Scan Activo") else tr("Escaneo Manual"),
                                            color = if (autoScanEnabled) Color(0xFF00FF7F) else TextMuted,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Botón Escaneo Manual
                                IconButton(
                                    onClick = { triggerManualScan() },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    if (isScanning) {
                                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = HextechCyan, strokeWidth = 2.dp)
                                    } else {
                                        Icon(Icons.Default.FlashOn, contentDescription = "Escanear", tint = HextechCyan, modifier = Modifier.size(18.dp))
                                    }
                                }

                                // Botón Limpiar Draft
                                IconButton(
                                    onClick = {
                                        allies.clear()
                                        enemies.clear()
                                        selectedChampionDetail = null
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.DeleteSweep, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(18.dp))
                                }

                                // Botón Minimizar
                                IconButton(
                                    onClick = {
                                        isExpanded = false
                                        onExpandedChange(false)
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minimizar", tint = TextPrimary, modifier = Modifier.size(20.dp))
                                }
                            }
                        }

                        // Banner de estado de escaneo si existe
                        if (scanNoticeMessage != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = scanNoticeMessage ?: "",
                                    color = HextechCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                        }

                        // Contenido Scrollable del Drafting
                        Column(
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            // 1. TABLERO DE DRAFT (5 ALIADOS VS 5 ENEMIGOS)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                // Columna Aliados (Azul)
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurface)
                                        .border(1.dp, AllyBlue.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                        .padding(6.dp)
                                ) {
                                    Text("🔵 " + tr("Aliados") + " (${allies.size}/5)", color = AllyBlue, fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    for (i in 0 until 5) {
                                        val champ = allies.getOrNull(i)
                                        DraftSlotItem(
                                            slotIndex = i + 1,
                                            champion = champ,
                                            isAlly = true,
                                            onSlotClick = {
                                                if (champ != null) {
                                                    selectedChampionDetail = champ
                                                } else {
                                                    showChampionPickerForSlot = Pair(true, i)
                                                }
                                            },
                                            onRemoveClick = {
                                                if (champ != null) allies.remove(champ)
                                            }
                                        )
                                        if (i < 4) Spacer(modifier = Modifier.height(3.dp))
                                    }
                                }

                                // Columna Enemigos (Rojo)
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurface)
                                        .border(1.dp, DangerRed.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                        .padding(6.dp)
                                ) {
                                    Text("🔴 " + tr("Enemigos") + " (${enemies.size}/5)", color = DangerRed, fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    for (i in 0 until 5) {
                                        val champ = enemies.getOrNull(i)
                                        DraftSlotItem(
                                            slotIndex = i + 1,
                                            champion = champ,
                                            isAlly = false,
                                            onSlotClick = {
                                                if (champ != null) {
                                                    selectedChampionDetail = champ
                                                } else {
                                                    showChampionPickerForSlot = Pair(false, i)
                                                }
                                            },
                                            onRemoveClick = {
                                                if (champ != null) enemies.remove(champ)
                                            }
                                        )
                                        if (i < 4) Spacer(modifier = Modifier.height(3.dp))
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // 2. SELECTOR DE MI ROL / LÍNEA & BLIND PICK
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                LaneRole.entries.forEach { role ->
                                    val isSelected = activeRole == role
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(if (isSelected) HextechCyan else HextechSurface)
                                            .border(1.dp, if (isSelected) HextechGold else HextechCardBorder, RoundedCornerShape(6.dp))
                                            .clickable { activeRole = role }
                                            .padding(vertical = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = tr(role.shortName),
                                            fontSize = 9.5.sp,
                                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal,
                                            color = if (isSelected) HextechDarkBg else TextPrimary
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "⚔️ " + tr("RECOMENDACIÓN:") + " ${tr(activeRole.displayName)}",
                                    color = HextechGold,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = if (isFirstPick) "⭐ " + tr("1ª Elección (Blind)") else "🎯 " + tr("Counter Pick"),
                                    color = if (isFirstPick) HextechGold else HextechCyan,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(HextechSurface)
                                        .border(0.5.dp, if (isFirstPick) HextechGold else HextechCyan, RoundedCornerShape(4.dp))
                                        .clickable { isFirstPick = !isFirstPick }
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            // 3. MEJORES PICKS RECOMENDADOS POR EL COACH
                            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                analysis.recommendations.take(3).forEach { pick ->
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable { selectedChampionDetail = pick.champion },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            ChampionAvatar(champion = pick.champion, size = 38.dp)
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(pick.champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(3.dp))
                                                            .background(TierSPlusColor)
                                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                                    ) {
                                                        Text(pick.champion.tier, color = Color.Black, fontSize = 8.sp, fontWeight = FontWeight.Black)
                                                    }
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text("WR: ${pick.estimatedWinrate}%", color = HextechGold, fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
                                                }
                                                Text(pick.advantageBadge, color = HextechCyan, fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
                                                Text(pick.tacticalReason, color = TextMuted, fontSize = 8.5.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                            }
                                        }
                                    }
                                }
                            }

                            // 4. DETALLE RÁPIDO DE CAMPEÓN SI ESTÁ SELECCIONADO
                            if (selectedChampionDetail != null) {
                                val champ = selectedChampionDetail!!
                                Spacer(modifier = Modifier.height(8.dp))
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = HextechSurfaceVariant),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan)
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "🛡️ ${champ.name} • ${tr("Runas y Core")}",
                                                color = HextechGold,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 11.sp
                                            )
                                            Text(
                                                text = "✕ " + tr("Cerrar"),
                                                color = DangerRed,
                                                fontSize = 9.5.sp,
                                                modifier = Modifier.clickable { selectedChampionDetail = null }
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "${tr("Runa Clave")}: ${champ.recommendedRunes}",
                                            color = HextechCyan,
                                            fontSize = 9.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        if (champ.runeTreeDetails.isNotBlank()) {
                                            Text(
                                                text = champ.runeTreeDetails,
                                                color = TextPrimary,
                                                fontSize = 8.5.sp,
                                                lineHeight = 11.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(
                                            text = "${tr("Objetos Core")}: " + champ.coreItems.take(4).joinToString(", "),
                                            color = HextechGoldLight,
                                            fontSize = 9.sp,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Footer / Desactivar alerta
                        if (isPanelNearClose) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DangerRed)
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🔥 " + tr("SOLTAR AQUÍ PARA CERRAR ASISTENTE"),
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "✕ " + tr("Detener Asistente"),
                                    color = DangerRed,
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .clickable { onClose() }
                                        .padding(4.dp)
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = tr("Auto-Scan"),
                                        color = TextMuted,
                                        fontSize = 9.5.sp,
                                        modifier = Modifier.padding(end = 4.dp)
                                    )
                                    Switch(
                                        checked = autoScanEnabled,
                                        onCheckedChange = { autoScanEnabled = it },
                                        modifier = Modifier.scale(0.7f),
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = HextechDarkBg,
                                            checkedTrackColor = Color(0xFF00FF7F)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal de selección rápida de campeón si el usuario toca un slot manual
    if (showChampionPickerForSlot != null) {
        val (isAllySlot, _) = showChampionPickerForSlot!!
        var searchChampQuery by remember { mutableStateOf("") }
        val filteredList = remember(searchChampQuery) {
            WildRiftRepository.champions.filter {
                searchChampQuery.isBlank() || it.name.contains(searchChampQuery, ignoreCase = true)
            }
        }

        Dialog(onDismissRequest = { showChampionPickerForSlot = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, if (isAllySlot) AllyBlue else DangerRed)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isAllySlot) "🔵 " + tr("Elegir Aliado") else "🔴 " + tr("Elegir Enemigo"),
                            color = if (isAllySlot) AllyBlue else DangerRed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        IconButton(onClick = { showChampionPickerForSlot = null }, modifier = Modifier.size(24.dp)) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                        }
                    }

                    OutlinedTextField(
                        value = searchChampQuery,
                        onValueChange = { searchChampQuery = it },
                        placeholder = { Text(tr("Buscar campeón..."), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth().height(46.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 11.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(filteredList) { champ ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .clickable {
                                        if (isAllySlot) {
                                            if (allies.none { it.id == champ.id } && allies.size < 5) {
                                                allies.add(champ)
                                            }
                                        } else {
                                            if (enemies.none { it.id == champ.id } && enemies.size < 5) {
                                                enemies.add(champ)
                                            }
                                        }
                                        showChampionPickerForSlot = null
                                    }
                                    .padding(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ChampionAvatar(champion = champ, size = 30.dp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(champ.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Spacer(modifier = Modifier.weight(1f))
                                Text(champ.tier, color = HextechGold, fontWeight = FontWeight.Black, fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DraftSlotItem(
    slotIndex: Int,
    champion: Champion?,
    isAlly: Boolean,
    onSlotClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(HextechDarkBg)
            .border(0.5.dp, if (isAlly) AllyBlue.copy(alpha = 0.4f) else DangerRed.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
            .clickable { onSlotClick() }
            .padding(horizontal = 4.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (champion != null) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                ChampionAvatar(champion = champion, size = 22.dp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = champion.name,
                    color = TextPrimary,
                    fontSize = 9.5.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Eliminar",
                tint = TextMuted,
                modifier = Modifier
                    .size(14.dp)
                    .clickable { onRemoveClick() }
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(HextechSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir", tint = TextMuted, modifier = Modifier.size(12.dp))
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${tr("Slot")} $slotIndex",
                    color = TextMuted,
                    fontSize = 9.sp
                )
            }
        }
    }
}
