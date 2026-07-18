package org.parowings.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.parowings.screens.common.CustomBottomNavigation
import org.parowings.screens.common.bottomNavItemFor
import org.parowings.screens.common.bottomNavScreenFor
import org.parowings.screens.common.shouldShowBottomBar
import org.parowings.screens.splash.Splash
import org.parowings.screens.welcome.Welcome

@Composable
fun AppAndroid(
    onRequestSignIn: () -> Unit = {}
) {
    MaterialTheme {
        Navigator(Splash) { navigator ->

            val currentScreen = navigator.lastItem

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        SlideTransition(navigator)
                    }
                }

                if (shouldShowBottomBar(currentScreen)) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) {
                        CustomBottomNavigation(
                            selectedItem = bottomNavItemFor(currentScreen),
                            onItemClick = { item ->
                                val targetScreen = bottomNavScreenFor(item)
                                if (currentScreen != targetScreen) {
                                    navigator.replace(targetScreen)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}