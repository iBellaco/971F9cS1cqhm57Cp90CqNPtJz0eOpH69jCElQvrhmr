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
import androidx.compose.ui.platform.LocalContext
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
        
        // Draw OCR boxes based on ratios
        val avatarDiameter = h * currentConfig.avatarDiameterRatio
        
        val textPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.YELLOW
            textSize = with(density) { 14.sp.toPx() }
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
            setShadowLayer(4f, 0f, 0f, android.graphics.Color.BLACK)
        }
        
        for (sIdx in 0..4) {
            // Ally
            val allyY = h * currentConfig.allySlotYRatios[sIdx]
            val allyX = w * currentConfig.allyAvatarCenterX
            
            drawRect(
                color = Color.Green,
                topLeft = Offset(allyX - avatarDiameter / 2, allyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3f)
            )
            
            val allyMatch = debugMatches["ally_$sIdx"]
            if (allyMatch != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    allyMatch,
                    allyX,
                    allyY - avatarDiameter / 2 - 10f,
                    textPaint
                )
            }
            
            // Enemy
            val enemyY = h * currentConfig.enemySlotYRatios[sIdx]
            val enemyX = w * currentConfig.enemyAvatarCenterX
            
            drawRect(
                color = Color.Red,
                topLeft = Offset(enemyX - avatarDiameter / 2, enemyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3f)
            )
            
            val enemyMatch = debugMatches["enemy_$sIdx"]
            if (enemyMatch != null) {
                drawContext.canvas.nativeCanvas.drawText(
                    enemyMatch,
                    enemyX,
                    enemyY - avatarDiameter / 2 - 10f,
                    textPaint
                )
            }
        }
        
        // Draw overlay boundary
        if (overlayRect != null) {
            drawRect(
                color = Color.Yellow,
                topLeft = Offset(overlayRect.left.toFloat(), overlayRect.top.toFloat()),
                size = Size(overlayRect.width().toFloat(), overlayRect.height().toFloat()),
                style = Stroke(width = 4f)
            )
        }
    }
}
