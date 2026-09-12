package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechCyanLight
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldGlow
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.TextMuted
import com.example.util.tr
import kotlin.math.cos
import kotlin.math.sin

/**
 * Botón Orbe Hextech de Alto Nivel Visual inspirado en League of Legends & Wild Rift:
 * - Engranajes mecánicos simétricos de alta precisión
 * - Anillo de latón pulido y biselado
 * - Cuchillas de obturador iris de latón dorado
 * - Anillo de runas arcanas flotantes en contra-rotación
 * - Núcleo de cristal resonante con tipografía de activación nítida
 */
@Composable
fun HextechOrbButton(
    isActive: Boolean,
    onToggle: () -> Unit,
    enabled: Boolean = true,
    size: Dp = 210.dp,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "hextech_orb_anim")

    // Rotación suave de los engranajes y anillo exterior
    val outerRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 7000 else 16000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "outer_rotation"
    )

    // Contra-rotación de las runas arcanas interiores
    val runesRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 5000 else 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "runes_rotation"
    )

    // Pulsación de respiración orgánica de energía
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (isActive) 1.05f else 1.025f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 900 else 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "energy_pulse"
    )

    // Halo de brillo respirante
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = if (isActive) 0.50f else 0.25f,
        targetValue = if (isActive) 0.90f else 0.50f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 900 else 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    // Transición de color del núcleo según estado
    val primaryEnergyColor by animateColorAsState(
        targetValue = if (isActive) HextechCyanLight else HextechGoldLight,
        animationSpec = tween(400),
        label = "primary_color"
    )

    val secondaryEnergyColor by animateColorAsState(
        targetValue = if (isActive) HextechCyan else HextechGold,
        animationSpec = tween(400),
        label = "secondary_color"
    )

    Box(
        modifier = modifier
            .size(size)
            .scale(pulseScale),
        contentAlignment = Alignment.Center
    ) {
        // ===================================================================
        // CANVAS DE ARTE HEXTECH SIMÉTRICO Y ELEGANTE
        // ===================================================================
        Canvas(modifier = Modifier.size(size)) {
            val center = Offset(this.size.width / 2f, this.size.height / 2f)
            val outerRadius = this.size.width / 2f - 18.dp.toPx()

            // 1. Resplandor Cósmico Radial (Glow exterior verde-azulado/dorado)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = if (isActive) {
                        listOf(
                            HextechCyan.copy(alpha = glowAlpha),
                            Color(0xFF00FFC2).copy(alpha = glowAlpha * 0.4f),
                            Color.Transparent
                        )
                    } else {
                        listOf(
                            HextechGold.copy(alpha = glowAlpha * 0.7f),
                            HextechGoldGlow.copy(alpha = glowAlpha * 0.3f),
                            Color.Transparent
                        )
                    },
                    center = center,
                    radius = outerRadius + 22.dp.toPx()
                ),
                radius = outerRadius + 22.dp.toPx(),
                center = center
            )

            // 2. Dientes de Engranaje Perimetrales (Steampunk Gear Cogs Simétricos)
            val cogCount = 18
            val cogInnerRadius = outerRadius - 2.dp.toPx()
            val cogOuterRadius = outerRadius + 7.dp.toPx()
            for (i in 0 until cogCount) {
                val baseAngle = outerRotation + (i * 360f / cogCount)
                val radStart = Math.toRadians((baseAngle - 4.5f).toDouble())
                val radEnd = Math.toRadians((baseAngle + 4.5f).toDouble())

                val p1 = Offset(center.x + (cogInnerRadius * cos(radStart)).toFloat(), center.y + (cogInnerRadius * sin(radStart)).toFloat())
                val p2 = Offset(center.x + (cogOuterRadius * cos(radStart)).toFloat(), center.y + (cogOuterRadius * sin(radStart)).toFloat())
                val p3 = Offset(center.x + (cogOuterRadius * cos(radEnd)).toFloat(), center.y + (cogOuterRadius * sin(radEnd)).toFloat())
                val p4 = Offset(center.x + (cogInnerRadius * cos(radEnd)).toFloat(), center.y + (cogInnerRadius * sin(radEnd)).toFloat())

                val cogPath = Path().apply {
                    moveTo(p1.x, p1.y)
                    lineTo(p2.x, p2.y)
                    lineTo(p3.x, p3.y)
                    lineTo(p4.x, p4.y)
                    close()
                }
                drawPath(
                    path = cogPath,
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFC8AA6E), Color(0xFF785A28), Color(0xFF32281E)),
                        start = p2,
                        end = p4
                    )
                )
            }

            // 3. Anillo de Latón Forjado
            drawCircle(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        HextechGold,
                        HextechGoldLight,
                        HextechGoldGlow,
                        Color(0xFF5A4018),
                        HextechGoldLight,
                        HextechGold
                    ),
                    center = center
                ),
                radius = outerRadius,
                center = center,
                style = Stroke(width = 3.dp.toPx())
            )

            // 4. Cuchillas de Obturador Iris (Aperture Blades)
            val bladeCount = 8
            val irisRadius = outerRadius - 14.dp.toPx()
            for (i in 0 until bladeCount) {
                val bladeAngle = runesRotation + (i * 360f / bladeCount)
                val rad = Math.toRadians(bladeAngle.toDouble())
                val radNext = Math.toRadians((bladeAngle + 45f).toDouble())

                val bp1 = Offset(center.x + (irisRadius * cos(rad)).toFloat(), center.y + (irisRadius * sin(rad)).toFloat())
                val bp2 = Offset(center.x + ((irisRadius * 0.68f) * cos(radNext)).toFloat(), center.y + ((irisRadius * 0.68f) * sin(radNext)).toFloat())

                drawLine(
                    brush = Brush.linearGradient(listOf(HextechGold.copy(alpha = 0.6f), Color.Transparent)),
                    start = bp1,
                    end = bp2,
                    strokeWidth = 1.5.dp.toPx()
                )
            }

            // 5. Anillo de Runas Arcanas Flotantes en Orbitación
            val runesRadius = outerRadius - 20.dp.toPx()
            drawCircle(
                color = primaryEnergyColor.copy(alpha = 0.35f),
                radius = runesRadius,
                center = center,
                style = Stroke(
                    width = 1.5.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(14f, 10f), 0f)
                )
            )

            // Dibujar 6 glifos rúnicos orbitales
            val glyphs = listOf("⚡", "ᚱ", "ᛟ", "ᚦ", "ᚠ", "ᛗ")
            for (i in glyphs.indices) {
                val gRad = Math.toRadians((runesRotation + i * 60).toDouble())
                val gx = center.x + (runesRadius * cos(gRad)).toFloat()
                val gy = center.y + (runesRadius * sin(gRad)).toFloat()

                drawCircle(
                    color = primaryEnergyColor.copy(alpha = 0.8f),
                    radius = 2.5.dp.toPx(),
                    center = Offset(gx, gy)
                )
            }

            // 6. Borde Biselado Interior del Núcleo Central
            val coreBorderRadius = outerRadius - 28.dp.toPx()
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        primaryEnergyColor.copy(alpha = 0.9f),
                        secondaryEnergyColor.copy(alpha = 0.4f)
                    ),
                    center = center,
                    radius = coreBorderRadius
                ),
                radius = coreBorderRadius,
                center = center,
                style = Stroke(width = 2.5.dp.toPx())
            )
        }

        // ===================================================================
        // NÚCLEO 3D INTERACTIVO CON CRISTAL HEXTECH
        // ===================================================================
        val coreSize = size - 76.dp

        Box(
            modifier = Modifier
                .size(coreSize)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = if (isActive) {
                            listOf(
                                Color(0xFF00FFC2).copy(alpha = 0.85f),
                                Color(0xFF02364F), // Azul eléctrico activo central
                                Color(0xFF051D2D), // Azul profundo intermedio
                                Color(0xFF000810)  // Borde exterior oscuro
                            )
                        } else {
                            listOf(
                                Color(0xFF052B35), // Verde azulado Zaun/Piltover
                                Color(0xFF091C26), // Azul marino profundo
                                Color(0xFF040A10)
                            )
                        }
                    )
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = if (enabled) ripple(bounded = true, color = primaryEnergyColor) else null,
                    onClick = onToggle
                )
                .testTag("hextech_activate_button"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                // Icono de Energía Hextech
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            if (!enabled) TextMuted.copy(alpha = 0.15f)
                            else if (isActive) HextechCyan.copy(alpha = 0.25f)
                            else HextechCyan.copy(alpha = 0.15f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PowerSettingsNew,
                        contentDescription = null,
                        tint = if (!enabled) TextMuted else primaryEnergyColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Texto Principal: ACTIVAR / DETENER con tipografía LoL estilizada
                Text(
                    text = if (!enabled) tr("ACTIVAR") else if (isActive) tr("DETENER") else tr("ACTIVAR"),
                    color = if (!enabled) TextMuted else if (isActive) Color.White else HextechCyanLight,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Indicador de Estado Táctico
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(if (!enabled) TextMuted else if (isActive) Color(0xFF00FFC2) else HextechGold)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = if (!enabled) tr("Desactivado") else if (isActive) tr("ONLINE") else tr("• Desactivado"),
                        color = if (!enabled) TextMuted else if (isActive) Color(0xFF00FFC2) else TextMuted,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}
