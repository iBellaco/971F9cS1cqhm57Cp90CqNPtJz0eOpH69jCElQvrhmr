import re

with open('app/src/main/java/com/example/ui/components/DownloadProgressWidget.kt', 'r') as f:
    content = f.read()

# Add isFullyDownloaded
content = content.replace(
    'val logs by ImagePrefetcher.downloadLogs.collectAsState()',
    'val logs by ImagePrefetcher.downloadLogs.collectAsState()\n    val isFullyDownloaded by ImagePrefetcher.isFullyDownloaded.collectAsState()\n    val context = androidx.compose.ui.platform.LocalContext.current'
)

# Update Text and Buttons
old_row = '''                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isDownloading) tr("Descargando Recursos") else tr("Descarga Finalizada"),
                            color = TextPrimary,
                            fontSize = if (isMinimized) 12.sp else 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            if (isDownloading) {
                                IconButton(
                                    onClick = { ImagePrefetcher.cancelPrefetch() },
                                    modifier = Modifier.size(24.dp).padding(end = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Cancelar",
                                        tint = com.example.ui.theme.DangerRed
                                    )
                                }
                                IconButton(
                                    onClick = { ImagePrefetcher.isUiMinimized.value = !isMinimized },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isMinimized) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Minimizar",
                                        tint = TextMuted
                                    )
                                }
                            } else {
                                IconButton(
                                    onClick = { ImagePrefetcher.showProgressUi.value = false },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Cerrar",
                                        tint = TextMuted
                                    )
                                }
                            }
                        }
                    }'''

new_row = '''                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val titleText = when {
                            isDownloading -> tr("Descargando Recursos")
                            isFullyDownloaded -> tr("Descarga Finalizada")
                            else -> tr("Descarga Pausada")
                        }
                        Text(
                            text = titleText,
                            color = TextPrimary,
                            fontSize = if (isMinimized) 12.sp else 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            if (isDownloading) {
                                IconButton(
                                    onClick = { ImagePrefetcher.cancelPrefetch() },
                                    modifier = Modifier.size(24.dp).padding(end = 8.dp)
                                ) {
                                    Icon(
                                        imageVector = androidx.compose.material.icons.Icons.Default.Pause,
                                        contentDescription = "Pausar",
                                        tint = com.example.ui.theme.HextechGold
                                    )
                                }
                                IconButton(
                                    onClick = { ImagePrefetcher.isUiMinimized.value = !isMinimized },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isMinimized) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Minimizar",
                                        tint = TextMuted
                                    )
                                }
                            } else {
                                if (!isFullyDownloaded) {
                                    IconButton(
                                        onClick = { ImagePrefetcher.startPrefetch(context) },
                                        modifier = Modifier.size(24.dp).padding(end = 8.dp)
                                    ) {
                                        Icon(
                                            imageVector = androidx.compose.material.icons.Icons.Default.PlayArrow,
                                            contentDescription = "Reanudar",
                                            tint = com.example.ui.theme.HextechCyan
                                        )
                                    }
                                }
                                if (isFullyDownloaded) {
                                    IconButton(
                                        onClick = { ImagePrefetcher.showProgressUi.value = false },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Cerrar",
                                            tint = TextMuted
                                        )
                                    }
                                }
                            }
                        }
                    }'''

content = content.replace(old_row, new_row)
with open('app/src/main/java/com/example/ui/components/DownloadProgressWidget.kt', 'w') as f:
    f.write(content)
