package org.parowings.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.parowings.theming.DarkGray
import cafe.adriel.voyager.core.screen.Screen
import org.parowings.screens.home.Home
import org.parowings.screens.pet.myPets.MyPets
import org.parowings.screens.userProfile.Profile

enum class BottomNavItem {
    HOME,
    PETS,
    PROFILE
}

@Composable
fun CustomBottomNavigation(
    selectedItem: BottomNavItem,
    onItemClick: (BottomNavItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(36.dp),
        color = Color(0xFFF8F8F8),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavIcon(
                icon = Icons.Default.GridView,
                selected = selectedItem == BottomNavItem.HOME,
                onClick = { onItemClick(BottomNavItem.HOME) }
            )

            BottomNavIcon(
                icon = Icons.Default.Pets,
                selected = selectedItem == BottomNavItem.PETS,
                onClick = { onItemClick(BottomNavItem.PETS) }
            )

            BottomNavIcon(
                icon = Icons.Rounded.Settings,
                selected = selectedItem == BottomNavItem.PROFILE,
                onClick = { onItemClick(BottomNavItem.PROFILE) }
            )
        }
    }
}

@Composable
private fun BottomNavIcon(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                if (selected) DarkGray else Color.Transparent
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) Color.White else Color.LightGray
        )
    }
}

fun shouldShowBottomBar(currentScreen: Screen): Boolean {
    return currentScreen is Home || currentScreen is MyPets || currentScreen is Profile
}

fun bottomNavItemFor(screen: Screen): BottomNavItem {
    return when (screen) {
        is Home -> BottomNavItem.HOME
        is MyPets -> BottomNavItem.PETS
        is Profile -> BottomNavItem.PROFILE
        else -> BottomNavItem.HOME
    }
}

fun bottomNavScreenFor(item: BottomNavItem): Screen {
    return when (item) {
        BottomNavItem.HOME -> Home
        BottomNavItem.PETS -> MyPets
        BottomNavItem.PROFILE -> Profile
    }
}
