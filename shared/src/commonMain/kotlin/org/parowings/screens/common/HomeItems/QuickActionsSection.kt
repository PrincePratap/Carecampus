package org.parowings.screens.common.HomeItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
fun QuickActionsSection(
    onReportClick: () -> Unit = {},
    onAdoptClick: () -> Unit = {},
    onDonateClick: () -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    Column {
        Text(
            text = "Quick actions",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionItem(
                icon = Icons.Outlined.PhotoCamera,
                label = "Report",
                bgColor = Color(0xFFE8F5E9),
                onClick = onReportClick
            )

            QuickActionItem(
                icon = Icons.Outlined.FavoriteBorder,
                label = "Adopt",
                bgColor = Color(0xFFFFF3E0),
                onClick = onAdoptClick
            )

            QuickActionItem(
                icon = Icons.Outlined.VolunteerActivism,
                label = "Donate",
                bgColor = Color(0xFFFFEBEE),
                onClick = onDonateClick
            )

            QuickActionItem(
                icon = Icons.Outlined.GridView,
                label = "More",
                bgColor = Color(0xFFE8F5E9),
                onClick = onMoreClick
            )
        }
    }
}

@Composable
fun QuickActionItem(
    icon: ImageVector,
    label: String,
    bgColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Surface(
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(16.dp),
            color = bgColor
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            fontSize = 12.sp,
            color = TextGray
        )
    }
}