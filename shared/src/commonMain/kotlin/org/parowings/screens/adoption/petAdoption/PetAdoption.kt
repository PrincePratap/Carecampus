package org.parowings.screens.adoption.petAdoption

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import org.parowings.screens.adoption.petDetail.PetDetail

object PetAdoption : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: PetAdoptionViewModel = koinInject()
        val uiState by viewModel.adoptionListState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.getAdoptions()
        }

        PetAdoptionScreen(
            petList = uiState.adoptionList,
            isLoading = uiState.isLoading,
            error = uiState.error,
            onAdoptionClick = { navigator.push(PetDetail(it)) }
        )
    }
}
