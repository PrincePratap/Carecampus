package org.parowings.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.parowings.screens.authentication.getStarted.GetStarted


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ParoWingsApp() {
    var showBottomBar by remember { mutableStateOf(true) }

//    CrTripTheme {
//        TabNavigator(HomeTab) {
//
//            CompositionLocalProvider(LocalBottomBarVisibility provides { visible -> showBottomBar = visible }
//            ) {
//                Scaffold(
//                    content = { padding ->
//                        Box(
//                            Modifier
//                                .fillMaxSize()
//                                .padding(bottom = padding.calculateBottomPadding(), top = padding.calculateTopPadding())
//                        ) {
//                            CurrentTab()
//                        }
//                    },
//                    bottomBar = {
//                        if (showBottomBar) {
//                            CustomBottomNavBar()
//                        }
//                    }
//                )
//            }
//        }
//    }








}
val LocalBottomBarVisibility = compositionLocalOf<(Boolean) -> Unit> {
    error("No bottom bar visibility controller provided")
}