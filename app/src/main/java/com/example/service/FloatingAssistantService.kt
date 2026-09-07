package com.example.service
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.Canvas

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import com.example.data.repository.DraftHistoryRepository
import com.example.util.ChampionRoleAdapter
import kotlinx.coroutines.launch

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
import android.animation.ValueAnimator
import android.view.animation.DecelerateInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.UnfoldLess
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Hardware
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import com.example.data.local.entity.SavedDraftEntity
import com.example.model.Champion
import com.example.model.DraftSlot
import com.example.model.LaneRole
import com.example.service.screen.DraftVisionScanner
import com.example.service.screen.ScreenCaptureManager
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.components.FormattedWildRiftText
import com.example.ui.components.SaveDraftDialog
import com.example.ui.components.WomboCombo
import com.example.ui.components.WomboComboSynergyDetector
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
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TierSPlusColor
import com.example.util.AppLogger
import com.example.util.LocalLanguage
import com.example.util.SubscriptionManager
import com.example.util.tr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

enum class OverlayHubTab { DRAFT, TIER_LIST, CHAMPIONS, HISTORY }

class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    private val overlayState = OverlayState()
    private var screenCaptureManager: ScreenCaptureManager? = null

    private var windowManager: WindowManager? = null
    private var floatingComposeView: ComposeView? = null
    
    // Estado para la orientación de la pantalla real
    private val isDeviceLandscape = androidx.compose.runtime.mutableStateOf(false)
    private var closeTargetComposeView: ComposeView? = null
    private var debugBoxesComposeView: ComposeView? = null
    private var floatingParams: WindowManager.LayoutParams? = null
    private var isOverlayExpanded: Boolean = false
    private var isCompactBubbleMode: Boolean = false
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
            com.example.data.WildRiftRepository.initChampions(applicationContext)
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
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE or
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        startForeground(
                            NOTIFICATION_ID,
                            notification,
                            android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                        )
                    } else {
                        startForeground(NOTIFICATION_ID, notification)
                    }
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
            try {
                val notification = buildForegroundNotification()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    startForeground(
                        NOTIFICATION_ID,
                        notification,
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE or
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    startForeground(
                        NOTIFICATION_ID,
                        notification,
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MANIFEST
                    )
                }
            } catch (e: Exception) {
                AppLogger.w("FloatingService", "Error asegurando tipo FGS: ${e.message}")
            }

            val success = screenCaptureManager?.initializeProjection(
                ScreenCaptureManager.pendingMediaProjectionResultCode,
                ScreenCaptureManager.pendingMediaProjectionData!!
            )
            if (success == true) {
                AppLogger.d("FloatingService", "ScreenCaptureManager initialized from pending intent.")
            }
            ScreenCaptureManager.pendingMediaProjectionData = null
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        store.clear()

        removeFloatingOverlay()
        try {
            screenCaptureManager?.release()
            screenCaptureManager = null
        } catch (_: Exception) {}
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
        removeFloatingOverlay()
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val density = displayMetrics.density
        val marginPx = (8 * density).toInt()
        
        val isLandscape = displayMetrics.widthPixels > displayMetrics.heightPixels
        val cardWidthPx = ((if (isLandscape) 560 else 330) * density).toInt()
        val cardHeightPx = ((if (isLandscape) 390 else 520) * density).toInt()
        var bubbleSizePx = (46 * density).toInt() // local

        

        val layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val closeTargetParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            y = (24 * density).toInt()
        }

        var isCloseTargetVisible by mutableStateOf(false)
        var isCloseTargetHovered by mutableStateOf(false)

        closeTargetComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow)

            setContent {
                FloatingCloseTarget(
                    isVisible = isCloseTargetVisible,
                    isTargeted = isCloseTargetHovered
                )
            }
        }

        val debugParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
        }

        debugBoxesComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                val showDebug by showVisionDebugger.collectAsState()
                if (showDebug) {
                    VisionDebugOverlay()
                }
            }
        }

        try {
            windowManager?.addView(closeTargetComposeView, closeTargetParams)
            windowManager?.addView(debugBoxesComposeView, debugParams)
        } catch (_: Exception) {}

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
        floatingParams = params

        floatingComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow)

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
                            state = overlayState,
                            isLandscapeMode = isDeviceLandscape.value,
                            screenCaptureManager = screenCaptureManager,
                            onClose = { stopSelf() },
                            onDragDelta = { dx, dy, isDragging, isEnded ->
                                val currentMetrics = resources.displayMetrics
                                val currentScreenWidth = currentMetrics.widthPixels
                                val currentScreenHeight = currentMetrics.heightPixels
                                val currentWidth = if (overlayState.isExpanded) cardWidthPx else bubbleSizePx
                                val currentHeight = if (overlayState.isExpanded) cardHeightPx else bubbleSizePx
                                val maxX = (currentScreenWidth - currentWidth - marginPx).coerceAtLeast(marginPx)
                                val maxY = (currentScreenHeight - currentHeight - marginPx).coerceAtLeast(marginPx)
                                
                                params.x = (params.x + dx).coerceIn(marginPx, maxX)
                                params.y = (params.y + dy).coerceIn(marginPx, maxY)

                                if (!isOverlayExpanded) {
                                    if (isDragging) {
                                        isCloseTargetVisible = true
                                        // Centro de la burbuja flotante
                                        val bubbleCenterX = params.x + bubbleSizePx / 2
                                        val bubbleCenterY = params.y + bubbleSizePx / 2
                                        
                                        // Centro del target circular inferior
                                        val targetCenterX = currentScreenWidth / 2
                                        val targetCenterY = currentScreenHeight - (24 * density).toInt() - (32 * density).toInt()
                                        
                                        val dist = kotlin.math.hypot(
                                            (bubbleCenterX - targetCenterX).toDouble(),
                                            (bubbleCenterY - targetCenterY).toDouble()
                                        )
                                        
                                        val isOver = dist < (72 * density) || (
                                            params.y >= currentScreenHeight - bubbleSizePx - (45 * density).toInt() &&
                                            kotlin.math.abs(bubbleCenterX - targetCenterX) < (80 * density).toInt()
                                        )
                                        isCloseTargetHovered = isOver
                                    } else {
                                        val shouldClose = isCloseTargetHovered
                                        isCloseTargetVisible = false
                                        isCloseTargetHovered = false

                                        if (shouldClose) {
                                            stopSelf()
                                        } else {
                                            // AUTO-SNAP: Cuando se suelta en forma de burbuja, pegarlo al borde lateral con animación fluida
                                            val targetX = if (params.x < currentScreenWidth / 2) marginPx else maxX
                                            val targetY = params.y

                                            val animator = ValueAnimator.ofFloat(0f, 1f)
                                            animator.duration = 250 // ms
                                            animator.interpolator = DecelerateInterpolator()
                                            
                                            val startX = params.x
                                            val startY = params.y
                                            
                                            animator.addUpdateListener { animation ->
                                                val fraction = animation.animatedFraction
                                                params.x = (startX + (targetX - startX) * fraction).toInt()
                                                params.y = (startY + (targetY - startY) * fraction).toInt()
                                                try {
                                                    windowManager?.updateViewLayout(this@apply, params)
                                                } catch (_: Exception) {}
                                            }
                                            animator.start()
                                        }
                                    }
                                } else {
                                    isCloseTargetVisible = false
                                    isCloseTargetHovered = false
                                }

                                try {
                                    windowManager?.updateViewLayout(this@apply, params)
                                } catch (_: Exception) {}
                            },
                            onExpandedChange = { expanded ->
                                val currentMetrics = resources.displayMetrics
                                val currentScreenWidth = currentMetrics.widthPixels
                                val currentScreenHeight = currentMetrics.heightPixels
                                isOverlayExpanded = expanded
                                isCloseTargetVisible = false
                                isCloseTargetHovered = false

                                if (expanded) {
                                    params.flags = params.flags and WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE.inv()
                                } else {
                                    params.flags = params.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                }
                                if (expanded) {
                                    if (params.x + cardWidthPx > currentScreenWidth - marginPx) {
                                        params.x = (currentScreenWidth - cardWidthPx - marginPx).coerceAtLeast(marginPx)
                                    }
                                    if (params.y + cardHeightPx > currentScreenHeight - marginPx) {
                                        params.y = (currentScreenHeight - cardHeightPx - marginPx).coerceAtLeast(marginPx)
                                    }
                                }
                                try {
                                    if (this@apply.isAttachedToWindow) {
                                        windowManager?.updateViewLayout(this@apply, params)
                                    }
                                } catch (_: Exception) {}
                            },
                            onCompactModeChange = { isCompact ->
                                isCompactBubbleMode = isCompact
                                bubbleSizePx = ((if (isCompact) 36f else 46f) * density).toInt()
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
            floatingComposeView?.let { view ->
                try { windowManager?.removeViewImmediate(view) } catch (_: Exception) {}
            }
            floatingComposeView = null
            closeTargetComposeView?.let { view ->
                try { windowManager?.removeViewImmediate(view) } catch (_: Exception) {}
            }
            closeTargetComposeView = null
        } catch (_: Exception) {}
    }

    private var lastScreenWidth = 0
    private var lastScreenHeight = 0

        override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        try {
            val metrics = resources.displayMetrics
            if (lastScreenWidth != metrics.widthPixels || lastScreenHeight != metrics.heightPixels) {
                lastScreenWidth = metrics.widthPixels
                lastScreenHeight = metrics.heightPixels
                isDeviceLandscape.value = lastScreenWidth > lastScreenHeight
                screenCaptureManager?.refreshProjection()
                
                val currentX = floatingParams?.x
                val currentY = floatingParams?.y
                
                // Ya no recreamos toda la ventana para evitar crashes (BadTokenException/WindowManager)
                // updateViewLayout será suficiente porque el ComposeView es responsive
                // createFloatingOverlay()
                
                if (currentX != null && currentY != null && floatingParams != null && floatingComposeView != null) {
                    val density = metrics.density
                    val marginPx = (8 * density).toInt()
                    val currentBubblePx = ((if (overlayState.isCompactBubble) 36f else 46f) * density).toInt()
                    val isLandscape = lastScreenWidth > lastScreenHeight
                    val cardWidthPx = ((if (isLandscape) 560 else 330) * density).toInt()
                    val cardHeightPx = ((if (isLandscape) 390 else 520) * density).toInt()
                    
                    val viewWidth = if (overlayState.isExpanded) cardWidthPx else currentBubblePx
                    val viewHeight = if (overlayState.isExpanded) cardHeightPx else currentBubblePx
                    
                    val maxX = (lastScreenWidth - viewWidth - marginPx).coerceAtLeast(marginPx)
                    val maxY = (lastScreenHeight - viewHeight - marginPx).coerceAtLeast(marginPx)
                    
                    floatingParams!!.x = currentX.coerceIn(marginPx, maxX)
                    floatingParams!!.y = currentY.coerceIn(marginPx, maxY)
                    windowManager?.updateViewLayout(floatingComposeView, floatingParams)
                }
            } else {
                floatingComposeView?.dispatchConfigurationChanged(newConfig)
                closeTargetComposeView?.dispatchConfigurationChanged(newConfig)
            }
        } catch (e: Throwable) {
            AppLogger.w("FloatingService", "Error adaptando layout tras cambio de configuración: ${e.message}")
        }
    }

    companion object {
        const val ACTION_START = "ACTION_START_FLOATING_ASSISTANT"
        const val ACTION_STOP = "ACTION_STOP_FLOATING_ASSISTANT"
        const val CHANNEL_ID = "wildrift_overlay_channel"
        const val NOTIFICATION_ID = 2001
    }
}

