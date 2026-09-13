package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Reply
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.SupportReplyManager
import com.example.data.remote.model.FeedbackReport
import com.example.data.supabase.FeedbackRepository
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGreen
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private const val TAG = "AdminSupportReports"

data class UnifiedSupportReport(
    val id: String,
    val type: String = "SOPORTE",
    val title: String = "",
    val description: String = "",
    val userId: String = "",
    val userEmail: String = "",
    val userName: String = "",
    val photosBase64: List<String> = emptyList(),
    val status: String = FeedbackRepository.STATUS_PENDING,
    val appVersion: String = "",
    val device: String = "",
    val createdAtMillis: Long = System.currentTimeMillis(),
    val rawSupabaseReport: FeedbackReport? = null,
    val isFirestoreDoc: Boolean = false,
    val adminReply: String = "",
    val repliedAtMillis: Long = 0L,
    val repliedBy: String = ""
)

@Composable
fun AdminSupportReportsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val reportsList = remember { mutableStateListOf<UnifiedSupportReport>() }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("ALL") } // "ALL", "PENDING", "READ", "SOLVED"
    var previewZoomBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var reportToDelete by remember { mutableStateOf<UnifiedSupportReport?>(null) }
    var reportToReply by remember { mutableStateOf<UnifiedSupportReport?>(null) }
    var initialReplyText by remember { mutableStateOf("") }

    val authUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
    val userEmail = authUser?.email ?: ""
    val userRole by com.example.util.SubscriptionManager.userRole.collectAsState()
    val isAdmin = userRole == "admin" || com.example.util.AuthManager.isCurrentUserAdmin() || userEmail.contains("barbadiego", ignoreCase = true)

    fun loadAllReports() {
        isLoading = true
        coroutineScope.launch {
            // Ejecutar purga automática de reportes expirados (30 días leídos, 60 días sin leer)
            try {
                SupportReplyManager.autoPurgeAllExpired(context)
            } catch (e: Exception) {
                Log.w(TAG, "Error durante auto-purga: ${e.message}")
            }

            val combined = mutableListOf<UnifiedSupportReport>()

            // 1. Cargar desde Supabase (FeedbackRepository) - Fuente de datos primaria
            try {
                val supabaseResult = FeedbackRepository.getAllFeedbacks()
                if (supabaseResult.isSuccess) {
                    val supaList = supabaseResult.getOrDefault(emptyList())
                    for (fb in supaList) {
                        val rawType = fb.type.trim().uppercase()
                        val isSupport = rawType in listOf("SOPORTE", "SUPPORT", "TICKET", "AYUDA") ||
                                fb.title.contains("Soporte", ignoreCase = true) ||
                                fb.title.contains("Ticket", ignoreCase = true)
                        
                        if (!isSupport) continue

                        val id = fb.id ?: "${fb.title}_${fb.createdAt}"
                        val status = FeedbackRepository.getReportStatus(context, fb)
                        val email = fb.parsedEmail ?: ""
                        val cleanDesc = fb.cleanDescription.substringBefore("[IMAGE_BASE64]").trim()
                        val cleanDev = fb.deviceInfo.substringBefore("[IMAGE_BASE64]").trim()

                        // Extraer fotos base64
                        val photos = mutableListOf<String>()
                        val fullRaw = "${fb.description}\n${fb.deviceInfo}"
                        if (fullRaw.contains("[IMAGE_BASE64]")) {
                            val parts = fullRaw.split("[IMAGE_BASE64]")
                            for (i in 1 until parts.size) {
                                val segment = parts[i].trim().substringBefore("\n\n").substringBefore("[IMAGE_BASE64]").trim()
                                if (segment.isNotBlank()) {
                                    photos.add(segment)
                                }
                            }
                        }

                        val createdMillis = parseIsoDateToMillis(fb.createdAt)
                        val localReply = SupportReplyManager.getLocalReply(context, id)
                        val finalReply = if (!fb.adminReply.isNullOrBlank()) fb.adminReply else (localReply?.text ?: "")
                        val repliedAt = if (!fb.repliedAt.isNullOrBlank()) parseIsoDateToMillis(fb.repliedAt) else (localReply?.timestampMillis ?: 0L)
                        val repliedBy = localReply?.author ?: "Equipo Coach"

                        combined.add(
                            UnifiedSupportReport(
                                id = id,
                                type = fb.type,
                                title = fb.title.ifBlank { "Reporte sin título" },
                                description = cleanDesc,
                                userId = "",
                                userEmail = email,
                                userName = fb.parsedUserName ?: "",
                                photosBase64 = photos,
                                status = status,
                                appVersion = fb.appVersion,
                                device = cleanDev,
                                createdAtMillis = createdMillis,
                                rawSupabaseReport = fb,
                                isFirestoreDoc = false,
                                adminReply = finalReply,
                                repliedAtMillis = repliedAt,
                                repliedBy = repliedBy
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error cargando feedbacks de Supabase: ${e.message}")
            }

            // 2. Intentar leer también desde Firestore silenciosamente
            try {
                FirebaseFirestore.getInstance()
                    .collection("support_reports")
                    .orderBy("createdAt", Query.Direction.DESCENDING)
                    .limit(50)
                    .get()
                    .addOnSuccessListener { snapshot ->
                        if (snapshot != null && !snapshot.isEmpty) {
                            for (doc in snapshot.documents) {
                                val docId = doc.id
                                val docTitle = doc.getString("title") ?: ""
                                val existing = combined.find { it.id == docId || (docTitle.isNotBlank() && it.title == docTitle) }
                                val docReply = doc.getString("adminReply") ?: ""
                                val docRepliedAt = doc.getTimestamp("repliedAt")?.toDate()?.time ?: 0L
                                val docRepliedBy = doc.getString("repliedBy") ?: ""
                                val localReply = SupportReplyManager.getLocalReply(context, docId)
                                val finalReply = if (docReply.isNotBlank()) docReply else (localReply?.text ?: "")
                                val finalRepliedAt = if (docRepliedAt > 0L) docRepliedAt else (localReply?.timestampMillis ?: 0L)
                                val finalRepliedBy = if (docRepliedBy.isNotBlank()) docRepliedBy else (localReply?.author ?: "Equipo Coach")

                                if (existing != null) {
                                    val idx = combined.indexOf(existing)
                                    combined[idx] = existing.copy(
                                        adminReply = if (finalReply.isNotBlank()) finalReply else existing.adminReply,
                                        repliedAtMillis = if (finalRepliedAt > 0L) finalRepliedAt else existing.repliedAtMillis,
                                        repliedBy = if (finalRepliedBy.isNotBlank()) finalRepliedBy else existing.repliedBy
                                    )
                                } else {
                                    val desc = doc.getString("description") ?: ""
                                    val userId = doc.getString("userId") ?: ""
                                    val userEmail = doc.getString("userEmail") ?: ""
                                    val userName = doc.getString("userName") ?: ""
                                    @Suppress("UNCHECKED_CAST")
                                    val photos = (doc.get("photos") as? List<String>) ?: emptyList()
                                    val rawStatus = doc.getString("status") ?: "PENDIENTE"
                                    val normalizedStatus = when (rawStatus.uppercase()) {
                                        "SOLVED", "SOLUCIONADO", "RESUELTO" -> FeedbackRepository.STATUS_SOLVED
                                        "READ", "LEIDO", "LEÍDO" -> FeedbackRepository.STATUS_READ
                                        else -> FeedbackRepository.STATUS_PENDING
                                    }
                                    val appVer = doc.getString("appVersion") ?: ""
                                    val dev = doc.getString("device") ?: ""
                                    val ts = doc.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis()

                                    combined.add(
                                        UnifiedSupportReport(
                                            id = docId,
                                            type = "SOPORTE",
                                            title = docTitle.ifBlank { "Ticket de soporte" },
                                            description = desc,
                                            userId = userId,
                                            userEmail = userEmail,
                                            userName = userName,
                                            photosBase64 = photos,
                                            status = normalizedStatus,
                                            appVersion = appVer,
                                            device = dev,
                                            createdAtMillis = ts,
                                            rawSupabaseReport = null,
                                            isFirestoreDoc = true,
                                            adminReply = finalReply,
                                            repliedAtMillis = finalRepliedAt,
                                            repliedBy = finalRepliedBy
                                        )
                                    )
                                }
                            }
                        }
                        reportsList.clear()
                        reportsList.addAll(combined.sortedByDescending { it.createdAtMillis })
                        isLoading = false
                    }
                    .addOnFailureListener {
                        reportsList.clear()
                        reportsList.addAll(combined.sortedByDescending { it.createdAtMillis })
                        isLoading = false
                    }
            } catch (e: Exception) {
                reportsList.clear()
                reportsList.addAll(combined.sortedByDescending { it.createdAtMillis })
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadAllReports()
        while (true) {
            kotlinx.coroutines.delay(10000L)
            try {
                val supabaseResult = FeedbackRepository.getAllFeedbacks()
                if (supabaseResult.isSuccess) {
                    val supaList = supabaseResult.getOrDefault(emptyList())
                    for (fb in supaList) {
                        val id = fb.id ?: "${fb.title}_${fb.createdAt}"
                        val status = FeedbackRepository.getReportStatus(context, fb)
                        val existingIdx = reportsList.indexOfFirst { it.id == id }
                        if (existingIdx != -1) {
                            if (reportsList[existingIdx].status != status || reportsList[existingIdx].adminReply != fb.adminReply) {
                                reportsList[existingIdx] = reportsList[existingIdx].copy(
                                    status = status,
                                    adminReply = if (!fb.adminReply.isNullOrBlank()) fb.adminReply else reportsList[existingIdx].adminReply
                                )
                            }
                        }
                    }
                }
            } catch (_: Exception) {}
        }
    }

    // Filtrar reportes
    val filteredReports = remember(reportsList.toList(), searchQuery, selectedFilter) {
        reportsList.filter { item ->
            val matchesFilter = when (selectedFilter) {
                "PENDING" -> item.status == FeedbackRepository.STATUS_PENDING || item.status.equals("PENDIENTE", ignoreCase = true)
                "READ" -> item.status == FeedbackRepository.STATUS_READ || item.status.equals("LEIDO", ignoreCase = true) || item.status.equals("LEÍDO", ignoreCase = true)
                "SOLVED" -> item.status == FeedbackRepository.STATUS_SOLVED || item.status.equals("SOLUCIONADO", ignoreCase = true) || item.status.equals("RESUELTO", ignoreCase = true) || item.status.equals("ACCEPTED", ignoreCase = true)
                else -> true
            }
            val matchesSearch = if (searchQuery.isBlank()) true else {
                item.title.contains(searchQuery, ignoreCase = true) ||
                item.description.contains(searchQuery, ignoreCase = true) ||
                item.userEmail.contains(searchQuery, ignoreCase = true) ||
                item.userName.contains(searchQuery, ignoreCase = true) ||
                item.device.contains(searchQuery, ignoreCase = true)
            }
            matchesFilter && matchesSearch
        }
    }

    val pendingCount = remember(reportsList.toList()) {
        reportsList.count { it.status == FeedbackRepository.STATUS_PENDING || it.status.equals("PENDIENTE", ignoreCase = true) }
    }
    val readCount = remember(reportsList.toList()) {
        reportsList.count { it.status == FeedbackRepository.STATUS_READ || it.status.equals("LEIDO", ignoreCase = true) || it.status.equals("LEÍDO", ignoreCase = true) }
    }
    val solvedCount = remember(reportsList.toList()) {
        reportsList.count { it.status == FeedbackRepository.STATUS_SOLVED || it.status.equals("SOLUCIONADO", ignoreCase = true) || it.status.equals("RESUELTO", ignoreCase = true) || it.status.equals("ACCEPTED", ignoreCase = true) }
    }

    fun updateReportStatus(report: UnifiedSupportReport, newStatus: String) {
        val idx = reportsList.indexOfFirst { it.id == report.id }
        if (idx != -1) {
            reportsList[idx] = reportsList[idx].copy(status = newStatus)
        }

        // 1. Guardar en SharedPreferences y sincronizar en Supabase
        if (report.rawSupabaseReport != null) {
            FeedbackRepository.setFeedbackStatus(context, report.rawSupabaseReport, newStatus)
            coroutineScope.launch {
                report.rawSupabaseReport.id?.let { sid ->
                    FeedbackRepository.updateFeedbackStatusInCloud(sid, newStatus)
                }
            }
        } else {
            val fakeReport = FeedbackReport(id = report.id, title = report.title, type = report.type)
            FeedbackRepository.setFeedbackStatus(context, fakeReport, newStatus)
        }

        // 2. Si es de Firestore, intentar actualizar documento
        if (report.isFirestoreDoc || report.id.isNotBlank()) {
            coroutineScope.launch {
                try {
                    val firestoreStatus = when (newStatus) {
                        FeedbackRepository.STATUS_SOLVED -> "SOLUCIONADO"
                        FeedbackRepository.STATUS_READ -> "LEIDO"
                        else -> "PENDIENTE"
                    }
                    FirebaseFirestore.getInstance()
                        .collection("support_reports")
                        .document(report.id)
                        .update("status", firestoreStatus)
                } catch (_: Exception) {}
            }
        }

        val statusLabel = when (newStatus) {
            FeedbackRepository.STATUS_SOLVED -> "Solucionado"
            FeedbackRepository.STATUS_READ -> "Leído"
            else -> "Pendiente"
        }
        Toast.makeText(context, "Estado actualizado: $statusLabel", Toast.LENGTH_SHORT).show()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(HextechDarkBg.copy(alpha = 0.95f))
                .padding(horizontal = 8.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.5.dp, HextechCyan)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp)
                ) {
                    // Encabezado Superior
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver",
                                    tint = HextechCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(HextechGold.copy(alpha = 0.15f))
                                    .border(1.dp, HextechGold, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.HeadsetMic,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Buzón de Soporte y Reportes",
                                    color = HextechGold,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$pendingCount pendiente(s) • $readCount leído(s) • $solvedCount solucionado(s)",
                                    color = if (pendingCount > 0) HextechCyan else TextMuted,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = { loadAllReports() },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    Icons.Default.Refresh,
                                    contentDescription = "Recargar",
                                    tint = HextechCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Barra de búsqueda compacta
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Buscar por título, usuario, correo o detalle...", fontSize = 11.5.sp, color = TextMuted) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(18.dp)) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(24.dp)) {
                                    Icon(Icons.Default.Close, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(15.dp))
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedContainerColor = HextechSurface,
                            unfocusedContainerColor = HextechSurface
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Chips de filtro (TODOS, PENDIENTES, LEÍDOS, SOLUCIONADOS)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            "ALL" to "Todos (${reportsList.size})",
                            "PENDING" to "Pendientes ($pendingCount)",
                            "READ" to "Leídos ($readCount)",
                            "SOLVED" to "Solucionados ($solvedCount)"
                        ).forEach { (filterKey, label) ->
                            val isSelected = selectedFilter == filterKey
                            val activeColor = when (filterKey) {
                                "PENDING" -> HextechGold
                                "READ" -> HextechCyan
                                "SOLVED" -> HextechGreen
                                else -> HextechCyan
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) activeColor.copy(alpha = 0.22f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isSelected) activeColor else HextechCardBorder,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { selectedFilter = filterKey }
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    color = if (isSelected) activeColor else TextSecondary,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Aviso de política de auto-eliminación
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurfaceVariant)
                            .border(0.8.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.HourglassBottom,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Retención activa: 30 días para mensajes leídos y 60 días para mensajes pendientes.",
                                color = HextechGold,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Registro copiable de mensajes y estadísticas (Solo Administrador)
                    if (isAdmin) {
                        val todayMillis = java.util.Calendar.getInstance().apply {
                            set(java.util.Calendar.HOUR_OF_DAY, 0)
                            set(java.util.Calendar.MINUTE, 0)
                            set(java.util.Calendar.SECOND, 0)
                            set(java.util.Calendar.MILLISECOND, 0)
                        }.timeInMillis
                        val weekMillis = todayMillis - (7L * 24 * 60 * 60 * 1000)

                        val totalRecv = reportsList.size
                        val senders = reportsList.map { it.userName.ifBlank { it.userEmail.ifBlank { "Anónimo" } } }.distinct()
                        val repliedList = reportsList.filter { it.adminReply.isNotBlank() }
                        val totalReplied = repliedList.size
                        val repliedToday = repliedList.count { it.repliedAtMillis >= todayMillis }
                        val repliedWeek = repliedList.count { it.repliedAtMillis >= weekMillis }
                        val repliers = repliedList.map { it.repliedBy.ifBlank { "Soporte Coach" } }.distinct()

                        val logText = buildString {
                            appendLine("=== REGISTRO ADMINISTRATIVO DE SOPORTE COACH ===")
                            appendLine("Total de Mensajes Recibidos: $totalRecv")
                            appendLine("Remitentes (${senders.size}): ${senders.joinToString(", ")}")
                            appendLine("Total de Mensajes Respondidos: $totalReplied")
                            appendLine("Respondidos Hoy: $repliedToday")
                            appendLine("Respondidos Esta Semana: $repliedWeek")
                            appendLine("Personal de Respuesta: ${repliers.joinToString(", ")}")
                            appendLine("----------------------------------------------")
                            reportsList.take(20).forEach { r ->
                                val rDate = try { SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(r.createdAtMillis)) } catch(_: Exception){ "" }
                                appendLine("• [$rDate] De: ${r.userName.ifBlank { r.userEmail }} | Asunto: ${r.title}")
                                if (r.adminReply.isNotBlank()) {
                                    appendLine("  -> Respondido por: ${r.repliedBy.ifBlank { "Soporte Coach" }} | Resp: ${r.adminReply}")
                                } else {
                                    appendLine("  -> Estado: Pendiente de respuesta")
                                }
                            }
                            appendLine("==============================================")
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = HextechSurface),
                            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechGold, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Registro y Estadísticas (Admin)",
                                            color = HextechGold,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            val clip = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            clip.setPrimaryClip(ClipData.newPlainText("Registro Soporte Coach", logText))
                                            Toast.makeText(context, "Registro copiado al portapapeles", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.height(28.dp),
                                        border = BorderStroke(0.8.dp, HextechGold),
                                        shape = RoundedCornerShape(6.dp),
                                        contentPadding = PaddingValues(horizontal = 8.dp)
                                    ) {
                                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechGold, modifier = Modifier.size(11.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Copiar Log", color = HextechGold, fontSize = 10.sp)
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Recibidos: $totalRecv | Respondidos: $totalReplied | Hoy: $repliedToday | Semanal: $repliedWeek",
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }

                    // Contenido: Lista de reportes
                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(color = HextechCyan, modifier = Modifier.size(36.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Cargando reportes y tickets...", color = TextMuted, fontSize = 12.sp)
                            }
                        }
                    } else if (filteredReports.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "📭 No hay reportes de soporte en esta categoría",
                                    color = TextMuted,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                if (searchQuery.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    TextButton(onClick = { searchQuery = "" }) {
                                        Text("Limpiar búsqueda", color = HextechCyan, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(filteredReports, key = { it.id }) { item ->
                                UnifiedReportAdminCard(
                                    report = item,
                                    isAdmin = isAdmin,
                                    onImageClick = { bmp -> previewZoomBitmap = bmp },
                                    onSetStatus = { newStat -> updateReportStatus(item, newStat) },
                                    onReplyClick = {
                                        initialReplyText = ""
                                        reportToReply = item
                                    },
                                    onEditReplyClick = {
                                        initialReplyText = item.adminReply
                                        reportToReply = item
                                    },
                                    onDelete = { reportToDelete = item }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal para responder al mensaje de soporte
    if (reportToReply != null) {
        val targetReport = reportToReply!!
        SupportReplyDialog(
            reportId = targetReport.id,
            reportTitle = targetReport.title,
            reportDescription = targetReport.description,
            userEmail = targetReport.userEmail,
            userName = targetReport.userName,
            initialReply = initialReplyText,
            isFirestoreDoc = targetReport.isFirestoreDoc,
            onDismiss = {
                reportToReply = null
                initialReplyText = ""
            },
            onReplySent = { replyText, markedAsRead ->
                val idx = reportsList.indexOfFirst { it.id == targetReport.id }
                if (idx != -1) {
                    val updatedStatus = if (markedAsRead) FeedbackRepository.STATUS_READ else reportsList[idx].status
                    val existing = reportsList[idx].adminReply
                    val finalReply = if (existing.isNotBlank() && initialReplyText.isBlank()) {
                        "$existing\n\n---\n\n$replyText"
                    } else {
                        replyText
                    }
                    reportsList[idx] = reportsList[idx].copy(
                        adminReply = finalReply,
                        repliedAtMillis = System.currentTimeMillis(),
                        status = updatedStatus
                    )
                }
                reportToReply = null
                initialReplyText = ""
            }
        )
    }

    // Modal de confirmación para eliminar reporte
    if (reportToDelete != null) {
        val target = reportToDelete!!
        AlertDialog(
            onDismissRequest = { reportToDelete = null },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = DangerRed, modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Eliminar reporte", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            },
            text = {
                Column {
                    Text(
                        text = "¿Estás seguro de que deseas eliminar permanentemente este reporte?",
                        color = TextPrimary,
                        fontSize = 13.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "\"${target.title}\"",
                        color = HextechCyan,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (target.userEmail.isNotBlank()) {
                        Text(
                            text = "De: ${target.userEmail}",
                            color = TextMuted,
                            fontSize = 11.sp
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val idToDelete = target.id
                        reportToDelete = null
                        reportsList.removeAll { it.id == idToDelete }
                        coroutineScope.launch {
                            // 1. Eliminar de Supabase
                            try {
                                if (target.rawSupabaseReport?.id != null) {
                                    FeedbackRepository.deleteFeedback(target.rawSupabaseReport.id)
                                } else {
                                    FeedbackRepository.deleteFeedback(idToDelete)
                                }
                            } catch (e: Exception) {
                                Log.w(TAG, "Error eliminando en Supabase: ${e.message}")
                            }

                            // 2. Eliminar de Firestore
                            try {
                                FirebaseFirestore.getInstance()
                                    .collection("support_reports")
                                    .document(idToDelete)
                                    .delete()
                            } catch (_: Exception) {}

                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Reporte eliminado permanentemente", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text("Eliminar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { reportToDelete = null }) {
                    Text("Cancelar", color = TextSecondary)
                }
            },
            containerColor = HextechSurfaceVariant
        )
    }

    // Modal de zoom para foto adjunta
    if (previewZoomBitmap != null) {
        Dialog(onDismissRequest = { previewZoomBitmap = null }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.94f))
                    .clickable { previewZoomBitmap = null }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        bitmap = previewZoomBitmap!!.asImageBitmap(),
                        contentDescription = "Foto ampliada",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, HextechGold, RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Toca la pantalla para cerrar",
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun UnifiedReportAdminCard(
    report: UnifiedSupportReport,
    isAdmin: Boolean,
    onImageClick: (Bitmap) -> Unit,
    onSetStatus: (String) -> Unit,
    onReplyClick: () -> Unit,
    onEditReplyClick: () -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current
    val isPending = report.status == FeedbackRepository.STATUS_PENDING || report.status.equals("PENDIENTE", ignoreCase = true)
    val isRead = report.status == FeedbackRepository.STATUS_READ || report.status.equals("LEIDO", ignoreCase = true) || report.status.equals("LEÍDO", ignoreCase = true)
    val isSolved = report.status == FeedbackRepository.STATUS_SOLVED || report.status.equals("SOLUCIONADO", ignoreCase = true) || report.status.equals("RESUELTO", ignoreCase = true) || report.status.equals("ACCEPTED", ignoreCase = true)

    val countdown = remember(report.createdAtMillis, isRead, isSolved) {
        SupportReplyManager.calculateCountdown(report.createdAtMillis, isRead || isSolved)
    }

    val dateStr = remember(report.createdAtMillis) {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            sdf.format(Date(report.createdAtMillis))
        } catch (e: Exception) {
            ""
        }
    }

    val borderColor = when {
        isSolved -> HextechGreen.copy(alpha = 0.6f)
        isRead -> HextechCyan.copy(alpha = 0.6f)
        else -> HextechGold.copy(alpha = 0.6f)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Fila Superior: Tipo + Estado actual + Contador auto-borrado + Fecha + Eliminar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    // Badge de Tipo
                    val typeLabel = when (report.type.uppercase()) {
                        "SOPORTE" -> "🎧 SOPORTE"
                        "BUG" -> "🐛 BUG"
                        "SUGGESTION" -> "💡 SUGERENCIA"
                        else -> "📝 ${report.type}"
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(HextechDarkBg)
                            .border(0.5.dp, HextechCyan.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = typeLabel,
                            color = HextechCyan,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Badge de Estado Actual
                    val (statusText, statusBg, statusTextColor) = when {
                        isSolved -> Triple("✓ SOLUCIONADO", HextechGreen.copy(alpha = 0.2f), HextechGreen)
                        isRead -> Triple("👁️ LEÍDO", HextechCyan.copy(alpha = 0.2f), HextechCyan)
                        else -> Triple("⏳ PENDIENTE", HextechGold.copy(alpha = 0.2f), HextechGold)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(statusBg)
                            .border(1.dp, statusTextColor, RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = statusText,
                            color = statusTextColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    // Badge de Contador de Auto-eliminación (30 días leídos, 60 días sin leer)
                    val countdownBg = when {
                        countdown.isExpired -> DangerRed.copy(alpha = 0.2f)
                        countdown.remainingDays <= 3 -> Color(0xFFFF9800).copy(alpha = 0.2f)
                        isRead || isSolved -> HextechCyan.copy(alpha = 0.15f)
                        else -> HextechGold.copy(alpha = 0.15f)
                    }
                    val countdownColor = when {
                        countdown.isExpired -> DangerRed
                        countdown.remainingDays <= 3 -> Color(0xFFFF9800)
                        isRead || isSolved -> HextechCyan
                        else -> HextechGold
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(countdownBg)
                            .border(0.8.dp, countdownColor, RoundedCornerShape(6.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.HourglassBottom,
                                contentDescription = null,
                                tint = countdownColor,
                                modifier = Modifier.size(10.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = countdown.displayText,
                                color = countdownColor,
                                fontSize = 8.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = dateStr,
                        color = TextMuted,
                        fontSize = 10.5.sp
                    )
                    if (isAdmin) {
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            onClick = onDelete,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = DangerRed.copy(alpha = 0.85f),
                                modifier = Modifier.size(17.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Título
            Text(
                text = report.title,
                color = TextPrimary,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Descripción del reporte
            Text(
                text = report.description,
                color = TextSecondary,
                fontSize = 12.sp,
                lineHeight = 16.5.sp
            )

            // Datos de contacto y dispositivo
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val emailOrName = when {
                    report.userEmail.isNotBlank() -> report.userEmail
                    report.userName.isNotBlank() -> report.userName
                    else -> "Usuario de la App"
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = emailOrName,
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1
                    )
                }

                if (report.device.isNotBlank()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f, fill = false)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Smartphone,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = report.device,
                            color = TextMuted,
                            fontSize = 10.sp,
                            maxLines = 1
                        )
                    }
                }
            }

            if (report.appVersion.isNotBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Versión: ${report.appVersion}",
                    color = TextMuted.copy(alpha = 0.7f),
                    fontSize = 9.5.sp
                )
            }

            // Fotos adjuntas
            if (report.photosBase64.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Capturas adjuntas (${report.photosBase64.size}):",
                    color = HextechGold,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(report.photosBase64) { idx, b64 ->
                        val bmp = remember(b64) {
                            try {
                                val cleanB64 = if (b64.contains(",")) b64.substringAfter(",") else b64
                                val bytes = Base64.decode(cleanB64, Base64.DEFAULT)
                                BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                            } catch (e: Exception) {
                                null
                            }
                        }
                        if (bmp != null) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                    .clickable { onImageClick(bmp) }
                            ) {
                                Image(
                                    bitmap = bmp.asImageBitmap(),
                                    contentDescription = "Foto $idx",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 🎯 BOTONES DE ACCIÓN: PENDIENTE | LEÍDO | SOLUCIONADO
            Text(
                text = "Cambiar estado del reporte:",
                color = TextMuted,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Botón Pendiente
                Button(
                    onClick = { onSetStatus(FeedbackRepository.STATUS_PENDING) },
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isPending) HextechGold.copy(alpha = 0.25f) else HextechDarkBg
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isPending) HextechGold else HextechCardBorder
                    ),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Default.HourglassEmpty,
                        contentDescription = null,
                        tint = if (isPending) HextechGold else TextMuted,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Pendiente",
                        color = if (isPending) HextechGold else TextSecondary,
                        fontSize = 10.sp,
                        fontWeight = if (isPending) FontWeight.Bold else FontWeight.Normal
                    )
                }

                // Botón Leído
                Button(
                    onClick = { onSetStatus(FeedbackRepository.STATUS_READ) },
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRead) HextechCyan.copy(alpha = 0.25f) else HextechDarkBg
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isRead) HextechCyan else HextechCardBorder
                    ),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Default.Visibility,
                        contentDescription = null,
                        tint = if (isRead) HextechCyan else TextMuted,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Leído",
                        color = if (isRead) HextechCyan else TextSecondary,
                        fontSize = 10.sp,
                        fontWeight = if (isRead) FontWeight.Bold else FontWeight.Normal
                    )
                }

                // Botón Solucionado
                Button(
                    onClick = { onSetStatus(FeedbackRepository.STATUS_SOLVED) },
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSolved) HextechGreen.copy(alpha = 0.25f) else HextechDarkBg
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isSolved) HextechGreen else HextechCardBorder
                    ),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (isSolved) HextechGreen else TextMuted,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Solucionado",
                        color = if (isSolved) HextechGreen else TextSecondary,
                        fontSize = 10.sp,
                        fontWeight = if (isSolved) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 💬 SECCIÓN DE RESPUESTA DE SOPORTE AL USUARIO
            if (report.adminReply.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechDarkBg)
                        .border(1.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.QuestionAnswer,
                                    contentDescription = null,
                                    tint = HextechCyan,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Respuesta de Soporte Coach:",
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            if (report.repliedAtMillis > 0L) {
                                val replyDateStr = remember(report.repliedAtMillis) {
                                    try {
                                        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                                        sdf.format(Date(report.repliedAtMillis))
                                    } catch (_: Exception) { "" }
                                }
                                Text(text = replyDateStr, color = TextMuted, fontSize = 9.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = report.adminReply,
                            color = TextPrimary,
                            fontSize = 11.5.sp,
                            lineHeight = 15.5.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            OutlinedButton(
                                onClick = onEditReplyClick,
                                modifier = Modifier.height(28.dp),
                                border = BorderStroke(0.8.dp, HextechCyan),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Editar", color = HextechCyan, fontSize = 10.sp)
                            }
                            OutlinedButton(
                                onClick = {
                                    val clip = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clip.setPrimaryClip(ClipData.newPlainText("Respuesta Soporte", report.adminReply))
                                    Toast.makeText(context, "Respuesta copiada", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.height(28.dp),
                                border = BorderStroke(0.8.dp, HextechCardBorder),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp)
                            ) {
                                Icon(Icons.Default.ContentCopy, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Copiar", color = TextSecondary, fontSize = 10.sp)
                            }
                            if (report.userEmail.isNotBlank()) {
                                OutlinedButton(
                                    onClick = {
                                        try {
                                            val intent = SupportReplyManager.createEmailReplyIntent(
                                                email = report.userEmail,
                                                title = report.title,
                                                replyText = report.adminReply
                                            )
                                            context.startActivity(intent)
                                        } catch (_: Exception) {
                                            Toast.makeText(context, "No hay aplicación de correo disponible", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    modifier = Modifier.height(28.dp),
                                    border = BorderStroke(0.8.dp, HextechCyan.copy(alpha = 0.5f)),
                                    shape = RoundedCornerShape(6.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp)
                                ) {
                                    Icon(Icons.Default.Email, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Reenviar Correo", color = HextechCyan, fontSize = 10.sp)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedButton(
                            onClick = onReplyClick,
                            modifier = Modifier.fillMaxWidth().height(32.dp),
                            border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f)),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan)
                        ) {
                            Icon(Icons.Default.Reply, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Seguir Respondiendo / Nueva Réplica", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            } else {
                Button(
                    onClick = onReplyClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha = 0.18f)),
                    border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    Icon(Icons.Default.Reply, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Responder Mensaje de Soporte",
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

private fun parseIsoDateToMillis(dateStr: String?): Long {
    if (dateStr.isNullOrBlank()) return System.currentTimeMillis()
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val clean = dateStr.substringBefore(".").substringBefore("+").substringBefore("Z")
        sdf.parse(clean)?.time ?: System.currentTimeMillis()
    } catch (_: Exception) {
        System.currentTimeMillis()
    }
}
