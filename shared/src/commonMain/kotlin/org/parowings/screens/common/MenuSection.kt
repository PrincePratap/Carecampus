package org.parowings.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray
import androidx.compose.foundation.clickable
import org.parowings.theming.BackgroundLight


@Composable
fun MenuSection(
    onProfileClick: () -> Unit = {},
    onAdoptionsClick: () -> Unit = {},
    onRescueReportsClick: () -> Unit = {},
    onTrainingClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onTermsClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = BackgroundLight),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            MenuItem(Icons.Outlined.Person, "Profile", onProfileClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.Pets, "My Adoptions", onAdoptionsClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.Campaign, "Rescue Reports", onRescueReportsClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.School, "Training Bookings", onTrainingClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.FavoriteBorder, "Favorites", onFavoritesClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.NotificationsNone, "Notifications", onNotificationsClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.SupportAgent, "Help & Support", onHelpClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.PrivacyTip, "Privacy Policy", onPrivacyClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            MenuItem(Icons.Outlined.Description, "Terms & Conditions", onTermsClick)
        }
    }
}


@Composable
fun MenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
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