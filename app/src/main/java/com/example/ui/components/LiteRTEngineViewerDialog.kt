package com.example.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.ViewCarousel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Champion
import com.example.model.DraftAnalysisResult
import com.example.model.LaneRole
import com.example.service.screen.LiteRTVisionClassifier

enum class VisorSection(val title: String, val icon: ImageVector) {
    CAPTURED_DATA("Datos del Draft", Icons.Default.ViewCarousel),
    STEP_BY_STEP_LOGIC("Lógica Paso a Paso", Icons.Default.Psychology),
    CONCLUSIONS("Recomendaciones & Macro", Icons.Default.EmojiEvents)
}

@Composable
fun LiteRTEngineViewerDialog(
    allies: List<Champion?> = emptyList(),
    enemies: List<Champion?> = emptyList(),
    activeRole: LaneRole = LaneRole.MID,
    analysis: DraftAnalysisResult? = null,
    isFirstPick: Boolean = true,
    onDismissRequest: () -> Unit
) {
    val report by LiteRTVisionClassifier.reportFlow.collectAsStateWithLifecycle()
    var selectedSectionIndex by remember { mutableIntStateOf(0) }
    val defaultRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
    val userSlotIndex = defaultRoles.indexOf(activeRole).takeIf { it >= 0 }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.90f))
            .clickable { onDismissRequest() },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false) { /* Prevenir cierre al hacer click dentro */ }
                .padding(6.dp)
                .testTag("draft_visor_modal"),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF0B1120),
            tonalElevation = 12.dp,
            border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF1E293B))
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Cabecera Principal del Visor de Diagnóstico
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0F172A))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF00E5FF).copy(alpha = 0.15f))
                                .border(1.dp, Color(0xFF00E5FF).copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "Visor de Draft",
                                tint = Color(0xFF00E5FF),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "VISOR DE DRAFT Y DIAGNÓSTICO",
                                    color = Color.White,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 15.sp,
                                    letterSpacing = 0.5.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color(0xFF10B981).copy(alpha = 0.2f))
                                        .border(1.dp, Color(0xFF10B981), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 5.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "LIVE",
                                        color = Color(0xFF10B981),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Text(
                                text = "Pipeline OCR + LiteRT On-Device + Recomendaciones Tácticas",
                                color = Color(0xFF94A3B8),
                                fontSize = 11.sp
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1E293B))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar Visor",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Barra de Pestañas de Navegación del Visor
                ScrollableTabRow(
                    selectedTabIndex = selectedSectionIndex,
                    containerColor = Color(0xFF0F172A),
                    contentColor = Color(0xFF00E5FF),
                    edgePadding = 12.dp,
                    indicator = { tabPositions ->
                        if (selectedSectionIndex < tabPositions.size) {
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSectionIndex]),
                                color = Color(0xFF00E5FF),
                                height = 3.dp
                            )
                        }
                    },
                    divider = {
                        HorizontalDivider(color = Color(0xFF1E293B))
                    }
                ) {
                    VisorSection.values().forEachIndexed { index, section ->
                        val isSelected = selectedSectionIndex == index
                        Tab(
                            selected = isSelected,
                            onClick = { selectedSectionIndex = index },
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 6.dp)
                                ) {
                                    Icon(
                                        imageVector = section.icon,
                                        contentDescription = null,
                                        tint = if (isSelected) Color(0xFF00E5FF) else Color(0xFF64748B),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = section.title,
                                        color = if (isSelected) Color.White else Color(0xFF94A3B8),
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        )
                    }
                }

                // Contenido Desplazable de la Pestaña Seleccionada
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    when (selectedSectionIndex) {
                        0 -> CapturedDraftDataSection(
                            allies = allies,
                            enemies = enemies,
                            defaultRoles = defaultRoles,
                            userSlotIndex = userSlotIndex,
                            report = report,
                            analysis = analysis,
                            isFirstPick = isFirstPick
                        )
                        1 -> StepByStepLogicSection(
                            report = report,
                            allies = allies,
                            enemies = enemies
                        )
                        2 -> ConclusionsAndTacticsSection(
                            analysis = analysis,
                            allies = allies,
                            enemies = enemies,
                            activeRole = activeRole,
                            userSlotIndex = userSlotIndex
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// SECCIÓN 1: DATOS CAPTURADOS DEL DRAFT (CAPTURED DRAFT DATA)
// -------------------------------------------------------------------------------------------------
@Composable
private fun CapturedDraftDataSection(
    allies: List<Champion?>,
    enemies: List<Champion?>,
    defaultRoles: List<LaneRole>,
    userSlotIndex: Int?,
    report: LiteRTVisionClassifier.LiteRTInferenceReport,
    analysis: DraftAnalysisResult?,
    isFirstPick: Boolean
) {
    // 1. Resumen de Fase y Orden de Selección
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "ORDEN DE SELECCIÓN",
                    color = Color(0xFF94A3B8),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (isFirstPick) "1ª Elección (First Pick - Azul)" else "2ª Elección (Second Pick - Rojo)",
                    color = if (isFirstPick) Color(0xFF38BDF8) else Color(0xFFF87171),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "PICKS CONFIRMADOS",
                    color = Color(0xFF94A3B8),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                val totalConfirmed = (allies.filterNotNull().size) + (enemies.filterNotNull().size)
                Text(
                    text = "$totalConfirmed de 10 Seleccionados",
                    color = if (totalConfirmed >= 9) Color(0xFF10B981) else Color(0xFFF59E0B),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 2. Grilla de Slots: Equipo Aliado vs Equipo Rival
    Text(
        text = "MATRIZ DE SLOTS CAPTURADOS (5 VS 5)",
        color = Color(0xFFE2E8F0),
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Columna Aliada
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(Color(0xFF0369A1))
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "EQUIPO ALIADO (AZUL)",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }

            defaultRoles.forEachIndexed { idx, role ->
                val champ = allies.getOrNull(idx)
                val isUserSlot = userSlotIndex == idx
                SlotDataCard(
                    role = role,
                    champion = champ,
                    isAlly = true,
                    isUserSlot = isUserSlot,
                    slotNumber = idx + 1
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        // Columna Rival
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(Color(0xFF991B1B))
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "EQUIPO RIVAL (ROJO)",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }

            defaultRoles.forEachIndexed { idx, role ->
                val champ = enemies.getOrNull(idx)
                SlotDataCard(
                    role = role,
                    champion = champ,
                    isAlly = false,
                    isUserSlot = false,
                    slotNumber = idx + 6
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 3. Tarjeta de Diagnóstico y Recorte del 10º Pick
    TenthPickCropCard(report = report)

    Spacer(modifier = Modifier.height(14.dp))

    // 4. Balance de Daño Estimado
    if (analysis != null) {
        DamageBalanceCard(analysis = analysis)
    }
}

@Composable
private fun SlotDataCard(
    role: LaneRole,
    champion: Champion?,
    isAlly: Boolean,
    isUserSlot: Boolean,
    slotNumber: Int
) {
    val borderColor = when {
        isUserSlot -> Color(0xFFFACC15)
        champion != null -> if (isAlly) Color(0xFF0284C7) else Color(0xFFDC2626)
        else -> Color(0xFF334155)
    }

    val bgColor = when {
        isUserSlot -> Color(0xFF1E293B)
        champion != null -> Color(0xFF0F172A)
        else -> Color(0xFF090D16)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador de Rol o Avatar
        if (champion != null) {
            ChampionAvatar(
                champion = champion,
                size = 28.dp,
                modifier = Modifier.clip(CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1E293B)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = role.displayName.take(3).uppercase(),
                    color = Color(0xFF94A3B8),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = champion?.name ?: "En Espera (${role.displayName})",
                    color = if (champion != null) Color.White else Color(0xFF64748B),
                    fontWeight = if (champion != null) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 11.sp,
                    maxLines = 1
                )
                if (isUserSlot) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFFFACC15))
                            .padding(horizontal = 3.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "TÚ",
                            color = Color.Black,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Text(
                text = if (champion != null) "100% OCR • Confirmado" else "Mostrando Icono de Línea",
                color = if (champion != null) Color(0xFF10B981) else Color(0xFF475569),
                fontSize = 9.sp
            )
        }
    }
}

@Composable
private fun TenthPickCropCard(report: LiteRTVisionClassifier.LiteRTInferenceReport) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF00E5FF).copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CropFree,
                        contentDescription = null,
                        tint = Color(0xFF00E5FF),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "RECORTE REAL DEL 10º PICK (LITERT ON-DEVICE)",
                        color = Color(0xFF00E5FF),
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp
                    )
                }

                val statusBadgeColor = when (report.status) {
                    LiteRTVisionClassifier.EngineStatus.COMPLETED -> Color(0xFF10B981)
                    LiteRTVisionClassifier.EngineStatus.RUNNING_INFERENCE -> Color(0xFF38BDF8)
                    else -> Color(0xFFF59E0B)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(statusBadgeColor.copy(alpha = 0.2f))
                        .border(1.dp, statusBadgeColor, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = report.status.name,
                        color = statusBadgeColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Recorte
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                        .border(2.dp, Color(0xFF00E5FF), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (report.cropBitmap != null && !report.cropBitmap.isRecycled) {
                        Image(
                            bitmap = report.cropBitmap.asImageBitmap(),
                            contentDescription = "Recorte 10º Pick",
                            modifier = Modifier.size(54.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null,
                            tint = Color(0xFF475569),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    val champ = report.pickedChampion
                    if (champ != null) {
                        Text(
                            text = champ.name,
                            color = Color.White,
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Confianza: ${report.confidencePercent}% • ${report.inferenceTimeMs}ms",
                            color = Color(0xFF10B981),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    } else {
                        Text(
                            text = "Slot Final en Espera",
                            color = Color(0xFF94A3B8),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = if (report.slotDescription.isNotBlank()) report.slotDescription else "Evaluando tensores de imagen...",
                            color = Color(0xFF64748B),
                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = "Estabilidad: ${report.stableFramesCount}/${report.requiredStableFrames} frames • Umbral mín: ${(report.minConfidenceThreshold * 100).toInt()}%",
                        color = Color(0xFF38BDF8),
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

@Composable
private fun DamageBalanceCard(analysis: DraftAnalysisResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "BALANCE DE DAÑO DE LAS COMPOSICIONES",
                color = Color(0xFFE2E8F0),
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Equipo Rival
            Text(
                text = "Daño Rival: ${analysis.physicalDamagePercent}% Físico (AD) | ${analysis.magicDamagePercent}% Mágico (AP) | ${analysis.trueDamagePercent}% Verdadero",
                color = Color(0xFFF87171),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
            ) {
                Box(modifier = Modifier.weight((analysis.physicalDamagePercent.coerceAtLeast(1)).toFloat()).fillMaxHeight().background(Color(0xFFEF4444)))
                Box(modifier = Modifier.weight((analysis.magicDamagePercent.coerceAtLeast(1)).toFloat()).fillMaxHeight().background(Color(0xFF3B82F6)))
                Box(modifier = Modifier.weight((analysis.trueDamagePercent.coerceAtLeast(1)).toFloat()).fillMaxHeight().background(Color(0xFFF59E0B)))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Equipo Aliado
            Text(
                text = "Daño Aliado: ${analysis.allyPhysicalDamagePercent}% Físico (AD) | ${analysis.allyMagicDamagePercent}% Mágico (AP) | ${analysis.allyTrueDamagePercent}% Verdadero",
                color = Color(0xFF38BDF8),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
            ) {
                Box(modifier = Modifier.weight((analysis.allyPhysicalDamagePercent.coerceAtLeast(1)).toFloat()).fillMaxHeight().background(Color(0xFF0284C7)))
                Box(modifier = Modifier.weight((analysis.allyMagicDamagePercent.coerceAtLeast(1)).toFloat()).fillMaxHeight().background(Color(0xFF8B5CF6)))
                Box(modifier = Modifier.weight((analysis.allyTrueDamagePercent.coerceAtLeast(1)).toFloat()).fillMaxHeight().background(Color(0xFFF59E0B)))
            }

            if (analysis.allyCompositionWarning != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF78350F).copy(alpha = 0.5f))
                        .border(1.dp, Color(0xFFF59E0B), RoundedCornerShape(6.dp))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = analysis.allyCompositionWarning,
                        color = Color(0xFFFDE68A),
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// SECCIÓN 2: LÓGICA DE ANÁLISIS PASO A PASO (STEP-BY-STEP ANALYSIS LOGIC)
// -------------------------------------------------------------------------------------------------
@Composable
private fun StepByStepLogicSection(
    report: LiteRTVisionClassifier.LiteRTInferenceReport,
    allies: List<Champion?>,
    enemies: List<Champion?>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "PIPELINE TÁCTICO DE RECONOCIMIENTO Y VISIÓN ARTIFICIAL",
            color = Color(0xFF00E5FF),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Paso 1
        StepCard(
            stepNumber = 1,
            title = "Aislamiento Óptico Columnar y Protección Anti-Overlay",
            description = "El escáner toma el bitmap nativo del dispositivo y excluye dinámicamente el área central ocupada por el Hub Flotante. Segmenta la pantalla en 2 columnas fijas de alta resolución (Izquierda: Aliados, Derecha: Rivales).",
            status = "Completado • Sin Oclusión",
            icon = Icons.Default.CropFree,
            statusColor = Color(0xFF10B981)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Paso 2
        StepCard(
            stepNumber = 2,
            title = "Supresión de Prefijos de Maestría & Separación Rol vs Campeón",
            description = "Se eliminan iconos gráficos de maestría (niveles 1 a 7, insignias y glifos como '•', 'V', 'M7', '»', 'LV7'). Si el texto corresponde a un carril ('Calle Central', 'Jungla', 'Apoyo'), el slot se mantiene como 'En Espera'. Si es un nombre ('Jinx', 'Sett', 'Vi'), se confirma con 100% de certeza.",
            status = "Activo • Multi-Idioma (ES, EN, PT)",
            icon = Icons.Default.Psychology,
            statusColor = Color(0xFF10B981)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Paso 3
        StepCard(
            stepNumber = 3,
            title = "Inferencia On-Device LiteRT / MediaPipe para el 10º Pick",
            description = "Para el último slot (que muestra únicamente avatar sin texto tras fijar), se normaliza un tensor RGB de 48x48x3 y se compara con la base de datos de campeones. Requiere 3 fotogramas estables consecutivos con similitud >65% para confirmar.",
            status = if (report.isConfirmed) "Confirmado • 10º Pick Decidido" else "En Espera • ${report.stableFramesCount}/${report.requiredStableFrames} Frames",
            icon = Icons.Default.Memory,
            statusColor = if (report.isConfirmed) Color(0xFF10B981) else Color(0xFF38BDF8)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Paso 4
        StepCard(
            stepNumber = 4,
            title = "Matriz de Sinergias, Matchups y Arquetipos de Composición",
            description = "El motor táctico cruza los 10 campeones contra la base de datos del meta actual, clasificando las identidades de equipo (Poke, Dive, Teamfight, Split-Push, Escala) y calculando la ventaja matemática en cada carril.",
            status = "Calculado • Nivel Soberano",
            icon = Icons.Default.Analytics,
            statusColor = Color(0xFF10B981)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Tabla de Comparación Top 5 de LiteRT si está disponible
        Text(
            text = "TOP 5 CANDIDATOS DE TENSOR (LITERT)",
            color = Color(0xFFE2E8F0),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (report.topCandidates.isNotEmpty()) {
            for (candidate in report.topCandidates) {
                CandidateRowItem(candidate = candidate)
                Spacer(modifier = Modifier.height(6.dp))
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E293B), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "La tabla de tensores se activará cuando la cámara apunte al 10º pick en la fase final del draft.",
                    color = Color(0xFF64748B),
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun StepCard(
    stepNumber: Int,
    title: String,
    description: String,
    status: String,
    icon: ImageVector,
    statusColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00E5FF).copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$stepNumber",
                            color = Color(0xFF00E5FF),
                            fontWeight = FontWeight.Black,
                            fontSize = 11.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(statusColor.copy(alpha = 0.15f))
                        .border(1.dp, statusColor, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = status,
                        color = statusColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                color = Color(0xFF94A3B8),
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

// -------------------------------------------------------------------------------------------------
// SECCIÓN 3: CONCLUSIONES Y RECOMENDACIONES TÁCTICAS (FINAL RECOMMENDATIONS)
// -------------------------------------------------------------------------------------------------
@Composable
private fun ConclusionsAndTacticsSection(
    analysis: DraftAnalysisResult?,
    allies: List<Champion?>,
    enemies: List<Champion?>,
    activeRole: LaneRole,
    userSlotIndex: Int?
) {
    val mySelectedChamp = if (userSlotIndex != null) allies.getOrNull(userSlotIndex) else null

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "DIRECTIVAS TÁCTICAS DEL COACH DE ÉLITE",
            color = Color(0xFF00E5FF),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 1. Lectura del Draft y Win Condition
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF00E5FF).copy(alpha = 0.4f))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = Color(0xFF00E5FF),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "1. CONDICIÓN DE VICTORIA (WIN CONDITION)",
                        color = Color(0xFF00E5FF),
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                val winConditionText = when {
                    analysis?.directMatchupWarning != null -> analysis.directMatchupWarning
                    mySelectedChamp != null -> "Priorizar escalado de ${mySelectedChamp.name}, controlar visión en río y ejecutar iniciaciones coordinadas en objetivos neutrales."
                    else -> "Asegurar control de carril en ${activeRole.displayName}, denegar recursos al rival y jugar alrededor del primer Dragón o Heraldo de la Grieta."
                }

                Text(
                    text = winConditionText,
                    color = Color.White,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 2. Picks y Sinergias
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.SportsEsports,
                        contentDescription = null,
                        tint = Color(0xFF38BDF8),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "2. PICKS RECOMENDADOS Y SINERGIAS",
                        color = Color(0xFF38BDF8),
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (analysis?.recommendations?.isNotEmpty() == true) {
                    analysis.recommendations.take(3).forEachIndexed { index, rec ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF0F172A))
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "#${index + 1}",
                                color = Color(0xFF00E5FF),
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                modifier = Modifier.width(24.dp)
                            )
                            ChampionAvatar(
                                champion = rec.champion,
                                size = 32.dp,
                                modifier = Modifier.clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${rec.champion.name} (${(rec.estimatedWinrate * 100).toInt()}% WR)",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = rec.tacticalReason,
                                    color = Color(0xFF94A3B8),
                                    fontSize = 10.sp,
                                    maxLines = 2
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                } else {
                    Text(
                        text = if (mySelectedChamp != null) {
                            "Campeón fijado: ${mySelectedChamp.name}. Sinergias activas con la línea frontal y cadena de control de masas."
                        } else {
                            "Recomendando selecciones de alto impacto para la línea de ${activeRole.displayName}."
                        },
                        color = Color(0xFFCBD5E1),
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 3. Configuración Óptima (Runas y Hechizos)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "3. CONFIGURACIÓN ÓPTIMA (RUNAS & HECHIZOS)",
                        color = Color(0xFFF59E0B),
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                val champToConfig = mySelectedChamp ?: analysis?.bestOverallPick?.champion
                val runeText = if (champToConfig != null) {
                    "Runa Clave: Conquistador / Primer Golpe • Secundarias: Valor (Segundo Aire + Sobrecrecimiento) • Dominación (Impacto Súbito)."
                } else {
                    "Adaptar runas según el daño predominante rival (Físico AD o Mágico AP)."
                }

                val spellText = when (activeRole) {
                    LaneRole.JUNGLE -> "Hechizos: Destello + Castigo (Obligatorio en jungla)."
                    LaneRole.SUPPORT -> "Hechizos: Destello + Extenuación o Ignición."
                    LaneRole.TOP -> "Hechizos: Destello + Ignición o Teletransporte."
                    else -> "Hechizos: Destello + Ignición o Barrera."
                }

                Text(
                    text = runeText,
                    color = Color(0xFFCBD5E1),
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = spellText,
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 4. Macro Game & Micro-Tip Definitivo
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.4f))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "4. PLAN MACRO & MICRO-TIP DEFINITIVO",
                        color = Color(0xFF10B981),
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "• Early Game (Niveles 1-5): Mantén la oleada cerca de tu torre antes del nivel 5 para evitar emboscadas de jungla.",
                    color = Color(0xFFCBD5E1),
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "• Mid/Late Game: Agrupa con tu equipo 30 segundos antes de la aparición del Dragón o Barón para asegurar visión.",
                    color = Color(0xFFCBD5E1),
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF064E3B).copy(alpha = 0.4f))
                        .border(1.dp, Color(0xFF10B981), RoundedCornerShape(6.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Micro-Tip Maestro: Guarda tu Definitiva (H4) para contrarrestar la iniciación rival y encadena tu Habilidad 1 (H1) con el control de masas aliado para maximizar daño.",
                        color = Color(0xFFA7F3D0),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun CandidateRowItem(candidate: LiteRTVisionClassifier.LiteRTCandidateScore) {
    val isWinner = candidate.rank == 1
    val borderColor = if (isWinner) Color(0xFF00E5FF) else Color(0xFF334155)
    val bgColor = if (isWinner) Color(0xFF1E293B) else Color(0xFF0F172A)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Text(
                text = "#${candidate.rank}",
                color = if (isWinner) Color(0xFF00E5FF) else Color(0xFF64748B),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.width(24.dp)
            )

            ChampionAvatar(
                champion = candidate.champion,
                size = 32.dp,
                modifier = Modifier.clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = candidate.champion.name,
                    color = Color.White,
                    fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 13.sp
                )
                Text(
                    text = "Probabilidad Softmax: ${(candidate.softmaxProbability * 100).toInt()}%",
                    color = Color(0xFF94A3B8),
                    fontSize = 10.sp
                )
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${(candidate.similarityScore * 100).toInt()}% Tensor",
                color = if (isWinner) Color(0xFF10B981) else Color(0xFFCBD5E1),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            LinearProgressIndicator(
                progress = { candidate.similarityScore.coerceIn(0f, 1f) },
                modifier = Modifier
                    .width(64.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = if (isWinner) Color(0xFF00E5FF) else Color(0xFF64748B),
                trackColor = Color(0xFF334155)
            )
        }
    }
}
