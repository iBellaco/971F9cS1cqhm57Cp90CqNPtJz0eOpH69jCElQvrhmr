import sys

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    content = f.read()

target = """                when (downloadState) {
                    DownloadState.IDLE -> {
                        Button(
                            onClick = { OfflineResourceManager.startDownload(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val sizeText = if (totalMB > 0f) " (~${String.format(java.util.Locale.US, "%.1f", totalMB)} MB)" else ""
                            Text(tr("Descargar Recursos") + sizeText, fontWeight = FontWeight.Bold)
                        }
                    }"""
                    
replacement = """                when (downloadState) {
                    DownloadState.IDLE -> {
                        Column {
                            // Botón de Admin para exportar recursos offline (Imágenes, descripciones, etc)
                            val coroutineScope = androidx.compose.runtime.rememberCoroutineScope()
                            Button(
                                onClick = { 
                                    coroutineScope.kotlinx.coroutines.launch {
                                        val result = com.example.util.AssetExporter.exportAssetsToZip(context)
                                        result.onSuccess { msg ->
                                            android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG).show()
                                        }.onFailure { e ->
                                            android.widget.Toast.makeText(context, "Error: ${e.message}", android.widget.Toast.LENGTH_LONG).show()
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan, contentColor = HextechDarkBg),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(tr("Exportar Recursos (Admin)"), fontWeight = FontWeight.Bold)
                            }
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Button(
                                onClick = { OfflineResourceManager.startDownload(context) },
                                colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val sizeText = if (totalMB > 0f) " (~${String.format(java.util.Locale.US, "%.1f", totalMB)} MB)" else ""
                                Text(tr("Descargar Recursos") + sizeText, fontWeight = FontWeight.Bold)
                            }
                        }
                    }"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
        f.write(content)
    print("Admin button injected")
else:
    print("Admin button target not found")
