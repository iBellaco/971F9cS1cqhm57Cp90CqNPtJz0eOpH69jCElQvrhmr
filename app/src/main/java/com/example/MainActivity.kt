package com.example

import android.content.Context
import android.os.Bundle
import com.google.firebase.FirebaseApp

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LaneRole
import com.example.data.auth.AuthRepository
import com.example.ui.screens.DatabaseTestScreen
import com.example.ui.screens.InfoScreen
import com.example.ui.screens.LanguageSelectionScreen
import com.example.ui.screens.MainDraftingScreen
import com.example.ui.screens.MetaAndDraftScreen
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.MyApplicationTheme

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
        try {
            FirebaseApp.initializeApp(this)
            com.example.util.AppLogger.d("APP", "Firebase initialized in MainActivity")
        } catch (e: Exception) {
            com.example.util.AppLogger.e("APP", "Firebase init failed in MainActivity", e)
        }

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
                            text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
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
fun DraftingApp() {
    val context = LocalContext.current
    val sharedPrefs = remember { context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    val isLanguageSet = remember { sharedPrefs.getBoolean("is_language_set", false) }

    var currentScreen by remember { 
        mutableStateOf(if (isLanguageSet) AppScreen.MAIN else AppScreen.LANGUAGE_SELECTION) 
    }
    
    var mainRole by remember { mutableStateOf(LaneRole.MID) }
    var secondRole by remember { mutableStateOf(LaneRole.TOP) }
    var autofillRole by remember { mutableStateOf(LaneRole.JUNGLE) }

    LaunchedEffect(Unit) {
        // Ejecuta la sincronización en segundo plano al arrancar la app para traer los datos desde la nube
        com.example.data.sync.MetaCrawlerSyncService.syncPatchData(context)
    }

    BackHandler(enabled = currentScreen != AppScreen.MAIN && currentScreen != AppScreen.LANGUAGE_SELECTION) {
        currentScreen = AppScreen.MAIN
    }

    var selectedLanguage by remember { mutableStateOf(sharedPrefs.getString("selected_language", "es") ?: "es") }
    LaunchedEffect(selectedLanguage) {
        // Just trigger recompose
    }
    CompositionLocalProvider(LocalLanguage provides selectedLanguage) {
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
                        selectedLanguage = langCode
                        currentScreen = AppScreen.MAIN
                    }
                )
            }
            AppScreen.MAIN -> {
                MainDraftingScreen(
                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
                    onNavigateToMeta = { currentScreen = AppScreen.META },
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
            AppScreen.INFO -> {
                InfoScreen(
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
            AppScreen.META -> {
                MetaAndDraftScreen(
                    userMainRole = mainRole,
                    onNavigateBack = { currentScreen = AppScreen.MAIN }
                )
            }
        }
    }
}
}
