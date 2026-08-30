package com.example.ui.auth

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info

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
            }
        )
        return
    }

    val uiState by viewModel.uiState.collectAsState()

    // Triggered when login/register succeeds to force a recomposition with the new user state and redirect
    val onAuthSuccess: () -> Unit = {
        currentUser = auth?.currentUser
        SubscriptionManager.init(context)
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
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
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
    val savedUserName by SubscriptionManager.userName.collectAsState()
    var showThemeDialog by remember { mutableStateOf(false) }
    var showPlansDialog by remember { mutableStateOf(false) }
    var showAdminDashboard by remember { mutableStateOf(false) }

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
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f))
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

            val initialChar = finalUserName.firstOrNull()?.uppercaseChar()?.toString() ?: "U"

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .background(
                        androidx.compose.ui.graphics.Brush.radialGradient(
                            listOf(com.example.ui.theme.HextechSurfaceVariant, com.example.ui.theme.HextechDarkBg)
                        )
                    )
                    .border(2.dp, com.example.ui.theme.HextechGold, androidx.compose.foundation.shape.CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initialChar,
                    color = com.example.ui.theme.HextechGold,
                    fontSize = 26.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = finalUserName,
                color = com.example.ui.theme.HextechGold,
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                letterSpacing = 0.3.sp
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

            // Premium Status Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isPremium) com.example.ui.theme.HextechGold.copy(alpha = 0.1f) else androidx.compose.ui.graphics.Color.Transparent
                ),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp, 
                    if (isPremium) com.example.ui.theme.HextechGold else com.example.ui.theme.TextMuted
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isPremium) "Suscripción Activa" else "Plan Gratuito",
                            color = if (isPremium) com.example.ui.theme.HextechGold else com.example.ui.theme.TextPrimary,
                            fontSize = 16.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = if (isPremium) "Acceso total desbloqueado" else "Limitado a funciones básicas",
                            color = com.example.ui.theme.TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (userRole == "admin") com.example.ui.theme.DangerRed else if (isPremium) com.example.ui.theme.HextechGold else com.example.ui.theme.HextechSurface)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userRole.uppercase(),
                            color = if (userRole == "free") com.example.ui.theme.TextPrimary else com.example.ui.theme.HextechDarkBg,
                            fontSize = 12.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            TextButton(onClick = { showPlansDialog = true }) {
                Text("Comparar Planes", color = com.example.ui.theme.HextechCyan, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
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
                border = androidx.compose.foundation.BorderStroke(1.dp, DangerRed.copy(alpha = 0.3f))
            ) {
                Text("Cerrar Sesión", color = DangerRed)
            }
        }
    }
}
