package org.parowings.screens.report.animalLocation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.report.reportSuccess.ReportSuccess


object AnimalReportLocation : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        AnimalReportLocationScreen(
            onBackClick = { navigator.pop() },
            onContinueClick = { navigator.push(ReportSuccess) }
        )
    }

}