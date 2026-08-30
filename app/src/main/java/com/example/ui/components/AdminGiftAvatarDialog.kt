package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminGiftAvatarDialog(
    user: UserRecord,
    onDismiss: () -> Unit,
    onAvatarGifted: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf("") }
    var selectedRegionFilter by remember { mutableStateOf("Todos") }
    var selectedAvatar by remember { mutableStateOf<AvatarItem?>(null) }
    var autoEquipForUser by remember { mutableStateOf(true) }
    var isSending by remember { mutableStateOf(false) }

    val regions = listOf("Todos", "Jonia", "Zaun / Piltóver", "Demacia / Noxus", "Freljord / Islas", "Mascotas / Épicos")

    val filteredAvatars = remember(searchQuery, selectedRegionFilter) {
        AvatarCatalog.avatars.filter { avatar ->
            val matchesSearch = searchQuery.isBlank() ||
                    avatar.name.contains(searchQuery, ignoreCase = true) ||
                    avatar.title.contains(searchQuery, ignoreCase = true) ||
                    avatar.region.contains(searchQuery, ignoreCase = true)

            val matchesRegion = when (selectedRegionFilter) {
                "Jonia" -> avatar.region.equals("Jonia", ignoreCase = true)
                "Zaun / Piltóver" -> avatar.region.contains("Zaun", ignoreCase = true) || avatar.region.contains("Piltóver", ignoreCase = true)
                "Demacia / Noxus" -> avatar.region.contains("Demacia", ignoreCase = true) || avatar.region.contains("Noxus", ignoreCase = true)
                "Freljord / Islas" -> avatar.region.contains("Freljord", ignoreCase = true) || avatar.region.contains("Islas", ignoreCase = true) || avatar.region.contains("Oscuros", ignoreCase = true) || avatar.region.contains("Vacío", ignoreCase = true)
                "Mascotas / Épicos" -> avatar.region.contains("Grieta", ignoreCase = true) || avatar.region.contains("Bandle", ignoreCase = true) || avatar.region.contains("Arcade", ignoreCase = true) || avatar.isDefault
                else -> true
            }

            matchesSearch && matchesRegion
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        BorderStroke(
                            1.5.dp,
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFFC8AA6E),
                                    Color(0xFF785A28),
                                    Color(0xFF0AC8B9),
                                    Color(0xFFC8AA6E)
                                )
                            )
                        ),
                        RoundedCornerShape(16.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF091428)),
                shape = RoundedCornerShape(16.dp)
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
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFC8AA6E).copy(alpha = 0.2f))
                                    .border(1.dp, Color(0xFFC8AA6E), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.CardGiftcard,
                                    contentDescription = null,
                                    tint = Color(0xFFC8AA6E),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "OBSEQUIAR AVATAR LOL",
                                    color = Color(0xFFF0E6D2),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = "Destinatario: ${user.name.ifBlank { user.email.substringBefore("@") }}",
                                    color = Color(0xFF0AC8B9),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color(0xFFF0E6D2))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // User Target Info Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFF785A28), RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A182E)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            UserAvatarView(
                                avatarId = user.avatarId,
                                size = 42.dp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = user.email,
                                    color = Color(0xFFF0E6D2),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Rol: ${user.role.uppercase()} • UID: ${user.uid.take(10)}...",
                                    color = Color(0xFFA09B8C),
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Search Field
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Buscar avatar o campeón de LoL...", fontSize = 12.sp, color = Color(0xFFA09B8C)) },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFC8AA6E), modifier = Modifier.size(18.dp))
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFC8AA6E),
                            unfocusedBorderColor = Color(0xFF785A28),
                            focusedTextColor = Color(0xFFF0E6D2),
                            unfocusedTextColor = Color(0xFFF0E6D2),
                            cursorColor = Color(0xFF0AC8B9)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Region Filter Tabs
                    ScrollableTabRow(
                        selectedTabIndex = regions.indexOf(selectedRegionFilter).coerceAtLeast(0),
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF0AC8B9),
                        edgePadding = 0.dp,
                        divider = {}
                    ) {
                        regions.forEach { region ->
                            val isSelected = selectedRegionFilter == region
                            Tab(
                                selected = isSelected,
                                onClick = { selectedRegionFilter = region },
                                text = {
                                    Text(
                                        text = region,
                                        color = if (isSelected) Color(0xFF0AC8B9) else Color(0xFFA09B8C),
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Avatar Grid
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 90.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(filteredAvatars, key = { it.id }) { avatar ->
                            val isSelected = selectedAvatar?.id == avatar.id
                            val isAlreadyUnlocked = user.unlockedAvatars.contains(avatar.id) || avatar.isDefault
                            val isCurrentlyEquipped = user.avatarId == avatar.id

                            val parsedColor = try {
                                Color(android.graphics.Color.parseColor(avatar.borderHex))
                            } catch (e: Exception) {
                                Color(0xFFC8AA6E)
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedAvatar = avatar }
                                    .border(
                                        width = if (isSelected) 2.dp else 1.dp,
                                        color = if (isSelected) Color(0xFF0AC8B9) else if (isAlreadyUnlocked) Color(0xFF10B981).copy(alpha = 0.6f) else Color(0xFF785A28).copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected) Color(0xFF0AC8B9).copy(alpha = 0.15f) else Color(0xFF0A182E)
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(contentAlignment = Alignment.BottomEnd) {
                                        UserAvatarView(
                                            avatarId = avatar.id,
                                            size = 46.dp,
                                            customBorderColor = if (isSelected) Color(0xFF0AC8B9) else parsedColor
                                        )

                                        if (isSelected) {
                                            Box(
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clip(CircleShape)
                                                    .background(Color(0xFF0AC8B9)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF091428), modifier = Modifier.size(11.dp))
                                            }
                                        } else if (isCurrentlyEquipped) {
                                            Box(
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clip(CircleShape)
                                                    .background(Color(0xFFC8AA6E)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFF091428), modifier = Modifier.size(10.dp))
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = avatar.name,
                                        color = if (isSelected) Color(0xFF0AC8B9) else Color(0xFFF0E6D2),
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        text = avatar.region,
                                        color = Color(0xFFA09B8C),
                                        fontSize = 8.5.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center
                                    )

                                    if (isAlreadyUnlocked && !avatar.isDefault) {
                                        Text(
                                            text = "YA POSEE",
                                            color = Color(0xFF10B981),
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Auto equip Checkbox
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF0A182E))
                            .clickable { autoEquipForUser = !autoEquipForUser }
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = autoEquipForUser,
                            onCheckedChange = { autoEquipForUser = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFC8AA6E),
                                checkmarkColor = Color(0xFF040A14)
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Equipar inmediatamente este avatar en el perfil del invocador",
                            color = Color(0xFFF0E6D2),
                            fontSize = 11.5.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Confirmation / Gift Action Button
                    Button(
                        onClick = {
                            val avatar = selectedAvatar ?: return@Button
                            isSending = true
                            scope.launch {
                                try {
                                    val db = FirebaseFirestore.getInstance()
                                    val userRef = db.collection("users").document(user.uid)
                                    val updates = hashMapOf<String, Any>(
                                        "unlockedAvatars" to FieldValue.arrayUnion(avatar.id)
                                    )
                                    if (autoEquipForUser) {
                                        updates["avatarId"] = avatar.id
                                    }
                                    userRef.set(updates, SetOptions.merge())
                                    Toast.makeText(
                                        context,
                                        "🎁 ¡Avatar '${avatar.name}' obsequiado con éxito a ${user.name.ifBlank { user.email }}!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    onAvatarGifted()
                                    onDismiss()
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                                } finally {
                                    isSending = false
                                }
                            }
                        },
                        enabled = selectedAvatar != null && !isSending,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC8AA6E),
                            disabledContainerColor = Color(0xFF785A28).copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        if (isSending) {
                            CircularProgressIndicator(color = Color(0xFF040A14), modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Enviando regalo...", color = Color(0xFF040A14), fontWeight = FontWeight.Bold)
                        } else {
                            Icon(Icons.Default.CardGiftcard, contentDescription = null, tint = Color(0xFF040A14), modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (selectedAvatar != null) "Obsequiar '${selectedAvatar!!.name}'" else "Selecciona un Avatar",
                                color = Color(0xFF040A14),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
