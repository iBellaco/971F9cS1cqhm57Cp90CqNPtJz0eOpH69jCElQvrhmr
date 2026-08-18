package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var dragOffsetY by remember { mutableFloatStateOf(0f) }
    var dragOffsetX by remember { mutableFloatStateOf(0f) }
    var adviceIndex by remember { mutableIntStateOf(0) }
    var showSpeechBubble by remember { mutableStateOf(true) }

    val sampleAdvices = remember {
        listOf(
            "Morgana: Su Escudo Negro anula el CC de Vi, Sett y Rakan, protegiendo a Vayne.\nJanna: Proporciona un gran desengage y escudo para frenar emboscadas.",
            "Janna: Su desarme y escudos protegen a Vayne de la ofensiva enemiga.\nLulu: Su polimorfismo y definitiva son vitales para neutralizar a Vi y Sett.",
            "Viego: Aprovecha el CC de Cho'Gath para asegurar la primera baja y resetear su definitiva.\nSett: Posiciónate para rematar al tanque rival con daño verdadero."
        )
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Centered Draggable Container with Swipe-Down-To-Dismiss Detection
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        dragOffsetX.roundToInt(),
                        dragOffsetY.roundToInt()
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            // If dragged downwards by more than 130px, dismiss
                            if (dragOffsetY > 130f) {
                                onDismiss()
                            } else {
                                // Snap back to center
                                dragOffsetY = 0f
                            }
                        },
                        onDragCancel = {
                            dragOffsetY = 0f
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragOffsetX += dragAmount.x
                            dragOffsetY += dragAmount.y
                        }
                    )
                }
                .testTag("floating_assistant_container"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                // 1. Tactical Speech Bubble (Centered above)
                AnimatedVisibility(
                    visible = showSpeechBubble,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { -20 }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { -20 })
                ) {
                    Box(
                        modifier = Modifier
                            .widthIn(max = 290.dp)
                            .shadow(16.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        HextechDarkBg.copy(alpha = 0.96f),
                                        Color(0xFF0F172A).copy(alpha = 0.98f)
                                    )
                                )
                            )
                            .border(1.8.dp, HextechGold, RoundedCornerShape(16.dp))
                            .clickable {
                                // Cycle advice on tap
                                adviceIndex = (adviceIndex + 1) % sampleAdvices.size
                            }
                            .padding(16.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = sampleAdvices[adviceIndex],
                                color = TextCyan,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Toca para siguiente sugerencia",
                                color = Color.White.copy(alpha = 0.6f),
                                fontSize = 9.5.sp
                            )
                        }
                    }
                }

                // 2. Centered Floating Action Button (Teal Camera HUD Trigger)
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .shadow(12.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(HextechCyan, Color(0xFF00B4D8), Color(0xFF0077B6))
                            )
                        )
                        .border(2.5.dp, HextechGold, CircleShape)
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
                        modifier = Modifier.size(32.dp)
                    )
                }

                // 3. Swipe Down To Close Gesture Indicator Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(HextechDarkBg.copy(alpha = 0.85f))
                        .border(1.dp, HextechCyan.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                        .testTag("floating_swipe_down_hint")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Deslizar hacia abajo",
                            tint = HextechGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Desliza hacia abajo para cerrar",
                            color = HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
