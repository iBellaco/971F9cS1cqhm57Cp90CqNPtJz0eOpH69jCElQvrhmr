package com.example

import android.content.Context
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Block
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LaneRole
import com.example.ui.components.AppUpdateDialog
import com.example.ui.screens.InfoScreen
import com.example.ui.screens.LanguageSelectionScreen
import com.example.ui.screens.MainDraftingScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.MetaScreenMode
import com.example.ui.screens.MetaAndDraftScreen
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold
import com.example.ui.theme.AppThemeManager
import com.example.ui.theme.TextSecondary
import com.example.util.tr
import com.example.util.AppUpdateManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

import androidx.compose.material3.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
enum class AppScreen {
    ONBOARDING,
    LOGIN,
    LANGUAGE_SELECTION,
    MAIN,
    INFO,
    META
}

class MainActivity : ComponentActivity() {    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted
        } else {
            // Permission is denied
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                AppThemeManager.init(this)
        com.example.util.SubscriptionManager.init(this)
        askNotificationPermission()

        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                com.example.ui.components.BlurredMeshBackground(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        DraftingApp()
                        

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
    val pagerState = rememberPagerState(pageCount = { 5 })
    var showExitDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    if (showExitDialog) {
        com.example.ui.components.ExitConfirmationDialog(
            onConfirmExit = {
                val activity = context as? android.app.Activity
                activity?.finish()
            },
            onDismiss = { showExitDialog = false }
        )
    }

    BackHandler(enabled = true) {
        if (pagerState.currentPage == 0) {
            showExitDialog = true
        } else {
            coroutineScope.launch { pagerState.animateScrollToPage(0) }
        }
    }

    val navBg = AppThemeManager.getNavBarBackgroundColor()
    val navAccent = AppThemeManager.getNavBarAccentColor()
    val navIndicator = AppThemeManager.getNavBarIndicatorColor()
    val navSelectedIcon = AppThemeManager.getNavBarSelectedIconColor()
    val navSelectedText = AppThemeManager.getNavBarSelectedTextColor()
    val navUnselected = AppThemeManager.getNavBarUnselectedColor()

    Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        bottomBar = {
            NavigationBar(
                containerColor = navBg,
                contentColor = navSelectedText
            ) {
                // 1. Inicio
                NavigationBarItem(
                    selected = pagerState.currentPage == 0,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text(tr("Inicio")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navSelectedIcon,
                        selectedTextColor = navSelectedText,
                        indicatorColor = navIndicator,
                        unselectedIconColor = navUnselected,
                        unselectedTextColor = navUnselected
                    )
                )
                // 2. Selección (Drafting)
                NavigationBarItem(
                    selected = pagerState.currentPage == 1,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
                    icon = { Icon(Icons.Default.Groups, contentDescription = "Selección") },
                    label = { Text(tr("Selección")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navSelectedIcon,
                        selectedTextColor = navSelectedText,
                        indicatorColor = navIndicator,
                        unselectedIconColor = navUnselected,
                        unselectedTextColor = navUnselected
                    )
                )
                // 3. Tier List
                NavigationBarItem(
                    selected = pagerState.currentPage == 2,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(2) } },
                    icon = { Icon(Icons.Default.TrendingUp, contentDescription = "Tier List") },
                    label = { Text(tr("Tier List")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navSelectedIcon,
                        selectedTextColor = navSelectedText,
                        indicatorColor = navIndicator,
                        unselectedIconColor = navUnselected,
                        unselectedTextColor = navUnselected
                    )
                )
                // 4. Catálogo (Objetos, Runas, Hechizos)
                NavigationBarItem(
                    selected = pagerState.currentPage == 3,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(3) } },
                    icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "Catálogo") },
                    label = { Text(tr("Catálogo")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navSelectedIcon,
                        selectedTextColor = navSelectedText,
                        indicatorColor = navIndicator,
                        unselectedIconColor = navUnselected,
                        unselectedTextColor = navUnselected
                    )
                )
                // 5. Usuario
                NavigationBarItem(
                    selected = pagerState.currentPage == 4,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(4) } },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuario") },
                    label = { Text(tr("Usuario")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = navSelectedIcon,
                        selectedTextColor = navSelectedText,
                        indicatorColor = navIndicator,
                        unselectedIconColor = navUnselected,
                        unselectedTextColor = navUnselected
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true
            ) { page ->
                when (page) {
                0 -> {
                    MainDraftingScreen(
                        onNavigateToInfo = onNavigateToInfo,
                        onNavigateToMeta = { coroutineScope.launch { pagerState.animateScrollToPage(2) } },
                        onNavigateToLogin = { coroutineScope.launch { pagerState.animateScrollToPage(4) } },
                        mainRole = mainRole,
                        onMainRoleChange = onMainRoleChange,
                        secondRole = secondRole,
                        onSecondRoleChange = onSecondRoleChange,
                        autofillRole = autofillRole,
                        onAutofillRoleChange = onAutofillRoleChange,
                        currentLanguage = currentLanguage,
                        onLanguageChange = onLanguageChange
                    )
                }
                1 -> {
                    MetaAndDraftScreen(
                        mode = MetaScreenMode.DRAFTING,
                        userMainRole = mainRole,
                        onNavigateBack = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                    )
                }
                2 -> {
                    MetaAndDraftScreen(
                        mode = MetaScreenMode.TIER_LIST,
                        userMainRole = mainRole,
                        onNavigateBack = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                    )
                }
                3 -> {
                    MetaAndDraftScreen(
                        mode = MetaScreenMode.CATALOG,
                        userMainRole = mainRole,
                        onNavigateBack = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                    )
                }
                4 -> {
                    com.example.ui.auth.AuthFlowContainer()
                }
                else -> {
                    MainDraftingScreen(
                        onNavigateToInfo = onNavigateToInfo,
                        onNavigateToMeta = { coroutineScope.launch { pagerState.animateScrollToPage(2) } },
                        onNavigateToLogin = { coroutineScope.launch { pagerState.animateScrollToPage(4) } },
                        mainRole = mainRole,
                        onMainRoleChange = onMainRoleChange,
                        secondRole = secondRole,
                        onSecondRoleChange = onSecondRoleChange,
                        autofillRole = autofillRole,
                        onAutofillRoleChange = onAutofillRoleChange,
                        currentLanguage = currentLanguage,
                        onLanguageChange = onLanguageChange
                    )
                }
            }
            } // HorizontalPager
        }
    }
}


