package org.parowings.screens.authentication.citizenAuth

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object CitizenRegistration : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        CitizenRegistrationScreen(
            onBackClick = {
                navigator.pop()
            },
            onContinueClick = {
                // Handle registration success
            }
        )
    }
}
