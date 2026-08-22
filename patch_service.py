import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Add imports
imports_target = "import com.example.MainActivity"
imports_replacement = """import com.example.MainActivity
import com.example.service.screen.ScreenCaptureManager
import com.example.service.screen.DraftVisionScanner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext"""
content = content.replace(imports_target, imports_replacement)

# Add screenCaptureManager var
var_target = "class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {"
var_replacement = """class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    private var screenCaptureManager: ScreenCaptureManager? = null"""
content = content.replace(var_target, var_replacement)

# Initialize ScreenCaptureManager
init_target = """    override fun onCreate() {
        super.onCreate()
        try {"""
init_replacement = """    override fun onCreate() {
        super.onCreate()
        try {
            screenCaptureManager = ScreenCaptureManager(this)"""
content = content.replace(init_target, init_replacement)

# Service foreground type
fg_target = """                    android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE"""
fg_replacement = """                    android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE or 32""" # MEDIA_PROJECTION is 32 in API 29+
content = content.replace(fg_target, fg_replacement)

# onStartCommand check for pending projection
start_target = """    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            stopSelf()
            return START_NOT_STICKY
        }
        return START_STICKY
    }"""
start_replacement = """    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
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
    }"""
content = content.replace(start_target, start_replacement)

# onDestroy release projection
destroy_target = """        store.clear()
        removeFloatingOverlay()"""
destroy_replacement = """        store.clear()
        screenCaptureManager?.release()
        removeFloatingOverlay()"""
content = content.replace(destroy_target, destroy_replacement)

# Mocked list to State list, and scanning implementation
mock_target = """    var lockedChampion by remember { mutableStateOf<Champion?>(null) }

    val allies = remember {
        listOfNotNull(
            WildRiftRepository.getChampionById("vayne"),
            WildRiftRepository.getChampionById("janna"),
            WildRiftRepository.getChampionById("viego")
        )
    }

    val enemies = remember {
        listOfNotNull(
            WildRiftRepository.getChampionById("sett"),
            WildRiftRepository.getChampionById("vi"),
            WildRiftRepository.getChampionById("caitlyn")
        )
    }"""
mock_replacement = """    var lockedChampion by remember { mutableStateOf<Champion?>(null) }

    val allies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }
    val enemies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }"""
content = content.replace(mock_target, mock_replacement)

scan_target = """                .clickable {
                    if (!isExpanded) {
                        isScanning = true
                        selectedTab = 0
                        // Simular escaneo de 1.2s antes de abrir
                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                            isScanning = false
                            isExpanded = true
                            onExpandedChange(true)
                        }, 1200)
                    } else {
                        isExpanded = false
                        onExpandedChange(false)
                    }
                },"""
scan_replacement = """                .clickable {
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
                },"""
content = content.replace(scan_target, scan_replacement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
