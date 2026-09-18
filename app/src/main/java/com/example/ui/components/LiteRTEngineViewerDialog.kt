package com.example.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.service.screen.LiteRTVisionClassifier

@Composable
fun LiteRTEngineViewerDialog(
    onDismissRequest: () -> Unit
) {
    val report by LiteRTVisionClassifier.reportFlow.collectAsStateWithLifecycle()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .heightIn(max = 680.dp)
                .padding(8.dp)
                .testTag("litert_viewer_dialog"),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF0F172A),
            tonalElevation = 8.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Cabecera
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "LiteRT Motor",
                            tint = Color(0xFF00E5FF),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Visor Google MediaPipe / LiteRT",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Motor de Inferencia del 10º Pick (On-Device)",
                                color = Color(0xFF94A3B8),
                                fontSize = 11.sp
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Estado del Motor
                val (badgeBg, badgeBorder, badgeText, statusIcon) = when (report.status) {
                    LiteRTVisionClassifier.EngineStatus.WAITING_FOR_PICKS_1_TO_9 -> Quadruple(
                        Color(0xFF422006),
                        Color(0xFFF59E0B),
                        "EN ESPERA: Requiere selecciones 1 al 9 (${report.evaluatedPicksCount}/9)",
                        Icons.Default.HourglassEmpty
                    )
                    LiteRTVisionClassifier.EngineStatus.RUNNING_INFERENCE -> Quadruple(
                        Color(0xFF1E293B),
                        Color(0xFF38BDF8),
                        "PROCESANDO TENSORES DE IMAGEN",
                        Icons.Default.AutoAwesome
                    )
                    LiteRTVisionClassifier.EngineStatus.COMPLETED -> Quadruple(
                        Color(0xFF064E3B),
                        Color(0xFF10B981),
                        "INFERENCIA COMPLETADA • 10º PICK CONFIRMADO",
                        Icons.Default.CheckCircle
                    )
                    LiteRTVisionClassifier.EngineStatus.NO_DETECTION -> Quadruple(
                        Color(0xFF1E293B),
                        Color(0xFF64748B),
                        "EN ESPERA DE FRAME ACTIVO",
                        Icons.Default.Info
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(badgeBg)
                        .border(1.dp, badgeBorder, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = statusIcon,
                            contentDescription = null,
                            tint = badgeBorder,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = badgeText,
                            color = badgeBorder,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Tarjeta de Decisión y Recorte Analizado
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "DECISIÓN DEL MOTOR MEDIAPIPE / LITERT",
                            color = Color(0xFF00E5FF),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Vista del recorte real analizado
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(end = 12.dp)
                            ) {
                                Text(
                                    text = "Recorte Escaneado",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 10.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                        .border(2.dp, Color(0xFF00E5FF), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (report.cropBitmap != null && !report.cropBitmap!!.isRecycled) {
                                        Image(
                                            bitmap = report.cropBitmap!!.asImageBitmap(),
                                            contentDescription = "Recorte 10º Pick",
                                            modifier = Modifier.size(60.dp)
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.AutoAwesome,
                                            contentDescription = null,
                                            tint = Color(0xFF475569),
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }
                            }

                            // Datos del campeón decidido
                            Column(modifier = Modifier.weight(1f)) {
                                val champ = report.pickedChampion
                                if (champ != null) {
                                    Text(
                                        text = champ.name,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp
                                    )
                                    Text(
                                        text = "Rol: ${champ.primaryRole.displayName}",
                                        color = Color(0xFF38BDF8),
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Confianza: ${report.confidencePercent}%",
                                            color = Color(0xFF10B981),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "• ${report.inferenceTimeMs} ms",
                                            color = Color(0xFF94A3B8),
                                            fontSize = 11.sp
                                        )
                                    }
                                } else {
                                    Text(
                                        text = if (report.status == LiteRTVisionClassifier.EngineStatus.WAITING_FOR_PICKS_1_TO_9) {
                                            "A la espera de picks 1 a 9"
                                        } else {
                                            "Evaluando tensores..."
                                        },
                                        color = Color(0xFF94A3B8),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = report.slotDescription.ifBlank { "Slot 5 (10º Pick)" },
                                        color = Color(0xFF64748B),
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Métricas técnicas del Tensor
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF0F172A), RoundedCornerShape(6.dp))
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tensor: ${report.tensorDimensions}",
                                color = Color(0xFF64748B),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = "Espacio: RGB [-1.0, 1.0]",
                                color = Color(0xFF64748B),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Tabla de Candidatos Comparados por LiteRT
                Text(
                    text = "COMPARACIÓN DE TENSORES (TOP 5 CANDIDATOS)",
                    color = Color(0xFFE2E8F0),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (report.topCandidates.isNotEmpty()) {
                    report.topCandidates.forEach { candidate ->
                        CandidateRowItem(candidate = candidate)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1E293B), RoundedCornerShape(8.dp))
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Los candidatos comparados aparecerán aquí cuando se procese el frame del 10º pick.",
                            color = Color(0xFF64748B),
                            fontSize = 12.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Motivo y Registro de Decisión
                Text(
                    text = "MOTIVO DE LA DECISIÓN",
                    color = Color(0xFFE2E8F0),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E293B), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = report.decisionReason,
                        color = Color(0xFFCBD5E1),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CandidateRowItem(candidate: LiteRTVisionClassifier.LiteRTCandidateScore) {
    val isWinner = candidate.rank == 1
    val borderColor = if (isWinner) Color(0xFF00E5FF) else Color(0xFF334155)
    val bgColor = if (isWinner) Color(0xFF1E293B) else Color(0xFF0F172A)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Text(
                text = "#${candidate.rank}",
                color = if (isWinner) Color(0xFF00E5FF) else Color(0xFF64748B),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.width(24.dp)
            )

            ChampionAvatar(
                champion = candidate.champion,
                size = 32.dp,
                modifier = Modifier.clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = candidate.champion.name,
                    color = Color.White,
                    fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 13.sp
                )
                Text(
                    text = "Probabilidad Softmax: ${(candidate.softmaxProbability * 100).toInt()}%",
                    color = Color(0xFF94A3B8),
                    fontSize = 10.sp
                )
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${(candidate.similarityScore * 100).toInt()}% Tensor",
                color = if (isWinner) Color(0xFF10B981) else Color(0xFFCBD5E1),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            LinearProgressIndicator(
                progress = { candidate.similarityScore.coerceIn(0f, 1f) },
                modifier = Modifier
                    .width(64.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = if (isWinner) Color(0xFF00E5FF) else Color(0xFF64748B),
                trackColor = Color(0xFF334155)
            )
        }
    }
}

private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
