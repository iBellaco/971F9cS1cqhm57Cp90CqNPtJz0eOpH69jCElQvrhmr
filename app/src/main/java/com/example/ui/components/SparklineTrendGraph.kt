package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*

/**
 * Micro-Gráfico de Tendencia (Sparkline) para tarjetas de Campeones en la Tier List.
 * Dibuja una curva suavizada con relleno degradado y punto de inflexión neón.
 */
@Composable
fun SparklineTrendGraph(
    winrate: Double,
    delta: Double,
    modifier: Modifier = Modifier
        .width(52.dp)
        .height(22.dp)
) {
    val roundedWinrate = Math.round(winrate * 100.0) / 100.0
    val roundedDelta = Math.round(delta * 100.0) / 100.0
    val isPositive = roundedDelta >= 0
    val trendColor = if (isPositive) Color(0xFF00FF7F) else Color(0xFFFF453A)
    val glowColor = if (isPositive) Color(0xFF00E5FF) else Color(0xFFFF6B6B)

    // Calculamos 5 puntos representativos de la tendencia en el meta (precisión de 2 decimales)
    val base = roundedWinrate.toFloat()
    val d = roundedDelta.toFloat().coerceIn(-4f, 4f)
    val points = listOf(
        base - d * 1.4f,
        base - d * 0.8f,
        base - d * 0.3f,
        base + d * 0.2f,
        base + d * 0.6f
    )

    val minVal = points.minOrNull() ?: 45f
    val maxVal = (points.maxOrNull() ?: 55f).coerceAtLeast(minVal + 0.5f)

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val padding = 2f

        val coords = points.mapIndexed { index, value ->
            val x = padding + (index.toFloat() / (points.size - 1)) * (width - 2 * padding)
            val normalizedY = ((value - minVal) / (maxVal - minVal)).coerceIn(0f, 1f)
            val y = height - padding - (normalizedY * (height - 2 * padding))
            Offset(x, y)
        }

        if (coords.size >= 2) {
            // Camino suave con curvas de Bezier
            val linePath = Path().apply {
                moveTo(coords[0].x, coords[0].y)
                for (i in 0 until coords.size - 1) {
                    val p0 = coords[i]
                    val p1 = coords[i + 1]
                    val controlX = (p0.x + p1.x) / 2f
                    cubicTo(controlX, p0.y, controlX, p1.y, p1.x, p1.y)
                }
            }

            // Camino cerrado para el relleno con gradiente
            val fillPath = Path().apply {
                addPath(linePath)
                lineTo(coords.last().x, height)
                lineTo(coords.first().x, height)
                close()
            }

            // 1. Dibujar relleno translúcido degradado
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        trendColor.copy(alpha = 0.35f),
                        trendColor.copy(alpha = 0.02f)
                    ),
                    startY = 0f,
                    endY = height
                )
            )

            // 2. Dibujar línea de tendencia
            drawPath(
                path = linePath,
                color = trendColor,
                style = Stroke(
                    width = 1.8.dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )

            // 3. Punto final con halo de brillo neón
            val lastPoint = coords.last()
            drawCircle(
                color = glowColor.copy(alpha = 0.45f),
                radius = 3.5.dp.toPx(),
                center = lastPoint
            )
            drawCircle(
                color = trendColor,
                radius = 1.8.dp.toPx(),
                center = lastPoint
            )
        }
    }
}
