package org.parowings.screens.home



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import org.koin.core.qualifier.named
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.screens.common.CustomBottomNavigation
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import org.parowings.screens.common.HomeCard
import org.parowings.theming.CardBlue
import org.parowings.theming.CardGreen
import org.parowings.theming.CardIndigo
import org.parowings.theming.CardOrange
import org.parowings.theming.CardPink
import org.parowings.theming.CardPurple
import org.parowings.theming.DarkGray



data class TrainingItem(
    val title: String,
    val color: Color,
    val onClick: () -> Unit
)


@Preview(showBackground = true)
@Composable
fun HomeScreen(
    clickOnAnimalRescue: () -> Unit = {},
    clickOnAdoption: () -> Unit = {},

) {
    val homeItems = listOf(
        TrainingItem(
            title = "Animal Rescue",
            color = CardBlue,
            onClick = {
                // navigator.push(AnimalRescueScreen())
                println("Animal Rescue")
            }
        ),
        TrainingItem(
            title = "Adoption",
            color = CardPink,
            onClick = {
                clickOnAdoption()
                println("Adoption")
            }
        ),
        TrainingItem(
            title = "NGOs",
            color = CardPurple,
            onClick = {
                println("NGOs")
            }
        ),
        TrainingItem(
            title = "Training",
            color = CardGreen,
            onClick = {
                println("Training")
            }
        ),
        TrainingItem(
            title = "Veterinary",
            color = CardOrange,
            onClick = {
                println("Veterinary")
            }
        ),
        TrainingItem(
            title = "Lost & Found",
            color = CardIndigo,
            onClick = {
                println("Lost & Found")
            }
        ),
        TrainingItem(
            title = "Donate",
            color = CardBlue,
            onClick = {
                println("Donate")
            }
        ),
        TrainingItem(
            title = "Volunteers",
            color = CardPink,
            onClick = {
                println("Volunteers")
            }
        )
    )

    Scaffold(
        bottomBar = { CustomBottomNavigation() }
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
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(homeItems) { item ->
                    HomeCard(item)
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Paw Icon
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = Color(0xFFF5F5F5)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Pets, contentDescription = null, tint = DarkGray)
            }
        }

    }
}







