package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.service.screen.DraftVisionScanner
import com.example.service.screen.VisionCalibrationConfig
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale

@Composable
fun VisionCalibrationTab(
    modifier: Modifier = Modifier,
    isOverlay: Boolean = true
) {
    val context = LocalContext.current
    val config by DraftVisionScanner.calibrationConfig.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    var showIndividualEnemySlots by remember { mutableStateOf(false) }
    var showIndividualAllySlots by remember { mutableStateOf(false) }
    var showJsonEditor by remember { mutableStateOf(false) }
    var jsonInputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HextechDarkBg)
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {
        // Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "🎯 Calibración de Zonas de Detección",
                    color = HextechGold,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ajuste direccional (⬅️ ➡️ ⬆️ ⬇️) para Rivales y Aliados",
                    color = TextSecondary,
                    fontSize = 10.sp
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                // Botón Copiar Configuración
                IconButton(
                    onClick = {
                        val json = config.toJsonString(true)
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("WildRift_Vision_Config", json)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "📋 Coordenadas copiadas al portapapeles", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copiar Coordenadas",
                        tint = HextechCyan,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Botón Restaurar Predeterminados
                IconButton(
                    onClick = {
                        DraftVisionScanner.resetCalibration(context)
                        Toast.makeText(context, "Valores por defecto restaurados", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.RestartAlt,
                        contentDescription = "Restaurar por defecto",
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 1: CONTROL DIRECCIONAL COMPLETO - LADO RIVAL (ENEMIGOS)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFF5252).copy(alpha = 0.6f))
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "🔴 LADO RIVAL (Rivales / Derecha)",
                            color = Color(0xFFFF5252),
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Mueve los cuadros hacia Arriba, Abajo, Izquierda o Derecha",
                            color = TextSecondary,
                            fontSize = 9.5.sp
                        )
                    }
                    Text(
                        text = "X: ${"%.3f".format(Locale.US, config.enemyAvatarCenterX)}",
                        color = HextechGold,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Control Pad Direccional para Rivales
                DirectionalPad(
                    accentColor = Color(0xFFFF5252),
                    onUp = {
                        val updated = config.enemySlotYRatios.map { (it - 0.003f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = updated))
                    },
                    onUpFast = {
                        val updated = config.enemySlotYRatios.map { (it - 0.008f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = updated))
                    },
                    onDown = {
                        val updated = config.enemySlotYRatios.map { (it + 0.003f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = updated))
                    },
                    onDownFast = {
                        val updated = config.enemySlotYRatios.map { (it + 0.008f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = updated))
                    },
                    onLeft = {
                        val newX = (config.enemyAvatarCenterX - 0.003f).coerceIn(0.65f, 0.99f)
                        DraftVisionScanner.updateCalibration(context, config.copy(enemyAvatarCenterX = newX))
                    },
                    onLeftFast = {
                        val newX = (config.enemyAvatarCenterX - 0.008f).coerceIn(0.65f, 0.99f)
                        DraftVisionScanner.updateCalibration(context, config.copy(enemyAvatarCenterX = newX))
                    },
                    onRight = {
                        val newX = (config.enemyAvatarCenterX + 0.003f).coerceIn(0.65f, 0.99f)
                        DraftVisionScanner.updateCalibration(context, config.copy(enemyAvatarCenterX = newX))
                    },
                    onRightFast = {
                        val newX = (config.enemyAvatarCenterX + 0.008f).coerceIn(0.65f, 0.99f)
                        DraftVisionScanner.updateCalibration(context, config.copy(enemyAvatarCenterX = newX))
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Toggle para ajuste individual por slot rival
                OutlinedButton(
                    onClick = { showIndividualEnemySlots = !showIndividualEnemySlots },
                    modifier = Modifier.fillMaxWidth().height(26.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 1.dp)
                ) {
                    Text(
                        text = if (showIndividualEnemySlots) "Ocultar Slots Rivales 1 al 5" else "⚙️ Ajustar Altura de cada Slot Rival (1..5)",
                        fontSize = 9.5.sp,
                        color = Color(0xFFFF8A80)
                    )
                }

                if (showIndividualEnemySlots) {
                    Spacer(modifier = Modifier.height(4.dp))
                    config.enemySlotYRatios.forEachIndexed { index, yRatio ->
                        SlotYAdjusterRow(
                            label = "Rival Slot ${index + 1}",
                            yVal = yRatio,
                            onUp = {
                                val list = config.enemySlotYRatios.toMutableList()
                                list[index] = (list[index] - 0.003f).coerceIn(0.04f, 0.96f)
                                DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = list))
                            },
                            onDown = {
                                val list = config.enemySlotYRatios.toMutableList()
                                list[index] = (list[index] + 0.003f).coerceIn(0.04f, 0.96f)
                                DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = list))
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 2: CONTROL DIRECCIONAL COMPLETO - LADO ALIADO (TU EQUIPO)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f))
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "🔵 LADO ALIADO (Tu Equipo / Izquierda)",
                            color = HextechCyan,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Mueve los cuadros hacia Arriba, Abajo, Izquierda o Derecha",
                            color = TextSecondary,
                            fontSize = 9.5.sp
                        )
                    }
                    Text(
                        text = "X: ${"%.3f".format(Locale.US, config.allyAvatarCenterX)}",
                        color = HextechGold,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Control Pad Direccional para Aliados
                DirectionalPad(
                    accentColor = HextechCyan,
                    onUp = {
                        val updated = config.allySlotYRatios.map { (it - 0.003f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = updated))
                    },
                    onUpFast = {
                        val updated = config.allySlotYRatios.map { (it - 0.008f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = updated))
                    },
                    onDown = {
                        val updated = config.allySlotYRatios.map { (it + 0.003f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = updated))
                    },
                    onDownFast = {
                        val updated = config.allySlotYRatios.map { (it + 0.008f).coerceIn(0.04f, 0.96f) }.toMutableList()
                        DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = updated))
                    },
                    onLeft = {
                        val newX = (config.allyAvatarCenterX - 0.003f).coerceIn(0.02f, 0.35f)
                        DraftVisionScanner.updateCalibration(context, config.copy(allyAvatarCenterX = newX))
                    },
                    onLeftFast = {
                        val newX = (config.allyAvatarCenterX - 0.008f).coerceIn(0.02f, 0.35f)
                        DraftVisionScanner.updateCalibration(context, config.copy(allyAvatarCenterX = newX))
                    },
                    onRight = {
                        val newX = (config.allyAvatarCenterX + 0.003f).coerceIn(0.02f, 0.35f)
                        DraftVisionScanner.updateCalibration(context, config.copy(allyAvatarCenterX = newX))
                    },
                    onRightFast = {
                        val newX = (config.allyAvatarCenterX + 0.008f).coerceIn(0.02f, 0.35f)
                        DraftVisionScanner.updateCalibration(context, config.copy(allyAvatarCenterX = newX))
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Toggle para ajuste individual por slot aliado
                OutlinedButton(
                    onClick = { showIndividualAllySlots = !showIndividualAllySlots },
                    modifier = Modifier.fillMaxWidth().height(26.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 1.dp)
                ) {
                    Text(
                        text = if (showIndividualAllySlots) "Ocultar Slots Aliados 1 al 5" else "⚙️ Ajustar Altura de cada Slot Aliado (1..5)",
                        fontSize = 9.5.sp,
                        color = HextechCyan
                    )
                }

                if (showIndividualAllySlots) {
                    Spacer(modifier = Modifier.height(4.dp))
                    config.allySlotYRatios.forEachIndexed { index, yRatio ->
                        SlotYAdjusterRow(
                            label = "Aliado Slot ${index + 1}",
                            yVal = yRatio,
                            onUp = {
                                val list = config.allySlotYRatios.toMutableList()
                                list[index] = (list[index] - 0.003f).coerceIn(0.04f, 0.96f)
                                DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = list))
                            },
                            onDown = {
                                val list = config.allySlotYRatios.toMutableList()
                                list[index] = (list[index] + 0.003f).coerceIn(0.04f, 0.96f)
                                DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = list))
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 3: TAMAÑO DE CUADROS Y HECHIZOS
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "📐 Tamaño de Cuadros y Hechizos",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Diámetro / Tamaño del Cuadro Avatar
                StepAdjusterRow(
                    label = "Tamaño Cuadro Avatares",
                    valueFormatted = "${"%.3f".format(Locale.US, config.avatarDiameterRatio)} (~${(config.avatarDiameterRatio * 100).toInt()}%)",
                    onStepLeft = {
                        val newD = (config.avatarDiameterRatio - 0.004f).coerceIn(0.060f, 0.200f)
                        DraftVisionScanner.updateCalibration(context, config.copy(avatarDiameterRatio = newD))
                    },
                    onStepRight = {
                        val newD = (config.avatarDiameterRatio + 0.004f).coerceIn(0.060f, 0.200f)
                        DraftVisionScanner.updateCalibration(context, config.copy(avatarDiameterRatio = newD))
                    },
                    leftHint = "Reducir -",
                    rightHint = "+ Agrandar"
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Posición X Hechizos
                StepAdjusterRow(
                    label = "Hechizos Aliados (X)",
                    valueFormatted = "${"%.3f".format(Locale.US, config.spellLeftRatio)}",
                    onStepLeft = {
                        val newL = (config.spellLeftRatio - 0.003f).coerceIn(0.005f, 0.10f)
                        DraftVisionScanner.updateCalibration(context, config.copy(spellLeftRatio = newL))
                    },
                    onStepRight = {
                        val newL = (config.spellLeftRatio + 0.003f).coerceIn(0.005f, 0.10f)
                        DraftVisionScanner.updateCalibration(context, config.copy(spellLeftRatio = newL))
                    },
                    leftHint = "◀ Izq",
                    rightHint = "Der ▶"
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Tamaño Hechizos
                StepAdjusterRow(
                    label = "Tamaño Cuadro Hechizo",
                    valueFormatted = "${"%.3f".format(Locale.US, config.spellSizeRatio)}",
                    onStepLeft = {
                        val newS = (config.spellSizeRatio - 0.003f).coerceIn(0.020f, 0.08f)
                        DraftVisionScanner.updateCalibration(context, config.copy(spellSizeRatio = newS))
                    },
                    onStepRight = {
                        val newS = (config.spellSizeRatio + 0.003f).coerceIn(0.020f, 0.08f)
                        DraftVisionScanner.updateCalibration(context, config.copy(spellSizeRatio = newS))
                    },
                    leftHint = "Menor -",
                    rightHint = "+ Mayor"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 4: INTERRUPTORES DE VISIBILIDAD (CAPAS)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "👁️ Capas Visibles en Diagnóstico (Overlay)",
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Toggle Avatares
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Avatares de Campeón", color = TextPrimary, fontSize = 11.sp)
                    Switch(
                        checked = config.showAvatarBoxes,
                        onCheckedChange = {
                            val updated = config.copy(showAvatarBoxes = it)
                            DraftVisionScanner.updateCalibration(context, updated)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = HextechCyan,
                            checkedTrackColor = HextechCyan.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.size(36.dp)
                    )
                }

                // Toggle Nombres / Roles (OCR)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Nombres de Campeón (OCR)", color = TextPrimary, fontSize = 11.sp)
                        Text("Aliados (Invocador+Champ) / Rivales (Solo Champ)", color = TextMuted, fontSize = 9.sp)
                    }
                    Switch(
                        checked = config.showNameBoxes,
                        onCheckedChange = {
                            val updated = config.copy(showNameBoxes = it)
                            DraftVisionScanner.updateCalibration(context, updated)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = HextechGold,
                            checkedTrackColor = HextechGold.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.size(36.dp)
                    )
                }

                // Toggle Hechizos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Hechizos de Invocador", color = TextPrimary, fontSize = 11.sp)
                        Text("Exclusivo bando aliado", color = TextMuted, fontSize = 9.sp)
                    }
                    Switch(
                        checked = config.showSpellBoxes,
                        onCheckedChange = {
                            val updated = config.copy(showSpellBoxes = it)
                            DraftVisionScanner.updateCalibration(context, updated)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFFFF9100),
                            checkedTrackColor = Color(0xFFFF9100).copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 5: COPIAR / PEGAR CONFIGURACIÓN JSON
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "📋 Registro y Respaldo de Coordenadas",
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Copia este texto y pégalo en el chat para registrarlo en la próxima actualización:",
                    color = TextSecondary,
                    fontSize = 9.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(HextechDarkBg)
                        .border(1.dp, HextechCardBorder, RoundedCornerShape(6.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = config.toJsonString(true),
                        color = HextechGold,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = {
                            val json = config.toJsonString(true)
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("WildRift_Vision_Config", json)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "✅ ¡Coordenadas copiadas! Pégalas en el chat", Toast.LENGTH_LONG).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                        modifier = Modifier.weight(1f).height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Copiar Todo", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = HextechDarkBg)
                    }

                    OutlinedButton(
                        onClick = {
                            showJsonEditor = !showJsonEditor
                            if (showJsonEditor) {
                                jsonInputText = config.toJsonString(true)
                            }
                        },
                        modifier = Modifier.weight(1f).height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.ContentPaste, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (showJsonEditor) "Ocultar" else "Pegar JSON", fontSize = 11.sp)
                    }
                }

                if (showJsonEditor) {
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = jsonInputText,
                        onValueChange = { jsonInputText = it },
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 10.sp, fontFamily = FontFamily.Monospace, color = TextPrimary),
                        placeholder = { Text("Pega aquí un JSON de coordenadas...", fontSize = 10.sp, color = TextMuted) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        onClick = {
                            try {
                                val parsed = VisionCalibrationConfig.fromJsonString(jsonInputText)
                                DraftVisionScanner.updateCalibration(context, parsed)
                                Toast.makeText(context, "✅ Coordenadas aplicadas correctamente", Toast.LENGTH_SHORT).show()
                                showJsonEditor = false
                            } catch (e: Exception) {
                                Toast.makeText(context, "JSON inválido: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        modifier = Modifier.fillMaxWidth().height(30.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 2.dp)
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Aplicar JSON Importado", fontSize = 11.sp, color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

/**
 * Control Pad Direccional (DPad) 4-Vías con botones fino y rápido para Arriba, Abajo, Izquierda, Derecha.
 */
@Composable
private fun DirectionalPad(
    accentColor: Color,
    onUp: () -> Unit,
    onUpFast: () -> Unit,
    onDown: () -> Unit,
    onDownFast: () -> Unit,
    onLeft: () -> Unit,
    onLeftFast: () -> Unit,
    onRight: () -> Unit,
    onRightFast: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(HextechDarkBg)
            .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Fila Superior: ARRIBA
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(
                onClick = onUpFast,
                modifier = Modifier.size(26.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Subir Rápido", tint = accentColor, modifier = Modifier.size(18.dp))
            }

            Button(
                onClick = onUp,
                colors = ButtonDefaults.buttonColors(containerColor = accentColor.copy(alpha = 0.25f)),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 2.dp),
                modifier = Modifier.height(26.dp).border(1.dp, accentColor, RoundedCornerShape(4.dp))
            ) {
                Icon(Icons.Default.ArrowUpward, contentDescription = "Subir", tint = accentColor, modifier = Modifier.size(13.dp))
                Spacer(modifier = Modifier.width(3.dp))
                Text("ARRIBA (▲)", fontSize = 9.5.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
            }

            IconButton(
                onClick = onUpFast,
                modifier = Modifier.size(26.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Subir Rápido", tint = accentColor, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Fila Central: IZQUIERDA | CENTRO | DERECHA
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // IZQUIERDA
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                IconButton(
                    onClick = onLeftFast,
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Izquierda Rápido", tint = accentColor, modifier = Modifier.size(18.dp))
                }
                Button(
                    onClick = onLeft,
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor.copy(alpha = 0.25f)),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    modifier = Modifier.height(26.dp).border(1.dp, accentColor, RoundedCornerShape(4.dp))
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Izquierda", tint = accentColor, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("IZQ (◀)", fontSize = 9.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
                }
            }

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(accentColor))
            }

            // DERECHA
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Button(
                    onClick = onRight,
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor.copy(alpha = 0.25f)),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    modifier = Modifier.height(26.dp).border(1.dp, accentColor, RoundedCornerShape(4.dp))
                ) {
                    Text("DER (▶)", fontSize = 9.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = "Derecha", tint = accentColor, modifier = Modifier.size(12.dp))
                }
                IconButton(
                    onClick = onRightFast,
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Derecha Rápido", tint = accentColor, modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Fila Inferior: ABAJO
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(
                onClick = onDownFast,
                modifier = Modifier.size(26.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Bajar Rápido", tint = accentColor, modifier = Modifier.size(18.dp))
            }

            Button(
                onClick = onDown,
                colors = ButtonDefaults.buttonColors(containerColor = accentColor.copy(alpha = 0.25f)),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 2.dp),
                modifier = Modifier.height(26.dp).border(1.dp, accentColor, RoundedCornerShape(4.dp))
            ) {
                Icon(Icons.Default.ArrowDownward, contentDescription = "Abajo", tint = accentColor, modifier = Modifier.size(13.dp))
                Spacer(modifier = Modifier.width(3.dp))
                Text("ABAJO (▼)", fontSize = 9.5.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
            }

            IconButton(
                onClick = onDownFast,
                modifier = Modifier.size(26.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Bajar Rápido", tint = accentColor, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun SlotYAdjusterRow(
    label: String,
    yVal: Float,
    onUp: () -> Unit,
    onDown: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(label, color = TextPrimary, fontSize = 10.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text("${"%.3f".format(Locale.US, yVal)}", color = HextechGold, fontSize = 9.5.sp, fontFamily = FontFamily.Monospace)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            OutlinedButton(
                onClick = onUp,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 1.dp),
                modifier = Modifier.height(24.dp)
            ) {
                Icon(Icons.Default.ArrowUpward, contentDescription = "Subir", modifier = Modifier.size(11.dp))
                Text("▲", fontSize = 8.sp)
            }

            OutlinedButton(
                onClick = onDown,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 1.dp),
                modifier = Modifier.height(24.dp)
            ) {
                Icon(Icons.Default.ArrowDownward, contentDescription = "Bajar", modifier = Modifier.size(11.dp))
                Text("▼", fontSize = 8.sp)
            }
        }
    }
}

@Composable
private fun StepAdjusterRow(
    label: String,
    valueFormatted: String,
    onStepLeft: () -> Unit,
    onStepRight: () -> Unit,
    leftHint: String,
    rightHint: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(label, color = TextPrimary, fontSize = 11.sp, fontWeight = FontWeight.Medium)
            Text(valueFormatted, color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            OutlinedButton(
                onClick = onStepLeft,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                modifier = Modifier.height(28.dp)
            ) {
                Text(leftHint, fontSize = 9.5.sp)
            }

            OutlinedButton(
                onClick = onStepRight,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                modifier = Modifier.height(28.dp)
            ) {
                Text(rightHint, fontSize = 9.5.sp)
            }
        }
    }
}
