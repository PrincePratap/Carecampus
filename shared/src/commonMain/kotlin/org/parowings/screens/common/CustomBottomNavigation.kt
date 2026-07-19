package org.parowings.screens.common

import androidx.compose.foundation.LocalIndication
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
import cafe.adriel.voyager.core.screen.Screen
import org.parowings.screens.home.Home
import org.parowings.screens.userProfile.Profile







//// new code

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.remember

import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.parowings.screens.community.Community
import org.parowings.screens.rescue.Rescue
import org.parowings.screens.animalReport.AnimalReport
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.TextGray



enum class BottomNavItem {
    HOME,
    RESCUE,
    ADD,
    COMMUNITY,
    PROFILE
}

@Composable
fun CustomBottomNavigation(
    selectedItem: BottomNavItem,
    onItemClick: (BottomNavItem) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = Color.White
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                BottomNavIcon(
                    Icons.Outlined.Home,
                    "Home",
                    selectedItem == BottomNavItem.HOME
                ) {
                    onItemClick(BottomNavItem.HOME)
                }

                BottomNavIcon(
                    Icons.Outlined.LocationOn,
                    "Rescue",
                    selectedItem == BottomNavItem.RESCUE
                ) {
                    onItemClick(BottomNavItem.RESCUE)
                }

                Spacer(modifier = Modifier.width(56.dp))

                BottomNavIcon(
                    Icons.Outlined.PeopleOutline,
                    "Community",
                    selectedItem == BottomNavItem.COMMUNITY
                ) {
                    onItemClick(BottomNavItem.COMMUNITY)
                }

                BottomNavIcon(
                    Icons.Outlined.AccountCircle,
                    "Profile",
                    selectedItem == BottomNavItem.PROFILE
                ) {
                    onItemClick(BottomNavItem.PROFILE)
                }
            }

            FloatingActionButton(
                onClick = { onItemClick(BottomNavItem.ADD) },
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp),
                containerColor = PrimaryOrange,
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
private fun BottomNavIcon(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(72.dp)
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current
            ) {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) PrimaryGreen else TextGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) PrimaryGreen else TextGray
        )
    }
}

fun shouldShowBottomBar(screen: Screen): Boolean {
    return screen is Home ||
            screen is Rescue ||
            screen is Community ||
            screen is Profile
}

fun bottomNavItemFor(screen: Screen): BottomNavItem {
    return when (screen) {
        is Home -> BottomNavItem.HOME
        is Rescue -> BottomNavItem.RESCUE
        is Community -> BottomNavItem.COMMUNITY
        is Profile -> BottomNavItem.PROFILE
        is AnimalReport -> BottomNavItem.ADD
        else -> BottomNavItem.HOME
    }
}

fun bottomNavScreenFor(item: BottomNavItem): Screen {
    return when (item) {
        BottomNavItem.HOME -> Home
        BottomNavItem.RESCUE -> Rescue
        BottomNavItem.ADD -> AnimalReport
        BottomNavItem.COMMUNITY -> Community
        BottomNavItem.PROFILE -> Profile
    }
}
