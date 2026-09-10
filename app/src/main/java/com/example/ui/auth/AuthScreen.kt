package com.example.ui.auth

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MarkEmailUnread
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.util.SubscriptionManager
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Edit
import com.example.data.AvatarCatalog
import com.example.ui.components.UserAvatarView
import com.example.ui.components.AvatarSelectionBottomSheet
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechSurface
import com.example.util.AuthManager

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthFlowContainer(
    viewModel: AuthViewModel = viewModel(),
    onLoginSuccess: (() -> Unit)? = null
) {
    val auth = AuthManager.getAuth()
    val context = LocalContext.current
    var currentUser by remember { mutableStateOf(auth?.currentUser) }
    
    LaunchedEffect(Unit) {
        currentUser = auth?.currentUser
    }

    LaunchedEffect(currentUser) {
        SubscriptionManager.init(context)
    }
    
    // Check if user is already authenticated
    if (currentUser != null) {
        AuthenticatedProfilePanel(
            user = currentUser!!,
            onSignOut = {
                auth?.signOut()
                currentUser = null
                viewModel.resetSuccessState()
            }
        )
        return
    }

    val uiState by viewModel.uiState.collectAsState()

    // Triggered when login/register succeeds to force a recomposition with the new user state and redirect
    val onAuthSuccess: () -> Unit = {
        com.example.util.DeviceAndSessionManager.registerDeviceAndSession(
            context = context,
            onSuccess = {
                currentUser = auth?.currentUser
                com.example.util.SubscriptionManager.init(context)
                viewModel.resetSuccessState()
                onLoginSuccess?.invoke()
            },
            onError = { errorMessage ->
                if (errorMessage.contains("Límite de dispositivos", ignoreCase = true)) {
                    auth?.signOut()
                    currentUser = null
                    android.widget.Toast.makeText(context, errorMessage, android.widget.Toast.LENGTH_LONG).show()
                } else {
                    currentUser = auth?.currentUser
                    com.example.util.SubscriptionManager.init(context)
                    onLoginSuccess?.invoke()
                }
                viewModel.resetSuccessState()
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface),
            border = BorderStroke(1.dp, HextechCardBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AnimatedContent(
                    targetState = uiState.authScreen,
                    transitionSpec = {
                        (slideInHorizontally { width -> if (targetState > initialState) width else -width } + fadeIn()).togetherWith(
                            slideOutHorizontally { width -> if (targetState > initialState) -width else width } + fadeOut()
                        )
                    },
                    label = "auth_screen_transition"
                ) { targetScreen ->
                    when (targetScreen) {
                        AuthScreenType.LOGIN -> LoginScreen(
                            viewModel = viewModel,
                            onNavigateToRegister = { viewModel.navigateTo(AuthScreenType.REGISTER) },
                            onNavigateToForgot = { viewModel.navigateTo(AuthScreenType.FORGOT_PASSWORD) },
                            onLoginSuccess = onAuthSuccess
                        )
                        AuthScreenType.REGISTER -> RegisterScreen(
                            viewModel = viewModel,
                            onNavigateToLogin = { viewModel.navigateTo(AuthScreenType.LOGIN) },
                            onRegisterSuccess = onAuthSuccess
                        )
                        AuthScreenType.FORGOT_PASSWORD -> ForgotPasswordScreen(
                            viewModel = viewModel,
                            onNavigateToLogin = { viewModel.navigateTo(AuthScreenType.LOGIN) }
                        )
                        else -> { }
                    }
                }
            }
        }
    }
}

