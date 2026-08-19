package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
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
import com.example.util.tr
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ChampionAvatar
import com.example.ui.components.InAppWebSourceDialog
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChampionDetailSheet(
    champion: Champion?,
    onDismiss: () -> Unit
) {
    if (champion == null) return

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var activeWebUrl by remember { mutableStateOf<String?>(null) }
    var activeWebTitle by remember { mutableStateOf<String?>(null) }
    var selectedRole by remember(champion.id) { mutableStateOf(champion.primaryRole) }
    var matchupExplanationTarget by remember { mutableStateOf<String?>(null) }
    var matchupExplanationType by remember { mutableStateOf<String?>(null) }

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
                                    text = "Tier ${champion.tier}",
                                    color = Color.Black,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                        if (champion.title.isNotBlank()) {
                            Text(
                                text = champion.title,
                                color = HextechGoldLight,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            text = "${selectedRole.displayName}${if (selectedRole != champion.primaryRole) " (Flex)" else ""} • ${champion.damageType.displayName}",
                            color = HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        if (champion.secondaryRoles.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Flex:",
                                    color = HextechGold,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                champion.secondaryRoles.forEach { sec ->
                                    val isSelected = selectedRole == sec
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(
                                                if (isSelected) HextechGold.copy(alpha = 0.35f)
                                                else HextechGold.copy(alpha = 0.15f)
                                            )
                                            .border(
                                                width = if (isSelected) 1.5.dp else 1.dp,
                                                color = if (isSelected) HextechGold else HextechGold.copy(alpha = 0.5f),
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .clickable { selectedRole = sec }
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = if (isSelected) "✓ ${sec.shortName}" else sec.shortName,
                                            color = if (isSelected) HextechGold else HextechGoldLight,
                                            fontSize = 10.5.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Selector interactivo de Rol / Flex
            if (champion.secondaryRoles.isNotEmpty()) {
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
                                text = "Cambiar Rol / Flex Activo:",
                                color = HextechGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Toca para alternar",
                                color = TextMuted,
                                fontSize = 10.5.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Rol Principal
                            val isPrimarySelected = selectedRole == champion.primaryRole
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isPrimarySelected) HextechCyan.copy(alpha = 0.25f)
                                        else HextechSurfaceVariant
                                    )
                                    .border(
                                        width = if (isPrimarySelected) 1.5.dp else 1.dp,
                                        color = if (isPrimarySelected) HextechCyan else HextechCardBorder,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable { selectedRole = champion.primaryRole }
                                    .padding(vertical = 6.dp, horizontal = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${if (isPrimarySelected) "✓ " else ""}${champion.primaryRole.shortName} (Principal)",
                                    color = if (isPrimarySelected) HextechCyan else TextPrimary,
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isPrimarySelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }

                            // Roles Flex Secundarios
                            champion.secondaryRoles.forEach { sec ->
                                val isSecSelected = selectedRole == sec
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            if (isSecSelected) HextechGold.copy(alpha = 0.25f)
                                            else HextechSurfaceVariant
                                        )
                                        .border(
                                            width = if (isSecSelected) 1.5.dp else 1.dp,
                                            color = if (isSecSelected) HextechGold else HextechCardBorder,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable { selectedRole = sec }
                                        .padding(vertical = 6.dp, horizontal = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${if (isSecSelected) "✓ " else ""}${sec.shortName} (Flex)",
                                        color = if (isSecSelected) HextechGold else TextPrimary,
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isSecSelected) FontWeight.Bold else FontWeight.Medium
                                    )
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
                                    text = "⭐ Configuración Flex activa: ${selectedRole.displayName}. Consejos adaptados a esta línea.",
                                    color = HextechGoldLight,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Winrate, Pickrate, Banrate Stats Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(HextechSurface)
                    .border(1.dp, HextechCardBorder, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Winrate", color = TextMuted, fontSize = 11.sp)
                    Text("${champion.winrate}%", color = HextechGold, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Pick Rate", color = TextMuted, fontSize = 11.sp)
                    Text("${champion.pickRate}%", color = HextechCyan, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ban Rate", color = TextMuted, fontSize = 11.sp)
                    Text("${champion.banRate}%", color = DangerRed, fontSize = 17.sp, fontWeight = FontWeight.Bold)
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
                        Text("Análisis Táctico en Wild Rift", color = HextechGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(champion.summary, color = TextPrimary, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // HABILIDADES DEL CAMPEÓN (CON IMÁGENES)
            // ==========================================
            if (champion.skills.isNotEmpty()) {
                Text(
                    text = "Habilidades de ${champion.name}",
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
                                        Text(
                                            text = "${skill.slotName}: ${skill.name}",
                                            color = HextechGoldLight,
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
                                    Text(
                                        text = skill.description,
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
            // HECHIZOS DE INVOCADOR & RUNAS (CON IMÁGENES)
            // ==========================================
            Text(
                text = "Hechizos & Runas Recomendadas",
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
                    // Spells
                    Text(tr("Hechizos de Invocador:"), color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        champion.spellsIcons.forEachIndexed { idx, iconUrl ->
                            val spellName = champion.recommendedSpells.getOrNull(idx) ?: "Spell"
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(1.dp, HextechGoldLight.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                AppAssetImage(
                                    url = iconUrl,
                                    contentDescription = spellName,
                                    fallbackText = spellName,
                                    modifier = Modifier.size(28.dp),
                                    borderColor = HextechGold,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(spellName, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Rune
                    Text(tr("Árbol de Runas Meta:"), color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (champion.primaryRuneIconUrl.isNotBlank()) {
                            AppAssetImage(
                                url = champion.primaryRuneIconUrl,
                                contentDescription = champion.recommendedRunes,
                                fallbackText = "Runa",
                                modifier = Modifier.size(34.dp),
                                borderColor = HextechCyan,
                                shape = CircleShape
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                        Column {
                            Text(champion.recommendedRunes, color = HextechGoldLight, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            if (champion.runeTreeDetails.isNotBlank()) {
                                Text(champion.runeTreeDetails, color = TextMuted, fontSize = 11.5.sp, lineHeight = 15.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // OBJETOS PRINCIPALES (CORE & SITUACIONALES CON IMÁGENES)
            // ==========================================
            Text(
                text = "Objetos Clave (Build Recomendada)",
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
                    Text(tr("Core Items (Obligatorios):"), color = HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        champion.coreItems.forEachIndexed { idx, itemName ->
                            val iconUrl = champion.coreItemsIcons.getOrNull(idx) ?: ""
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(HextechSurfaceVariant)
                                    .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                AppAssetImage(
                                    url = iconUrl,
                                    contentDescription = itemName,
                                    fallbackText = itemName,
                                    modifier = Modifier.size(28.dp),
                                    borderColor = HextechGold,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(itemName, color = TextPrimary, fontSize = 12.sp)
                            }
                        }
                    }

                    if (champion.situationalItems.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(tr("Objetos Situacionales:"), color = TextMuted, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            champion.situationalItems.forEachIndexed { idx, itemName ->
                                val iconUrl = champion.situationalItemsIcons.getOrNull(idx) ?: ""
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                                        .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    AppAssetImage(
                                        url = iconUrl,
                                        contentDescription = itemName,
                                        fallbackText = itemName,
                                        modifier = Modifier.size(24.dp),
                                        borderColor = HextechCyan,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(itemName, color = TextMuted, fontSize = 11.5.sp)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // COUNTERS Y SINERGIAS
            // ==========================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Fuerte Contra
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AllyBlue.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(tr("Ventaja Contra:"), color = AllyBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        val advantageList = (champion.advantageAgainst + listOf("Garen", "Ashe", "Lux", "Vi", "Master Yi")).distinct().take(5)
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

                // Débil Contra
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DangerRed.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(tr("Débil Contra:"), color = DangerRed, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        val counteredList = (champion.counteredBy + listOf("Zed", "Lee Sin", "Darius", "Akali", "Katarina")).distinct().take(5)
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
                    Text(tr("Mejores Sinergias (Composición):"), color = HextechGoldLight, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    val synergyList = (champion.synergies + listOf("Malphite", "Amumu", "Nami", "Lulu", "Yasuo")).distinct().take(5)
                    
                    @OptIn(ExperimentalLayoutApi::class)
                    androidx.compose.foundation.layout.FlowRow(
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

            Spacer(modifier = Modifier.height(18.dp))

            // Portal Meta Links
            Text(tr("Ver Guía & Estadísticas en Fuentes Meta:"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))

            val links = listOf(
                Pair("Oficial Wild Rift (ES)", "https://wildrift.leagueoflegends.com/es-es/champions/"),
                Pair("WildRiftCore (ES)", champion.wildRiftCoreUrl),
                Pair("BestBuildWR", champion.bestBuildWrUrl),
                Pair("WildRiftFire", champion.wildRiftFireUrl),
                Pair("WR-Meta", champion.wrMetaUrl)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                links.forEach { (name, url) ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechSurface)
                            .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .clickable {
                                activeWebUrl = url
                                activeWebTitle = name
                            }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Language, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(name, color = TextPrimary, fontSize = 11.5.sp, fontWeight = FontWeight.Medium)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(12.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    if (activeWebUrl != null) {
        InAppWebSourceDialog(
            url = activeWebUrl!!,
            title = activeWebTitle ?: "Fuente Meta",
            onDismiss = {
                activeWebUrl = null
                activeWebTitle = null
            }
        )
    }

    if (matchupExplanationTarget != null && matchupExplanationType != null) {
        AlertDialog(
            onDismissRequest = { matchupExplanationTarget = null },
            title = {
                Text(
                    text = when(matchupExplanationType) {
                        "Ventaja" -> "Ventaja contra ${matchupExplanationTarget}"
                        "Debilidad" -> "Débil contra ${matchupExplanationTarget}"
                        else -> "Sinergia con ${matchupExplanationTarget}"
                    },
                    color = HextechGold,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                val reasonText = when(matchupExplanationType) {
                    "Ventaja" -> "${champion.name} tiene ventaja táctica sobre ${matchupExplanationTarget} porque su kit de habilidades le permite mitigar su daño o castigar su falta de movilidad durante la fase de líneas y las peleas de equipo."
                    "Debilidad" -> "${champion.name} es débil contra ${matchupExplanationTarget}. El kit del enemigo neutraliza tus opciones principales, o tiene mayor facilidad para controlarte (ej. aplicando CC o burst)."
                    else -> "${champion.name} y ${matchupExplanationTarget} forman una sinergia muy fuerte. Sus habilidades se combinan bien (ej. control de masas en área + daño), facilitando los asedios y asegurar objetivos."
                }
                Text(reasonText, color = TextPrimary)
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
}
