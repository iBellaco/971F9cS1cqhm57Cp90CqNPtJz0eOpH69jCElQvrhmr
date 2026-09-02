import sys

with open("app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt", "r") as f:
    content = f.read()

target = """                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = tr("Cuentas / Perfiles"),
                                color = HextechGold,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
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
                        }
                    }"""

replacement = """                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = null,
                                    tint = HextechGold,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = tr("Cuentas / Perfiles"),
                                    color = HextechGold,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            TextButton(
                                onClick = { showBackupRestoreDialog = true },
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                            ) {
                                Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = HextechGold, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(tr("JSON Backup"), color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
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
                        }
                    }"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt", "w") as f:
    f.write(content)

