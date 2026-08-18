package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TierSColor
import kotlin.math.roundToInt

data class TacticalAdvice(
    val title: String,
    val primaryPick: String,
    val primaryReason: String,
    val secondaryPick: String,
    val secondaryReason: String,
    val synergyTag: String
)

@Composable
fun FloatingAssistantOverlay(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isVisible) return

    val density = LocalDensity.current
    var dragOffsetY by remember { mutableFloatStateOf(0f) }
    var dragOffsetX by remember { mutableFloatStateOf(0f) }
    var adviceIndex by remember { mutableIntStateOf(0) }
    var showSpeechBubble by remember { mutableStateOf(true) }

    val adviceList = remember {
        listOf(
            TacticalAdvice(
                title = "Respuesta Anti-CC & Protección",
                primaryPick = "Morgana",
                primaryReason = "Su Escudo Negro anula el CC de Vi, Sett y Rakan, asegurando la supervivencia del tirador.",
                secondaryPick = "Janna",
                secondaryReason = "Proporciona desengage total con Monzón y escudos para frenar emboscadas agresivas.",
                synergyTag = "Counter vs Iniciación Pesada"
            ),
            TacticalAdvice(
                title = "Disrupción de Tanques & Frontlane",
                primaryPick = "Vayne / Sett",
                primaryReason = "Daño verdadero y porcentual de vida máxima para derretir tanques con mucha armadura.",
                secondaryPick = "Lulu",
                secondaryReason = "Polimorfismo para anular al asesino enemigo antes de que salte sobre la retaguardia.",
                synergyTag = "Sinergia de Escalado Seguro"
            ),
            TacticalAdvice(
                title = "Reset en Peleas de Equipo & Burst",
                primaryPick = "Viego / Yone",
                primaryReason = "Aprovecha el CC aliado para asegurar la primera baja y resetear habilidades en cadena.",
                secondaryPick = "Nautilus",
                secondaryReason = "Lanza definitiva de control garantizado sobre el tirador rival.",
                synergyTag = "Composición de Enganche Directo"
            )
        )
    }

    val currentAdvice = adviceList[adviceIndex % adviceList.size]

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .testTag("floating_assistant_overlay_root"),
        contentAlignment = Alignment.Center
    ) {
        val screenWidthPx = with(density) { maxWidth.toPx() }
        val screenHeightPx = with(density) { maxHeight.toPx() }

        // Max horizontal drag bound to keep elements safely inside screen padding
        val maxSafeHorizontalOffset = (screenWidthPx * 0.35f)
        val maxSafeVerticalOffset = (screenHeightPx * 0.35f)

        // Semi-transparent backdrop when advice panel is expanded for optimal readability
        if (showSpeechBubble) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.40f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        // Tapping background toggles/collapses speech bubble
                        showSpeechBubble = false
                    }
            )
        }

        // Draggable container with smart bounded offsets
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset {
                    IntOffset(
                        dragOffsetX.coerceIn(-maxSafeHorizontalOffset, maxSafeHorizontalOffset).roundToInt(),
                        dragOffsetY.coerceIn(-maxSafeVerticalOffset, maxSafeVerticalOffset).roundToInt()
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            // If dragged downwards significantly (> 120px), dismiss assistant
                            if (dragOffsetY > 120f) {
                                onDismiss()
                            } else {
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // ==========================================
                // 1. PANEL TÁCTICO DE CONSEJOS (100% RESPONSIVE Y SIN RECORTES)
                // ==========================================
                AnimatedVisibility(
                    visible = showSpeechBubble,
                    enter = fadeIn() + scaleIn(initialScale = 0.92f) + slideInVertically(initialOffsetY = { -30 }),
                    exit = fadeOut() + scaleOut(targetScale = 0.92f) + slideOutVertically(targetOffsetY = { -30 })
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 380.dp)
                            .shadow(20.dp, RoundedCornerShape(16.dp))
                            .border(1.5.dp, HextechGold, RoundedCornerShape(16.dp))
                            .testTag("tactical_advice_card"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = HextechDarkBg.copy(alpha = 0.98f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            // Header Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(HextechCyan.copy(alpha = 0.15f))
                                            .border(1.dp, HextechCyan, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AutoAwesome,
                                            contentDescription = null,
                                            tint = HextechCyan,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = currentAdvice.title,
                                            color = HextechGold,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Wild Rift • ${WildRiftRepository.CURRENT_PATCH_VERSION}",
                                            color = TextMuted,
                                            fontSize = 10.sp
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = { showSpeechBubble = false },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Ocultar panel",
                                        tint = TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Synergy Tag Badge
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechCyan.copy(alpha = 0.12f))
                                    .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = currentAdvice.synergyTag,
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Primary Pick Row
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurface)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "★ Opción Prioritaria: ${currentAdvice.primaryPick}",
                                    color = HextechGoldLight,
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = currentAdvice.primaryReason,
                                    color = TextPrimary,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            // Secondary Pick Row
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "• Alternativa Recomendada: ${currentAdvice.secondaryPick}",
                                    color = HextechCyan,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = currentAdvice.secondaryReason,
                                    color = TextPrimary.copy(alpha = 0.9f),
                                    fontSize = 11.5.sp,
                                    lineHeight = 15.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Bottom Navigation and Page Indicator
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurface)
                                    .clickable {
                                        adviceIndex = (adviceIndex + 1) % adviceList.size
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Consejo ${adviceIndex + 1} de ${adviceList.size} • Toca para cambiar",
                                    color = TextMuted,
                                    fontSize = 10.5.sp
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Siguiente",
                                        color = HextechGold,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Icon(
                                        imageVector = Icons.Default.NavigateNext,
                                        contentDescription = null,
                                        tint = HextechGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // ==========================================
                // 2. ORBE FLOTANTE DE CÁMARA TÁCTICA
                // ==========================================
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .shadow(16.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(HextechCyan, Color(0xFF00B4D8), Color(0xFF0077B6))
                            )
                        )
                        .border(2.5.dp, HextechGold, CircleShape)
                        .clickable {
                            showSpeechBubble = !showSpeechBubble
                            if (showSpeechBubble) {
                                adviceIndex = (adviceIndex + 1) % adviceList.size
                            }
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

                // ==========================================
                // 3. PILL INDICADOR DE GESTO "DESLIZAR HACIA ABAJO"
                // ==========================================
                val isDragClosing = dragOffsetY > 40f
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isDragClosing) TierSColor.copy(alpha = 0.9f)
                            else HextechDarkBg.copy(alpha = 0.92f)
                        )
                        .border(
                            1.dp,
                            if (isDragClosing) TierSColor else HextechCyan.copy(alpha = 0.6f),
                            RoundedCornerShape(20.dp)
                        )
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
                            tint = if (isDragClosing) Color.White else HextechGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (isDragClosing) "Suelta para cerrar asistente" else "Desliza hacia abajo para cerrar",
                            color = if (isDragClosing) Color.White else HextechGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
