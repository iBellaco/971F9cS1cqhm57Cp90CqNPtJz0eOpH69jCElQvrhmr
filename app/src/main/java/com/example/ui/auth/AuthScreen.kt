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
    var userEmail by remember { mutableStateOf(auth?.currentUser?.email) }
    
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
                title = "Perfil",
                subtitle = "Sesión iniciada correctamente"
            )
            
            Text(
                text = email,
                color = HextechCyan,
                fontSize = 16.sp
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
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
