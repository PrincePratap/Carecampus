package org.parowings.screens.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object EnableLocation : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        EnableLocationScreen(
            onEnableClick = {
                // Handle permission request then navigate
                // navigator.push(NextScreen)
            },
            onMaybeLaterClick = {
                navigator.pop()
            }
        )
    }
}
