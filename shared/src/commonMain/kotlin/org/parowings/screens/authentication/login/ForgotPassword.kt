package org.parowings.screens.authentication.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object ForgotPassword : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        ForgotPasswordScreen(
            onBackClick = {
                navigator.pop()
            },
            onSendCodeClick = { email ->
                // Handle sending reset code logic
                // navigator.push(VerifyOtp(email))
            }
        )
    }
}
