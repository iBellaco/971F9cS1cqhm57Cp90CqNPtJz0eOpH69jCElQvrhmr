package com.example.ui.auth

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
        currentUser = auth?.currentUser
        SubscriptionManager.init(context)
        viewModel.resetSuccessState()
        onLoginSuccess?.invoke()
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
    var showAdminDashboard by remember { mutableStateOf(false) }

    val isExpiringSoon = remember(premiumUntil, isPremium, userRole) {
        SubscriptionManager.isExpiringSoon()
    }
    val remainingFormatted = remember(premiumUntil, isPremium, userRole) {
        SubscriptionManager.getRemainingPremiumTimeFormatted()
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

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
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
            Spacer(modifier = Modifier.height(16.dp))

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
            
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { showAvatarDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.HextechGold),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null, tint = com.example.ui.theme.HextechDarkBg)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cambiar Avatar de LoL", color = com.example.ui.theme.HextechDarkBg, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { showThemeDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.HextechCyan),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(imageVector = Icons.Filled.Palette, contentDescription = null, tint = com.example.ui.theme.HextechDarkBg)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Personalizar Tema", color = com.example.ui.theme.HextechDarkBg, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))
            
            if (userRole == "admin") {
                Button(
                    onClick = { showAdminDashboard = true },
                    colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.DangerRed),
                    modifier = Modifier.fillMaxWidth().height(50.dp),
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
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, DangerRed.copy(alpha = 0.3f))
            ) {
                Text("Cerrar Sesión", color = DangerRed)
            }
        }
    }
}
