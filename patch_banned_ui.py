import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

banned_state = """    val activeUpdateInfo by AppUpdateManager.updateInfo.collectAsStateWithLifecycle()
    val isBanned by com.example.util.SubscriptionManager.isBanned.collectAsStateWithLifecycle()"""

text = text.replace('    val activeUpdateInfo by AppUpdateManager.updateInfo.collectAsStateWithLifecycle()', banned_state)

banned_ui = """    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
        if (isBanned) {
            Box(
                modifier = Modifier.fillMaxSize().background(HextechDarkBg),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                    Icon(Icons.Default.Block, contentDescription = null, tint = Color.Red, modifier = Modifier.size(64.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Cuenta Suspendida", color = Color.Red, fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tu acceso ha sido revocado permanentemente. Contacta con soporte si crees que esto es un error.", color = TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }
            return@CompositionLocalProvider
        }
        
        // Modal de Alerta de Actualización Disponible con opción de descarga directa"""

text = text.replace('    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {\n        // Modal de Alerta de Actualización Disponible con opción de descarga directa', banned_ui)

# Need to add import for Icons.Default.Block
if 'import androidx.compose.material.icons.filled.Block' not in text:
    text = text.replace('import androidx.compose.material.icons.Icons', 'import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Block')


with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
