import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# 1. Add a state variable for landscape mode
state_var = """    private var floatingComposeView: ComposeView? = null
    
    // Estado para la orientación de la pantalla real
    private val isDeviceLandscape = androidx.compose.runtime.mutableStateOf(false)"""

text = text.replace("    private var floatingComposeView: ComposeView? = null", state_var)

# 2. Update it in onConfigurationChanged
config_update = """            val metrics = resources.displayMetrics
            if (lastScreenWidth != metrics.widthPixels || lastScreenHeight != metrics.heightPixels) {
                lastScreenWidth = metrics.widthPixels
                lastScreenHeight = metrics.heightPixels
                isDeviceLandscape.value = lastScreenWidth > lastScreenHeight
                screenCaptureManager?.refreshProjection()
            }"""

text = text.replace("""            val metrics = resources.displayMetrics
            if (lastScreenWidth != metrics.widthPixels || lastScreenHeight != metrics.heightPixels) {
                lastScreenWidth = metrics.widthPixels
                lastScreenHeight = metrics.heightPixels
                screenCaptureManager?.refreshProjection()
            }""", config_update)

# 3. Initialize it in createFloatingOverlay
init_state = """        val displayMetrics = resources.displayMetrics
        isDeviceLandscape.value = displayMetrics.widthPixels > displayMetrics.heightPixels
        val density = displayMetrics.density"""

text = text.replace("""        val displayMetrics = resources.displayMetrics
        val density = displayMetrics.density""", init_state)

# 4. Use it in the Compose content instead of LocalConfiguration
compose_use = """    val isLandscapeMode by isDeviceLandscape

    Column(
        modifier = Modifier.fillMaxSize()
    ) {"""

text = text.replace("""    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val isLandscapeMode = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier.fillMaxSize()
    ) {""", compose_use)


with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
