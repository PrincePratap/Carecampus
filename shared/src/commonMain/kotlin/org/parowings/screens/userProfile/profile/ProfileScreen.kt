package org.parowings.screens.userProfile.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.parowings.screens.common.ProfileItems.HeaderSection
import org.parowings.screens.common.ProfileItems.ImpactSection
import org.parowings.screens.common.ProfileItems.MenuSection
import org.parowings.screens.common.ProfileItems.StatsCard
import org.parowings.theming.BackgroundLight


@Composable
fun ProfileScreen(
    userName: String = "Prince Rathi",
    userEmail: String = "Citizen · Joined Feb 2025",
    onEditProfileClick: () -> Unit = {},
    onSavedAnimalsClick: () -> Unit = {},
    onMyReportsClick: () -> Unit = {},
    onMyAdoptionsClick: () -> Unit = {},
    onMyDonationsClick: () -> Unit = {},
    onAchievementsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onCommunityClick: () -> Unit = {},
    onEmergencyContactsClick: () -> Unit = {},
    onMyPetsClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundLight,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(userName, userEmail, onEditClick = onEditProfileClick)
            
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .offset(y = (-30).dp)
            ) {
                StatsCard()
                Spacer(modifier = Modifier.height(24.dp))
                ImpactSection()
                Spacer(modifier = Modifier.height(24.dp))
                MenuSection(
                    onSavedAnimalsClick = onSavedAnimalsClick,
                    onMyReportsClick = onMyReportsClick,
                    onMyAdoptionsClick = onMyAdoptionsClick,
                    onMyDonationsClick = onMyDonationsClick,
                    onAchievementsClick = onAchievementsClick,
                    onSettingsClick = onSettingsClick,
                    onCommunityClick = onCommunityClick,
                    onEmergencyContactsClick = onEmergencyContactsClick,
                    onMyPetsClick = onMyPetsClick
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}













//@Composable
//private fun CustomBottomBar() {
//    Surface(
//        modifier = Modifier.fillMaxWidth(),
//        shadowElevation = 8.dp,
//        color = Color.White
//    ) {
//        Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
//            Row(
//                modifier = Modifier.fillMaxSize(),
//                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                BottomNavItem(Icons.Outlined.Home, "Home", false)
//                BottomNavItem(Icons.Outlined.LocationOn, "Rescue", false)
//                Spacer(modifier = Modifier.width(56.dp))
//                BottomNavItem(Icons.Outlined.PeopleOutline, "Community", false)
//                BottomNavItem(Icons.Outlined.AccountCircle, "Profile", true)
//            }
//
//            // Central Add Button
//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .offset(y = (-20).dp)
//                    .size(64.dp)
//                    .shadow(8.dp, CircleShape)
//                    .background(PrimaryOrange, CircleShape),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
//            }
//        }
//    }
//}

//@Composable
//private fun BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Icon(
//            icon,
//            contentDescription = null,
//            tint = if (isSelected) PrimaryGreen else TextGray,
//            modifier = Modifier.size(24.dp)
//        )
//        Text(
//            text = label,
//            fontSize = 10.sp,
//            color = if (isSelected) PrimaryGreen else TextGray,
//            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
//        )
//    }
//}
