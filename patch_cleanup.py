import sys
import os

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    content = f.read()

# 1. Remove Language Dropdown
lang_target = """                    actions = {
                        var expandedLang by remember { mutableStateOf(false) }
                        val activeFlag = when (currentLanguage.lowercase()) {
                            "en" -> "🇺🇸"
                            "pt" -> "🇧🇷"
                            else -> "🇪🇸"
                        }
                        Box {
                            TextButton(
                                onClick = { expandedLang = true },
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurface)
                                    .border(1.dp, HextechGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                    .testTag("nav_language_button")
                            ) {
                                Text(activeFlag, fontSize = 16.sp)
                            }
                            androidx.compose.material3.DropdownMenu(
                                expanded = expandedLang,
                                onDismissRequest = { expandedLang = false },
                                modifier = Modifier.background(HextechSurface)
                            ) {
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { 
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("🇪🇸 Español", color = if (currentLanguage == "es") HextechGold else TextPrimary, fontWeight = if (currentLanguage == "es") FontWeight.Bold else FontWeight.Normal)
                                            if (currentLanguage == "es") {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text("(Activo)", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    },
                                    onClick = { 
                                        onLanguageChange("es")
                                        expandedLang = false 
                                    }
                                )
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { 
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("🇧🇷 Português", color = TextMuted, fontWeight = FontWeight.Normal)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("(Em Manutenção)", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                        }
                                    },
                                    onClick = { 
                                        android.widget.Toast.makeText(context, "Idioma Português em manutenção / Idioma Português em breve", android.widget.Toast.LENGTH_SHORT).show()
                                        expandedLang = false 
                                    }
                                )
                            }
                        }

                        IconButton(
                            onClick = { showBugReportDialog = true },"""
lang_replacement = """                    actions = {
                        IconButton(
                            onClick = { showBugReportDialog = true },"""
                            
if lang_target in content:
    content = content.replace(lang_target, lang_replacement)
    print("Language Dropdown Removed")
else:
    print("Language Dropdown NOT FOUND")

# 2. Remove Admin Export Button
admin_target = """                when (downloadState) {
                    DownloadState.IDLE -> {
                        Column {
                            // Botón de Admin para exportar recursos offline (Imágenes, descripciones, etc)
                            val coroutineScope = androidx.compose.runtime.rememberCoroutineScope()
                            Button(
                                onClick = { 
                                    coroutineScope.launch {
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
                    
admin_replacement = """                when (downloadState) {
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

if admin_target in content:
    content = content.replace(admin_target, admin_replacement)
    print("Admin Export Button Removed")
else:
    print("Admin Export Button NOT FOUND")
    
with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(content)
