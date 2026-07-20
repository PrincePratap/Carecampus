package org.parowings.screens.services

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object AiPetCare : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        AiPetCareScreen(
            onBackClick = { navigator.pop() },
            onServiceClick = { service ->
                when (service) {
                    "scanner" -> navigator.push(MedicineScanner)
                    "breed" -> navigator.push(BreedDetector)
                    "food" -> navigator.push(AiFoodAdvisor)
                    "symptoms" -> navigator.push(SymptomChecker)
                }
            }
        )
    }
}

object MedicineScanner : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MedicineScannerScreen(onBackClick = { navigator.pop() })
    }
}

object BreedDetector : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        BreedDetectorScreen(onBackClick = { navigator.pop() })
    }
}

object AiFoodAdvisor : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        AiFoodAdvisorScreen(onBackClick = { navigator.pop() })
    }
}

object SymptomChecker : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        SymptomCheckerScreen(onBackClick = { navigator.pop() })
    }
}
