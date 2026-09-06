package com.example.ui.components

import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.sync.WrMetaScraper
import com.example.data.sync.WrMetaScrapingState
import com.example.ui.theme.*
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun WrMetaScrapingCard(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scrapingState by WrMetaScraper.scrapingState.collectAsState()
    
    var lastScrapeInfo by remember { mutableStateOf(WrMetaScraper.getLastScrapeInfo(context)) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(
                    1.2.dp,
                    Brush.horizontalGradient(
                        listOf(
                            HextechGold.copy(alpha = 0.8f),
                            HextechCyan.copy(alpha = 0.8f),
                            HextechGold.copy(alpha = 0.8f)
                        )
                    )
                ),
                RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = HextechSurface.copy(alpha = 0.95f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Cabecera del Descargador
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(HextechGoldDark, HextechCyan.copy(alpha = 0.6f))
                                )
                            )
                            .border(1.dp, HextechGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = null,
                            tint = HextechGoldLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Descargador de Avatares a Celular",
                                color = HextechGoldLight,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.3.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(HextechCyan.copy(alpha = 0.2f))
                                    .border(0.5.dp, HextechCyan, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "141 Picks",
                                    color = HextechCyan,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Text(
                            text = "Carpeta: Almacenamiento/Download/WR_META_141/",
                            color = TextSecondary,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Guarda los avatares en formato JPG/WEBP en la carpeta pública 'Download/WR_META_141/imagenes/' y genera el archivo 'urls_imagenes.txt' con Nombre y URL de cada campeón.",
                color = TextMuted,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Estado reactivo y barra de progreso
            when (val state = scrapingState) {
                is WrMetaScrapingState.Idle -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechDarkBg.copy(alpha = 0.6f))
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Estado de guardado:",
                                color = TextSecondary,
                                fontSize = 10.5.sp
                            )
                            Text(
                                text = if (lastScrapeInfo.first > 0) "${lastScrapeInfo.first} avatares guardados" else lastScrapeInfo.second,
                                color = if (lastScrapeInfo.first > 0) HextechCyan else TextMuted,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        if (lastScrapeInfo.first > 0) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Ruta: ${lastScrapeInfo.third.ifEmpty { "Download/WR_META_141/" }}",
                                color = TextMuted,
                                fontSize = 9.sp
                            )
                        }
                    }
                }

                is WrMetaScrapingState.ExtractingUrls -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechDarkBg.copy(alpha = 0.8f))
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Extrayendo URLs: ${state.category}",
                                color = HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                            Text(
                                text = "${(state.progressPercent * 100).toInt()}%",
                                color = HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { state.progressPercent },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = HextechCyan,
                            trackColor = HextechCardBorder.copy(alpha = 0.4f)
                        )
                    }
                }

                is WrMetaScrapingState.DownloadingImages -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechDarkBg.copy(alpha = 0.8f))
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Guardando: ${state.currentChampion}",
                                color = HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                            Text(
                                text = "${state.downloadedCount}/${state.totalCount}",
                                color = HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { state.progressPercent },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = HextechGold,
                            trackColor = HextechCardBorder.copy(alpha = 0.4f)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Escribiendo en carpeta Download/WR_META_141/imagenes/...",
                            color = TextMuted,
                            fontSize = 9.5.sp
                        )
                    }
                }

                is WrMetaScrapingState.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(TierSColor.copy(alpha = 0.12f))
                            .border(1.dp, TierSColor.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = TierSColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "¡Guardado con Éxito en tu Celular!",
                                color = TierSColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "📁 Ubicación: ${state.publicDirectoryPath}",
                            color = TextPrimary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "📄 ${state.downloadedImagesCount} imágenes en 'imagenes/' y 'urls_imagenes.txt'",
                            color = TextSecondary,
                            fontSize = 9.5.sp
                        )
                    }
                }

                is WrMetaScrapingState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(DangerRed.copy(alpha = 0.12f))
                            .border(1.dp, DangerRed.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = DangerRed,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Aviso de almacenamiento",
                                color = DangerRed,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = state.message,
                            color = TextSecondary,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón de Ejecución
            val isRunning = scrapingState is WrMetaScrapingState.ExtractingUrls || scrapingState is WrMetaScrapingState.DownloadingImages
            Button(
                onClick = {
                    coroutineScope.launch {
                        WrMetaScraper.runScraping(context)
                        lastScrapeInfo = WrMetaScraper.getLastScrapeInfo(context)
                    }
                },
                enabled = !isRunning,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HextechGoldDark,
                    disabledContainerColor = HextechSurface
                ),
                border = BorderStroke(1.dp, HextechGold)
            ) {
                if (isRunning) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = HextechCyan,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Guardando en almacenamiento...",
                        color = HextechCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.CloudDownload,
                        contentDescription = null,
                        tint = HextechGoldLight,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Descargar Avatares y URLs a Celular",
                        color = HextechGoldLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.3.sp
                    )
                }
            }

            // Botón secundario para Compartir / Abrir urls_imagenes.txt
            if (lastScrapeInfo.first > 0 || scrapingState is WrMetaScrapingState.Success) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {
                        WrMetaScraper.shareUrlsFile(context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = HextechCyan
                    ),
                    border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.7f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Abrir / Compartir 'urls_imagenes.txt'",
                        color = HextechCyan,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
