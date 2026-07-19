package org.parowings.screens.authentication.ngoAuth

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object NgoRegistrationStep1 : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        NgoRegistrationStep1Screen(
            onBackClick = {
                navigator.pop()
            },
            onContinueClick = {
                // Navigate to Step 2
                // navigator.push(NgoRegistrationStep2)
            }
        )
    }
}
