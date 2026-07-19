package org.parowings.screens.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object Welcome : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        WelcomeScreen(
            onGoToDashboardClick = {
                // In a real app, this might navigate to the main dashboard or clear backstack
                // navigator.replaceAll(Dashboard)
            }
        )
    }
}