@Composable
private fun FloatingCloseTarget(
    isVisible: Boolean,
    isTargeted: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + scaleIn(initialScale = 0.5f),
        exit = fadeOut() + scaleOut(targetScale = 0.5f)
    ) {
        val targetSize by animateDpAsState(
            targetValue = if (isTargeted) 68.dp else 54.dp,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )
        val targetBgColor by animateColorAsState(
            targetValue = if (isTargeted) DangerRed else Color(0xDD12151D),
            animationSpec = tween(150)
        )
        val targetBorderColor by animateColorAsState(
            targetValue = if (isTargeted) Color.White else DangerRed.copy(alpha = 0.75f),
            animationSpec = tween(150)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(targetSize)
                    .clip(CircleShape)
                    .background(targetBgColor)
                    .border(2.5.dp, targetBorderColor, CircleShape)
                    .shadow(elevation = if (isTargeted) 16.dp else 6.dp, shape = CircleShape, ambientColor = DangerRed, spotColor = DangerRed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar y desactivar overlay",
                    tint = Color.White,
                    modifier = Modifier.size(if (isTargeted) 32.dp else 24.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isTargeted) DangerRed else Color.Black.copy(alpha = 0.75f),
                border = BorderStroke(1.dp, if (isTargeted) Color.White else DangerRed.copy(alpha = 0.4f))
            ) {
                Text(
                    text = if (isTargeted) "✕ Soltar para desactivar" else "Arrastra aquí para cerrar",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

class OverlayState {
    var isExpanded by androidx.compose.runtime.mutableStateOf(false)
    var overlayHubTab by androidx.compose.runtime.mutableStateOf(OverlayHubTab.DRAFT)
    var showSaveDraftDialog by androidx.compose.runtime.mutableStateOf(false)
    var showRoleChangeDialog by androidx.compose.runtime.mutableStateOf(false)
    var isSavedRecently by androidx.compose.runtime.mutableStateOf(false)
    var activeRole by androidx.compose.runtime.mutableStateOf(LaneRole.MID)
    var isFirstPick by androidx.compose.runtime.mutableStateOf(false)
    var isCompactBubble by androidx.compose.runtime.mutableStateOf(false)
    var isScanning by androidx.compose.runtime.mutableStateOf(false)
    var autoScanEnabled by androidx.compose.runtime.mutableStateOf(false)
    var scanNoticeMessage by androidx.compose.runtime.mutableStateOf<String?>(null)
    var isDraggingBubble by androidx.compose.runtime.mutableStateOf(false)
    var dragAccumulatedY by androidx.compose.runtime.mutableFloatStateOf(0f)
    var isNearCloseThreshold by androidx.compose.runtime.mutableStateOf(false)
    var selectedChampionDetail by androidx.compose.runtime.mutableStateOf<com.example.model.Champion?>(null)
    var showChampionPickerForSlot by androidx.compose.runtime.mutableStateOf<Pair<Boolean, Int>?>(null)
    var isLoadingScreenMode by androidx.compose.runtime.mutableStateOf(false)
    var isOverlayTabsMinimized by androidx.compose.runtime.mutableStateOf(false)
    val allies = androidx.compose.runtime.mutableStateListOf<com.example.model.Champion?>().apply { repeat(5) { add(null) } }
    val enemies = androidx.compose.runtime.mutableStateListOf<com.example.model.Champion?>().apply { repeat(5) { add(null) } }
    val enemyConfidences = androidx.compose.runtime.mutableStateMapOf<LaneRole, Int>()
}

@Composable
private fun FloatingOverlayContent(
    state: OverlayState,
    isLandscapeMode: Boolean,
    screenCaptureManager: ScreenCaptureManager?,
    onClose: () -> Unit,
    onDragDelta: (dx: Int, dy: Int, isDragging: Boolean, isEnded: Boolean) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onCompactModeChange: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isExpanded by state::isExpanded
    var overlayHubTab by state::overlayHubTab
    var showSaveDraftDialog by state::showSaveDraftDialog
    var showRoleChangeDialog by state::showRoleChangeDialog
    var isSavedRecently by state::isSavedRecently
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()
    val activeProfileId by com.example.data.AccountProfileManager.activeProfileId.collectAsStateWithLifecycle()
    val isLoggedInAndPremium = isPremium && activeProfileId != null
    val userRole by com.example.util.SubscriptionManager.userRole.collectAsStateWithLifecycle()
    val currentAuthEmail = remember { com.example.util.AuthManager.getAuth()?.currentUser?.email }
    val isAdmin = com.example.util.AuthManager.isCurrentUserAdmin()
    val context = LocalContext.current
    var activeRole by state::activeRole
    var isFirstPick by state::isFirstPick
    var isCompactBubble by state::isCompactBubble

    val defaultRoles = remember { listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT) }
    val allies = state.allies
    val enemies = state.enemies

    // Funciones de asignación con Regla Estricta MOBA de Unicidad Absoluta (ningún campeón puede duplicarse en ningún bando)
    val assignAllySlot: (Int, Champion) -> Unit = { targetIdx, champ ->
        for (i in 0 until 5) {
            if (enemies[i]?.id == champ.id) enemies[i] = null
        }
        for (i in 0 until 5) {
            if (i != targetIdx && allies[i]?.id == champ.id) allies[i] = null
        }
        if (targetIdx in 0 until 5) {
            allies[targetIdx] = champ
        }
    }

    val assignEnemySlot: (Int, Champion, Int?) -> Unit = { targetIdx, champ, conf ->
        for (i in 0 until 5) {
            if (allies[i]?.id == champ.id) allies[i] = null
        }
        for (i in 0 until 5) {
            if (i != targetIdx && enemies[i]?.id == champ.id) {
                enemies[i] = null
                defaultRoles.getOrNull(i)?.let { state.enemyConfidences.remove(it) }
            }
        }
        if (targetIdx in 0 until 5) {
            enemies[targetIdx] = champ
            defaultRoles.getOrNull(targetIdx)?.let { role ->
                state.enemyConfidences[role] = conf ?: 85
            }
        }
    }

    var isScanning by state::isScanning
    var autoScanEnabled by state::autoScanEnabled
    var scanNoticeMessage by state::scanNoticeMessage

    var isDraggingBubble by state::isDraggingBubble
    var dragAccumulatedY by state::dragAccumulatedY
    var isNearCloseThreshold by state::isNearCloseThreshold

    var selectedChampionDetail by state::selectedChampionDetail
    var showChampionPickerForSlot by state::showChampionPickerForSlot
    var isLoadingScreenMode by state::isLoadingScreenMode
    var isOverlayTabsMinimized by state::isOverlayTabsMinimized

    val explicitEnemyOpponent = remember(activeRole, enemies.toList()) {
        val roleIndex = defaultRoles.indexOf(activeRole).coerceIn(0, 4)
        enemies.getOrNull(roleIndex)
    }

    val analysis = remember(activeRole, isFirstPick, allies.toList(), enemies.toList(), explicitEnemyOpponent) {
        WildRiftRepository.analyzeDraft(
            myRole = activeRole,
            allies = allies.filterNotNull(),
            enemies = enemies.filterNotNull(),
            enemyLaneOpponent = explicitEnemyOpponent,
            isFirstPick = isFirstPick
        )
    }

    // Auto-Scan Loop en segundo plano cada 1.1 segundos mientras esté activo
    LaunchedEffect(autoScanEnabled) {
        if (!autoScanEnabled) return@LaunchedEffect
        while (true) {
            delay(1100)
            if (screenCaptureManager == null || !screenCaptureManager.isReady()) {
                scanNoticeMessage = "⚠️ Permiso de captura inactivo. Toca aquí para activarlo."
            } else if (!isScanning) {
                try {
                    val bitmap = screenCaptureManager.captureCurrentFrame()
                    if (bitmap != null) {
                        val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)
                        if (result.isSuccessful) {
                            var newAlliesAdded = 0
                            var newEnemiesAdded = 0
                            
                            // 1. Asignación directa y de alta precisión por rol/carril detectado
                            defaultRoles.forEachIndexed { idx, role ->
                                val scannedAlly = result.alliesByRole[role]
                                if (scannedAlly != null) {
                                    if (allies[idx]?.id != scannedAlly.id) {
                                        assignAllySlot(idx, scannedAlly)
                                        newAlliesAdded++
                                    }
                                }
                                val scannedEnemy = result.enemiesByRole[role]
                                if (scannedEnemy != null && (enemies[idx]?.id != scannedEnemy.id || state.enemyConfidences[role] != result.enemyConfidencesByRole[role])) {
                                    assignEnemySlot(idx, scannedEnemy, result.enemyConfidencesByRole[role])
                                    newEnemiesAdded++
                                }
                            }

                            if (result.detectedRole != null && activeRole != result.detectedRole) {
                                activeRole = result.detectedRole
                                com.example.util.UserPreferences.setActiveDraftRole(context, result.detectedRole)
                                scanNoticeMessage = "⚡ Auto-Scan: Tu rol detectado (${result.detectedRole.shortName})"
                            } else if (newAlliesAdded > 0 || newEnemiesAdded > 0) {
                                scanNoticeMessage = "⚡ Auto-Scan: +${newAlliesAdded + newEnemiesAdded} picks detectados"
                            }
                            if (scanNoticeMessage != null) {
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
        if (screenCaptureManager?.isReady() != true) {
            scanNoticeMessage = "⚠️ Requiere permiso de pantalla. Abriendo solicitud..."
            try {
                val reqIntent = Intent(context, com.example.MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("EXTRA_REQUEST_CAPTURE", true)
                }
                context.startActivity(reqIntent)
            } catch (_: Exception) {}
            coroutineScope.launch {
                delay(3500)
                scanNoticeMessage = null
            }
            return
        }
        isScanning = true
        scanNoticeMessage = "Escaneando selección en directo..."
        coroutineScope.launch(Dispatchers.IO) {
            val bitmap = screenCaptureManager?.captureCurrentFrame()
            if (bitmap != null) {
                val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)
                withContext(Dispatchers.Main) {
                    if (result.isSuccessful) {
                        // 1. Asignación directa y de alta precisión por rol/posición
                        defaultRoles.forEachIndexed { idx, role ->
                            val scannedAlly = result.alliesByRole[role]
                            if (scannedAlly != null) {
                                assignAllySlot(idx, scannedAlly)
                            } else if (result.allies.isNotEmpty() && !result.allies.contains(allies[idx])) {
                                allies[idx] = null
                            }
                            val scannedEnemy = result.enemiesByRole[role]
                            if (scannedEnemy != null) {
                                assignEnemySlot(idx, scannedEnemy, result.enemyConfidencesByRole[role])
                            } else {
                                enemies[idx] = null
                                state.enemyConfidences.remove(role)
                            }
                        }

                        if (result.detectedRole != null) {
                            activeRole = result.detectedRole
                            com.example.util.UserPreferences.setActiveDraftRole(context, result.detectedRole)
                        }
                        val totalDetected = allies.filterNotNull().size + enemies.filterNotNull().size
                        scanNoticeMessage = "✅ Escaneo exitoso ($totalDetected picks" +
                                (if (result.detectedRole != null) ", tu rol: ${result.detectedRole.shortName})" else ")")
                    } else {
                        scanNoticeMessage = "ℹ️ ${result.statusMessage}"
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
                            .size(if (isCompactBubble) 36.dp else 46.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(HextechCyan, Color(0xFF005A82), HextechDarkBg)
                                )
                            )
                            .border(2.5.dp, if (isScanning) HextechCyan else HextechGold, CircleShape)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = {
                                        isDraggingBubble = true
                                    },
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                                    },
                                    onDragEnd = {
                                        isDraggingBubble = false
                                        onDragDelta(0, 0, false, false)
                                    },
                                    onDragCancel = {
                                        isDraggingBubble = false
                                        onDragDelta(0, 0, false, false)
                                    }
                                )
                            }
                            .clickable {
                                isExpanded = true
                                onExpandedChange(true)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isScanning) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(if (isCompactBubble) 28.dp else 36.dp),
                                color = HextechCyan,
                                strokeWidth = 3.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = "Wild Rift Drafting Coach",
                                tint = Color.White,
                                modifier = Modifier.size(if (isCompactBubble) 16.dp else 22.dp)
                            )
                        }

                        if (!isScanning) {
                            // Pulsing green auto-scan indicator
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .align(Alignment.TopEnd)
                                    .clip(CircleShape)
                                    .background(if (autoScanEnabled && isAdmin) Color(0xFF00FF7F) else HextechGold)
                            )
                        }
                    }

                    // Indicador sutil de arrastre
                    if (isDraggingBubble) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = tr("↓ Arrastra al círculo inferior para cerrar"),
                                color = TextSecondary,
                                fontSize = 8.5.sp,
                                fontWeight = FontWeight.SemiBold
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

                Card(
                    modifier = Modifier
                        .widthIn(min = if (isLandscapeMode) 520.dp else 300.dp, max = if (isLandscapeMode) 560.dp else 340.dp)
                        .height(if (isLandscapeMode) 380.dp else 530.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, HextechGold)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                        // Header con barra de arrastre para reposicionar el Hub cómodamente
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = {
                                            isDraggingPanel = true
                                            dragAccumulatedY = 0f
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            dragAccumulatedY += dragAmount.y
                                            onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                                        },
                                        onDragEnd = {
                                            isDraggingPanel = false
                                            if (dragAccumulatedY > 120f) {
                                                onClose()
                                            } else {
                                                onDragDelta(0, 0, false, false)
                                            }
                                            dragAccumulatedY = 0f
                                        },
                                        onDragCancel = {
                                            isDraggingPanel = false
                                            dragAccumulatedY = 0f
                                            onDragDelta(0, 0, false, false)
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
                                    val isCaptureReady = screenCaptureManager?.isReady() == true
                                    val indicatorColor = when {
                                        !isCaptureReady -> Color(0xFFFFB300)
                                        autoScanEnabled -> Color(0xFF00FF7F)
                                        else -> HextechGold
                                    }
                                    val indicatorText = when {
                                        !isCaptureReady -> tr("Sin permiso de pantalla")
                                        autoScanEnabled -> tr("Auto-Scan Activo")
                                        else -> tr("Escaneo Manual")
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.clickable {
                                            if (!isCaptureReady) {
                                                try {
                                                    val reqIntent = Intent(context, com.example.MainActivity::class.java).apply {
                                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                                        putExtra("EXTRA_REQUEST_CAPTURE", true)
                                                    }
                                                    context.startActivity(reqIntent)
                                                } catch (_: Exception) {}
                                            }
                                        }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(indicatorColor)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = indicatorText,
                                            color = indicatorColor,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Botón Minimizar/Mostrar Pestañas del Hub
                                IconButton(
                                    onClick = { isOverlayTabsMinimized = !isOverlayTabsMinimized },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isOverlayTabsMinimized) Icons.Default.UnfoldMore else Icons.Default.UnfoldLess,
                                        contentDescription = if (isOverlayTabsMinimized) "Mostrar pestañas del Hub" else "Minimizar pestañas del Hub",
                                        tint = if (isOverlayTabsMinimized) HextechGold else TextMuted,
                                        modifier = Modifier.size(17.dp)
                                    )
                                }

                                // Botón Escaneo Manual
                                IconButton(
                                    onClick = { triggerManualScan() },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    if (isScanning) {
                                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = HextechCyan, strokeWidth = 2.dp)
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.FlashOn,
                                            contentDescription = "Escanear selección",
                                            tint = HextechCyan,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }

                                // Botón Debug Visual
                                val isDebug = showVisionDebugger.collectAsStateWithLifecycle().value
                                IconButton(
                                    onClick = {
                                        showVisionDebugger.value = !isDebug
                                    },
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(
                                            color = if (isDebug) Color.Red.copy(alpha = 0.3f) else Color.Transparent,
                                            shape = CircleShape
                                        )
                                        .border(
                                            width = if (isDebug) 1.dp else 0.dp,
                                            color = if (isDebug) Color.Red else Color.Transparent,
                                            shape = CircleShape
                                        )
                                ) {
                                    Text(
                                        text = "🐛",
                                        fontSize = if (isDebug) 16.sp else 14.sp,
                                        modifier = Modifier.alpha(if (isDebug) 1f else 0.5f)
                                    )
                                }

                                // Botón Limpiar Draft
                                IconButton(
                                    onClick = {
                                        for (i in 0 until 5) {
                                            allies[i] = null
                                            enemies[i] = null
                                        }
                                        selectedChampionDetail = null
                                        com.example.service.screen.DraftVisionScanner.resetSlotMemory()
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.DeleteSweep, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(18.dp))
                                }

                                // Botón Tamaño de Burbuja (Compact/Expanded)
                                IconButton(
                                    onClick = {
                                        isCompactBubble = !isCompactBubble
                                        onCompactModeChange(isCompactBubble)
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isCompactBubble) Icons.Default.UnfoldMore else Icons.Default.UnfoldLess,
                                        contentDescription = "Cambiar tamaño de burbuja",
                                        tint = TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
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

                        // Sub-Header con Pestañas de Navegación del Hub
                        AnimatedVisibility(
                            visible = !isOverlayTabsMinimized
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                            // Pestaña 1: Draft Coach
                            val isDraftActive = overlayHubTab == OverlayHubTab.DRAFT
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isDraftActive) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isDraftActive) HextechCyan else HextechCardBorder.copy(alpha = 0.5f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable { overlayHubTab = OverlayHubTab.DRAFT }
                                    .padding(vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Shield,
                                        contentDescription = null,
                                        tint = if (isDraftActive) HextechCyan else TextMuted,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "Draft",
                                        color = if (isDraftActive) HextechCyan else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isDraftActive) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }

                            // Pestaña 2: Tier & Builds
                            val isTierActive = overlayHubTab == OverlayHubTab.TIER_LIST
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isTierActive) HextechGold.copy(alpha = 0.2f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isTierActive) HextechGold else HextechCardBorder.copy(alpha = 0.5f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable { overlayHubTab = OverlayHubTab.TIER_LIST }
                                    .padding(vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        Icons.Default.EmojiEvents,
                                        contentDescription = null,
                                        tint = if (isTierActive) HextechGold else TextMuted,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "Tiers",
                                        color = if (isTierActive) HextechGold else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isTierActive) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }

                            // Pestaña 3: Campeones
                            val isChampsActive = overlayHubTab == OverlayHubTab.CHAMPIONS
                            Box(
                                modifier = Modifier
                                    .weight(1.1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isChampsActive) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isChampsActive) HextechCyan else HextechCardBorder.copy(alpha = 0.5f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable { overlayHubTab = OverlayHubTab.CHAMPIONS }
                                    .padding(vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Shield,
                                        contentDescription = null,
                                        tint = if (isChampsActive) HextechCyan else TextMuted,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "Champs",
                                        color = if (isChampsActive) HextechCyan else TextMuted,
                                        fontSize = 10.sp,
                                        fontWeight = if (isChampsActive) FontWeight.Bold else FontWeight.Medium
                                    )
                                }
                            }

                            // Pestaña 4: Historial
                            if (isLoggedInAndPremium) {
                                val isHistoryActive = overlayHubTab == OverlayHubTab.HISTORY
                                Box(
                                    modifier = Modifier
                                        .weight(0.9f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isHistoryActive) Color(0xFF00FF7F).copy(alpha = 0.15f) else HextechSurface)
                                        .border(
                                            1.dp,
                                            if (isHistoryActive) Color(0xFF00FF7F) else HextechCardBorder.copy(alpha = 0.5f),
                                            RoundedCornerShape(6.dp)
                                        )
                                        .clickable { overlayHubTab = OverlayHubTab.HISTORY }
                                        .padding(vertical = 5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.History,
                                            contentDescription = null,
                                            tint = if (isHistoryActive) Color(0xFF00FF7F) else TextMuted,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Text(
                                            text = "Hist",
                                            color = if (isHistoryActive) Color(0xFF00FF7F) else TextMuted,
                                            fontSize = 10.sp,
                                            fontWeight = if (isHistoryActive) FontWeight.Bold else FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                        }

                        if (isOverlayTabsMinimized) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                                    .clickable { isOverlayTabsMinimized = false }
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "⚡ Pestaña: ${when (overlayHubTab) {
                                        OverlayHubTab.DRAFT -> "Draft Coach"
                                        OverlayHubTab.TIER_LIST -> "Tiers & Builds"
                                        OverlayHubTab.CHAMPIONS -> "Campeones"
                                        OverlayHubTab.HISTORY -> "Historial & Perfiles"
                                    }}",
                                    color = HextechGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Mostrar barra", color = HextechCyan, fontSize = 9.sp)
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(12.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                        }

                        // Banner de estado de escaneo si existe
                        if (scanNoticeMessage != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                    .clickable {
                                        if (scanNoticeMessage?.contains("Permiso", ignoreCase = true) == true) {
                                            try {
                                                val intent = Intent(context, com.example.MainActivity::class.java).apply {
                                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                                    putExtra("EXTRA_REQUEST_CAPTURE", true)
                                                }
                                                context.startActivity(intent)
                                            } catch (_: Exception) {}
                                        }
                                    }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = scanNoticeMessage ?: "",
                                    color = HextechCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        // Contenido Principal del Hub según la Pestaña Activa o Detalle de Campeón
                        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                            if (selectedChampionDetail != null) {
                                com.example.ui.screens.ChampionDetailSheet(
                                    isOverlay = true,
                                    champion = selectedChampionDetail,
                                    onDismiss = { selectedChampionDetail = null }
                                )
                            } else {
                                when (overlayHubTab) {
                                    OverlayHubTab.DRAFT -> {
                                        FloatingDraftCoachView(
                                            isLandscapeMode = isLandscapeMode,
                                            activeRole = activeRole,
                                            onActiveRoleChange = { 
                                                activeRole = it 
                                                com.example.util.UserPreferences.setActiveDraftRole(context, it)
                                            },
                                            isFirstPick = isFirstPick,
                                            onFirstPickToggle = { isFirstPick = !isFirstPick },
                                            isLoadingScreenMode = isLoadingScreenMode,
                                            onLoadingScreenModeToggle = { isLoadingScreenMode = !isLoadingScreenMode },
                                            allies = allies,
                                            enemies = enemies,
                                            enemyConfidences = state.enemyConfidences,
                                            analysis = analysis,
                                            selectedChampionDetail = selectedChampionDetail,
                                            onSelectChampion = { selectedChampionDetail = it },
                                            onOpenChampionPicker = { isAlly, idx -> 
                                                autoScanEnabled = false
                                                showChampionPickerForSlot = Pair(isAlly, idx) 
                                            },
                                            onSaveDraftClick = { showSaveDraftDialog = true },
                                            isSavedRecently = isSavedRecently,
                                            onClearAll = { 
                                                for (i in 0 until 5) {
                                                    allies[i] = null
                                                    enemies[i] = null
                                                }
                                                state.enemyConfidences.clear()
                                                DraftVisionScanner.resetSlotMemory()
                                                android.widget.Toast.makeText(context, "Equipos vaciados", android.widget.Toast.LENGTH_SHORT).show()
                                            },
                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST }, onManualEdit = { autoScanEnabled = false }
                                        )
                                    }
                                    OverlayHubTab.TIER_LIST -> {
                                        com.example.ui.screens.TierListTab(
                                            isOverlay = true,
                                            onSelectChampion = { selectedChampionDetail = it },
                                            isPremium = isPremium
                                        )
                                    }
                                    OverlayHubTab.CHAMPIONS -> {
                                        com.example.ui.screens.ChampionsCatalogTab(
                                            isOverlay = true,
                                            onSelectChampion = { selectedChampionDetail = it }
                                        )
                                    }
                                    OverlayHubTab.HISTORY -> {
                                        com.example.ui.screens.DraftHistoryScreen(
                                            isOverlay = true,
                                            onNavigateBack = {
                                                overlayHubTab = OverlayHubTab.DRAFT
                                            },
                                            onLoadDraft = { loadedAllies, loadedEnemies, role, isFirst ->
                                                for (i in 0 until 5) {
                                                    allies[i] = loadedAllies.getOrNull(i)?.champion
                                                    enemies[i] = loadedEnemies.getOrNull(i)?.champion
                                                }
                                                activeRole = role
                                                isFirstPick = isFirst
                                                overlayHubTab = OverlayHubTab.DRAFT
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Footer con acciones y estado
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
                                    enabled = true,
                                    onCheckedChange = { isChecked -> 
                                        if (isChecked) {
                                            autoScanEnabled = true
                                            if (screenCaptureManager?.isReady() != true) {
                                                scanNoticeMessage = "⚠️ Requiere permiso de pantalla. Toca aquí para activarlo."
                                                try {
                                                    val reqIntent = Intent(context, com.example.MainActivity::class.java).apply {
                                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                                        putExtra("EXTRA_REQUEST_CAPTURE", true)
                                                    }
                                                    context.startActivity(reqIntent)
                                                } catch (_: Exception) {}
                                            }
                                        } else {
                                            autoScanEnabled = false
                                        }
                                    },
                                    modifier = Modifier.scale(0.7f),
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = HextechDarkBg,
                                        checkedTrackColor = Color(0xFF00FF7F),
                                        disabledCheckedTrackColor = TextMuted.copy(alpha = 0.3f),
                                        disabledUncheckedTrackColor = HextechSurface
                                    )
                                )
                            }
                            }
                        }
                    }

                    } // close Box
                } // close Card
            } // close AnimatedVisibility
        } // close Column

    // Modal de selección de rol
    if (showRoleChangeDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .clickable { showRoleChangeDialog = false },
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .clickable { /* no-op */ },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurfaceVariant),
                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = tr("Selecciona tu Línea"),
                        color = HextechGold,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LaneRole.entries.forEach { role ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (role == activeRole) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                                .border(1.dp, if (role == activeRole) HextechCyan else HextechCardBorder, RoundedCornerShape(8.dp))
                                .clickable {
                                    activeRole = role
                                    com.example.util.UserPreferences.setActiveDraftRole(context, role)
                                    showRoleChangeDialog = false
                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = when (role) {
                                    LaneRole.TOP -> Icons.Default.Hardware
                                    LaneRole.JUNGLE -> Icons.Default.Eco
                                    LaneRole.MID -> Icons.Default.LocalFireDepartment
                                    LaneRole.ADC -> Icons.Default.Security
                                    LaneRole.SUPPORT -> Icons.Default.Healing
                                },
                                contentDescription = null,
                                tint = if (role == activeRole) HextechCyan else TextMuted,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = tr(role.displayName),
                                color = if (role == activeRole) HextechCyan else TextPrimary,
                                fontWeight = if (role == activeRole) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

    // Modal para Guardar Partida en Base de Datos Room
    if (showSaveDraftDialog) {
        FloatingSaveMatchDialog(
            activeRole = activeRole,
            isFirstPick = isFirstPick,
            allies = allies.filterNotNull(),
            enemies = enemies.filterNotNull(),
            analysis = analysis,
            onDismiss = { showSaveDraftDialog = false },
            onSaved = {
                isSavedRecently = true
                showSaveDraftDialog = false
                overlayHubTab = OverlayHubTab.HISTORY
            }
        )
    }

    // Modal de selección rápida de campeón si el usuario toca un slot manual
    if (showChampionPickerForSlot != null) {
        val (isAllySlot, slotIndex) = showChampionPickerForSlot!!
        val targetRole = defaultRoles.getOrNull(slotIndex)
        var selectedRoleFilter by remember { mutableStateOf<LaneRole?>(null) }
        var searchChampQuery by remember { mutableStateOf("") }
        val currentChampInSlot = if (isAllySlot) allies.getOrNull(slotIndex)?.id else enemies.getOrNull(slotIndex)?.id
        val alreadySelectedIds = remember(allies.toList(), enemies.toList(), slotIndex, isAllySlot) {
            val set = (allies.filterNotNull().map { it.id } + enemies.filterNotNull().map { it.id }).toMutableSet()
            if (currentChampInSlot != null) {
                set.remove(currentChampInSlot)
            }
            set
        }

        val filteredList = remember(searchChampQuery, alreadySelectedIds, selectedRoleFilter, targetRole) {
            WildRiftRepository.champions.filter { champ ->
                val notSelected = !alreadySelectedIds.contains(champ.id)
                val matchesQuery = searchChampQuery.isBlank() || champ.name.contains(searchChampQuery, ignoreCase = true) || champ.summary.contains(searchChampQuery, ignoreCase = true)
                val matchesRole = selectedRoleFilter == null || champ.primaryRole == selectedRoleFilter || champ.secondaryRoles.contains(selectedRoleFilter)
                notSelected && matchesQuery && matchesRole
            }.sortedWith(
                compareByDescending<Champion> { selectedRoleFilter != null && it.primaryRole == selectedRoleFilter }
                    .thenByDescending { selectedRoleFilter == null && targetRole != null && (it.primaryRole == targetRole || it.secondaryRoles.contains(targetRole)) }
                    .thenByDescending { it.tier == "S+" }
                    .thenByDescending { it.tier == "S" }
                    .thenBy { it.name }
            )
        }

        Box(
            modifier = Modifier
                .widthIn(min = 280.dp, max = 330.dp)
                .heightIn(min = 340.dp, max = 460.dp)
                .padding(4.dp)
                .pointerInput(Unit) { },
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(390.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, if (isAllySlot) AllyBlue else DangerRed)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = (if (isAllySlot) "🔵 " + tr("Elegir Aliado") else "🔴 " + tr("Elegir Rival")) + (if (targetRole != null) " - ${com.example.util.tr(targetRole.displayName)}" else ""),
                            color = if (isAllySlot) AllyBlue else DangerRed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp
                        )
                        IconButton(onClick = { showChampionPickerForSlot = null }, modifier = Modifier.size(22.dp)) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted, modifier = Modifier.size(16.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Buscador Compacto y Proporcionado
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(HextechSurface)
                            .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                if (searchChampQuery.isEmpty()) {
                                    Text(tr("Buscar campeón..."), color = TextMuted, fontSize = 10.5.sp)
                                }
                                androidx.compose.foundation.text.BasicTextField(
                                    value = searchChampQuery,
                                    onValueChange = { searchChampQuery = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = androidx.compose.ui.text.TextStyle(color = TextPrimary, fontSize = 10.5.sp),
                                    singleLine = true,
                                    cursorBrush = androidx.compose.ui.graphics.SolidColor(HextechCyan)
                                )
                            }
                            if (searchChampQuery.isNotEmpty()) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Limpiar",
                                    tint = TextMuted,
                                    modifier = Modifier.size(12.dp).clickable { searchChampQuery = "" }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Selector de Líneas / Filtro Flexible por Rol
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        val filterOptions = listOf<LaneRole?>(null, LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
                        filterOptions.forEach { lane ->
                            val isSel = selectedRoleFilter == lane
                            val label = lane?.let { com.example.util.tr(it.shortName) } ?: tr("Todos")
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (isSel) HextechCyan else HextechSurface)
                                    .border(0.5.dp, if (isSel) HextechGold else HextechCardBorder, RoundedCornerShape(4.dp))
                                    .clickable { selectedRoleFilter = lane }
                                    .padding(vertical = 3.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 8.sp,
                                    fontWeight = if (isSel) FontWeight.Black else FontWeight.Medium,
                                    color = if (isSel) HextechDarkBg else TextPrimary,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        items(filteredList) { champ ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechSurface)
                                    .clickable {
                                        if (isAllySlot) {
                                            for (i in 0 until 5) {
                                                if (allies[i]?.id == champ.id) allies[i] = null
                                                if (enemies[i]?.id == champ.id) enemies[i] = null
                                            }
                                            if (slotIndex in 0 until 5) {
                                                allies[slotIndex] = champ
                                            }
                                        } else {
                                            for (i in 0 until 5) {
                                                if (allies[i]?.id == champ.id) allies[i] = null
                                                if (enemies[i]?.id == champ.id) enemies[i] = null
                                            }
                                            if (slotIndex in 0 until 5) {
                                                enemies[slotIndex] = champ
                                            }
                                        }
                                        showChampionPickerForSlot = null
                                    }
                                    .padding(horizontal = 6.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ChampionAvatar(champion = champ, size = 26.dp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(champ.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 11.sp, maxLines = 1)
                                    Text(
                                        text = com.example.util.tr(champ.primaryRole.displayName),
                                        color = TextMuted,
                                        fontSize = 8.5.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(HextechGold.copy(alpha = 0.15f))
                                        .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Text(champ.tier, color = HextechGold, fontWeight = FontWeight.Black, fontSize = 9.sp)
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
private fun FloatingSaveMatchDialog(
    activeRole: LaneRole,
    isFirstPick: Boolean,
    allies: List<Champion>,
    enemies: List<Champion>,
    analysis: com.example.model.DraftAnalysisResult,
    onDismiss: () -> Unit,
    onSaved: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var selectedResult by remember { mutableStateOf("PENDING") }
    var notesText by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        com.example.data.AccountProfileManager.init(context)
    }

    val profiles by com.example.data.AccountProfileManager.allProfiles.collectAsState()
    val activeProfileId by com.example.data.AccountProfileManager.activeProfileId.collectAsState()
    var selectedProfileId by remember(activeProfileId) { mutableStateOf(activeProfileId) }

    val myChampion = allies.getOrNull(activeRole.ordinal) ?: allies.firstOrNull()
    val enemyOpponent = enemies.getOrNull(activeRole.ordinal) ?: enemies.firstOrNull()
    val winrateDisplay = (analysis.bestOverallPick?.estimatedWinrate ?: analysis.recommendations.firstOrNull()?.estimatedWinrate ?: 50.0).toInt()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.82f))
            .padding(10.dp)
            .pointerInput(Unit) { },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 480.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
            border = BorderStroke(1.5.dp, HextechGold)
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "💾 " + tr("Guardar en Historial"),
                        color = HextechGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }

                // Matchup summary badge
                if (myChampion != null || enemyOpponent != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .border(1.dp, HextechCyan.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${myChampion?.name ?: "Mi Pick"} (${activeRole.shortName})",
                                color = AllyBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                            if (enemyOpponent != null) {
                                Text(" vs ", color = TextMuted, fontSize = 10.sp)
                                Text(
                                    text = enemyOpponent.name,
                                    color = DangerRed,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }
                        Text(
                            text = "WR: $winrateDisplay%",
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp
                        )
                    }
                }

                // Perfil de Cuenta
                if (profiles.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "👤 " + tr("Perfil / Cuenta:"),
                        color = TextPrimary,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        profiles.take(3).forEach { profile ->
                            val isSelected = selectedProfileId == profile.id
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isSelected) HextechCyan.copy(alpha = 0.25f) else HextechSurface)
                                    .border(1.dp, if (isSelected) HextechCyan else HextechCardBorder, RoundedCornerShape(6.dp))
                                    .clickable { selectedProfileId = profile.id }
                                    .padding(vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = profile.name,
                                    color = if (isSelected) HextechCyan else TextPrimary,
                                    fontSize = 9.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = tr("Resultado de la Partida:"),
                    color = TextPrimary,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val isPending = selectedResult == "PENDING" || selectedResult == "IN_PROGRESS"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isPending) HextechGold.copy(alpha = 0.25f) else HextechSurface)
                            .border(1.5.dp, if (isPending) HextechGold else HextechCardBorder, RoundedCornerShape(8.dp))
                            .clickable { selectedResult = "PENDING" }
                            .padding(vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "⏳ " + tr("En espera"),
                            color = if (isPending) HextechGold else TextMuted,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp
                        )
                    }

                    val isVic = selectedResult == "VICTORY"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isVic) Color(0xFF00FF7F).copy(alpha = 0.25f) else HextechSurface)
                            .border(1.5.dp, if (isVic) Color(0xFF00FF7F) else HextechCardBorder, RoundedCornerShape(8.dp))
                            .clickable { selectedResult = "VICTORY" }
                            .padding(vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "👑 " + tr("Victoria"),
                            color = if (isVic) Color(0xFF00FF7F) else TextMuted,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp
                        )
                    }

                    val isDef = selectedResult == "DEFEAT"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isDef) DangerRed.copy(alpha = 0.25f) else HextechSurface)
                            .border(1.5.dp, if (isDef) DangerRed else HextechCardBorder, RoundedCornerShape(8.dp))
                            .clickable { selectedResult = "DEFEAT" }
                            .padding(vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "💔 " + tr("Derrota"),
                            color = if (isDef) DangerRed else TextMuted,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = tr("Notas tácticas / Matchup:"),
                    color = TextPrimary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(3.dp))
                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    placeholder = { Text(tr("Ej: Matchup ganado en nivel 3, priorizar cortar curaciones..."), fontSize = 9.5.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 10.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechCyan,
                        unfocusedBorderColor = HextechCardBorder
                    ),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (!isSaving) {
                            isSaving = true
                            coroutineScope.launch {
                                val allySlots = allies.mapIndexed { index, champ ->
                                    val role = when (index) {
                                        0 -> LaneRole.TOP
                                        1 -> LaneRole.JUNGLE
                                        2 -> LaneRole.MID
                                        3 -> LaneRole.ADC
                                        else -> LaneRole.SUPPORT
                                    }
                                    DraftSlot(champ, role)
                                }
                                val chosenProfile = profiles.find { it.id == selectedProfileId }
                                    ?: com.example.data.AccountProfileManager.getActiveProfile(context)
                                DraftHistoryRepository.saveDraft(
                                    context = context,
                                    myRole = activeRole,
                                    isFirstPick = isFirstPick,
                                    allies = allySlots,
                                    enemies = enemies,
                                    analysis = analysis,
                                    notes = notesText,
                                    matchResult = selectedResult,
                                    accountProfileId = chosenProfile.id,
                                    accountProfileName = chosenProfile.name
                                )
                                android.widget.Toast.makeText(context, "¡Partida guardada en el historial!", android.widget.Toast.LENGTH_SHORT).show()
                                isSaving = false
                                onSaved()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    enabled = !isSaving
                ) {
                    if (isSaving) {
                        CircularProgressIndicator(modifier = Modifier.size(14.dp), color = HextechDarkBg, strokeWidth = 2.dp)
                    } else {
                        Text(
                            text = "Guardar y Actualizar Historial",
                            color = HextechDarkBg,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FloatingDraftCoachView(
    isLandscapeMode: Boolean,
    activeRole: LaneRole,
    onActiveRoleChange: (LaneRole) -> Unit,
    isFirstPick: Boolean,
    onFirstPickToggle: () -> Unit,
    isLoadingScreenMode: Boolean,
    onLoadingScreenModeToggle: () -> Unit,
    allies: androidx.compose.runtime.snapshots.SnapshotStateList<Champion?>,
    enemies: androidx.compose.runtime.snapshots.SnapshotStateList<Champion?>,
    enemyConfidences: androidx.compose.runtime.snapshots.SnapshotStateMap<LaneRole, Int>,
    analysis: com.example.model.DraftAnalysisResult,
    selectedChampionDetail: Champion?,
    onSelectChampion: (Champion?) -> Unit,
    onOpenChampionPicker: (isAlly: Boolean, index: Int) -> Unit,
    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    onManualEdit: () -> Unit
) {
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()

    val defaultRoles = remember { listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT) }

    val explicitEnemyOpponent = remember(activeRole, enemies.toList(), isLoadingScreenMode) {
        if (isLoadingScreenMode) {
            val roleIndex = defaultRoles.indexOf(activeRole).coerceIn(0, 4)
            enemies.getOrNull(roleIndex)
        } else {
            enemies.filterNotNull().find { it.primaryRole == activeRole }
        }
    }

    val allySlots = remember(allies.toList()) {
        defaultRoles.mapIndexedNotNull { index, role ->
            allies.getOrNull(index)?.let { DraftSlot(champion = it, assignedRole = role) }
        }
    }
    val enemySlots = remember(enemies.toList(), enemyConfidences.toMap()) {
        defaultRoles.mapIndexedNotNull { index, role ->
            enemies.getOrNull(index)?.let {
                val conf = enemyConfidences[role] ?: 85
                DraftSlot(champion = it, assignedRole = role, confidence = conf)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 4.dp)
    ) {
        // TABLERO DE DRAFT VERSUS (ALIADO VS RIVAL POR LÍNEAS)
        OverlayVersusDraftBoard(
            allySlots = allySlots,
            enemySlots = enemySlots,
            activeUserRole = activeRole,
            onPickChampionForRole = { isAlly, role ->
                val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                onOpenChampionPicker(isAlly, index)
            },
            onRemoveChampionForRole = { isAlly, role ->
                val roleIndex = defaultRoles.indexOf(role)
                if (roleIndex in 0 until 5) {
                    if (isAlly) {
                        allies[roleIndex] = null
                    } else {
                        enemies[roleIndex] = null
                        enemyConfidences.remove(role)
                    }
                    onManualEdit()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // CONTENIDO DEL COACH (CONTROLES Y ANÁLISIS)
        CoachContent(
            allies = allies,
            enemies = enemies,
            activeRole = activeRole,
            onActiveRoleChange = onActiveRoleChange,
            isFirstPick = isFirstPick,
            onFirstPickToggle = onFirstPickToggle,
            analysis = analysis,
            explicitEnemyOpponent = explicitEnemyOpponent,
            onSelectChampion = onSelectChampion,
            onSaveDraftClick = onSaveDraftClick,
            isSavedRecently = isSavedRecently,
            onClearAll = onClearAll,
            onGoToTierList = onGoToTierList,
            isPremium = isPremium
        )
    }
}


@Composable
private fun OverlayVersusDraftBoard(
    allySlots: List<DraftSlot>,
    enemySlots: List<DraftSlot>,
    activeUserRole: LaneRole?,
    onPickChampionForRole: (isAlly: Boolean, LaneRole) -> Unit,
    onRemoveChampionForRole: (isAlly: Boolean, LaneRole) -> Unit
) {
    val roles = listOf(
        Triple(LaneRole.TOP, "TOP", R.drawable.ic_wr_role_solo),
        Triple(LaneRole.JUNGLE, "JUG", R.drawable.ic_wr_role_jungle),
        Triple(LaneRole.MID, "MID", R.drawable.ic_wr_role_mid),
        Triple(LaneRole.ADC, "DÚO", R.drawable.ic_wr_role_duo),
        Triple(LaneRole.SUPPORT, "SUP", R.drawable.ic_wr_role_support)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(tr("ALIADO"), color = AllyBlue, fontWeight = FontWeight.Black, fontSize = 11.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
                Text("VS", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 10.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text(tr("RIVAL"), color = DangerRed, fontWeight = FontWeight.Black, fontSize = 11.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }

            roles.forEachIndexed { index, (role, label, iconRes) ->
                val allySlot = allySlots.find { it.assignedRole == role }
                val enemySlot = enemySlots.find { it.assignedRole == role }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ally Avatar + Name
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPickChampionForRole(true, role) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        DraftAvatarBox(
                            slot = allySlot,
                            isEnemy = false,
                            isMyRole = activeUserRole == role,
                            onClick = { onPickChampionForRole(true, role) },
                            onRemove = { onRemoveChampionForRole(true, role) }
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        if (allySlot?.champion != null) {
                            Column(modifier = Modifier.weight(1f, fill = false)) {
                                Text(
                                    text = allySlot.champion.name,
                                    color = if (activeUserRole == role) HextechCyan else TextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = allySlot.champion.tier,
                                    color = HextechGold,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        } else {
                            Text(
                                text = tr("Elegir"),
                                color = TextMuted.copy(alpha = 0.6f),
                                fontSize = 8.5.sp,
                                maxLines = 1
                            )
                        }
                    }

                    // Center Role (Horizontal layout to save vertical space)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        Icon(painterResource(id = iconRes), contentDescription = label, tint = HextechGold, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(label, color = TextSecondary, fontSize = 8.5.sp, fontWeight = FontWeight.Bold)
                    }

                    // Enemy Name + Confidence Tag + Enemy Avatar
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPickChampionForRole(false, role) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (enemySlot?.confidence != null && enemySlot.champion != null) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 3.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFF0F1923).copy(alpha = 0.9f))
                                    .border(0.5.dp, if (enemySlot.confidence >= 80) HextechCyan.copy(alpha = 0.6f) else HextechGold.copy(alpha = 0.6f), RoundedCornerShape(3.dp))
                                    .padding(horizontal = 2.5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "${enemySlot.confidence}%",
                                    color = if (enemySlot.confidence >= 80) HextechCyan else HextechGold,
                                    fontSize = 7.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        if (enemySlot?.champion != null) {
                            Column(
                                horizontalAlignment = Alignment.End,
                                modifier = Modifier.weight(1f, fill = false).padding(end = 5.dp)
                            ) {
                                Text(
                                    text = enemySlot.champion.name,
                                    color = DangerRed,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.End
                                )
                                Text(
                                    text = enemySlot.champion.tier,
                                    color = HextechGold,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.End
                                )
                            }
                        } else {
                            Text(
                                text = tr("Elegir"),
                                color = TextMuted.copy(alpha = 0.6f),
                                fontSize = 8.5.sp,
                                maxLines = 1,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                        }

                        DraftAvatarBox(
                            slot = enemySlot,
                            isEnemy = true,
                            isMyRole = false,
                            onClick = { onPickChampionForRole(false, role) },
                            onRemove = { onRemoveChampionForRole(false, role) }
                        )
                    }
                }
                
                if (index < roles.size - 1) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(HextechCardBorder.copy(alpha=0.5f)))
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
private fun DraftAvatarBox(
    slot: DraftSlot?,
    isEnemy: Boolean,
    isMyRole: Boolean,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    val champ = slot?.champion
    val borderColor = if (isMyRole) HextechCyan else if (champ != null) (if (isEnemy) DangerRed else HextechGold) else HextechCardBorder
    
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(when {
                isMyRole -> HextechCyan.copy(alpha = 0.22f)
                champ != null -> if (isEnemy) DangerRed.copy(alpha=0.2f) else HextechGold.copy(alpha=0.2f)
                else -> Color(0xFF070D15)
            })
            .border(1.5.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (champ != null) {
            AppAssetImage(
                url = champ.avatarUrl,
                contentDescription = champ.name,
                fallbackText = champ.name,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
            )
            if (isMyRole) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(HextechCyan)
                        .padding(horizontal = 3.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = tr("TÚ"),
                        color = Color.Black,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(16.dp)
                    .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(bottomStart = 8.dp))
                    .clickable { onRemove() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Close, contentDescription = "Quitar", tint = Color.White, modifier = Modifier.size(12.dp))
            }
        } else {
            Icon(Icons.Default.Add, contentDescription = "Añadir", tint = TextMuted, modifier = Modifier.size(20.dp))
        }
    }
}


@Composable
private fun CoachContent(
    allies: List<com.example.model.Champion?>,
    enemies: List<com.example.model.Champion?>,
    activeRole: LaneRole,
    onActiveRoleChange: (LaneRole) -> Unit,
    isFirstPick: Boolean,
    onFirstPickToggle: () -> Unit,
    analysis: com.example.model.DraftAnalysisResult,
    explicitEnemyOpponent: Champion?,
    onSelectChampion: (Champion?) -> Unit,
    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    isPremium: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // 2. SELECTOR DE MI ROL / LÍNEA
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
                        .clickable { onActiveRoleChange(role) }
                        .padding(vertical = 3.5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tr(role.shortName),
                        fontSize = 9.sp,
                        fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal,
                        color = if (isSelected) HextechDarkBg else TextPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "⚔️ " + tr("RECOMENDACIÓN:") + " ${tr(activeRole.displayName)}",
                color = HextechGold,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (isFirstPick) "⭐ " + tr("1ª Elección") else "🎯 " + tr("Counter Pick"),
                color = if (isFirstPick) HextechGold else HextechCyan,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(HextechSurface)
                    .border(0.5.dp, if (isFirstPick) HextechGold else HextechCyan, RoundedCornerShape(4.dp))
                    .clickable { onFirstPickToggle() }
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            )
        }

        // Sinergias (Wombos)
        val allyWombos = remember(allies.toList()) { WomboComboSynergyDetector.detectWombos(allies.filterNotNull()) }
        val enemyWombos = remember(enemies.toList()) { WomboComboSynergyDetector.detectWombos(enemies.filterNotNull()) }

        if (allyWombos.isNotEmpty() || enemyWombos.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                allyWombos.forEach { wombo ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.6f)),
                        border = BorderStroke(0.5.dp, AllyBlue)
                    ) {
                        Text(text = "🔵 ${wombo.title}: ${wombo.description}", color = AllyBlue, fontSize = 8.5.sp, modifier = Modifier.padding(3.dp))
                    }
                }
                enemyWombos.forEach { wombo ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.6f)),
                        border = BorderStroke(0.5.dp, DangerRed)
                    ) {
                        Text(text = "🔴 ${wombo.title}: ${wombo.description}", color = DangerRed, fontSize = 8.5.sp, modifier = Modifier.padding(3.dp))
                    }
                }
            }
        }

        // Distribución de Daño del Draft (Aliados vs Enemigos)
        if (allies.any { it != null } || enemies.any { it != null }) {
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(HextechSurface)
                    .border(0.5.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "🔵 Daño Aliado: AD ${analysis.allyPhysicalDamagePercent}% | AP ${analysis.allyMagicDamagePercent}%",
                        color = AllyBlue,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "🔴 Daño Enemigo: AD ${analysis.physicalDamagePercent}% | AP ${analysis.magicDamagePercent}%",
                        color = DangerRed,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                val winrateDisplay = (analysis.bestOverallPick?.estimatedWinrate ?: analysis.recommendations.firstOrNull()?.estimatedWinrate ?: 50.0).toInt()
                Text(
                    text = "WR Estimado: ${winrateDisplay}%",
                    color = HextechGold,
                    fontSize = 8.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Alerta táctica del Coach / Win condition
        if (!analysis.directMatchupWarning.isNullOrBlank() || !analysis.allyCompositionWarning.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            val warningText = analysis.directMatchupWarning ?: analysis.allyCompositionWarning ?: ""
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HextechGold.copy(alpha = 0.12f)),
                border = BorderStroke(0.8.dp, HextechGold.copy(alpha = 0.7f)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⚠️", fontSize = 10.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = warningText,
                        color = HextechGold,
                        fontSize = 8.5.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 10.sp
                    )
                }
            }
        }

        // Análisis 1v1 de línea / Matchup Directo con Rival
        if (explicitEnemyOpponent != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = BorderStroke(1.dp, DangerRed.copy(alpha = 0.6f)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Column(modifier = Modifier.padding(6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ChampionAvatar(champion = explicitEnemyOpponent, size = 22.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "⚔️ " + tr("Matchup 1v1 vs") + " ${explicitEnemyOpponent.name}",
                            color = DangerRed,
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = analysis.directMatchupWarning ?: "Analizando ventana de poder en línea contra ${explicitEnemyOpponent.name}.",
                        color = TextPrimary,
                        fontSize = 8.5.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Botones de acción rápida: Guardar Partida, Vaciar Todo y Ver Tier List
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = onSaveDraftClick,
                modifier = Modifier.weight(1.1f).height(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSavedRecently) Color(0xFF00FF7F).copy(alpha = 0.2f) else HextechGold.copy(alpha = 0.15f)
                ),
                border = BorderStroke(1.dp, if (isSavedRecently) Color(0xFF00FF7F) else HextechGold),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (isSavedRecently) "✓ " + tr("Guardado") else "💾 " + tr("Guardar"),
                        color = if (isSavedRecently) Color(0xFF00FF7F) else HextechGold,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (!isPremium) {
                        Spacer(modifier = Modifier.width(2.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(3.dp))
                                .background(HextechGold)
                                .padding(horizontal = 2.5.dp, vertical = 0.5.dp)
                        ) {
                            Text("PRO", color = HextechDarkBg, fontSize = 6.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }

            Button(
                onClick = onClearAll,
                modifier = Modifier.weight(0.9f).height(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DangerRed.copy(alpha = 0.15f)),
                border = BorderStroke(1.dp, DangerRed.copy(alpha = 0.7f)),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
            ) {
                Text(
                    text = "🧹 " + tr("Vaciar"),
                    color = DangerRed,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onGoToTierList,
                modifier = Modifier.weight(1f).height(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
            ) {
                Text(
                    text = "🏆 " + tr("Tier List"),
                    color = HextechDarkBg,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // 3. MEJORES PICKS RECOMENDADOS POR EL COACH
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            analysis.recommendations.take(4).forEach { pick ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onSelectChampion(pick.champion) },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChampionAvatar(champion = pick.champion, size = 34.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(pick.champion.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(TierSPlusColor)
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Text(pick.champion.tier, color = Color.Black, fontSize = 7.5.sp, fontWeight = FontWeight.Black)
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("WR: ${pick.estimatedWinrate}%", color = HextechGold, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(pick.advantageBadge, color = HextechCyan, fontSize = 8.5.sp, fontWeight = FontWeight.SemiBold)
                            Text(pick.tacticalReason, color = TextMuted, fontSize = 8.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                    }
                    
                }
            }
        }
    }
}


@Composable
fun VisionDebugOverlay() {
    val bitmap by com.example.service.screen.DraftVisionScanner.lastDebugBitmap.collectAsStateWithLifecycle()
    val diagnostics by com.example.service.screen.DraftVisionScanner.lastDiagnostics.collectAsStateWithLifecycle()
    
    // Test box to see if overlay works at all
    androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize().background(Color.Red.copy(alpha=0.3f)))

    val currentBitmap = bitmap ?: return

    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleX = size.width / currentBitmap.width.toFloat()
        val scaleY = size.height / currentBitmap.height.toFloat()

        for (diag in diagnostics) {
            val rect = diag.roiRect ?: continue
            val isAlly = diag.isAlly

            val left = rect.left * scaleX
            val top = rect.top * scaleY
            val right = rect.right * scaleX
            val bottom = rect.bottom * scaleY

            val color = when (diag.status) {
                com.example.service.screen.DiagnosticStatus.CONFIRMADO -> androidx.compose.ui.graphics.Color.Green
                com.example.service.screen.DiagnosticStatus.VACIO -> androidx.compose.ui.graphics.Color.Gray
                else -> androidx.compose.ui.graphics.Color.Red
            }

            val insetX = (right - left) * 0.075f
            val insetY = (bottom - top) * 0.075f
            val drawLeft = left + insetX
            val drawTop = top + insetY
            val drawWidth = (right - left) - (insetX * 2)
            val drawHeight = (bottom - top) - (insetY * 2)

            drawRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(drawLeft, drawTop),
                size = androidx.compose.ui.geometry.Size(drawWidth, drawHeight),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 12f)
            )

            // Draw score
            val finalChampName = diag.finalChampion?.name ?: (diag.ocrChampion?.name ?: diag.candidate1?.name ?: "Unknown")
            val method = if (diag.finalChampion?.id == diag.ocrChampion?.id && diag.ocrChampion != null) "OCR" else "VISUAL"
            
            drawContext.canvas.nativeCanvas.drawText(
                "${finalChampName} [$method]",
                left,
                top - 10f,
                android.graphics.Paint().apply {
                    this.color = android.graphics.Color.YELLOW
                    this.textSize = 30f
                    this.isAntiAlias = true
                }
            )
        }
    }
}
val showVisionDebugger = kotlinx.coroutines.flow.MutableStateFlow(false)
