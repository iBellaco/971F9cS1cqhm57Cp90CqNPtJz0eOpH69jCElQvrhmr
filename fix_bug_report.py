import re

with open("app/src/main/java/com/example/ui/components/BugReportFeedbackDialog.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Replace selectedImageBase64 with list
content = content.replace(
    'var selectedImageBase64 by remember { mutableStateOf<String?>(null) }',
    'var selectedImages by remember { mutableStateOf<List<String>>(emptyList()) }'
)

# Fix imagePickerLauncher
old_launcher = """    val imagePickerLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.GetContent()
    ) { uri: android.net.Uri? ->
        uri?.let {
            selectedImageUri = it
            scope.launch {
                val base64 = com.example.util.ImageUtils.uriToBase64(context, it)
                if (base64 != null) {
                    selectedImageBase64 = base64
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                    selectedImageUri = null
                }
            }
        }
    }"""

new_launcher = """    val imagePickerLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)
    ) { uris ->
        if (uris.isNotEmpty()) {
            scope.launch {
                val newImages = mutableListOf<String>()
                for (uri in uris) {
                    val base64 = com.example.util.ImageUtils.uriToBase64(context, uri)
                    if (base64 != null) {
                        newImages.add(base64)
                    }
                }
                if (newImages.isNotEmpty()) {
                    val combined = (selectedImages + newImages).take(3)
                    selectedImages = combined
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }"""

content = content.replace(old_launcher, new_launcher)

# Update canPublish: "un mínimo de una imagen"
content = content.replace(
    'val canPublish = title.trim().isNotBlank() && description.trim().isNotBlank() && selectedImageBase64 != null',
    'val canPublish = title.trim().isNotBlank() && description.trim().isNotBlank() && selectedImages.isNotEmpty()'
)

# Update submitFeedback call
old_submit = """                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = title,
                    description = description,
                    imageBase64 = selectedImageBase64,
                    retentionDays = 7
                )"""

new_submit = """                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = title,
                    description = description,
                    imagesBase64 = selectedImages,
                    retentionDays = 7
                )"""

content = content.replace(old_submit, new_submit)

# Update UI for images
old_ui = """                // Subir Imagen
                if (selectedImageBase64 == null) {
                    OutlinedButton(
                        onClick = { imagePickerLauncher.launch("image/*") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Image,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Adjuntar Captura"),
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                            .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Image,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = tr("Imagen subida"),
                                color = TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        IconButton(
                            onClick = {
                                selectedImageUri = null
                                selectedImageBase64 = null
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = tr("Eliminar imagen"),
                                tint = Color(0xFFFF5252)
                            )
                        }
                    }
                }"""

new_ui = """                // Subir Imágenes (Max 3)
                if (selectedImages.size < 3) {
                    OutlinedButton(
                        onClick = {
                            imagePickerLauncher.launch(
                                androidx.activity.result.PickVisualMediaRequest(
                                    androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Image,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tr("Adjuntar Captura") + " (${selectedImages.size}/3)",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                
                if (selectedImages.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    selectedImages.forEachIndexed { index, base64 ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                                .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(12.dp)
                                .padding(bottom = if (index < selectedImages.size - 1) 8.dp else 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = androidx.compose.material.icons.Icons.Default.Image,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = tr("Imagen subida") + " " + (index + 1),
                                    color = TextPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            IconButton(
                                onClick = {
                                    val newList = selectedImages.toMutableList()
                                    newList.removeAt(index)
                                    selectedImages = newList
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = tr("Eliminar imagen"),
                                    tint = Color(0xFFFF5252)
                                )
                            }
                        }
                    }
                }"""

content = content.replace(old_ui, new_ui)

with open("app/src/main/java/com/example/ui/components/BugReportFeedbackDialog.kt", "w", encoding="utf-8") as f:
    f.write(content)

