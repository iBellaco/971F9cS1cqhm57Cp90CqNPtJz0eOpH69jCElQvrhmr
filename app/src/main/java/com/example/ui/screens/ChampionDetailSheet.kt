package com.example.ui.screens

import com.example.utils.parseHtmlColorToAnnotatedString
import com.example.data.WildRiftItemsData
import com.example.model.WildRiftItem
import com.example.ui.theme.HextechGoldLight

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SituationalItemAdvisor
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.LocalLanguage
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.components.FormattedWildRiftText
import com.example.ui.theme.AllyBlue
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TierSPlusColor
import com.example.util.ChampionRoleAdapter
import com.example.util.CoachingGenerator
import com.example.util.tr

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChampionDetailSheet(
    champion: Champion?,
    onDismiss: () -> Unit
) {
    if (champion == null) return

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    // Solo las líneas Main y Flex en las que realmente se juega el campeón
    val availableRoles = remember(champion.id) {
        (listOf(champion.primaryRole) + champion.secondaryRoles).distinct()
    }
    var selectedRole by remember(champion.id) { 
        mutableStateOf(champion.primaryRole) 
    }
    
    LaunchedEffect(champion.id) {
        if (selectedRole !in availableRoles) {
            selectedRole = champion.primaryRole
        }
    }

    var matchupExplanationTarget by remember { mutableStateOf<String?>(null) }
    var matchupExplanationType by remember { mutableStateOf<String?>(null) }
    var selectedSituationalItem by remember { mutableStateOf<String?>(null) }
    var itemForDetail by remember { mutableStateOf<com.example.model.WildRiftItem?>(null) }
    var runeForDetail by remember { mutableStateOf<com.example.model.RuneItem?>(null) }
    var spellForDetail by remember { mutableStateOf<com.example.model.SummonerSpellItem?>(null) }

    // Perfil dinámico de estadísticas, build, runas y counters adaptados a la línea elegida
    val roleProfile = remember(champion.id, selectedRole) {
        ChampionRoleAdapter.getProfile(champion, selectedRole)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechSurfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header with Avatar, Name, Tier and Winrate
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ChampionAvatar(champion = champion, size = 68.dp)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = champion.name,
                                color = TextPrimary,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(TierSPlusColor)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Tier ${roleProfile.tier}",
                                    color = Color.Black,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                        if (champion.title.isNotBlank()) {
                            Text(
                                text = tr(champion.title),
                                color = TextPrimary,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            text = "${tr(selectedRole.displayName)}${if (selectedRole != champion.primaryRole) " (Flex)" else ""} • ${tr(champion.damageType.displayName)}",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ==========================================
            // SELECTOR DE LÍNEA / ROL (TOP, JUNGLA, MID, ADC, SUPPORTE)
            // ==========================================
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = tr("Cambiar Línea / Rol Activo:"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tr(selectedRole.displayName),
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        availableRoles.forEach { role ->
                            val isSelected = selectedRole == role
                            val isPrimary = champion.primaryRole == role
                            val isSecondary = champion.secondaryRoles.contains(role)

                            val roleLabel = when (role) {
                                LaneRole.TOP -> tr("TOP")
                                LaneRole.JUNGLE -> tr("JUNGLA")
                                LaneRole.MID -> tr("MID")
                                LaneRole.ADC -> tr("DÚO")
                                LaneRole.SUPPORT -> tr("SOPORTE")
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when {
                                            isSelected -> HextechGold.copy(alpha = 0.28f)
                                            isPrimary -> HextechSurfaceVariant
                                            else -> HextechSurfaceVariant.copy(alpha = 0.7f)
                                        }
                                    )
                                    .border(
                                        width = if (isSelected) 1.5.dp else 1.dp,
                                        color = if (isSelected) HextechGold else HextechCardBorder,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable { selectedRole = role }
                                    .padding(vertical = 6.dp, horizontal = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = roleLabel,
                                        color = if (isSelected) HextechGold else TextPrimary,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                    )
                                    if (isPrimary) {
                                        Text("Main", color = HextechCyan, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    } else {
                                        Text("Flex", color = TextPrimary, fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        }
                    }

                    if (selectedRole != champion.primaryRole) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(HextechGold.copy(alpha = 0.12f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "⭐ ${tr("Estadísticas, hechizos, runas y build adaptadas a")} ${tr(selectedRole.displayName)}.",
                                color = TextPrimary,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ==========================================
            // ESTADÍSTICAS ADAPTADAS A LA LÍNEA (CON COMPARATIVA VS. AYER)
            // ==========================================
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.TrendingUp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = tr("Estadísticas del Meta Oficial"),
                                color = HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = tr("Tendencia en Vivo"),
                            color = HextechCyan,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        // Winrate + Delta
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(tr("Tasa de Victoria"), color = TextMuted, fontSize = 11.sp)
                            Text("${String.format(java.util.Locale.US, "%.2f", roleProfile.winrate)}%", color = HextechGold, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                            val winDelta = roleProfile.winrateDelta
                            val winDeltaText = if (winDelta >= 0) "+${winDelta}%" else "${winDelta}%"
                            val winDeltaColor = if (winDelta >= 0) Color(0xFF4CAF50) else DangerRed
                            Text(
                                text = if (winDelta >= 0) "▲ $winDeltaText" else "▼ $winDeltaText",
                                color = winDeltaColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Pick Rate + Delta
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(tr("Tasa de Selección"), color = TextMuted, fontSize = 11.sp)
                            Text("${String.format(java.util.Locale.US, "%.2f", roleProfile.pickRate)}%", color = HextechCyan, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                            val pickDelta = roleProfile.pickRateDelta
                            val pickDeltaText = if (pickDelta >= 0) "+${pickDelta}%" else "${pickDelta}%"
                            val pickDeltaColor = if (pickDelta >= 0) Color(0xFF29B6F6) else Color(0xFFFFA726)
                            Text(
                                text = if (pickDelta >= 0) "▲ $pickDeltaText" else "▼ $pickDeltaText",
                                color = pickDeltaColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Ban Rate + Delta
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(tr("Tasa de Bloqueo"), color = TextMuted, fontSize = 11.sp)
                            Text("${String.format(java.util.Locale.US, "%.2f", roleProfile.banRate)}%", color = DangerRed, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                            val banDelta = roleProfile.banRateDelta
                            val banDeltaText = if (banDelta >= 0) "+${banDelta}%" else "${banDelta}%"
                            val banDeltaColor = if (banDelta >= 0) DangerRed else Color(0xFF4CAF50)
                            Text(
                                text = if (banDelta >= 0) "▲ $banDeltaText" else "▼ $banDeltaText",
                                color = banDeltaColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Resumen Táctico
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(tr("Análisis Táctico en Wild Rift"), color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    val currentLang = com.example.util.LocalLanguage.current
                    val fullAnalysis = remember(champion.id, currentLang, selectedRole) {
                        CoachingGenerator.generateTacticalAnalysis(champion, selectedRole, currentLang)
                    }
                    FormattedWildRiftText(
                        text = fullAnalysis,
                        color = TextPrimary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // HABILIDADES DEL CAMPEÓN (CON IMÁGENES)
            // ==========================================
            if (champion.skills.isNotEmpty()) {
                Text(
                    text = tr("Habilidades de") + " ${champion.name}",
                    color = HextechGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    champion.skills.forEach { skill ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = HextechSurface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                AppAssetImage(
                                    url = skill.iconUrl,
                                    contentDescription = skill.name,
                                    fallbackText = skill.slot,
                                    modifier = Modifier.size(42.dp),
                                    borderColor = HextechCyan,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        val slotTranslation = when {
                                            skill.slotName.contains("Pasiva", true) -> tr("Pasiva:")
                                            skill.slotName.contains("Habilidad 1", true) -> tr("Habilidad") + " 1:"
                                            skill.slotName.contains("Habilidad 2", true) -> tr("Habilidad") + " 2:"
                                            skill.slotName.contains("Habilidad 3", true) -> tr("Habilidad") + " 3:"
                                            skill.slotName.contains("Definitiva", true) -> tr("Definitiva:")
                                            else -> skill.slotName
                                        }
                                        Text(
                                            text = "$slotTranslation ${skill.name}",
                                            color = TextPrimary,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        if (skill.cooldown.isNotBlank()) {
                                            Text(
                                                text = skill.cooldown,
                                                color = HextechCyan,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(3.dp))
                                    FormattedWildRiftText(
                                        text = tr(skill.description),
                                        color = TextPrimary.copy(alpha = 0.9f),
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // ==========================================
            // BUILD BÁSICA (8 OBJETOS: 1-6 CORE + 7-8 SITUACIONALES)
            // ==========================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${tr("Build básica")} • ${selectedRole.shortName}",
                    color = HextechGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "8 Objetos (1-6 Core • 7-8 Situacionales)",
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    val buildList = if (roleProfile.build8Items.isNotEmpty()) {
                        roleProfile.build8Items
                    } else {
                        (roleProfile.coreItems + roleProfile.situationalItems).take(8)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        buildList.forEachIndexed { idx, rawName ->
                            val isSituational = idx >= 6
                            val dbItem = WildRiftItemsData.getItemByName(rawName)
                                ?: com.example.data.WildRiftRepository.items.find {
                                    it.name.equals(rawName, ignoreCase = true) || it.nameEn.equals(rawName, ignoreCase = true)
                                }
                            val iconUrl = dbItem?.iconUrl ?: WildRiftItemsData.getItemIconByName(rawName)
                            val itemName = dbItem?.name?.let { tr(it) } ?: tr(rawName)

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable {
                                        if (dbItem != null) {
                                            itemForDetail = dbItem
                                        } else {
                                            selectedSituationalItem = rawName
                                        }
                                    }
                                    .padding(vertical = 4.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(HextechSurfaceVariant)
                                        .border(
                                            width = if (isSituational) 1.5.dp else 1.5.dp,
                                            color = if (isSituational) HextechCyan.copy(alpha = 0.8f) else HextechGold,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                ) {
                                    AppAssetImage(
                                        url = iconUrl,
                                        contentDescription = itemName,
                                        fallbackText = itemName,
                                        modifier = Modifier.size(42.dp),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clip(CircleShape)
                                        .background(if (isSituational) HextechCyan.copy(alpha = 0.25f) else HextechGold.copy(alpha = 0.25f))
                                        .border(1.dp, if (isSituational) HextechCyan else HextechGold, CircleShape)
                                ) {
                                    Text(
                                        text = "${idx + 1}",
                                        color = if (isSituational) HextechCyan else HextechGold,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ==========================================
            // BOTAS Y MEJORAS + HECHIZOS (DOS COLUMNAS)
            // ==========================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Card 1: Botas y Mejoras
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = tr("Botas y Mejoras"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val bootBaseName = roleProfile.bootBase.ifBlank { "Botas blindadas" }
                            val bootUpgradeName = roleProfile.bootUpgrade.ifBlank { "Avance blindado" }
                            
                            val dbBoot1 = com.example.data.WildRiftRepository.items.find { it.name.equals(bootBaseName, ignoreCase = true) || bootBaseName.contains(it.name, ignoreCase = true) }
                            val dbBoot2 = com.example.data.WildRiftRepository.items.find { it.name.equals(bootUpgradeName, ignoreCase = true) || bootUpgradeName.contains(it.name, ignoreCase = true) }
                            
                            val boot1Icon = dbBoot1?.iconUrl ?: com.example.data.WildRiftItemsData.getItemIconByName(bootBaseName)
                            val boot2Icon = dbBoot2?.iconUrl ?: com.example.data.WildRiftItemsData.getItemIconByName(bootUpgradeName)

                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(1.dp, HextechGold, RoundedCornerShape(8.dp))
                                    .clickable { if (dbBoot1 != null) itemForDetail = dbBoot1 }
                            ) {
                                AppAssetImage(
                                    url = boot1Icon,
                                    contentDescription = tr(bootBaseName),
                                    fallbackText = tr(bootBaseName),
                                    modifier = Modifier.fillMaxSize(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = ">",
                                tint = HextechCyan,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))

                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(1.dp, HextechCyan, RoundedCornerShape(8.dp))
                                    .clickable { if (dbBoot2 != null) itemForDetail = dbBoot2 }
                            ) {
                                AppAssetImage(
                                    url = boot2Icon,
                                    contentDescription = tr(bootUpgradeName),
                                    fallbackText = tr(bootUpgradeName),
                                    modifier = Modifier.fillMaxSize(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }
                        }
                    }
                }

                // Card 2: Hechizos de Invocador
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = tr("Hechizos"),
                            color = HextechGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            roleProfile.spellsIcons.take(2).forEachIndexed { idx, iconUrl ->
                                val rawSpellName = roleProfile.recommendedSpells.getOrNull(idx) ?: "Destello"
                                val spellName = tr(rawSpellName)
                                val dbSpell = com.example.data.WildRiftRepository.summonerSpells.find {
                                    it.name.equals(rawSpellName, ignoreCase = true) || rawSpellName.contains(it.name, ignoreCase = true) || it.name.contains(rawSpellName, ignoreCase = true)
                                }
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(HextechSurfaceVariant)
                                        .border(1.5.dp, HextechCyan, CircleShape)
                                        .clickable { if (dbSpell != null) spellForDetail = dbSpell }
                                ) {
                                    AppAssetImage(
                                        url = iconUrl,
                                        contentDescription = spellName,
                                        fallbackText = spellName,
                                        modifier = Modifier.fillMaxSize(),
                                        shape = CircleShape
                                    )
                                }
                                if (idx == 0) Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ==========================================
            // SECCIÓN RUNAS (1ª OPCIÓN & 2ª OPCIÓN)
            // ==========================================
            Text(
                text = "${tr("Runas")} • ${selectedRole.shortName}",
                color = HextechGold,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    val opt1 = if (roleProfile.runesOption1.isNotEmpty()) {
                        roleProfile.runesOption1
                    } else {
                        val parsed = roleProfile.runeTreeDetails.replace(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+:\\s*"), "").split("•").map { it.trim() }.filter { it.isNotEmpty() }
                        listOf(roleProfile.recommendedRunes) + parsed
                    }

                    // 1ª Opción
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tr("1º opción") + " (Meta)",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (opt1.isNotEmpty()) {
                            Text(
                                text = tr(opt1.first()),
                                color = HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        opt1.take(5).forEachIndexed { idx, rName ->
                            val isKeystone = idx == 0
                            val foundRune = com.example.data.WildRiftSpellsAndRunes.getRuneByName(rName)
                                ?: com.example.data.WildRiftRepository.runes.find { r -> r.name.equals(rName, ignoreCase = true) || rName.contains(r.name, ignoreCase = true) || r.name.contains(rName, ignoreCase = true) }
                            val iconUrl = foundRune?.iconUrl ?: com.example.data.WildRiftSpellsAndRunes.getRuneIconByName(rName)

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(if (isKeystone) 42.dp else 36.dp)
                                    .clip(CircleShape)
                                    .background(HextechSurfaceVariant)
                                    .border(
                                        width = if (isKeystone) 2.dp else 1.dp,
                                        color = if (isKeystone) HextechGold else HextechCyan.copy(alpha = 0.6f),
                                        shape = CircleShape
                                    )
                                    .clickable { 
                                        runeForDetail = foundRune ?: com.example.model.RuneItem(
                                            id = rName.lowercase().replace(" ", "_"),
                                            name = rName,
                                            category = if (isKeystone) "Clave" else "Secundaria",
                                            iconUrl = iconUrl,
                                            description = "Runa oficial de Wild Rift recomendada para esta configuración táctica."
                                        )
                                    }
                            ) {
                                AppAssetImage(
                                    url = iconUrl,
                                    contentDescription = tr(rName),
                                    fallbackText = tr(rName),
                                    modifier = Modifier.fillMaxSize(),
                                    shape = CircleShape
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    androidx.compose.material3.HorizontalDivider(
                        color = HextechCardBorder.copy(alpha = 0.6f),
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val opt2 = if (roleProfile.runesOption2.isNotEmpty()) {
                        roleProfile.runesOption2
                    } else {
                        listOf("Irrupción de Fase", "Banda de Maná", "Trascendencia", "Piroláser", "Se Avecina Tormenta")
                    }

                    // 2ª Opción
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = tr("2º opción") + " (Situacional)",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (opt2.isNotEmpty()) {
                            Text(
                                text = tr(opt2.first()),
                                color = HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        opt2.take(5).forEachIndexed { idx, rName ->
                            val isKeystone = idx == 0
                            val foundRune = com.example.data.WildRiftSpellsAndRunes.getRuneByName(rName)
                                ?: com.example.data.WildRiftRepository.runes.find { r -> r.name.equals(rName, ignoreCase = true) || rName.contains(r.name, ignoreCase = true) || r.name.contains(rName, ignoreCase = true) }
                            val iconUrl = foundRune?.iconUrl ?: com.example.data.WildRiftSpellsAndRunes.getRuneIconByName(rName)

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(if (isKeystone) 42.dp else 36.dp)
                                    .clip(CircleShape)
                                    .background(HextechSurfaceVariant)
                                    .border(
                                        width = if (isKeystone) 2.dp else 1.dp,
                                        color = if (isKeystone) HextechCyan else HextechCardBorder,
                                        shape = CircleShape
                                    )
                                    .clickable { 
                                        runeForDetail = foundRune ?: com.example.model.RuneItem(
                                            id = rName.lowercase().replace(" ", "_"),
                                            name = rName,
                                            category = if (isKeystone) "Clave" else "Secundaria",
                                            iconUrl = iconUrl,
                                            description = "Runa oficial de Wild Rift recomendada para esta configuración táctica."
                                        )
                                    }
                            ) {
                                AppAssetImage(
                                    url = iconUrl,
                                    contentDescription = tr(rName),
                                    fallbackText = tr(rName),
                                    modifier = Modifier.fillMaxSize(),
                                    shape = CircleShape
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

                    if (roleProfile.itemSwaps.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(tr("CAMBIOS SITUACIONALES"), color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        
                        roleProfile.itemSwaps.forEach { swap ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
                                    .border(1.dp, HextechCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF07121A)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(tr(swap.reasonTitle), color = HextechCyan, fontSize = 11.sp, fontWeight = FontWeight.Black)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        // Primer Objeto (Situacional Base 7 u 8)
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable {
                                                    val db = WildRiftItemsData.getItemByName(swap.coreItem)
                                                    if (db != null) {
                                                        itemForDetail = db
                                                    } else {
                                                        selectedSituationalItem = swap.coreItem
                                                    }
                                                }
                                                .padding(4.dp)
                                        ) {
                                            AppAssetImage(
                                                url = swap.coreItemIcon,
                                                contentDescription = tr(swap.coreItem),
                                                fallbackText = tr(swap.coreItem),
                                                modifier = Modifier.size(44.dp),
                                                borderColor = HextechGold,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "${tr("Base")}\n${tr(swap.coreItem)}",
                                                color = HextechGoldLight,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                textAlign = TextAlign.Center,
                                                lineHeight = 12.sp
                                            )
                                        }
                                        
                                        // Arrow
                                        Box(
                                            modifier = Modifier
                                                .padding(horizontal = 6.dp)
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(HextechCyan.copy(alpha = 0.2f))
                                                .border(1.dp, HextechCyan, RoundedCornerShape(6.dp))
                                                .padding(6.dp)
                                        ) {
                                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Swap", tint = HextechGold, modifier = Modifier.size(16.dp))
                                        }
                                        
                                        // Segundo Objeto (Alternativa Situacional)
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable {
                                                    val db = WildRiftItemsData.getItemByName(swap.altItem)
                                                    if (db != null) {
                                                        itemForDetail = db
                                                    } else {
                                                        selectedSituationalItem = swap.altItem
                                                    }
                                                }
                                                .padding(4.dp)
                                        ) {
                                            AppAssetImage(
                                                url = swap.altItemIcon,
                                                contentDescription = tr(swap.altItem),
                                                fallbackText = tr(swap.altItem),
                                                modifier = Modifier.size(44.dp),
                                                borderColor = HextechCyan,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "${tr("Reemplazo")}\n${tr(swap.altItem)}",
                                                color = HextechCyan,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                textAlign = TextAlign.Center,
                                                lineHeight = 12.sp
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(tr(swap.reasonDesc), color = TextPrimary, fontSize = 12.sp, lineHeight = 16.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(tr("Bueno contra:") + " " + tr(swap.againstWho), color = TextPrimary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                }
                            }
                        }
                    }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // COUNTERS Y SINERGIAS (ADAPTADOS AL ROL)
            // ==========================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Fuerte Contra (Ventaja)
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AllyBlue.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(tr("Ventaja Contra:"), color = AllyBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        val advantageList = roleProfile.advantageAgainst.take(5)
                        advantageList.forEach { target ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        matchupExplanationTarget = target
                                        matchupExplanationType = "Ventaja"
                                    }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("• $target", color = TextPrimary, fontSize = 12.sp)
                            }
                        }
                    }
                }

                // Débil Contra (Debilidad)
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DangerRed.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(tr("Débil Contra:"), color = DangerRed, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        val counteredList = roleProfile.counteredBy.take(5)
                        counteredList.forEach { counter ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        matchupExplanationTarget = counter
                                        matchupExplanationType = "Debilidad"
                                    }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("• $counter", color = TextPrimary, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mejores Sinergias
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechGoldLight.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(tr("Mejores Sinergias:"), color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    val synergyList = roleProfile.synergies.take(5)

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        synergyList.forEach { ally ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant)
                                    .clickable {
                                        matchupExplanationTarget = ally
                                        matchupExplanationType = "Sinergia"
                                    }
                                    .padding(horizontal = 8.dp, vertical = 6.dp)
                            ) {
                                Text(ally, color = TextPrimary, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    // ==========================================
    // DIALOG DE DETALLE DE OBJETO SITUACIONAL
    // ==========================================
    if (selectedSituationalItem != null) {
        val itemName = selectedSituationalItem!!
        val advice = SituationalItemAdvisor.getAdvice(itemName)

        AlertDialog(
            onDismissRequest = { selectedSituationalItem = null },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (advice.iconUrl.isNotBlank()) {
                        AppAssetImage(
                            url = advice.iconUrl,
                            contentDescription = tr(advice.name),
                            fallbackText = advice.name,
                            modifier = Modifier.size(36.dp),
                            borderColor = HextechGold,
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Column {
                        Text(
                            text = tr(advice.name),
                            color = HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = tr(advice.categoryName),
                            color = HextechCyan,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // ¿Por qué comprarlo?
                    Column {
                        Text(
                            text = tr("¿Por qué comprar este objeto?"),
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        FormattedWildRiftText(
                            text = advice.purpose,
                            color = TextPrimary,
                            fontSize = 12.5.sp,
                            lineHeight = 17.sp
                        )
                    }

                    // Contra quién o qué es bueno
                    Column {
                        Text(
                            text = tr("Efectivo contra:"),
                            color = DangerRed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            advice.bestAgainst.forEach { target ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(HextechSurfaceVariant)
                                        .border(1.dp, DangerRed.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "⚔️ $target",
                                        color = TextPrimary,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }

                    // Efecto clave
                    Column {
                        Text(
                            text = tr("Efecto clave:"),
                            color = HextechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        FormattedWildRiftText(
                            text = advice.keyEffect,
                            color = TextPrimary.copy(alpha = 0.9f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }

                    // Consejo táctico
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechGold.copy(alpha = 0.12f))
                            .border(1.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "💡 ${advice.recommendationTip}",
                            color = TextPrimary,
                            fontSize = 11.5.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedSituationalItem = null }) {
                    Text(tr("Entendido"), color = HextechCyan, fontWeight = FontWeight.Bold)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold,
            textContentColor = TextPrimary
        )
    }

    // ==========================================
    // DIALOG DE DETALLE DE MATCHUP / SINERGIA
    // ==========================================
    if (matchupExplanationTarget != null && matchupExplanationType != null) {
        val type = matchupExplanationType!!
        val target = matchupExplanationTarget!!

        val titleText = if (com.example.util.LocalLanguage.current == "es" || com.example.util.LocalLanguage.current == "auto") {
            when (type) {
                "Ventaja" -> "Ventaja contra $target"
                "Debilidad" -> "Débil contra $target"
                "Situacional" -> "Objeto Situacional: $target"
                else -> "Sinergia con $target"
            }
        } else {
            when (type) {
                "Ventaja" -> "Strong against $target"
                "Debilidad" -> "Weak against $target"
                "Situacional" -> "Situational Item: $target"
                else -> "Synergy with $target"
            }
        }

        val descText = CoachingGenerator.generateMatchupReason(champion, selectedRole, target, type, com.example.util.LocalLanguage.current)

        AlertDialog(
            onDismissRequest = { matchupExplanationTarget = null },
            title = {
                Text(
                    text = titleText,
                    color = HextechGold,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(descText, color = TextPrimary)
            },
            confirmButton = {
                TextButton(onClick = { matchupExplanationTarget = null }) {
                    Text(tr("Entendido"), color = HextechCyan)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold,
            textContentColor = TextPrimary
        )
    }


    itemForDetail?.let { item ->
        androidx.compose.ui.window.Dialog(onDismissRequest = { itemForDetail = null }) {
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = com.example.ui.theme.HextechDarkBg),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, com.example.ui.theme.HextechGold)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val lang = LocalLanguage.current
                    val localizedName = item.getLocalizedName(lang)
                    val statsList = item.getStatsList(lang)
                    val localizedPassive = item.getLocalizedPassive(lang)
                    val localizedCoachTip = item.getLocalizedCoachTip(lang)

                    com.example.ui.components.AppAssetImage(
                        url = item.iconUrl,
                        contentDescription = localizedName,
                        fallbackText = localizedName,
                        modifier = Modifier.size(72.dp),
                        borderColor = com.example.ui.theme.HextechGold,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = localizedName,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .background(com.example.ui.theme.HextechCyan.copy(alpha = 0.2f), androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = tr(item.category),
                                color = com.example.ui.theme.HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(com.example.ui.theme.HextechGold.copy(alpha = 0.2f), androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "🟡 ${item.goldCost} ${tr("Oro")}",
                                color = com.example.ui.theme.HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    if (statsList.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = tr("Estadísticas:"),
                            color = com.example.ui.theme.HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.5.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            statsList.forEach { stat ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .background(com.example.ui.theme.HextechCyan, androidx.compose.foundation.shape.CircleShape)
                                    )
                                    Text(
                                        text = stat.parseHtmlColorToAnnotatedString(),
                                        color = TextPrimary,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                    if (localizedPassive.isNotBlank()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = tr("Efecto / Pasiva:"),
                            color = com.example.ui.theme.HextechGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.5.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        com.example.ui.components.FormattedWildRiftText(
                            text = localizedPassive,
                            color = com.example.ui.theme.TextMuted,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (localizedCoachTip.isNotBlank()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.HextechGold.copy(alpha = 0.08f)),
                            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.HextechGold.copy(alpha = 0.4f))
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text("💡", fontSize = 13.sp)
                                    Text(
                                        text = tr("Consejos del Coach:"),
                                        color = com.example.ui.theme.HextechGoldLight,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                com.example.ui.components.FormattedWildRiftText(
                                    text = localizedCoachTip,
                                    color = TextPrimary.copy(alpha = 0.95f),
                                    fontSize = 11.5.sp,
                                    lineHeight = 15.5.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                            .background(com.example.ui.theme.HextechCyan)
                            .clickable { itemForDetail = null }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(tr("Cerrar"), color = com.example.ui.theme.HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }

    runeForDetail?.let { rune ->
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { runeForDetail = null },
            containerColor = com.example.ui.theme.HextechSurface,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    com.example.ui.components.AppAssetImage(
                        url = rune.iconUrl,
                        contentDescription = rune.name,
                        fallbackText = rune.name,
                        modifier = Modifier.size(48.dp),
                        borderColor = com.example.ui.theme.HextechGold,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = tr(rune.name),
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = tr(rune.category),
                            color = com.example.ui.theme.HextechCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            },
            text = {
                Text(
                    text = tr(rune.description),
                    color = com.example.ui.theme.TextPrimary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { runeForDetail = null }) {
                    Text(tr("Cerrar"), color = com.example.ui.theme.HextechCyan, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    spellForDetail?.let { spell ->
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { spellForDetail = null },
            containerColor = com.example.ui.theme.HextechSurface,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    com.example.ui.components.AppAssetImage(
                        url = spell.iconUrl,
                        contentDescription = spell.name,
                        fallbackText = spell.name,
                        modifier = Modifier.size(48.dp),
                        borderColor = com.example.ui.theme.HextechGold,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = tr(spell.name),
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            text = {
                Text(
                    text = tr(spell.description),
                    color = com.example.ui.theme.TextPrimary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { spellForDetail = null }) {
                    Text(tr("Cerrar"), color = com.example.ui.theme.HextechCyan, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

}
