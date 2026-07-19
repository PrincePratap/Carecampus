package org.parowings.screens.authentication.signup

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object VerifyPhone : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        VerifyPhoneScreen(
            onBackClick = {
                navigator.pop()
            },
            onVerifyClick = { otp ->
                // Handle verification logic
                // navigator.push(NextScreen)
            }
        )
    }
}
