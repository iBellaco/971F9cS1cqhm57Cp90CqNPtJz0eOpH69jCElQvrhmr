package com.example.ui.components.admin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.data.WildRiftRepository
import com.example.data.local.WildRiftLocalCache
import com.example.data.supabase.SupabaseClientManager
import com.example.data.supabase.WildRiftSupabaseRepository
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.launch

@Composable
fun AdminSupabaseSyncTab() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var patchVersionInput by remember { mutableStateOf(WildRiftRepository.CURRENT_PATCH_VERSION) }
    var patchNotesInput by remember { mutableStateOf("Actualización de balance y estadísticas.") }
    var isPublishingPatch by remember { mutableStateOf(false) }

    // Supabase Config fields
    var customUrlInput by remember { mutableStateOf(SupabaseClientManager.getActiveUrl()) }
    var customKeyInput by remember { mutableStateOf(SupabaseClientManager.getActiveKey()) }
    var isTestingConnection by remember { mutableStateOf(false) }
    var testConnectionResult by remember { mutableStateOf<String?>(null) }

    // Seeding & Sync state
    var isSeeding by remember { mutableStateOf(false) }
    var isSyncing by remember { mutableStateOf(false) }

    // Popup summary dialog state
    var showSummaryDialog by remember { mutableStateOf(false) }
    var summaryDialogTitle by remember { mutableStateOf("") }
    var summaryDialogContent by remember { mutableStateOf("") }

    fun showSummary(title: String, content: String) {
        summaryDialogTitle = title
        summaryDialogContent = content
        showSummaryDialog = true
    }

    if (showSummaryDialog) {
        AlertDialog(
            onDismissRequest = { showSummaryDialog = false },
            title = { Text(summaryDialogTitle, color = HextechCyan) },
            text = { Text(summaryDialogContent, color = TextPrimary) },
            confirmButton = {
                TextButton(onClick = { showSummaryDialog = false }) {
                    Text("Aceptar", color = HextechGold)
                }
            },
            containerColor = HextechSurface
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // 1. CARD: CONTROL Y PUBLICACIÓN DE PARCHES
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechGold)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.RocketLaunch, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(tr("Publicador de Parche de Wild Rift"), color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Al cambiar el parche aquí y publicarlo, todos los usuarios sincronizarán automáticamente la nueva versión.",
                    color = TextMuted,
                    fontSize = 11.5.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = patchVersionInput,
                    onValueChange = { patchVersionInput = it },
                    label = { Text("Versión del Parche (ej. Parche 7.2c / Parche 7.3)", fontSize = 11.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = patchNotesInput,
                    onValueChange = { patchNotesInput = it },
                    label = { Text("Notas breves del parche", fontSize = 11.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        if (patchVersionInput.isBlank()) return@Button
                        isPublishingPatch = true
                        scope.launch {
                            val res = WildRiftSupabaseRepository.publishPatch(patchVersionInput, patchNotesInput)
                            isPublishingPatch = false
                            if (res.isSuccess) {
                                WildRiftLocalCache.saveToLocalCache(context, patchVersion = patchVersionInput)
                                showSummary("Parche Publicado", "¡Parche $patchVersionInput publicado en Supabase exitosamente!")
                            } else {
                                showSummary("Error", "Error publicando: ${res.exceptionOrNull()?.message}")
                            }
                        }
                    },
                    enabled = !isPublishingPatch,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (isPublishingPatch) {
                        CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(tr("🚀 Publicar Parche Oficial en la Nube"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. CARD: SEMBRAR MASIVAMENTE (GUARDADO POR SECCIÓN)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudUpload, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(tr("Subir a Supabase (Individual por Sección)"), color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Sube las modificaciones locales a la nube seleccionando la categoría específica para no sobreescribir todo.",
                    color = TextMuted,
                    fontSize = 11.5.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Campeones
                Button(
                    onClick = {
                        isSeeding = true
                        scope.launch {
                            val res = WildRiftSupabaseRepository.seedChampionsToSupabase()
                            isSeeding = false
                            if (res.isSuccess) {
                                showSummary("Campeones Subidos", "Se subieron exitosamente ${res.getOrNull()} campeones a la nube.")
                            } else {
                                showSummary("Error Campeones", "Error: ${res.exceptionOrNull()?.message}")
                            }
                        }
                    },
                    enabled = !isSeeding && !isSyncing,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(tr("Subir Campeones"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Objetos
                Button(
                    onClick = {
                        isSeeding = true
                        scope.launch {
                            val res = WildRiftSupabaseRepository.seedItemsToSupabase()
                            isSeeding = false
                            if (res.isSuccess) {
                                showSummary("Objetos Subidos", "Se subieron exitosamente ${res.getOrNull()} objetos a la nube.")
                            } else {
                                showSummary("Error Objetos", "Error: ${res.exceptionOrNull()?.message}")
                            }
                        }
                    },
                    enabled = !isSeeding && !isSyncing,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(tr("Subir Objetos"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Runas
                Button(
                    onClick = {
                        isSeeding = true
                        scope.launch {
                            val res = WildRiftSupabaseRepository.seedRunesToSupabase()
                            isSeeding = false
                            if (res.isSuccess) {
                                showSummary("Runas Subidas", "Se subieron exitosamente ${res.getOrNull()} runas a la nube.")
                            } else {
                                showSummary("Error Runas", "Error: ${res.exceptionOrNull()?.message}")
                            }
                        }
                    },
                    enabled = !isSeeding && !isSyncing,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(tr("Subir Runas"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = HextechCardBorder)
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudDownload, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(tr("Descargar de Supabase"), color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Sincroniza la aplicación bajando toda la base de datos de la nube.",
                    color = TextMuted,
                    fontSize = 11.5.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        isSyncing = true
                        scope.launch {
                            val res = WildRiftSupabaseRepository.syncAllFromSupabase(context)
                            isSyncing = false
                            if (res.isSuccess) {
                                showSummary("Sincronización Completa", "¡Éxito!\n\n${res.getOrNull()}")
                            } else {
                                showSummary("Error Sincronizando", "Error: ${res.exceptionOrNull()?.message}")
                            }
                        }
                    },
                    enabled = !isSeeding && !isSyncing,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (isSyncing) {
                        CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(tr("⚡ Sincronizar Datos desde Supabase Ahora"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. CARD: CONFIGURACIÓN DE CONEXIÓN SUPABASE
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Settings, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(tr("Credenciales de Supabase (URL y Clave)"), color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Puedes ingresar la URL de tu proyecto y la Anon Key o Service Role Key de Supabase.",
                    color = TextMuted,
                    fontSize = 11.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = customUrlInput,
                    onValueChange = { customUrlInput = it },
                    label = { Text("Supabase Project URL", fontSize = 11.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = customKeyInput,
                    onValueChange = { customKeyInput = it },
                    label = { Text("Supabase Anon Key / API Key", fontSize = 11.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            SupabaseClientManager.saveCustomCredentials(context, customUrlInput, customKeyInput)
                            Toast.makeText(context, "Credenciales guardadas", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(tr("Guardar Claves"), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            isTestingConnection = true
                            testConnectionResult = null
                            scope.launch {
                                val res = SupabaseClientManager.testConnection()
                                isTestingConnection = false
                                if (res.isSuccess) {
                                    testConnectionResult = "✅ ${res.getOrNull()}"
                                } else {
                                    testConnectionResult = "❌ Error: ${res.exceptionOrNull()?.message}"
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        if (isTestingConnection) {
                            CircularProgressIndicator(color = HextechCyan, modifier = Modifier.size(14.dp), strokeWidth = 2.dp)
                        } else {
                            Text(tr("Probar Conexión"), color = HextechCyan, fontSize = 12.sp)
                        }
                    }
                }

                testConnectionResult?.let {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(it, color = if (it.startsWith("✅")) HextechGreen else DangerRed, fontSize = 11.5.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Botón Copiar Script SQL
                OutlinedButton(
                    onClick = {
                        val schema = SupabaseClientManager.getSupabaseSqlSchema()
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Supabase SQL Schema", schema))
                        Toast.makeText(context, "¡Script SQL copiado al portapapeles! Pégalo en Supabase SQL Editor.", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(tr("Copiar Script SQL para Supabase (Crear Tablas)"), color = HextechGold, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        
        // 4. CARD: IMPORTADOR Y EXPORTADOR MASIVO DE JSON / BACKUP
        AdminJsonBackupManager()

        Spacer(modifier = Modifier.height(30.dp))
    }
}
