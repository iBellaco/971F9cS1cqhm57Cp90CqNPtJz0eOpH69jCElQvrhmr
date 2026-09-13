package com.example.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.AppNotice
import com.example.data.AppNoticeManager
import com.example.ui.theme.*
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

@Composable
fun SponsorCpmPanelDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val authUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
    val userEmail = authUser?.email ?: "patrocinador@coach.app"

    val allNotices by AppNoticeManager.notices.collectAsState()
    
    // Lista local de anuncios pendientes (guardados en SharedPreferences para evitar que desaparezcan)
    val prefs = context.getSharedPreferences("sponsor_pending_ads", Context.MODE_PRIVATE)
    var localPendingAds by remember { 
        mutableStateOf<List<AppNotice>>(
            try {
                val json = prefs.getString("pending_ads", "[]") ?: "[]"
                val jsonArray = org.json.JSONArray(json)
                val list = mutableListOf<AppNotice>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    list.add(
                        AppNotice(
                            id = obj.optString("id", ""),
                            title = obj.optString("title", ""),
                            content = obj.optString("content", ""),
                            expandedImageUrl = obj.optString("expandedImageUrl", ""),
                            externalUrl = obj.optString("externalUrl", ""),
                            tag = obj.optString("tag", "Publicidad"),
                            budget = obj.optDouble("budget", 0.0),
                            budgetUnit = obj.optString("budgetUnit", "day"),
                            isApproved = obj.optBoolean("isApproved", false),
                            isEnabled = obj.optBoolean("isEnabled", false),
                            sponsorEmail = obj.optString("sponsorEmail", "")
                        )
                    )
                }
                list
            } catch (e: Exception) { emptyList() }
        )
    }

    val myNotices = remember(allNotices, userEmail, localPendingAds) {
        val remoteAds = allNotices.filter { it.sponsorEmail.equals(userEmail, ignoreCase = true) || it.tag.equals("Publicidad", true) }
        val remoteAdIds = remoteAds.map { it.id }.toSet()
        // Mostrar los remotos + los locales que aún no están en la lista remota
        remoteAds + localPendingAds.filter { it.id !in remoteAdIds }
    }

    var showCreateDialog by remember { mutableStateOf(false) }
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }
    var imageUrlInput by remember { mutableStateOf("") }
    var externalUrlInput by remember { mutableStateOf("") }
    var durationValueInput by remember { mutableStateOf("1") }
    var selectedDurationUnit by remember { mutableStateOf("day") } // "hour", "day", "week", "month", "year"

    val durationValueInt = remember(durationValueInput) {
        durationValueInput.toIntOrNull()?.coerceAtLeast(1) ?: 1
    }

    // El presupuesto se calcula automáticamente según la unidad y la cantidad
    val autoBudget = remember(selectedDurationUnit, durationValueInt) {
        val unitPrice = when (selectedDurationUnit) {
            "hour" -> 1.50
            "day" -> 10.00
            "week" -> 50.00
            "month" -> 150.00
            "year" -> 1000.00
            else -> 10.00
        }
        val total = unitPrice * durationValueInt
        String.format(java.util.Locale.US, "%.2f", total)
    }
    var budgetInput by remember { mutableStateOf("10.00") }
    LaunchedEffect(autoBudget) { budgetInput = autoBudget }

    val coroutineScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = HextechDarkBg,
            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Campaign, contentDescription = null, tint = HextechGold, modifier = Modifier.size(28.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Panel CPM de Patrocinador", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Gestiona tus anuncios publicitarios y presupuestos", color = TextSecondary, fontSize = 12.sp)
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Action Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Tus Anuncios (${myNotices.size})", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Button(
                        onClick = {
                            titleInput = ""
                            contentInput = ""
                            imageUrlInput = ""
                            externalUrlInput = ""
                            budgetInput = "10.00"
                            selectedDurationUnit = "day"
                            showCreateDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Nuevo Anuncio", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // List of notices
                if (myNotices.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.AdsClick, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No tienes anuncios publicados.", color = TextSecondary, fontSize = 14.sp)
                            Text("Crea uno y espera la aprobación del administrador.", color = TextSecondary.copy(alpha = 0.7f), fontSize = 11.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(myNotices, key = { it.id }) { notice ->
                            SponsorNoticeCard(notice = notice, onDelete = {
                                val updatedLocal = localPendingAds.filter { it.id != notice.id }
                                localPendingAds = updatedLocal
                                
                                val jsonArray = org.json.JSONArray()
                                updatedLocal.forEach { n ->
                                    val obj = org.json.JSONObject()
                                    obj.put("id", n.id)
                                    obj.put("title", n.title)
                                    obj.put("content", n.content)
                                    obj.put("expandedImageUrl", n.expandedImageUrl)
                                    obj.put("externalUrl", n.externalUrl)
                                    obj.put("tag", n.tag)
                                    obj.put("budget", n.budget)
                                    obj.put("budgetUnit", n.budgetUnit)
                                    obj.put("isApproved", n.isApproved)
                                    obj.put("isEnabled", n.isEnabled)
                                    obj.put("sponsorEmail", n.sponsorEmail)
                                    jsonArray.put(obj)
                                }
                                prefs.edit().putString("pending_ads", jsonArray.toString()).apply()

                                val updated = allNotices.filter { it.id != notice.id }
                                AppNoticeManager.saveNotices(context, updated)
                                Toast.makeText(context, "Anuncio eliminado", Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }
    }

    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            containerColor = HextechSurface,
            title = { Text("Publicar Anuncio CPM", color = HextechGold, fontWeight = FontWeight.Bold) },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth().verticalScroll(androidx.compose.foundation.rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = titleInput,
                        onValueChange = { titleInput = it },
                        label = { Text("Título del Anuncio") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechGold, unfocusedBorderColor = HextechSurfaceVariant)
                    )
                    OutlinedTextField(
                        value = contentInput,
                        onValueChange = { contentInput = it },
                        label = { Text("Contenido / Mensaje Publicitario") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechGold, unfocusedBorderColor = HextechSurfaceVariant)
                    )
                    OutlinedTextField(
                        value = imageUrlInput,
                        onValueChange = { imageUrlInput = it },
                        label = { Text("URL de Imagen / Banner Publicitario") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechGold, unfocusedBorderColor = HextechSurfaceVariant)
                    )
                    OutlinedTextField(
                        value = externalUrlInput,
                        onValueChange = { externalUrlInput = it },
                        label = { Text("Enlace Web Externo (CTA)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechGold, unfocusedBorderColor = HextechSurfaceVariant)
                    )

                    OutlinedTextField(
                        value = budgetInput,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Presupuesto Total (USD)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold, 
                            unfocusedBorderColor = HextechSurfaceVariant,
                            disabledTextColor = HextechCyan
                        )
                    )

                    Text("Duración de la Publicación:", color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = durationValueInput,
                            onValueChange = { if (it.all { char -> char.isDigit() }) durationValueInput = it.take(3) },
                            label = { Text("Cantidad") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(100.dp),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HextechGold, unfocusedBorderColor = HextechSurfaceVariant)
                        )

                        Text("Unidad de tiempo:", color = TextSecondary, fontSize = 11.sp)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            "hour" to "Horas",
                            "day" to "Días",
                            "week" to "Semanas",
                            "month" to "Meses",
                            "year" to "Años"
                        ).forEach { (unitId, unitLabel) ->
                            val isSelected = selectedDurationUnit == unitId
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedDurationUnit = unitId },
                                label = { Text(unitLabel, fontSize = 10.sp) },
                                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = HextechGold, selectedLabelColor = HextechDarkBg)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Nota: El anuncio requiere la aprobación de un administrador para ser visible en la plataforma.", color = TextSecondary, fontSize = 10.5.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsedBudget = budgetInput.replace(',', '.').toDoubleOrNull() ?: 10.0
                        if (titleInput.isBlank() || contentInput.isBlank()) {
                            Toast.makeText(context, "El título y contenido son obligatorios", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        
                        val descriptionStr = """
                            Presupuesto: $autoBudget USD
                            Duración: $durationValueInt $selectedDurationUnit
                            Imagen: $imageUrlInput
                            Enlace: $externalUrlInput
                            
                            $contentInput
                        """.trimIndent()
                        
                        val newPendingNotice = AppNotice(
                            id = UUID.randomUUID().toString(),
                            title = titleInput.trim(),
                            content = contentInput.trim(),
                            expandedImageUrl = imageUrlInput.trim(),
                            externalUrl = externalUrlInput.trim(),
                            tag = "Publicidad",
                            budget = parsedBudget,
                            budgetUnit = selectedDurationUnit,
                            durationValue = durationValueInt,
                            durationUnit = selectedDurationUnit,
                            isApproved = false,
                            isEnabled = false,
                            sponsorEmail = userEmail
                        )

                        // Guardar en localPendingAds y registrar en AppNoticeManager para moderación
                        val updatedLocalList = localPendingAds + newPendingNotice
                        localPendingAds = updatedLocalList
                        AppNoticeManager.submitPendingSponsorNotice(context, newPendingNotice)
                        
                        val jsonArray = org.json.JSONArray()
                        updatedLocalList.forEach { n ->
                            val obj = org.json.JSONObject()
                            obj.put("id", n.id)
                            obj.put("title", n.title)
                            obj.put("content", n.content)
                            obj.put("expandedImageUrl", n.expandedImageUrl)
                            obj.put("externalUrl", n.externalUrl)
                            obj.put("tag", n.tag)
                            obj.put("budget", n.budget)
                            obj.put("budgetUnit", n.budgetUnit)
                            obj.put("durationValue", n.durationValue)
                            obj.put("durationUnit", n.durationUnit)
                            obj.put("isApproved", n.isApproved)
                            obj.put("isEnabled", n.isEnabled)
                            obj.put("sponsorEmail", n.sponsorEmail)
                            jsonArray.put(obj)
                        }
                        prefs.edit().putString("pending_ads", jsonArray.toString()).apply()

                        // Enviar la solicitud a Supabase como Feedback
                        coroutineScope.launch {
                            try {
                                com.example.data.supabase.FeedbackRepository.submitFeedback(
                                    type = "SPONSOR_AD",
                                    title = titleInput.trim(),
                                    description = descriptionStr,
                                    email = userEmail
                                )
                                Toast.makeText(context, "Anuncio enviado a revisión de administrador", Toast.LENGTH_SHORT).show()
                                showCreateDialog = false
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error al enviar: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
                ) {
                    Text("Enviar a Revisión", color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}

@Composable
fun SponsorNoticeCard(
    notice: AppNotice,
    onDelete: () -> Unit
) {
    val statusText = if (notice.isApproved) "Aprobado y Activo" else "Pendiente de Aprobación"
    val statusColor = if (notice.isApproved) Color(0xFF10B981) else Color(0xFFF59E0B)

    val metricsMap by com.example.data.AppNoticeAnalyticsManager.metricsMap.collectAsState()
    val metrics = metricsMap[notice.id] ?: com.example.data.NoticeMetrics(notice.id)
    val ctr = if (metrics.impressions > 0) (metrics.clicks.toDouble() / metrics.impressions) * 100 else 0.0

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, statusColor.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(notice.title, color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = statusColor.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = statusText,
                        color = statusColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Text(notice.content, color = TextSecondary, fontSize = 12.sp, maxLines = 2, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)

            // Statistics Row
            if (notice.isApproved) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(HextechDarkBg, RoundedCornerShape(6.dp))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Vistas", color = TextSecondary, fontSize = 10.sp)
                        Text("${metrics.impressions}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Clics", color = TextSecondary, fontSize = 10.sp)
                        Text("${metrics.clicks}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("CTR", color = TextSecondary, fontSize = 10.sp)
                        Text(String.format(java.util.Locale.US, "%.1f%%", ctr), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            val unitLabel = when (notice.durationUnit.lowercase(Locale.ROOT)) {
                "hour", "hours", "hora", "horas" -> "Horas"
                "day", "days", "dia", "dias", "día", "días" -> "Días"
                "week", "weeks", "semana", "semanas" -> "Semanas"
                "month", "months", "mes", "meses" -> "Meses"
                "year", "years", "año", "años", "ano", "anos" -> "Años"
                else -> notice.durationUnit
            }

            val expirationStr = if (notice.expiresAtMillis > 0L) {
                val diff = notice.expiresAtMillis - System.currentTimeMillis()
                if (diff > 0) {
                    val hours = diff / (1000 * 60 * 60)
                    val days = hours / 24
                    if (days > 0) "Expira en: ${days}d ${hours % 24}h"
                    else "Expira en: ${hours}h ${(diff / (1000 * 60)) % 60}m"
                } else {
                    "Expirado"
                }
            } else null

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Presupuesto: $${String.format(java.util.Locale.US, "%.2f", notice.budget)} USD", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    Text("Duración: ${notice.durationValue} $unitLabel", color = TextSecondary, fontSize = 10.sp)
                    if (expirationStr != null) {
                        Text(expirationStr, color = if (expirationStr == "Expirado") DangerRed else HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                    }
                }
                IconButton(onClick = onDelete, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
