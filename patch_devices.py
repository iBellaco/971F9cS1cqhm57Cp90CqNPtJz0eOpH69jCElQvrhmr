import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

target = """                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(Icons.Default.LockReset,"""

replacement = """                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(androidx.compose.material.icons.filled.PhonelinkErase, contentDescription = null, tint = LolHextechCyan, modifier = Modifier.size(16.dp))
                                },
                                text = { Text("Resetear Dispositivos (Desvincular Hardware)", color = LolHextechCyan, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                                onClick = {
                                    FirebaseFirestore.getInstance().collection("users")
                                        .document(user.uid)
                                        .update("registeredDevices", emptyList<String>())
                                        .addOnSuccessListener {
                                            Toast.makeText(context, "Dispositivos liberados con éxito", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(context, "Error al liberar dispositivos", Toast.LENGTH_SHORT).show()
                                        }
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(Icons.Default.LockReset,"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)

print("Patch applied.")
