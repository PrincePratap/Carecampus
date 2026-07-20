package org.parowings.screens.userProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.theming.*

object SavedAnimals : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        SavedAnimalsScreen(onBackClick = { navigator.pop() })
    }
}

@Composable
fun SavedAnimalsScreen(onBackClick: () -> Unit = {}) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            SavedAnimalsTopBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SavedAnimalsSearchBar(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp))

            SavedAnimalsFilters(modifier = Modifier.padding(bottom = 16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    AnimalCard(
                        name = "Milo",
                        breed = "Labrador mix",
                        age = "8 months",
                        gender = "Male",
                        location = "Paws of Delhi NGO",
                        distance = "1.2 km",
                        status = "Adoptable",
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFB4D6A4)
                    )
                }
                item {
                    AnimalCard(
                        name = "Simba",
                        breed = "Tabby cat",
                        age = "1 year",
                        gender = "Male",
                        location = "CR Park Animal Shelter",
                        distance = "2.4 km",
                        status = null,
                        icon = Icons.Outlined.Pets, // Should be Cat icon if available
                        iconBgColor = Color(0xFFF5D19E)
                    )
                }
                item {
                    AnimalCard(
                        name = "Chintu",
                        breed = "Indie pup",
                        age = "4 months",
                        gender = "Male",
                        location = "Saket Strays Trust",
                        distance = "3.1 km",
                        status = "Adoptable",
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFC9B4E3)
                    )
                }
                item {
                    AnimalCard(
                        name = "Coco",
                        breed = "Persian cat",
                        age = "2 years",
                        gender = "Female",
                        location = "Nehru Park Rescue",
                        distance = "0.9 km",
                        status = "Adoptable",
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFB4CDE3)
                    )
                }
                item {
                    AnimalCard(
                        name = "Sheru",
                        breed = "German Shepherd mix",
                        age = "2 years",
                        gender = "Male",
                        location = "Paws of Delhi NGO",
                        distance = "4.6 km",
                        status = null,
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFE3D1B4)
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun SavedAnimalsTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            onClick = onBackClick,
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.size(48.dp),
            shadowElevation = 1.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp),
                    tint = TextDark
                )
            }
        }

        Text(
            text = "Saved animals",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Surface(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.size(48.dp),
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier.size(24.dp),
                        tint = TextDark
                    )
                }
            }
            Surface(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.size(48.dp),
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = "Grid view",
                        modifier = Modifier.size(24.dp),
                        tint = TextDark
                    )
                }
            }
        }
    }
}

@Composable
fun SavedAnimalsSearchBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Search saved animals...",
                color = TextGray,
                fontSize = 15.sp,
                modifier = Modifier.weight(1f)
            )
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.size(32.dp),
                shadowElevation = 0.5.dp,
                border = androidx.compose.foundation.BorderStroke(0.5.dp, Color.LightGray.copy(alpha = 0.3f))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Tune,
                        contentDescription = "Filter",
                        modifier = Modifier.size(18.dp),
                        tint = TextDark
                    )
                }
            }
        }
    }
}

@Composable
fun SavedAnimalsFilters(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            FilterChip(label = "All", isSelected = true)
        }
        item {
            FilterChip(label = "Dogs", icon = Icons.Outlined.Pets)
        }
        item {
            FilterChip(label = "Cats", icon = Icons.Outlined.Pets) // Should be Cat icon
        }
        item {
            FilterChip(label = "Adoptable", icon = Icons.Outlined.FavoriteBorder)
        }
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean = false, icon: ImageVector? = null) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) PrimaryGreen else Color.White,
        modifier = Modifier.height(40.dp),
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = if (isSelected) Color.White else TextGray
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else TextGray
            )
        }
    }
}

@Composable
fun AnimalCard(
    name: String,
    breed: String,
    age: String,
    gender: String,
    location: String,
    distance: String,
    status: String?,
    icon: ImageVector,
    iconBgColor: Color
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Animal Thumbnail placeholder
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = iconBgColor,
                modifier = Modifier.size(72.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.DarkGray.copy(alpha = 0.6f),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    if (status != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PrimaryGreen.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = status,
                                color = PrimaryGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                
                Text(
                    text = "$breed · $age · $gender",
                    fontSize = 13.sp,
                    color = TextGray,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$location · $distance",
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White,
                    modifier = Modifier.size(32.dp),
                    shadowElevation = 0.5.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Favorite",
                            tint = Color.Red,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Surface(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White,
                    modifier = Modifier.size(32.dp),
                    shadowElevation = 0.5.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = EmergencyRed,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
