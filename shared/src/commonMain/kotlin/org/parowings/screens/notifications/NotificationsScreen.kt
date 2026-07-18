package org.parowings.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.BackgroundGray
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

data class NotificationData(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBgColor: Color
)

@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit = {}
) {
    val todayNotifications = listOf(
        NotificationData(
            id = 1,
            title = "Rescuer accepted your report",
            description = "A volunteer is heading to Lajpat Nagar now.",
            time = "10 min ago",
            icon = Icons.Default.LocalHospital,
            iconColor = Color(0xFFE57373),
            iconBgColor = Color(0xFFFFEBEE)
        ),
        NotificationData(
            id = 2,
            title = "Adoption request approved",
            description = "Your request to adopt Simba was approved.",
            time = "2 hours ago",
            icon = Icons.Default.FavoriteBorder,
            iconColor = Color(0xFF81C784),
            iconBgColor = Color(0xFFE8F5E9)
        )
    )

    val earlierNotifications = listOf(
        NotificationData(
            id = 3,
            title = "Donation received",
            description = "Thanks for giving ₹500 to the monsoon feeding drive.",
            time = "Yesterday",
            icon = Icons.Default.VolunteerActivism,
            iconColor = Color(0xFFFFB74D),
            iconBgColor = Color(0xFFFFF3E0)
        )
    )

    Scaffold(
        containerColor = BackgroundGray,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Surface(
                    onClick = onBackClick,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    modifier = Modifier.size(48.dp),
                    shadowElevation = 2.dp
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
                    text = "Notifications",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(32.dp)) }

            item {
                SectionHeader("TODAY")
            }

            items(todayNotifications) { notification ->
                NotificationItemView(notification)
                HorizontalDivider(
                    modifier = Modifier.padding(start = 64.dp, top = 8.dp, bottom = 8.dp),
                    thickness = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.3f)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("EARLIER")
            }

            items(earlierNotifications) { notification ->
                NotificationItemView(notification)
                HorizontalDivider(
                    modifier = Modifier.padding(start = 64.dp, top = 8.dp, bottom = 8.dp),
                    thickness = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = TextGray.copy(alpha = 0.6f),
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun NotificationItemView(notification: NotificationData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(notification.iconBgColor, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = notification.icon,
                contentDescription = null,
                tint = notification.iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = notification.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification.description,
                fontSize = 14.sp,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification.time,
                fontSize = 12.sp,
                color = TextGray.copy(alpha = 0.7f)
            )
        }
    }
}
