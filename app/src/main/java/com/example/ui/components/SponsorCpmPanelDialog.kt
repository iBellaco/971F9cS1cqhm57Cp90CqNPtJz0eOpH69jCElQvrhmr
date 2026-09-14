package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.AppNotice
import com.example.data.AppNoticeManager
import com.example.ui.theme.*
import com.example.util.NoticeMediaStorageManager
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
    val coroutineScope = rememberCoroutineScope()

    val allNotices by AppNoticeManager.notices.collectAsState()
    val currentBlueEssence by com.example.util.SubscriptionManager.blueEssence.collectAsState()
    var showBuyEssenceDialog by remember { mutableStateOf(false) }

    if (showBuyEssenceDialog) {
        BuyEssenceDialog(isAdmin = false, onDismiss = { showBuyEssenceDialog = false })
    }
    
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
                            videoUrl = obj.optString("videoUrl", ""),
                            expandedImageUrl = obj.optString("expandedImageUrl", ""),
                            externalUrl = obj.optString("externalUrl", ""),
                            tag = obj.optString("tag", "Publicidad"),
                            budget = obj.optDouble("budget", 0.0),
                            budgetUnit = obj.optString("budgetUnit", "day"),
                            durationValue = obj.optInt("durationValue", 1),
                            durationUnit = obj.optString("durationUnit", "day"),
                            approvedAtMillis = obj.optLong("approvedAtMillis", 0L),
                            expiresAtMillis = obj.optLong("expiresAtMillis", 0L),
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

    val now = System.currentTimeMillis()
    val sevenDaysMillis = 7 * 24 * 60 * 60 * 1000L

    val myNotices = remember(allNotices, userEmail, localPendingAds, now) {
        val remoteAds = allNotices.filter { it.sponsorEmail.equals(userEmail, true) || (it.sponsorEmail.isBlank() && it.tag.equals("Publicidad", true)) }
        val localFiltered = localPendingAds.filter { it.sponsorEmail.equals(userEmail, true) || it.sponsorEmail.isBlank() }
        val remoteAdIds = remoteAds.map { it.id }.toSet()
        val combined = remoteAds + localFiltered.filter { it.id !in remoteAdIds }
        
        // Conservar visibles durante 7 días después de haber expirado con contador regresivo de eliminación
        combined.filter { notice ->
            if (notice.expiresAtMillis > 0L) {
                val isExpired = now >= notice.expiresAtMillis
                if (isExpired) {
                    val elapsedSinceExp = now - notice.expiresAtMillis
                    elapsedSinceExp <= sevenDaysMillis // Retener durante 7 días
                } else true
            } else true
        }
    }

    var showCreateDialog by remember { mutableStateOf(false) }
    var showConfirmReviewDialog by remember { mutableStateOf(false) }
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }
    var horizontalMediaInput by remember { mutableStateOf("") } // Banner horizontal o video horizontal
    var verticalMediaInput by remember { mutableStateOf("") } // Media vertical para pantalla completa
    var externalUrlInput by remember { mutableStateOf("") }
    var titleColor by remember { mutableStateOf("#FFD700") }
    var durationValueInput by remember { mutableStateOf("1") }
    var selectedDurationUnit by remember { mutableStateOf("day") } // "hour", "day", "week", "month" (sin opción de 1 año)
    var isUploadingMedia by remember { mutableStateOf(false) }

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
            else -> 10.00
        }
        val total = unitPrice * durationValueInt
        String.format(Locale.US, "%.2f", total)
    }
    var budgetInput by remember { mutableStateOf("10.00") }
    LaunchedEffect(autoBudget) { budgetInput = autoBudget }

    // Launcher para seleccionar multimedia horizontal (imágenes solo PNG, videos solo MP4 máx 10s, máx 10MB)
    val horizontalPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        val mimeType = context.contentResolver.getType(uri) ?: ""
        val path = uri.toString().lowercase()
        val isVideo = NoticeMediaStorageManager.isUriVideo(context, uri) || mimeType.startsWith("video")

        if (isVideo) {
            val isMp4 = mimeType.equals("video/mp4", true) || path.endsWith(".mp4")
            if (!isMp4) {
                Toast.makeText(context, "Los videos deben estar estrictamente en formato MP4", Toast.LENGTH_LONG).show()
                return@rememberLauncherForActivityResult
            }
            try {
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(context, uri)
                val durStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                val durMs = durStr?.toLongOrNull() ?: 0L
                retriever.release()
                if (durMs > 10_500L) {
                    Toast.makeText(context, "El video no puede superar los 10 segundos de duración", Toast.LENGTH_LONG).show()
                    return@rememberLauncherForActivityResult
                }
            } catch (_: Exception) {}
        } else {
            val isPng = mimeType.equals("image/png", true) || path.endsWith(".png")
            if (!isPng) {
                Toast.makeText(context, "Las imágenes de galería deben estar estrictamente en formato PNG", Toast.LENGTH_LONG).show()
                return@rememberLauncherForActivityResult
            }
            try {
                val pfd = context.contentResolver.openFileDescriptor(uri, "r")
                val size = pfd?.statSize ?: 0L
                pfd?.close()
                if (size > 10 * 1024 * 1024L) {
                    Toast.makeText(context, "La imagen no debe superar los 10MB", Toast.LENGTH_LONG).show()
                    return@rememberLauncherForActivityResult
                }
            } catch (_: Exception) {}
        }

        isUploadingMedia = true
        coroutineScope.launch {
            val result = if (isVideo) {
                NoticeMediaStorageManager.uploadOrSaveVideo(context, uri)
            } else {
                NoticeMediaStorageManager.convertImageToCloudDataUrl(context, uri)
            }
            horizontalMediaInput = result
            isUploadingMedia = false
            Toast.makeText(context, "Multimedia horizontal cargada (PNG/MP4)", Toast.LENGTH_SHORT).show()
        }
    }

    // Launcher para seleccionar multimedia vertical (imágenes solo PNG, videos solo MP4 máx 10s, máx 10MB)
    val verticalPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        val mimeType = context.contentResolver.getType(uri) ?: ""
        val path = uri.toString().lowercase()
        val isVideo = NoticeMediaStorageManager.isUriVideo(context, uri) || mimeType.startsWith("video")

        if (isVideo) {
            val isMp4 = mimeType.equals("video/mp4", true) || path.endsWith(".mp4")
            if (!isMp4) {
                Toast.makeText(context, "Los videos deben estar estrictamente en formato MP4", Toast.LENGTH_LONG).show()
                return@rememberLauncherForActivityResult
            }
            try {
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(context, uri)
                val durStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                val durMs = durStr?.toLongOrNull() ?: 0L
                retriever.release()
                if (durMs > 10_500L) {
                    Toast.makeText(context, "El video no puede superar los 10 segundos de duración", Toast.LENGTH_LONG).show()
                    return@rememberLauncherForActivityResult
                }
            } catch (_: Exception) {}
        } else {
            val isPng = mimeType.equals("image/png", true) || path.endsWith(".png")
            if (!isPng) {
                Toast.makeText(context, "Las imágenes de galería deben estar estrictamente en formato PNG", Toast.LENGTH_LONG).show()
                return@rememberLauncherForActivityResult
            }
            try {
                val pfd = context.contentResolver.openFileDescriptor(uri, "r")
                val size = pfd?.statSize ?: 0L
                pfd?.close()
                if (size > 10 * 1024 * 1024L) {
                    Toast.makeText(context, "La imagen no debe superar los 10MB", Toast.LENGTH_LONG).show()
                    return@rememberLauncherForActivityResult
                }
            } catch (_: Exception) {}
        }

        isUploadingMedia = true
        coroutineScope.launch {
            val result = if (isVideo) {
                NoticeMediaStorageManager.uploadOrSaveVideo(context, uri)
            } else {
                NoticeMediaStorageManager.convertImageToCloudDataUrl(context, uri)
            }
            verticalMediaInput = result
            isUploadingMedia = false
            Toast.makeText(context, "Multimedia vertical cargada (PNG/MP4)", Toast.LENGTH_SHORT).show()
        }
    }

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
                            horizontalMediaInput = ""
                            verticalMediaInput = ""
                            externalUrlInput = ""
                            budgetInput = "10.00"
                            selectedDurationUnit = "day"
                            durationValueInput = "1"
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
                                    obj.put("videoUrl", n.videoUrl)
                                    obj.put("expandedImageUrl", n.expandedImageUrl)
                                    obj.put("externalUrl", n.externalUrl)
                                    obj.put("tag", n.tag)
                                    obj.put("budget", n.budget)
                                    obj.put("budgetUnit", n.budgetUnit)
                                    obj.put("durationValue", n.durationValue)
                                    obj.put("durationUnit", n.durationUnit)
                                    obj.put("approvedAtMillis", n.approvedAtMillis)
                                    obj.put("expiresAtMillis", n.expiresAtMillis)
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
        val quantityLabel = when (selectedDurationUnit) {
            "hour" -> "Cantidad de horas"
            "day" -> "Cantidad de días"
            "week" -> "Cantidad de semanas"
            "month" -> "Cantidad de meses"
            else -> "Cantidad"
        }

        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            containerColor = HextechSurface,
            title = { Text("Publicar Anuncio CPM", color = HextechGold, fontWeight = FontWeight.Bold) },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Título Obligatorio
                    OutlinedTextField(
                        value = titleInput,
                        onValueChange = { titleInput = it },
                        label = { Text("Título del Anuncio * (Obligatorio)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold, 
                            unfocusedBorderColor = HextechSurfaceVariant,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    // Opción de Color del Título
                    Text("Color del Título:", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val colorsList = listOf(
                            "Dorado" to "#FFD700",
                            "Cian" to "#00F2FE",
                            "Blanco" to "#FFFFFF",
                            "Verde" to "#00FF66",
                            "Naranja" to "#FF9900",
                            "Rojo" to "#FF3333",
                            "Morado" to "#CC66FF"
                        )
                        colorsList.forEach { (name, hex) ->
                            val isSelected = titleColor.equals(hex, true)
                            val parsedColor = try { Color(android.graphics.Color.parseColor(hex)) } catch (_: Exception) { HextechGold }
                            Button(
                                onClick = { titleColor = hex },
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) parsedColor else HextechSurfaceVariant
                                ),
                                border = BorderStroke(1.dp, parsedColor)
                            ) {
                                Text(name, color = if (isSelected) HextechDarkBg else parsedColor, fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Multimedia Horizontal (Banner/Video horizontal para inicio)
                    Text("1. Multimedia Horizontal (Banner de Inicio):", color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    Text("• Medidas recomendadas: 1920 x 1080 px (Relación 16:9)\n• Límite: Máximo 10 MB (Imagen o Video máx 10s)", color = TextSecondary, fontSize = 10.sp)
                    
                    Button(
                        onClick = { horizontalPicker.launch("*/*") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, HextechGold)
                    ) {
                        Icon(Icons.Default.PhotoLibrary, contentDescription = null, tint = HextechGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (horizontalMediaInput.isBlank()) "Seleccionar desde Galería (Horizontal)" else "Cambiar Multimedia Horizontal", color = Color.White, fontSize = 12.sp)
                    }

                    // Preview Horizontal
                    if (horizontalMediaInput.isNotBlank()) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = HextechDarkBg,
                            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.8f))
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                if (horizontalMediaInput.endsWith(".mp4", true) || horizontalMediaInput.contains("video", true)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Videocam, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(24.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Video Horizontal Seleccionado", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    }
                                } else {
                                    AsyncImage(
                                        model = horizontalMediaInput,
                                        contentDescription = "Vista previa horizontal",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Multimedia Vertical (Imagen/Video vertical para modal pantalla completa)
                    Text("2. Multimedia Vertical (Vista Ampliada):", color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    Text("• Medidas recomendadas: 1080 x 1920 px (Relación 9:16)\n• Límite: Máximo 10 MB (Imagen o Video máx 10s)", color = TextSecondary, fontSize = 10.sp)

                    Button(
                        onClick = { verticalPicker.launch("*/*") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, HextechCyan)
                    ) {
                        Icon(Icons.Default.VideoLibrary, contentDescription = null, tint = HextechCyan)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (verticalMediaInput.isBlank()) "Seleccionar desde Galería (Vertical)" else "Cambiar Multimedia Vertical", color = Color.White, fontSize = 12.sp)
                    }

                    // Preview Vertical
                    if (verticalMediaInput.isNotBlank()) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = HextechDarkBg,
                            border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.8f))
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                if (verticalMediaInput.endsWith(".mp4", true) || verticalMediaInput.contains("video", true)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Videocam, contentDescription = null, tint = HextechGold, modifier = Modifier.size(24.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Video Vertical Seleccionado", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    }
                                } else {
                                    AsyncImage(
                                        model = verticalMediaInput,
                                        contentDescription = "Vista previa vertical",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }

                    // Enlace Web Externo (OPCIONAL)
                    OutlinedTextField(
                        value = externalUrlInput,
                        onValueChange = { externalUrlInput = it },
                        label = { Text("Enlace Web Externo (Opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold, 
                            unfocusedBorderColor = HextechSurfaceVariant,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    // Presupuesto Calculado
                    OutlinedTextField(
                        value = "$autoBudget USD",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Presupuesto Total (USD)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold, 
                            unfocusedBorderColor = HextechSurfaceVariant,
                            disabledTextColor = HextechCyan,
                            focusedTextColor = HextechCyan,
                            unfocusedTextColor = HextechCyan
                        )
                    )

                    Text("Duración de la Publicación:", color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    
                    // Cantidad Dinámica según Unidad
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = durationValueInput,
                            onValueChange = { if (it.all { char -> char.isDigit() }) durationValueInput = it.take(3) },
                            label = { Text(quantityLabel) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = HextechGold, 
                                unfocusedBorderColor = HextechSurfaceVariant,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )
                    }

                    // Selector de Unidades (Sin opción de 1 año)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            "hour" to "Horas",
                            "day" to "Días",
                            "week" to "Semanas",
                            "month" to "Meses"
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

                    Spacer(modifier = Modifier.height(8.dp))
                    val parsedBudgetVal = autoBudget.replace(',', '.').toDoubleOrNull() ?: 10.0
                    val requiredEssences = (parsedBudgetVal * 10).toLong()
                    val hasEnoughEssence = currentBlueEssence >= requiredEssences

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = HextechSurface,
                        border = BorderStroke(1.dp, if (hasEnoughEssence) HextechGold.copy(alpha = 0.5f) else Color(0xFFEF4444))
                    ) {
                        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text("Costo en Esencias Azules ($1 = 10 EA):", color = TextSecondary, fontSize = 11.sp)
                                Text("$requiredEssences EA", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text("Tu Saldo Actual:", color = TextSecondary, fontSize = 11.sp)
                                Text("$currentBlueEssence EA", color = if (hasEnoughEssence) Color(0xFF10B981) else Color(0xFFEF4444), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                            if (!hasEnoughEssence) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Button(
                                    onClick = { showBuyEssenceDialog = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(vertical = 4.dp)
                                ) {
                                    Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Recargar Esencias (Insuficientes)", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Nota: El anuncio requiere la aprobación de un administrador para ser visible en la plataforma. Al vencer permanecerá 7 días en tu historial con contador antes de su eliminación.", color = TextSecondary, fontSize = 10.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsedBudget = autoBudget.replace(',', '.').toDoubleOrNull() ?: 10.0
                        val requiredEssences = (parsedBudget * 10).toLong()
                        if (titleInput.trim().isBlank()) {
                            Toast.makeText(context, "El título del anuncio es obligatorio", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        if (currentBlueEssence < requiredEssences) {
                            Toast.makeText(context, "No tienes suficientes esencias azules ($requiredEssences EA requeridas). Recarga para publicar.", Toast.LENGTH_LONG).show()
                            showBuyEssenceDialog = true
                            return@Button
                        }
                        showConfirmReviewDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    enabled = !isUploadingMedia
                ) {
                    if (isUploadingMedia) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = HextechDarkBg, strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                    }
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

    if (showConfirmReviewDialog) {
        val parsedBudget = autoBudget.replace(',', '.').toDoubleOrNull() ?: 10.0
        val requiredEssences = (parsedBudget * 10).toLong()

        AlertDialog(
            onDismissRequest = { showConfirmReviewDialog = false },
            containerColor = HextechSurface,
            title = { Text("⚠️ Advertencia de Envío a Revisión", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "• Una vez enviado el anuncio a revisión, NO SE PUEDE MODIFICAR.\n" +
                        "• NO EXISTEN DEVOLUCIONES de esencias azules bajo ninguna circunstancia.\n" +
                        "• Formatos estrictos: Imágenes en formato PNG y Videos en formato MP4 (máximo 10 segundos).\n" +
                        "• Regla de Seguridad y Enlaces: Está estrictamente prohibido agregar enlaces maliciosos, contenido inapropiado o incumplir cualquiera de las normas.\n" +
                        "• Penalización: Si se infringe cualquier regla, el anuncio será rechazado permanentemente y se perderán todas las esencias azules invertidas sin derecho a reclamo ni apelación.",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text("¿Estás completamente seguro de enviar el anuncio?", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmReviewDialog = false
                        showCreateDialog = false

                        val newPendingNotice = AppNotice(
                            id = UUID.randomUUID().toString(),
                            title = titleInput.trim(),
                            content = contentInput.trim(),
                            videoUrl = horizontalMediaInput.trim(),
                            expandedImageUrl = verticalMediaInput.trim().ifBlank { horizontalMediaInput.trim() },
                            externalUrl = externalUrlInput.trim(),
                            tag = "Publicidad",
                            titleColor = titleColor,
                            budget = parsedBudget,
                            budgetUnit = selectedDurationUnit,
                            durationValue = durationValueInt,
                            durationUnit = selectedDurationUnit,
                            isApproved = false,
                            isEnabled = false,
                            sponsorEmail = userEmail
                        )

                        val updatedLocalList = localPendingAds + newPendingNotice
                        localPendingAds = updatedLocalList
                        AppNoticeManager.submitPendingSponsorNotice(context, newPendingNotice)
                        
                        val jsonArray = org.json.JSONArray()
                        updatedLocalList.forEach { n ->
                            val obj = org.json.JSONObject()
                            obj.put("id", n.id)
                            obj.put("title", n.title)
                            obj.put("content", n.content)
                            obj.put("videoUrl", n.videoUrl)
                            obj.put("expandedImageUrl", n.expandedImageUrl)
                            obj.put("externalUrl", n.externalUrl)
                            obj.put("tag", n.tag)
                            obj.put("titleColor", n.titleColor)
                            obj.put("budget", n.budget)
                            obj.put("budgetUnit", n.budgetUnit)
                            obj.put("durationValue", n.durationValue)
                            obj.put("durationUnit", n.durationUnit)
                            obj.put("approvedAtMillis", n.approvedAtMillis)
                            obj.put("expiresAtMillis", n.expiresAtMillis)
                            obj.put("isApproved", n.isApproved)
                            obj.put("isEnabled", n.isEnabled)
                            obj.put("sponsorEmail", n.sponsorEmail)
                            jsonArray.put(obj)
                        }
                        prefs.edit().putString("pending_ads", jsonArray.toString()).apply()
                        coroutineScope.launch {
                            com.example.util.SubscriptionManager.addBlueEssence(-requiredEssences)
                        }

                        Toast.makeText(context, "Anuncio enviado a revisión. Se descontaron $requiredEssences EA.", Toast.LENGTH_LONG).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444))
                ) {
                    Text("Aceptar y Enviar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmReviewDialog = false }) {
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
    val context = LocalContext.current
    val now = System.currentTimeMillis()
    val isExpired = notice.expiresAtMillis > 0L && now >= notice.expiresAtMillis

    val statusText = when {
        isExpired -> "Finalizado"
        notice.isApproved -> "Aprobado y Activo"
        else -> "Pendiente de Aprobación"
    }
    val statusColor = when {
        isExpired -> Color(0xFFEF4444)
        notice.isApproved -> Color(0xFF10B981)
        else -> Color(0xFFF59E0B)
    }

    val metricsMap by com.example.data.AppNoticeAnalyticsManager.metricsMap.collectAsState()
    val metrics = metricsMap[notice.id] ?: com.example.data.NoticeMetrics(notice.id)
    val ctr = if (metrics.impressions > 0) (metrics.clicks.toDouble() / metrics.impressions) * 100 else 0.0

    val unitLabel = when (notice.durationUnit.lowercase(Locale.ROOT)) {
        "hour", "hours", "hora", "horas" -> "Horas"
        "day", "days", "dia", "dias", "día", "días" -> "Días"
        "week", "weeks", "semana", "semanas" -> "Semanas"
        "month", "months", "mes", "meses" -> "Meses"
        else -> notice.durationUnit
    }

    // Cálculo de eliminación automática en 7 días para anuncios finalizados
    val deletionNoticeStr = if (isExpired && notice.expiresAtMillis > 0L) {
        val remainingDeletionMillis = (notice.expiresAtMillis + 7 * 24 * 60 * 60 * 1000L) - now
        if (remainingDeletionMillis > 0) {
            val totalHours = remainingDeletionMillis / (1000 * 60 * 60)
            val days = totalHours / 24
            val hours = totalHours % 24
            if (days > 0) "Se eliminará del historial en ${days}d ${hours}h"
            else "Se eliminará del historial en ${hours}h"
        } else {
            "Programado para eliminación"
        }
    } else if (notice.expiresAtMillis > 0L) {
        val diff = notice.expiresAtMillis - now
        val hours = diff / (1000 * 60 * 60)
        val days = hours / 24
        if (days > 0) "Expira en: ${days}d ${hours % 24}h"
        else "Expira en: ${hours}h ${(diff / (1000 * 60)) % 60}m"
    } else null

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

            if (notice.content.isNotBlank()) {
                Text(notice.content, color = TextSecondary, fontSize = 12.sp, maxLines = 2, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
            }

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
                        Text(String.format(Locale.US, "%.1f%%", ctr), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Presupuesto: $${String.format(Locale.US, "%.2f", notice.budget)} USD", color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    Text("Duración: ${notice.durationValue} $unitLabel", color = TextSecondary, fontSize = 10.sp)
                    if (deletionNoticeStr != null) {
                        Text(deletionNoticeStr, color = if (isExpired) DangerRed else HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    // Botón para copiar estadísticas
                    IconButton(
                        onClick = {
                            val statsText = """
📊 Estadísticas del Anuncio:
• Título: ${notice.title}
• Estado: $statusText
• Vistas (Impresiones): ${metrics.impressions}
• Clics: ${metrics.clicks}
• CTR: ${String.format(Locale.US, "%.1f%%", ctr)}
• Presupuesto: $${String.format(Locale.US, "%.2f", notice.budget)} USD
• Duración: ${notice.durationValue} $unitLabel
${if (deletionNoticeStr != null) "• Estado de tiempo: $deletionNoticeStr" else ""}
                            """.trimIndent()
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                            val clip = ClipData.newPlainText("Estadísticas de Anuncio", statsText)
                            clipboard?.setPrimaryClip(clip)
                            Toast.makeText(context, "Estadísticas copiadas al portapapeles", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copiar estadísticas", tint = HextechCyan, modifier = Modifier.size(17.dp))
                    }

                    IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = DangerRed, modifier = Modifier.size(17.dp))
                    }
                }
            }
        }
    }
}
