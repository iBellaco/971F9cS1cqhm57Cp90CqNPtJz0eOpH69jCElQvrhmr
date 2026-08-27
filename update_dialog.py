import re

fpath = "app/src/main/java/com/example/ui/components/BugReportFeedbackDialog.kt"
with open(fpath, "r", encoding="utf-8") as f:
    content = f.read()

# 1. Add the state variables
state_vars = """    var email by remember { mutableStateOf("") }
    var suggestedChampion by remember { mutableStateOf("") }
    var suggestedRole by remember { mutableStateOf("") }
    var suggestedRunes by remember { mutableStateOf("") }
    var suggestedSpells by remember { mutableStateOf("") }"""

content = content.replace('    var email by remember { mutableStateOf("") }', state_vars)

# 2. Modify sendFeedbackMessage
send_msg_old = """            scope.launch {
                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = title,
                    description = description,
                    email = email.trim().takeIf { it.isNotEmpty() },"""

send_msg_new = """            scope.launch {
                var finalDesc = description
                if (suggestedChampion.isNotBlank()) finalDesc += "\\n\\nCampeón Sugerido: $suggestedChampion"
                if (suggestedRole.isNotBlank()) finalDesc += "\\nRol Sugerido: $suggestedRole"
                if (suggestedRunes.isNotBlank()) finalDesc += "\\nRunas Sugeridas: $suggestedRunes"
                if (suggestedSpells.isNotBlank()) finalDesc += "\\nHechizos Sugeridos: $suggestedSpells"
                
                val result = FeedbackRepository.submitFeedback(
                    type = selectedType.name,
                    title = title,
                    description = finalDesc,
                    email = email.trim().takeIf { it.isNotEmpty() },"""

content = content.replace(send_msg_old, send_msg_new)

# 3. Add text fields in UI
ui_old = """                if (email.isNotBlank() && !isEmailValid) {
                    Text(
                        text = tr("Formato de correo inválido"),
                        color = Color(0xFFFF5252),
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }"""

ui_new = ui_old + """
                
                if (selectedType == FeedbackType.SUGGESTION) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(tr("Datos sugeridos para Build de Campeón (Opcional)"), color = HextechGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    
                    OutlinedTextField(
                        value = suggestedChampion,
                        onValueChange = { suggestedChampion = it },
                        label = { Text(tr("Campeón"), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedLabelColor = HextechGold,
                            unfocusedLabelColor = TextMuted
                        )
                    )
                    OutlinedTextField(
                        value = suggestedRole,
                        onValueChange = { suggestedRole = it },
                        label = { Text(tr("Rol"), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedLabelColor = HextechGold,
                            unfocusedLabelColor = TextMuted
                        )
                    )
                    OutlinedTextField(
                        value = suggestedRunes,
                        onValueChange = { suggestedRunes = it },
                        label = { Text(tr("Runas Sugeridas"), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedLabelColor = HextechGold,
                            unfocusedLabelColor = TextMuted
                        )
                    )
                    OutlinedTextField(
                        value = suggestedSpells,
                        onValueChange = { suggestedSpells = it },
                        label = { Text(tr("Hechizos Sugeridos"), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedLabelColor = HextechGold,
                            unfocusedLabelColor = TextMuted
                        )
                    )
                }
"""

content = content.replace(ui_old, ui_new)

with open(fpath, "w", encoding="utf-8") as f:
    f.write(content)

print("Updated Dialog")
