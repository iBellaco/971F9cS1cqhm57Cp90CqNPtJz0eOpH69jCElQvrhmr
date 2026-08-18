package com.example

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.model.LaneRole
import com.example.ui.screens.InfoScreen
import com.example.ui.screens.MainDraftingScreen
import com.example.ui.screens.MetaAndDraftScreen
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.MyApplicationTheme

enum class AppScreen {
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
                    DraftingApp()
                }
            }
        }
    }
}

@Composable
fun DraftingApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.MAIN) }
    var mainRole by remember { mutableStateOf(LaneRole.MID) }
    var secondRole by remember { mutableStateOf(LaneRole.TOP) }
    var autofillRole by remember { mutableStateOf(LaneRole.JUNGLE) }

    BackHandler(enabled = currentScreen != AppScreen.MAIN) {
        currentScreen = AppScreen.MAIN
    }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            if (targetState == AppScreen.MAIN) {
                (slideInHorizontally { -it } + fadeIn()).togetherWith(slideOutHorizontally { it } + fadeOut())
            } else {
                (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())
            }
        },
        label = "screen_navigation"
    ) { screen ->
        when (screen) {
            AppScreen.MAIN -> {
                MainDraftingScreen(
                    onNavigateToInfo = { currentScreen = AppScreen.INFO },
                    onNavigateToMeta = { currentScreen = AppScreen.META },
                    mainRole = mainRole,
                    onMainRoleChange = { mainRole = it },
                    secondRole = secondRole,
                    onSecondRoleChange = { secondRole = it },
                    autofillRole = autofillRole,
                    onAutofillRoleChange = { autofillRole = it }
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
