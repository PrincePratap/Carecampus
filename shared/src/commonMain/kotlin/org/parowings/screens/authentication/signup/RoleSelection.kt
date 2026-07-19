package org.parowings.screens.authentication.signup

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.authentication.login.Login

object RoleSelection : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        RoleSelectionScreen(
            onContinueClick = { _ ->
                // Navigate to SignUp screen
                navigator.push(SignUp)
            },
            onLoginClick = {
                navigator.push(Login)
            }
        )
    }
}
