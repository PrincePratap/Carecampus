package org.parowings.screens.userProfile.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import org.parowings.common.data.local.UserSettings
import org.parowings.common.data.local.UserSettingsRepository
import org.parowings.screens.community.Community
import org.parowings.screens.pets.myPets.MyPets
import org.parowings.screens.userProfile.EditProfile
import org.parowings.screens.userProfile.MyReports
import org.parowings.screens.userProfile.SavedAnimals
import org.parowings.screens.userProfile.Settings
import org.parowings.screens.userProfile.achievements.Achievements
import org.parowings.screens.userProfile.emergencyContacts.EmergencyContacts
import androidx.compose.runtime.*

object Profile : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val userRepo: UserSettingsRepository = koinInject()

        val settings by userRepo.userSettingsFlow.collectAsState(
            initial = UserSettings()
        )

        ProfileScreen(
            userName = settings.fullName.ifEmpty { "User" },
            userEmail = settings.email,
            onEditProfileClick = { navigator.push(EditProfile) },
            onSavedAnimalsClick = { navigator.push(SavedAnimals) },
            onMyReportsClick = { navigator.push(MyReports) },
//            onMyAdoptionsClick = { navigator.push(MyAdoptions) },
//            onMyDonationsClick = { navigator.push(MyDonations) },
            onAchievementsClick = { navigator.push(Achievements) },
            onSettingsClick = { navigator.push(Settings) },
            onCommunityClick = { navigator.push(Community) },
            onEmergencyContactsClick = { navigator.push(EmergencyContacts) },
            onMyPetsClick = { navigator.push(MyPets) }
        )
    }
}