package org.parowings.screens.report.animalCondition

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.report.animalLocation.AnimalReportLocation


object AnimalReportCondition : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        AnimalReportConditionScreen(
            onBackClick = { navigator.pop() },
            onContinueClick = { navigator.push(AnimalReportLocation) }
        )
    }

}