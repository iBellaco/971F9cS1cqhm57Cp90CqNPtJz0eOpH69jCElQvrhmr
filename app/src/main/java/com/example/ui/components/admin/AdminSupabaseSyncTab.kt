package com.example.ui.components.admin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
    var seedProgressText by remember { mutableStateOf("") }
    var seedProgressPercent by remember { mutableFloatStateOf(0f) }

    var isSyncing by remember { mutableStateOf(false) }
    var syncResultText by remember { mutableStateOf<String?>(null) }

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
                    Text(tr("Publicador de Parche de Wild Rift"), color = HextechGoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                                Toast.makeText(context, "¡Parche $patchVersionInput publicado en Supabase!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Error publicando: ${res.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
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
                    Text(tr("Publicar Parche en Supabase"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. CARD: SIEMBRA DE DATOS Y SINCRONIZACIÓN
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechCyan)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudSync, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(tr("Sincronización y Siembra (Seed)"), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Subir todo el catálogo actual de la app (160+ ítems, 110+ campeones, runas y hechizos) a Supabase con 1 clic.",
                    color = TextMuted,
                    fontSize = 11.5.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Botón Seed Data
                Button(
                    onClick = {
                        isSeeding = true
                        seedProgressPercent = 0f
                        seedProgressText = "Iniciando siembra en Supabase..."
                        scope.launch {
                            val res = WildRiftSupabaseRepository.seedAllDataToSupabase { current, total, msg ->
                                seedProgressPercent = if (total > 0) current.toFloat() / total.toFloat() else 0f
                                seedProgressText = msg
                            }
                            isSeeding = false
                            if (res.isSuccess) {
                                Toast.makeText(context, "¡Todo el catálogo fue sembrado en Supabase!", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(context, "Error en siembra: ${res.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    enabled = !isSeeding && !isSyncing,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGreen),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (isSeeding) {
                        CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(tr("🌱 Subir Todo el Catálogo a Supabase (Seed All)"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }

                if (isSeeding || seedProgressText.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { seedProgressPercent },
                        modifier = Modifier.fillMaxWidth(),
                        color = HextechGreen,
                        trackColor = HextechSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(seedProgressText, color = TextPrimary, fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Botón Sincronizar desde Supabase
                Button(
                    onClick = {
                        isSyncing = true
                        syncResultText = "Descargando datos desde Supabase..."
                        scope.launch {
                            val res = WildRiftSupabaseRepository.syncAllFromSupabase(context)
                            isSyncing = false
                            if (res.isSuccess) {
                                syncResultText = res.getOrNull()
                                Toast.makeText(context, "¡Sincronización completada!", Toast.LENGTH_SHORT).show()
                            } else {
                                syncResultText = "Error: ${res.exceptionOrNull()?.message}"
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

                syncResultText?.let {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(it, color = if (it.startsWith("Error")) DangerRed else HextechCyan, fontSize = 11.5.sp)
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
                    Text(tr("Credenciales de Supabase (URL y Clave)"), color = HextechGoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
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

        Spacer(modifier = Modifier.height(30.dp))
    }
}
