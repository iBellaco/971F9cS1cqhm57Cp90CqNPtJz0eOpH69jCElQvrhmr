package com.example.ui.components.admin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.data.local.WildRiftLocalCache
import com.example.data.supabase.WildRiftSupabaseRepository
import com.example.model.*
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class WrBackupDatabaseDto(
    val formatVersion: String = "1.0",
    val patchVersion: String = "",
    val patchNotes: String = "",
    val exportedAt: Long = System.currentTimeMillis(),
    val champions: List<Champion> = emptyList(),
    val items: List<WildRiftItem> = emptyList(),
    val runes: List<RuneItem> = emptyList(),
    val spells: List<SummonerSpellItem> = emptyList(),
    val mapObjectives: List<MapObjectiveItem> = emptyList()
)

@Composable
fun AdminJsonBackupManager(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val jsonFormat = remember { Json { prettyPrint = true; ignoreUnknownKeys = true; isLenient = true; encodeDefaults = true } }

    var jsonExportString by remember { mutableStateOf("") }
    var jsonImportInput by remember { mutableStateOf("") }
    var isExporting by remember { mutableStateOf(false) }
    var isImporting by remember { mutableStateOf(false) }
    var importStatusMessage by remember { mutableStateOf<String?>(null) }
    var isSyncingToSupabaseAfterImport by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // =====================================================================
        // CARD 1: EXPORTADOR MASIVO DE BASE DE DATOS
        // =====================================================================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechGold)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FileUpload, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "📤 Exportar Base de Datos a JSON",
                        color = HextechGoldLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Genera un backup íntegro en JSON con todos los Campeones, Objetos, Runas, Hechizos y Objetivos actuales.",
                    color = TextMuted,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            isExporting = true
                            scope.launch(Dispatchers.Default) {
                                val backup = WrBackupDatabaseDto(
                                    patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION,
                                    patchNotes = "Copia de seguridad oficial del catálogo.",
                                    exportedAt = System.currentTimeMillis(),
                                    champions = WildRiftRepository.champions,
                                    items = WildRiftRepository.items,
                                    runes = WildRiftRepository.runes,
                                    spells = WildRiftRepository.summonerSpells,
                                    mapObjectives = WildRiftRepository.mapObjectives
                                )
                                val jsonStr = jsonFormat.encodeToString(backup)
                                withContext(Dispatchers.Main) {
                                    jsonExportString = jsonStr
                                    isExporting = false
                                }
                            }
                        },
                        enabled = !isExporting,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        if (isExporting) {
                            CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp))
                        } else {
                            Icon(Icons.Default.Code, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Generar JSON", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    if (jsonExportString.isNotBlank()) {
                        Button(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("WildRift_Backup_JSON", jsonExportString)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "¡JSON copiado al portapapeles con éxito!", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Copiar", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }

                if (jsonExportString.isNotBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Resumen: ${WildRiftRepository.champions.size} Campeones • ${WildRiftRepository.items.size} Objetos • ${WildRiftRepository.runes.size} Runas • ${(jsonExportString.length / 1024)} KB",
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = jsonExportString.take(600) + if (jsonExportString.length > 600) "\n... [${jsonExportString.length} caracteres totales]" else "",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        textStyle = LocalTextStyle.current.copy(fontSize = 10.sp, fontFamily = FontFamily.Monospace),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = HextechDarkBg,
                            unfocusedContainerColor = HextechDarkBg,
                            focusedBorderColor = HextechCardBorder,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // =====================================================================
        // CARD 2: IMPORTADOR MASIVO DE BASE DE DATOS
        // =====================================================================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechCyan)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FileDownload, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "📥 Importar / Restaurar Base de Datos desde JSON",
                        color = HextechCyan,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Pega una copia de seguridad en JSON para actualizar de golpe todos los datos del juego.",
                    color = TextMuted,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = jsonImportInput,
                    onValueChange = { jsonImportInput = it },
                    placeholder = { Text("Pega aquí el JSON completo del parche o backup...", fontSize = 11.sp, color = TextMuted) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 10.5.sp, fontFamily = FontFamily.Monospace),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = HextechDarkBg,
                        unfocusedContainerColor = HextechDarkBg,
                        focusedBorderColor = HextechCyan,
                        unfocusedBorderColor = HextechCardBorder
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = isSyncingToSupabaseAfterImport,
                        onCheckedChange = { isSyncingToSupabaseAfterImport = it },
                        colors = CheckboxDefaults.colors(checkedColor = HextechCyan, checkmarkColor = HextechDarkBg)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Subir automáticamente a Supabase tras importar", color = TextPrimary, fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    onClick = {
                        if (jsonImportInput.isBlank()) {
                            Toast.makeText(context, "Pega un JSON válido primero.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        isImporting = true
                        importStatusMessage = "Validando e importando catálogo..."
                        scope.launch(Dispatchers.IO) {
                            try {
                                val backup = jsonFormat.decodeFromString<WrBackupDatabaseDto>(jsonImportInput)

                                if (backup.champions.isNotEmpty()) {
                                    WildRiftRepository.champions = backup.champions
                                }
                                if (backup.items.isNotEmpty()) {
                                    WildRiftRepository.items = backup.items
                                }
                                if (backup.runes.isNotEmpty()) {
                                    WildRiftRepository.runes = backup.runes
                                }
                                if (backup.spells.isNotEmpty()) {
                                    WildRiftRepository.summonerSpells = backup.spells
                                }
                                if (backup.patchVersion.isNotBlank()) {
                                    WildRiftRepository.CURRENT_PATCH_VERSION = backup.patchVersion
                                }

                                // Persistir en la caché local
                                WildRiftLocalCache.saveToLocalCache(
                                    context = context,
                                    items = WildRiftRepository.items,
                                    champions = WildRiftRepository.champions,
                                    runes = WildRiftRepository.runes,
                                    spells = WildRiftRepository.summonerSpells,
                                    patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION
                                )

                                var cloudSyncNote = ""
                                if (isSyncingToSupabaseAfterImport) {
                                    val cloudResult = WildRiftSupabaseRepository.seedAllDataToSupabase { _, _, _ -> }
                                    cloudSyncNote = if (cloudResult.isSuccess) {
                                        " y subido a Supabase Cloud con éxito."
                                    } else {
                                        " (Nota: Falló la subida a Supabase: ${cloudResult.exceptionOrNull()?.message})"
                                    }
                                }

                                withContext(Dispatchers.Main) {
                                    isImporting = false
                                    importStatusMessage = "✅ Importación exitosa: ${backup.champions.size} campeones, ${backup.items.size} objetos, ${backup.runes.size} runas ($backup.patchVersion)$cloudSyncNote"
                                    Toast.makeText(context, "¡Base de datos restaurada correctamente!", Toast.LENGTH_LONG).show()
                                }
                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    isImporting = false
                                    importStatusMessage = "❌ Error en el JSON: ${e.message}"
                                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    },
                    enabled = !isImporting && jsonImportInput.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isImporting) {
                        CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Procesando...", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    } else {
                        Icon(Icons.Default.CloudSync, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Aplicar e Importar Masivamente", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }

                if (importStatusMessage != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = importStatusMessage!!,
                        color = if (importStatusMessage!!.startsWith("✅")) AllyBlue else DangerRed,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
