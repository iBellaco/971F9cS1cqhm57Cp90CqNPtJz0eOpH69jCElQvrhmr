package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.service.screen.VisionCalibrationConfig
import com.example.service.screen.DraftVisionScanner

@Composable
fun ScannerDebugOverlay(
    config: VisionCalibrationConfig = DraftVisionScanner.calibrationConfigFlow.collectAsStateWithLifecycle().value,
    overlayRect: android.graphics.Rect?
) {
    val currentConfig by DraftVisionScanner.calibrationConfigFlow.collectAsStateWithLifecycle()
    val debugMatches by DraftVisionScanner.debugVisualMatches.collectAsStateWithLifecycle()
    val tenthPickOnly by DraftVisionScanner.showTenthPickOnly.collectAsStateWithLifecycle()
    val activeSide by DraftVisionScanner.activeTenthPickSide.collectAsStateWithLifecycle()
    val density = LocalDensity.current
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        
        val avatarDiameter = h * currentConfig.avatarDiameterRatio
        val topDiameter = h * currentConfig.topAvatarDiameterRatio
        val topRadius = topDiameter / 2f
        val topY = h * currentConfig.topAvatarYRatio
        
        val allyTextPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.parseColor("#00E5FF")
            textSize = with(density) { 10.sp.toPx() }
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
            setShadowLayer(4f, 0f, 0f, android.graphics.Color.BLACK)
        }

        val enemyTextPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.parseColor("#FF5252")
            textSize = with(density) { 10.sp.toPx() }
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
            setShadowLayer(4f, 0f, 0f, android.graphics.Color.BLACK)
        }

        val goldTextPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.parseColor("#C89B3C")
            textSize = with(density) { 10.sp.toPx() }
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
            setShadowLayer(4f, 0f, 0f, android.graphics.Color.BLACK)
        }

        if (tenthPickOnly) {
            // MODO EXCLUSIVO 10º PICK: Dibujar el anillo y guías exteriores en el 5º slot inferior (izquierdo o derecho)
            val drawAlly10 = (activeSide == null || activeSide == 0)
            val drawEnemy10 = (activeSide == null || activeSide == 1)
            val slotRadius = avatarDiameter / 2f

            if (drawAlly10) {
                val ally5X = w * currentConfig.allyAvatarCenterX
                val ally5Y = h * currentConfig.allySlotYRatios.getOrElse(4) { 0.72f }
                val isSelected = (activeSide == 0)

                // Anillo delimitador exterior del avatar en slot inferior izquierdo (Aliado 5)
                drawCircle(
                    color = if (isSelected) Color(0xFF00E5FF) else Color(0x8800E5FF),
                    center = Offset(ally5X, ally5Y),
                    radius = slotRadius,
                    style = Stroke(width = if (isSelected) 2.5f else 1.5f)
                )

                // Guías exteriores tipo retícula (ticks)
                val tickStart = slotRadius + 2f
                val tickEnd = slotRadius + 8f
                val tickColor = if (isSelected) Color(0xFF00E5FF) else Color(0x8800E5FF)
                val tickWidth = if (isSelected) 2.0f else 1.2f

                // Izquierda
                drawLine(tickColor, Offset(ally5X - tickEnd, ally5Y), Offset(ally5X - tickStart, ally5Y), tickWidth)
                // Derecha
                drawLine(tickColor, Offset(ally5X + tickStart, ally5Y), Offset(ally5X + tickEnd, ally5Y), tickWidth)
                // Arriba
                drawLine(tickColor, Offset(ally5X, ally5Y - tickEnd), Offset(ally5X, ally5Y - tickStart), tickWidth)
                // Abajo
                drawLine(tickColor, Offset(ally5X, ally5Y + tickStart), Offset(ally5X, ally5Y + tickEnd), tickWidth)

                drawContext.canvas.nativeCanvas.drawText(
                    "10º PICK (SLOT 5 ALIADO)",
                    ally5X,
                    ally5Y + slotRadius + 15f,
                    allyTextPaint
                )
            }

            if (drawEnemy10) {
                val enemy5X = w * currentConfig.enemyAvatarCenterX
                val enemy5Y = h * currentConfig.enemySlotYRatios.getOrElse(4) { 0.72f }
                val isSelected = (activeSide == 1)

                // Anillo delimitador exterior del avatar en slot inferior derecho (Rival 5)
                drawCircle(
                    color = if (isSelected) Color(0xFFFF5252) else Color(0x88FF5252),
                    center = Offset(enemy5X, enemy5Y),
                    radius = slotRadius,
                    style = Stroke(width = if (isSelected) 2.5f else 1.5f)
                )

                // Guías exteriores tipo retícula (ticks)
                val tickStart = slotRadius + 2f
                val tickEnd = slotRadius + 8f
                val tickColor = if (isSelected) Color(0xFFFF5252) else Color(0x88FF5252)
                val tickWidth = if (isSelected) 2.0f else 1.2f

                // Izquierda
                drawLine(tickColor, Offset(enemy5X - tickEnd, enemy5Y), Offset(enemy5X - tickStart, enemy5Y), tickWidth)
                // Derecha
                drawLine(tickColor, Offset(enemy5X + tickStart, enemy5Y), Offset(enemy5X + tickEnd, enemy5Y), tickWidth)
                // Arriba
                drawLine(tickColor, Offset(enemy5X, enemy5Y - tickEnd), Offset(enemy5X, enemy5Y - tickStart), tickWidth)
                // Abajo
                drawLine(tickColor, Offset(enemy5X, enemy5Y + tickStart), Offset(enemy5X, enemy5Y + tickEnd), tickWidth)

                drawContext.canvas.nativeCanvas.drawText(
                    "10º PICK (SLOT 5 RIVAL)",
                    enemy5X,
                    enemy5Y + slotRadius + 15f,
                    enemyTextPaint
                )
            }
            return@Canvas
        }
        
        // 1. SLOTS VERTICALES (LADO IZQUIERDO Y DERECHO)
        for (sIdx in 0..4) {
            val isTenthPickSlot = (sIdx == 4)
            // Columna Aliada (Izquierda)
            val allyY = h * currentConfig.allySlotYRatios.getOrElse(sIdx) { 0.2f + sIdx * 0.13f }
            val allyX = w * currentConfig.allyAvatarCenterX
            
            drawCircle(
                color = if (isTenthPickSlot) Color(0xFF00E5FF) else Color(0x9900B0FF),
                center = Offset(allyX, allyY),
                radius = avatarDiameter / 2f,
                style = Stroke(width = if (isTenthPickSlot) 3.5f else 2.0f)
            )
            
            val allyMatch = debugMatches["ally_$sIdx"]
            if (allyMatch != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    allyMatch,
                    allyX,
                    allyY - avatarDiameter / 2 - 6f,
                    allyTextPaint
                )
            }
            
            // Columna Rival (Derecha)
            val enemyY = h * currentConfig.enemySlotYRatios.getOrElse(sIdx) { 0.2f + sIdx * 0.13f }
            val enemyX = w * currentConfig.enemyAvatarCenterX
            
            drawCircle(
                color = if (isTenthPickSlot) Color(0xFFFF5252) else Color(0x99FF1744),
                center = Offset(enemyX, enemyY),
                radius = avatarDiameter / 2f,
                style = Stroke(width = if (isTenthPickSlot) 3.5f else 2.0f)
            )
            
            val enemyMatch = debugMatches["enemy_$sIdx"]
            if (enemyMatch != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    enemyMatch,
                    enemyX,
                    enemyY - avatarDiameter / 2 - 6f,
                    enemyTextPaint
                )
            }
        }

        // 2. CÍRCULOS SUPERIORES DIRECTOS EN PANTALLA (LOS 10 CAMPEONES EN LA BARRA SUPERIOR)
        // Aliados Superiores (5 Círculos en Top-Left: Índices 0..4)
        for (idx in 0..4) {
            val topAllyX = w * currentConfig.topAllyXRatios.getOrElse(idx) { 0.028f + idx * 0.035f }
            val isTarget10 = (idx == 4)
            
            // Círculo del campeón en la barra superior
            drawCircle(
                color = if (isTarget10) Color(0xFF00E5FF) else Color(0xCC00B0FF),
                center = Offset(topAllyX, topY),
                radius = topRadius,
                style = Stroke(width = if (isTarget10) 3.2f else 2.0f)
            )

            // Retícula / Cruz para el 5º Aliado (10º Pick si no tiene 1ª selección)
            if (isTarget10) {
                // Cruz de objetivo
                drawLine(
                    color = Color(0xFF00E5FF),
                    start = Offset(topAllyX - topRadius - 4f, topY),
                    end = Offset(topAllyX + topRadius + 4f, topY),
                    strokeWidth = 2.0f
                )
                drawLine(
                    color = Color(0xFF00E5FF),
                    start = Offset(topAllyX, topY - topRadius - 4f),
                    end = Offset(topAllyX, topY + topRadius + 4f),
                    strokeWidth = 2.0f
                )
                drawContext.canvas.nativeCanvas.drawText(
                    "A5 (10º)",
                    topAllyX,
                    topY + topRadius + 14f,
                    allyTextPaint
                )
            }

            val match = debugMatches["top_ally_$idx"]
            if (match != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    match,
                    topAllyX,
                    topY + topRadius + if (isTarget10) 24f else 14f,
                    allyTextPaint
                )
            }
        }

        // Rivales Superiores (5 Círculos en Top-Right: Índices 0..4)
        for (idx in 0..4) {
            val topEnemyX = w * currentConfig.topEnemyXRatios.getOrElse(idx) { 0.832f + idx * 0.035f }
            val isTarget10 = (idx == 4)
            
            // Círculo del campeón rival en la barra superior
            drawCircle(
                color = if (isTarget10) Color(0xFFFF5252) else Color(0xCCFF1744),
                center = Offset(topEnemyX, topY),
                radius = topRadius,
                style = Stroke(width = if (isTarget10) 3.2f else 2.0f)
            )

            // Retícula / Cruz para el 5º Rival (10º Pick si tiene 1ª selección)
            if (isTarget10) {
                // Cruz de objetivo
                drawLine(
                    color = Color(0xFFFF5252),
                    start = Offset(topEnemyX - topRadius - 4f, topY),
                    end = Offset(topEnemyX + topRadius + 4f, topY),
                    strokeWidth = 2.0f
                )
                drawLine(
                    color = Color(0xFFFF5252),
                    start = Offset(topEnemyX, topY - topRadius - 4f),
                    end = Offset(topEnemyX, topY + topRadius + 4f),
                    strokeWidth = 2.0f
                )
                drawContext.canvas.nativeCanvas.drawText(
                    "R5 (10º)",
                    topEnemyX,
                    topY + topRadius + 14f,
                    enemyTextPaint
                )
            }

            val match = debugMatches["top_enemy_$idx"]
            if (match != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    match,
                    topEnemyX,
                    topY + topRadius + if (isTarget10) 24f else 14f,
                    enemyTextPaint
                )
            }
        }
        
        // 3. Límites del Asistente Flotante
        if (overlayRect != null) {
            drawRect(
                color = Color(0x55C89B3C),
                topLeft = Offset(overlayRect.left.toFloat(), overlayRect.top.toFloat()),
                size = Size(overlayRect.width().toFloat(), overlayRect.height().toFloat()),
                style = Stroke(width = 1.5f)
            )
        }
    }
}

