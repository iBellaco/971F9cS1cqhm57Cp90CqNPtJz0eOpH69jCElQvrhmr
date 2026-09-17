package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AppThemeManager
import com.example.ui.theme.TextMuted
import com.example.util.tr

/**
 * Botón de Iniciar / Campaña Estilo Insignia / Escudo Hexagonal (Campaign Map Abyss 5V5 Style):
 * - Insignia vertical facetada con corona superior ornamental y bordes dorados/regionales biselados.
 * - Fondo dinámico que cambia automáticamente según la región y tema activo de Runaterra (Piltover, Aguas Estancadas, Bandle, Demacia, El Vacío, etc.).
 * - Núcleo de energía interactivo con efecto de pulso y tipografía táctica de alto impacto.
 */
@Composable
fun HextechOrbButton(
    isActive: Boolean,
    onToggle: () -> Unit,
    enabled: Boolean = true,
    width: Dp = 210.dp,
    height: Dp = 260.dp,
    modifier: Modifier = Modifier
) {
    val theme = AppThemeManager.currentTheme
    val primaryColor = theme.primary
    val primaryLight = theme.primaryLight
    val primaryDarkColor = theme.primaryDark
    val secondaryColor = theme.secondary
    val secondaryLight = theme.secondaryLight
    val regionBg = theme.background
    val surfaceColor = theme.surface

    val infiniteTransition = rememberInfiniteTransition(label = "campaign_shield_anim")

    // Pulsación de respiración orgánica de energía
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (isActive) 1.04f else 1.01f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 900 else 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "energy_pulse"
    )

    // Halo de brillo respirante
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = if (isActive) 0.65f else 0.35f,
        targetValue = if (isActive) 0.95f else 0.60f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 900 else 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    val energyTint by animateColorAsState(
        targetValue = if (isActive) primaryLight else secondaryLight,
        animationSpec = tween(400),
        label = "energy_tint"
    )

    Box(
        modifier = modifier
            .size(width = width, height = height)
            .scale(pulseScale),
        contentAlignment = Alignment.Center
    ) {
        // ===================================================================
        // CANVAS DE ARTE CAMPAIGN MAP SHIELD (ESTILO ABYSS 5V5 / RITMO LOL)
        // ===================================================================
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val crownHeight = 24.dp.toPx()
            val pad = 8.dp.toPx()

            // 1. Resplandor Cósmico / Regional de Fondo
            drawCircle(
                brush = Brush.radialGradient(
                    colors = if (isActive) {
                        listOf(
                            primaryColor.copy(alpha = glowAlpha),
                            primaryLight.copy(alpha = glowAlpha * 0.4f),
                            Color.Transparent
                        )
                    } else {
                        listOf(
                            secondaryColor.copy(alpha = glowAlpha * 0.7f),
                            secondaryLight.copy(alpha = glowAlpha * 0.2f),
                            Color.Transparent
                        )
                    },
                    center = Offset(w / 2f, h / 2f),
                    radius = w * 0.55f
                ),
                radius = w * 0.55f,
                center = Offset(w / 2f, h / 2f)
            )

            // 2. Trazado del Escudo Vertical con Corona Superior Ornamental (Abyss Style)
            val shieldPath = Path().apply {
                val cr = 14.dp.toPx()
                // Corona superior arqueada
                moveTo(w * 0.25f, crownHeight)
                quadraticBezierTo(w * 0.5f, crownHeight - 12.dp.toPx(), w * 0.75f, crownHeight)
                // Lados derechos
                lineTo(w - pad, crownHeight + 16.dp.toPx())
                lineTo(w - pad, h - pad - cr)
                lineTo(w - pad - cr, h - pad)
                // Punta inferior
                lineTo(w * 0.5f, h - 4.dp.toPx())
                lineTo(pad + cr, h - pad)
                lineTo(pad, h - pad - cr)
                lineTo(pad, crownHeight + 16.dp.toPx())
                close()
            }

            // Fondo dinámico de obsidiana tintado por la región/tema activo
            drawPath(
                path = shieldPath,
                brush = Brush.verticalGradient(
                    colors = if (isActive) {
                        listOf(
                            surfaceColor,
                            regionBg,
                            Color(0xFF02060D)
                        )
                    } else {
                        listOf(
                            surfaceColor.copy(alpha = 0.9f),
                            regionBg,
                            Color(0xFF050302)
                        )
                    }
                )
            )

            // Borde Metálico Biselado Exterior (Regional Theme Accent)
            drawPath(
                path = shieldPath,
                brush = Brush.linearGradient(
                    colors = listOf(
                        secondaryLight,
                        secondaryColor,
                        primaryColor,
                        secondaryColor,
                        secondaryLight
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(w, h)
                ),
                style = Stroke(width = 3.5.dp.toPx())
            )

            // Filigrana interior geométrica
            val innerPad = pad + 6.dp.toPx()
            val innerPath = Path().apply {
                val icr = 10.dp.toPx()
                moveTo(w * 0.28f, crownHeight + 8.dp.toPx())
                quadraticBezierTo(w * 0.5f, crownHeight - 4.dp.toPx(), w * 0.72f, crownHeight + 8.dp.toPx())
                lineTo(w - innerPad, crownHeight + 22.dp.toPx())
                lineTo(w - innerPad, h - innerPad - icr)
                lineTo(w - innerPad - icr, h - innerPad)
                lineTo(w * 0.5f, h - 10.dp.toPx())
                lineTo(innerPad + icr, h - innerPad)
                lineTo(innerPad, h - innerPad - icr)
                lineTo(innerPad, crownHeight + 22.dp.toPx())
                close()
            }
            drawPath(
                path = innerPath,
                color = primaryColor.copy(alpha = 0.45f),
                style = Stroke(width = 1.25.dp.toPx())
            )
        }

        // ===================================================================
        // CONTENEDOR TÁCTICO E INTERACTIVO DE CONTENIDO
        // ===================================================================
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = if (enabled) ripple(bounded = true, color = energyTint) else null,
                    onClick = onToggle
                )
                .testTag("hextech_activate_button"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                // Etiqueta de Región / Tema Dinámico (ej. CIUDAD DEL PROGRESO / PILTOVER)
                Text(
                    text = theme.regionTag.uppercase(),
                    color = secondaryLight,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Núcleo de Energía / Ícono Central con Resplandor
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.radialGradient(
                                colors = if (isActive) {
                                    listOf(primaryColor.copy(alpha = 0.5f), primaryDarkColor.copy(alpha = 0.2f))
                                } else {
                                    listOf(secondaryColor.copy(alpha = 0.4f), Color.Transparent)
                                }
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PowerSettingsNew,
                        contentDescription = null,
                        tint = if (!enabled) TextMuted else energyTint,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Título de Campaña / Modo (ej. INICIAR / ACTIVAR)
                Text(
                    text = if (!enabled) tr("ACTIVAR") else if (isActive) tr("DETENER") else tr("INICIAR"),
                    color = if (!enabled) TextMuted else if (isActive) Color.White else secondaryLight,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.0.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Subtítulo de Modo de Juego (ej. 5V5 • ONLINE)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(50))
                            .background(if (!enabled) TextMuted else if (isActive) Color(0xFF00FFC2) else secondaryColor)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (!enabled) tr("DESACTIVADO") else if (isActive) "5V5 • ONLINE" else "5V5 • ABYSS",
                        color = if (!enabled) TextMuted else if (isActive) Color(0xFF00FFC2) else TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.0.sp
                    )
                }
            }
        }
    }
}
