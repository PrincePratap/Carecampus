package org.parowings.screens.common.ProfileItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
fun MenuSection(
    onMyPetsClick: () -> Unit = {},
    onSavedAnimalsClick: () -> Unit = {},
    onMyReportsClick: () -> Unit = {},
    onMyAdoptionsClick: () -> Unit = {},
    onMyDonationsClick: () -> Unit = {},
    onAchievementsClick: () -> Unit = {},
    onCommunityClick: () -> Unit = {},
    onEmergencyContactsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Column {

        MenuItem(
            icon = Icons.Outlined.Pets,
            title = "My pets",
            onClick = onMyPetsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.BookmarkBorder,
            title = "Saved animals",
            onClick = onSavedAnimalsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.Flag,
            title = "My reports",
            onClick = onMyReportsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.FavoriteBorder,
            title = "My adoptions",
            onClick = onMyAdoptionsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.VolunteerActivism,
            title = "My donations",
            onClick = onMyDonationsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.EmojiEvents,
            title = "Achievements",
            onClick = onAchievementsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.Groups,
            title = "Community",
            onClick = onCommunityClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.Phone,
            title = "Emergency contacts",
            onClick = onEmergencyContactsClick
        )
        HorizontalDivider(color = Color(0xFFF0F0F0))

        MenuItem(
            icon = Icons.Outlined.Settings,
            title = "Settings & preferences",
            onClick = onSettingsClick
        )
    }
}

@Composable
private fun MenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = TextDark
        )

        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color(0xFFCCCCCC)
        )
    }
}