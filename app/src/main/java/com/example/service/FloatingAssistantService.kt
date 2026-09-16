package com.example.service
import android.graphics.Bitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.BoxWithConstraints

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.FileUpload
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
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import com.example.ui.components.DraftCalibrationPanel
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
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
import com.example.service.screen.LocalVisionAnalyzer
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
import android.content.ComponentCallbacks2
import android.content.res.Configuration
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

class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner, ComponentCallbacks2 {
    private val overlayState = OverlayState()
    private var screenCaptureManager: ScreenCaptureManager? = null
    private var isDestroyed = false

    private var windowManager: WindowManager? = null
    private var floatingComposeView: ComposeView? = null
    
    // Estado para la orientación de la pantalla real
    private val isDeviceLandscape = androidx.compose.runtime.mutableStateOf(false)
    private var closeTargetComposeView: ComposeView? = null
    private var debugOverlayView: ComposeView? = null
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
            val hasPendingCapture = ScreenCaptureManager.pendingMediaProjectionData != null
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    val fgsType = if (hasPendingCapture) {
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE or
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
                    } else {
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                    }
                    androidx.core.app.ServiceCompat.startForeground(
                        this,
                        NOTIFICATION_ID,
                        notification,
                        fgsType
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (hasPendingCapture) {
                        androidx.core.app.ServiceCompat.startForeground(
                            this,
                            NOTIFICATION_ID,
                            notification,
                            android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
                        )
                    } else {
                        startForeground(NOTIFICATION_ID, notification)
                    }
                } else {
                    startForeground(NOTIFICATION_ID, notification)
                }
            } catch (e: Exception) {
                AppLogger.w("FloatingService", "Fallback foreground service start: ${e.message}")
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        androidx.core.app.ServiceCompat.startForeground(
                            this,
                            NOTIFICATION_ID,
                            notification,
                            android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                        )
                    } else {
                        startForeground(NOTIFICATION_ID, notification)
                    }
                } catch (_: Exception) {}
            }

            if (ScreenCaptureManager.pendingMediaProjectionData != null) {
                screenCaptureManager?.initializeProjection(
                    ScreenCaptureManager.pendingMediaProjectionResultCode,
                    ScreenCaptureManager.pendingMediaProjectionData!!
                )
                ScreenCaptureManager.pendingMediaProjectionData = null
            }

            createFloatingOverlay()
            registerComponentCallbacks(this)
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
                    androidx.core.app.ServiceCompat.startForeground(
                        this,
                        NOTIFICATION_ID,
                        notification,
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE or
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    androidx.core.app.ServiceCompat.startForeground(
                        this,
                        NOTIFICATION_ID,
                        notification,
                        android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
                    )
                } else {
                    startForeground(NOTIFICATION_ID, notification)
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

    private fun updateOverlayRect(params: WindowManager.LayoutParams, isExpanded: Boolean) {
        try {
            val density = resources.displayMetrics.density
            val isLandscape = resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels
            val cWidth = floatingComposeView?.width?.takeIf { it > 0 } ?: if (isExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
            val cHeight = floatingComposeView?.height?.takeIf { it > 0 } ?: if (isExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
            
            // Adjust coordinates to absolute screen pixels to match MediaProjection bitmap
            val loc = IntArray(2)
            floatingComposeView?.getLocationOnScreen(loc)
            val absoluteX = if (loc[0] != 0) loc[0] else params.x
            val absoluteY = if (loc[1] != 0) loc[1] else params.y
            
            val margin = (32 * density).toInt() // Incremented margin to be safe
            DraftVisionScanner.overlayRect = android.graphics.Rect(absoluteX - margin, absoluteY - margin, absoluteX + cWidth + margin, absoluteY + cHeight + margin)
        } catch (_: Exception) {}
    }

    override fun onDestroy() {
        if (isDestroyed) return
        isDestroyed = true

        try {
            unregisterComponentCallbacks(this)
        } catch (_: Exception) {}

        // 1. Desconectar todas las vistas del WindowManager ANTES de destruir el ciclo de vida
        removeFloatingOverlay()

        // 2. Liberar recursos de captura
        try {
            screenCaptureManager?.release()
            screenCaptureManager = null
        } catch (_: Exception) {}

        // 3. Notificar fin de ciclo de vida y limpiar ViewModelStore de forma segura
        try {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            store.clear()
        } catch (_: Exception) {}

        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        try {
            System.gc()
        } catch (_: Exception) {}
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level >= TRIM_MEMORY_MODERATE) {
            try {
                System.gc()
            } catch (_: Exception) {}
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        // No detener el servicio en segundo plano para permitir uso continuo sobre Wild Rift y evitar cierres involuntarios al rotar o cambiar de app
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
            .setContentTitle("Coach Activo")
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
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                FloatingCloseTarget(
                    isVisible = isCloseTargetVisible,
                    isTargeted = isCloseTargetHovered
                )
            }
        }

        try {
            windowManager?.addView(closeTargetComposeView, closeTargetParams)
        } catch (_: Exception) {}

        val debugParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
        }

        debugOverlayView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                val showBoxes by com.example.service.screen.DraftVisionScanner.showCalibrationBoxes.collectAsStateWithLifecycle()
                if (showBoxes) {
                    com.example.ui.components.ScannerDebugOverlay(
                        config = com.example.service.screen.DraftVisionScanner.calibrationConfig,
                        overlayRect = com.example.service.screen.DraftVisionScanner.overlayRect
                    )
                }
            }
        }

        try {
            windowManager?.addView(debugOverlayView, debugParams)
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
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

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
                                val currentIsLandscape = currentScreenWidth > currentScreenHeight
                                val dynamicCardWidthPx = ((if (currentIsLandscape) 560 else 330) * density).toInt()
                                val currentWidth = if (overlayState.isExpanded) dynamicCardWidthPx else bubbleSizePx
                                val dynamicCardHeightPx = ((if (currentIsLandscape) 390 else 520) * density).toInt()
                                val currentHeight = if (overlayState.isExpanded) dynamicCardHeightPx else bubbleSizePx
                                val maxX = currentScreenWidth - marginPx
                                val maxY = (currentScreenHeight - currentHeight - marginPx).coerceAtLeast(marginPx)
                                
                                params.x = (params.x + dx).coerceIn(0, maxX)
                                params.y = (params.y + dy).coerceIn(0, maxY)

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
                                        this@FloatingAssistantService.updateOverlayRect(params, isOverlayExpanded)
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
            val wm = windowManager ?: return
            floatingComposeView?.let { view ->
                try { wm.removeViewImmediate(view) } catch (_: Exception) {}
            }
            floatingComposeView = null
            closeTargetComposeView?.let { view ->
                try { wm.removeViewImmediate(view) } catch (_: Exception) {}
            }
            closeTargetComposeView = null
            debugOverlayView?.let { view ->
                try { wm.removeViewImmediate(view) } catch (_: Exception) {}
            }
            debugOverlayView = null
        } catch (_: Exception) {}
    }

    private var lastScreenWidth = 0
    private var lastScreenHeight = 0

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        try {
            val metrics = resources.displayMetrics
            val isNowLandscape = newConfig.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE || metrics.widthPixels > metrics.heightPixels
            isDeviceLandscape.value = isNowLandscape

            // Notificar al ScreenCaptureManager de la rotación de manera segura
            try {
                screenCaptureManager?.refreshProjection()
            } catch (_: Throwable) {}

            fun applyClampedLayout() {
                try {
                    val curMetrics = resources.displayMetrics
                    val screenW = curMetrics.widthPixels
                    val screenH = curMetrics.heightPixels
                    lastScreenWidth = screenW
                    lastScreenHeight = screenH

                    val params = floatingParams ?: return
                    val view = floatingComposeView ?: return
                    if (!view.isAttachedToWindow) return

                    val density = curMetrics.density
                    val marginPx = (8 * density).toInt()
                    val currentBubblePx = ((if (overlayState.isCompactBubble) 36f else 46f) * density).toInt()
                    val cardWidthPx = ((if (isNowLandscape) 550 else 330) * density).toInt()
                    val cardHeightPx = ((if (isNowLandscape) 345 else 520) * density).toInt()

                    val viewWidth = if (overlayState.isExpanded) cardWidthPx else currentBubblePx
                    val viewHeight = if (overlayState.isExpanded) cardHeightPx else currentBubblePx

                    val maxX = (screenW - viewWidth - marginPx).coerceAtLeast(marginPx)
                    val maxY = (screenH - viewHeight - marginPx).coerceAtLeast(marginPx)

                    params.x = params.x.coerceIn(0, maxX)
                    params.y = params.y.coerceIn(0, maxY)

                    windowManager?.updateViewLayout(view, params)
                    updateOverlayRect(params, isOverlayExpanded)
                } catch (eLayout: Throwable) {
                    AppLogger.w("FloatingService", "Error actualizando layout en rotación: ${eLayout.message}")
                }
            }

            // Aplicar inmediatamente y reaplicar tras 150ms para sincronizar con la animación de rotación del sistema
            applyClampedLayout()
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                applyClampedLayout()
            }, 150L)

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
    var isRoleManuallySelected by androidx.compose.runtime.mutableStateOf(false)
    var isFirstPick by androidx.compose.runtime.mutableStateOf(false)
    var isLegendaryQueue by androidx.compose.runtime.mutableStateOf(false)
    var hasScanned10thPick by androidx.compose.runtime.mutableStateOf(false)
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
    val manualLockedAllySlots = androidx.compose.runtime.mutableStateMapOf<Int, Boolean>()
    val manualLockedEnemySlots = androidx.compose.runtime.mutableStateMapOf<Int, Boolean>()
    val allySummonerNames = androidx.compose.runtime.mutableStateMapOf<Int, String>()
    val enemySummonerNames = androidx.compose.runtime.mutableStateMapOf<Int, String>()
    val allySpells = androidx.compose.runtime.mutableStateMapOf<Int, List<String>>()
    val enemySpells = androidx.compose.runtime.mutableStateMapOf<Int, List<String>>()
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
    val isAdmin = userRole == "admin" || userRole == "moderador" || (currentAuthEmail != null && currentAuthEmail.contains("barbadiego", ignoreCase = true)) || com.example.util.AuthManager.isCurrentUserAdmin()
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current
    var activeRole by state::activeRole
    
    val sharedPrefs = remember { context.getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE) }
    
    LaunchedEffect(Unit) {
        val savedRoleStr = sharedPrefs.getString("saved_active_role", null)
        if (savedRoleStr != null) {
            try {
                activeRole = LaneRole.valueOf(savedRoleStr)
                state.isRoleManuallySelected = true
            } catch (e: Exception) { }
        }
    }
    
    LaunchedEffect(activeRole) {
        sharedPrefs.edit().putString("saved_active_role", activeRole.name).apply()
    }

    var isFirstPick by state::isFirstPick
    var isLegendaryQueue by state::isLegendaryQueue
    var isCompactBubble by state::isCompactBubble
    var showCalibrationPanel by remember { mutableStateOf(false) }
    var showTenthPickLogsDialog by remember { mutableStateOf(false) }

    val defaultRoles = remember { listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT) }
    val allies = state.allies
    val enemies = state.enemies
    val manualLockedAllySlots = state.manualLockedAllySlots
    val manualLockedEnemySlots = state.manualLockedEnemySlots

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

    // Auto-Scan Loop en segundo plano optimizado en Dispatchers.IO (cada 200ms)
    // Se desactiva automáticamente al completar los 10 picks (5 aliados + 5 rivales) para evitar falsos positivos
    LaunchedEffect(autoScanEnabled) {
        if (!autoScanEnabled) return@LaunchedEffect
        while (autoScanEnabled) {
            delay(200)
            try {
                if (screenCaptureManager == null || !screenCaptureManager.isReady()) {
                    withContext(Dispatchers.Main) {
                        scanNoticeMessage = "Permiso de captura inactivo. Toca aquí para activarlo."
                    }
                } else if (!isScanning) {
                    val bitmap = withContext(Dispatchers.IO) {
                        screenCaptureManager?.captureCurrentFrame()
                    }
                    if (bitmap != null && !bitmap.isRecycled && bitmap.width > 0 && bitmap.height > 0) {
                        try {
                            val result = withContext(Dispatchers.IO) {
                                DraftVisionScanner.scanDraftFromBitmap(bitmap, context, isFirstPick, activeRole)
                            }
                            withContext(Dispatchers.Main) {
                                if (result.isSuccessful) {
                                    if (result.detectedFirstPick != null) {
                                        isFirstPick = result.detectedFirstPick
                                    }

                                    var newAlliesAdded = 0
                                    var newEnemiesAdded = 0
                                    
                                    defaultRoles.forEachIndexed { idx, role ->
                                        if (manualLockedAllySlots[idx] != true) {
                                            val scannedAlly = result.alliesByRole[role]
                                            if (scannedAlly != null) {
                                                if (allies[idx] == null || allies[idx]?.id != scannedAlly.id) {
                                                    assignAllySlot(idx, scannedAlly)
                                                    newAlliesAdded++
                                                }
                                            }
                                        }
                                        if (manualLockedEnemySlots[idx] != true) {
                                            val scannedEnemy = result.enemiesByRole[role]
                                            if (scannedEnemy != null) {
                                                if (enemies[idx] == null || enemies[idx]?.id != scannedEnemy.id) {
                                                    assignEnemySlot(idx, scannedEnemy, result.enemyConfidencesByRole[role])
                                                    if (enemies[idx] == null) newEnemiesAdded++
                                                }
                                            }
                                        }
                                    }

                                    val currentAllyPicks = allies.count { it != null }
                                    val currentEnemyPicks = enemies.count { it != null }
                                    if (currentEnemyPicks > 0 && currentAllyPicks == 0) {
                                        isFirstPick = false
                                    } else if (currentAllyPicks > 0 && currentEnemyPicks == 0) {
                                        isFirstPick = true
                                    }

                                    if (result.isLegendaryRanked) {
                                        if (!isLegendaryQueue) {
                                            isLegendaryQueue = true
                                        }
                                        if (state.allySummonerNames.isNotEmpty()) {
                                            state.allySummonerNames.clear()
                                        }
                                    } else {
                                        defaultRoles.forEachIndexed { idx, role ->
                                            val sName = result.allySummonerNamesByRole[role] ?: result.allySummonerNamesBySlot[idx]
                                            if (!sName.isNullOrBlank()) {
                                                val current = state.allySummonerNames[idx]
                                                if (current.isNullOrBlank() || sName.length > current.length || (sName.contains(" ") && !current.contains(" "))) {
                                                    state.allySummonerNames[idx] = sName
                                                }
                                            }
                                            val spells = result.allySpellsByRole[role] ?: result.allySpellsBySlot[idx]
                                            if (!spells.isNullOrEmpty()) {
                                                state.allySpells[idx] = spells
                                            }
                                        }
                                    }
                                    if (state.enemySpells.isNotEmpty()) {
                                        state.enemySpells.clear()
                                    }

                                    val finalAlliesPicked = allies.filterNotNull().size
                                    val finalEnemiesPicked = enemies.filterNotNull().size

                                    val isDraftFullyConfirmed = (finalAlliesPicked == 5 && finalEnemiesPicked == 5 && result.isLastPickConfirmed) || result.isPreparationPhase

                                    if (result.userExplicitlyDetectedRole != null && activeRole != result.userExplicitlyDetectedRole) {
                                        activeRole = result.userExplicitlyDetectedRole
                                        com.example.util.UserPreferences.setActiveDraftRole(context, result.userExplicitlyDetectedRole)
                                        scanNoticeMessage = "Auto-Scan: Tu rol detectado (${result.userExplicitlyDetectedRole.shortName})"
                                    } else if (isDraftFullyConfirmed) {
                                        autoScanEnabled = false
                                        scanNoticeMessage = "10/10 Campeones confirmados (Fase de Preparación)"
                                        AppLogger.i("FloatingService", "Auto-Scan desactivado: 10/10 campeones confirmados definitivamente con el 10º pick sellado.")
                                    } else if (finalAlliesPicked == 5 && finalEnemiesPicked == 5 && !result.isLastPickConfirmed) {
                                        val hoverName = result.lastPickChampion?.name ?: "10º Pick"
                                        scanNoticeMessage = "10º Pick en preselección: $hoverName (esperando bloqueo...)"
                                        AppLogger.d("FloatingService", "Auto-Scan activo: 10º pick en preselección ($hoverName), esperando confirmación en barra superior.")
                                    } else if (finalAlliesPicked + finalEnemiesPicked == 9) {
                                        scanNoticeMessage = "9/10 picks confirmados • Escaneando 10º pick en directo..."
                                    } else if (newAlliesAdded > 0 || newEnemiesAdded > 0) {
                                        scanNoticeMessage = "Auto-Scan: +${newAlliesAdded + newEnemiesAdded} picks detectados ($finalAlliesPicked/5 vs $finalEnemiesPicked/5)"
                                    }

                                    if (scanNoticeMessage != null) {
                                        launch {
                                            delay(2000)
                                            scanNoticeMessage = null
                                        }
                                    }
                                }
                            }
                        } finally {
                            try {
                                bitmap.recycle()
                            } catch (_: Throwable) {}
                        }
                    }
                }
            } catch (t: Throwable) {
                AppLogger.e("FloatingService", "Error in auto-scan loop", t)
                delay(300)
            }
        }
    }

    fun triggerManualScan() {
        if (screenCaptureManager?.isReady() != true) {
            scanNoticeMessage = "Requiere permiso de pantalla. Abriendo solicitud..."
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
                val result = DraftVisionScanner.scanDraftFromBitmap(bitmap, context, isFirstPick, activeRole)
                withContext(Dispatchers.Main) {
                    if (result.isSuccessful) {
                        // Sincronizar primera selección si se detectó
                        if (result.detectedFirstPick != null) {
                            isFirstPick = result.detectedFirstPick
                        }

                        // 1. Asignación directa y de alta precisión por rol (respetando selecciones manuales)
                        defaultRoles.forEachIndexed { idx, role ->
                            if (manualLockedAllySlots[idx] != true) {
                                val scannedAlly = result.alliesByRole[role]
                                if (scannedAlly != null) {
                                    assignAllySlot(idx, scannedAlly)
                                }
                            }
                            if (manualLockedEnemySlots[idx] != true) {
                                val scannedEnemy = result.enemiesByRole[role]
                                if (scannedEnemy != null) {
                                    assignEnemySlot(idx, scannedEnemy, result.enemyConfidencesByRole[role])
                                }
                            }
                        }

                        // Verificación complementaria: si el rival ya tiene picks y aliados no, rival eligió 1º
                        val currentAllyPicks = allies.count { it != null }
                        val currentEnemyPicks = enemies.count { it != null }
                        if (currentEnemyPicks > 0 && currentAllyPicks == 0) {
                            isFirstPick = false
                        } else if (currentAllyPicks > 0 && currentEnemyPicks == 0) {
                            isFirstPick = true
                        }

                        // Sincronizar nombres de invocador aliados y hechizos
                        if (result.isLegendaryRanked) {
                            state.allySummonerNames.clear()
                        } else {
                            defaultRoles.forEachIndexed { idx, role ->
                                val sName = result.allySummonerNamesByRole[role] ?: result.allySummonerNamesBySlot[idx]
                                if (!sName.isNullOrBlank()) {
                                    val current = state.allySummonerNames[idx]
                                    if (current.isNullOrBlank() || sName.length > current.length || (sName.contains(" ") && !current.contains(" "))) {
                                        state.allySummonerNames[idx] = sName
                                    }
                                }
                                val spells = result.allySpellsByRole[role] ?: result.allySpellsBySlot[idx]
                                if (!spells.isNullOrEmpty()) {
                                    state.allySpells[idx] = spells
                                }
                            }
                        }
                        state.enemySpells.clear()

                        val totalAlliesPicked = allies.filterNotNull().size
                        val totalEnemiesPicked = enemies.filterNotNull().size

                        // Fase de confirmación de picks finalizada mediante escaneo local y OCR
                        if (result.detectedRole != null) {
                            activeRole = result.detectedRole
                            com.example.util.UserPreferences.setActiveDraftRole(context, result.detectedRole)
                        }
                        val totalDetected = allies.filterNotNull().size + enemies.filterNotNull().size
                        scanNoticeMessage = if (result.isLastPickImageRecognized && result.lastPickChampion != null) {
                            "10/10 Detectado por Imagen: ${result.lastPickChampion.name}"
                        } else if (allies.filterNotNull().size == 5 && enemies.filterNotNull().size == 5) {
                            "10/10 Campeones confirmados (Fase de Preparación)"
                        } else {
                            "Escaneo exitoso ($totalDetected picks" +
                                (if (result.detectedRole != null) ", tu rol: ${result.detectedRole.shortName})" else ")")
                        }
                    } else {
                        scanNoticeMessage = result.statusMessage
                    }
                    isScanning = false
                }
            } else {
                withContext(Dispatchers.Main) {
                    scanNoticeMessage = "No hay frame de captura disponible"
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
                            Image(
                                painter = painterResource(id = com.example.R.drawable.ic_overlay_logo),
                                contentDescription = "Wild Rift Drafting Coach",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(if (isCompactBubble) 32.dp else 42.dp)
                                    .clip(CircleShape)
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

                val targetCardHeight = if (isLandscapeMode) 345.dp else 520.dp
                Card(
                    modifier = Modifier
                        .widthIn(min = if (isLandscapeMode) 520.dp else 300.dp, max = if (isLandscapeMode) 560.dp else 340.dp)
                        .height(targetCardHeight)
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
                                            onDragDelta(0, 0, false, false)
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
                                Image(
                                    painter = painterResource(id = com.example.R.drawable.ic_overlay_logo),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, HextechGold, CircleShape)
                                )
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

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                // Toggle Clasificatoria Legendaria / Estándar
                                Surface(
                                    modifier = Modifier
                                        .clickable { isLegendaryQueue = !isLegendaryQueue },
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isLegendaryQueue) Color(0xFF581C87).copy(alpha = 0.5f) else Color(0xFF1E293B),
                                    border = BorderStroke(1.dp, if (isLegendaryQueue) Color(0xFFC084FC) else HextechGold.copy(alpha = 0.4f))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = if (isLegendaryQueue) tr("Legendaria") else tr("Clasificatoria"),
                                            color = if (isLegendaryQueue) Color(0xFFE9D5FF) else HextechGold,
                                            fontSize = 8.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                if (isAdmin) {
                                    // Botón de Depurado / Calibrador (EXCLUSIVO ADMINISTRADORES)
                                    Surface(
                                        modifier = Modifier
                                            .height(28.dp)
                                            .clickable {
                                                showCalibrationPanel = !showCalibrationPanel
                                                DraftVisionScanner.showCalibrationBoxes.value = showCalibrationPanel
                                            },
                                        shape = RoundedCornerShape(6.dp),
                                        color = if (showCalibrationPanel) HextechCyan.copy(alpha = 0.35f) else Color(0xFF1E293B),
                                        border = BorderStroke(1.dp, if (showCalibrationPanel) HextechCyan else HextechGold)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.BugReport,
                                                contentDescription = "Depuración y Calibrador",
                                                tint = if (showCalibrationPanel) HextechCyan else HextechGold,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Text(
                                                text = "Depurar",
                                                color = if (showCalibrationPanel) HextechCyan else HextechGold,
                                                fontSize = 8.5.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                                // Botón Minimizar (a Burbuja flotante)
                                Surface(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clickable {
                                            isExpanded = false
                                            onExpandedChange(false)
                                        },
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color(0xFF1E293B),
                                    border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.UnfoldLess,
                                            contentDescription = "Minimizar a Burbuja",
                                            tint = HextechGold,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
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
                                        .weight(0.85f)
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
                                            fontSize = 9.5.sp,
                                            fontWeight = if (isHistoryActive) FontWeight.Bold else FontWeight.Medium
                                        )
                                    }
                                }
                            }

                            // Pestaña 5: Depurar / Calibrador (Exclusivo Administrador en el Overlay Hub)
                            if (isAdmin) {
                                val isDebugActive = showCalibrationPanel
                                Box(
                                    modifier = Modifier
                                        .weight(0.95f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isDebugActive) HextechCyan.copy(alpha = 0.25f) else HextechSurface)
                                        .border(
                                            1.dp,
                                            if (isDebugActive) HextechCyan else HextechCardBorder.copy(alpha = 0.5f),
                                            RoundedCornerShape(6.dp)
                                        )
                                        .clickable {
                                            showCalibrationPanel = !showCalibrationPanel
                                            DraftVisionScanner.showCalibrationBoxes.value = showCalibrationPanel
                                        }
                                        .padding(vertical = 5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.BugReport,
                                            contentDescription = "Depuración y Calibrador",
                                            tint = if (isDebugActive) HextechCyan else HextechGold,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Text(
                                            text = "Depurar",
                                            color = if (isDebugActive) HextechCyan else HextechGold,
                                            fontSize = 9.5.sp,
                                            fontWeight = if (isDebugActive) FontWeight.Bold else FontWeight.Medium
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
                                    text = "Pestaña: ${when (overlayHubTab) {
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
                                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(12.dp))
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
                            if (showCalibrationPanel) {
                                DraftCalibrationPanel(
                                    onDismiss = {
                                        showCalibrationPanel = false
                                        DraftVisionScanner.showCalibrationBoxes.value = false
                                    },
                                    onDragDelta = onDragDelta
                                )
                            } else if (showTenthPickLogsDialog) {
                                TenthPickScannerViewerDialog(
                                    screenCaptureManager = screenCaptureManager,
                                    allies = allies.toList(),
                                    enemies = enemies.toList(),
                                    onDismiss = { showTenthPickLogsDialog = false },
                                    onDragDelta = onDragDelta
                                )
                            } else if (selectedChampionDetail != null) {
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
                                                state.isRoleManuallySelected = true
                                                com.example.util.UserPreferences.setActiveDraftRole(context, it)
                                            },
                                            isFirstPick = isFirstPick,
                                            onFirstPickToggle = { 
                                                if (!state.isRoleManuallySelected) {
                                                    android.widget.Toast.makeText(context, "Selecciona tu línea primero", android.widget.Toast.LENGTH_SHORT).show()
                                                } else {
                                                    isFirstPick = !isFirstPick 
                                                }
                                            },
                                            isLegendaryQueue = isLegendaryQueue,
                                            onToggleLegendaryQueue = { isLegendaryQueue = !isLegendaryQueue },
                                            isLoadingScreenMode = isLoadingScreenMode,
                                            onLoadingScreenModeToggle = { isLoadingScreenMode = !isLoadingScreenMode },
                                            allies = allies,
                                            enemies = enemies,
                                            enemyConfidences = state.enemyConfidences,
                                            allySummonerNames = state.allySummonerNames,
                                            enemySummonerNames = state.enemySummonerNames,
                                            allySpells = state.allySpells,
                                            enemySpells = state.enemySpells,
                                            analysis = analysis,
                                            selectedChampionDetail = selectedChampionDetail,
                                            onSelectChampion = { haptic.performHapticFeedback(HapticFeedbackType.LongPress); selectedChampionDetail = it },
                                            onOpenChampionPicker = { isAlly, idx -> 
                                                autoScanEnabled = false
                                                showChampionPickerForSlot = Pair(isAlly, idx) 
                                            },
                                            onSaveDraftClick = { 
                                                if (isPremium) {
                                                    if (!state.isRoleManuallySelected) {
                                                        android.widget.Toast.makeText(context, "Selecciona tu línea primero", android.widget.Toast.LENGTH_SHORT).show()
                                                    } else if (allies.count { it != null } < 5 || enemies.count { it != null } < 5) {
                                                        android.widget.Toast.makeText(context, "Debes seleccionar los 10 campeones", android.widget.Toast.LENGTH_SHORT).show()
                                                    } else {
                                                        showSaveDraftDialog = true 
                                                    }
                                                } else {
                                                    android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()
                                                }
                                            },
                                            isSavedRecently = isSavedRecently,
                                            onClearAll = { 
                                                for (i in 0 until 5) {
                                                    allies[i] = null
                                                    enemies[i] = null
                                                }
                                                manualLockedAllySlots.clear()
                                                manualLockedEnemySlots.clear()
                                                state.enemyConfidences.clear()
                                                state.allySummonerNames.clear()
                                                state.enemySummonerNames.clear()
                                                state.allySpells.clear()
                                                state.enemySpells.clear()
                                                state.isRoleManuallySelected = false
                                                state.hasScanned10thPick = false
                                                DraftVisionScanner.resetSlotMemory()
                                                android.widget.Toast.makeText(context, "Equipos vaciados", android.widget.Toast.LENGTH_SHORT).show()
                                            },
                                            onGoToTierList = { overlayHubTab = OverlayHubTab.TIER_LIST },
                                            onManualEdit = { autoScanEnabled = false },
                                            onOpenTenthPickLogs = { showTenthPickLogsDialog = true }
                                        )
                                    }
                                    OverlayHubTab.TIER_LIST -> {
                                        com.example.ui.screens.TierListTab(
                                            isOverlay = true,
                                            onSelectChampion = { haptic.performHapticFeedback(HapticFeedbackType.LongPress); selectedChampionDetail = it },
                                            isPremium = isPremium
                                        )
                                    }
                                    OverlayHubTab.CHAMPIONS -> {
                                        com.example.ui.screens.ChampionsCatalogTab(
                                            isOverlay = true,
                                            onSelectChampion = { haptic.performHapticFeedback(HapticFeedbackType.LongPress); selectedChampionDetail = it }
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
                                            DraftVisionScanner.resetSlotMemory()
                                            if (screenCaptureManager?.isReady() != true) {
                                                scanNoticeMessage = "Requiere permiso de pantalla. Toca aquí para activarlo."
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
                            Image(
                                painter = painterResource(id = role.iconResId),
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
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
            isLegendary = isLegendaryQueue,
            allies = allies.mapIndexedNotNull { index, champ -> 
                champ?.let { 
                    val role = when (index) { 
                        0 -> LaneRole.TOP 
                        1 -> LaneRole.JUNGLE 
                        2 -> LaneRole.MID 
                        3 -> LaneRole.ADC 
                        else -> LaneRole.SUPPORT 
                    } 
                    DraftSlot(it, role) 
                } 
            },
            enemies = enemies.mapIndexedNotNull { index, champ -> 
                champ?.let { 
                    val role = when (index) { 
                        0 -> LaneRole.TOP 
                        1 -> LaneRole.JUNGLE 
                        2 -> LaneRole.MID 
                        3 -> LaneRole.ADC 
                        else -> LaneRole.SUPPORT 
                    } 
                    DraftSlot(it, role) 
                } 
            },
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
                            text = (if (isAllySlot) tr("Elegir Aliado") else tr("Elegir Rival")) + (if (targetRole != null) " - ${com.example.util.tr(targetRole.displayName)}" else ""),
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
                                    .padding(vertical = 3.dp, horizontal = 2.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    if (lane != null) {
                                        Image(
                                            painter = painterResource(id = lane.iconResId),
                                            contentDescription = null,
                                            modifier = Modifier.size(11.dp)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                    }
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
                                            manualLockedAllySlots[slotIndex] = true
                                            for (i in 0 until 5) {
                                                if (allies[i]?.id == champ.id) allies[i] = null
                                                if (enemies[i]?.id == champ.id) enemies[i] = null
                                            }
                                            if (slotIndex in 0 until 5) {
                                                allies[slotIndex] = champ
                                            }
                                        } else {
                                            manualLockedEnemySlots[slotIndex] = true
                                            for (i in 0 until 5) {
                                                if (allies[i]?.id == champ.id) allies[i] = null
                                                if (enemies[i]?.id == champ.id) enemies[i] = null
                                            }
                                            if (slotIndex in 0 until 5) {
                                                enemies[slotIndex] = champ
                                            }
                                        }
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
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
    isLegendary: Boolean = false,
    allies: List<DraftSlot>,
    enemies: List<DraftSlot>,
    analysis: com.example.model.DraftAnalysisResult,
    onDismiss: () -> Unit,
    onSaved: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var selectedResult by remember { mutableStateOf("PENDING") }
    var isLegendaryMatch by remember(isLegendary) { mutableStateOf(isLegendary) }
    var notesText by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }
    var showDuplicateConfirmation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        com.example.data.AccountProfileManager.init(context)
    }

    val profiles by com.example.data.AccountProfileManager.allProfiles.collectAsState()
    val activeProfileId by com.example.data.AccountProfileManager.activeProfileId.collectAsState()
    var selectedProfileId by remember(activeProfileId) { mutableStateOf(activeProfileId) }

    val myChampion = allies.find { it.assignedRole == activeRole }?.champion ?: allies.firstOrNull()?.champion
    val enemyOpponent = enemies.find { it.assignedRole == activeRole }?.champion ?: enemies.firstOrNull()?.champion
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
                        text = tr("Guardar en Historial"),
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
                        text = tr("Perfil / Cuenta:"),
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
                                    maxLines = 1, softWrap = false,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

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
                            text = tr("En espera"),
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
                            text = tr("Victoria"),
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
                            text = tr("Derrota"),
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
                                val chosenProfile = profiles.find { it.id == selectedProfileId }
                                    ?: com.example.data.AccountProfileManager.getActiveProfile(context)
                                
                                val exists = DraftHistoryRepository.checkDraftExists(
                                    context = context,
                                    myRole = activeRole,
                                    allies = allies,
                                    enemies = enemies,
                                    accountProfileId = chosenProfile.id
                                )

                                if (exists) {
                                    showDuplicateConfirmation = true
                                    isSaving = false
                                } else {
                                    DraftHistoryRepository.saveDraft(
                                        context = context,
                                        myRole = activeRole,
                                        isFirstPick = isFirstPick,
                                        isLegendary = isLegendaryMatch,
                                        allies = allies,
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
        
        if (showDuplicateConfirmation) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showDuplicateConfirmation = false },
                title = { Text(tr("Draft Duplicado"), fontWeight = FontWeight.Bold, color = TextPrimary) },
                text = { Text(tr("Es el mismo draft que el anterior, ¿deseas guardarlo de todas formas?"), color = TextSecondary) },
                containerColor = HextechSurface,
                confirmButton = {
                    Button(
                        onClick = {
                            showDuplicateConfirmation = false
                            isSaving = true
                            coroutineScope.launch {
                                val chosenProfile = profiles.find { it.id == selectedProfileId }
                                    ?: com.example.data.AccountProfileManager.getActiveProfile(context)
                                DraftHistoryRepository.saveDraft(
                                    context = context,
                                    myRole = activeRole,
                                    isFirstPick = isFirstPick,
                                    isLegendary = isLegendaryMatch,
                                    allies = allies,
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
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                    ) {
                        Text(tr("Sí"), color = Color.Black)
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showDuplicateConfirmation = false },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMuted)
                    ) {
                        Text(tr("No"))
                    }
                }
            )
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
    isLegendaryQueue: Boolean = false,
    onToggleLegendaryQueue: (() -> Unit)? = null,
    isLoadingScreenMode: Boolean,
    onLoadingScreenModeToggle: () -> Unit,
    allies: androidx.compose.runtime.snapshots.SnapshotStateList<Champion?>,
    enemies: androidx.compose.runtime.snapshots.SnapshotStateList<Champion?>,
    enemyConfidences: androidx.compose.runtime.snapshots.SnapshotStateMap<LaneRole, Int>,
    allySummonerNames: androidx.compose.runtime.snapshots.SnapshotStateMap<Int, String> = remember { androidx.compose.runtime.mutableStateMapOf() },
    enemySummonerNames: androidx.compose.runtime.snapshots.SnapshotStateMap<Int, String> = remember { androidx.compose.runtime.mutableStateMapOf() },
    allySpells: androidx.compose.runtime.snapshots.SnapshotStateMap<Int, List<String>> = remember { androidx.compose.runtime.mutableStateMapOf() },
    enemySpells: androidx.compose.runtime.snapshots.SnapshotStateMap<Int, List<String>> = remember { androidx.compose.runtime.mutableStateMapOf() },
    analysis: com.example.model.DraftAnalysisResult,
    selectedChampionDetail: Champion?,
    onSelectChampion: (Champion?) -> Unit,
    onOpenChampionPicker: (isAlly: Boolean, index: Int) -> Unit,
    onSaveDraftClick: () -> Unit,
    isSavedRecently: Boolean,
    onClearAll: () -> Unit,
    onGoToTierList: () -> Unit,
    onManualEdit: () -> Unit,
    onOpenTenthPickLogs: (() -> Unit)? = null
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

    val allySlots = remember(allies.toList(), allySummonerNames.toMap(), allySpells.toMap()) {
        defaultRoles.mapIndexedNotNull { index, role ->
            allies.getOrNull(index)?.let {
                DraftSlot(
                    champion = it,
                    assignedRole = role,
                    summonerName = allySummonerNames[index],
                    spells = allySpells[index] ?: emptyList()
                )
            }
        }
    }
    val enemySlots = remember(enemies.toList(), enemyConfidences.toMap(), enemySpells.toMap(), enemySummonerNames.toMap()) {
        defaultRoles.mapIndexedNotNull { index, role ->
            enemies.getOrNull(index)?.let {
                val conf = enemyConfidences[role] ?: 85
                DraftSlot(
                    champion = it,
                    assignedRole = role,
                    confidence = conf,
                    summonerName = enemySummonerNames[index],
                    spells = enemySpells[index] ?: emptyList()
                )
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
            allySummonerNames = allySummonerNames.toMap(),
            activeUserRole = activeRole,
            isFirstPick = isFirstPick,
            onToggleFirstPick = onFirstPickToggle,
            isLegendary = isLegendaryQueue,
            onToggleLegendary = onToggleLegendaryQueue,
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
            },
            onOpenTenthPickLogs = onOpenTenthPickLogs
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
    allySummonerNames: Map<Int, String> = emptyMap(),
    activeUserRole: LaneRole?,
    isFirstPick: Boolean = true,
    onToggleFirstPick: (() -> Unit)? = null,
    isLegendary: Boolean = false,
    onToggleLegendary: (() -> Unit)? = null,
    onPickChampionForRole: (isAlly: Boolean, LaneRole) -> Unit,
    onRemoveChampionForRole: (isAlly: Boolean, LaneRole) -> Unit,
    onOpenTenthPickLogs: (() -> Unit)? = null
) {
    val roles = listOf(
        Pair(LaneRole.TOP, "TOP"),
        Pair(LaneRole.JUNGLE, "JUG"),
        Pair(LaneRole.MID, "MID"),
        Pair(LaneRole.ADC, "DÚO"),
        Pair(LaneRole.SUPPORT, "SUP")
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 6.dp)) {
            // Etiquetas de Primera Selección y Clasificatoria Legendaria
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isFirstPick) {
                    Surface(
                        modifier = Modifier.clickable { onToggleFirstPick?.invoke() },
                        shape = RoundedCornerShape(12.dp),
                        color = AllyBlue.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, AllyBlue.copy(alpha = 0.6f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tr("Primera Selección"),
                                color = AllyBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.5.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                }

                Surface(
                    modifier = Modifier.clickable { onToggleLegendary?.invoke() },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isLegendary) Color(0xFFFF9800).copy(alpha = 0.2f) else HextechDarkBg,
                    border = BorderStroke(
                        1.dp,
                        if (isLegendary) Color(0xFFFF9800) else HextechCardBorder.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isLegendary) tr("Legendaria") else tr("Clasificatoria"),
                            color = if (isLegendary) Color(0xFFFFB74D) else TextSecondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.5.sp
                        )
                    }
                }

                if (onOpenTenthPickLogs != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        modifier = Modifier.clickable { onOpenTenthPickLogs() },
                        shape = RoundedCornerShape(12.dp),
                        color = HextechGold.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tr("Visor Escáner 10"),
                                color = HextechGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.5.sp
                            )
                        }
                    }
                }
            }

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 2.dp, start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(AllyBlue))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("EQUIPO ALIADO"), color = AllyBlue, fontWeight = FontWeight.Black, fontSize = 10.5.sp)
                }

                Surface(
                    color = HextechDarkBg,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(0.5.dp, HextechGold.copy(alpha = 0.4f))
                ) {
                    Text(
                        "VS",
                        color = HextechGold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 9.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(tr("EQUIPO RIVAL"), color = DangerRed, fontWeight = FontWeight.Black, fontSize = 10.5.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(DangerRed))
                }
            }

            roles.forEachIndexed { index, (role, label) ->
                val allySlot = allySlots.find { it.assignedRole == role }
                val enemySlot = enemySlots.find { it.assignedRole == role }
                val isMyRole = activeUserRole == role
                val allyChamp = allySlot?.champion
                val enemyChamp = enemySlot?.champion

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    color = if (isMyRole) HextechCyan.copy(alpha = 0.08f) else HextechDarkBg.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        if (isMyRole) 1.dp else 0.5.dp,
                        if (isMyRole) HextechCyan.copy(alpha = 0.6f) else HextechCardBorder.copy(alpha = 0.4f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // LADO ALIADO (Avatar + 1. Nombre -> 2. Stats (WR/Ban/Pick) -> 3. Tier List)
                        Row(
                            modifier = Modifier
                                .weight(1.3f)
                                .clickable { onPickChampionForRole(true, role) },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DraftAvatarBox(
                                slot = allySlot,
                                placeholderInitial = null,
                                isEnemy = false,
                                isMyRole = isMyRole,
                                onClick = { onPickChampionForRole(true, role) },
                                onRemove = { onRemoveChampionForRole(true, role) }
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            if (allyChamp != null) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    // 1. Nombre del Campeón
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = allyChamp.name,
                                            color = if (isMyRole) HextechCyan else TextPrimary,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            softWrap = false,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        if (isMyRole) {
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text(
                                                text = "(TÚ)",
                                                color = HextechCyan,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 7.5.sp
                                            )
                                        }
                                    }
                                    // 2. Estadísticas (WR, Ban, Pick)
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Text(
                                            text = "W:${allyChamp.winrate.toInt()}%",
                                            color = Color(0xFF00FF7F),
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "B:${allyChamp.banRate.toInt()}%",
                                            color = DangerRed,
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "P:${allyChamp.pickRate.toInt()}%",
                                            color = Color(0xFFFF9800),
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                    }
                                    // 3. Tier List
                                    Text(
                                        text = "Tier ${allyChamp.tier}",
                                        color = HextechGold,
                                        fontSize = 7.5.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            } else {
                                Text(
                                    text = tr("+ Elegir"),
                                    color = AllyBlue.copy(alpha = 0.8f),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1
                                )
                            }
                        }

                        // CENTRO: ÍCONO Y ETIQUETA DEL ROL + VS
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .widthIn(min = 40.dp)
                        ) {
                            Image(
                                painter = painterResource(id = role.iconResId),
                                contentDescription = label,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = label,
                                color = if (isMyRole) HextechCyan else TextSecondary,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "VS",
                                color = HextechGold.copy(alpha = 0.7f),
                                fontSize = 7.sp,
                                fontWeight = FontWeight.Black
                            )
                        }

                        // LADO RIVAL (1. Nombre -> 2. Stats (WR/Ban/Pick) -> 3. Tier List + Avatar)
                        Row(
                            modifier = Modifier
                                .weight(1.3f)
                                .clickable { onPickChampionForRole(false, role) },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            if (enemyChamp != null) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 6.dp),
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    // 1. Nombre del Campeón
                                    Text(
                                        text = enemyChamp.name,
                                        color = DangerRed,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.End
                                    )
                                    // 2. Estadísticas (WR, Ban, Pick)
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Text(
                                            text = "W:${enemyChamp.winrate.toInt()}%",
                                            color = Color(0xFF00FF7F),
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "B:${enemyChamp.banRate.toInt()}%",
                                            color = DangerRed,
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "P:${enemyChamp.pickRate.toInt()}%",
                                            color = Color(0xFFFF9800),
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                    }
                                    // 3. Tier List
                                    Text(
                                        text = "Tier ${enemyChamp.tier}",
                                        color = HextechGold,
                                        fontSize = 7.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End
                                    )
                                }
                            } else {
                                Text(
                                    text = tr("+ Rival"),
                                    color = DangerRed.copy(alpha = 0.8f),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1,
                                    softWrap = false,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.padding(end = 6.dp)
                                )
                            }

                            DraftAvatarBox(
                                slot = enemySlot,
                                placeholderInitial = null,
                                isEnemy = true,
                                isMyRole = false,
                                onClick = { onPickChampionForRole(false, role) },
                                onRemove = { onRemoveChampionForRole(false, role) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DraftAvatarBox(
    slot: DraftSlot?,
    placeholderInitial: String? = null,
    isEnemy: Boolean,
    isMyRole: Boolean,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    val champ = slot?.champion
    val borderColor = if (isMyRole) HextechCyan else if (champ != null) (if (isEnemy) DangerRed else HextechGold) else HextechCardBorder.copy(alpha = 0.6f)

    Box(
        modifier = Modifier
            .size(34.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when {
                    isMyRole -> HextechCyan.copy(alpha = 0.2f)
                    champ != null -> if (isEnemy) DangerRed.copy(alpha = 0.15f) else HextechGold.copy(alpha = 0.15f)
                    else -> Color(0xFF070D15)
                }
            )
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
                        .padding(1.5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(HextechCyan)
                        .padding(horizontal = 2.5.dp, vertical = 0.5.dp)
                ) {
                    Text(
                        text = "TÚ",
                        color = Color.Black,
                        fontSize = 7.5.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(15.dp)
                    .background(Color.Black.copy(alpha = 0.75f), RoundedCornerShape(bottomStart = 6.dp))
                    .clickable { onRemove() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Close, contentDescription = "Quitar", tint = Color.White, modifier = Modifier.size(11.dp))
            }
        } else if (!placeholderInitial.isNullOrBlank()) {
            Box(
                modifier = Modifier.fillMaxSize().background(HextechSurface.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = placeholderInitial,
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black
                )
            }
        } else {
            Icon(
                Icons.Default.Add,
                contentDescription = "Añadir",
                tint = if (isEnemy) DangerRed.copy(alpha = 0.5f) else AllyBlue.copy(alpha = 0.5f),
                modifier = Modifier.size(18.dp)
            )
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = role.iconResId),
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = tr(role.shortName),
                            fontSize = 9.sp,
                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal,
                            color = if (isSelected) HextechDarkBg else TextPrimary
                        )
                    }
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
                text = tr("RECOMENDACIÓN:") + " ${tr(activeRole.displayName)}",
                color = HextechGold,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (isFirstPick) tr("1ª Elección") else tr("Counter Pick"),
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

        if (allyWombos.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                allyWombos.forEach { wombo ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.6f)),
                        border = BorderStroke(0.5.dp, AllyBlue)
                    ) {
                        Text(text = "${wombo.title}: ${wombo.description}", color = AllyBlue, fontSize = 8.5.sp, modifier = Modifier.padding(3.dp))
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
                        text = "Daño Aliado: AD ${analysis.allyPhysicalDamagePercent}% | AP ${analysis.allyMagicDamagePercent}%",
                        color = AllyBlue,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Daño Enemigo: AD ${analysis.physicalDamagePercent}% | AP ${analysis.magicDamagePercent}%",
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
                            text = tr("Matchup 1v1 vs") + " ${explicitEnemyOpponent.name}",
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
                        text = if (isSavedRecently) tr("Guardado") else tr("Guardar"),
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
                    text = tr("Vaciar"),
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
                    text = tr("Tier List"),
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
                            Text(pick.tacticalReason, color = TextMuted, fontSize = 8.sp, maxLines = 1, softWrap = false, overflow = TextOverflow.Ellipsis)
                        }
                    }
                    
                }
            }
        }
    }
}

