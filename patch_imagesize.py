import re

file_path = "app/src/main/java/com/example/ui/components/BugReportFeedbackDialog.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Add a check for file size
new_logic = """                val newImages = mutableListOf<String>()
                for (uri in uris) {
                    try {
                        val cursor = context.contentResolver.query(uri, null, null, null, null)
                        var sizeInBytes: Long = 0
                        if (cursor != null && cursor.moveToFirst()) {
                            val sizeIndex = cursor.getColumnIndex(android.provider.OpenableColumns.SIZE)
                            if (sizeIndex != -1) {
                                sizeInBytes = cursor.getLong(sizeIndex)
                            }
                            cursor.close()
                        }
                        
                        // Si el tamaño es mayor a 2 MB (2 * 1024 * 1024 = 2097152 bytes), rechazar
                        if (sizeInBytes > 2 * 1024 * 1024) {
                            Toast.makeText(context, tr("La imagen excede el límite de 2 MB"), Toast.LENGTH_LONG).show()
                            continue
                        }
                        
                        val base64 = com.example.util.ImageUtils.uriToBase64(context, uri)
                        if (base64 != null) {
                            newImages.add(base64)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }"""

content = re.sub(r'val newImages = mutableListOf<String>\(\)\s*for \(uri in uris\) \{\s*val base64 = com\.example\.util\.ImageUtils\.uriToBase64\(context, uri\)\s*if \(base64 != null\) \{\s*newImages\.add\(base64\)\s*\}\s*\}', new_logic, content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