@Composable
fun AuthenticatedProfilePanel(user: com.google.firebase.auth.FirebaseUser, onSignOut: () -> Unit) {
    val context = LocalContext.current
    val isPremium by SubscriptionManager.isPremium.collectAsState()
    val userRole by SubscriptionManager.userRole.collectAsState()
    val premiumUntil by SubscriptionManager.premiumUntil.collectAsState()
    val savedUserName by SubscriptionManager.userName.collectAsState()
    val currentAvatarId by SubscriptionManager.currentAvatarId.collectAsState()
    val currentRankBorder by SubscriptionManager.currentRankBorder.collectAsState()
    var showAvatarDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    var showPlansDialog by remember { mutableStateOf(false) }
    var showHistoryDialog by remember { mutableStateOf(false) }
    var showAdminDashboard by remember { mutableStateOf(false) }
    var showCommunityCreatorsDialog by remember { mutableStateOf(false) }
    var showBlueEssenceStoreDialog by remember { mutableStateOf(false) }
    val activeProfile by com.example.data.AccountProfileManager.activeProfile.collectAsState()

    val isExpiringSoon = remember(premiumUntil, isPremium, userRole) {
        SubscriptionManager.isExpiringSoon()
    }
    var remainingFormatted by remember { mutableStateOf(SubscriptionManager.getRemainingPremiumTimeFormatted()) }

    LaunchedEffect(premiumUntil, isPremium, userRole) {
        while (true) {
            remainingFormatted = SubscriptionManager.getRemainingPremiumTimeFormatted()
            kotlinx.coroutines.delay(1000)
        }
    }

    if (showAvatarDialog) {
        com.example.ui.components.AvatarSelectionBottomSheet(
            onDismiss = { showAvatarDialog = false },
            onOpenPremiumPlans = {
                showAvatarDialog = false
                showPlansDialog = true
            }
        )
    }

    if (showThemeDialog) {
        com.example.ui.components.ThemeCustomizationBottomSheet(
            isPremium = isPremium,
            onOpenPremiumPlans = {
                showThemeDialog = false
                showPlansDialog = true
            },
            onDismiss = { showThemeDialog = false }
        )
    }

    if (showPlansDialog) {
        com.example.ui.components.SubscriptionPlansBottomSheet(
            onDismiss = { showPlansDialog = false }
        )
    }

    if (showAdminDashboard) {
        com.example.ui.components.AdminDashboardDialog(
            onDismiss = { showAdminDashboard = false }
        )
    }

    if (showCommunityCreatorsDialog) {
        com.example.ui.components.CommunityCreatorsDialog(
            onDismiss = { showCommunityCreatorsDialog = false },
            onOpenBlueEssenceStore = {
                showCommunityCreatorsDialog = false
                val isAdminUser = userRole == "admin" || AuthManager.isCurrentUserAdmin()
                if (isAdminUser) {
                    showBlueEssenceStoreDialog = true
                } else {
                    Toast.makeText(context, "Servicio temporalmente fuera de servicio", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    if (showBlueEssenceStoreDialog) {
        com.example.ui.components.BlueEssenceStoreDialog(
            profileId = activeProfile.id,
            onDismiss = { showBlueEssenceStoreDialog = false }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthHeader(
                title = "Perfil de Invocador",
                subtitle = "Sesión iniciada correctamente"
            )
            
            // Summoner Crest Avatar
            val finalUserName = savedUserName.takeIf { it.isNotBlank() }
                ?: user.displayName?.takeIf { it.isNotBlank() }
                ?: user.email?.substringBefore("@")
                ?: "Invocador"

            val equippedAvatar = AvatarCatalog.getAvatarById(currentAvatarId)

            Box(
                modifier = Modifier
                    .clickable { showAvatarDialog = true },
                contentAlignment = Alignment.BottomEnd
            ) {
                UserAvatarView(
                    avatarId = currentAvatarId,
                                    rankBorder = currentRankBorder,
                    size = 72.dp,
                    fallbackInitial = finalUserName
                )
                // Edit badge
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(androidx.compose.foundation.shape.CircleShape)
                        .background(com.example.ui.theme.HextechGold)
                        .border(1.5.dp, com.example.ui.theme.HextechDarkBg, androidx.compose.foundation.shape.CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Cambiar Avatar",
                        tint = com.example.ui.theme.HextechDarkBg,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = finalUserName,
                color = com.example.ui.theme.HextechGold,
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                letterSpacing = 0.3.sp
            )

            // Avatar Title & Region subtitle
            Text(
                text = "${equippedAvatar.title} • ${equippedAvatar.region}",
                color = com.example.ui.theme.HextechCyan,
                fontSize = 12.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
            
            var isEmailVisible by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp).clickable { isEmailVisible = !isEmailVisible }
            ) {
                Text(
                    text = if (isEmailVisible) (user.email ?: "") else "••••••••@••••.com",
                    color = com.example.ui.theme.TextSecondary,
                    fontSize = 13.5.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = com.example.ui.theme.TextMuted,
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            val currentBlueEssence by SubscriptionManager.blueEssence.collectAsState()
            val isAdminUser = userRole == "admin" || AuthManager.isCurrentUserAdmin()

            var showBuyEssenceDialog by remember { mutableStateOf(false) }
            var showEssenceLabel by remember { mutableStateOf(false) }
            var showPurchaseHistoryDialog by remember { mutableStateOf(false) }

            if (showBuyEssenceDialog) {
                com.example.ui.components.BuyEssenceDialog(
                    isAdmin = isAdminUser,
                    onDismiss = { showBuyEssenceDialog = false }
                )
            }

            if (showPurchaseHistoryDialog) {
                com.example.ui.components.PurchaseHistoryDialog(
                    isAdmin = isAdminUser,
                    onDismiss = { showPurchaseHistoryDialog = false }
                )
            }

            // Contenedor Esencia Azul: Logo ENCIMA de la cantidad
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                // Logo de la Esencia Azul encima (al presionar muestra 'Esencia Azul')
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(androidx.compose.foundation.shape.CircleShape)
                        .background(com.example.ui.theme.HextechCyan.copy(alpha = 0.15f))
                        .clickable {
                            showEssenceLabel = !showEssenceLabel
                            android.widget.Toast.makeText(context, "Esencia Azul", android.widget.Toast.LENGTH_SHORT).show()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = com.example.R.drawable.ic_blue_essence),
                        contentDescription = "Esencia Azul",
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Etiqueta al presionar encima del logo
                AnimatedVisibility(visible = showEssenceLabel) {
                    Text(
                        text = "Esencia Azul",
                        color = com.example.ui.theme.HextechCyan,
                        fontSize = 11.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Cantidad de Esencia Azul debajo (al presionar abre diálogo de comprar)
                Surface(
                    modifier = Modifier.clickable { showBuyEssenceDialog = true },
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF1E293B),
                    border = BorderStroke(1.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$currentBlueEssence EA",
                            color = com.example.ui.theme.HextechCyan,
                            fontSize = 13.5.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "+",
                            color = com.example.ui.theme.HextechGold,
                            fontSize = 14.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }
                }
            }
            
            var showInboxDialog by remember { mutableStateOf(false) }
            if (showInboxDialog) {
                com.example.ui.components.UserInboxDialog(
                    userUid = user.uid,
                    onDismiss = { showInboxDialog = false }
                )
            }
            
            val unreadCount by SubscriptionManager.unreadMessagesCount.collectAsState()
            if (unreadCount > 0) {
                val infiniteTransition = rememberInfiniteTransition(label = "NewMessageAnimation")
                val pulseScale by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.035f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "pulseScale"
                )
                val iconWiggle by infiniteTransition.animateFloat(
                    initialValue = -12f,
                    targetValue = 12f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 280, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "iconWiggle"
                )
                val glowAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.5f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "glowAlpha"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .graphicsLayer {
                            scaleX = pulseScale
                            scaleY = pulseScale
                        }
                ) {
                    Button(
                        onClick = { showInboxDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(
                            1.5.dp,
                            Brush.horizontalGradient(
                                listOf(
                                    com.example.ui.theme.HextechGold.copy(alpha = glowAlpha),
                                    DangerRed.copy(alpha = glowAlpha),
                                    com.example.ui.theme.HextechCyan.copy(alpha = glowAlpha)
                                )
                            )
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFFDC2626),
                                        Color(0xFF991B1B)
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MarkEmailUnread,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(22.dp)
                                        .graphicsLayer {
                                            rotationZ = iconWiggle
                                        }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Tienes $unreadCount mensaje(s) nuevo(s)",
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 13.5.sp
                                    )
                                    Text(
                                        text = "Toca para abrir tu bandeja de entrada",
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = 10.5.sp
                                    )
                                }
                            }

                            Surface(
                                color = com.example.ui.theme.HextechGold.copy(alpha = glowAlpha),
                                shape = RoundedCornerShape(6.dp),
                                shadowElevation = 4.dp
                            ) {
                                Text(
                                    text = "¡NUEVO!",
                                    color = Color(0xFF0F172A),
                                    fontWeight = FontWeight.Black,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                OutlinedButton(
                    onClick = { showInboxDialog = true },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = com.example.ui.theme.HextechCyan),
                    border = BorderStroke(1.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                ) {
                    Icon(Icons.Default.Message, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Bandeja de Entrada")
                }
            }

            // Premium Status Card & Expiration Indicator
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        userRole == "admin" -> com.example.ui.theme.HextechGold.copy(alpha = 0.12f)
                        isExpiringSoon -> com.example.ui.theme.DangerRed.copy(alpha = 0.12f)
                        isPremium -> com.example.ui.theme.HextechGold.copy(alpha = 0.1f)
                        else -> com.example.ui.theme.HextechSurfaceVariant.copy(alpha = 0.5f)
                    }
                ),
                border = BorderStroke(
                    1.2.dp,
                    when {
                        userRole == "admin" -> com.example.ui.theme.HextechGold
                        isExpiringSoon -> com.example.ui.theme.DangerRed
                        isPremium -> com.example.ui.theme.HextechGold
                        else -> com.example.ui.theme.TextMuted.copy(alpha = 0.5f)
                    }
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (isExpiringSoon) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = com.example.ui.theme.DangerRed,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text(
                                    text = when {
                                        userRole == "admin" -> "👑 Administrador"
                                        isExpiringSoon -> "⚠️ Suscripción por Vencer"
                                        isPremium -> "🌟 Suscripción Activa"
                                        else -> "Plan Gratuito"
                                    },
                                    color = when {
                                        isExpiringSoon -> com.example.ui.theme.DangerRed
                                        isPremium -> com.example.ui.theme.HextechGold
                                        else -> com.example.ui.theme.TextPrimary
                                    },
                                    fontSize = 15.sp,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = when {
                                    userRole == "admin" -> "Acceso vitalicio ilimitado a todas las funciones"
                                    isPremium -> "⏳ $remainingFormatted"
                                    else -> "Funciones básicas limitadas"
                                },
                                color = if (isExpiringSoon) com.example.ui.theme.DangerRed.copy(alpha = 0.9f) else com.example.ui.theme.TextSecondary,
                                fontSize = 12.sp,
                                fontWeight = if (isExpiringSoon) androidx.compose.ui.text.font.FontWeight.SemiBold else androidx.compose.ui.text.font.FontWeight.Normal
                            )
                        }
                        
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    when {
                                        userRole == "admin" -> com.example.ui.theme.DangerRed
                                        isExpiringSoon -> com.example.ui.theme.DangerRed
                                        isPremium -> com.example.ui.theme.HextechGold
                                        else -> com.example.ui.theme.HextechSurface
                                    }
                                )
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isExpiringSoon) "EXPIRA PRONTO" else userRole.uppercase(),
                                color = if (userRole == "free") com.example.ui.theme.TextPrimary else com.example.ui.theme.HextechDarkBg,
                                fontSize = 10.5.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
                            )
                        }
                    }

                    // Expiring soon alert banner & CTA
                    if (isExpiringSoon) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(com.example.ui.theme.DangerRed.copy(alpha = 0.15f))
                                .border(1.dp, com.example.ui.theme.DangerRed.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Column {
                                Text(
                                    text = "⚡ ¡Tu pase está a punto de finalizar! Quedan $remainingFormatted. Renueva ahora para no perder tus avatares, temas y asistente de drafting.",
                                    color = com.example.ui.theme.DangerRed,
                                    fontSize = 11.5.sp,
                                    lineHeight = 16.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { showPlansDialog = true },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = com.example.ui.theme.DangerRed,
                                        contentColor = androidx.compose.ui.graphics.Color.White
                                    ),
                                    modifier = Modifier.fillMaxWidth().height(36.dp),
                                    shape = RoundedCornerShape(6.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(15.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        "Renovar / Extender Suscripción",
                                        fontSize = 11.5.sp,
                                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            TextButton(onClick = { showPlansDialog = true }) {
                Text(
                    text = if (isPremium) "Ver / Cambiar Plan de Suscripción" else "Comparar Planes Premium",
                    color = com.example.ui.theme.HextechCyan,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            
            TextButton(onClick = { showHistoryDialog = true }) {
                Text(
                    text = "Historial de Suscripciones",
                    color = com.example.ui.theme.TextSecondary,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
            
            var showSupportDialog by remember { mutableStateOf(false) }

            if (showSupportDialog) {
                com.example.ui.components.SupportReportDialog(onDismiss = { showSupportDialog = false })
            }

            if (showHistoryDialog) {
                com.example.ui.components.SubscriptionHistoryDialog(
                    userId = user.uid,
                    userEmail = user.email,
                    onDismiss = { showHistoryDialog = false }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Slot usage and no-logout recommendation
            var registeredDevicesCount by remember { mutableStateOf(1) }
            LaunchedEffect(user.uid) {
                com.google.firebase.firestore.FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(user.uid)
                    .get()
                    .addOnSuccessListener { doc ->
                        val devs = doc.get("registeredDevices") as? List<*> ?: emptyList<Any>()
                        registeredDevicesCount = devs.size.coerceAtLeast(1)
                    }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.HextechSurfaceVariant.copy(alpha = 0.5f)),
                border = BorderStroke(1.dp, com.example.ui.theme.HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "📱 " + com.example.util.tr("Slots de Dispositivo:"),
                                color = com.example.ui.theme.HextechGold,
                                fontSize = 12.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                        }
                        Text(
                            text = "$registeredDevicesCount de 2 en uso",
                            color = com.example.ui.theme.HextechCyan,
                            fontSize = 12.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "🔒 " + com.example.util.tr("Por seguridad de tu cuenta, la liberación y reasignación de slots de hardware es gestionada exclusivamente por los Administradores desde el panel de soporte."),
                        color = com.example.ui.theme.TextSecondary,
                        fontSize = 10.5.sp,
                        lineHeight = 14.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "⚠️ " + com.example.util.tr("Recomendación: Se recomienda no cerrar sesión para evitar un mal funcionamiento o problemas a futuro con tu cuenta, sincronización de licencias y el acceso fluido a tus herramientas de drafting."),
                        color = com.example.ui.theme.TextMuted,
                        fontSize = 10.5.sp,
                        lineHeight = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Grid: 2x2 Clean Hextech Card Layout
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Card 1: Avatar Collection
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(68.dp)
                            .clickable { showAvatarDialog = true },
                        shape = RoundedCornerShape(12.dp),
                        color = com.example.ui.theme.HextechSurfaceVariant,
                        border = BorderStroke(1.2.dp, com.example.ui.theme.HextechGold.copy(alpha = 0.8f))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(com.example.ui.theme.HextechGold.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null,
                                    tint = com.example.ui.theme.HextechGold,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Avatares",
                                    color = com.example.ui.theme.HextechGold,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = "Colección & Marcos",
                                    color = com.example.ui.theme.TextMuted,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }

                    // Card 2: Theme Customization
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(68.dp)
                            .clickable { showThemeDialog = true },
                        shape = RoundedCornerShape(12.dp),
                        color = com.example.ui.theme.HextechSurfaceVariant,
                        border = BorderStroke(1.2.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.8f))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(com.example.ui.theme.HextechCyan.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Palette,
                                    contentDescription = null,
                                    tint = com.example.ui.theme.HextechCyan,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Temas",
                                    color = com.example.ui.theme.HextechCyan,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = "Colores & Estilos",
                                    color = com.example.ui.theme.TextMuted,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Card 3: Blue Essence History
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(68.dp)
                            .clickable {
                                if (isAdminUser) {
                                    showPurchaseHistoryDialog = true
                                } else {
                                    android.widget.Toast.makeText(context, "Fuera de servicio temporalmente", android.widget.Toast.LENGTH_SHORT).show()
                                }
                            },
                        shape = RoundedCornerShape(12.dp),
                        color = com.example.ui.theme.HextechSurfaceVariant,
                        border = BorderStroke(1.2.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(com.example.ui.theme.HextechCyan.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.foundation.Image(
                                    painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.ic_blue_essence),
                                    contentDescription = "Esencia Azul",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Esencia Azul",
                                    color = com.example.ui.theme.HextechCyan,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = "Historial & Recargas",
                                    color = com.example.ui.theme.TextMuted,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }

                    // Card 4: Creators / Community
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(68.dp)
                            .clickable {
                                if (userRole == "admin") showCommunityCreatorsDialog = true
                                else android.widget.Toast.makeText(context, "Fuera de servicio temporalmente", android.widget.Toast.LENGTH_SHORT).show()
                            },
                        shape = RoundedCornerShape(12.dp),
                        color = com.example.ui.theme.HextechSurfaceVariant,
                        border = BorderStroke(1.2.dp, com.example.ui.theme.HextechGold.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(com.example.ui.theme.HextechGold.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("👑", fontSize = 18.sp)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Creadores",
                                    color = com.example.ui.theme.HextechGold,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = "Comunidad Pro",
                                    color = com.example.ui.theme.TextMuted,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Support Center Card Button
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showSupportDialog = true },
                shape = RoundedCornerShape(12.dp),
                color = com.example.ui.theme.HextechSurfaceVariant,
                border = BorderStroke(1.dp, com.example.ui.theme.HextechCyan.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = com.example.ui.theme.HextechCyan,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                "Centro de Soporte y Ayuda",
                                color = com.example.ui.theme.HextechCyan,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(
                                "Reportar bugs, consultas o sugerencias",
                                color = com.example.ui.theme.TextMuted,
                                fontSize = 10.5.sp
                            )
                        }
                    }
                    Text("›", color = com.example.ui.theme.HextechCyan, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            
            if (userRole == "admin") {
                Button(
                    onClick = { showAdminDashboard = true },
                    colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.DangerRed),
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = Icons.Default.AdminPanelSettings, contentDescription = null, tint = com.example.ui.theme.HextechDarkBg)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Panel de Administración", color = com.example.ui.theme.HextechDarkBg, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            Button(
                onClick = onSignOut,
                colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.HextechSurfaceVariant),
                modifier = Modifier.fillMaxWidth().height(46.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, DangerRed.copy(alpha = 0.35f))
            ) {
                Text("Cerrar Sesión", color = DangerRed, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
