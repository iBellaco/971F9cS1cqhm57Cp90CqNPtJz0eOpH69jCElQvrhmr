package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Stop
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechCyanLight
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldGlow
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.util.tr
import kotlin.math.cos
import kotlin.math.sin

/**
 * Botón Orbe Hextech Rediseñado de Alto Nivel Visual
 * Inspirado en la forja de Piltover / Zaun y el Núcleo Hextech de Wild Rift:
 * - Anillo exterior rúnico dorado con grabado de nodos cardinales.
 * - Anillos orbitales giratorios duales con estelas de energía cyan y partículas de pulso.
 * - Núcleo 3D con halo de profundidad, switch de estado, icono de energía dinámico y subtítulo de acción.
 */
@Composable
fun HextechOrbButton(
    isActive: Boolean,
    onToggle: () -> Unit,
    enabled: Boolean = true,
    size: Dp = 230.dp,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "hextech_orb_anim")

    // Rotación suave del anillo exterior
    val outerRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 6000 else 14000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "outer_rotation"
    )

    // Contra-rotación rápida del anillo rúnico interior
    val innerRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 4500 else 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "inner_rotation"
    )

    // Pulsación de respiración orgánica de energía
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (isActive) 1.06f else 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isActive) 900 else 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "energy_pulse"
    )

    // Halo de brillo respirante
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = if (isActive) 0.45f else 0.20f,
        targetValue = if (isActive) 0.85f else 0.45f,
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
        // CANVAS DE ARTE HEXTECH: Resplandores, Runas, Anillos y Segmentos
        // ===================================================================
        Canvas(modifier = Modifier.size(size)) {
            val center = Offset(this.size.width / 2f, this.size.height / 2f)
            val outerRadius = this.size.width / 2f - 6.dp.toPx()

            // 1. Resplandor Cósmico Radial (Glow exterior)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = if (isActive) {
                        listOf(
                            HextechCyan.copy(alpha = glowAlpha),
                            HextechCyan.copy(alpha = glowAlpha * 0.4f),
                            Color.Transparent
                        )
                    } else {
                        listOf(
                            HextechGold.copy(alpha = glowAlpha * 0.8f),
                            HextechGoldGlow.copy(alpha = glowAlpha * 0.3f),
                            Color.Transparent
                        )
                    },
                    center = center,
                    radius = outerRadius + 14.dp.toPx()
                ),
                radius = outerRadius + 14.dp.toPx(),
                center = center
            )

            // 2. Anillo Base Metálico Dorado Piltoviano
            drawCircle(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        HextechGold,
                        HextechGoldLight,
                        HextechGoldGlow,
                        HextechGold,
                        HextechGoldLight,
                        HextechGold
                    ),
                    center = center
                ),
                radius = outerRadius,
                center = center,
                style = Stroke(width = 2.5.dp.toPx())
            )

            // 3. Arcos Orbitales Exteriores (Rotación en sentido horario)
            val arcRectSize = Size(outerRadius * 2, outerRadius * 2)
            val arcTopLeft = Offset(center.x - outerRadius, center.y - outerRadius)

            // Arco 1
            drawArc(
                brush = Brush.horizontalGradient(
                    listOf(secondaryEnergyColor.copy(alpha = 0.2f), primaryEnergyColor)
                ),
                startAngle = outerRotation,
                sweepAngle = 75f,
                useCenter = false,
                topLeft = arcTopLeft,
                size = arcRectSize,
                style = Stroke(width = 4.5.dp.toPx(), cap = StrokeCap.Round)
            )

            // Arco 2 (Opuesto)
            drawArc(
                brush = Brush.horizontalGradient(
                    listOf(secondaryEnergyColor.copy(alpha = 0.2f), primaryEnergyColor)
                ),
                startAngle = outerRotation + 180f,
                sweepAngle = 75f,
                useCenter = false,
                topLeft = arcTopLeft,
                size = arcRectSize,
                style = Stroke(width = 4.5.dp.toPx(), cap = StrokeCap.Round)
            )

            // 4. Nodos de Poder Cardinales (4 orbes en 0°, 90°, 180°, 270°)
            val nodeDistance = outerRadius
            for (i in 0 until 4) {
                val angleRad = Math.toRadians((outerRotation + i * 90).toDouble())
                val nodeX = center.x + (nodeDistance * cos(angleRad)).toFloat()
                val nodeY = center.y + (nodeDistance * sin(angleRad)).toFloat()

                // Glow del nodo
                drawCircle(
                    color = primaryEnergyColor.copy(alpha = 0.8f),
                    radius = 3.5.dp.toPx(),
                    center = Offset(nodeX, nodeY)
                )
                // Centro blanco brillante del nodo
                drawCircle(
                    color = Color.White,
                    radius = 1.8.dp.toPx(),
                    center = Offset(nodeX, nodeY)
                )
            }

            // 5. Anillo Intermedio Rúnico (Segmentado / Dash)
            val midRadius = outerRadius - 14.dp.toPx()
            drawCircle(
                color = if (isActive) HextechCyan.copy(alpha = 0.5f) else HextechGold.copy(alpha = 0.35f),
                radius = midRadius,
                center = center,
                style = Stroke(
                    width = 1.8.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
                )
            )

            // 6. Arcos de Energía Interiores en Contra-rotación (Antihorario)
            val innerArcSize = Size(midRadius * 2, midRadius * 2)
            val innerArcTopLeft = Offset(center.x - midRadius, center.y - midRadius)

            drawArc(
                color = if (isActive) HextechCyanLight else HextechGoldLight,
                startAngle = innerRotation,
                sweepAngle = 55f,
                useCenter = false,
                topLeft = innerArcTopLeft,
                size = innerArcSize,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = if (isActive) HextechCyanLight else HextechGoldLight,
                startAngle = innerRotation + 180f,
                sweepAngle = 55f,
                useCenter = false,
                topLeft = innerArcTopLeft,
                size = innerArcSize,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )

            // 7. Borde Biselado Interior del Núcleo Central
            val coreBorderRadius = outerRadius - 26.dp.toPx()
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        primaryEnergyColor.copy(alpha = 0.8f),
                        secondaryEnergyColor.copy(alpha = 0.3f)
                    ),
                    center = center,
                    radius = coreBorderRadius
                ),
                radius = coreBorderRadius,
                center = center,
                style = Stroke(width = 2.dp.toPx())
            )
        }

        // ===================================================================
        // NÚCLEO 3D INTERIOR INTERACTIVO
        // ===================================================================
        val coreSize = size - 58.dp

        Box(
            modifier = Modifier
                .size(coreSize)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = if (isActive) {
                            listOf(
                                Color(0xFF02364F), // Azul eléctrico activo central
                                Color(0xFF051D2D), // Azul profundo intermedio
                                Color(0xFF000000)      // Borde exterior oscuro
                            )
                        } else {
                            listOf(
                                Color(0xFF1E170A), // Ámbar oscuro forja inactivo
                                Color(0xFF0F1724), // Azul marino oscuro
                                Color(0xFF000000)
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
                modifier = Modifier.padding(10.dp)
            ) {
                // Icono de Estado / Energía en la parte superior del núcleo
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(
                            if (!enabled) TextMuted.copy(alpha = 0.15f)
                            else if (isActive) HextechCyan.copy(alpha = 0.20f)
                            else HextechGold.copy(alpha = 0.15f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isActive) Icons.Default.PowerSettingsNew else Icons.Default.PowerSettingsNew,
                        contentDescription = null,
                        tint = if (!enabled) TextMuted else primaryEnergyColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Texto Principal: ACTIVAR / DETENER con tipografía Hextech de alto contraste
                Text(
                    text = if (!enabled) tr("ACTIVAR") else if (isActive) tr("DETENER") else tr("ACTIVAR"),
                    color = if (!enabled) TextMuted else if (isActive) HextechCyanLight else HextechGoldLight,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.5.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Subtexto o Indicador de Estado Táctico
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(if (!enabled) TextMuted else if (isActive) HextechCyanLight else HextechGold)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = if (!enabled) tr("Desactivado") else if (isActive) tr("ONLINE") else tr("Toca para iniciar"),
                        color = if (!enabled) TextMuted else if (isActive) HextechCyan.copy(alpha = 0.9f) else TextMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}
