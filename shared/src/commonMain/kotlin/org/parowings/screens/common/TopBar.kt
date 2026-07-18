package org.parowings.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.parowings.theming.PrimaryBlue
import org.parowings.theming.TextDark

@Composable
fun TopBar() {
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