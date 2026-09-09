package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.*
import com.example.model.Champion
import com.example.ui.theme.*
import com.example.util.tr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityCreatorsDialog(
    onDismiss: () -> Unit,
    onOpenBlueEssenceStore: () -> Unit
) {
    val context = LocalContext.current
    val activeProfile by AccountProfileManager.activeProfile.collectAsState()
    val allProfiles by AccountProfileManager.allProfiles.collectAsState()

    var selectedTab by remember { mutableStateOf(0) } // 0: Explorar Creadores, 1: Mis Suscripciones, 2: Taller de Creador
    var creatorsList by remember { mutableStateOf(CommunityCreatorCatalog.getCommunityCreators(context)) }

    var selectedCreatorForDetail by remember { mutableStateOf<CommunityCreatorItem?>(null) }
    var showCreatorEditor by remember { mutableStateOf(false) }
    var showApplyCreatorDialog by remember { mutableStateOf(false) }

    fun refreshData() {
        creatorsList = CommunityCreatorCatalog.getCommunityCreators(context)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.92f),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
            border = BorderStroke(1.5.dp, HextechGold)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "🌟 " + tr("Comunidad & Creadores"),
                                color = HextechGold,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = com.example.R.drawable.ic_blue_essence),
                                contentDescription = null,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = tr("Suscríbete con Esencia Azul a tus creadores favoritos"),
                                color = TextSecondary,
                                fontSize = 11.5.sp
                            )
                        }
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                
                // Banner Esencia Azul
                val currentBlueEssence by com.example.util.SubscriptionManager.blueEssence.collectAsState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HextechSurface)
                        .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = com.example.R.drawable.ic_blue_essence),
                            contentDescription = "Esencia Azul",
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(tr("Tu Esencia Azul"), color = TextSecondary, fontSize = 11.sp)
                            Text(
                                text = "$currentBlueEssence EA",
                                color = HextechCyan,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Button(
                        onClick = onOpenBlueEssenceStore,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tr("Comprar"), color = HextechDarkBg, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Tabs Navigation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val tabs = listOf("Explorar Creadores", "Mis Suscripciones (${activeProfile.subscribedTo.size})", "Mi Rol Creador")
                    tabs.forEachIndexed { index, label ->
                        val isSel = selectedTab == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSel) HextechGold else HextechSurface)
                                .clickable { selectedTab = index }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = tr(label),
                                color = if (isSel) HextechDarkBg else TextPrimary,
                                fontSize = 11.5.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Content by Tab
                when (selectedTab) {
                    0 -> {
                        // All Creators List
                        val filteredList = creatorsList.filter { it.id != activeProfile.id }
                        if (filteredList.isEmpty()) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("No hay creadores disponibles en este momento.", color = TextMuted)
                            }
                        
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(filteredList) { creator ->
                                    CreatorCardItem(
                                        creator = creator,
                                        isSubscribed = activeProfile.subscribedTo.contains(creator.id),
                                        onViewDetails = { selectedCreatorForDetail = creator },
                                        onSubscribe = {
                                            val (success, msg) = CommunityCreatorCatalog.subscribeToCreator(
                                                context = context,
                                                creatorId = creator.id,
                                                subscriberProfileId = activeProfile.id,
                                                cost = 100
                                            )
                                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                            refreshData()
                                        },
                                        onUnsubscribe = {
                                            CommunityCreatorCatalog.unsubscribeFromCreator(context, creator.id, activeProfile.id)
                                            Toast.makeText(context, "Te has desuscrito de ${creator.name}", Toast.LENGTH_SHORT).show()
                                            refreshData()
                                        }
                                    )
                                }
                            }
                        }
                    }

                    1 -> {
                        // Subscribed Creators
                        val subscribedCreators = creatorsList.filter { activeProfile.subscribedTo.contains(it.id) }
                        if (subscribedCreators.isEmpty()) {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Default.StarBorder, contentDescription = null, tint = HextechGold.copy(alpha = 0.4f), modifier = Modifier.size(56.dp))
                                Spacer(modifier = Modifier.height(12.dp))
                                Text("Aún no te has suscrito a ningún creador", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "Explora el catálogo y suscríbete con Esencia Azul para acceder a las builds y guías exclusivas de 1 campeón.",
                                    color = TextSecondary,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = { selectedTab = 0 },
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Explorar Creadores", color = HextechDarkBg, fontWeight = FontWeight.Bold)
                                }
                            }
                        
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(subscribedCreators) { creator ->
                                    CreatorCardItem(
                                        creator = creator,
                                        isSubscribed = true,
                                        onViewDetails = { selectedCreatorForDetail = creator },
                                        onSubscribe = {},
                                        onUnsubscribe = {
                                            CommunityCreatorCatalog.unsubscribeFromCreator(context, creator.id, activeProfile.id)
                                            refreshData()
                                        }
                                    )
                                }
                            }
                        }
                    }

                    2 -> {
                        // My Creator Profile / Application Tab
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            if (!activeProfile.isCreator) {
                                // Card to Apply for Creator Role
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                    border = BorderStroke(1.2.dp, HextechGold),
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("👑", fontSize = 28.sp)
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text(
                                                    text = tr("Solicitar Rol de Creador"),
                                                    color = HextechGold,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = tr("Rol exclusivo para autores de builds y guías"),
                                                    color = HextechCyan,
                                                    fontSize = 11.5.sp
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(12.dp))

                                        Text(
                                            text = "Los Creadores de la Comunidad pueden publicar la build definitiva, runas, hechizos y guía táctica de 1 campeón emblemático. Otros jugadores podrán suscribirse a tu contenido con Esencia Azul (hasta un límite de 100 suscriptores).",
                                            color = TextSecondary,
                                            fontSize = 12.sp,
                                            lineHeight = 17.sp
                                        )

                                        Spacer(modifier = Modifier.height(12.dp))

                                        // Application feature bullet points
                                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                            FeatureBulletPoint(text = "🛡️ Creación de Build Completa para 1 solo campeón")
                                            FeatureBulletPoint(text = "📦 Configuración detallada de Items, Botas y Encantamiento")
                                            FeatureBulletPoint(text = "⚡ Selección de Runas Clave, secundarias y Hechizos")
                                            FeatureBulletPoint(text = "🗺️ Guía táctica de combos con terminología Wild Rift (H1-H4)")
                                            FeatureBulletPoint(text = "💎 Límite estricto de 100 suscriptores con recompensas en Esencia Azul")
                                        }

                                        Spacer(modifier = Modifier.height(16.dp))

                                        Button(
                                            onClick = { showApplyCreatorDialog = true },
                                            colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                                            modifier = Modifier.fillMaxWidth().height(46.dp),
                                            shape = RoundedCornerShape(10.dp)
                                        ) {
                                            Icon(Icons.Default.Star, contentDescription = null, tint = HextechDarkBg)
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Solicitar ser Creador Oficial",
                                                color = HextechDarkBg,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp
                                            )
                                        }
                                    }
                                }
                            
                                // User IS a Creator!
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                    border = BorderStroke(1.2.dp, HextechGold),
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                UserAvatarView(avatarId = activeProfile.avatarId, size = 44.dp, fallbackInitial = activeProfile.name)
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Column {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Text(activeProfile.name, color = HextechGold, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Box(
                                                            modifier = Modifier
                                                                .clip(RoundedCornerShape(4.dp))
                                                                .background(HextechGold)
                                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                                        ) {
                                                            Text("CREADOR", color = HextechDarkBg, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold)
                                                        }
                                                    }
                                                    Text(activeProfile.tag.ifBlank { "Invocador Pro" }, color = HextechCyan, fontSize = 11.5.sp)
                                                }
                                            }

                                            // Subscribers counter badge
                                            Column(horizontalAlignment = Alignment.End) {
                                                Text(
                                                    text = "${activeProfile.subscribersCount} / 100",
                                                    color = if (activeProfile.subscribersCount >= 100) DangerRed else HextechGold,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.ExtraBold
                                                )
                                                Text(
                                                    text = "Suscriptores (Máx 100)",
                                                    color = TextMuted,
                                                    fontSize = 10.sp
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Progress bar for 100 subscribers
                                        val progress = (activeProfile.subscribersCount / 100f).coerceIn(0f, 1f)
                                        LinearProgressIndicator(
                                            progress = { progress },
                                            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                                            color = if (activeProfile.subscribersCount >= 100) DangerRed else HextechGold,
                                            trackColor = HextechSurfaceVariant
                                        )

                                        Spacer(modifier = Modifier.height(14.dp))
                                        HorizontalDivider(color = HextechCardBorder)
                                        Spacer(modifier = Modifier.height(14.dp))

                                        // Champion Build Status
                                        val myBuild = activeProfile.creatorBuild
                                        if (myBuild == null) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                                                    .padding(16.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Icon(Icons.Default.Build, contentDescription = null, tint = HextechGold, modifier = Modifier.size(36.dp))
                                                    Spacer(modifier = Modifier.height(8.dp))
                                                    Text(
                                                        text = "Aún no has creado tu build de campeón",
                                                        color = TextPrimary,
                                                        fontSize = 13.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = "Solo puedes crear la build de 1 campeón. ¡Elige con sabiduría!",
                                                        color = TextSecondary,
                                                        fontSize = 11.5.sp,
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Spacer(modifier = Modifier.height(12.dp))
                                                    Button(
                                                        onClick = { showCreatorEditor = true },
                                                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Text("Crear Build de mi Campeón (1 Solo)", color = HextechDarkBg, fontWeight = FontWeight.Bold)
                                                    }
                                                }
                                            }
                                        } else {

                                        
                                            // Displays active 1 champion build
                                            val champ = WildRiftRepository.champions.find { it.name.equals(myBuild.championName, ignoreCase = true) }
                                            Column {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        if (champ != null) {
                                                            ChampionAvatar(champion = champ, size = 48.dp, showTierBadge = false)
                                                            Spacer(modifier = Modifier.width(10.dp))
                                                        }
                                                        Column {
                                                            Text(myBuild.title, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                                            Text("${myBuild.championName} • Rol ${myBuild.role}", color = HextechCyan, fontSize = 11.5.sp)
                                                        }
                                                    }

                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(6.dp))
                                                            .background(DangerRed.copy(alpha = 0.2f))
                                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                                    ) {
                                                        Text("1 Campeón Asignado", color = DangerRed, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                }

                                                Spacer(modifier = Modifier.height(10.dp))

                                                // Objects & Runes preview
                                                Text("Items Principales:", color = TextMuted, fontSize = 10.5.sp)
                                                Text(
                                                    text = myBuild.coreItems.joinToString(" • ").ifBlank { "Sin items asignados" },
                                                    color = TextSecondary,
                                                    fontSize = 11.sp,
                                                    maxLines = 2,
                                                    overflow = TextOverflow.Ellipsis
                                                )

                                                Spacer(modifier = Modifier.height(6.dp))

                                                Text("Runas & Hechizos:", color = TextMuted, fontSize = 10.5.sp)
                                                Text(
                                                    text = "${myBuild.keystoneRune} + (${myBuild.secondaryRunes.joinToString()}) | ${myBuild.spell1} / ${myBuild.spell2}",
                                                    color = TextSecondary,
                                                    fontSize = 11.sp
                                                )

                                                Spacer(modifier = Modifier.height(12.dp))

                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Button(
                                                        onClick = { showCreatorEditor = true },
                                                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                                                        modifier = Modifier.weight(1f),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Icon(Icons.Default.Edit, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Text("Editar mi Build", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                                    }

                                                    OutlinedButton(
                                                        onClick = {
                                                            AccountProfileManager.deleteCreatorBuild(context, activeProfile.id)
                                                            Toast.makeText(context, "Build eliminada. Ahora puedes elegir otro campeón.", Toast.LENGTH_SHORT).show()
                                                            refreshData()
                                                        },
                                                        border = BorderStroke(1.dp, DangerRed),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Text("Cambiar Campeón", color = DangerRed, fontSize = 12.sp)
                                                    }
                                                }
                                            }
                                        }


                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal: Detail View of a Creator's Build & Guide
    if (selectedCreatorForDetail != null) {
        CreatorBuildDetailDialog(
            creator = selectedCreatorForDetail!!,
            isSubscribed = activeProfile.subscribedTo.contains(selectedCreatorForDetail!!.id),
            onDismiss = { selectedCreatorForDetail = null },
            onSubscribe = {
                val (success, msg) = CommunityCreatorCatalog.subscribeToCreator(
                    context = context,
                    creatorId = selectedCreatorForDetail!!.id,
                    subscriberProfileId = activeProfile.id,
                    cost = 100
                )
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                refreshData()
                selectedCreatorForDetail = null
            },
            onUnsubscribe = {
                CommunityCreatorCatalog.unsubscribeFromCreator(context, selectedCreatorForDetail!!.id, activeProfile.id)
                Toast.makeText(context, "Te has desuscrito de ${selectedCreatorForDetail!!.name}", Toast.LENGTH_SHORT).show()
                refreshData()
                selectedCreatorForDetail = null
            }
        )
    }

    // Modal: Creator Build Editor (Strictly 1 Champion)
    if (showCreatorEditor) {
        CreatorBuildEditorDialog(
            initialBuild = activeProfile.creatorBuild,
            onDismiss = { showCreatorEditor = false },
            onBuildSaved = {
                showCreatorEditor = false
                refreshData()
            }
        )
    }

    // Modal: Application Dialog to become a Creator
    if (showApplyCreatorDialog) {
        ApplyCreatorDialog(
            onDismiss = { showApplyCreatorDialog = false },
            onApply = { reason ->
                AccountProfileManager.applyForCreator(context, activeProfile.id, reason)
                Toast.makeText(context, "¡Felicitaciones! Ahora tienes el Rol de Creador activo.", Toast.LENGTH_LONG).show()
                showApplyCreatorDialog = false
                refreshData()
            }
        )
    }
}

