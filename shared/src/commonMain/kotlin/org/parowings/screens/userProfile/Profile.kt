package org.parowings.screens.userProfile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.parowings.common.data.local.UserSettingsRepository
import org.parowings.screens.home.Home
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject
import org.parowings.common.data.local.UserSettings


object Profile : Screen {
    @Composable
    override fun Content() {
        val userRepo: UserSettingsRepository = koinInject()

        val settings by userRepo.userSettingsFlow.collectAsState(
            initial = UserSettings()
        )

        ProfileScreen(
            userName = if (settings.fullName.isNotEmpty()) settings.fullName else "User",
            userEmail = settings.email
        )
    }

}