package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.util.ImagePrefetcher
import com.example.util.tr

@Composable
fun DownloadProgressWidget() {
    val showUi by ImagePrefetcher.showProgressUi.collectAsState()
    val isMinimized by ImagePrefetcher.isUiMinimized.collectAsState()
    val isDownloading by ImagePrefetcher.isDownloading.collectAsState()
    val progress by ImagePrefetcher.downloadProgress.collectAsState()
    val logs by ImagePrefetcher.downloadLogs.collectAsState()

    if (!showUi) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(if (isMinimized) 0.5f else 1f)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, HextechCyan, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.95f))
            ) {
                Column(
                    modifier = Modifier.padding(if (isMinimized) 12.dp else 16.dp)
                ) {
                    Row(
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
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = HextechCyan,
                        trackColor = HextechSurfaceVariant
                    )
                    
                    if (!isMinimized) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(HextechSurface, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                reverseLayout = true
                            ) {
                                items(logs.reversed()) { rawLog ->
                                    val translatedLog = when {
                                        rawLog.startsWith("Descargando: ") -> tr("Descargando") + ": " + rawLog.substringAfter("Descargando: ")
                                        rawLog.startsWith("Descargado: ") -> tr("Descargado") + ": " + rawLog.substringAfter("Descargado: ")
                                        rawLog.startsWith("Total descargado: ") -> tr("Total descargado") + ": " + rawLog.substringAfter("Total descargado: ")
                                        rawLog.startsWith("En caché: ") -> tr("En caché") + ": " + rawLog.substringAfter("En caché: ")
                                        rawLog.startsWith("Reintentando: ") -> tr("Reintentando") + ": " + rawLog.substringAfter("Reintentando: ")
                                        rawLog.startsWith("Total de elementos: ") -> tr("Total de elementos") + ": " + rawLog.substringAfter("Total de elementos: ")
                                        rawLog.startsWith("✅ Descarga completada con éxito") -> tr("Descarga completada")
                                        else -> tr(rawLog)
                                    }
                                    Text(
                                        text = translatedLog,
                                        color = if (rawLog.contains("Error")) DangerRed else TextMuted,
                                        fontSize = 10.sp,
                                        lineHeight = 12.sp
                                    )
                                }
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "${(progress * 100).toInt()}%",
                            color = TextCyan,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
