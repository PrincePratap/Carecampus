package org.parowings.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.adoption.petAdoption.PetAdoption
import org.parowings.screens.report.animalReport.AnimalReport
import org.parowings.screens.donation.Donation
import org.parowings.screens.services.AiPetCare
import org.parowings.screens.services.Services
import org.parowings.screens.training.animals.Animals


object Home : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        HomeScreen(
            onReportClick = { navigator.push(AnimalReport) },
            onDonateClick = { navigator.push(Donation) },
            onAiToolsClick = { navigator.push(Animals) },
            onAdoptClick = { navigator.push(PetAdoption) },
            onMoreClick = { navigator.push(Services) },
            onMyPetsClick = { navigator.push(Services) },
            onLostFoundClick = { navigator.push(Services) },
            onNearbyVetsClick = { navigator.push(Services) },
            onBannerClick = { navigator.push(AiPetCare) }

        )
    }

}