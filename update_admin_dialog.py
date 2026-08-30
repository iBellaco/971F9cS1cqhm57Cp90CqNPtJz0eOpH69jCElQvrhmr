import re

new_admin_code = '''package com.example.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*
import com.example.util.SubscriptionManager
import com.example.util.tr
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class UserRecord(
    val uid: String,
    val email: String,
    val role: String,
    val lastActive: Long,
    val name: String = ""
)

// LoL Themed Palette Constants
private val LolDeepNavy = Color(0xFF040A14)
private val LolClientBg = Color(0xFF091428)
private val LolCardBg = Color(0xFF0A182E)
private val LolBorderGold = Color(0xFFC8AA6E)
private val LolBorderGoldDark = Color(0xFF785A28)
private val LolGoldLight = Color(0xFFF0E6D2)
private val LolHextechCyan = Color(0xFF0AC8B9)
private val LolCyanGlow = Color(0xFF00E5FF)
private val LolNoxusRed = Color(0xFFFF2A42)
private val LolZaunGreen = Color(0xFF00FF7F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardDialog(
    onDismiss: () -> Unit
) {
    val userRole by SubscriptionManager.userRole.collectAsState()
    if (userRole != "admin") {
        LaunchedEffect(Unit) { onDismiss() }
        return
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    var users by remember { mutableStateOf<List<UserRecord>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var showReportsPanel by remember { mutableStateOf(false) }

    // Search and filter states
    var searchQuery by remember { mutableStateOf("") }
    var selectedRoleFilter by remember { mutableStateOf("ALL") }

    fun loadUsers() {
        isLoading = true
        scope.launch {
            try {
                val snapshot = FirebaseFirestore.getInstance().collection("users").get().await()
                val list = snapshot.documents.mapNotNull { doc ->
                    val email = doc.getString("email") ?: "Sin email"
                    val role = doc.getString("role") ?: "free"
                    val lastActive = doc.getLong("last_active") ?: 0L
                    val name = doc.getString("name") ?: ""
                    UserRecord(doc.id, email, role, lastActive, name)
                }.sortedWith(compareByDescending<UserRecord> { it.role == "admin" }
                    .thenByDescending { it.role == "premium" }
                    .thenBy { it.name.ifEmpty { it.email } })
                users = list
            } catch (e: Exception) {
                Log.e("AdminDashboard", "Error loading users", e)
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadUsers()
    }

    if (showReportsPanel) {
        AdminFeedbackBottomSheet(
            onDismiss = { showReportsPanel = false }
        )
    }

    // Filtered user list
    val filteredUsers = remember(users, searchQuery, selectedRoleFilter) {
        users.filter { user ->
            val matchesQuery = if (searchQuery.isBlank()) {
                true
            } else {
                user.name.contains(searchQuery, ignoreCase = true) ||
                user.email.contains(searchQuery, ignoreCase = true) ||
                user.uid.contains(searchQuery, ignoreCase = true)
            }

            val matchesRole = when (selectedRoleFilter) {
                "ALL" -> true
                "ADMIN" -> user.role.equals("admin", ignoreCase = true)
                "PREMIUM" -> user.role.equals("premium", ignoreCase = true)
                "FREE" -> user.role.equals("free", ignoreCase = true)
                "BANNED" -> user.role.equals("banned", ignoreCase = true)
                else -> true
            }

            matchesQuery && matchesRole
        }
    }

    // Animated container background and border colors for smooth transitions
    val animatedContainerBg by animateColorAsState(
        targetValue = LolClientBg,
        animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
        label = "adminContainerBg"
    )
    val animatedBorderColor by animateColorAsState(
        targetValue = LolBorderGold,
        animationSpec = tween(durationMillis = 350),
        label = "adminBorderColor"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 14.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        BorderStroke(
                            1.5.dp,
                            Brush.linearGradient(
                                listOf(
                                    animatedBorderColor,
                                    LolBorderGoldDark,
                                    LolGoldLight.copy(alpha = 0.8f),
                                    animatedBorderColor
                                )
                            )
                        ),
                        RoundedCornerShape(16.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = animatedContainerBg),
                shape = RoundedCornerShape(16.dp)
            ) {
                // Outer LoL Runic Frame Gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    LolDeepNavy,
                                    LolClientBg,
                                    Color(0xFF05101E)
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // --- LEAGUE OF LEGENDS RUNIC HEADER ---
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // Runic Diamond Motif
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(
                                                Brush.radialGradient(
                                                    listOf(LolNoxusRed, Color(0xFF6B0E1B))
                                                )
                                            )
                                            .border(1.dp, LolBorderGold, RoundedCornerShape(4.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Security,
                                            contentDescription = null,
                                            tint = LolGoldLight,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = "CONSOLA DE ADMINISTRACIÓN",
                                            color = LolBorderGold,
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            letterSpacing = 1.2.sp
                                        )
                                        Text(
                                            text = "GESTIÓN CENTRAL DE INVOCADORES & ROLES",
                                            color = LolHextechCyan,
                                            fontSize = 9.sp,
                                            letterSpacing = 1.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(LolCardBg)
                                    .border(1.dp, LolBorderGold.copy(alpha = 0.5f), CircleShape)
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Cerrar",
                                    tint = LolGoldLight,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // LoL Runic Gold Separator with Diamond Emblem
                        RunicGoldDivider()

                        Spacer(modifier = Modifier.height(12.dp))

                        // Action Buttons Row (Reports Box + Refresh)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { showReportsPanel = true },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = LolCardBg),
                                border = BorderStroke(
                                    1.dp,
                                    Brush.horizontalGradient(
                                        listOf(LolHextechCyan, LolBorderGold)
                                    )
                                ),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp)
                            ) {
                                Icon(
                                    Icons.Default.BugReport,
                                    contentDescription = null,
                                    tint = LolBorderGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    "Buzón de Reportes",
                                    color = LolGoldLight,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }

                            IconButton(
                                onClick = { loadUsers() },
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(LolCardBg)
                                    .border(1.dp, LolBorderGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Recargar",
                                    tint = LolHextechCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // --- ADMIN STATS DASHBOARD ---
                        val totalUsers = users.size
                        val premiumUsers = users.count { it.role == "premium" }
                        val onlineUsers = users.count { System.currentTimeMillis() - it.lastActive < 900_000 }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            AdminStatCard(
                                modifier = Modifier.weight(1f),
                                title = "TOTAL",
                                value = totalUsers.toString(),
                                icon = Icons.Default.Group,
                                accentColor = LolHextechCyan,
                                glowColor = LolCyanGlow
                            )
                            AdminStatCard(
                                modifier = Modifier.weight(1f),
                                title = "EN LÍNEA",
                                value = onlineUsers.toString(),
                                icon = Icons.Default.Bolt,
                                accentColor = LolZaunGreen,
                                glowColor = Color(0xFF39FF14)
                            )
                            AdminStatCard(
                                modifier = Modifier.weight(1f),
                                title = "PREMIUM",
                                value = premiumUsers.toString(),
                                icon = Icons.Default.Stars,
                                accentColor = LolBorderGold,
                                glowColor = LolGoldLight
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // --- SEARCH BAR (LoL Universe Style) ---
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            placeholder = {
                                Text(
                                    "Buscar por nombre de usuario, correo o ID...",
                                    color = TextMuted,
                                    fontSize = 12.5.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar",
                                    tint = if (searchQuery.isNotEmpty()) LolBorderGold else LolHextechCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { searchQuery = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Limpiar",
                                            tint = LolGoldLight,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = LolCardBg,
                                unfocusedContainerColor = LolCardBg.copy(alpha = 0.7f),
                                focusedBorderColor = LolBorderGold,
                                unfocusedBorderColor = LolBorderGoldDark.copy(alpha = 0.6f),
                                focusedTextColor = LolGoldLight,
                                unfocusedTextColor = TextPrimary,
                                cursorColor = LolBorderGold
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // --- ROLE FILTER CHIPS ---
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val filterOptions = listOf(
                                "ALL" to "TODOS (${users.size})",
                                "ADMIN" to "ADMINS (${users.count { it.role == "admin" }})",
                                "PREMIUM" to "PREMIUM (${users.count { it.role == "premium" }})",
                                "FREE" to "FREE (${users.count { it.role == "free" }})",
                                "BANNED" to "BANS (${users.count { it.role == "banned" }})"
                            )
                            items(filterOptions) { (key, label) ->
                                val isSelected = selectedRoleFilter == key
                                val animatedChipBg by animateColorAsState(
                                    targetValue = if (isSelected) LolBorderGold.copy(alpha = 0.25f) else LolCardBg,
                                    animationSpec = tween(250),
                                    label = "chipBg"
                                )
                                val animatedChipBorder by animateColorAsState(
                                    targetValue = if (isSelected) LolBorderGold else LolBorderGoldDark.copy(alpha = 0.4f),
                                    animationSpec = tween(250),
                                    label = "chipBorder"
                                )
                                val animatedChipTextColor by animateColorAsState(
                                    targetValue = if (isSelected) LolGoldLight else TextMuted,
                                    animationSpec = tween(250),
                                    label = "chipTextColor"
                                )

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(animatedChipBg)
                                        .border(1.dp, animatedChipBorder, RoundedCornerShape(6.dp))
                                        .clickable { selectedRoleFilter = key }
                                        .padding(horizontal = 10.dp, vertical = 5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = label,
                                        color = animatedChipTextColor,
                                        fontSize = 10.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        letterSpacing = 0.5.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Filter Counter & Title
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "LISTADO DE CUENTAS (${filteredUsers.size})",
                                color = LolHextechCyan,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            if (searchQuery.isNotBlank() || selectedRoleFilter != "ALL") {
                                Text(
                                    text = "Filtro activo",
                                    color = LolBorderGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        if (isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    CircularProgressIndicator(
                                        color = LolBorderGold,
                                        modifier = Modifier.size(36.dp),
                                        strokeWidth = 3.dp
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "Cargando invocadores...",
                                        color = LolGoldLight,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        } else if (filteredUsers.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SearchOff,
                                        contentDescription = null,
                                        tint = LolBorderGold.copy(alpha = 0.6f),
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "No se encontraron usuarios",
                                        color = LolGoldLight,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Intenta con otro nombre, correo o restablece los filtros.",
                                        color = TextMuted,
                                        fontSize = 11.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentPadding = PaddingValues(vertical = 4.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(filteredUsers, key = { it.uid }) { user ->
                                    UserManagementCard(
                                        context = context,
                                        clipboard = clipboard,
                                        user = user,
                                        onRoleChange = { newRole ->
                                            scope.launch {
                                                try {
                                                    FirebaseFirestore.getInstance().collection("users")
                                                        .document(user.uid)
                                                        .update("role", newRole)
                                                        .await()
                                                    loadUsers()
                                                } catch (e: Exception) {
                                                    Log.e("AdminDashboard", "Error updating role", e)
                                                }
                                            }
                                        },
                                        onNameChange = { newName ->
                                            scope.launch {
                                                try {
                                                    FirebaseFirestore.getInstance().collection("users")
                                                        .document(user.uid)
                                                        .update("name", newName)
                                                        .await()
                                                    loadUsers()
                                                } catch (e: Exception) {
                                                    Log.e("AdminDashboard", "Error updating name", e)
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RunicGoldDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Transparent, LolBorderGold.copy(alpha = 0.8f))
                    )
                )
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "◆",
            color = LolBorderGold,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(LolBorderGold.copy(alpha = 0.8f), Color.Transparent)
                    )
                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementCard(
    user: UserRecord,
    context: android.content.Context,
    clipboard: ClipboardManager,
    onRoleChange: (String) -> Unit,
    onNameChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showNameEdit by remember { mutableStateOf(false) }

    val isOnline = System.currentTimeMillis() - user.lastActive < 900_000

    val roleBorderColor by animateColorAsState(
        targetValue = when (user.role.lowercase()) {
            "admin" -> LolNoxusRed
            "premium" -> LolBorderGold
            "banned" -> Color.Gray
            else -> LolHextechCyan.copy(alpha = 0.5f)
        },
        animationSpec = tween(300),
        label = "roleBorderColor"
    )

    if (showNameEdit) {
        var newName by remember { mutableStateOf(user.name) }
        AlertDialog(
            onDismissRequest = { showNameEdit = false },
            title = {
                Text(
                    "Cambiar Nombre de Invocador",
                    color = LolBorderGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp
                )
            },
            text = {
                Column {
                    Text(
                        "Ingresa el nuevo identificador de invocador:",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text("Nombre de Usuario") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LolBorderGold,
                            unfocusedBorderColor = LolBorderGoldDark,
                            focusedTextColor = LolGoldLight,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onNameChange(newName)
                        showNameEdit = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LolBorderGold),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Guardar", color = LolDeepNavy, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showNameEdit = false }) {
                    Text("Cancelar", color = TextMuted)
                }
            },
            containerColor = LolCardBg,
            shape = RoundedCornerShape(12.dp)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, roleBorderColor.copy(alpha = 0.4f)),
                RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = LolCardBg),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Online indicator pulse
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(if (isOnline) LolZaunGreen else Color.Gray.copy(alpha = 0.5f))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    if (user.name.isNotBlank()) {
                        Text(
                            text = user.name,
                            color = LolGoldLight,
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.3.sp
                        )
                    } else {
                        Text(
                            text = user.email.substringBefore("@"),
                            color = LolGoldLight,
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = user.email,
                    color = TextSecondary,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val roleColor = when (user.role.lowercase()) {
                        "admin" -> LolNoxusRed
                        "premium" -> LolBorderGold
                        "banned" -> Color.Gray
                        else -> LolHextechCyan
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(roleColor.copy(alpha = 0.15f))
                            .border(0.5.dp, roleColor.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = user.role.uppercase(),
                            color = roleColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.5.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "ID: ${user.uid.take(8)}...",
                        color = TextMuted,
                        fontSize = 9.5.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            // Dropdown Menu Button with LoL Icon Styling
            Box {
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(LolDeepNavy)
                        .border(1.dp, LolBorderGoldDark.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "Opciones",
                        tint = LolBorderGold,
                        modifier = Modifier.size(16.dp)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(LolClientBg)
                        .border(1.dp, LolBorderGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                ) {
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Fingerprint, contentDescription = null, tint = LolHextechCyan, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Copiar ID Invocador", color = TextPrimary, fontSize = 12.5.sp) },
                        onClick = {
                            clipboard.setText(AnnotatedString(user.uid))
                            Toast.makeText(context, "ID copiado", Toast.LENGTH_SHORT).show()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = LolHextechCyan, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Copiar Correo", color = TextPrimary, fontSize = 12.5.sp) },
                        onClick = {
                            clipboard.setText(AnnotatedString(user.email))
                            Toast.makeText(context, "Correo copiado", Toast.LENGTH_SHORT).show()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Badge, contentDescription = null, tint = LolBorderGold, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Cambiar Nombre de Invocador", color = LolBorderGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                        onClick = {
                            showNameEdit = true
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.LockReset, contentDescription = null, tint = LolHextechCyan, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Restablecer Contraseña", color = LolHextechCyan, fontSize = 12.5.sp) },
                        onClick = {
                            FirebaseAuth.getInstance().sendPasswordResetEmail(user.email)
                            Toast.makeText(context, "Correo de restablecimiento enviado", Toast.LENGTH_SHORT).show()
                            expanded = false
                        }
                    )
                    HorizontalDivider(color = LolBorderGoldDark.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 4.dp))
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = TextMuted, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Asignar Rol: GRATIS", color = TextPrimary, fontSize = 12.5.sp) },
                        onClick = {
                            onRoleChange("free")
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Stars, contentDescription = null, tint = LolBorderGold, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Asignar Rol: PREMIUM", color = LolBorderGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                        onClick = {
                            onRoleChange("premium")
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Block, contentDescription = null, tint = LolNoxusRed, modifier = Modifier.size(16.dp))
                        },
                        text = { Text("Suspender Invocador (BAN)", color = LolNoxusRed, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                        onClick = {
                            onRoleChange("banned")
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AdminStatCard(
    modifier: Modifier,
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    glowColor: Color
) {
    val animatedAccent by animateColorAsState(
        targetValue = accentColor,
        animationSpec = tween(300),
        label = "statAccent"
    )

    Card(
        modifier = modifier
            .border(
                BorderStroke(1.dp, animatedAccent.copy(alpha = 0.4f)),
                RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = LolCardBg),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            animatedAccent.copy(alpha = 0.08f),
                            Color.Transparent
                        )
                    )
                )
                .padding(horizontal = 8.dp, vertical = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = animatedAccent,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = value,
                    color = LolGoldLight,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = title,
                    color = TextMuted,
                    fontSize = 8.5.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
            }
        }
    }
}
'''

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(new_admin_code)

print("AdminDashboardDialog updated with LoL Universe Theme, search bar, and color animations.")
