package org.parowings.screens.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.authentication.login.Login

object Onboarding2 : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        OnBoardingScreen2(
            onSkipClick = {
                navigator.push(Login)
            },
            onNextClick = {
                // Navigate to next onboarding or login
                navigator.push(Login)
            }
        )
    }
}
