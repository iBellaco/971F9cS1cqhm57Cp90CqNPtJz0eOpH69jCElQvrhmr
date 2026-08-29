import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

# Add imports
imports = """import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
"""
text = text.replace('import android.os.Bundle', imports + 'import android.os.Bundle')

# Request Permission in MainActivity
permission_request = """    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted
        } else {
            // Permission is denied
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {"""

text = re.sub(
    r'\s*override fun onCreate\(savedInstanceState: Bundle\?\) \{',
    permission_request,
    text
)

call_ask = """        AppThemeManager.init(this)
        com.example.util.SubscriptionManager.init(this)
        askNotificationPermission()"""

text = re.sub(
    r'AppThemeManager\.init\(this\)\s*com\.example\.util\.SubscriptionManager\.init\(this\)',
    call_ask,
    text
)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
