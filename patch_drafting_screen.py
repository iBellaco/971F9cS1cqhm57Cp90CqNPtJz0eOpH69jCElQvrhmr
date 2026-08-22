import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import_statement = """import com.example.ui.theme.TextPrimary
import com.example.util.SystemPermissionHelper
import android.media.projection.MediaProjectionManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.service.screen.ScreenCaptureManager
import android.content.Context"""

content = content.replace("""import com.example.ui.theme.TextPrimary
import com.example.util.SystemPermissionHelper""", import_statement)

logic_target = """    val toggleAssistant: () -> Unit = {
        if (isAssistantActive) {
            SystemPermissionHelper.stopFloatingService(context)
            isAssistantActive = false
        } else {
            if (!SystemPermissionHelper.hasOverlayPermission(context)) {
                showPermissionDialog = true
            } else {
                SystemPermissionHelper.startFloatingService(context)
                isAssistantActive = true
            }
        }
    }"""

logic_replacement = """    val mediaProjectionLauncher = rememberLauncherForActivityResult(
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
    }"""

content = content.replace(logic_target, logic_replacement)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
