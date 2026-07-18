package org.parowings.screens.common.HomeItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.EmergencyRed
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
 fun LatestRescueCasesSection() {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Latest rescue cases", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextDark)
            Text(text = "See all", color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            RescueCaseCard(
                "Injured street dog", "Lajpat Nagar · 1.2 km", "Emergency",
                Color(0xFFA5D6A7), Modifier.weight(1f)
            )
            RescueCaseCard(
                "Kitten, abandoned", "Saket · 2.4 km", "Pending",
                Color(0xFFFFCC80), Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun RescueCaseCard(title: String, location: String, status: String, topColor: Color, modifier: Modifier) {
    Card(
        modifier = modifier.height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(90.dp).background(topColor), contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.Pets, contentDescription = null, modifier = Modifier.size(32.dp), tint = TextDark.copy(alpha = 0.6f))
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                Text(text = location, fontSize = 10.sp, color = TextGray)
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (status == "Emergency") Color(0xFFFFEBEE) else Color(0xFFFFF3E0)
                ) {
                    Text(
                        text = status,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (status == "Emergency") EmergencyRed else PrimaryOrange
                    )
                }
            }
        }
    }
}