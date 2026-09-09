package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.service.screen.VisionCalibrationConfig

@Composable
fun ScannerDebugOverlay(
    config: VisionCalibrationConfig,
    overlayRect: android.graphics.Rect?
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        
        // Draw OCR boxes based on ratios
        val avatarDiameter = h * config.avatarDiameterRatio
        
        for (sIdx in 0..4) {
            // Ally
            val allyY = h * config.allySlotYRatios[sIdx]
            val allyX = w * config.allyAvatarCenterX
            
            drawRect(
                color = Color.Green,
                topLeft = Offset(allyX - avatarDiameter / 2, allyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3f)
            )
            
            // Enemy
            val enemyY = h * config.enemySlotYRatios[sIdx]
            val enemyX = w * config.enemyAvatarCenterX
            
            drawRect(
                color = Color.Red,
                topLeft = Offset(enemyX - avatarDiameter / 2, enemyY - avatarDiameter / 2),
                size = Size(avatarDiameter, avatarDiameter),
                style = Stroke(width = 3f)
            )
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
