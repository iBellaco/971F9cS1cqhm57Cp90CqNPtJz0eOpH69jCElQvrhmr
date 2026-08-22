package com.example

import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.util.LocalLanguage
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LaneRole
import com.example.data.auth.AuthRepository
import com.example.ui.components.AppUpdateDialog
import com.example.ui.screens.DatabaseTestScreen
import com.example.ui.screens.InfoScreen
import com.example.ui.screens.LanguageSelectionScreen
import com.example.ui.screens.MainDraftingScreen
import com.example.ui.screens.MetaAndDraftScreen
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold
import com.example.util.tr
import com.example.util.AppUpdateManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

import androidx.compose.material3.*
import androidx.compose.material.icons.filled.*
enum class AppScreen {
    LOGIN,
    LANGUAGE_SELECTION,
    MAIN,
    INFO,
    META
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = HextechDarkBg
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        DraftingApp()
                        
                        Text(
                            text = "Alfa v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
}
}

@Composable
fun DashboardScreen(
    onNavigateToInfo: () -> Unit,
    onNavigateToLogin: () -> Unit,
    mainRole: LaneRole,
    onMainRoleChange: (LaneRole) -> Unit,
    secondRole: LaneRole,
    onSecondRoleChange: (LaneRole) -> Unit,
    autofillRole: LaneRole,
    onAutofillRoleChange: (LaneRole) -> Unit,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    var showExitDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showExitDialog) {
        com.example.ui.components.ExitConfirmationDialog(
            onConfirmExit = {
                val activity = context as? android.app.Activity
                activity?.finish()
            },
            onDismiss = { showExitDialog = false }
        )
    }

    BackHandler(enabled = selectedTab == 0) {
        showExitDialog = true
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = com.example.ui.theme.HextechDarkBg,
                contentColor = com.example.ui.theme.HextechGold
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text(tr("Inicio")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HextechDarkBg,
                        selectedTextColor = HextechGold,
                        indicatorColor = HextechGold,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Catálogo") },
                    label = { Text(tr("Catálogo")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HextechDarkBg,
                        selectedTextColor = HextechCyan,
                        indicatorColor = HextechCyan,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (selectedTab == 0) {
                MainDraftingScreen(
                    onNavigateToInfo = onNavigateToInfo,
                    onNavigateToMeta = { selectedTab = 1 }, // Navigates to Catalog tab
                    onNavigateToLogin = onNavigateToLogin,
                    mainRole = mainRole,
                    onMainRoleChange = onMainRoleChange,
                    secondRole = secondRole,
                    onSecondRoleChange = onSecondRoleChange,
                    autofillRole = autofillRole,
                    onAutofillRoleChange = onAutofillRoleChange,
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange
                )
            } else {
                MetaAndDraftScreen(
                    userMainRole = mainRole,
                    onNavigateBack = { selectedTab = 0 }
                )
            }
        }
    }
}


@Composable
fun DraftingApp() {
    val context = LocalContext.current
    val sharedPrefs = remember { context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    var isLanguageSet by remember { mutableStateOf(sharedPrefs.getBoolean("is_language_set", false)) }

    var currentScreen by remember { 
        mutableStateOf(if (isLanguageSet) AppScreen.MAIN else AppScreen.LANGUAGE_SELECTION) 
    }
    
    val coroutineScope = rememberCoroutineScope()
    var mainRole by remember { mutableStateOf(LaneRole.TOP) }
    var secondRole by remember { mutableStateOf(LaneRole.MID) }
    var autofillRole by remember { mutableStateOf(LaneRole.SUPPORT) }
    val activeUpdateInfo by AppUpdateManager.updateInfo.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        // Ejecuta la sincronización en segundo plano al arrancar la app para traer los datos desde la nube
        com.example.data.sync.MetaCrawlerSyncService.syncPatchData(context)
        if (isLanguageSet) {
            AppUpdateManager.checkForUpdates(context)
        }
    }

    var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }

    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
        // Modal de Alerta de Actualización Disponible con opción de descarga directa
        activeUpdateInfo?.let { update ->
            if (update.isUpdateAvailable) {
                AppUpdateDialog(
                    updateInfo = update,
                    onDismiss = { AppUpdateManager.dismissAlert() }
                )
            }
        }

    BackHandler(enabled = currentScreen != AppScreen.MAIN && currentScreen != AppScreen.LANGUAGE_SELECTION) {
        currentScreen = AppScreen.MAIN
    }

                    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            if (targetState == AppScreen.MAIN && (initialState == AppScreen.LANGUAGE_SELECTION || initialState == AppScreen.LOGIN)) {
                (fadeIn()).togetherWith(fadeOut())
            } else if (targetState == AppScreen.LANGUAGE_SELECTION && initialState == AppScreen.LOGIN) {
                (fadeIn()).togetherWith(fadeOut())
            } else if (targetState == AppScreen.MAIN) {
                (slideInHorizontally { -it } + fadeIn()).togetherWith(slideOutHorizontally { it } + fadeOut())
            } else {
                (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())
            }
        },
        label = "screen_navigation"
    ) { screen ->
        when (screen) {
            AppScreen.LOGIN -> {
                DatabaseTestScreen(
                    onContinue = {
                        currentScreen = if (isLanguageSet) AppScreen.MAIN else AppScreen.LANGUAGE_SELECTION
                    }
                )
            }
            AppScreen.LANGUAGE_SELECTION -> {
                LanguageSelectionScreen(
                    onLanguageSelected = { langCode ->
                        sharedPrefs.edit()
                            .putBoolean("is_language_set", true)
                            .putString("selected_language", langCode)
                            .apply()
                        isLanguageSet = true
                        selectedLanguage = langCode
                        currentScreen = AppScreen.MAIN
                        // Iniciar comprobación de actualización tras seleccionar el idioma (aparecerá como pop-up)
                        coroutineScope.launch {
                            AppUpdateManager.checkForUpdates(context, true)
                        }
                    }
                )
            }
            AppScreen.MAIN -> {
                DashboardScreen(
                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
                    onNavigateToLogin = { currentScreen = AppScreen.LOGIN },
                    mainRole = mainRole,
                    onMainRoleChange = { mainRole = it },
                    secondRole = secondRole,
                    onSecondRoleChange = { secondRole = it },
                    autofillRole = autofillRole,
                    onAutofillRoleChange = { autofillRole = it },
                    currentLanguage = selectedLanguage,
                    onLanguageChange = { newLang ->
                        sharedPrefs.edit().putString("selected_language", newLang).apply()
                        selectedLanguage = newLang
                    }
                )
            }
            AppScreen.META -> {}
            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
        }
    }
}
}
