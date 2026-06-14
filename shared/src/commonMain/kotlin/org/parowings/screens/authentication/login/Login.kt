package org.parowings.screens.authentication.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.home.Home


object Login : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        LoginScreen(
            onLoginClick = {navigator.push(Home)}
        )
    }

}