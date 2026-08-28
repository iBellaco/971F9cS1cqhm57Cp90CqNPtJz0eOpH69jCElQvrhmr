import re

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

import re
# We need to extract the "Acerca De" button block and place it below the Instagram banner.
# And place OfflineResourceDownloadCard above the Instagram banner.
# The layout is currently:
#                 androidx.compose.material3.OutlinedButton(
#                     onClick = onNavigateToInfo,
#                     modifier = Modifier...testTag("btn_about_below_download")
#                 ) { ... }
#                 Spacer(modifier = Modifier.height(12.dp))
#                 // Banner Red Social Instagram - Diego Barba Chavez
#                 val context = LocalContext.current
#                 val instagramUrl = "https://www.instagram.com/Diego.Barba.Chavez"
#                 androidx.compose.foundation.Image(
#                     ...
#                 )
#                 Spacer(modifier = Modifier.height(32.dp))

pattern_about_btn = re.compile(r'( {16}androidx\.compose\.material3\.OutlinedButton\(\s*onClick = onNavigateToInfo,\s*modifier = Modifier[\s\S]*?testTag\("btn_about_below_download"\)[\s\S]*?\} {16}\}\s*Spacer\(modifier = Modifier\.height\(12\.dp\)\)\s*)')
about_btn_match = pattern_about_btn.search(code)

if about_btn_match:
    about_btn_text = about_btn_match.group(1)
    # Remove it from its original place
    code = code.replace(about_btn_text, "")
    
    # Now find the instagram block to insert things around it
    pattern_insta = re.compile(r'( {16}// Banner Red Social Instagram - Diego Barba Chavez\s*val context = LocalContext\.current[\s\S]*?contentScale = ContentScale\.FillWidth\s*\))')
    insta_match = pattern_insta.search(code)
    if insta_match:
        insta_text = insta_match.group(1)
        new_layout = f"""                OfflineResourceDownloadCard()
                Spacer(modifier = Modifier.height(16.dp))

{insta_text}

                Spacer(modifier = Modifier.height(16.dp))
{about_btn_text}"""
        code = code.replace(insta_text, new_layout)
    else:
        print("Insta block not found")
else:
    print("About button block not found")

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

