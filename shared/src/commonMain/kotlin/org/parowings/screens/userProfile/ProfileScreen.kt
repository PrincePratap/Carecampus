package org.parowings.screens.userProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Local colors to match the image precisely
private val PrimaryGreen = Color(0xFF008A45)
private val SecondaryGreen = Color(0xFF005C2E)
private val PrimaryOrange = Color(0xFFFF7A00)
private val BackgroundLight = Color(0xFFF8FAF9)
private val TextDark = Color(0xFF121212)
private val TextGray = Color(0xFF757575)

@Composable
fun ProfileScreen(
    userName: String = "Prince Rathi",
    userEmail: String = "Citizen · Joined Feb 2025"
) {
    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = { CustomBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(userName, userEmail)
            
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .offset(y = (-30).dp)
            ) {
                StatsCard()
                Spacer(modifier = Modifier.height(24.dp))
                ImpactSection()
                Spacer(modifier = Modifier.height(24.dp))
                MenuSection()
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun HeaderSection(name: String, info: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryGreen, SecondaryGreen)
                )
            )
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Profile",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PR",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = info,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun StatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem("14", "Rescued")
            VerticalDivider(modifier = Modifier.height(30.dp), color = Color(0xFFF0F0F0))
            StatItem("3", "Adopted")
            VerticalDivider(modifier = Modifier.height(30.dp), color = Color(0xFFF0F0F0))
            StatItem("₹4.2k", "Donated")
        }
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Text(text = label, fontSize = 12.sp, color = TextGray)
    }
}

@Composable
private fun ImpactSection() {
    Column {
        Text(
            text = "Your impact this year",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val months = listOf("Feb", "Mar", "Apr", "May", "Jun", "Jul")
                    val heights = listOf(0.4f, 0.6f, 0.8f, 0.5f, 0.7f, 1f)
                    val colors = listOf(PrimaryGreen, PrimaryGreen, PrimaryOrange, PrimaryGreen, PrimaryGreen, PrimaryOrange)
                    
                    months.forEachIndexed { index, month ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .fillMaxHeight(heights[index])
                                    .background(colors[index], RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = month, fontSize = 10.sp, color = TextGray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuSection() {
    Column {
        MenuItem(Icons.Outlined.BookmarkBorder, "Saved animals")
        HorizontalDivider(color = Color(0xFFF0F0F0))
        MenuItem(Icons.Outlined.OutlinedFlag, "My reports")
        HorizontalDivider(color = Color(0xFFF0F0F0))
        MenuItem(Icons.Outlined.FavoriteBorder, "My adoptions")
        HorizontalDivider(color = Color(0xFFF0F0F0))
        MenuItem(Icons.Outlined.VolunteerActivism, "My donations")
        HorizontalDivider(color = Color(0xFFF0F0F0))
        MenuItem(Icons.Outlined.EmojiEvents, "Achievements")
        HorizontalDivider(color = Color(0xFFF0F0F0))
        MenuItem(Icons.Outlined.Settings, "Settings")
    }
}

@Composable
private fun MenuItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = TextGray, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, modifier = Modifier.weight(1f), fontSize = 16.sp, color = TextDark)
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFFCCCCCC))
    }
}

@Composable
private fun CustomBottomBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(80.dp)) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(Icons.Outlined.Home, "Home", false)
                BottomNavItem(Icons.Outlined.LocationOn, "Rescue", false)
                Spacer(modifier = Modifier.width(56.dp))
                BottomNavItem(Icons.Outlined.PeopleOutline, "Community", false)
                BottomNavItem(Icons.Outlined.AccountCircle, "Profile", true)
            }
            
            // Central Add Button
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-20).dp)
                    .size(64.dp)
                    .shadow(8.dp, CircleShape)
                    .background(PrimaryOrange, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
private fun BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            icon, 
            contentDescription = null, 
            tint = if (isSelected) PrimaryGreen else TextGray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label, 
            fontSize = 10.sp, 
            color = if (isSelected) PrimaryGreen else TextGray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