@Composable
private fun TenthPickScannerViewerDialog(
    screenCaptureManager: ScreenCaptureManager? = null,
    allies: List<Champion?> = emptyList(),
    enemies: List<Champion?> = emptyList(),
    onDismiss: () -> Unit,
    onDragDelta: ((dx: Int, dy: Int, isDragging: Boolean, isEnded: Boolean) -> Unit)? = null
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var calib by remember { mutableStateOf(com.example.service.screen.VisionCalibrationConfig.loadFromPrefs(context)) }
    // Solo dos visiones exclusivas: 0 = Visión Izquierda (10º Aliado), 1 = Visión Derecha (10º Rival)
    var selectedVision by remember { mutableStateOf(1) }
    var isLiveStreaming by remember { mutableStateOf(true) }
    var isFastStep by remember { mutableStateOf(false) }
    var currentCrop by remember { mutableStateOf<Bitmap?>(null) }
    var currentLog by remember { mutableStateOf<com.example.service.screen.LocalVisionAnalyzer.TenthPickDecisionLog?>(null) }
    var selectedEngine by remember { mutableStateOf(com.example.service.screen.VisionInferenceEngineType.ZNCC_LOCAL_NATIVE) }
    var currentBenchmark by remember { mutableStateOf<com.example.service.screen.EngineInferenceBenchmark?>(null) }
    var isAutoEvaluating by remember { mutableStateOf(false) }

    val step = if (isFastStep) 0.010f else 0.002f

    // Proyectar círculos de calibración directamente sobre la pantalla exclusivamente para el 10º pick
    DisposableEffect(selectedVision) {
        com.example.service.screen.DraftVisionScanner.showCalibrationBoxes.value = true
        com.example.service.screen.DraftVisionScanner.showTenthPickOnly.value = true
        com.example.service.screen.DraftVisionScanner.activeTenthPickSide.value = selectedVision
        onDispose {
            com.example.service.screen.DraftVisionScanner.showCalibrationBoxes.value = false
            com.example.service.screen.DraftVisionScanner.showTenthPickOnly.value = false
            com.example.service.screen.DraftVisionScanner.activeTenthPickSide.value = null
        }
    }

    val performSingleEvaluation: suspend () -> Unit = {
        withContext(Dispatchers.Default) {
            try {
                if (screenCaptureManager?.isReady() == true) {
                    val bmp = screenCaptureManager.captureCurrentFrame()
                    if (bmp != null && !bmp.isRecycled) {
                        try {
                            if (com.example.data.WildRiftRepository.champions.isEmpty()) {
                                com.example.data.WildRiftRepository.initChampions(context)
                            }
                            val allChamps = com.example.data.WildRiftRepository.champions.toList()
                            // Excluir de la evaluación visual del 10º pick a todos los campeones ya fijados en los picks 1 al 9
                            val draftConfirmedIds = (allies.filterNotNull().map { it.id } +
                                                     enemies.filterNotNull().map { it.id } +
                                                     com.example.service.screen.DraftVisionScanner.allySlotConfirmedChampions.mapNotNull { it?.id } +
                                                     com.example.service.screen.DraftVisionScanner.enemySlotConfirmedChampions.mapNotNull { it?.id }).toSet()
                            val dec = com.example.service.screen.LocalVisionAnalyzer.inspectSlotDetailed(
                                bitmap = bmp,
                                isAlly = (selectedVision == 0),
                                slotIndex = 4, // 10º Pick (5º avatar)
                                calib = calib,
                                allChamps = allChamps,
                                excludedChampionIds = draftConfirmedIds,
                                context = context
                            )
                            val crop = com.example.service.screen.LocalVisionAnalyzer.lastTenthPickCrop?.let {
                                if (!it.isRecycled) {
                                    try { it.copy(Bitmap.Config.ARGB_8888, false) } catch (_: Throwable) { null }
                                } else null
                            }
                            val bench = if (crop != null && !crop.isRecycled) {
                                com.example.service.screen.VisionInferenceManager.runEngineInference(
                                    cropBitmap = crop,
                                    engine = selectedEngine,
                                    allChamps = allChamps,
                                    expectedRole = null,
                                    context = context
                                )
                            } else null

                            withContext(Dispatchers.Main) {
                                if (crop != null && !crop.isRecycled) {
                                    currentCrop = crop
                                }
                                currentLog = dec
                                currentBenchmark = bench
                            }
                        } finally {
                            try {
                                if (!bmp.isRecycled) bmp.recycle()
                            } catch (_: Throwable) {}
                        }
                    }
                }
            } catch (_: Throwable) {}
        }
    }

    // Bucle en Vivo (Live Stream): captura y actualiza en tiempo real de forma 100% automática
    LaunchedEffect(isLiveStreaming, selectedVision, calib, selectedEngine) {
        while (isLiveStreaming) {
            try {
                isAutoEvaluating = true
                performSingleEvaluation()
            } catch (_: Throwable) {
            } finally {
                isAutoEvaluating = false
            }
            delay(500)
        }
    }

    val adjustCoordinates: (Float, Float, Float) -> Unit = { dx, dy, dDiam ->
        val newY = (calib.topAvatarYRatio + dy).coerceIn(0.01f, 0.25f)
        val newDiam = (calib.topAvatarDiameterRatio + dDiam).coerceIn(0.02f, 0.15f)
        val updated = if (selectedVision == 0) {
            val currentX = calib.topAlly5XRatio
            val newX = (currentX + dx).coerceIn(0.01f, 0.40f)
            val updatedAllies = calib.topAllyXRatios.toMutableList()
            if (4 in updatedAllies.indices) updatedAllies[4] = newX
            calib.copy(
                topAvatarYRatio = newY,
                topAvatarDiameterRatio = newDiam,
                topAlly5XRatio = newX,
                topAllyXRatios = updatedAllies
            )
        } else {
            val currentX = calib.topEnemy5XRatio
            val newX = (currentX + dx).coerceIn(0.60f, 0.99f)
            val updatedEnemies = calib.topEnemyXRatios.toMutableList()
            if (4 in updatedEnemies.indices) updatedEnemies[4] = newX
            calib.copy(
                topAvatarYRatio = newY,
                topAvatarDiameterRatio = newDiam,
                topEnemy5XRatio = newX,
                topEnemyXRatios = updatedEnemies
            )
        }
        calib = updated
        updated.saveToPrefs(context)
        com.example.service.screen.DraftVisionScanner.updateCalibration(context, updated)
        coroutineScope.launch(Dispatchers.IO) { performSingleEvaluation() }
    }

    // Función para copiar coordenadas al portapapeles
    val copyCoordinatesToClipboard = {
        val curX = if (selectedVision == 0) calib.topAlly5XRatio else calib.topEnemy5XRatio
        val visionName = if (selectedVision == 0) "Izquierda (10º Aliado)" else "Derecha (10º Rival)"
        val text = """
            [Coach 10º Pick - Calibración]
            • Visión: $visionName
            • Posición X: ${"%.1f".format(java.util.Locale.US, curX * 100)}% (${"%.3f".format(java.util.Locale.US, curX)}f)
            • Posición Y: ${"%.1f".format(java.util.Locale.US, calib.topAvatarYRatio * 100)}% (${"%.3f".format(java.util.Locale.US, calib.topAvatarYRatio)}f)
            • Diámetro (⌀): ${"%.1f".format(java.util.Locale.US, calib.topAvatarDiameterRatio * 100)}% (${"%.3f".format(java.util.Locale.US, calib.topAvatarDiameterRatio)}f)
        """.trimIndent()
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText("Coordenadas 10º Pick", text)
        clipboard?.setPrimaryClip(clip)
        android.widget.Toast.makeText(context, "Coordenadas copiadas al portapapeles", android.widget.Toast.LENGTH_SHORT).show()
    }

    // Función para guardar coordenadas
    val saveCoordinates = {
        calib.saveToPrefs(context)
        com.example.service.screen.DraftVisionScanner.updateCalibration(context, calib)
        android.widget.Toast.makeText(context, "Coordenadas guardadas correctamente", android.widget.Toast.LENGTH_SHORT).show()
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
        border = BorderStroke(1.5.dp, HextechGold)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Barra de agarre (Drag Handle) superior para reposicionar el Visor
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                onDragDelta?.invoke(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                            },
                            onDragEnd = { onDragDelta?.invoke(0, 0, false, false) },
                            onDragCancel = { onDragDelta?.invoke(0, 0, false, false) }
                        )
                    }
                    .padding(top = 1.dp, bottom = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(HextechGold.copy(alpha = 0.65f))
                )
            }

            // Cabecera del Visor con soporte de arrastre
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                onDragDelta?.invoke(dragAmount.x.roundToInt(), dragAmount.y.roundToInt(), true, false)
                            },
                            onDragEnd = { onDragDelta?.invoke(0, 0, false, false) },
                            onDragCancel = { onDragDelta?.invoke(0, 0, false, false) }
                        )
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Videocam,
                        contentDescription = null,
                        tint = if (isLiveStreaming) Color(0xFF00FF7F) else HextechGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "VISOR 10º PICK (EN PANTALLA)",
                        color = HextechGold,
                        fontWeight = FontWeight.Black,
                        fontSize = 11.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Botón Copiar Coordenadas
                    IconButton(
                        onClick = copyCoordinatesToClipboard,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copiar Coordenadas",
                            tint = HextechCyan,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(2.dp))

                    // Botón Guardar Coordenadas
                    IconButton(
                        onClick = saveCoordinates,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Guardar Coordenadas",
                            tint = Color(0xFF00FF7F),
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(2.dp))

                    // Limpiar captura
                    IconButton(
                        onClick = {
                            com.example.service.screen.LocalVisionAnalyzer.resetTenthPickData()
                            currentCrop = null
                            currentLog = null
                            coroutineScope.launch(Dispatchers.IO) { performSingleEvaluation() }
                            android.widget.Toast.makeText(context, "Capturas anteriores limpiadas", android.widget.Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Limpiar Captura",
                            tint = DangerRed,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = HextechGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // CONTENEDOR SCROLLABLE QUE ABARCA TODO EL CONTENIDO DEL VISOR
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // NOTA INFORMATIVA DE LA REGLA OFICIAL
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    color = HextechCyan.copy(alpha = 0.10f),
                    border = BorderStroke(0.8.dp, HextechCyan.copy(alpha = 0.45f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Los círculos de escaneo se proyectan directamente sobre los 10 campeones de tu pantalla.",
                            color = HextechCyan,
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // SELECTOR EXCLUSIVO DE LAS 2 VISIONES (IZQUIERDA O DERECHA)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Visión Izquierda (10º Aliado)
                    val isLeftSelected = selectedVision == 0
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedVision = 0 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (isLeftSelected) AllyBlue.copy(alpha = 0.30f) else HextechSurface,
                        border = BorderStroke(1.5.dp, if (isLeftSelected) AllyBlue else HextechCardBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "VISIÓN IZQUIERDA",
                                color = if (isLeftSelected) AllyBlue else TextPrimary,
                                fontWeight = FontWeight.Black,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "10º Aliado (Sin 1ª Selección)",
                                color = if (isLeftSelected) HextechGold else TextMuted,
                                fontSize = 7.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Visión Derecha (10º Rival)
                    val isRightSelected = selectedVision == 1
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedVision = 1 },
                        shape = RoundedCornerShape(8.dp),
                        color = if (isRightSelected) DangerRed.copy(alpha = 0.30f) else HextechSurface,
                        border = BorderStroke(1.5.dp, if (isRightSelected) DangerRed else HextechCardBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "VISIÓN DERECHA",
                                color = if (isRightSelected) DangerRed else TextPrimary,
                                fontWeight = FontWeight.Black,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "10º Rival (Con 1ª Selección)",
                                color = if (isRightSelected) HextechGold else TextMuted,
                                fontSize = 7.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // BANNER DE COORDENADAS ACTIVAS DIRECTAS EN PANTALLA
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = HextechSurface,
                    border = BorderStroke(1.2.dp, if (selectedVision == 0) AllyBlue else DangerRed)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(if (selectedVision == 0) Color(0xFF00E5FF) else Color(0xFFFF5252))
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (selectedVision == 0) "MIRA 10º ALIADO (PANTALLA)" else "MIRA 10º RIVAL (PANTALLA)",
                                    color = if (selectedVision == 0) Color(0xFF00E5FF) else Color(0xFFFF5252),
                                    fontWeight = FontWeight.Black,
                                    fontSize = 9.5.sp
                                )
                            }
                            val curX = if (selectedVision == 0) calib.topAlly5XRatio else calib.topEnemy5XRatio
                            Text(
                                text = "X: ${"%.1f".format(java.util.Locale.US, curX * 100)}% | Y: ${"%.1f".format(java.util.Locale.US, calib.topAvatarYRatio * 100)}% | ⌀: ${"%.1f".format(java.util.Locale.US, calib.topAvatarDiameterRatio * 100)}%",
                                color = HextechGold,
                                fontSize = 8.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Los 10 círculos están visibles en la barra superior en tiempo real.",
                            color = TextMuted,
                            fontSize = 8.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // BOTONES PRINCIPALES: ESTADO EN VIVO (100% AUTOMÁTICO) + GUARDAR + COPIAR
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Indicador de Escaneo Automático Continuo
                    Surface(
                        modifier = Modifier
                            .weight(1.2f)
                            .height(34.dp),
                        shape = RoundedCornerShape(6.dp),
                        color = if (isLiveStreaming) Color(0xFF00FF7F).copy(alpha = 0.15f) else HextechSurface,
                        border = BorderStroke(1.dp, if (isLiveStreaming) Color(0xFF00FF7F) else HextechCardBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isAutoEvaluating) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(10.dp),
                                    color = Color(0xFF00FF7F),
                                    strokeWidth = 1.5.dp
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(if (isLiveStreaming) Color(0xFF00FF7F) else TextMuted)
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = if (isLiveStreaming) "ESCANEANDO SOLO" else "PAUSADO",
                                color = if (isLiveStreaming) Color(0xFF00FF7F) else TextMuted,
                                fontSize = 8.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Botón GUARDAR
                    Button(
                        onClick = saveCoordinates,
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.25f)),
                        border = BorderStroke(1.dp, HextechCyan),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 4.dp)
                    ) {
                        Icon(Icons.Default.BookmarkAdd, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("GUARDAR", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 8.5.sp)
                    }

                    // Botón COPIAR
                    Button(
                        onClick = copyCoordinatesToClipboard,
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.7f)),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 4.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("COPIAR", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 8.5.sp)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // CONTROLES DE MIRA ERGONÓMICOS (D-PAD)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "CALIBRAR POSICIÓN (D-PAD)",
                                color = HextechGold,
                                fontWeight = FontWeight.Black,
                                fontSize = 9.sp
                            )
                            // Toggle de paso
                            Surface(
                                modifier = Modifier.clickable { isFastStep = !isFastStep },
                                shape = RoundedCornerShape(4.dp),
                                color = if (isFastStep) HextechGold.copy(alpha = 0.2f) else HextechDarkBg,
                                border = BorderStroke(0.5.dp, if (isFastStep) HextechGold else HextechCardBorder)
                            ) {
                                Text(
                                    text = if (isFastStep) "Paso: Rápido (1.0%)" else "Paso: Fino (0.2%)",
                                    color = if (isFastStep) HextechGold else TextSecondary,
                                    fontSize = 7.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Fila Arriba (▲)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { adjustCoordinates(0f, -step, 0f) },
                                modifier = Modifier.size(width = 58.dp, height = 28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("▲", color = HextechGold, fontWeight = FontWeight.Black, fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        // Fila Centro: Izquierda (◀), Diámetro/Zoom, Derecha (▶)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { adjustCoordinates(-step, 0f, 0f) },
                                modifier = Modifier.size(width = 58.dp, height = 28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("◀", color = HextechGold, fontWeight = FontWeight.Black, fontSize = 12.sp)
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Zoom In / Out
                            Button(
                                onClick = { adjustCoordinates(0f, 0f, step) },
                                modifier = Modifier.size(width = 46.dp, height = 28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.2f)),
                                border = BorderStroke(1.dp, HextechCyan),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("+⌀", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 9.5.sp)
                            }

                            Spacer(modifier = Modifier.width(4.dp))

                            Button(
                                onClick = { adjustCoordinates(0f, 0f, -step) },
                                modifier = Modifier.size(width = 46.dp, height = 28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.2f)),
                                border = BorderStroke(1.dp, HextechCyan),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("-⌀", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 9.5.sp)
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = { adjustCoordinates(step, 0f, 0f) },
                                modifier = Modifier.size(width = 58.dp, height = 28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("▶", color = HextechGold, fontWeight = FontWeight.Black, fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        // Fila Abajo (▼) y Reset
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { adjustCoordinates(0f, step, 0f) },
                                modifier = Modifier.size(width = 58.dp, height = 28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechDarkBg),
                                border = BorderStroke(1.dp, HextechGold),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("▼", color = HextechGold, fontWeight = FontWeight.Black, fontSize = 12.sp)
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Surface(
                                modifier = Modifier.clickable {
                                    val def = com.example.service.screen.VisionCalibrationConfig.resetToDefaults(context)
                                    calib = def
                                    com.example.service.screen.DraftVisionScanner.calibrationConfig = def
                                    coroutineScope.launch(Dispatchers.IO) { performSingleEvaluation() }
                                    android.widget.Toast.makeText(context, "Calibración restablecida", android.widget.Toast.LENGTH_SHORT).show()
                                },
                                shape = RoundedCornerShape(4.dp),
                                color = DangerRed.copy(alpha = 0.15f),
                                border = BorderStroke(0.5.dp, DangerRed)
                            ) {
                                Text(
                                    text = "↺ Reset",
                                    color = DangerRed,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Banner informativo sobre dinámica de Draft vs Fase de Preparación
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                    border = BorderStroke(1.dp, HextechCardBorder.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "En selección activa, la barra superior muestra BANEADOS. Al concluir la selección (Fase de Preparación), la barra superior cambia a los 10 campeones ELEGIDOS.",
                            color = TextMuted,
                            fontSize = 7.5.sp,
                            lineHeight = 10.sp
                        )
                    }
                }

                // COMPARATIVA EN VIVO DE GRAN TAMAÑO (84.dp): RECORTE EN VIVO VS AVATAR DE REFERENCIA
                Text(
                    text = "COMPARATIVA DE VISIÓN (TAMAÑO AMPLIADO)",
                    color = HextechCyan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp
                )
                Spacer(modifier = Modifier.height(3.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Recorte en vivo (84.dp)
                        val displayCrop = currentCrop
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Recorte en Vivo", color = TextMuted, fontSize = 8.5.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            if (displayCrop != null && !displayCrop.isRecycled) {
                                Box(
                                    modifier = Modifier
                                        .size(84.dp)
                                        .clip(CircleShape)
                                        .background(HextechDarkBg)
                                        .border(2.dp, if (selectedVision == 0) AllyBlue else DangerRed, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        bitmap = displayCrop.asImageBitmap(),
                                        contentDescription = "Recorte en Vivo",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(84.dp)
                                        .clip(CircleShape)
                                        .background(HextechDarkBg)
                                        .border(1.dp, HextechCardBorder, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Sin señal", color = TextMuted, fontSize = 9.sp)
                                }
                            }
                        }

                        // Similitud / Coincidencia Central
                        val log = currentLog
                        val topCand = log?.topCandidates?.firstOrNull()
                        val rawScore = if (log?.isConfirmed == true && log.selectedChampion != null) {
                            log.confidence
                        } else {
                            (topCand?.compositeScore ?: (log?.confidence ?: 0f))
                        }
                        val score = (rawScore * 100).toInt()
                        val isConfirmed = log?.isConfirmed == true && log.selectedChampion != null && score >= 65

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$score%",
                                color = if (isConfirmed || score >= 55) HextechGold else TextMuted,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isConfirmed) "CONFIRMADO" else if (score >= 48) "COINCIDENCIA" else "BUSCANDO",
                                color = if (isConfirmed) Color(0xFF00FF7F) else if (score >= 48) HextechCyan else TextMuted,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black
                            )
                        }

                        // Avatar Campeón Referencia (84.dp)
                        val matchedChamp = log?.selectedChampion ?: topCand?.champion
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = if (matchedChamp != null) {
                                    if (isConfirmed) "${matchedChamp.name} (Confirmado)" else "${matchedChamp.name} ($score%)"
                                } else "Sin asignar",
                                color = if (isConfirmed) HextechGold else if (matchedChamp != null) HextechCyan else TextMuted,
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.5.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            if (matchedChamp != null) {
                                ChampionAvatar(
                                    champion = matchedChamp,
                                    size = 84.dp,
                                    showTierBadge = false,
                                    borderColor = if (isConfirmed) HextechGold else HextechCyan
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(84.dp)
                                        .clip(CircleShape)
                                        .background(HextechDarkBg)
                                        .border(1.dp, HextechCardBorder, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Info, contentDescription = null, tint = TextMuted, modifier = Modifier.size(24.dp))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // TOP CANDIDATOS EVALUADOS
                val safeLog = currentLog
                if (safeLog != null && safeLog.topCandidates.isNotEmpty()) {
                    Text(
                        text = "TOP CANDIDATOS EVALUADOS",
                        color = HextechCyan,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    safeLog.topCandidates.take(5).forEachIndexed { idx, c ->
                        val isChosen = (idx == 0 && safeLog.selectedChampion != null) || (safeLog.selectedChampion?.id == c.champion.id)
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            shape = RoundedCornerShape(6.dp),
                            color = if (isChosen) HextechGold.copy(alpha = 0.12f) else HextechSurface,
                            border = BorderStroke(
                                1.dp,
                                if (isChosen) HextechGold.copy(alpha = 0.6f) else HextechCardBorder.copy(alpha = 0.4f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "#${idx + 1}",
                                        color = if (isChosen) HextechGold else TextMuted,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 8.5.sp
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    ChampionAvatar(
                                        champion = c.champion,
                                        size = 24.dp,
                                        showTierBadge = false
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column {
                                        Text(
                                            text = c.champion.name,
                                            color = if (isChosen) HextechGold else TextPrimary,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.5.sp
                                        )
                                    }
                                }
                                Text(
                                    text = "${(c.compositeScore * 100).toInt()}%",
                                    color = if (isChosen) HextechGold else TextSecondary,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                } else {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(6.dp),
                        color = HextechSurface,
                        border = BorderStroke(1.dp, HextechCardBorder.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = "Alineando retícula... En selección activa la barra superior muestra baneados. Al entrar a Fase de Preparación mostrará los campeones comparados.",
                            color = TextMuted,
                            fontSize = 8.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // =========================================================================
                // PANEL DE PRUEBA DE MOTORES DE INFERENCIA (ONNX, NCNN, MEDIAPIPE/LITERT, ZNCC)
                // =========================================================================
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                    border = BorderStroke(1.2.dp, HextechGold)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        // Cabecera del Panel de Motores
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Science,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "MOTORES DE INFERENCIA VISUAL (10º PICK)",
                                    color = HextechGold,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 9.5.sp
                                )
                            }

                            // Badge de modo prueba en vivo
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = HextechCyan.copy(alpha = 0.2f),
                                border = BorderStroke(0.5.dp, HextechCyan)
                            ) {
                                Text(
                                    text = "PRUEBA ACTIVA",
                                    color = HextechCyan,
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Selecciona un motor de inferencia para evaluar y validar la precisión del escaneo en tiempo real del 10º pick.",
                            color = TextMuted,
                            fontSize = 7.5.sp,
                            lineHeight = 10.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // SELECTOR DE CHIPS DE LOS 4 MOTORES
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            com.example.service.screen.VisionInferenceEngineType.values().forEach { engine ->
                                val isSelected = (selectedEngine == engine)
                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable {
                                            selectedEngine = engine
                                            com.example.service.screen.VisionInferenceManager.setEngine(engine)
                                            coroutineScope.launch(Dispatchers.IO) { performSingleEvaluation() }
                                        },
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSelected) HextechGold.copy(alpha = 0.25f) else HextechSurface,
                                    border = BorderStroke(
                                        1.dp,
                                        if (isSelected) HextechGold else HextechCardBorder
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 2.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = engine.shortName,
                                            color = if (isSelected) HextechGold else TextPrimary,
                                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Medium,
                                            fontSize = 8.sp,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.height(1.dp))
                                        Text(
                                            text = "${engine.defaultLatencyMs}ms",
                                            color = if (isSelected) Color(0xFF00FF7F) else TextMuted,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 7.sp
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // 1. RECORTE CAPTURADO EN TIEMPO REAL
                        Text(
                            text = "1. RECORTE EN VIVO DEL 10º PICK",
                            color = HextechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.5.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        val cropBmp = currentCrop
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            color = HextechSurface,
                            border = BorderStroke(1.dp, HextechCardBorder)
                        ) {
                            Row(
                                modifier = Modifier.padding(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (cropBmp != null && !cropBmp.isRecycled) {
                                    Box(
                                        modifier = Modifier
                                            .size(52.dp)
                                            .clip(CircleShape)
                                            .border(1.5.dp, if (selectedVision == 0) AllyBlue else DangerRed, CircleShape)
                                    ) {
                                        Image(
                                            bitmap = cropBmp.asImageBitmap(),
                                            contentDescription = "Recorte Capturado",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Dim: ${cropBmp.width}x${cropBmp.height} px | Modo: ${if (selectedVision == 0) "10º Aliado" else "10º Rival"}",
                                            color = TextPrimary,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 8.5.sp
                                        )
                                        val m = safeLog?.scannedMetrics
                                        Text(
                                            text = "Luminancia: ${m?.avgLum?.toInt() ?: "--"} | Contraste: ${m?.contrast?.toInt() ?: "--"}",
                                            color = TextSecondary,
                                            fontSize = 8.sp
                                        )
                                        Text(
                                            text = "Tono dominante: ${m?.dominantHueName ?: "Calculando..."}",
                                            color = HextechGold,
                                            fontSize = 8.sp
                                        )
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .size(52.dp)
                                            .clip(CircleShape)
                                            .background(HextechDarkBg)
                                            .border(1.dp, HextechCardBorder, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("Sin crop", color = TextMuted, fontSize = 8.sp)
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Capturando automáticamente en vivo...",
                                        color = TextMuted,
                                        fontSize = 8.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // 2. TARJETA DETALLADA DEL MOTOR ACTIVO
                        val bench = currentBenchmark
                        val activeChamp = bench?.topCandidate ?: safeLog?.selectedChampion ?: safeLog?.topCandidates?.firstOrNull()?.champion
                        val activeScore = ((bench?.confidenceScore ?: (safeLog?.topCandidates?.firstOrNull()?.compositeScore ?: 0f)) * 100).toInt()
                        val latency = bench?.inferenceTimeMs ?: selectedEngine.defaultLatencyMs

                        Text(
                            text = "2. RESULTADO DE INFERENCIA: ${selectedEngine.displayName.uppercase()}",
                            color = HextechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.5.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(containerColor = HextechSurface),
                            border = BorderStroke(
                                1.dp,
                                if (activeScore >= 50) Color(0xFF00FF7F).copy(alpha = 0.6f) else HextechCardBorder
                            )
                        ) {
                            Column(modifier = Modifier.padding(7.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(7.dp)
                                                .clip(CircleShape)
                                                .background(if (activeScore >= 50) Color(0xFF00FF7F) else HextechGold)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = selectedEngine.shortName,
                                            color = HextechGold,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 8.5.sp
                                        )
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // Badge de Latencia
                                        Surface(
                                            shape = RoundedCornerShape(3.dp),
                                            color = HextechCyan.copy(alpha = 0.2f),
                                            border = BorderStroke(0.5.dp, HextechCyan)
                                        ) {
                                            Text(
                                                text = "⚡ ${latency} ms",
                                                color = HextechCyan,
                                                fontSize = 7.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(4.dp))
                                        // Badge de Precisión
                                        Surface(
                                            shape = RoundedCornerShape(3.dp),
                                            color = if (activeScore >= 55) Color(0xFF00FF7F).copy(alpha = 0.2f) else HextechDarkBg,
                                            border = BorderStroke(0.5.dp, if (activeScore >= 55) Color(0xFF00FF7F) else TextMuted)
                                        ) {
                                            Text(
                                                text = "$activeScore% MATCH",
                                                color = if (activeScore >= 55) Color(0xFF00FF7F) else TextSecondary,
                                                fontSize = 7.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = activeChamp?.name ?: "Analizando fotograma...",
                                    color = TextPrimary,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 11.5.sp
                                )

                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = "• Pipeline: ${selectedEngine.pipelineDetails}\n• Backend: ${selectedEngine.backendInfo}\n• Tensor: ${bench?.tensorResolution ?: "128x128"}",
                                    color = Color(0xFF00FF7F),
                                    fontSize = 7.sp,
                                    lineHeight = 9.sp
                                )

                                if (bench?.extraMetrics?.isNotEmpty() == true) {
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        bench.extraMetrics.forEach { (k, v) ->
                                            Text(
                                                text = "$k: $v",
                                                color = HextechGold,
                                                fontSize = 7.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // 3. COMPARATIVA MULTI-MOTOR EN PARALELO
                        Text(
                            text = "3. CONCORDANCIA ENTRE MOTORES (BENCHMARK)",
                            color = HextechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.5.sp
                        )
                        Spacer(modifier = Modifier.height(3.dp))

                        val allEngines = com.example.service.screen.VisionInferenceEngineType.values()
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            allEngines.forEach { eng ->
                                val isCur = (eng == selectedEngine)
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedEngine = eng
                                            com.example.service.screen.VisionInferenceManager.setEngine(eng)
                                            coroutineScope.launch(Dispatchers.IO) { performSingleEvaluation() }
                                        },
                                    shape = RoundedCornerShape(4.dp),
                                    color = if (isCur) HextechGold.copy(alpha = 0.12f) else HextechSurface,
                                    border = BorderStroke(
                                        0.5.dp,
                                        if (isCur) HextechGold.copy(alpha = 0.6f) else HextechCardBorder.copy(alpha = 0.3f)
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 6.dp, vertical = 3.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = eng.displayName,
                                                color = if (isCur) HextechGold else TextSecondary,
                                                fontSize = 8.sp,
                                                fontWeight = if (isCur) FontWeight.Bold else FontWeight.Normal
                                            )
                                        }
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "${eng.defaultLatencyMs}ms",
                                                color = HextechCyan,
                                                fontSize = 7.5.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = if (activeChamp != null) activeChamp.name else "Listo",
                                                color = if (isCur) Color(0xFF00FF7F) else TextMuted,
                                                fontSize = 7.5.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

