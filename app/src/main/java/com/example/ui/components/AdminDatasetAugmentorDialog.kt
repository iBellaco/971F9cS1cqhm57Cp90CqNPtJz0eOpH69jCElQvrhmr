package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.sync.DatasetAugmentor
import com.example.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun AdminDatasetAugmentorDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by DatasetAugmentor.augmentorState.collectAsState()

    var selectedTreeUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFolderName by remember { mutableStateOf<String?>(null) }

    val directoryPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION or android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            } catch (e: Exception) {
                // Ignorar si no requiere persistencia
            }
            selectedTreeUri = uri
            selectedFolderName = uri.lastPathSegment ?: "Carpeta Seleccionada"
            Toast.makeText(context, "Carpeta de dataset importada correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    Dialog(
        onDismissRequest = {
            if (!state.isRunning) onDismiss()
            else Toast.makeText(context, "El proceso de aumento está en curso...", Toast.LENGTH_SHORT).show()
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
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = HextechGold, modifier = Modifier.size(26.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Aumento de Dataset (Variantes IA)", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("Generación de 6 variantes por cada campeón", color = TextSecondary, fontSize = 11.sp)
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
                            Text("Estado y Selección", color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(state.progressText, color = TextPrimary, fontSize = 12.sp)

                            if (selectedFolderName != null) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Carpeta: $selectedFolderName", color = HextechCyan, fontSize = 11.sp)
                            }

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
                                OutlinedButton(
                                    onClick = { directoryPickerLauncher.launch(null) },
                                    enabled = !state.isRunning,
                                    modifier = Modifier.weight(1f),
                                    border = BorderStroke(1.dp, HextechCyan)
                                ) {
                                    Icon(Icons.Default.FolderOpen, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Importar Carpeta", color = HextechCyan, fontSize = 12.sp)
                                }

                                Button(
                                    onClick = {
                                        val uri = selectedTreeUri
                                        if (uri != null) {
                                            coroutineScope.launch {
                                                DatasetAugmentor.processFolder(context, uri)
                                            }
                                        } else {
                                            Toast.makeText(context, "Primero selecciona la carpeta del dataset", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    enabled = !state.isRunning && selectedTreeUri != null,
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(if (state.isRunning) "Procesando..." else "Generar Variantes", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
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
                                    fontFamily = FontFamily.Monospace
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
