package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.sync.WildRiftChampionScraper
import com.example.ui.theme.*
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun AdminChampionScraperDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by WildRiftChampionScraper.scraperState.collectAsState()

    val clipboardManager = remember { context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager }

    fun copyToClipboard(label: String, text: String) {
        val clip = ClipData.newPlainText(label, text)
        clipboardManager?.setPrimaryClip(clip)
        Toast.makeText(context, "Copiado al portapapeles: $text", Toast.LENGTH_SHORT).show()
    }

    Dialog(
        onDismissRequest = {
            if (!state.isRunning) onDismiss()
            else Toast.makeText(context, "El scraping está en curso...", Toast.LENGTH_SHORT).show()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = HextechDarkBg
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                // Header
                Surface(
                    color = HextechSurface,
                    tonalElevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CloudDownload, contentDescription = null, tint = HextechGold, modifier = Modifier.size(26.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Scraper de Campeones (Wiki Wild Rift)", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("Organización por carpetas con nombre del campeón", color = TextSecondary, fontSize = 11.sp)
                            }
                        }
                        IconButton(
                            onClick = {
                                if (!state.isRunning) onDismiss()
                                else Toast.makeText(context, "Espera a que finalice el proceso", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextPrimary)
                        }
                    }
                }

                // Content Body
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Estado del Proceso", color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(state.progressText, color = TextPrimary, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                StatBadge("Encontrados", "${state.totalFound}", HextechCyan)
                                StatBadge("Procesados", "${state.processedCount}", Color(0xFF10B981))
                                StatBadge("Errores", "${state.errorCount}", DangerRed)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        coroutineScope.launch {
                                            WildRiftChampionScraper.runScraper(context)
                                        }
                                    },
                                    enabled = !state.isRunning,
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(if (state.isRunning) "Scraping en curso..." else "Iniciar Scraping", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }

                                if (state.datasetPath != null) {
                                    OutlinedButton(
                                        onClick = {
                                            state.datasetPath?.let { path ->
                                                copyToClipboard("Ruta Dataset", path)
                                            }
                                        },
                                        border = BorderStroke(1.dp, HextechCyan)
                                    ) {
                                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Copiar Ruta", color = HextechCyan, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Consola de Logs en Tiempo Real:", color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        color = HextechSurfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, TextMuted.copy(alpha = 0.3f))
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(state.logs) { log ->
                                Text(
                                    text = log,
                                    color = if (log.contains("ERROR") || log.contains("FATAL")) DangerRed else TextSecondary,
                                    fontSize = 10.5.sp,
                                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatBadge(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, color = TextMuted, fontSize = 10.sp)
    }
}
