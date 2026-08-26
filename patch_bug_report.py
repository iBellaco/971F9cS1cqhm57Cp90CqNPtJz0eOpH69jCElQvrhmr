import re

file_path = "app/src/main/java/com/example/ui/components/BugReportFeedbackDialog.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Add limitMsg and errorMsg outside
new_launcher = """    val successMsg = tr("Imagen adjuntada correctamente")
    val errorMsg = tr("Error al procesar la imagen")
    val limitMsg = tr("La imagen excede el límite de 2 MB")
    
    val imagePickerLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)
    ) { uris ->
        if (uris.isNotEmpty()) {
            scope.launch {
                val newImages = mutableListOf<String>()
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
                            Toast.makeText(context, limitMsg, Toast.LENGTH_LONG).show()
                            continue
                        }
                        
                        val base64 = com.example.util.ImageUtils.uriToBase64(context, uri)
                        if (base64 != null) {
                            newImages.add(base64)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                
                if (newImages.isNotEmpty()) {
                    val combined = (selectedImages + newImages).take(3)
                    selectedImages = combined
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }"""

content = re.sub(r'val successMsg = tr\("Imagen adjuntada correctamente"\).*?Toast\.makeText\(context, errorMsg, Toast\.LENGTH_SHORT\)\.show\(\)\s*\}\s*\}\s*\}\s*\}', new_launcher, content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
