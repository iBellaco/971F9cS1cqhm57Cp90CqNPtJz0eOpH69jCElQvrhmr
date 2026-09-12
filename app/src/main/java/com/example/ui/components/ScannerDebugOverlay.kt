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
        
        // 1. SLOTS VERTICALES (Únicamente Slot 4 / Último avatar inferior: izquierdo y derecho)
        for (sIdx in listOf(4)) {
            // Columna Aliada (Izquierda Abajo)
            val allyY = h * currentConfig.allySlotYRatios[sIdx]
            val allyX = w * currentConfig.allyAvatarCenterX
            
            drawRect(
                color = Color(0xFFFFD700),
                topLeft = Offset(allyX - avatarDiameter / 2, allyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3.5f)
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
            
            // Columna Rival (Derecha Abajo)
            val enemyY = h * currentConfig.enemySlotYRatios[sIdx]
            val enemyX = w * currentConfig.enemyAvatarCenterX
            
            drawRect(
                color = Color(0xFFFFD700),
                topLeft = Offset(enemyX - avatarDiameter / 2, enemyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3.5f)
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

        // 2. CÍRCULOS SUPERIORES (Únicamente el 10º Pick / Índice 4: superior izquierdo y superior derecho)
        // Aliados Superiores (Índice 4 - Superior Izquierdo)
        for (idx in listOf(4)) {
            val topAllyX = w * currentConfig.topAllyXRatios.getOrElse(idx) { 0.148f }
            drawRect(
                color = Color(0xFFFFD700),
                topLeft = Offset(topAllyX - topDiameter / 2, topY - topDiameter / 2),
                size = Size(topDiameter, topDiameter),
                style = Stroke(width = 4f)
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

        // Rivales Superiores (Índice 4 - Superior Derecho / 10º Pick Rival)
        for (idx in listOf(4)) {
            val topEnemyX = w * currentConfig.topEnemyXRatios.getOrElse(idx) { 0.952f }
            drawRect(
                color = Color(0xFFFFD700),
                topLeft = Offset(topEnemyX - topDiameter / 2, topY - topDiameter / 2),
                size = Size(topDiameter, topDiameter),
                style = Stroke(width = 4.5f)
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
