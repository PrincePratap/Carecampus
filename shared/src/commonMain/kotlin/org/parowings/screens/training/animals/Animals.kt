package org.parowings.screens.training.animals

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.common.AnimalTrainingItem
import org.parowings.screens.pet.addPet.AddPetScreen
import parowings.shared.generated.resources.Res
import parowings.shared.generated.resources.cat
import parowings.shared.generated.resources.dog


object Animals : Screen {
    @Composable
    override fun Content() {

        val trainingItems = listOf(
            AnimalTrainingItem(
                title = "Dog",
                image = Res.drawable.dog,
                color = Color.Transparent,
                onClick = { }
            ),
            AnimalTrainingItem(
                title = "Cat",
                image = Res.drawable.cat,
                color = Color.Transparent,
                onClick = { }
            ),
//            AnimalTrainingItem(
//                title = "Bird",
//                image = Res.drawable.bird,
//                color = Color.Transparent,
//                onClick = { }
//            )
        )

        val navigator = LocalNavigator.currentOrThrow

        AnimalsScreen(
            animalList = trainingItems,
        )
    }

}