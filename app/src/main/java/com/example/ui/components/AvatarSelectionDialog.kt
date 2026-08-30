package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.AvatarCatalog
import com.example.model.AvatarItem
import com.example.ui.theme.*
import com.example.util.SubscriptionManager
import com.example.util.tr

fun getRarityColor(rarity: String): Color {
    return when (rarity.lowercase()) {
        "común", "comun" -> Color(0xFF9E9E9E)
        "raro" -> Color(0xFF3B82F6) // HextechCyan-like
        "épico", "epico" -> Color(0xFFA855F7) // Purple
        "legendario" -> Color(0xFFEF4444) // Red
        "mítico", "mitico" -> Color(0xFFEC4899) // Pink
        else -> HextechGold
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarSelectionBottomSheet(
    onDismiss: () -> Unit,
    onOpenPremiumPlans: () -> Unit
) {
    val context = LocalContext.current
    val isPremium by SubscriptionManager.isPremium.collectAsState()
    val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()
    val unlockedAvatars by SubscriptionManager.unlockedAvatars.collectAsState()
    val userRole by SubscriptionManager.userRole.collectAsState()

    val validRegions = remember {
        setOf("Aguas Esturbias", "Ciudad de Bandle", "Demacia", "El Vacío", "Freljord", "Islas de la Sombra", "Jonia", "Ixtal", "Noxus", "Piltóver", "Runaterra", "Shurima", "Targon", "Zaun")
    }
    
    val filterOptions = remember {
        AvatarCatalog.avatars.map { it.region }.filter { validRegions.contains(it) }.distinct().sorted()
    }
    var selectedFilter by remember { mutableStateOf(filterOptions.firstOrNull() ?: "") }
    
    var showPremiumRequiredDialog by remember { mutableStateOf<AvatarItem?>(null) }
    var isUpdating by remember { mutableStateOf(false) }

    val groupedAvatars = remember(selectedFilter) {
        AvatarCatalog.avatars.filter { it.region.equals(selectedFilter, ignoreCase = true) }
            .groupBy { it.region }.toSortedMap()
    }

    // Modal Bottom Sheet / Full Screen Dialog
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = HextechDarkBg,
        dragHandle = {
            Surface(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .size(width = 40.dp, height = 4.dp),
                shape = CircleShape,
                color = HextechGold.copy(alpha = 0.5f)
            ) {}
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.88f)
                .padding(horizontal = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(HextechGold.copy(alpha = 0.15f))
                            .border(1.dp, HextechGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Diamond,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = tr("Avatares"),
                            color = HextechGoldLight,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isPremium) tr("Acceso Total Premium Desbloqueado") else tr("Avatares Exclusivos de League of Legends"),
                            color = if (isPremium) HextechCyan else TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Current Equipped Avatar Banner
            val currentAvatar = AvatarCatalog.getAvatarById(currentAvatarId)
            val currentRarityColorTop = getRarityColor(currentAvatar.rarity)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, currentRarityColorTop.copy(alpha = 0.7f), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserAvatarView(
                        avatarId = currentAvatarId,
                        size = 54.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = tr("Avatar Actual:"),
                                color = TextMuted,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            val currentRarityColor = getRarityColor(currentAvatar.rarity)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(currentRarityColor.copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = currentAvatar.rarity.uppercase(),
                                    color = currentRarityColor,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                        Text(
                            text = currentAvatar.name,
                            color = HextechGoldLight,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${currentAvatar.title} • ${currentAvatar.region}",
                            color = HextechCyan,
                            fontSize = 11.5.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips Carousel
            ScrollableTabRow(
                selectedTabIndex = filterOptions.indexOf(selectedFilter).coerceAtLeast(0),
                containerColor = Color.Transparent,
                contentColor = HextechCyan,
                edgePadding = 0.dp,
                divider = {}
            ) {
                filterOptions.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    Tab(
                        selected = isSelected,
                        onClick = { selectedFilter = filter },
                        text = {
                            Text(
                                text = tr(filter),
                                color = if (isSelected) HextechCyan else TextSecondary,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.5.sp
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Non-premium info banner
            if (!isPremium && userRole != "admin") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechGold.copy(alpha = 0.1f))
                        .border(1.dp, HextechGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = tr("El cambio de avatares requiere membresía Premium."),
                                color = TextPrimary,
                                fontSize = 11.sp,
                                lineHeight = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        TextButton(
                            onClick = onOpenPremiumPlans,
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = tr("Ver Planes"),
                                color = HextechGoldLight,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Grid of Avatars
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                groupedAvatars.forEach { (region, avatarsInRegion) ->
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = region.uppercase(),
                            color = HextechGoldLight,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 4.dp)
                        )
                    }
                    items(avatarsInRegion, key = { it.id }) { avatar ->
                    val isEquipped = currentAvatarId.equals(avatar.id, ignoreCase = true)
                    val isGifted = unlockedAvatars.contains(avatar.id)
                    val canEquip = isPremium || userRole == "admin" || avatar.isDefault || isGifted || avatar.rarity.equals("común", true) || avatar.rarity.equals("comun", true)

                    val rarityColor = getRarityColor(avatar.rarity)
                    val parsedBorder = rarityColor

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = canEquip || isEquipped) {
                                if (isEquipped) {
                                    Toast.makeText(context, "Este avatar ya está equipado.", Toast.LENGTH_SHORT).show()
                                } else {
                                    isUpdating = true
                                    SubscriptionManager.changeAvatar(
                                        avatarId = avatar.id,
                                        onSuccess = {
                                            isUpdating = false
                                            Toast.makeText(context, "¡Avatar actualizado con éxito!", Toast.LENGTH_SHORT).show()
                                        },
                                        onError = { err ->
                                            isUpdating = false
                                            Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                                        }
                                    )
                                }
                            }
                            .border(
                                width = if (isEquipped) 2.dp else 1.dp,
                                color = rarityColor.copy(alpha = if (isEquipped) 1f else 0.8f),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isEquipped) rarityColor.copy(alpha = 0.15f) else if (!canEquip) HextechSurface.copy(alpha = 0.5f) else HextechSurface
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                UserAvatarView(
                                    avatarId = avatar.id,
                                    size = 54.dp,
                                    customBorderColor = rarityColor
                                )

                                if (isEquipped) {
                                    Box(
                                        modifier = Modifier
                                            .size(18.dp)
                                            .clip(CircleShape)
                                            .background(HextechGold)
                                            .border(1.5.dp, HextechDarkBg, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Check,
                                            contentDescription = "Equipado",
                                            tint = HextechDarkBg,
                                            modifier = Modifier.size(12.dp)
                                        )
                                    }
                                } else if (!canEquip) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF0F172A).copy(alpha = 0.95f))
                                            .border(1.5.dp, HextechGold, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Lock,
                                            contentDescription = "Bloqueado",
                                            tint = HextechGold,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                } else if (isGifted && !avatar.isDefault) {
                                    Box(
                                        modifier = Modifier
                                            .size(18.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF10B981))
                                            .border(1.5.dp, HextechDarkBg, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Star,
                                            contentDescription = "Obsequiado",
                                            tint = Color.White,
                                            modifier = Modifier.size(11.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = avatar.name,
                                color = if (isEquipped) HextechGoldLight else if (canEquip) TextPrimary else TextMuted,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = avatar.region,
                                color = if (canEquip) HextechCyan else TextMuted.copy(alpha = 0.7f),
                                fontSize = 9.5.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Status Tag
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(
                                        if (isEquipped) HextechGold.copy(alpha = 0.25f)
                                        else if (isGifted && !avatar.isDefault) Color(0xFF10B981).copy(alpha = 0.2f)
                                        else if (canEquip) HextechCyan.copy(alpha = 0.15f)
                                        else Color.Black.copy(alpha = 0.4f)
                                    )
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = if (isEquipped) tr("ACTIVO")
                                    else if (isGifted && !avatar.isDefault) tr("REGALO")
                                    else if (canEquip) tr("LISTO")
                                    else tr("PREMIUM"),
                                    color = if (isEquipped) HextechGold
                                    else if (isGifted && !avatar.isDefault) Color(0xFF10B981)
                                    else if (canEquip) HextechCyan
                                    else HextechGold.copy(alpha = 0.8f),
                                    fontSize = 8.5.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                }
                } // End of forEach
            }
        }
    }

    // Modal when user tries to equip a locked avatar
    if (showPremiumRequiredDialog != null) {
        val lockedAvatar = showPremiumRequiredDialog!!
        AlertDialog(
            onDismissRequest = { showPremiumRequiredDialog = null },
            containerColor = HextechDarkBg,
            shape = RoundedCornerShape(16.dp),
            icon = {
                UserAvatarView(
                    avatarId = lockedAvatar.id,
                    size = 64.dp
                )
            },
            title = {
                Text(
                    text = tr("Avatar Exclusivo Premium"),
                    color = HextechGoldLight,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = lockedAvatar.name,
                        color = HextechGold,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${lockedAvatar.title} • ${lockedAvatar.region}",
                        color = HextechCyan,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = tr("Para equipar este avatar legendario de League of Legends necesitas una membresía Premium activa."),
                        color = TextSecondary,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPremiumRequiredDialog = null
                        onDismiss()
                        onOpenPremiumPlans()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Diamond, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(tr("Desbloquear con Premium"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showPremiumRequiredDialog = null }) {
                    Text(tr("Cerrar"), color = TextSecondary)
                }
            }
        )
    }
}
