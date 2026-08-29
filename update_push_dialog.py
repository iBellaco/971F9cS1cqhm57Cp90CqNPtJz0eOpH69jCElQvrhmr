import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

# Extract from `if (showPushDialog) {` to the end of the if block.
# Wait, it's safer to use regex or replace target

target = """    if (showPushDialog) {

        var pushTitle by remember { mutableStateOf("") }
        var pushBody by remember { mutableStateOf("") }
        var pushTarget by remember { mutableStateOf("all") }
        var isSendingPush by remember { mutableStateOf(false) }
        
        AlertDialog(
            onDismissRequest = { if (!isSendingPush) showPushDialog = false },
            title = { Text("Nueva Notificación Push", color = HextechGold) },
            text = {
                Column {
                    OutlinedTextField(
                        value = pushTitle,
                        onValueChange = { pushTitle = it },
                        label = { Text("Título") },
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedBorderColor = HextechGold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = pushBody,
                        onValueChange = { pushBody = it },
                        label = { Text("Mensaje (Ej. Meta actualizado)") },
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedBorderColor = HextechGold
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Destinatarios:", color = TextMuted)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = pushTarget == "all", 
                            onClick = { pushTarget = "all" },
                            colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = HextechCyan)
                        )
                        Text("Todos los usuarios", color = TextPrimary)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = pushTarget == "premium", 
                            onClick = { pushTarget = "premium" },
                            colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = HextechGold)
                        )
                        Text("Solo Premium", color = HextechGold)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (pushTitle.isBlank() || pushBody.isBlank()) return@TextButton
                        isSendingPush = true
                        scope.launch {
                            try {
                                val data = hashMapOf(
                                    "title" to pushTitle,
                                    "body" to pushBody,
                                    "target" to pushTarget,
                                    "targetTier" to pushTarget,
                                    "isPremiumTarget" to (pushTarget == "premium"),
                                    "createdAt" to System.currentTimeMillis()
                                )
                                FirebaseFirestore.getInstance().collection("global_notifications").add(data).await()
                                Toast.makeText(context, "Notificación enviada", Toast.LENGTH_SHORT).show()
                                showPushDialog = false
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                            } finally {
                                isSendingPush = false
                            }
                        }
                    },
                    enabled = !isSendingPush
                ) {
                    Text(if (isSendingPush) "Enviando..." else "Enviar", color = HextechCyan)
                }
            },
            dismissButton = {
                TextButton(onClick = { showPushDialog = false }, enabled = !isSendingPush) {
                    Text("Cancelar", color = TextMuted)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold
        )
    }"""

replacement = """    if (showPushDialog) {

        var pushTitle by remember { mutableStateOf("") }
        var pushBody by remember { mutableStateOf("") }
        var pushTarget by remember { mutableStateOf("all") }
        var isSendingPush by remember { mutableStateOf(false) }
        var pushStatus by remember { mutableStateOf("IDLE") } // IDLE, PENDING, SENT, FAILED
        var pushErrorMessage by remember { mutableStateOf<String?>(null) }
        
        AlertDialog(
            onDismissRequest = { if (!isSendingPush) showPushDialog = false },
            title = { Text("Nueva Notificación Push", color = HextechGold) },
            text = {
                Column {
                    OutlinedTextField(
                        value = pushTitle,
                        onValueChange = { pushTitle = it; pushStatus = "IDLE" },
                        label = { Text("Título") },
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedBorderColor = HextechGold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = pushBody,
                        onValueChange = { pushBody = it; pushStatus = "IDLE" },
                        label = { Text("Mensaje (Ej. Meta actualizado)") },
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedBorderColor = HextechGold
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Destinatarios:", color = TextMuted)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = pushTarget == "all", 
                            onClick = { pushTarget = "all"; pushStatus = "IDLE" },
                            colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = HextechCyan)
                        )
                        Text("Todos los usuarios", color = TextPrimary)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = pushTarget == "premium", 
                            onClick = { pushTarget = "premium"; pushStatus = "IDLE" },
                            colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = HextechGold)
                        )
                        Text("Solo Premium", color = HextechGold)
                    }
                    
                    if (pushStatus != "IDLE") {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    when (pushStatus) {
                                        "PENDING" -> Color(0xFFFFA000).copy(alpha = 0.1f)
                                        "SENT" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                                        "FAILED" -> DangerRed.copy(alpha = 0.1f)
                                        else -> Color.Transparent
                                    },
                                    RoundedCornerShape(8.dp)
                                )
                                .border(
                                    1.dp,
                                    when (pushStatus) {
                                        "PENDING" -> Color(0xFFFFA000)
                                        "SENT" -> Color(0xFF4CAF50)
                                        "FAILED" -> DangerRed
                                        else -> Color.Transparent
                                    },
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    val statusIcon = when(pushStatus) {
                                        "PENDING" -> Icons.Default.Refresh
                                        "SENT" -> Icons.Default.CheckCircle
                                        "FAILED" -> Icons.Default.Warning
                                        else -> Icons.Default.Info
                                    }
                                    val statusTint = when(pushStatus) {
                                        "PENDING" -> Color(0xFFFFA000)
                                        "SENT" -> Color(0xFF4CAF50)
                                        "FAILED" -> DangerRed
                                        else -> Color.White
                                    }
                                    Icon(statusIcon, contentDescription = null, tint = statusTint, modifier = Modifier.size(20.dp))
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = when(pushStatus) {
                                            "PENDING" -> "Enviando a Firebase..."
                                            "SENT" -> "Entregado a Firestore"
                                            "FAILED" -> "Fallo en la entrega"
                                            else -> ""
                                        },
                                        color = statusTint,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                                if (pushStatus == "FAILED" && !pushErrorMessage.isNullOrBlank()) {
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = pushErrorMessage!!,
                                        color = DangerRed,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (pushTitle.isBlank() || pushBody.isBlank()) return@TextButton
                        isSendingPush = true
                        pushStatus = "PENDING"
                        pushErrorMessage = null
                        scope.launch {
                            try {
                                val data = hashMapOf(
                                    "title" to pushTitle,
                                    "body" to pushBody,
                                    "target" to pushTarget,
                                    "targetTier" to pushTarget,
                                    "isPremiumTarget" to (pushTarget == "premium"),
                                    "createdAt" to System.currentTimeMillis()
                                )
                                FirebaseFirestore.getInstance().collection("global_notifications").add(data).await()
                                pushStatus = "SENT"
                            } catch (e: Exception) {
                                pushStatus = "FAILED"
                                pushErrorMessage = e.message ?: "Error desconocido de Firebase"
                            } finally {
                                isSendingPush = false
                            }
                        }
                    },
                    enabled = !isSendingPush && pushStatus != "SENT"
                ) {
                    Text(if (isSendingPush) "Enviando..." else if (pushStatus == "SENT") "Enviado" else "Enviar", color = HextechCyan)
                }
            },
            dismissButton = {
                TextButton(onClick = { showPushDialog = false }, enabled = !isSendingPush) {
                    Text(if (pushStatus == "SENT") "Cerrar" else "Cancelar", color = TextMuted)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold
        )
    }"""

if target in text:
    text = text.replace(target, replacement)
    with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
        f.write(text)
    print("Replaced successfully")
else:
    print("Target not found. Please verify.")

