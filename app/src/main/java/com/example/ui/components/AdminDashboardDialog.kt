package com.example.ui.components
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.DisposableEffect

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
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
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.math.PI
import kotlin.math.sin
import kotlin.math.cos
import kotlin.random.Random

data class UserRecord(
    val uid: String,
    val email: String,
    val role: String,
    val lastActive: Long,
    val name: String = "",
    val avatarId: String = "default_poro",
    val unlockedAvatars: List<String> = emptyList(),
    val premiumUntil: Long? = null,
    val registeredDevices: List<String> = emptyList()
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

enum class RuneParticleShape {
    DIAMOND,
    CROSS_STAR,
    ORB,
    RUNIC_PULSE
}

data class RunicParticle(
    val relX: Float,
    val relY: Float,
    val driftSpeed: Float,
    val swayFreq: Float,
    val swayAmp: Float,
    val size: Float,
    val shape: RuneParticleShape,
    val baseColor: Color,
    val pulsePhase: Float
)

@Composable
fun RunicHeaderParticleAnimation(
    modifier: Modifier = Modifier,
    particleCount: Int = 18
) {
    val infiniteTransition = rememberInfiniteTransition(label = "runicTransition")
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 14000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "particleProgress"
    )

    // Precompute particles deterministically so no re-allocations occur on recomposition
    val particles = remember {
        val random = Random(42)
        val colors = listOf(
            LolBorderGold,
            LolHextechCyan,
            LolCyanGlow,
            LolGoldLight,
            Color(0xFF818CF8),
            LolZaunGreen
        )
        val shapes = RuneParticleShape.values()
        List(particleCount) {
            RunicParticle(
                relX = random.nextFloat(),
                relY = random.nextFloat(),
                driftSpeed = 0.2f + random.nextFloat() * 0.45f,
                swayFreq = 1.0f + random.nextFloat() * 2.0f,
                swayAmp = 0.02f + random.nextFloat() * 0.04f,
                size = 3.5f + random.nextFloat() * 6.5f,
                shape = shapes[random.nextInt(shapes.size)],
                baseColor = colors[random.nextInt(colors.size)],
                pulsePhase = random.nextFloat() * (2f * PI.toFloat())
            )
        }
    }

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        if (width <= 0 || height <= 0) return@Canvas

        // Draw soft ambient runic backdrop nebula glow
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(
                    LolHextechCyan.copy(alpha = 0.08f),
                    LolBorderGold.copy(alpha = 0.04f),
                    Color.Transparent
                ),
                center = Offset(width * 0.3f, height * 0.5f),
                radius = width * 0.6f
            )
        )
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(
                    LolNoxusRed.copy(alpha = 0.06f),
                    Color.Transparent
                ),
                center = Offset(width * 0.85f, height * 0.5f),
                radius = width * 0.4f
            )
        )

        // Compute current positions for constellation connections
        val computedPoints = particles.map { p ->
            val rawY = (p.relY - animationProgress * p.driftSpeed) % 1f
            val currentY = if (rawY < 0f) rawY + 1f else rawY
            val sway = sin(animationProgress * 2f * PI.toFloat() * p.swayFreq + p.pulsePhase) * p.swayAmp
            val currentX = (p.relX + sway).coerceIn(0.02f, 0.98f)
            val px = currentX * width
            val py = currentY * height
            val alpha = (sin(animationProgress * 2f * PI.toFloat() * 1.5f + p.pulsePhase) * 0.35f + 0.55f).coerceIn(0.12f, 0.92f)
            Triple(Offset(px, py), alpha, p)
        }

        // Draw subtle connective runic lines between neighboring particles
        for (i in computedPoints.indices) {
            val (pos1, alpha1, p1) = computedPoints[i]
            for (j in i + 1 until computedPoints.size) {
                val (pos2, alpha2, _) = computedPoints[j]
                val dx = pos1.x - pos2.x
                val dy = pos1.y - pos2.y
                val dist = dx * dx + dy * dy
                val maxDist = (width * 0.22f) * (width * 0.22f)
                if (dist < maxDist) {
                    val lineAlpha = (1f - dist / maxDist) * 0.14f * ((alpha1 + alpha2) * 0.5f)
                    drawLine(
                        brush = Brush.linearGradient(
                            listOf(
                                p1.baseColor.copy(alpha = lineAlpha),
                                LolBorderGold.copy(alpha = lineAlpha * 0.6f)
                            )
                        ),
                        start = pos1,
                        end = pos2,
                        strokeWidth = 1f
                    )
                }
            }
        }

        // Draw individual glowing runic particles
        for ((pos, alpha, p) in computedPoints) {
            val px = pos.x
            val py = pos.y
            val baseRadius = p.size

            // 1. Soft glowing outer halo
            drawCircle(
                color = p.baseColor.copy(alpha = alpha * 0.22f),
                radius = baseRadius * 2.8f,
                center = pos
            )

            // 2. Crisp Runic Shape
            when (p.shape) {
                RuneParticleShape.DIAMOND -> {
                    val path = Path().apply {
                        moveTo(px, py - baseRadius)
                        lineTo(px + baseRadius * 0.8f, py)
                        lineTo(px, py + baseRadius)
                        lineTo(px - baseRadius * 0.8f, py)
                        close()
                    }
                    drawPath(path, color = p.baseColor.copy(alpha = alpha))
                    drawPath(
                        path,
                        color = LolGoldLight.copy(alpha = alpha * 0.85f),
                        style = Stroke(width = 1f)
                    )
                }
                RuneParticleShape.CROSS_STAR -> {
                    val armLength = baseRadius * 1.4f
                    val starColor = p.baseColor.copy(alpha = alpha)
                    drawLine(
                        color = starColor,
                        start = Offset(px - armLength, py),
                        end = Offset(px + armLength, py),
                        strokeWidth = 1.2f
                    )
                    drawLine(
                        color = starColor,
                        start = Offset(px, py - armLength),
                        end = Offset(px, py + armLength),
                        strokeWidth = 1.2f
                    )
                    drawCircle(
                        color = LolGoldLight.copy(alpha = alpha),
                        radius = baseRadius * 0.35f,
                        center = pos
                    )
                }
                RuneParticleShape.ORB -> {
                    drawCircle(
                        color = p.baseColor.copy(alpha = alpha * 0.8f),
                        radius = baseRadius * 0.7f,
                        center = pos
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = alpha * 0.95f),
                        radius = baseRadius * 0.3f,
                        center = pos
                    )
                }
                RuneParticleShape.RUNIC_PULSE -> {
                    drawCircle(
                        color = p.baseColor.copy(alpha = alpha * 0.7f),
                        radius = baseRadius * 0.9f,
                        center = pos,
                        style = Stroke(width = 1.2f)
                    )
                    drawCircle(
                        color = LolBorderGold.copy(alpha = alpha * 0.9f),
                        radius = baseRadius * 0.35f,
                        center = pos
                    )
                }
            }
        }
    }
}

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

    DisposableEffect(Unit) {
        isLoading = true
        val listener = FirebaseFirestore.getInstance().collection("users")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("AdminDashboard", "Error loading users", error)
                    isLoading = false
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val list = snapshot.documents.mapNotNull { doc ->
                        try {
                            val email = doc.getString("email") ?: "Sin email"
                            val role = doc.getString("role") ?: "free"
                            val lastActive = doc.getLong("last_active") ?: 0L
                            val name = doc.getString("name") ?: ""
                            val avatarId = doc.getString("avatarId") ?: "default_poro"
                            val premiumUntil = doc.getLong("premiumUntil")
                            @Suppress("UNCHECKED_CAST")
                            val unlocked = doc.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")
                            @Suppress("UNCHECKED_CAST")
                            val regDevices = doc.get("registeredDevices") as? List<String> ?: emptyList()
                            UserRecord(doc.id, email, role, lastActive, name, avatarId, unlocked, premiumUntil, regDevices)
                        } catch (e: Exception) {
                            Log.e("AdminDashboard", "Error parsing user doc ${doc.id}", e)
                            null
                        }
                    }.sortedWith(compareByDescending<UserRecord> { it.role == "admin" }
                        .thenByDescending { it.role == "premium" }
                        .thenBy { it.name.ifEmpty { it.email } })
                    users = list
                    isLoading = false
                }
            }
        
        onDispose {
            listener.remove()
        }
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
                        // --- LEAGUE OF LEGENDS RUNIC HEADER WITH GLOWING PARTICLE BACKGROUND ---
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color(0xFF0B1B33).copy(alpha = 0.85f),
                                            LolCardBg.copy(alpha = 0.65f)
                                        )
                                    )
                                )
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        Brush.horizontalGradient(
                                            listOf(
                                                LolBorderGold.copy(alpha = 0.7f),
                                                LolHextechCyan.copy(alpha = 0.4f),
                                                LolBorderGold.copy(alpha = 0.7f)
                                            )
                                        )
                                    ),
                                    RoundedCornerShape(12.dp)
                                )
                        ) {
                            // Atmospheric glowing runic particle animation behind header content
                            RunicHeaderParticleAnimation(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clip(RoundedCornerShape(12.dp)),
                                particleCount = 20
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // Runic Diamond Motif with pulsing glow
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(
                                                    Brush.radialGradient(
                                                        listOf(LolNoxusRed, Color(0xFF6B0E1B))
                                                    )
                                                )
                                                .border(1.2.dp, LolBorderGold, RoundedCornerShape(6.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Security,
                                                contentDescription = null,
                                                tint = LolGoldLight,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = "CONSOLA DE ADMINISTRACIÓN",
                                                color = LolBorderGold,
                                                fontSize = 15.5.sp,
                                                fontWeight = FontWeight.ExtraBold,
                                                letterSpacing = 1.2.sp
                                            )
                                            Text(
                                                text = "GESTIÓN CENTRAL DE INVOCADORES & ROLES",
                                                color = LolHextechCyan,
                                                fontSize = 8.5.sp,
                                                letterSpacing = 1.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }

                                    IconButton(
                                        onClick = onDismiss,
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(CircleShape)
                                            .background(LolCardBg.copy(alpha = 0.9f))
                                            .border(1.dp, LolBorderGold.copy(alpha = 0.6f), CircleShape)
                                    ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Cerrar",
                                            tint = LolGoldLight,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // LoL Runic Gold Separator with Diamond Emblem
                                RunicGoldDivider()
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Action Buttons Row (Reports Box + Terminal Scraper + Refresh)
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
                                    "Buzón Reportes",
                                    color = LolGoldLight,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.3.sp
                                )
                            }

                            IconButton(
                                onClick = { /* Auto-updating */ },
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
                                                } catch (e: Exception) {
                                                    Log.e("AdminDashboard", "Error updating name", e)
                                                }
                                            }
                                        },
                                        onRefresh = { }
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
    onNameChange: (String) -> Unit,
    onRefresh: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var showNameEdit by remember { mutableStateOf(false) }
    var showGiftAvatarDialog by remember { mutableStateOf(false) }
    var showSubscriptionTimeDialog by remember { mutableStateOf(false) }
    var currentTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) { while(true) { kotlinx.coroutines.delay(1000L); currentTime = System.currentTimeMillis() } }

    val isOnline = System.currentTimeMillis() - user.lastActive < 900_000
    val isExpired = user.role.equals("premium", ignoreCase = true) && user.premiumUntil != null && user.premiumUntil > 0L && user.premiumUntil <= System.currentTimeMillis()

    val roleColor = when (user.role.lowercase()) {
        "admin" -> LolNoxusRed
        "premium" -> if (isExpired) LolNoxusRed else LolBorderGold
        "banned" -> Color.Gray
        else -> LolHextechCyan
    }

    if (showSubscriptionTimeDialog) {
        AdminManageSubscriptionDialog(
            user = user,
            onDismiss = { showSubscriptionTimeDialog = false },
            onSubscriptionUpdated = { onRefresh() }
        )
    }

    // Dynamic golden runic border animation: continuous shimmer & breathing effect
    val infiniteTransition = rememberInfiniteTransition(label = "runicBorderShimmer")
    val shimmerPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerPhase"
    )

    val cornerGlowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.45f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cornerGlow"
    )

    if (showGiftAvatarDialog) {
        AdminGiftAvatarDialog(
            user = user,
            onDismiss = { showGiftAvatarDialog = false },
            onAvatarGifted = {
                onRefresh()
            }
        )
    }

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

    // Outer Runic Card Container with Dynamic Golden Runic Border
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(LolCardBg)
    ) {
        // Inner card body with subtle ambient role gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            roleColor.copy(alpha = 0.08f),
                            LolCardBg,
                            Color(0xFF091428).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Summoner Crest Avatar + User Details
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // LoL Summoner Profile Crest with User Avatar
                    Box(contentAlignment = Alignment.BottomEnd) {
                        UserAvatarView(
                            avatarId = user.avatarId,
                            size = 42.dp,
                            fallbackInitial = if (user.name.isNotBlank()) user.name else user.email
                        )

                        // Online Status Bead
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(if (isOnline) LolZaunGreen else Color.DarkGray)
                                .border(1.dp, Color(0xFF05101E), CircleShape)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (user.name.isNotBlank()) user.name else user.email.substringBefore("@"),
                                color = LolGoldLight,
                                fontSize = 14.5.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.3.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.height(1.dp))

                        Text(
                            text = user.email,
                            color = TextSecondary,
                            fontSize = 11.5.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // Role Pill with Runic Golden Border
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(roleColor.copy(alpha = 0.15f))
                                    .border(0.8.dp, roleColor.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = user.role.uppercase(),
                                    color = roleColor,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.6.sp
                                )
                            }

                            // Subscription Duration Pill (Clickable to manage)
                            if (user.role.equals("premium", ignoreCase = true)) {
                                val dummyTime = currentTime
                                val durationText = SubscriptionManager.formatDuration(user.premiumUntil)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (isExpired) LolNoxusRed.copy(alpha = 0.18f) else LolBorderGold.copy(alpha = 0.15f))
                                        .border(0.8.dp, if (isExpired) LolNoxusRed else LolBorderGold.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                                        .clickable { showSubscriptionTimeDialog = true }
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = if (isExpired) Icons.Default.Warning else Icons.Default.HourglassBottom,
                                            contentDescription = null,
                                            tint = if (isExpired) LolNoxusRed else LolBorderGold,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = if (isExpired) "EXPIRADO" else durationText,
                                            color = if (isExpired) LolNoxusRed else LolGoldLight,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Text(
                                text = "UID: ${user.uid.take(8)}...",
                                color = TextMuted,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }

                // Quick Subscription Timer Button + Dropdown Menu
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    IconButton(
                        onClick = { showSubscriptionTimeDialog = true },
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(LolBorderGold.copy(alpha = 0.15f))
                            .border(1.dp, LolBorderGold.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                    ) {
                        Icon(
                            Icons.Default.HourglassTop,
                            contentDescription = "Gestionar Tiempo",
                            tint = LolBorderGold,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Box {
                        IconButton(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(LolDeepNavy)
                                .border(1.dp, LolBorderGoldDark.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
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
                                    Icon(Icons.Default.HourglassTop, contentDescription = null, tint = LolBorderGold, modifier = Modifier.size(16.dp))
                                },
                                text = { Text("⏱️ Gestionar Tiempo Suscripción", color = LolBorderGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                                onClick = {
                                    showSubscriptionTimeDialog = true
                                    expanded = false
                                }
                            )
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
                                    Icon(Icons.Default.CardGiftcard, contentDescription = null, tint = LolBorderGold, modifier = Modifier.size(16.dp))
                                },
                                text = { Text("🎁 Obsequiar Avatar LoL", color = LolBorderGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                                onClick = {
                                    showGiftAvatarDialog = true
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(Icons.Default.Delete, contentDescription = null, tint = LolHextechCyan, modifier = Modifier.size(16.dp))
                                },
                                text = { Text("Resetear Dispositivos (Desvincular Hardware)", color = LolHextechCyan, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
                                onClick = {
                                    FirebaseFirestore.getInstance().collection("users")
                                        .document(user.uid)
                                        .update("registeredDevices", emptyList<String>())
                                        .addOnSuccessListener {
                                            Toast.makeText(context, "Dispositivos liberados con éxito", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(context, "Error al liberar dispositivos", Toast.LENGTH_SHORT).show()
                                        }
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
                                text = { Text("Asignar Rol: PREMIUM (Vitalicio)", color = LolBorderGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold) },
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
                
                // --- HARDWARE & DEVICES SECTION ---
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = LolBorderGoldDark.copy(alpha = 0.2f), modifier = Modifier.padding(horizontal = 4.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            androidx.compose.material.icons.Icons.Default.Devices,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Dispositivos (${user.registeredDevices.size}/2)",
                            color = TextSecondary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (user.registeredDevices.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            user.registeredDevices.forEachIndexed { index, deviceId ->
                                Text(
                                    text = "Slot ${index + 1}: ${deviceId.take(12)}...",
                                    color = LolHextechCyan,
                                    fontSize = 9.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "0 slots ocupados",
                            color = TextMuted,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
                
                // UID footer
                Text(
                    text = "UID: ${user.uid}",
                    color = TextMuted.copy(alpha = 0.35f),
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                )
            }
        }

        // --- DYNAMIC RUNIC GOLDEN BORDER & CORNER ENGRAVINGS OVERLAY ---
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(12.dp))
        ) {
            val w = size.width
            val h = size.height
            val strokeW = 1.6.dp.toPx()
            val cornerLen = 14.dp.toPx()

            // 1. Shifting dynamic golden gradient border stroke
            val startX = (w + h) * shimmerPhase - h
            val shimmerBrush = Brush.linearGradient(
                colors = listOf(
                    LolBorderGoldDark.copy(alpha = 0.7f),
                    LolBorderGold,
                    LolGoldLight.copy(alpha = 0.95f),
                    LolBorderGold,
                    LolBorderGoldDark.copy(alpha = 0.7f)
                ),
                start = Offset(startX, 0f),
                end = Offset(startX + w * 0.8f, h)
            )

            drawRoundRect(
                brush = shimmerBrush,
                size = size,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(12.dp.toPx()),
                style = Stroke(width = strokeW)
            )

            // 2. League of Legends Runic Corner Accents (Top-Left, Top-Right, Bottom-Left, Bottom-Right)
            val cornerGold = LolGoldLight.copy(alpha = cornerGlowAlpha)
            val cornerStroke = 2.dp.toPx()

            // Top-Left Corner Bracket
            drawLine(
                color = cornerGold,
                start = Offset(0f, cornerLen),
                end = Offset(0f, 0f),
                strokeWidth = cornerStroke
            )
            drawLine(
                color = cornerGold,
                start = Offset(0f, 0f),
                end = Offset(cornerLen, 0f),
                strokeWidth = cornerStroke
            )

            // Top-Right Corner Bracket
            drawLine(
                color = cornerGold,
                start = Offset(w - cornerLen, 0f),
                end = Offset(w, 0f),
                strokeWidth = cornerStroke
            )
            drawLine(
                color = cornerGold,
                start = Offset(w, 0f),
                end = Offset(w, cornerLen),
                strokeWidth = cornerStroke
            )

            // Bottom-Left Corner Bracket
            drawLine(
                color = cornerGold,
                start = Offset(0f, h - cornerLen),
                end = Offset(0f, h),
                strokeWidth = cornerStroke
            )
            drawLine(
                color = cornerGold,
                start = Offset(0f, h),
                end = Offset(cornerLen, h),
                strokeWidth = cornerStroke
            )

            // Bottom-Right Corner Bracket
            drawLine(
                color = cornerGold,
                start = Offset(w - cornerLen, h),
                end = Offset(w, h),
                strokeWidth = cornerStroke
            )
            drawLine(
                color = cornerGold,
                start = Offset(w, h),
                end = Offset(w, h - cornerLen),
                strokeWidth = cornerStroke
            )

            // 3. Center Runic Diamond Motif on Top Edge
            val diamondSize = 3.dp.toPx()
            val midX = w / 2f
            val path = Path().apply {
                moveTo(midX, 0f)
                lineTo(midX + diamondSize, diamondSize)
                lineTo(midX, diamondSize * 2)
                lineTo(midX - diamondSize, diamondSize)
                close()
            }
            drawPath(
                path = path,
                color = LolBorderGold
            )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminManageSubscriptionDialog(
    user: UserRecord,
    onDismiss: () -> Unit,
    onSubscriptionUpdated: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isSubmitting by remember { mutableStateOf(false) }
    var customAmount by remember { mutableStateOf("") }
    var customUnit by remember { mutableStateOf("Días") }

    val isExpired = user.role.equals("premium", ignoreCase = true) && user.premiumUntil != null && user.premiumUntil > 0L && user.premiumUntil <= System.currentTimeMillis()
    val currentDurationFormatted = when {
        user.role.equals("admin", ignoreCase = true) -> "👑 Administrador (Vitalicio)"
        !user.role.equals("premium", ignoreCase = true) -> "Sin Suscripción Activa (Gratis)"
        isExpired -> "⚠️ Suscripción Expirada"
        else -> "⏳ ${SubscriptionManager.formatDuration(user.premiumUntil)}"
    }

    fun applySubscription(durationMillis: Long?, label: String) {
        isSubmitting = true
        scope.launch {
            try {
                val db = FirebaseFirestore.getInstance()
                val updateMap = hashMapOf<String, Any>(
                    "role" to if (durationMillis == -1L) "free" else "premium"
                )
                if (durationMillis == -1L) {
                    updateMap["premiumUntil"] = 0L
                } else if (durationMillis != null && durationMillis > 0) {
                    val baseTime = if (user.premiumUntil != null && user.premiumUntil > System.currentTimeMillis()) {
                        user.premiumUntil
                    } else {
                        System.currentTimeMillis()
                    }
                    updateMap["premiumUntil"] = baseTime + durationMillis
                } else {
                    updateMap["premiumUntil"] = 0L // Vitalicio / Permanente
                }

                db.collection("users").document(user.uid)
                    .set(updateMap, SetOptions.merge())
                    .await()

                if (durationMillis != -1L) {
                    val durationToLog = if (durationMillis != null && durationMillis > 0) durationMillis else 0L
                    com.example.util.SubscriptionHistoryManager.addRecordForUser(
                        uid = user.uid,
                        durationMillis = durationToLog,
                        planName = "Asignación Manual: $label",
                        status = "Completado (Admin)",
                        amount = "$0.00"
                    )
                }

                Toast.makeText(context, "Suscripción actualizada ($label) para ${user.name.ifEmpty { user.email }}", Toast.LENGTH_SHORT).show()
                onSubscriptionUpdated()
                onDismiss()
            } catch (e: Exception) {
                Log.e("AdminDashboard", "Error updating subscription duration", e)
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                isSubmitting = false
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.HourglassTop,
                    contentDescription = null,
                    tint = LolBorderGold,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Gestionar Suscripción",
                    color = LolBorderGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // User info summary banner
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = LolDeepNavy,
                    border = BorderStroke(1.dp, LolBorderGoldDark.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        UserAvatarView(
                            avatarId = user.avatarId,
                            size = 38.dp,
                            fallbackInitial = user.name.ifEmpty { user.email }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = user.name.ifEmpty { user.email.substringBefore("@") },
                                color = LolGoldLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(
                                text = currentDurationFormatted,
                                color = if (isExpired) LolNoxusRed else LolHextechCyan,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "ACCIONES RÁPIDAS (1-TOQUE)",
                    color = LolHextechCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                val presets = listOf(
                    Triple("⚡ +1 Hora", 1 * 3600 * 1000L, LolHextechCyan),
                    Triple("⚡ +6 Horas", 6 * 3600 * 1000L, LolHextechCyan),
                    Triple("📅 +24 Horas (1 Día)", 24 * 3600 * 1000L, LolHextechCyan),
                    Triple("🗓️ +7 Días (1 Sem)", 7L * 24 * 3600 * 1000L, LolBorderGold),
                    Triple("📆 +30 Días (1 Mes)", 30L * 24 * 3600 * 1000L, LolBorderGold),
                    Triple("🌟 +1 Año (365 Días)", 365L * 24 * 3600 * 1000L, LolGoldLight),
                    Triple("👑 Vitalicio / Permanente", 0L, LolBorderGold),
                    Triple("❌ Revocar (Gratis)", -1L, LolNoxusRed)
                )

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (chunk in presets.chunked(2)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            for ((label, duration, color) in chunk) {
                                Button(
                                    onClick = { applySubscription(duration, label) },
                                    enabled = !isSubmitting,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(38.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = color.copy(alpha = 0.15f)),
                                    border = BorderStroke(1.dp, color.copy(alpha = 0.8f)),
                                    shape = RoundedCornerShape(6.dp),
                                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                                ) {
                                    Text(
                                        text = label,
                                        color = color,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "DURACIÓN PERSONALIZADA",
                    color = LolHextechCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = customAmount,
                        onValueChange = { customAmount = it.filter { char -> char.isDigit() } },
                        placeholder = { Text("Ej: 15", fontSize = 12.sp, color = TextMuted) },
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LolBorderGold,
                            unfocusedBorderColor = LolBorderGoldDark,
                            focusedTextColor = LolGoldLight,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    // Unit selector
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(LolDeepNavy)
                            .border(1.dp, LolBorderGoldDark.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                    ) {
                        listOf("Horas", "Días").forEach { unit ->
                            val isSel = customUnit == unit
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isSel) LolBorderGold else Color.Transparent)
                                    .clickable { customUnit = unit }
                                    .padding(horizontal = 10.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = unit,
                                    color = if (isSel) LolDeepNavy else TextSecondary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            val count = customAmount.toLongOrNull() ?: 0L
                            if (count > 0) {
                                val millis = if (customUnit == "Horas") {
                                    count * 3600 * 1000L
                                } else {
                                    count * 24 * 3600 * 1000L
                                }
                                applySubscription(millis, "+$count $customUnit")
                            }
                        },
                        enabled = !isSubmitting && (customAmount.toLongOrNull() ?: 0L) > 0,
                        colors = ButtonDefaults.buttonColors(containerColor = LolBorderGold),
                        modifier = Modifier.height(44.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Text("Aplicar", color = LolDeepNavy, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar", color = TextMuted)
            }
        },
        containerColor = LolCardBg,
        shape = RoundedCornerShape(14.dp)
    )
}
