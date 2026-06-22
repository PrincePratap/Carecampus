package org.parowings.screens.authentication

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import org.parowings.common.authentication.AuthViewModel

object AuthTest : Screen {
    @Composable
    override fun Content() {
        val viewModel: AuthViewModel = koinInject()

        AuthTestScreen(
            viewModel = viewModel
        )
    }
}
