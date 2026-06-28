package org.parowings.screens.adoption.petDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import org.parowings.screens.adoption.petAdoption.PetAdoptionViewModel

data class PetDetail(
    val id: String
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: PetAdoptionViewModel = koinInject()
        val uiState by viewModel.selectedAdoptionState.collectAsStateWithLifecycle()

        LaunchedEffect(id) {
            viewModel.getAdoptionById(id)
        }

        PetDetailScreen(
            adoption = uiState.selectedAdoption,
            isLoading = uiState.isLoading,
            error = uiState.error,
            onBack = { navigator.pop() }
        )
    }
}
