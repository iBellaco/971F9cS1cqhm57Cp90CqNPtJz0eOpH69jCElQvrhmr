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
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.model.Champion
import com.example.model.LaneRole
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AppAssetImage
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
import com.example.service.screen.ScreenCaptureManager
import com.example.service.screen.DraftVisionScanner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.R
import com.example.data.WildRiftRepository
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.components.CooldownTrackerPanel
import com.example.ui.components.DamagePenetrationCalculator
import com.example.util.tr
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
import kotlin.math.abs

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startForeground(
                    NOTIFICATION_ID,
                    notification,
                    android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE or 32
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

            createFloatingOverlay()
        } catch (e: Exception) {
            com.example.util.AppLogger.e("FloatingService", "Error starting floating service", e)
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
                com.example.util.AppLogger.d("FloatingService", "ScreenCaptureManager initialized from pending intent.")
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
        val marginPx = (10 * density).toInt()
        val cardWidthPx = (320 * density).toInt()
        val cardHeightPx = (460 * density).toInt()
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
                // Use a mutable state and update it by observing SharedPreferences
                var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }

                androidx.compose.runtime.DisposableEffect(sharedPrefs) {
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

                androidx.compose.runtime.CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) {
                    MyApplicationTheme {
                        FloatingOverlayContent(
                            screenCaptureManager = screenCaptureManager,
                            onClose = { stopSelf() },
                            onDragDelta = { dx, dy ->
                                val currentWidth = if (isOverlayExpanded) cardWidthPx else bubbleSizePx
                                val currentHeight = if (isOverlayExpanded) cardHeightPx else bubbleSizePx
                                val maxX = (screenWidth - currentWidth - marginPx).coerceAtLeast(marginPx)
                                val maxY = (screenHeight - currentHeight - marginPx).coerceAtLeast(marginPx)
                                params.x = (params.x + dx).coerceIn(marginPx, maxX)
                                params.y = (params.y + dy).coerceIn(marginPx, maxY)
                                try {
                                    windowManager?.updateViewLayout(this@apply, params)
                                } catch (_: Exception) {}
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
    onDragDelta: (Int, Int) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) } // 0: Draft, 1: Objetivos, 2: Objetos, 3: Runas
    var activeRole by remember { mutableStateOf(LaneRole.MID) }
    var isFirstPick by remember { mutableStateOf(false) }
    var lockedChampion by remember { mutableStateOf<Champion?>(null) }

    val allies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }
    val enemies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }

    val analysis = remember(activeRole, isFirstPick, allies, enemies) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = allies,
            enemies = enemies,
            isFirstPick = isFirstPick
        )
    }

    val topPick = analysis.recommendations.firstOrNull()

    Box(modifier = Modifier.padding(2.dp)) {
        // Notification Manager
        androidx.compose.runtime.LaunchedEffect(Unit) {
            while (true) {
                val now = System.currentTimeMillis()
                val expiredKeys = com.example.ui.components.CooldownTrackerStateHolder.activeTimers.filter { it.value < now }.keys.toList()
                expiredKeys.forEach { key ->
                    com.example.ui.components.CooldownTrackerStateHolder.activeTimers.remove(key)
                    val parts = key.split("_")
                    if (parts.size == 2) {
                        val roleName = parts[0]
                        val spellId = parts[1]
                        val spell = com.example.ui.components.DEFAULT_TRACKED_SPELLS.find { it.id == spellId }
                        if (spell != null) {
                            val roleLabel = LaneRole.valueOf(roleName).shortName
                            val notif = com.example.ui.components.CDNotification(
                                id = java.util.UUID.randomUUID().toString(),
                                message = "${spell.name} de $roleLabel disponible",
                                iconUrl = spell.iconUrl,
                                fallbackIcon = spell.iconFallback,
                                color = spell.accentColor
                            )
                            com.example.ui.components.CooldownTrackerStateHolder.notifications.add(notif)
                            launch {
                                kotlinx.coroutines.delay(5000)
                                com.example.ui.components.CooldownTrackerStateHolder.notifications.remove(notif)
                            }
                        }
                    }
                }
                kotlinx.coroutines.delay(500)
            }
        }

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            var dragDownY by remember { mutableFloatStateOf(0f) }
        var isScanning by remember { mutableStateOf(false) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Floating Bubble Button (Arastrable y clicable)
            Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            HextechCyan,
                            Color(0xFF005A82),
                            HextechDarkBg
                        )
                    )
                )
                .border(2.dp, if (isScanning) HextechCyan else HextechGold, CircleShape)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragDownY += dragAmount.y
                            // Si desliza hacia abajo más de 100px, cerramos la burbuja flotante
                            if (dragDownY > 100f && !isExpanded) {
                                onClose()
                            } else {
                                onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt())
                            }
                        },
                        onDragEnd = { dragDownY = 0f },
                        onDragCancel = { dragDownY = 0f }
                    )
                }
                .clickable {
                    if (!isExpanded) {
                        isScanning = true
                        selectedTab = 0
                        
                        CoroutineScope(Dispatchers.IO).launch {
                            val bitmap = screenCaptureManager?.captureCurrentFrame()
                            if (bitmap != null) {
                                val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)
                                withContext(Dispatchers.Main) {
                                    allies.clear()
                                    allies.addAll(result.allies)
                                    enemies.clear()
                                    enemies.addAll(result.enemies)
                                    isScanning = false
                                    isExpanded = true
                                    onExpandedChange(true)
                                }
                            } else {
                                withContext(Dispatchers.Main) {
                                    isScanning = false
                                    isExpanded = true
                                    onExpandedChange(true)
                                }
                            }
                        }
                    } else {
                        isExpanded = false
                        onExpandedChange(false)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (isScanning) {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier.size(36.dp),
                    color = HextechCyan,
                    strokeWidth = 3.dp
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Videocam,
                    contentDescription = "Asistente Flotante Wild Rift",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }

            if (!isScanning) {
                // Pulse badge indicator
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(HextechGold)
                )
            }
        }

            if (!isExpanded && !isScanning) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = tr("↓ Desliza para cerrar"),
                        color = Color.White,
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Expanded Panel
        AnimatedVisibility(
            visible = isExpanded,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            var dragDownY by remember { mutableFloatStateOf(0f) }
            val isClosingSoon = dragDownY > 10f
            val currentBorderColor = if (isClosingSoon) Color.Red else HextechGold

            Card(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 320.dp)
                    .heightIn(max = 480.dp)
                    .clip(RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.98f)),
                border = androidx.compose.foundation.BorderStroke(if (isClosingSoon) 2.dp else 1.5.dp, currentBorderColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    // Barra / Indicador para deslizar hacia abajo y cerrar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        dragDownY += dragAmount.y
                                        if (dragDownY > 30f) {
                                            isExpanded = false
                                            onExpandedChange(false)
                                            dragDownY = 0f
                                        } else {
                                            onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt())
                                        }
                                    },
                                    onDragEnd = {
                                        if (dragDownY > 20f) {
                                            isExpanded = false
                                            onExpandedChange(false)
                                        }
                                        dragDownY = 0f
                                    },
                                    onDragCancel = {
                                        dragDownY = 0f
                                    }
                                )
                            }
                            .padding(bottom = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(3.dp)
                                    .clip(CircleShape)
                                    .background(if (isClosingSoon) Color.Red else HextechGold.copy(alpha = 0.7f))
                            )
                            if (isClosingSoon) {
                                Text(
                                    text = tr("Cerrando..."),
                                    color = Color.Red,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }

                    // Header con botón de arrastre y minimizar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        dragDownY += dragAmount.y
                                        if (dragDownY > 40f) {
                                            isExpanded = false
                                            onExpandedChange(false)
                                            dragDownY = 0f
                                        } else {
                                            onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt())
                                        }
                                    },
                                    onDragEnd = {
                                        if (dragDownY > 35f) {
                                            isExpanded = false
                                            onExpandedChange(false)
                                        }
                                        dragDownY = 0f
                                    },
                                    onDragCancel = {
                                        dragDownY = 0f
                                    }
                                )
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Shield, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Wild Rift HUD Inteligente", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                        }
                        IconButton(
                            onClick = {
                                isExpanded = false
                                onExpandedChange(false)
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Minimizar", tint = TextMuted, modifier = Modifier.size(16.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Tab Selector in Mini HUD (sin recortes)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .padding(2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val tabs = listOf(tr("Draft"), tr("CDs"), tr("Daño"), tr("Obj"), tr("Build"), tr("Runas"), tr("Hechizos"))
                        tabs.forEachIndexed { index, label ->
                            val isTabSelected = selectedTab == index
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isTabSelected) HextechCyan else Color.Transparent)
                                    .clickable { selectedTab = index }
                                    .padding(vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    color = if (isTabSelected) HextechDarkBg else TextMuted,
                                    fontSize = 9.5.sp,
                                    fontWeight = if (isTabSelected) FontWeight.Bold else FontWeight.Medium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tab Content
                    Column(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        when (selectedTab) {
                        0 -> {
                            // DRAFT TAB
                            // Selector de Líneas
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
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
                                            .padding(vertical = 3.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = tr(role.shortName),
                                            fontSize = 9.5.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
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
                                    text = if (isFirstPick) "★ " + tr("1ª Elección Segura") else "★ " + tr("MEJOR OPCIÓN") + " (${tr(activeRole.shortName)})",
                                    color = HextechGold,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (isFirstPick) tr("Blind Pick") else tr("Counter Pick"),
                                    color = HextechCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(HextechSurface)
                                        .clickable { isFirstPick = !isFirstPick }
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                analysis.recommendations.take(3).forEach { pick ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(HextechSurface)
                                            .border(1.dp, HextechGold.copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                                            .clickable {
                                                lockedChampion = pick.champion
                                                selectedTab = 2
                                            }
                                            .padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        ChampionAvatar(champion = pick.champion, size = 40.dp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(pick.champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(3.dp))
                                                        .background(TierSPlusColor)
                                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                                ) {
                                                    Text(pick.champion.tier, color = Color.Black, fontSize = 8.5.sp, fontWeight = FontWeight.Black)
                                                }
                                            }
                                            Text("WR: ${pick.estimatedWinrate}% • ${pick.advantageBadge}", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                                            Text(pick.tacticalReason, color = TextMuted, fontSize = 9.sp, maxLines = 2)
                                        }
                                    }
                                }
                            }
                        }

                        1 -> {
                            // CD TRACKER TAB
                            CooldownTrackerPanel(isCompactOverlay = true)
                        }

                        2 -> {
                            // DAMAGE & PENETRATION MATH TAB
                            DamagePenetrationCalculator(isCompactOverlay = true)
                        }

                        3 -> {
                            // OBJETIVOS TAB (SIN IMÁGENES, DISEÑO LIMPIO HEXTECH)
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                WildRiftRepository.mapObjectives.forEach { obj ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(HextechSurface)
                                            .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                                            .padding(horizontal = 8.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(tr(obj.name), color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            Text(tr(obj.buffDescription), color = TextMuted, fontSize = 9.5.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(HextechCyan.copy(alpha = 0.15f))
                                                .border(0.5.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(tr(obj.spawnTime), color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }

                        4 -> {
                            // BUILD TAB (SOLO OBJETOS CORE Y SITUACIONALES)
                            val currentChamp = lockedChampion ?: topPick?.champion ?: WildRiftRepository.champions.first()
                            val itemsToShow = currentChamp.coreItems + currentChamp.situationalItems
                            val champItems = itemsToShow.mapNotNull { itemName -> 
                                WildRiftRepository.items.find { it.name == itemName } 
                            }.takeIf { it.isNotEmpty() } ?: WildRiftRepository.items.take(4)

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(tr("Build de") + " ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                                    Text(tr("Cambiar"), color = HextechCyan, fontSize = 10.sp, modifier = Modifier.clickable { selectedTab = 0 })
                                }

                                champItems.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(HextechSurface)
                                            .padding(6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        val lang = com.example.util.LocalLanguage.current
                                        val localizedName = item.getLocalizedName(lang)
                                        val localizedPassive = item.getLocalizedPassive(lang)

                                        AppAssetImage(
                                            url = item.iconUrl,
                                            contentDescription = localizedName,
                                            fallbackText = localizedName,
                                            modifier = Modifier.size(30.dp),
                                            borderColor = HextechGold,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(localizedName, color = HextechGoldLight, fontSize = 11.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            Text(localizedPassive, color = TextMuted, fontSize = 9.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        }
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("${item.goldCost}g", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        5 -> {
                            // RUNAS TAB (EXCLUSIVO RUNAS)
                            val currentChamp = lockedChampion ?: topPick?.champion ?: WildRiftRepository.champions.first()
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(tr("Runas de") + " ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                                    Text(tr("Cambiar"), color = HextechCyan, fontSize = 10.sp, modifier = Modifier.clickable { selectedTab = 0 })
                                }

                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f))
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(
                                            text = tr(currentChamp.recommendedRunes),
                                            color = HextechGoldLight,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        if (currentChamp.runeTreeDetails.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = tr(currentChamp.runeTreeDetails),
                                                color = TextPrimary.copy(alpha = 0.85f),
                                                fontSize = 9.5.sp,
                                                lineHeight = 13.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        6 -> {
                            // HECHIZOS TAB (EXCLUSIVO HECHIZOS & MAXEO)
                            val currentChamp = lockedChampion ?: topPick?.champion ?: WildRiftRepository.champions.first()
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(tr("Hechizos de") + " ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                                    Text(tr("Cambiar"), color = HextechCyan, fontSize = 10.sp, modifier = Modifier.clickable { selectedTab = 0 })
                                }

                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(
                                                text = "${tr("Hechizos")}:",
                                                color = HextechGoldLight,
                                                fontSize = 11.5.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            currentChamp.recommendedSpells.forEach { rawSpell ->
                                                val spellIcon = com.example.data.WildRiftSpellsAndRunes.getSpellIconByName(rawSpell)
                                                AppAssetImage(
                                                    url = spellIcon,
                                                    contentDescription = tr(rawSpell),
                                                    fallbackText = rawSpell,
                                                    modifier = Modifier.size(18.dp),
                                                    borderColor = HextechGold,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                Text(
                                                    text = tr(rawSpell),
                                                    color = Color.White,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "${tr("Habilidades")}: ${currentChamp.skillOrder}",
                                            color = HextechCyan,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                    }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tr("Detener Asistente"),
                            color = DangerRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { onClose() }
                                .padding(4.dp)
                        )

                        Text(
                            text = tr("Minimizar HUD"),
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { isExpanded = false }
                                .padding(4.dp)
                        )
                    }
                    // End Scrollable Column
                }
            }
        }
    } // End of Column (Main layout)

    // Notificaciones sobrepuestas (siempre visibles, incluso si esta minimizado)
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(start = 64.dp, top = 8.dp) // Empuja a la derecha de la burbuja (52dp)
    ) {
        com.example.ui.components.CooldownTrackerStateHolder.notifications.forEach { notif ->
            androidx.compose.animation.AnimatedVisibility(
                visible = true,
                enter = androidx.compose.animation.slideInHorizontally { it } + androidx.compose.animation.fadeIn(),
                exit = androidx.compose.animation.slideOutHorizontally { it } + androidx.compose.animation.fadeOut()
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.95f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, notif.color),
                    modifier = Modifier.widthIn(max = 240.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(notif.color.copy(alpha = 0.2f))
                                .border(1.dp, notif.color, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (notif.iconUrl.isNotEmpty()) {
                                coil.compose.AsyncImage(
                                    model = notif.iconUrl,
                                    contentDescription = null,
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Text(
                                    text = notif.fallbackIcon,
                                    color = notif.color,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 8.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        val parts = notif.message.split(" de ")
                        val textStr = if (parts.size == 2) {
                            tr(parts[0]) + " de " + parts[1]
                        } else {
                            notif.message
                        }
                        Text(
                            text = textStr,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
} // End of Box
 // End of FloatingOverlayContent