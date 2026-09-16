package com.example.ui.components

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
import com.example.data.sync.ZipDatasetManager
import com.example.service.screen.LocalVisionAnalyzer
import com.example.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun AdminDatasetAugmentorDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by DatasetAugmentor.augmentorState.collectAsState()
    val zipStatus by ZipDatasetManager.status.collectAsState()

    LaunchedEffect(Unit) {
        ZipDatasetManager.refreshStats(context)
        LocalVisionAnalyzer.ensureInitialized(context)
    }

    var selectedTreeUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFolderName by remember { mutableStateOf<String?>(null) }

    val zipPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            coroutineScope.launch {
                val success = ZipDatasetManager.importFromUri(context, uri)
                if (success) {
                    Toast.makeText(context, "¡Dataset ZIP importado y sincronizado con el escáner!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Error al procesar el archivo ZIP", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val directoryPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION or android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            } catch (_: Exception) {}
            selectedTreeUri = uri
            selectedFolderName = uri.lastPathSegment ?: "Carpeta Seleccionada"
            Toast.makeText(context, "Carpeta de dataset seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    val isBusy = state.isRunning || zipStatus.isImporting

    Dialog(
        onDismissRequest = {
            if (!isBusy) onDismiss()
            else Toast.makeText(context, "Proceso en curso, espera...", Toast.LENGTH_SHORT).show()
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
                            Icon(Icons.Default.Inventory2, contentDescription = null, tint = HextechGold, modifier = Modifier.size(26.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Dataset de Campeones (141)", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("Importación de ZIP y variantes para el escáner del 10º pick", color = TextSecondary, fontSize = 11.sp)
                            }
                        }
                        IconButton(
                            onClick = {
                                if (!isBusy) onDismiss()
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
                    // SECCIÓN PRINCIPAL: DATASET ZIP DIRECTO (141 CAMPEONES)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.2.dp, if (zipStatus.isReady) HextechCyan else HextechGold.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.FolderZip, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Dataset ZIP (141 Campeones)", color = HextechGold, fontSize = 13.5.sp, fontWeight = FontWeight.Bold)
                                }
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (zipStatus.isReady) Color(0xFF10B981).copy(alpha = 0.2f) else DangerRed.copy(alpha = 0.2f),
                                    border = BorderStroke(1.dp, if (zipStatus.isReady) Color(0xFF10B981) else DangerRed)
                                ) {
                                    Text(
                                        text = if (zipStatus.isReady) "ACTIVO EN ESCÁNER" else "PENDIENTE DE CARGA",
                                        color = if (zipStatus.isReady) Color(0xFF10B981) else DangerRed,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(zipStatus.progressMessage, color = TextPrimary, fontSize = 11.5.sp)

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                StatBadge("Campeones ZIP", "${zipStatus.championsCount}", HextechGold)
                                StatBadge("Variantes Reales", "${zipStatus.totalImagesCount}", HextechCyan)
                                StatBadge("Motor de Visión", if (LocalVisionAnalyzer.isDatasetZipLoaded) "Sincronizado" else "Pendiente", if (LocalVisionAnalyzer.isDatasetZipLoaded) Color(0xFF10B981) else TextMuted)
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { zipPickerLauncher.launch("*/*") },
                                    enabled = !isBusy,
                                    modifier = Modifier.weight(1.2f),
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                                ) {
                                    Icon(Icons.Default.FileUpload, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (zipStatus.isImporting) "Extrayendo..." else "Importar ZIP",
                                        color = HextechDarkBg,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.5.sp
                                    )
                                }

                                OutlinedButton(
                                    onClick = {
                                        coroutineScope.launch {
                                            val found = ZipDatasetManager.autoDetectAndImportFromDownloads(context)
                                            if (found) {
                                                Toast.makeText(context, "¡ZIP encontrado en Descargas y sincronizado!", Toast.LENGTH_LONG).show()
                                            } else {
                                                Toast.makeText(context, "No se encontró ningún archivo .zip en la carpeta Descargas", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    },
                                    enabled = !isBusy,
                                    modifier = Modifier.weight(1f),
                                    border = BorderStroke(1.dp, HextechCyan)
                                ) {
                                    Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(15.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Buscar en Descargas", color = HextechCyan, fontSize = 11.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // SECCIÓN SECUNDARIA: GENERADOR / AUMENTO DE CARPETA LOCAL
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.6f)),
                        border = BorderStroke(0.8.dp, TextMuted.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Generador de Variantes (Carpeta)", color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(state.progressText, color = TextSecondary, fontSize = 10.5.sp)

                            if (selectedFolderName != null) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Carpeta: $selectedFolderName", color = HextechGold, fontSize = 10.sp)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { directoryPickerLauncher.launch(null) },
                                    enabled = !isBusy,
                                    modifier = Modifier.weight(1f),
                                    border = BorderStroke(0.8.dp, TextSecondary)
                                ) {
                                    Icon(Icons.Default.FolderOpen, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Seleccionar Carpeta", color = TextSecondary, fontSize = 10.5.sp)
                                }

                                Button(
                                    onClick = {
                                        val uri = selectedTreeUri
                                        if (uri != null) {
                                            coroutineScope.launch {
                                                DatasetAugmentor.processFolder(context, uri)
                                            }
                                        } else {
                                            Toast.makeText(context, "Selecciona primero una carpeta", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    enabled = !isBusy && selectedTreeUri != null,
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Generar 6 Variantes", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 10.5.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Registro de Operaciones en Vivo:", color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))

                    val allLogs = remember(zipStatus.logs, state.logs) {
                        (zipStatus.logs + state.logs)
                    }

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
                            if (allLogs.isEmpty()) {
                                item {
                                    Text(
                                        text = "Listo. Importa un archivo ZIP con los 141 campeones o presiona 'Buscar en Descargas' para sincronizar con el escáner del 10º pick.",
                                        color = TextMuted,
                                        fontSize = 10.5.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                            } else {
                                items(allLogs) { log ->
                                    Text(
                                        text = log,
                                        color = if (log.contains("ERROR") || log.contains("FATAL")) DangerRed else TextSecondary,
                                        fontSize = 10.sp,
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
}

@Composable
private fun StatBadge(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Text(label, color = TextMuted, fontSize = 9.5.sp)
    }
}
