package org.parowings.screens.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import org.parowings.common.data.local.UserSettingsRepository
import org.parowings.screens.home.Home
import org.parowings.screens.home.HomeScreen

object Welcome : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val signInAction: () -> Unit =
            koinInject(qualifier = named("googleSignIn"))

        val userRepo: UserSettingsRepository =
            koinInject()

        LaunchedEffect(userRepo) {
            userRepo.userSettingsFlow.collectLatest { settings ->
                if (settings.id.isNotEmpty()) {
                    navigator.replace(Home)
                }
            }
        }

        WelcomeScreen(
            onGoogleClick = signInAction
        )
    }
}