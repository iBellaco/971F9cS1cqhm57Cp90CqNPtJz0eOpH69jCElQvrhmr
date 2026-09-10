package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LaneRole
import com.example.service.screen.DraftVisionScanner
import com.example.service.screen.VisionCalibrationConfig
import com.example.ui.theme.*
import com.example.util.tr
import androidx.lifecycle.compose.collectAsStateWithLifecycle

enum class CalibrationTarget(val title: String, val subtitle: String) {
    GLOBAL_ALLY_X("Columna Aliados (X)", "Mover horizontalmente todos los avatares aliados"),
    GLOBAL_ENEMY_X("Columna Rivales (X)", "Mover horizontalmente todos los avatares rivales"),
    AVATAR_SIZE("Tamaño Avatar (⌀)", "Agrandar o reducir radio de escaneo de retratos"),
    ALLY_SLOT_0("Aliado 1 (TOP)", "Ajuste vertical Y del carril de Barón"),
    ALLY_SLOT_1("Aliado 2 (JG)", "Ajuste vertical Y de la Jungla"),
    ALLY_SLOT_2("Aliado 3 (MID)", "Ajuste vertical Y del carril Central"),
    ALLY_SLOT_3("Aliado 4 (ADC)", "Ajuste vertical Y del Tirador"),
    ALLY_SLOT_4("Aliado 5 (SUP)", "Ajuste vertical Y del Apoyo"),
    ENEMY_SLOT_0("Rival 1", "Ajuste vertical Y del slot 1 rival"),
    ENEMY_SLOT_1("Rival 2", "Ajuste vertical Y del slot 2 rival"),
    ENEMY_SLOT_2("Rival 3", "Ajuste vertical Y del slot 3 rival"),
    ENEMY_SLOT_3("Rival 4", "Ajuste vertical Y del slot 4 rival"),
    ENEMY_SLOT_4("Rival 5", "Ajuste vertical Y del slot 5 rival"),
    GLOBAL_Y("Mover Todo el Draft (Y)", "Desplazar verticalmente todas las casillas")
}

