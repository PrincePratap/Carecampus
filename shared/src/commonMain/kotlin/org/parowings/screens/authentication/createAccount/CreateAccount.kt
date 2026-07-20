package org.parowings.screens.authentication.createAccount

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object CreateAccount : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        CreateAccountScreen()
    }
}