package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechDarkBg

@Composable
fun BlurredMeshBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // We animate the offset of a few blobs to make it dynamic
    val infiniteTransition = rememberInfiniteTransition(label = "mesh")
    
    val offsetX1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "x1"
    )
    
    val offsetY1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "y1"
    )

    val offsetX2 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(18000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "x2"
    )
    
    val offsetY2 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(14000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "y2"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HextechDarkBg)
    ) {
        Canvas(modifier = Modifier.fillMaxSize().blur(100.dp)) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val currentTheme = com.example.ui.theme.AppThemeManager.currentTheme

            // Blob 1: Color Primario del Tema (Ej. Cian Arcano, Verde Quimtech, Naranja Fuego, Rojo Noxiano)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(currentTheme.primary.copy(alpha = 0.38f), Color.Transparent),
                    center = Offset(canvasWidth * 0.2f + (canvasWidth * 0.3f * offsetX1), canvasHeight * 0.2f + (canvasHeight * 0.4f * offsetY1)),
                    radius = canvasWidth * 0.8f
                ),
                center = Offset(canvasWidth * 0.2f + (canvasWidth * 0.3f * offsetX1), canvasHeight * 0.2f + (canvasHeight * 0.4f * offsetY1)),
                radius = canvasWidth * 0.8f
            )

            // Blob 2: Color Secundario del Tema (Ej. Oro Hextech, Ámbar Pirata, Lavanda, Púrpura)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(currentTheme.secondary.copy(alpha = 0.28f), Color.Transparent),
                    center = Offset(canvasWidth * 0.8f - (canvasWidth * 0.4f * offsetX2), canvasHeight * 0.8f - (canvasHeight * 0.3f * offsetY2)),
                    radius = canvasWidth * 0.9f
                ),
                center = Offset(canvasWidth * 0.8f - (canvasWidth * 0.4f * offsetX2), canvasHeight * 0.8f - (canvasHeight * 0.3f * offsetY2)),
                radius = canvasWidth * 0.9f
            )
            
            // Blob 3: Resplandor Rúnico característico de la Región
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(currentTheme.primaryGlow.copy(alpha = 0.22f), Color.Transparent),
                    center = Offset(canvasWidth * 0.5f + (canvasWidth * 0.3f * offsetX2), canvasHeight * 0.5f - (canvasHeight * 0.4f * offsetY1)),
                    radius = canvasWidth * 0.7f
                ),
                center = Offset(canvasWidth * 0.5f + (canvasWidth * 0.3f * offsetX2), canvasHeight * 0.5f - (canvasHeight * 0.4f * offsetY1)),
                radius = canvasWidth * 0.7f
            )
        }
        
        // Content on top (which will use glassmorphism / semi-transparent surfaces)
        content()
    }
}
