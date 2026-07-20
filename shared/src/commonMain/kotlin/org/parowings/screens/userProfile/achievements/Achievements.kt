package org.parowings.screens.userProfile.achievements

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object Achievements : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        AchievementsScreen(
            onBackClick = { navigator.pop() }
        )
    }
}