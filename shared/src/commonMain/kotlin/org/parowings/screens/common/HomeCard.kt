package org.parowings.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.screens.home.TrainingItem
import org.parowings.theming.DarkGray

@Composable
fun HomeCard(item: TrainingItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                item.onClick()
            },

        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = item.color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Placeholder for the Dog Illustration
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = DarkGray.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = DarkGray
            )
        }
    }
}