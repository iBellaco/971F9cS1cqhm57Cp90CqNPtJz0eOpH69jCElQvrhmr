package com.example.ui.components.admin

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*
import com.example.util.ImageStorageHelper
import com.example.util.tr
import kotlinx.coroutines.launch

@Composable
fun AdminImagePickerUploader(
    label: String,
    imageUrl: String,
    onImageUrlChange: (String) -> Unit,
    imagePrefix: String = "asset",
    accentColor: Color = HextechGold,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isProcessing by remember { mutableStateOf(false) }
    var uploadStatusText by remember { mutableStateOf<String?>(null) }

    // Selector de galería / archivos del sistema Android
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            isProcessing = true
            uploadStatusText = "Procesando y sincronizando imagen..."
            scope.launch {
                val result = ImageStorageHelper.saveImageFromUri(
                    context = context,
                    uri = uri,
                    prefix = imagePrefix,
                    uploadToCloud = true
                )
                isProcessing = false
                if (result.isSuccess) {
                    val saveResult = result.getOrNull()
                    val savedUrl = saveResult?.url.orEmpty()
                    onImageUrlChange(savedUrl)
                    if (saveResult?.isCloudUrl == true) {
                        uploadStatusText = "☁️ Subido a Supabase Storage con éxito"
                        Toast.makeText(context, "¡Imagen subida a Supabase Storage!", Toast.LENGTH_SHORT).show()
                    } else {
                        uploadStatusText = "💾 Guardado en almacenamiento local"
                        Toast.makeText(context, "¡Imagen guardada localmente!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorMsg = result.exceptionOrNull()?.message ?: "Error desconocido"
                    uploadStatusText = "⚠️ Error: $errorMsg"
                    Toast.makeText(context, "Error: $errorMsg", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(HextechSurface, RoundedCornerShape(10.dp))
            .border(1.dp, HextechCardBorder, RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = accentColor,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            if (imageUrl.isNotBlank()) {
                TextButton(
                    onClick = { 
                        onImageUrlChange("")
                        uploadStatusText = null
                    },
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                    modifier = Modifier.height(26.dp)
                ) {
                    Icon(Icons.Default.Clear, contentDescription = null, tint = DangerRed, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tr("Quitar"), color = DangerRed, fontSize = 10.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Preview en vivo
            AppAssetImage(
                url = imageUrl.trim(),
                contentDescription = label,
                fallbackText = "IMG",
                modifier = Modifier.size(54.dp),
                borderColor = accentColor,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Botón para subir desde galería
                Button(
                    onClick = { photoPickerLauncher.launch("image/*") },
                    enabled = !isProcessing,
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isProcessing) {
                        CircularProgressIndicator(
                            color = HextechDarkBg,
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(tr("Sincronizando..."), color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                    } else {
                        Icon(
                            Icons.Default.CloudUpload,
                            contentDescription = null,
                            tint = HextechDarkBg,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("📁 Subir Imagen (Cloud / Galería)"),
                            color = HextechDarkBg,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val isCloud = imageUrl.contains("supabase.co")
                    val isLocal = imageUrl.startsWith("file://")
                    val isWeb = imageUrl.startsWith("http") && !isCloud

                    val statusIcon = when {
                        isCloud -> Icons.Default.CloudDone
                        isLocal -> Icons.Default.PhoneAndroid
                        else -> Icons.Default.Link
                    }
                    val statusTint = when {
                        isCloud -> HextechCyan
                        isLocal -> HextechGold
                        else -> TextMuted
                    }

                    Icon(statusIcon, contentDescription = null, tint = statusTint, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = when {
                            uploadStatusText != null -> uploadStatusText!!
                            isCloud -> "☁️ Supabase Cloud Storage"
                            isLocal -> "💾 Almacenamiento local persistente"
                            isWeb -> "🌐 Enlace web remoto"
                            imageUrl.isBlank() -> "Sin imagen (usa monograma)"
                            else -> "Imagen asignada"
                        },
                        color = statusTint,
                        fontSize = 10.sp,
                        fontWeight = if (isCloud || isLocal) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para pegar URL opcionalmente
        OutlinedTextField(
            value = imageUrl,
            onValueChange = {
                uploadStatusText = null
                onImageUrlChange(it)
            },
            label = { Text("O ingresar URL web directa (WebP / PNG / JPG / HTTPS)", fontSize = 10.5.sp) },
            leadingIcon = { Icon(Icons.Default.Link, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = HextechCyan,
                unfocusedBorderColor = HextechCardBorder,
                focusedContainerColor = HextechDarkBg,
                unfocusedContainerColor = HextechDarkBg,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )
    }
}