@Composable
fun DraftingApp() {
    val context = LocalContext.current
    val sharedPrefs = remember { context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    var isLanguageSet by remember { mutableStateOf(sharedPrefs.getBoolean("is_language_set", false)) }
    var hasSeenOnboarding by remember { mutableStateOf(sharedPrefs.getBoolean("has_seen_onboarding", false)) }
    var currentScreen by remember { 
        mutableStateOf(
            when {
                !isLanguageSet -> AppScreen.LANGUAGE_SELECTION
                !hasSeenOnboarding -> AppScreen.ONBOARDING
                else -> AppScreen.MAIN
            }
        ) 
    }
    
    val coroutineScope = rememberCoroutineScope()
    var mainRole by remember { mutableStateOf(LaneRole.TOP) }
    var secondRole by remember { mutableStateOf(LaneRole.MID) }
    var autofillRole by remember { mutableStateOf(LaneRole.SUPPORT) }
    val activeUpdateInfo by AppUpdateManager.updateInfo.collectAsStateWithLifecycle()
    val isBanned by com.example.util.SubscriptionManager.isBanned.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        // Inicializar listado maestro de campeones desde assets JSON
        com.example.data.WildRiftRepository.initChampions(context)

        // Ejecuta la sincronización en segundo plano al arrancar la app para traer los datos desde la nube
        com.example.data.sync.MetaCrawlerSyncService.syncPatchData(context)
        if (isLanguageSet) {
            AppUpdateManager.checkForUpdates(context)
        }
    }

    var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }

    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
        if (isBanned) {
            Box(
                modifier = Modifier.fillMaxSize().background(HextechDarkBg),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                    Icon(Icons.Default.Block, contentDescription = null, tint = Color.Red, modifier = Modifier.size(64.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Cuenta Suspendida", color = Color.Red, fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tu acceso ha sido revocado permanentemente. Contacta con soporte si crees que esto es un error.", color = TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }
            return@CompositionLocalProvider
        }
        
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
            }
                        AppScreen.ONBOARDING -> {
                OnboardingScreen(
                    onFinish = {
                        sharedPrefs.edit().putBoolean("has_seen_onboarding", true).apply()
                        hasSeenOnboarding = true
                        currentScreen = AppScreen.MAIN
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
                        if (!hasSeenOnboarding) {
                            currentScreen = AppScreen.ONBOARDING
                        } else {
                            currentScreen = AppScreen.MAIN
                        }
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
