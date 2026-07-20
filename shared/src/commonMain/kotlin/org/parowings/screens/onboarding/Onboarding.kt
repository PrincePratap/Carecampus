package org.parowings.screens.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.authentication.login.Login

object Onboarding : Screen {
    @Composable
    override fun Content() {
        OnBoardingScreen1.Content()
    }
}
