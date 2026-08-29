package com.example.ui.auth

import androidx.compose.animation.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.util.SubscriptionManager
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechSurface
import com.example.util.AuthManager

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthFlowContainer(
    viewModel: AuthViewModel = viewModel()
) {
    val auth = AuthManager.getAuth()
    val context = LocalContext.current
    var userEmail by remember { mutableStateOf(auth?.currentUser?.email) }
    
    LaunchedEffect(userEmail) {
        SubscriptionManager.init(context)
    }
    
    // Check if user is already authenticated
    if (userEmail != null) {
        AuthenticatedProfilePanel(
            email = userEmail!!,
            onSignOut = {
                auth?.signOut()
                userEmail = null
            }
        )
        return
    }

    val uiState by viewModel.uiState.collectAsState()

    // Triggered when login/register succeeds to force a recomposition with the new user state
    val onAuthSuccess: () -> Unit = {
        userEmail = auth?.currentUser?.email
        SubscriptionManager.init(context)
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
fun AuthenticatedProfilePanel(email: String, onSignOut: () -> Unit) {
    val context = LocalContext.current
    val isPremium by SubscriptionManager.isPremium.collectAsState()
    var showThemeDialog by remember { mutableStateOf(false) }
    var showPlansDialog by remember { mutableStateOf(false) }

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
                title = "Perfil de Usuario",
                subtitle = "Sesión iniciada correctamente"
            )
            
            Text(
                text = email,
                color = HextechCyan,
                fontSize = 16.sp
            )

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
                            text = if (isPremium) "Suscripción Premium" else "Plan Gratuito",
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
                    Switch(
                        checked = isPremium,
                        onCheckedChange = { SubscriptionManager.setPremium(context, it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = com.example.ui.theme.HextechDarkBg,
                            checkedTrackColor = com.example.ui.theme.HextechGold
                        )
                    )
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
            
            Button(
                onClick = onSignOut,
                colors = ButtonDefaults.buttonColors(containerColor = DangerRed),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}
