package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.TextCyan
import kotlin.math.roundToInt

@Composable
fun FloatingAssistantOverlay(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isVisible) return

    var offsetX by remember { mutableFloatStateOf(60f) }
    var offsetY by remember { mutableFloatStateOf(280f) }
    var adviceIndex by remember { mutableIntStateOf(0) }
    var showSpeechBubble by remember { mutableStateOf(true) }

    val sampleAdvices = remember {
        listOf(
            "Morgana: Su Escudo Negro anula el CC de Vi, Sett y Rakan, protegiendo a Vayne.\nJanna: Proporciona un gran desengage y escudo para frenar emboscadas.",
            "Janna: Su desarme y escudos protegen a Vayne de la ofensiva enemiga.\nLulu: Su polimorfismo y definitiva son vitales para neutralizar a Vi y Sett.",
            "Viego: Aprovecha el CC de Cho'Gath para asegurar la primera baja y resetear su definitiva.\nSett: Posiciónate para rematar al tanque rival con daño verdadero."
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // Allow interaction without swallowing clicks outside
            }
    ) {
        // Draggable Overlay Container
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Tactical Speech Bubble
                AnimatedVisibility(
                    visible = showSpeechBubble,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Box(
                        modifier = Modifier
                            .widthIn(max = 240.dp)
                            .shadow(12.dp, RoundedCornerShape(14.dp))
                            .clip(RoundedCornerShape(14.dp))
                            .background(HextechDarkBg.copy(alpha = 0.94f))
                            .border(2.dp, HextechGold, RoundedCornerShape(14.dp))
                            .clickable {
                                // Cycle advice on tap
                                adviceIndex = (adviceIndex + 1) % sampleAdvices.size
                            }
                            .padding(14.dp)
                    ) {
                        Column {
                            Text(
                                text = sampleAdvices[adviceIndex],
                                color = TextCyan,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Toca para siguiente sugerencia • Arrastra para mover",
                                color = Color.White.copy(alpha = 0.6f),
                                fontSize = 9.sp
                            )
                        }
                    }
                }

                // Floating Action Bubbles Column
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Teal Camera Bubble (Scan & Analysis Trigger)
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(HextechCyan)
                            .border(2.dp, HextechGold, CircleShape)
                            .clickable {
                                showSpeechBubble = !showSpeechBubble
                                adviceIndex = (adviceIndex + 1) % sampleAdvices.size
                            }
                            .testTag("floating_camera_bubble"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Escanear Draft",
                            tint = HextechDarkBg,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    // Red Close Bubble
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(DangerRed)
                            .border(2.dp, Color.White.copy(alpha = 0.8f), CircleShape)
                            .clickable {
                                onDismiss()
                            }
                            .testTag("floating_close_bubble"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar Overlay",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}
