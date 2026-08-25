import re

with open("app/src/main/java/com/example/util/SystemPermissionHelper.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Fix isIgnoringBatteryOptimizations to use applicationContext
old_battery_check = """    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager
        return powerManager?.isIgnoringBatteryOptimizations(context.packageName) ?: false
    }"""

new_battery_check = """    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        val powerManager = context.applicationContext.getSystemService(Context.POWER_SERVICE) as? PowerManager
        return powerManager?.isIgnoringBatteryOptimizations(context.applicationContext.packageName) ?: false
    }

    fun hasStoragePermission(context: Context): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            androidx.core.content.ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_MEDIA_IMAGES) == android.content.pm.PackageManager.PERMISSION_GRANTED
        } else {
            androidx.core.content.ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }"""

content = content.replace(old_battery_check, new_battery_check)

with open("app/src/main/java/com/example/util/SystemPermissionHelper.kt", "w", encoding="utf-8") as f:
    f.write(content)
