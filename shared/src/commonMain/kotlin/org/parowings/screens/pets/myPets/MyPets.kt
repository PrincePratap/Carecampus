package org.parowings.screens.pets.myPets

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.pets.addPets.AddPet

object MyPets : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        MyPetsScreen(
            onPetClick = { pet ->
                // Handle pet click
            },
            onAddPetClick = {
                navigator.push(AddPet)
            }
        )
    }
}