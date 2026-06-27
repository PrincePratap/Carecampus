package org.parowings.screens.pet.addPet




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

// --- Colors ---
val HeaderBlue = Color(0xFF4D8AF0)
val BackgroundGray = Color(0xFFF7F8FA)
val ItemIconBackground = Color(0xFFE6EFFF)
val SuccessGreen = Color(0xFF4CAF50)
val TextGray = Color(0xFF9E9E9E)

// --- Custom Curved Shape for Header ---
val CurvedBottomShape = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height * 0.7f)
    quadraticBezierTo(
        size.width / 2f, size.height,
        0f, size.height * 0.7f
    )
    close()
}

@Preview(showBackground = true)
@Composable
fun AddPetScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
                // Blue Curved Background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(CurvedBottomShape)
                        .background(HeaderBlue)
                )

                // Top Bar Actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 45.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                    Text(
                        text = "Edit",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                // Profile Image & Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(130.dp)
                ) {
                    // Profile Picture
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        color = Color.LightGray
                    ) {
                        // Replace with painterResource(id = R.drawable.your_image)
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.padding(20.dp),
                            tint = Color.Gray
                        )
                    }

                    // Verified Badge
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(x = 5.dp, y = (-5).dp)
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(SuccessGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Verified",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Info
            Text(
                text = "ABIMBOLA",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "abims2642@gmail.com",
                fontSize = 16.sp,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Menu Items List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileMenuItem(
                    icon = Icons.Outlined.Person,
                    label = "My Profile"
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Email,
                    label = "Messages",
                    badgeCount = 2
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Settings,
                    label = "Settings"
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Description,
                    label = "Terms & Privacy Policy"
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))

            // Logout Button
            Row(
                modifier = Modifier.padding(bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null,
                    tint = TextGray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Logout",
                    color = TextGray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    badgeCount: Int? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = BackgroundGray,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Background
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ItemIconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = HeaderBlue,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = label,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )

            if (badgeCount != null) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(SuccessGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = badgeCount.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

