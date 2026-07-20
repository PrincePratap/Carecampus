package org.parowings.screens.userProfile.emergencyContacts

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.services.ServicesScreen


object EmergencyContacts : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        EmergencyContactsScreen()
    }
}
