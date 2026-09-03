package com.example.ui.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

data class SupportReportItem(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val userId: String = "",
    val userEmail: String = "",
    val userName: String = "",
    val photos: List<String> = emptyList(),
    val status: String = "PENDIENTE",
    val appVersion: String = "",
    val device: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Composable
fun AdminSupportReportsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val reports = remember { mutableStateListOf<SupportReportItem>() }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("TODOS") } // "TODOS", "PENDIENTE", "RESUELTO"
    var previewZoomBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var reportToDelete by remember { mutableStateOf<SupportReportItem?>(null) }

    // Listener en tiempo real a Firestore collection support_reports
    DisposableEffect(Unit) {
        val listener = FirebaseFirestore.getInstance()
            .collection("support_reports")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                isLoading = false
                if (error != null) {
                    Toast.makeText(context, "Error cargando reportes: ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    reports.clear()
                    for (doc in snapshot.documents) {
                        val id = doc.id
                        val title = doc.getString("title") ?: "Sin título"
                        val description = doc.getString("description") ?: ""
                        val userId = doc.getString("userId") ?: ""
                        val userEmail = doc.getString("userEmail") ?: ""
                        val userName = doc.getString("userName") ?: ""
                        @Suppress("UNCHECKED_CAST")
                        val photos = (doc.get("photos") as? List<String>) ?: emptyList()
                        val status = doc.getString("status") ?: "PENDIENTE"
                        val appVersion = doc.getString("appVersion") ?: ""
                        val device = doc.getString("device") ?: ""
                        val ts = doc.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis()

                        reports.add(
                            SupportReportItem(
                                id = id,
                                title = title,
                                description = description,
                                userId = userId,
                                userEmail = userEmail,
                                userName = userName,
                                photos = photos,
                                status = status,
                                appVersion = appVersion,
                                device = device,
                                createdAt = ts
                            )
                        )
                    }
                }
            }

        onDispose {
            listener.remove()
        }
    }

    val filteredReports = remember(reports.toList(), searchQuery, selectedFilter) {
        reports.filter { item ->
            val matchesFilter = when (selectedFilter) {
                "PENDIENTE" -> item.status.equals("PENDIENTE", ignoreCase = true)
                "RESUELTO" -> item.status.equals("RESUELTO", ignoreCase = true)
                else -> true
            }
            val matchesSearch = if (searchQuery.isBlank()) true else {
                item.title.contains(searchQuery, ignoreCase = true) ||
                item.description.contains(searchQuery, ignoreCase = true) ||
                item.userEmail.contains(searchQuery, ignoreCase = true) ||
                item.userName.contains(searchQuery, ignoreCase = true)
            }
            matchesFilter && matchesSearch
        }
    }

    val pendingCount = remember(reports.toList()) {
        reports.count { it.status.equals("PENDIENTE", ignoreCase = true) }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(HextechDarkBg.copy(alpha = 0.92f))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
                border = BorderStroke(1.5.dp, HextechCyan)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Encabezado
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.HeadsetMic,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Buzón de Soporte y Reportes",
                                    color = HextechGold,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$pendingCount reporte(s) pendiente(s) de revisión",
                                    color = if (pendingCount > 0) HextechCyan else TextMuted,
                                    fontSize = 11.5.sp
                                )
                            }
                        }

                        IconButton(onClick = onDismiss, modifier = Modifier.size(30.dp)) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Barra de búsqueda compacta
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Buscar por título, contenido o usuario...", fontSize = 12.sp, color = TextMuted) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(18.dp)) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Close, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(16.dp))
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedContainerColor = HextechSurface,
                            unfocusedContainerColor = HextechSurface
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Chips de filtro (TODOS, PENDIENTES, RESUELTOS)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "TODOS" to "Todos (${reports.size})",
                            "PENDIENTE" to "Pendientes ($pendingCount)",
                            "RESUELTO" to "Resueltos (${reports.size - pendingCount})"
                        ).forEach { (filterKey, label) ->
                            val isSelected = selectedFilter == filterKey
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) HextechCyan.copy(alpha = 0.25f) else HextechSurface)
                                    .border(
                                        1.dp,
                                        if (isSelected) HextechCyan else HextechCardBorder,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { selectedFilter = filterKey }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = label,
                                    color = if (isSelected) HextechCyan else TextSecondary,
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Contenido: Lista de reportes
                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = HextechCyan)
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
                                    fontSize = 13.sp
                                )
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(filteredReports, key = { it.id }) { item ->
                                SupportReportAdminCard(
                                    report = item,
                                    onImageClick = { bmp -> previewZoomBitmap = bmp },
                                    onToggleStatus = {
                                        val newStatus = if (item.status == "PENDIENTE") "RESUELTO" else "PENDIENTE"
                                        coroutineScope.launch {
                                            FirebaseFirestore.getInstance()
                                                .collection("support_reports")
                                                .document(item.id)
                                                .update("status", newStatus)
                                            Toast.makeText(context, "Estado actualizado a $newStatus", Toast.LENGTH_SHORT).show()
                                        }
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

    // Modal de confirmación para eliminar reporte
    if (reportToDelete != null) {
        AlertDialog(
            onDismissRequest = { reportToDelete = null },
            title = { Text("Eliminar reporte", color = HextechGold, fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "¿Estás seguro de que deseas eliminar permanentemente el reporte \"${reportToDelete!!.title}\"?",
                    color = TextPrimary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val id = reportToDelete!!.id
                        reportToDelete = null
                        coroutineScope.launch {
                            FirebaseFirestore.getInstance()
                                .collection("support_reports")
                                .document(id)
                                .delete()
                            Toast.makeText(context, "Reporte eliminado", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text("Eliminar", color = Color.White)
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
                    .background(Color.Black.copy(alpha = 0.92f))
                    .clickable { previewZoomBitmap = null }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    bitmap = previewZoomBitmap!!.asImageBitmap(),
                    contentDescription = "Foto ampliada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

@Composable
private fun SupportReportAdminCard(
    report: SupportReportItem,
    onImageClick: (Bitmap) -> Unit,
    onToggleStatus: () -> Unit,
    onDelete: () -> Unit
) {
    val isPending = report.status.equals("PENDIENTE", ignoreCase = true)
    val dateStr = remember(report.createdAt) {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            sdf.format(report.createdAt)
        } catch (e: Exception) {
            ""
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(
            1.dp,
            if (isPending) HextechGold.copy(alpha = 0.6f) else HextechGreen.copy(alpha = 0.4f)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Fila superior: Estado + Título + Acciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Badge de Estado
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isPending) HextechGold.copy(alpha = 0.2f) else HextechGreen.copy(alpha = 0.2f))
                        .border(
                            1.dp,
                            if (isPending) HextechGold else HextechGreen,
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = if (isPending) "⏳ PENDIENTE" else "✓ RESUELTO",
                        color = if (isPending) HextechGold else HextechGreen,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = dateStr,
                        color = TextMuted,
                        fontSize = 10.5.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    IconButton(onClick = onDelete, modifier = Modifier.size(26.dp)) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = DangerRed.copy(alpha = 0.8f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Título
            Text(
                text = report.title,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Descripción
            Text(
                text = report.description,
                color = TextSecondary,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            // Datos del usuario remitente y dispositivo
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (report.userEmail.isNotBlank()) report.userEmail else report.userName,
                        color = HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                if (report.device.isNotBlank()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
                            fontSize = 10.5.sp
                        )
                    }
                }
            }

            // Fotos adjuntas si tiene
            if (report.photos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Fotos adjuntas (${report.photos.size}):",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(report.photos) { idx, b64 ->
                        val bmp = remember(b64) {
                            try {
                                val bytes = Base64.decode(b64, Base64.DEFAULT)
                                BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                            } catch (e: Exception) {
                                null
                            }
                        }
                        if (bmp != null) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
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

            // Botón de alternar estado
            Button(
                onClick = onToggleStatus,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPending) HextechGreen.copy(alpha = 0.2f) else HextechGold.copy(alpha = 0.2f)
                ),
                border = BorderStroke(1.dp, if (isPending) HextechGreen else HextechGold),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = if (isPending) Icons.Default.CheckCircle else Icons.Default.HourglassEmpty,
                    contentDescription = null,
                    tint = if (isPending) HextechGreen else HextechGold,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isPending) "Marcar como Resuelto" else "Reabrir como Pendiente",
                    color = if (isPending) HextechGreen else HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
