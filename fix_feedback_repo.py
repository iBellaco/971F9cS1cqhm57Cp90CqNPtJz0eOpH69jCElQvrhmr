import re

with open("app/src/main/java/com/example/data/supabase/FeedbackRepository.kt", "r", encoding="utf-8") as f:
    content = f.read()

old_fun = """    suspend fun submitFeedback(
        type: String,
        title: String,
        description: String,
        imageBase64: String? = null,
        retentionDays: Int = 7
    ): Result<Unit> = withContext(Dispatchers.IO) {"""

new_fun = """    suspend fun submitFeedback(
        type: String,
        title: String,
        description: String,
        imagesBase64: List<String> = emptyList(),
        retentionDays: Int = 7
    ): Result<Unit> = withContext(Dispatchers.IO) {"""

content = content.replace(old_fun, new_fun)

old_device_info = """            val baseDeviceInfo = "${Build.MANUFACTURER} ${Build.MODEL} (Android ${Build.VERSION.RELEASE}, API ${Build.VERSION.SDK_INT})"
            val deviceInfo = if (imageBase64 != null) "$baseDeviceInfo\\n\\n[IMAGE_BASE64]\\n$imageBase64" else baseDeviceInfo"""

new_device_info = """            val baseDeviceInfo = "${Build.MANUFACTURER} ${Build.MODEL} (Android ${Build.VERSION.RELEASE}, API ${Build.VERSION.SDK_INT})"
            var deviceInfo = baseDeviceInfo
            if (imagesBase64.isNotEmpty()) {
                for (img in imagesBase64) {
                    deviceInfo += "\\n\\n[IMAGE_BASE64]\\n$img"
                }
            }"""

content = content.replace(old_device_info, new_device_info)

with open("app/src/main/java/com/example/data/supabase/FeedbackRepository.kt", "w", encoding="utf-8") as f:
    f.write(content)
