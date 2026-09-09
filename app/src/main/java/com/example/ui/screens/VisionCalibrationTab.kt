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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
                    text = "Ajusta en tiempo real las coordenadas de escaneo de Draft",
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

        // SECCIÓN 1: INTERRUPTORES DE VISIBILIDAD (ACTIVAR/DESACTIVAR CAPTURAS)
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
                    Text("Nombres e Invocadores (OCR)", color = TextPrimary, fontSize = 11.sp)
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
                    Text("Hechizos de Invocador", color = TextPrimary, fontSize = 11.sp)
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

        // SECCIÓN 2: CALIBRACIÓN DE AVATARES (IZQUIERDA Y DERECHA)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "🎮 Posición Horizontal de Avatares (Eje X)",
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Mueve los cuadros hacia los bordes de la pantalla",
                    color = TextSecondary,
                    fontSize = 9.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Avatar Aliado (Izquierda)
                StepAdjusterRow(
                    label = "Aliados (Izquierda X)",
                    valueFormatted = "${"%.3f".format(Locale.US, config.allyAvatarCenterX)} (~${(config.allyAvatarCenterX * 100).toInt()}%)",
                    onStepLeft = {
                        val newX = (config.allyAvatarCenterX - 0.005f).coerceIn(0.04f, 0.35f)
                        DraftVisionScanner.updateCalibration(context, config.copy(allyAvatarCenterX = newX))
                    },
                    onStepRight = {
                        val newX = (config.allyAvatarCenterX + 0.005f).coerceIn(0.04f, 0.35f)
                        DraftVisionScanner.updateCalibration(context, config.copy(allyAvatarCenterX = newX))
                    },
                    leftHint = "Más Izquierda ◀",
                    rightHint = "▶ Derecha"
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Avatar Enemigo (Derecha)
                StepAdjusterRow(
                    label = "Rivales (Derecha X)",
                    valueFormatted = "${"%.3f".format(Locale.US, config.enemyAvatarCenterX)} (~${(config.enemyAvatarCenterX * 100).toInt()}%)",
                    onStepLeft = {
                        val newX = (config.enemyAvatarCenterX - 0.005f).coerceIn(0.70f, 0.98f)
                        DraftVisionScanner.updateCalibration(context, config.copy(enemyAvatarCenterX = newX))
                    },
                    onStepRight = {
                        val newX = (config.enemyAvatarCenterX + 0.005f).coerceIn(0.70f, 0.98f)
                        DraftVisionScanner.updateCalibration(context, config.copy(enemyAvatarCenterX = newX))
                    },
                    leftHint = "◀ Izquierda",
                    rightHint = "Más Derecha ▶"
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Diámetro / Tamaño del Cuadro Avatar
                StepAdjusterRow(
                    label = "Tamaño Cuadros (Alto %)",
                    valueFormatted = "${"%.3f".format(Locale.US, config.avatarDiameterRatio)} (~${(config.avatarDiameterRatio * 100).toInt()}%)",
                    onStepLeft = {
                        val newD = (config.avatarDiameterRatio - 0.004f).coerceIn(0.070f, 0.180f)
                        DraftVisionScanner.updateCalibration(context, config.copy(avatarDiameterRatio = newD))
                    },
                    onStepRight = {
                        val newD = (config.avatarDiameterRatio + 0.004f).coerceIn(0.070f, 0.180f)
                        DraftVisionScanner.updateCalibration(context, config.copy(avatarDiameterRatio = newD))
                    },
                    leftHint = "Reducir -",
                    rightHint = "+ Agrandar"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 3: CALIBRACIÓN VERTICAL (EJE Y - SLOTS 1 A 5)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "↕️ Desplazamiento Vertical Global (Eje Y)",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Sube o baja todos los cuadros de un bando a la vez",
                    color = TextSecondary,
                    fontSize = 9.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Mover Aliados arriba / abajo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Slots Aliados (Y)", color = TextPrimary, fontSize = 11.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        OutlinedButton(
                            onClick = {
                                val updated = config.allySlotYRatios.map { (it - 0.005f).coerceIn(0.05f, 0.95f) }.toMutableList()
                                DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = updated))
                            },
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Icon(Icons.Default.ArrowUpward, contentDescription = "Arriba", modifier = Modifier.size(14.dp))
                            Text("Arriba", fontSize = 10.sp)
                        }

                        OutlinedButton(
                            onClick = {
                                val updated = config.allySlotYRatios.map { (it + 0.005f).coerceIn(0.05f, 0.95f) }.toMutableList()
                                DraftVisionScanner.updateCalibration(context, config.copy(allySlotYRatios = updated))
                            },
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Icon(Icons.Default.ArrowDownward, contentDescription = "Abajo", modifier = Modifier.size(14.dp))
                            Text("Abajo", fontSize = 10.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Mover Rivales arriba / abajo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Slots Rivales (Y)", color = TextPrimary, fontSize = 11.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        OutlinedButton(
                            onClick = {
                                val updated = config.enemySlotYRatios.map { (it - 0.005f).coerceIn(0.05f, 0.95f) }.toMutableList()
                                DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = updated))
                            },
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Icon(Icons.Default.ArrowUpward, contentDescription = "Arriba", modifier = Modifier.size(14.dp))
                            Text("Arriba", fontSize = 10.sp)
                        }

                        OutlinedButton(
                            onClick = {
                                val updated = config.enemySlotYRatios.map { (it + 0.005f).coerceIn(0.05f, 0.95f) }.toMutableList()
                                DraftVisionScanner.updateCalibration(context, config.copy(enemySlotYRatios = updated))
                            },
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Icon(Icons.Default.ArrowDownward, contentDescription = "Abajo", modifier = Modifier.size(14.dp))
                            Text("Abajo", fontSize = 10.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 4: HECHIZOS DE INVOCADOR
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "✨ Hechizos de Invocador (Extremo Izquierdo)",
                    color = Color(0xFFFF9100),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))

                StepAdjusterRow(
                    label = "Posición X Hechizos",
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

                StepAdjusterRow(
                    label = "Tamaño Hechizo",
                    valueFormatted = "${"%.3f".format(Locale.US, config.spellSizeRatio)}",
                    onStepLeft = {
                        val newS = (config.spellSizeRatio - 0.003f).coerceIn(0.025f, 0.08f)
                        DraftVisionScanner.updateCalibration(context, config.copy(spellSizeRatio = newS))
                    },
                    onStepRight = {
                        val newS = (config.spellSizeRatio + 0.003f).coerceIn(0.025f, 0.08f)
                        DraftVisionScanner.updateCalibration(context, config.copy(spellSizeRatio = newS))
                    },
                    leftHint = "Menor -",
                    rightHint = "+ Mayor"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SECCIÓN 5: COPIAR / PEGAR CONFIGURACIÓN JSON (PARA EL AGENTE O PRÓXIMA ACTUALIZACIÓN)
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
