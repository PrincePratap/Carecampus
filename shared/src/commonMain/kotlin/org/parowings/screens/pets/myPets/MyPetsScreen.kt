package org.parowings.screens.pets.myPets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

data class Pet(
    val id: Int,
    val name: String,
    val breed: String,
    val age: String,
    val gender: String,
    val status: String,
    val statusColor: Color,
    val statusBgColor: Color,
    val iconBgColor: Color,
    val icon: ImageVector
)

@Composable
fun MyPetsScreen(
    onPetClick: (Pet) -> Unit = {},
    onAddPetClick: () -> Unit = {}
) {
    val pets = listOf(
        Pet(
            id = 1,
            name = "Bruno",
            breed = "Labrador",
            age = "2 years",
            gender = "Male",
            status = "Vaccinated",
            statusColor = PrimaryGreen,
            statusBgColor = Color(0xFFE8F5E9),
            iconBgColor = Color(0xFFA5D6A7),
            icon = Icons.Outlined.Pets
        ),
        Pet(
            id = 2,
            name = "Simba",
            breed = "Persian cat",
            age = "1 year",
            gender = "Female",
            status = "Booster due",
            statusColor = PrimaryOrange,
            statusBgColor = Color(0xFFFFF3E0),
            iconBgColor = Color(0xFFFFCC80),
            icon = Icons.Outlined.Pets // Using Pets as a placeholder for cat
        )
    )

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            MyPetsTopBar()
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            
            items(pets) { pet ->
                PetCard(pet = pet)
            }
            
            item {
                AddPetDashedButton(
                    onClick = { onAddPetClick() }

                )
            }
            
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun MyPetsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "My pets",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
        
        Surface(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add pet",
                    tint = TextDark
                )
            }
        }
    }
}

@Composable
fun PetCard(pet: Pet) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pet Icon/Avatar
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(pet.iconBgColor, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = pet.icon,
                    contentDescription = null,
                    tint = TextDark.copy(alpha = 0.6f),
                    modifier = Modifier.size(36.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Pet Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pet.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = "${pet.breed} · ${pet.age} · ${pet.gender}",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                // Status Chip
                Row(
                    modifier = Modifier
                        .background(pet.statusBgColor, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (pet.status == "Vaccinated") Icons.Outlined.CheckCircle else Icons.Outlined.MedicalServices,
                        contentDescription = null,
                        tint = pet.statusColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = pet.status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = pet.statusColor
                    )
                }
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextGray.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun AddPetDashedButton(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clickable(onClick = {onClick()})
            .fillMaxWidth()
            .height(64.dp)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val dashWidth = 10f
            val dashGap = 10f
            drawRoundRect(
                color = TextGray.copy(alpha = 0.2f),
                style = Stroke(
                    width = 2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)
                ),
                cornerRadius = CornerRadius(32.dp.toPx())
            )
        }
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = TextDark,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add a pet",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
        }
    }
}

@Composable
fun PetsBottomBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .height(80.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(Icons.Outlined.Home, "Home", false)
                BottomNavItem(Icons.Outlined.LocationOn, "Rescue", false)
                Spacer(modifier = Modifier.width(56.dp))
                BottomNavItem(Icons.Outlined.Pets, "Pets", true)
                BottomNavItem(Icons.Outlined.AccountCircle, "Profile", false)
            }

            // Central FAB
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-28.dp))
                    .size(64.dp)
                    .shadow(12.dp, CircleShape, spotColor = PrimaryOrange)
                    .background(PrimaryOrange, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFE8F5E9), RoundedCornerShape(20.dp))
                    .padding(horizontal = 20.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
        } else {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextGray.copy(alpha = 0.6f),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = TextGray.copy(alpha = 0.6f)
            )
        }
    }
}
