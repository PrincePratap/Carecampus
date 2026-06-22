package org.parowings.screens.common

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.parowings.screens.home.DarkGray

@Composable
fun CustomBottomNavigation() {
    Surface(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(36.dp),
        color = Color(0xFFF8F8F8),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Selected Item
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(DarkGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.GridView, contentDescription = null, tint = Color.White)
            }

//            Icon(Icons.Default.Bone, contentDescription = null, tint = Color.LightGray)
            Icon(Icons.Default.Pets, contentDescription = null, tint = Color.LightGray)
            Icon(Icons.Rounded.Settings, contentDescription = null, tint = Color.LightGray)
        }
    }
}