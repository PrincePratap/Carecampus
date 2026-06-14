package org.parowings.screens.`rescue Agency`

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.screens.animalReport.PetBreed
import org.parowings.screens.common.AgencyCard
import org.parowings.screens.home.CustomBottomNavigation
import org.parowings.screens.home.DarkGray
import org.parowings.screens.home.HeaderSection
import org.parowings.screens.home.HomeScreen


@Composable
fun AgenciesScreen() {
    val breeds = listOf(
        PetBreed("Greyhound", "New York City", "Taking care of a pet is my favorite, it helps me to...", true),
        PetBreed("Dobermann", "New York City", "Taking care of a pet is my favorite, it helps me to...", false),
        PetBreed("Pomeranian", "New York City", "Taking care of a pet is my favorite, it helps me to...", true)
    )

    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header: Paw Icon and User Profile
            HeaderSection()

            Spacer(modifier = Modifier.height(32.dp))

            // Text Labels
            Text(
                text = "Let's train your pet!",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Beginners Training",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Grid of Training Cards
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(breeds) { item ->
                    AgencyCard(item)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDogTraining() {
    MaterialTheme {
        AgenciesScreen()
    }
}
