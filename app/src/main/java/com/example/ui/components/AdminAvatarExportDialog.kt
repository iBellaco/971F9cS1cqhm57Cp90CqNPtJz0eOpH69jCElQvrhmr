package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*
import com.example.util.ChampionAvatarExporter
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * Diálogo administrativo para descargar y exportar los 141 avatares oficiales de Wild Rift
 * con sus respectivos nombres directamente a la carpeta pública de descargas del usuario.
 */
@Composable
fun AdminAvatarExportDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val exportState by ChampionAvatarExporter.exportState.collectAsState()

    val animatedProgress by animateFloatAsState(
        targetValue = exportState.progressPercent,
        label = "ExportProgress"
    )

    Dialog(
        onDismissRequest = {
            if (!exportState.isExporting) {
                ChampionAvatarExporter.resetState()
                onDismiss()
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .padding(vertical = 24.dp)
                .animateContentSize(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, HextechGold.copy(alpha = 0.6f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Cabecera con Icono y Título
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Brush.linearGradient(listOf(HextechGold, Color(0xFF8B6B23)))),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.DownloadForOffline,
                                contentDescription = null,
                                tint = HextechDarkBg,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Exportar Avatares Oficiales",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = HextechGold
                            )
                            Text(
                                text = "141 Campeones de Wild Rift",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                        }
                    }

                    if (!exportState.isExporting) {
                        IconButton(
                            onClick = {
                                ChampionAvatarExporter.resetState()
                                onDismiss()
                            },
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.White.copy(alpha = 0.05f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = TextPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción informativa
                Surface(
                    color = HextechSurface,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.2f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.FolderZip, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Destino: ${exportState.targetPath}",
                                color = HextechCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Guarda las imágenes oficiales en alta resolución (120x120 RGBA) con el nombre exacto de cada campeón (ej: Dr. Mundo.png, Aatrox.png, etc.) a ritmo controlado.",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Estado Visual durante la descarga o en espera
                if (exportState.isExporting) {
                    // Avatar en proceso
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(HextechSurface)
                            .border(2.dp, HextechGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (exportState.currentChampionId.isNotBlank()) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data("file:///android_asset/champions/${exportState.currentChampionId}.png")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = exportState.currentChampionName,
                                modifier = Modifier.fillMaxSize().clip(CircleShape)
                            )
                        } else {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = HextechGold,
                                strokeWidth = 3.dp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Guardando: ${exportState.currentChampionName}",
                        fontWeight = FontWeight.Bold,
                        color = HextechGold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${exportState.currentCount} / ${exportState.totalCount} campeones procesados (${(exportState.progressPercent * 100).toInt()}%)",
                        color = TextMuted,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = HextechGold,
                        trackColor = HextechSurface
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedButton(
                        onClick = { ChampionAvatarExporter.cancelExport() },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Red.copy(alpha = 0.15f),
                            contentColor = Color(0xFFFF6B6B)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red.copy(alpha = 0.4f)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Stop, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Detener Descarga", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                } else if (exportState.isComplete) {
                    // Descarga completada con éxito
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00FF7F).copy(alpha = 0.2f))
                            .border(2.dp, Color(0xFF00FF7F), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xFF00FF7F),
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "¡Descarga Finalizada con Éxito!",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00FF7F),
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Se guardaron ${exportState.savedCount} avatares oficiales en tu carpeta:\n${exportState.targetPath}",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { ChampionAvatarExporter.startExport(context) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechGold),
                            border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Re-descargar", fontSize = 12.sp)
                        }

                        Button(
                            onClick = {
                                ChampionAvatarExporter.resetState()
                                onDismiss()
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Entendido", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                } else {
                    // Estado inicial listo para iniciar descarga
                    if (exportState.errorMessage != null) {
                        Surface(
                            color = Color.Red.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                        ) {
                            Text(
                                text = exportState.errorMessage ?: "",
                                color = Color(0xFFFF6B6B),
                                fontSize = 11.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            ChampionAvatarExporter.startExport(context)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HextechGold
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Icon(Icons.Default.Download, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Iniciar Descarga a Carpeta (141)",
                            color = HextechDarkBg,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
