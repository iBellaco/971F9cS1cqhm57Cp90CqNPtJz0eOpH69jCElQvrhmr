import re
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

replacement = """    private var lastScreenWidth = 0
    private var lastScreenHeight = 0

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        try {
            val metrics = resources.displayMetrics
            if (lastScreenWidth != metrics.widthPixels || lastScreenHeight != metrics.heightPixels) {
                lastScreenWidth = metrics.widthPixels
                lastScreenHeight = metrics.heightPixels
                screenCaptureManager?.refreshProjection()
            }
            
            // Reajustar coordenadas de la vista flotante para la nueva orientación de pantalla"""

text = text.replace("""    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        try {
            screenCaptureManager?.refreshProjection()
            
            // Reajustar coordenadas de la vista flotante para la nueva orientación de pantalla""", replacement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
