package org.parowings.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.parowings.screens.common.BottomNavItem
import org.parowings.screens.common.CustomBottomNavigation
import org.parowings.screens.common.bottomNavItemFor
import org.parowings.screens.common.bottomNavScreenFor
import org.parowings.screens.common.shouldShowBottomBar
import org.parowings.screens.welcome.Welcome

@Composable
fun AppAndroid(
    onRequestSignIn: () -> Unit = {}
) {
    MaterialTheme {
        Navigator(Welcome) { navigator ->
            val currentScreen = navigator.lastItem

            LaunchedEffect(currentScreen) {
                println("Voyager current screen = " + currentScreen::class.simpleName)
                println("shouldShowBottomBar = " + shouldShowBottomBar(currentScreen))
            }

            Scaffold(
                bottomBar = {
                    if (shouldShowBottomBar(currentScreen)) {
                        CustomBottomNavigation(
                            selectedItem = bottomNavItemFor(currentScreen),
                            onItemClick = { item ->
                                val targetScreen: Screen = bottomNavScreenFor(item)
                                if (currentScreen != targetScreen) {
                                    navigator.replace(targetScreen)
                                }
                            }
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    SlideTransition(navigator)
                }
            }
        }
    }
}