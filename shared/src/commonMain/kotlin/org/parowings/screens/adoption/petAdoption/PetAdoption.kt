package org.parowings.screens.adoption.petAdoption

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.authentication.login.LoginScreen
import org.parowings.screens.home.Home
import org.koin.compose.koinInject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.parowings.common.adoption.AdoptionViewModel


object PetAdoption : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel: AdoptionViewModel = koinInject()

        val petList by viewModel.adoptionList.collectAsState()
        val isLoading = viewModel.isLoading
        val error = viewModel.error

        LaunchedEffect(Unit) {
            viewModel.getAdoptions()
        }

        Box {
            PetAdoptionScreen(
                petList = petList
            )

            if (isLoading) {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = androidx.compose.ui.Modifier.align(Alignment.Center)
                )
            }

            if (error != null) {
                androidx.compose.material3.Text(
                    text = error,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = androidx.compose.ui.Modifier.align(Alignment.TopCenter).padding(16.dp)
                )
            }
        }
    }
}