package org.parowings.screens.authentication.ngoAuth

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object NgoRegistrationStep2 : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        NgoRegistrationStep2Screen(
            onBackClick = {
                navigator.pop()
            },
            onContinueClick = {
                // Navigate to Step 3
                // navigator.push(NgoRegistrationStep3)
            }
        )
    }
}
