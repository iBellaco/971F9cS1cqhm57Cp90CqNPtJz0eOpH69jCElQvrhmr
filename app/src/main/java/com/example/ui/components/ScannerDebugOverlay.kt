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
    val density = LocalDensity.current
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        
        val avatarDiameter = h * currentConfig.avatarDiameterRatio
        val topDiameter = h * currentConfig.topAvatarDiameterRatio
        val topY = h * currentConfig.topAvatarYRatio
        
        val textPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.YELLOW
            textSize = with(density) { 13.sp.toPx() }
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
            setShadowLayer(4f, 0f, 0f, android.graphics.Color.BLACK)
        }

        val topTextPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.CYAN
            textSize = with(density) { 11.sp.toPx() }
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
            setShadowLayer(4f, 0f, 0f, android.graphics.Color.BLACK)
        }
        
        // 1. SLOTS VERTICALES (Draft estándar)
        for (sIdx in 0..4) {
            // Columna Aliada
            val allyY = h * currentConfig.allySlotYRatios[sIdx]
            val allyX = w * currentConfig.allyAvatarCenterX
            
            drawRect(
                color = Color(0xFF00FF7F),
                topLeft = Offset(allyX - avatarDiameter / 2, allyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3f)
            )
            
            val allyMatch = debugMatches["ally_$sIdx"]
            if (allyMatch != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    allyMatch,
                    allyX,
                    allyY - avatarDiameter / 2 - 8f,
                    textPaint
                )
            }
            
            // Columna Rival
            val enemyY = h * currentConfig.enemySlotYRatios[sIdx]
            val enemyX = w * currentConfig.enemyAvatarCenterX
            
            drawRect(
                color = Color(0xFFFF453A),
                topLeft = Offset(enemyX - avatarDiameter / 2, enemyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3f)
            )
            
            val enemyMatch = debugMatches["enemy_$sIdx"]
            if (enemyMatch != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    enemyMatch,
                    enemyX,
                    enemyY - avatarDiameter / 2 - 8f,
                    textPaint
                )
            }
        }

        // 2. CÍRCULOS SUPERIORES (Top Bar / 10º Pick / Fase de Preparación)
        // Aliados Superiores (0..4)
        for (idx in 0..4) {
            val topAllyX = w * currentConfig.topAllyXRatios.getOrElse(idx) { 0.03f + idx * 0.03f }
            val is5th = idx == 4
            drawRect(
                color = if (is5th) Color(0xFFFFD700) else Color(0xFF00E5FF),
                topLeft = Offset(topAllyX - topDiameter / 2, topY - topDiameter / 2),
                size = Size(topDiameter, topDiameter),
                style = Stroke(width = if (is5th) 4f else 2f)
            )

            val match = debugMatches["top_ally_$idx"]
            if (match != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    match,
                    topAllyX,
                    topY + topDiameter / 2 + 14f,
                    topTextPaint
                )
            }
        }

        // Rivales Superiores (0..4)
        for (idx in 0..4) {
            val topEnemyX = w * currentConfig.topEnemyXRatios.getOrElse(idx) { 0.83f + idx * 0.03f }
            val is5th = idx == 4 // 10º Pick Rival!
            drawRect(
                color = if (is5th) Color(0xFFFFD700) else Color(0xFFFF9500),
                topLeft = Offset(topEnemyX - topDiameter / 2, topY - topDiameter / 2),
                size = Size(topDiameter, topDiameter),
                style = Stroke(width = if (is5th) 4.5f else 2f)
            )

            val match = debugMatches["top_enemy_$idx"]
            if (match != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    match,
                    topEnemyX,
                    topY + topDiameter / 2 + 14f,
                    textPaint
                )
            }
        }
        
        // 3. Límites del Asistente Flotante
        if (overlayRect != null) {
            drawRect(
                color = Color(0x88FFD700),
                topLeft = Offset(overlayRect.left.toFloat(), overlayRect.top.toFloat()),
                size = Size(overlayRect.width().toFloat(), overlayRect.height().toFloat()),
                style = Stroke(width = 2.5f)
            )
        }
    }
}
