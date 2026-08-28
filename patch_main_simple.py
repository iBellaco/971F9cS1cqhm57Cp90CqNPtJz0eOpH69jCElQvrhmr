with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

# Make sure to import what's needed
imports = """import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import com.example.data.sync.OfflineResourceManager
import com.example.data.sync.DownloadState
import com.example.ui.theme.HextechRed
"""
if "import com.example.data.sync.OfflineResourceManager" not in code:
    code = code.replace("import androidx.compose.runtime.*", imports + "import androidx.compose.runtime.*")

card_ui = """
@Composable
fun OfflineResourceDownloadCard() {
    val downloadState by OfflineResourceManager.downloadState.collectAsState()
    val progress by OfflineResourceManager.progress.collectAsState()
    val downloaded by OfflineResourceManager.downloadedCount.collectAsState()
    val total by OfflineResourceManager.totalCount.collectAsState()
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.CloudDownload, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                Text(
                    text = tr("Descarga de Recursos Offline"),
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = tr("Imágenes (campeones, habilidades, objetos, runas y hechizos) para usar sin conexión y carga más rápida."),
                color = TextSecondary,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
            
            Spacer(modifier = Modifier.height(12.dp))

            when (downloadState) {
                DownloadState.IDLE -> {
                    Button(
                        onClick = { OfflineResourceManager.startDownload(context) },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(tr("Descargar Recursos"), fontWeight = FontWeight.Bold)
                    }
                }
                DownloadState.DOWNLOADING -> {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth().height(6.dp),
                        color = HextechCyan,
                        trackColor = HextechSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("$downloaded / $total", color = TextPrimary, fontSize = 12.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = { OfflineResourceManager.pauseDownload() },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechGold),
                                border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                            ) {
                                Text(tr("Pausar"), fontSize = 12.sp)
                            }
                        }
                    }
                }
                DownloadState.PAUSED -> {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth().height(6.dp),
                        color = HextechGold,
                        trackColor = HextechSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(tr("Pausado") + " - $downloaded / $total", color = HextechGold, fontSize = 12.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = { OfflineResourceManager.cancelDownload() },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechRed),
                                border = BorderStroke(1.dp, HextechRed.copy(alpha = 0.6f)),
                                contentPadding = PaddingValues(horizontal = 12.dp)
                            ) {
                                Text(tr("Cancelar"), fontSize = 12.sp)
                            }
                            Button(
                                onClick = { OfflineResourceManager.resumeDownload(context) },
                                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan, contentColor = HextechDarkBg),
                                contentPadding = PaddingValues(horizontal = 12.dp)
                            ) {
                                Text(tr("Reanudar"), fontSize = 12.sp)
                            }
                        }
                    }
                }
                DownloadState.COMPLETED -> {
                    LinearProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.fillMaxWidth().height(6.dp),
                        color = HextechGold,
                        trackColor = HextechSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(tr("¡Descarga Completada!"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                DownloadState.ERROR -> {
                    Text(tr("Error en la descarga. Comprueba tu conexión."), color = HextechRed, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { OfflineResourceManager.startDownload(context) },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(tr("Reintentar"), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
"""

if "fun OfflineResourceDownloadCard" not in code:
    code += "\n" + card_ui

# Find the start of the button
btn_start = code.find("androidx.compose.material3.OutlinedButton(\n                    onClick = onNavigateToInfo")
if btn_start == -1:
    btn_start = code.find("                androidx.compose.material3.OutlinedButton(")

if btn_start != -1:
    # Find the end of the button (closing bracket matching the opening)
    btn_end_str = "                    )\n                    Spacer(modifier = Modifier.width(8.dp))\n                    Text(\n                        text = tr(\"Acerca De\"),\n                        color = HextechGold,\n                        fontSize = 13.sp,\n                        fontWeight = FontWeight.Bold\n                    )\n                }\n"
    btn_end = code.find(btn_end_str, btn_start)
    if btn_end != -1:
        btn_end += len(btn_end_str)
        # Include the spacer after it
        spacer_end = code.find("Spacer(modifier = Modifier.height(12.dp))\n", btn_end)
        if spacer_end != -1:
            spacer_end += len("Spacer(modifier = Modifier.height(12.dp))\n")
            
            button_block = code[btn_start:spacer_end]
            
            # Remove button block
            code = code[:btn_start] + code[spacer_end:]
            
            # Find instagram banner start
            insta_start_str = "                // Banner Red Social Instagram - Diego Barba Chavez\n"
            insta_start = code.find(insta_start_str)
            if insta_start != -1:
                # Find the end of the Instagram block (contentScale = ContentScale.FillWidth\n                )\n)
                insta_end_str = "contentScale = ContentScale.FillWidth\n                )\n"
                insta_end = code.find(insta_end_str, insta_start)
                if insta_end != -1:
                    insta_end += len(insta_end_str)
                    
                    # Insert Download card before instagram banner, and Button after
                    insert_before = "                OfflineResourceDownloadCard()\n                Spacer(modifier = Modifier.height(16.dp))\n\n"
                    insert_after = "\n                Spacer(modifier = Modifier.height(16.dp))\n" + button_block
                    
                    code = code[:insta_start] + insert_before + code[insta_start:insta_end] + insert_after + code[insta_end:]
                    print("Successfully moved UI elements!")

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

