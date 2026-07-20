package org.parowings.screens.report.reportSuccess

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object ReportSuccess : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ReportSuccessScreen(
            onBackToHome = {
                navigator.popUntilRoot()
            }
        )
    }
}