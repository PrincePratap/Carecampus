package org.parowings.screens.userProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

// Color Palette based on the image
val BackgroundGray = Color(0xFFF8F9FA)
val PrimaryBlue = Color(0xFF007AFF)
val TextDark = Color(0xFF1A1A1A)
val TextGray = Color(0xFF8E8E93)
val CardBackground = Color.White
val AvatarPink = Color(0xFFFFE4E9)


@Preview(showBackground = true)
@Composable
fun ProfileScreen(
    userName : String ,
    userEmail : String ,
) {
    Scaffold(
        containerColor = BackgroundGray,
        topBar = { ProfileTopBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Profile Header Section
            ProfileHeader(userName,userEmail)

            Spacer(modifier = Modifier.height(32.dp))

            // Stats Card
            StatsCard()

            Spacer(modifier = Modifier.height(32.dp))

            // Menu List
            MenuSection()

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Indicator (Mimicking the iOS-style bar in the image)
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ProfileTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft, contentDescription = "Back")
        }

        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        // Edit Button
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFEBF5FF), CircleShape)
        ) {
            Icon(
                Icons.Outlined.Edit,
                contentDescription = "Edit",
                tint = PrimaryBlue,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(name : String , email : String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Avatar Placeholder (Box with color to mimic the image background)
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(AvatarPink),
            contentAlignment = Alignment.BottomCenter
        ) {
            // In a real app, use Image() here.
            // Using a simple Icon as a placeholder for the illustration.
            Icon(
                Icons.Outlined.Person,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = PrimaryBlue.copy(alpha = 0.6f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Text(
            text = email,
            fontSize = 14.sp,
            color = TextGray
        )
    }
}

@Composable
fun StatsCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(label = "Reward Points", value = "360", modifier = Modifier.weight(1f))
            VerticalDivider(modifier = Modifier.height(40.dp), thickness = 1.dp, color = Color(0xFFF0F0F0))
            StatItem(label = "Travel Trips", value = "238", modifier = Modifier.weight(1f))
            VerticalDivider(modifier = Modifier.height(40.dp), thickness = 1.dp, color = Color(0xFFF0F0F0))
            StatItem(label = "Bucket List", value = "473", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
    }
}

@Composable
fun MenuSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            MenuItem(icon = Icons.Outlined.Person, title = "Profile")
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF8F9FA))
            MenuItem(icon = Icons.Outlined.BookmarkBorder, title = "Bookmarked")
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF8F9FA))
            MenuItem(icon = Icons.Outlined.Public, title = "Previous Trips") // Mimics the globe/plane icon
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF8F9FA))
            MenuItem(icon = Icons.Outlined.Settings, title = "Settings")
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFF8F9FA))
            MenuItem(icon = Icons.Outlined.Language, title = "Version")
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = TextDark
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = TextGray
        )
    }
}

