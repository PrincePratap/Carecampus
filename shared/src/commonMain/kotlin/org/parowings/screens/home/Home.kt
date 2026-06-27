package org.parowings.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.adoption.petAdoption.PetAdoption
import org.parowings.screens.authentication.signup.SignUpScreen



object Home : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        HomeScreen(
            clickOnAdoption = {
                navigator.push(PetAdoption)
            }
        )
    }

}