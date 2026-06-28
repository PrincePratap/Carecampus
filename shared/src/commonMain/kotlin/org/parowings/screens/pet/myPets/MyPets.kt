package org.parowings.screens.pet.myPets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import org.parowings.common.adoption.MyAdoptionResponse
import org.parowings.common.data.local.UserSettings
import org.parowings.common.data.local.UserSettingsRepository
import org.parowings.screens.adoption.petAdoption.PetAdoptionViewModel
import org.parowings.screens.adoption.petDetail.PetDetail
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object MyPets : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val userRepo: UserSettingsRepository = koinInject()
        val viewModel: PetAdoptionViewModel = koinInject()

        val settings by userRepo.userSettingsFlow.collectAsState(
            initial = UserSettings()
        )
        val uiState by viewModel.myAdoptionsState.collectAsState()

        LaunchedEffect(settings.userId) {
            if (settings.userId.isNotEmpty()) {
                viewModel.getMyAdoptions(settings.userId)
            }
        }

        MyPetsScreen(
            adoptionList = uiState.adoptionList,
            totalPosts = uiState.totalPosts,
            isLoading = uiState.isLoading,
            error = if (settings.userId.isEmpty()) {
                "Please sign in to view your adoptions."
            } else {
                uiState.error
            },
            onAdoptionClick = { adoptionId ->
                navigator.push(PetDetail(adoptionId))
            }
        )
    }
}
