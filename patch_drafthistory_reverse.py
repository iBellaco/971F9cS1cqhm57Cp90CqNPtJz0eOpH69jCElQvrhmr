import sys

with open("app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt", "r") as f:
    content = f.read()

replacement = """                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextButton(
                                onClick = { showBackupRestoreDialog = true },
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                            ) {
                                Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(tr("JSON Backup"), color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { showCreateProfileDialog = true },
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(tr("Crear Perfil"), color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }"""

target = """                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextButton(
                                onClick = { showBackupRestoreDialog = true },
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                            ) {
                                Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(tr("JSON Backup"), color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            TextButton(
                                onClick = { showCreateProfileDialog = true },
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(tr("Crear Perfil"), color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }"""

content = content.replace(replacement, target)

with open("app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt", "w") as f:
    f.write(content)

