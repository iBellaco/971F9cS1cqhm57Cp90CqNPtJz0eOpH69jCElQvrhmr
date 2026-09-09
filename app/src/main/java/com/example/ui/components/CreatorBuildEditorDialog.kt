package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.AccountProfileManager
import com.example.data.CreatorChampionBuild
import com.example.data.WildRiftItemsData
import com.example.data.WildRiftRepository
import com.example.data.WildRiftSpellsAndRunes
import com.example.model.Champion
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import com.example.ui.theme.*
import com.example.util.tr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorBuildEditorDialog(
    initialBuild: CreatorChampionBuild? = null,
    onDismiss: () -> Unit,
    onBuildSaved: (CreatorChampionBuild) -> Unit
) {
    val context = LocalContext.current
    val activeProfile by AccountProfileManager.activeProfile.collectAsState()

    var selectedChampionObj by remember {
        mutableStateOf(
            if (initialBuild != null) {
                WildRiftRepository.champions.find {
                    it.id.equals(initialBuild.championId, ignoreCase = true) ||
                    it.name.equals(initialBuild.championName, ignoreCase = true)
                }
            } else null
        )
    }

    var selectedRole by remember { mutableStateOf(initialBuild?.role ?: "Mid") }
    var buildTitle by remember { mutableStateOf(initialBuild?.title ?: "Build Magistral de Élite") }
    var startingItemName by remember { mutableStateOf(initialBuild?.startingItem ?: "Espada Larga") }
    
    var bootsItemObj by remember {
        mutableStateOf<WildRiftItem?>(
            if (!initialBuild?.bootsItem.isNullOrBlank()) {
                WildRiftItemsData.list.find { it.name.equals(initialBuild?.bootsItem, ignoreCase = true) }
            } else null
        )
    }
    var bootsEnchantObj by remember {
        mutableStateOf<WildRiftItem?>(
            if (!initialBuild?.bootsEnchant.isNullOrBlank()) {
                WildRiftItemsData.list.find { it.name.equals(initialBuild?.bootsEnchant, ignoreCase = true) }
            } else null
        )
    }

    val selectedCoreItems = remember {
        mutableStateListOf<WildRiftItem>().apply {
            initialBuild?.coreItems?.forEach { name ->
                WildRiftItemsData.list.find { it.name.equals(name, ignoreCase = true) }?.let { add(it) }
            }
        }
    }

    val selectedSituationalItems = remember {
        mutableStateListOf<WildRiftItem>().apply {
            initialBuild?.situationalItems?.forEach { name ->
                WildRiftItemsData.list.find { it.name.equals(name, ignoreCase = true) }?.let { add(it) }
            }
        }
    }

    var selectedKeystoneRune by remember {
        mutableStateOf<RuneItem?>(
            if (!initialBuild?.keystoneRune.isNullOrBlank()) {
                WildRiftSpellsAndRunes.runes.find { it.name.equals(initialBuild?.keystoneRune, ignoreCase = true) }
            } else null
        )
    }

    val selectedSecondaryRunes = remember {
        mutableStateListOf<RuneItem>().apply {
            initialBuild?.secondaryRunes?.forEach { name ->
                WildRiftSpellsAndRunes.runes.find { it.name.equals(name, ignoreCase = true) }?.let { add(it) }
            }
        }
    }

    var selectedSpell1 by remember {
        mutableStateOf<SummonerSpellItem?>(
            if (!initialBuild?.spell1.isNullOrBlank()) {
                WildRiftSpellsAndRunes.summonerSpells.find { it.name.equals(initialBuild?.spell1, ignoreCase = true) }
            } else WildRiftSpellsAndRunes.summonerSpells.find { it.name.contains("Destello", ignoreCase = true) }
        )
    }

    var selectedSpell2 by remember {
        mutableStateOf<SummonerSpellItem?>(
            if (!initialBuild?.spell2.isNullOrBlank()) {
                WildRiftSpellsAndRunes.summonerSpells.find { it.name.equals(initialBuild?.spell2, ignoreCase = true) }
            } else WildRiftSpellsAndRunes.summonerSpells.find { it.name.contains("Prender", ignoreCase = true) }
        )
    }

    var guideNotes by remember { mutableStateOf(initialBuild?.guideNotes ?: "") }
    var comboTips by remember { mutableStateOf(initialBuild?.comboTips ?: "") }

    // Dialog selection states
    var showChampPicker by remember { mutableStateOf(false) }
    var showItemPickerType by remember { mutableStateOf<String?>(null) } // "core", "boots_t2", "boots_t3", "situational"
    var showRunePickerType by remember { mutableStateOf<String?>(null) } // "keystone", "secondary"
    var showSpellSlot by remember { mutableStateOf<Int?>(null) } // 1 or 2

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
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "👑 " + tr("Taller de Creador"),
                                color = HextechGold,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(DangerRed.copy(alpha = 0.2f))
                                    .border(1.dp, DangerRed, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Límite: 1 Campeón",
                                    color = DangerRed,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                        Text(
                            text = tr("Crea la build completa (objetos, runas, hechizos y guía) de tu campeón emblemático"),
                            color = TextSecondary,
                            fontSize = 11.5.sp
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = HextechCardBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // Body Scroll
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // 1. Selector de 1 Campeón Obligatorio
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.2.dp, if (selectedChampionObj != null) HextechGold else DangerRed),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "1. Campeón Emblemático (Único)",
                                    color = HextechGold,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                TextButton(
                                    onClick = { showChampPicker = true },
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = if (selectedChampionObj == null) "Elegir Campeón" else "Cambiar Campeón",
                                        color = HextechCyan,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            if (selectedChampionObj != null) {
                                val champ = selectedChampionObj!!
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(top = 4.dp)
                                ) {
                                    ChampionAvatar(champion = champ, size = 52.dp, showTierBadge = false)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(champ.name, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                        Text(champ.title, color = HextechCyan, fontSize = 11.5.sp)
                                    }
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant.copy(alpha = 0.5f))
                                        .clickable { showChampPicker = true }
                                        .padding(14.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "👉 Toca aquí para seleccionar tu 1 campeón asignado",
                                        color = TextSecondary,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }

                    // 2. Rol en Línea & Título de Build
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Rol / Posición:", color = TextSecondary, fontSize = 11.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            val roles = listOf("Mid", "Baron", "Jungla", "Duo", "Soporte")
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                items(roles) { r ->
                                    val isSel = selectedRole == r
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(if (isSel) HextechCyan.copy(alpha = 0.25f) else HextechSurface)
                                            .border(1.dp, if (isSel) HextechCyan else HextechCardBorder, RoundedCornerShape(6.dp))
                                            .clickable { selectedRole = r }
                                            .padding(horizontal = 8.dp, vertical = 6.dp)
                                    ) {
                                        Text(r, color = if (isSel) HextechCyan else TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }

                    OutlinedTextField(
                        value = buildTitle,
                        onValueChange = { buildTitle = it },
                        label = { Text("Título de tu Build", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechGold,
                            unfocusedBorderColor = HextechCardBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    // 3. Ruta de Objetos Completa
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("📦 Build Completa de Objetos", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                            // Botas y Encantamiento
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Botas T2
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                                        .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                        .clickable { showItemPickerType = "boots_t2" }
                                        .padding(8.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (bootsItemObj != null) {
                                            AsyncImage(
                                                model = bootsItemObj!!.iconUrl,
                                                contentDescription = bootsItemObj!!.name,
                                                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(6.dp))
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(bootsItemObj!!.name, color = TextPrimary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        } else {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Botas T2", color = TextMuted, fontSize = 11.sp)
                                        }
                                    }
                                }

                                // Encantamiento T3
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant.copy(alpha = 0.6f))
                                        .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                        .clickable { showItemPickerType = "boots_t3" }
                                        .padding(8.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (bootsEnchantObj != null) {
                                            AsyncImage(
                                                model = bootsEnchantObj!!.iconUrl,
                                                contentDescription = bootsEnchantObj!!.name,
                                                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(6.dp))
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(bootsEnchantObj!!.name, color = TextPrimary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        } else {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Encantamiento T3", color = TextMuted, fontSize = 11.sp)
                                        }
                                    }
                                }
                            }

                            // 5 Objetos Principales
                            Text("Objetos Principales (Hasta 5):", color = TextSecondary, fontSize = 11.sp)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                for (i in 0 until 5) {
                                    val item = selectedCoreItems.getOrNull(i)
                                    Box(
                                        modifier = Modifier
                                            .size(52.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(HextechSurfaceVariant)
                                            .border(1.dp, if (item != null) HextechGold else HextechCardBorder, RoundedCornerShape(8.dp))
                                            .clickable {
                                                if (item != null) {
                                                    selectedCoreItems.remove(item)
                                                } else {
                                                    showItemPickerType = "core"
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (item != null) {
                                            AsyncImage(
                                                model = item.iconUrl,
                                                contentDescription = item.name,
                                                modifier = Modifier.fillMaxSize().padding(2.dp).clip(RoundedCornerShape(6.dp))
                                            )
                                        } else {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                                        }
                                    }
                                }
                            }

                            // Objetos Situacionales (Hasta 2)
                            Text("Opciones Situacionales (Hasta 2):", color = TextSecondary, fontSize = 11.sp)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                for (i in 0 until 2) {
                                    val item = selectedSituationalItems.getOrNull(i)
                                    Box(
                                        modifier = Modifier
                                            .size(46.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(HextechSurfaceVariant)
                                            .border(1.dp, if (item != null) HextechCyan else HextechCardBorder, RoundedCornerShape(8.dp))
                                            .clickable {
                                                if (item != null) {
                                                    selectedSituationalItems.remove(item)
                                                } else {
                                                    showItemPickerType = "situational"
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (item != null) {
                                            AsyncImage(
                                                model = item.iconUrl,
                                                contentDescription = item.name,
                                                modifier = Modifier.fillMaxSize().padding(2.dp).clip(RoundedCornerShape(6.dp))
                                            )
                                        } else {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(18.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 4. Runas Óptimas
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("🛡️ Runas de Combate", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Keystone
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier
                                            .size(52.dp)
                                            .clip(CircleShape)
                                            .background(HextechSurfaceVariant)
                                            .border(1.5.dp, HextechGold, CircleShape)
                                            .clickable { showRunePickerType = "keystone" },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (selectedKeystoneRune != null) {
                                            AsyncImage(
                                                model = selectedKeystoneRune!!.iconUrl,
                                                contentDescription = selectedKeystoneRune!!.name,
                                                modifier = Modifier.size(38.dp)
                                            )
                                        } else {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold)
                                        }
                                    }
                                    Text("Clave", color = HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }

                                Spacer(modifier = Modifier.width(4.dp))

                                // Secondary Runes (3)
                                for (i in 0 until 3) {
                                    val rune = selectedSecondaryRunes.getOrNull(i)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Box(
                                            modifier = Modifier
                                                .size(44.dp)
                                                .clip(CircleShape)
                                                .background(HextechSurfaceVariant)
                                                .border(1.dp, if (rune != null) HextechCyan else HextechCardBorder, CircleShape)
                                            .clickable {
                                                if (rune != null) {
                                                    selectedSecondaryRunes.remove(rune)
                                                } else {
                                                    showRunePickerType = "secondary"
                                                }
                                            },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (rune != null) {
                                                AsyncImage(
                                                    model = rune.iconUrl,
                                                    contentDescription = rune.name,
                                                    modifier = Modifier.size(30.dp)
                                                )
                                            } else {
                                                Icon(Icons.Default.Add, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                                            }
                                        }
                                        Text("Sec #${i+1}", color = TextMuted, fontSize = 9.5.sp)
                                    }
                                }
                            }
                        }
                    }

                    // 5. Hechizos de Invocador
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("⚡ Hechizos de Invocador", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                // Spell 1
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant)
                                        .border(1.dp, HextechGold, RoundedCornerShape(8.dp))
                                        .clickable { showSpellSlot = 1 },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (selectedSpell1 != null) {
                                        AsyncImage(
                                            model = selectedSpell1!!.iconUrl,
                                            contentDescription = selectedSpell1!!.name,
                                            modifier = Modifier.fillMaxSize().padding(2.dp).clip(RoundedCornerShape(6.dp))
                                        )
                                    } else {
                                        Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold)
                                    }
                                }

                                // Spell 2
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(HextechSurfaceVariant)
                                        .border(1.dp, HextechGold, RoundedCornerShape(8.dp))
                                        .clickable { showSpellSlot = 2 },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (selectedSpell2 != null) {
                                        AsyncImage(
                                            model = selectedSpell2!!.iconUrl,
                                            contentDescription = selectedSpell2!!.name,
                                            modifier = Modifier.fillMaxSize().padding(2.dp).clip(RoundedCornerShape(6.dp))
                                        )
                                    } else {
                                        Icon(Icons.Default.Add, contentDescription = null, tint = HextechGold)
                                    }
                                }
                            }
                        }
                    }

                    // 6. Guía Táctica & Combos (Terminología WR: H1, H2, H3, Definitiva H4)
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("🗺️ Guía Táctica & Micro-Tips", color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = "Consejo de Coach: Utiliza la terminología oficial de Wild Rift (H1, H2, H3 y Definitiva H4) al explicar combos y match-ups.",
                                color = TextMuted,
                                fontSize = 10.5.sp
                            )

                            OutlinedTextField(
                                value = guideNotes,
                                onValueChange = { guideNotes = it },
                                label = { Text("Fase de líneas, juego temprano y macro-juego", fontSize = 11.sp) },
                                modifier = Modifier.fillMaxWidth().heightIn(min = 90.dp),
                                maxLines = 6,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = HextechCyan,
                                    unfocusedBorderColor = HextechCardBorder,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                )
                            )

                            OutlinedTextField(
                                value = comboTips,
                                onValueChange = { comboTips = it },
                                label = { Text("Secuencia de Combos (Ej: H1 -> H2 -> H3 -> Definitiva H4)", fontSize = 11.sp) },
                                modifier = Modifier.fillMaxWidth().heightIn(min = 70.dp),
                                maxLines = 4,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = HextechCyan,
                                    unfocusedBorderColor = HextechCardBorder,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Action Save Button
                Button(
                    onClick = {
                        if (selectedChampionObj == null) {
                            Toast.makeText(context, "Debes seleccionar 1 campeón para tu build.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val build = CreatorChampionBuild(
                            championId = selectedChampionObj!!.id,
                            championName = selectedChampionObj!!.name,
                            role = selectedRole,
                            title = buildTitle.ifBlank { "Build Magistral de ${selectedChampionObj!!.name}" },
                            startingItem = startingItemName,
                            bootsItem = bootsItemObj?.name ?: "Botas Básicas",
                            bootsEnchant = bootsEnchantObj?.name ?: "",
                            coreItems = selectedCoreItems.map { it.name },
                            situationalItems = selectedSituationalItems.map { it.name },
                            keystoneRune = selectedKeystoneRune?.name ?: "Conquistador",
                            secondaryRunes = selectedSecondaryRunes.map { it.name },
                            spell1 = selectedSpell1?.name ?: "Destello",
                            spell2 = selectedSpell2?.name ?: "Prender",
                            guideNotes = guideNotes.ifBlank { "Enfócate en ganar prioridad en la fase temprana con H1 y coordinar la Definitiva (H4) en objetivos neutrales." },
                            comboTips = comboTips.ifBlank { "Combo: H1 -> H2 -> H3 -> Definitiva (H4)." }
                        )

                        val saved = AccountProfileManager.saveCreatorBuild(context, activeProfile.id, build)
                        if (saved) {
                            Toast.makeText(context, "¡Build de ${selectedChampionObj!!.name} guardada exitosamente!", Toast.LENGTH_LONG).show()
                            onBuildSaved(build)
                        } else {
                            Toast.makeText(context, "No tienes el rol de Creador activo.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = HextechDarkBg)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Guardar Build de Creador (1 Campeón)",
                        color = HextechDarkBg,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

    // Modal Champion Picker
    if (showChampPicker) {
        ChampionCatalogSelectionDialog(
            onDismiss = { showChampPicker = false },
            onSelect = { champion ->
                selectedChampionObj = champion
                showChampPicker = false
            }
        )
    }

    // Modal Item Picker
    if (showItemPickerType != null) {
        val pickerType = showItemPickerType!!
        val excluded = when (pickerType) {
            "core" -> selectedCoreItems.map { it.id }.toSet()
            "situational" -> (selectedCoreItems.map { it.id } + selectedSituationalItems.map { it.id }).toSet()
            else -> emptySet()
        }
        ItemCatalogSelectionDialog(
            type = pickerType,
            excludedItemIds = excluded,
            onDismiss = { showItemPickerType = null },
            onSelect = { item ->
                when (pickerType) {
                    "boots_t2" -> {
                        bootsItemObj = item
                        showItemPickerType = null
                    }
                    "boots_t3" -> {
                        bootsEnchantObj = item
                        showItemPickerType = null
                    }
                    "core" -> {
                        if (selectedCoreItems.size < 5) selectedCoreItems.add(item)
                        showItemPickerType = null
                    }
                    "situational" -> {
                        if (selectedSituationalItems.size < 2) selectedSituationalItems.add(item)
                        showItemPickerType = null
                    }
                    else -> showItemPickerType = null
                }
            }
        )
    }

    // Modal Rune Picker
    if (showRunePickerType != null) {
        val rType = showRunePickerType!!
        val excluded = if (rType == "secondary") selectedSecondaryRunes.map { it.name }.toSet() else emptySet()
        RuneCatalogSelectionDialog(
            mode = rType,
            excludedRuneNames = excluded,
            onDismiss = { showRunePickerType = null },
            onSelect = { rune ->
                if (rType == "keystone") {
                    selectedKeystoneRune = rune
                } else {
                    if (selectedSecondaryRunes.size < 3) selectedSecondaryRunes.add(rune)
                }
                showRunePickerType = null
            }
        )
    }

    // Modal Spell Picker
    if (showSpellSlot != null) {
        val slot = showSpellSlot!!
        val excluded = if (slot == 1 && selectedSpell2 != null) setOf(selectedSpell2!!.name)
            else if (slot == 2 && selectedSpell1 != null) setOf(selectedSpell1!!.name)
            else emptySet()
        SpellCatalogSelectionDialog(
            excludedSpellNames = excluded,
            onDismiss = { showSpellSlot = null },
            onSelect = { spell ->
                if (slot == 1) selectedSpell1 = spell else selectedSpell2 = spell
                showSpellSlot = null
            }
        )
    }
}
