package com.example.ui.components

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import com.example.util.tr
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

@Composable
fun HextechOrbButton(
    isActive: Boolean,
    onToggle: () -> Unit,
    size: Dp = 230.dp,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "hextech_orb")
    
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val counterRotationAngle by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "counter_rotation"
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isActive) 1.05f else 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(
        modifier = modifier
            .size(size)
            .scale(pulseScale),
        contentAlignment = Alignment.Center
    ) {
        // Outer Glowing Rings and Hextech Arcs
        Canvas(modifier = Modifier.size(size)) {
            val center = Offset(this.size.width / 2f, this.size.height / 2f)
            val radius = this.size.width / 2f - 8.dp.toPx()

            // Outer subtle glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = if (isActive) {
                        listOf(HextechCyanLight.copy(alpha = 0.35f), Color.Transparent)
                    } else {
                        listOf(HextechGold.copy(alpha = 0.25f), Color.Transparent)
                    },
                    center = center,
                    radius = radius + 15.dp.toPx()
                ),
                radius = radius + 10.dp.toPx(),
                center = center
            )

            // Outer Metallic Ring
            drawCircle(
                color = if (isActive) HextechCyanLight else HextechGold,
                radius = radius,
                center = center,
                style = Stroke(width = 3.dp.toPx())
            )

            // Outer Hextech Orbiting Arcs
            drawArc(
                color = if (isActive) HextechCyan else HextechGoldGlow,
                startAngle = rotationAngle,
                sweepAngle = 70f,
                useCenter = false,
                topLeft = Offset(8.dp.toPx(), 8.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = if (isActive) HextechCyan else HextechGoldGlow,
                startAngle = rotationAngle + 180f,
                sweepAngle = 70f,
                useCenter = false,
                topLeft = Offset(8.dp.toPx(), 8.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )

            // Inner Cyan Accent Ring
            val innerRadius = radius - 16.dp.toPx()
            drawCircle(
                color = HextechCyan.copy(alpha = 0.6f),
                radius = innerRadius,
                center = center,
                style = Stroke(width = 2.dp.toPx())
            )

            // Counter-rotating Inner Arcs
            drawArc(
                color = HextechCyanLight,
                startAngle = counterRotationAngle,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(24.dp.toPx(), 24.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(innerRadius * 2, innerRadius * 2),
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = HextechCyanLight,
                startAngle = counterRotationAngle + 180f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(24.dp.toPx(), 24.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(innerRadius * 2, innerRadius * 2),
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Inner 3D Central Core Button
        val coreSize = size - 52.dp
        Box(
            modifier = Modifier
                .size(coreSize)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = if (isActive) {
                            listOf(Color(0xFF003852), Color(0xFF071B2B), HextechDarkBg)
                        } else {
                            listOf(Color(0xFF0F1E36), Color(0xFF091426), HextechDarkBg)
                        }
                    )
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true, color = HextechCyanLight),
                    onClick = onToggle
                )
                .testTag("hextech_activate_button"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isActive) "DETENER" else "ACTIVAR",
                color = if (isActive) HextechCyanLight else HextechCyan,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 3.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
