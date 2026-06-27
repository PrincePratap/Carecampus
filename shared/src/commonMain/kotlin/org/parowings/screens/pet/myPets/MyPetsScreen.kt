package org.parowings.screens.pet.myPets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import parowings.shared.generated.resources.Res
import parowings.shared.generated.resources.paro

// Define the brand colors
val AppYellow = Color(0xFFFFD600)
val CardBackground = Color.White
val TextColor = Color.Black
val NotificationRed = Color(0xFFFF5722)

@Composable
fun MyPetsScreen() {
    // Outer yellow background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppYellow)
            .padding(top = 40.dp) // Simulating the phone notch area
    ) {
        // Main White "Phone" Container
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Top Action Bar (Back and Notification)


                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "My Pets",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextColor
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Grid of Pets
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    // "Add Pets" Card
                    item {
                        PetCardContainer {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = AppYellow,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Add Pets",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    // Simba Card
                    item {
                        PetItemCard(name = "Simba", imageRes = null) // Replace null with your drawable
                    }

                    // Bella Card
                    item {
                        PetItemCard(name = "Bella", imageRes = null)
                    }

                    // Johny Card
                    item {
                        PetItemCard(name = "Johny", imageRes = null)
                    }
                }
            }
        }
    }
}

@Composable
fun PetCardContainer(content: @Composable () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .aspectRatio(1f) // Makes the card square
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        content()
    }
}

@Composable
fun PetItemCard(name: String, imageRes: Int?) {
    PetCardContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Pet Avatar Circle
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray) // Placeholder color
            ) {
                if (imageRes != null) {
                    Image(
                        painter = painterResource(Res.drawable.paro),
                        contentDescription = name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPetsPreview() {
    MaterialTheme {
        MyPetsScreen()
    }
}