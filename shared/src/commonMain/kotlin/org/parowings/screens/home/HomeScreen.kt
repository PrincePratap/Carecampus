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

// Custom Pastel Colors from the image
val CardBlue = Color(0xFFE1F5FE)
val CardPink = Color(0xFFFCE4EC)
val CardPurple = Color(0xFFF3E5F5)
val CardGreen = Color(0xFFE8F5E9)
val CardOrange = Color(0xFFFFF3E0)
val CardIndigo = Color(0xFFE8EAF6)
val DarkGray = Color(0xFF333333)

data class TrainingItem(val title: String, val color: Color)

@Composable
fun HomeScreen() {
    val trainingItems = listOf(
        TrainingItem("Trust", CardBlue),
        TrainingItem("Targeting", CardPink),
        TrainingItem("Sit", CardPurple),
        TrainingItem("Stay", CardGreen),
        TrainingItem("Dog's Name", CardPink),
        TrainingItem("Roll", CardIndigo)
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
                items(trainingItems) { item ->
                    TrainingCard(item)
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

        // Right Profile Section
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            val signOutAction: (() -> Unit)? = try {
//                GlobalContext.get().get<() -> Unit>(qualifier = named("googleSignOut"))
//            } catch (t: Throwable) {
//                null
//            }
//
//            signOutAction?.let { signOut ->
//                TextButton(onClick = { signOut() }) {
//                    Text(text = "Sign out")
//                }
//            }
//            Text(
//                text = "Hi, David",
//                fontSize = 14.sp,
//                color = Color.Gray,
//                modifier = Modifier.padding(end = 8.dp)
//            )
//            // Profile Image Placeholder
//            Box(
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFA5D6A7)),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                // In a real app, use Image() here
//                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
//            }
//        }
    }
}

@Composable
fun TrainingCard(item: TrainingItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = item.color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Placeholder for the Dog Illustration
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = DarkGray.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = DarkGray
            )
        }
    }
}



// Custom Bone icon helper since it's not in standard Material Icons
//val Icons.Default.Bone: androidx.compose.ui.graphics.vector.ImageVector
//    get() = Icons.Default.Bento // Placeholder for visual similarity in preview

@Preview(showBackground = true)
@Composable
fun PreviewDogTraining() {
    MaterialTheme {
        HomeScreen()
    }
}