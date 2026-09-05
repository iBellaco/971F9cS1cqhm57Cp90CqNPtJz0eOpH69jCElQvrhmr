import re

with open('app/src/main/java/com/example/util/WildRiftOfficialScraper.kt', 'r') as f:
    content = f.read()

# Fix relative paths and MediaStore inserts in both functions
def replace_mediastore(match):
    return """
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/""" + """${OUTPUT_DIR_NAME}/")
                    put(MediaStore.MediaColumns.IS_PENDING, 1)
                }
            }

            var mediaStoreError: String? = null
            val uri = try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    resolver.insert(MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY), contentValues)
                        ?: resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                } else {
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                }
            } catch (e: Exception) {
                mediaStoreError = "insert: ${e.message}"
                null
            }

            if (uri != null) {
                try {
                    resolver.openOutputStream(uri)?.use { os ->
                        os.write(bytes)
                        os.flush()
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        contentValues.clear()
                        contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                        resolver.update(uri, contentValues, null, null)
                    }
                    return Pair("$relativeSubDir/$fileName", null)
                } catch (e: Exception) {
                    mediaStoreError = "openOutputStream: ${e.message}"
                    try { resolver.delete(uri, null, null) } catch (_: Exception) {}
                }
            }
"""

# Actually it's easier to use a targeted replacement.
