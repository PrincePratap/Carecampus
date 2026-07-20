package org.parowings.screens.report.animalReport

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.report.animalCondition.AnimalReportCondition

object AnimalReport : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        AnimalReportScreen(
            onBackClick = { navigator.pop() },
            onContinueClick = { navigator.push(AnimalReportCondition) }
        )
    }

}