@Composable
fun DraftCalibrationPanel(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentConfig by DraftVisionScanner.calibrationConfigFlow.collectAsStateWithLifecycle()
    var config by remember { mutableStateOf(currentConfig) }
    LaunchedEffect(currentConfig) {
        config = currentConfig
    }
    var selectedTarget by remember { mutableStateOf(CalibrationTarget.AVATAR_SIZE) }
    var stepFactor by remember { mutableStateOf(0.005f) } // 0.5% paso normal

    fun updateAndApply(newConfig: VisionCalibrationConfig) {
        config = newConfig
        DraftVisionScanner.updateCalibration(context, newConfig)
    }

    fun modify(deltaX: Float = 0f, deltaY: Float = 0f, deltaSize: Float = 0f) {
        val cur = config
        val updated = when (selectedTarget) {
            CalibrationTarget.GLOBAL_ALLY_X -> cur.copy(allyAvatarCenterX = (cur.allyAvatarCenterX + deltaX).coerceIn(0.01f, 0.40f))
            CalibrationTarget.GLOBAL_ENEMY_X -> cur.copy(enemyAvatarCenterX = (cur.enemyAvatarCenterX + deltaX).coerceIn(0.60f, 0.99f))
            CalibrationTarget.AVATAR_SIZE -> cur.copy(avatarDiameterRatio = (cur.avatarDiameterRatio + deltaSize).coerceIn(0.04f, 0.28f))
            CalibrationTarget.ALLY_SLOT_0 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[0] = (it[0] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_1 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[1] = (it[1] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_2 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[2] = (it[2] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_3 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[3] = (it[3] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_4 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[4] = (it[4] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_0 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[0] = (it[0] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_1 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[1] = (it[1] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_2 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[2] = (it[2] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_3 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[3] = (it[3] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_4 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[4] = (it[4] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.GLOBAL_Y -> cur.copy(
                allySlotYRatios = cur.allySlotYRatios.map { (it + deltaY).coerceIn(0.05f, 0.95f) },
                enemySlotYRatios = cur.enemySlotYRatios.map { (it + deltaY).coerceIn(0.05f, 0.95f) }
            )
        }
        updateAndApply(updated)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
        border = BorderStroke(1.5.dp, HextechCyan)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // CABECERA CON BOTÓN CERRAR Y TÍTULO DEPURACIÓN
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.BugReport,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "🐛 " + tr("Calibrador de Visión & ROI"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cerrar Calibrador",
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // MAPA VISUAL ESQUEMÁTICO DEL ÁREA DE ESCANEO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF07121E))
                    .border(1.dp, HextechCardBorder.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                // Dibujar columnas y slots
                val allyRoles = listOf("TOP", "JG", "MID", "ADC", "SUP")
                val diamPercent = (config.avatarDiameterRatio * 100).toInt()

                // Columna Aliada
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterStart)
                        .padding(start = (config.allyAvatarCenterX * 180).dp.coerceIn(4.dp, 60.dp)),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    config.allySlotYRatios.forEachIndexed { i, y ->
                        val isTargeted = selectedTarget == CalibrationTarget.GLOBAL_ALLY_X ||
                                selectedTarget == CalibrationTarget.AVATAR_SIZE ||
                                (selectedTarget == CalibrationTarget.ALLY_SLOT_0 && i == 0) ||
                                (selectedTarget == CalibrationTarget.ALLY_SLOT_1 && i == 1) ||
                                (selectedTarget == CalibrationTarget.ALLY_SLOT_2 && i == 2) ||
                                (selectedTarget == CalibrationTarget.ALLY_SLOT_3 && i == 3) ||
                                (selectedTarget == CalibrationTarget.ALLY_SLOT_4 && i == 4)

                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(if (isTargeted) AllyBlue.copy(alpha = 0.35f) else Color(0xFF13273D))
                                .border(
                                    if (isTargeted) 1.5.dp else 0.8.dp,
                                    if (isTargeted) HextechCyan else AllyBlue.copy(alpha = 0.5f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = allyRoles.getOrElse(i) { "$i" },
                                color = if (isTargeted) HextechCyan else TextMuted,
                                fontSize = 7.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Centro Informativo del Escáner
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ÁREA DRAFT EN VIVO",
                        color = HextechGold.copy(alpha = 0.8f),
                        fontSize = 8.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "⌀: ${(config.avatarDiameterRatio * 100).format(1)}% | Aliados X: ${(config.allyAvatarCenterX * 100).format(1)}% | Rivales X: ${(config.enemyAvatarCenterX * 100).format(1)}%",
                        color = HextechCyan,
                        fontSize = 7.5.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(HextechSurface)
                            .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Objetivo: ${selectedTarget.title}",
                            color = HextechGold,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // Columna Rival
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd)
                        .padding(end = ((1f - config.enemyAvatarCenterX) * 180).dp.coerceIn(4.dp, 60.dp)),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    config.enemySlotYRatios.forEachIndexed { i, y ->
                        val isTargeted = selectedTarget == CalibrationTarget.GLOBAL_ENEMY_X ||
                                selectedTarget == CalibrationTarget.AVATAR_SIZE ||
                                (selectedTarget == CalibrationTarget.ENEMY_SLOT_0 && i == 0) ||
                                (selectedTarget == CalibrationTarget.ENEMY_SLOT_1 && i == 1) ||
                                (selectedTarget == CalibrationTarget.ENEMY_SLOT_2 && i == 2) ||
                                (selectedTarget == CalibrationTarget.ENEMY_SLOT_3 && i == 3) ||
                                (selectedTarget == CalibrationTarget.ENEMY_SLOT_4 && i == 4)

                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(if (isTargeted) DangerRed.copy(alpha = 0.35f) else Color(0xFF38141B))
                                .border(
                                    if (isTargeted) 1.5.dp else 0.8.dp,
                                    if (isTargeted) Color(0xFFFF5252) else DangerRed.copy(alpha = 0.5f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "R${i + 1}",
                                color = if (isTargeted) Color(0xFFFF5252) else TextMuted,
                                fontSize = 7.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // SELECTOR DE OBJETIVO A CALIBRAR (CHIPS HORIZONTALES)
            Text(
                text = "1. Selecciona qué elemento calibrar:",
                color = TextPrimary,
                fontSize = 9.5.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CalibrationTarget.entries.forEach { target ->
                    val isSel = selectedTarget == target
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSel) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                            .border(
                                1.dp,
                                if (isSel) HextechCyan else HextechCardBorder.copy(alpha = 0.5f),
                                RoundedCornerShape(6.dp)
                            )
                            .clickable { selectedTarget = target }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = target.title,
                            color = if (isSel) HextechCyan else TextMuted,
                            fontSize = 8.5.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // CONTROLES DE MOVIMIENTO DIRECCIONAL Y TAMAÑO (D-PAD)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Pad de Movimiento
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    // Arriba
                    Surface(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { modify(deltaY = -stepFactor) },
                        shape = RoundedCornerShape(6.dp),
                        color = HextechSurface,
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Arriba", tint = HextechGold, modifier = Modifier.size(20.dp))
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(vertical = 3.dp)
                    ) {
                        // Izquierda
                        Surface(
                            modifier = Modifier
                                .size(34.dp)
                                .clickable { modify(deltaX = -stepFactor) },
                            shape = RoundedCornerShape(6.dp),
                            color = HextechSurface,
                            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Izquierda", tint = HextechGold, modifier = Modifier.size(20.dp))
                            }
                        }

                        // Indicador Central
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFF0F1E2E)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "±${(stepFactor * 100).format(1)}%",
                                color = HextechCyan,
                                fontSize = 7.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Derecha
                        Surface(
                            modifier = Modifier
                                .size(34.dp)
                                .clickable { modify(deltaX = stepFactor) },
                            shape = RoundedCornerShape(6.dp),
                            color = HextechSurface,
                            border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Derecha", tint = HextechGold, modifier = Modifier.size(20.dp))
                            }
                        }
                    }

                    // Abajo
                    Surface(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { modify(deltaY = stepFactor) },
                        shape = RoundedCornerShape(6.dp),
                        color = HextechSurface,
                        border = BorderStroke(1.dp, HextechGold.copy(alpha = 0.6f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Abajo", tint = HextechGold, modifier = Modifier.size(20.dp))
                        }
                    }
                }

                // Controles de Tamaño de Cuadro (Agrandar / Reducir) y Selector de Paso
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Tamaño de Detección:",
                        color = TextPrimary,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { modify(deltaSize = stepFactor) },
                            modifier = Modifier.weight(1f).height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B3854)),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("Agrandar", color = HextechCyan, fontSize = 8.5.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Button(
                            onClick = { modify(deltaSize = -stepFactor) },
                            modifier = Modifier.weight(1f).height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E1C22)),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Remove, contentDescription = null, tint = DangerRed, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("Reducir", color = DangerRed, fontSize = 8.5.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Selector de Paso de Ajuste
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Paso:", color = TextMuted, fontSize = 8.sp)
                        val steps = listOf(
                            Pair("Fino 0.1%", 0.001f),
                            Pair("Normal 0.5%", 0.005f),
                            Pair("Rápido 1%", 0.010f)
                        )
                        steps.forEach { (label, value) ->
                            val isSel = stepFactor == value
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (isSel) HextechGold.copy(alpha = 0.25f) else HextechSurface)
                                    .border(0.6.dp, if (isSel) HextechGold else HextechCardBorder, RoundedCornerShape(4.dp))
                                    .clickable { stepFactor = value }
                                    .padding(vertical = 2.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    color = if (isSel) HextechGold else TextMuted,
                                    fontSize = 7.5.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // BOTONES DE ACCIÓN: COPIAR COORDENADAS, RESTABLECER, GUARDAR
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Botón Copiar Coordenadas
                Button(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("WildRift_Vision_Calibration", config.toFormattedCoordinatesString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "📋 Coordenadas copiadas al portapapeles", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier.weight(1.3f).height(34.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Copiar Coordenadas", color = HextechDarkBg, fontSize = 9.sp, fontWeight = FontWeight.Black)
                    }
                }

                // Botón Restablecer
                Button(
                    onClick = {
                        DraftVisionScanner.resetCalibration(context)
                        config = DraftVisionScanner.calibrationConfig
                        Toast.makeText(context, "Valores restablecidos de fábrica", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(0.9f).height(34.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurface),
                    border = BorderStroke(1.dp, HextechCardBorder),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    Text("Restablecer", color = TextMuted, fontSize = 8.5.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

private fun Float.format(digits: Int): String = "%.${digits}f".format(java.util.Locale.US, this)