@Composable
private fun CreatorCardItem(
    creator: CommunityCreatorItem,
    isSubscribed: Boolean,
    onViewDetails: () -> Unit,
    onSubscribe: () -> Unit,
    onUnsubscribe: () -> Unit
) {
    val champ = remember(creator.build.championName) {
        WildRiftRepository.champions.find {
            it.name.equals(creator.build.championName, ignoreCase = true) ||
            it.id.equals(creator.build.championId, ignoreCase = true)
        }
    }

    val isFull = creator.subscribersCount >= 100

    Card(
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, if (isSubscribed) HextechGold else HextechCardBorder),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().clickable { onViewDetails() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Top Creator info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UserAvatarView(avatarId = creator.avatarId, size = 38.dp, fallbackInitial = creator.name)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(creator.name, color = HextechGold, fontSize = 13.5.sp, fontWeight = FontWeight.Bold)
                            if (creator.isLocalUser) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(HextechCyan.copy(alpha = 0.2f))
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Text("TÚ", color = HextechCyan, fontSize = 8.5.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                        Text(creator.tag, color = TextSecondary, fontSize = 11.sp)
                    }
                }

                // Subscribers badge with 100 limit
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            when {
                                isFull -> DangerRed.copy(alpha = 0.15f)
                                isSubscribed -> HextechGold.copy(alpha = 0.15f)
                                else -> HextechSurfaceVariant
                            }
                        )
                        .border(
                            1.dp,
                            when {
                                isFull -> DangerRed
                                isSubscribed -> HextechGold
                                else -> HextechCardBorder
                            },
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (isFull) "LÍMITE (100/100)" else "${creator.subscribersCount} / 100 Subs",
                        color = when {
                            isFull -> DangerRed
                            isSubscribed -> HextechGold
                            else -> HextechCyan
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Featured 1 Champion Build preview
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (champ != null) {
                    ChampionAvatar(champion = champ, size = 44.dp, showTierBadge = false)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = creator.build.championName,
                            color = TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(HextechCyan.copy(alpha = 0.2f))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(creator.build.role, color = HextechCyan, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Text(
                        text = creator.build.title,
                        color = HextechGold,
                        fontSize = 11.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onViewDetails,
                    modifier = Modifier.weight(1f).height(36.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.7f)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(Icons.Default.Visibility, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Ver Build & Guía", color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                }

                if (isSubscribed) {
                    Button(
                        onClick = onUnsubscribe,
                        modifier = Modifier.weight(1f).height(36.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                        border = BorderStroke(1.dp, HextechGold),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = HextechGold, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Suscrito ✓", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    }
                
                    Button(
                        onClick = onSubscribe,
                        enabled = !isFull,
                        modifier = Modifier.weight(1f).height(36.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFull) HextechSurfaceVariant else HextechGold,
                            disabledContainerColor = HextechSurfaceVariant
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        if (isFull) {
                            Text("Cupo Lleno (100)", color = DangerRed, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        } else {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = com.example.R.drawable.ic_blue_essence),
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Suscribirme (100 EA)", color = HextechDarkBg, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreatorBuildDetailDialog(
    creator: CommunityCreatorItem,
    isSubscribed: Boolean,
    onDismiss: () -> Unit,
    onSubscribe: () -> Unit,
    onUnsubscribe: () -> Unit
) {
    val build = creator.build
    val champ = remember(build.championName) {
        WildRiftRepository.champions.find {
            it.name.equals(build.championName, ignoreCase = true) ||
            it.id.equals(build.championId, ignoreCase = true)
        }
    }

    val isFull = creator.subscribersCount >= 100

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.92f),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg),
            border = BorderStroke(1.5.dp, HextechGold)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        UserAvatarView(avatarId = creator.avatarId, size = 40.dp, fallbackInitial = creator.name)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(creator.name, color = HextechGold, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(HextechGold)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text("CREADOR", color = HextechDarkBg, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            Text(creator.tag, color = TextSecondary, fontSize = 11.5.sp)
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = HextechCardBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // Scrollable Build & Guide
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // 1. Champion Banner (Only 1 champion)
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechGold),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (champ != null) {
                                ChampionAvatar(champion = champ, size = 56.dp, showTierBadge = false)
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(build.championName, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(HextechCyan.copy(alpha = 0.2f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(build.role, color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Text(build.title, color = HextechGold, fontSize = 12.5.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }

                    // 2. Ruta de Objetos
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("📦 Ruta de Objetos de Wild Rift", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                            // Objeto inicial
                            if (build.startingItem.isNotBlank()) {
                                Text("Objeto Inicial: ${build.startingItem}", color = TextSecondary, fontSize = 11.5.sp)
                            }

                            // Botas y Encantamiento
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val bItem = WildRiftItemsData.list.find { it.name.equals(build.bootsItem, ignoreCase = true) }
                                val bEnchant = WildRiftItemsData.list.find { it.name.equals(build.bootsEnchant, ignoreCase = true) }

                                if (bItem != null) {
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                        AsyncImage(model = bItem.iconUrl, contentDescription = bItem.name, modifier = Modifier.size(32.dp).clip(RoundedCornerShape(6.dp)))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(bItem.name, color = TextPrimary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    }
                                }
                                if (bEnchant != null) {
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                        AsyncImage(model = bEnchant.iconUrl, contentDescription = bEnchant.name, modifier = Modifier.size(32.dp).clip(RoundedCornerShape(6.dp)))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(bEnchant.name, color = HextechCyan, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    }
                                }
                            }

                            // Core Items
                            Text("Objetos Principales (Core):", color = TextSecondary, fontSize = 11.5.sp)
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                build.coreItems.forEach { name ->
                                    val item = WildRiftItemsData.list.find { it.name.equals(name, ignoreCase = true) }
                                    if (item != null) {
                                        AsyncImage(
                                            model = item.iconUrl,
                                            contentDescription = item.name,
                                            modifier = Modifier.size(44.dp).clip(RoundedCornerShape(6.dp)).border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                                        )
                                    
                                        Box(
                                            modifier = Modifier.size(44.dp).clip(RoundedCornerShape(6.dp)).background(HextechSurfaceVariant),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(name.take(3), color = TextPrimary, fontSize = 10.sp)
                                        }
                                    }
                                }
                            }

                            // Situational
                            if (build.situationalItems.isNotEmpty()) {
                                Text("Opciones Situacionales:", color = TextSecondary, fontSize = 11.5.sp)
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    build.situationalItems.forEach { name ->
                                        val item = WildRiftItemsData.list.find { it.name.equals(name, ignoreCase = true) }
                                        if (item != null) {
                                            AsyncImage(
                                                model = item.iconUrl,
                                                contentDescription = item.name,
                                                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(6.dp)).border(1.dp, HextechCyan, RoundedCornerShape(6.dp))
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 3. Runas y Hechizos
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("🛡️ Runas & Hechizos de Invocador", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Runes row
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                                    // Keystone
                                    val kRune = WildRiftSpellsAndRunes.runes.find { it.name.equals(build.keystoneRune, ignoreCase = true) }
                                    if (kRune != null) {
                                        AsyncImage(model = kRune.iconUrl, contentDescription = kRune.name, modifier = Modifier.size(36.dp).clip(CircleShape))
                                    
                                        Text(build.keystoneRune, color = HextechGold, fontSize = 11.sp)
                                    }

                                    // Secondary Runes
                                    build.secondaryRunes.forEach { sName ->
                                        val sRune = WildRiftSpellsAndRunes.runes.find { it.name.equals(sName, ignoreCase = true) }
                                        if (sRune != null) {
                                            AsyncImage(model = sRune.iconUrl, contentDescription = sRune.name, modifier = Modifier.size(28.dp).clip(CircleShape))
                                        }
                                    }
                                }

                                // Spells row
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    val sp1 = WildRiftSpellsAndRunes.summonerSpells.find { it.name.equals(build.spell1, ignoreCase = true) }
                                    val sp2 = WildRiftSpellsAndRunes.summonerSpells.find { it.name.equals(build.spell2, ignoreCase = true) }

                                    if (sp1 != null) {
                                        AsyncImage(model = sp1.iconUrl, contentDescription = sp1.name, modifier = Modifier.size(32.dp).clip(RoundedCornerShape(6.dp)))
                                    }
                                    if (sp2 != null) {
                                        AsyncImage(model = sp2.iconUrl, contentDescription = sp2.name, modifier = Modifier.size(32.dp).clip(RoundedCornerShape(6.dp)))
                                    }
                                }
                            }
                        }
                    }

                    // 4. Guía Táctica & Combos (Terminología WR: H1, H2, H3, H4)
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("🗺️ Guía Táctica del Creador", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                            if (build.guideNotes.isNotBlank()) {
                                Text(
                                    text = build.guideNotes,
                                    color = TextPrimary,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp
                                )
                            }

                            if (build.comboTips.isNotBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("⚡ Combos Clave (Wild Rift):", color = HextechCyan, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                                Text(
                                    text = build.comboTips,
                                    color = HextechGold,
                                    fontSize = 11.5.sp,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Bottom CTA Button
                if (isSubscribed) {
                    Button(
                        onClick = onUnsubscribe,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                        border = BorderStroke(1.dp, DangerRed),
                        modifier = Modifier.fillMaxWidth().height(46.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Cancelar Suscripción", color = DangerRed, fontWeight = FontWeight.Bold)
                    }
                
                    Button(
                        onClick = onSubscribe,
                        enabled = !isFull,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFull) HextechSurfaceVariant else HextechGold,
                            disabledContainerColor = HextechSurfaceVariant
                        ),
                        modifier = Modifier.fillMaxWidth().height(46.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        if (isFull) {
                            Text("Límite de 100 Suscriptores Alcanzado", color = DangerRed, fontWeight = FontWeight.Bold)
                        } else {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = com.example.R.drawable.ic_blue_essence),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Suscribirme con 100 Esencias Azules", color = HextechDarkBg, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ApplyCreatorDialog(
    onDismiss: () -> Unit,
    onApply: (String) -> Unit
) {
    var reason by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = HextechDarkBg,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("👑 " + tr("Solicitud de Rol de Creador"), color = HextechGold, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Al obtener el Rol de Creador, podrás publicar la build completa y guía táctica para exactamente 1 campeón. La comunidad podrá suscribirse a tu contenido con Esencia Azul (límite: 100 suscriptores).",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )

                OutlinedTextField(
                    value = reason,
                    onValueChange = { reason = it },
                    label = { Text("¿Cuál es tu campeón principal y tu motivación?", fontSize = 11.sp) },
                    placeholder = { Text("Ej: Soy Main Zed Challenger y quiero compartir mi build y combos H1-H4", fontSize = 11.sp) },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 90.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = HextechGold,
                        unfocusedBorderColor = HextechCardBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onApply(reason) },
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
            ) {
                Text("Confirmar y Activar Rol", color = HextechDarkBg, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = TextMuted)
            }
        }
    )
}

@Composable
private fun FeatureBulletPoint(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text, color = TextPrimary, fontSize = 11.sp)
    }
}
