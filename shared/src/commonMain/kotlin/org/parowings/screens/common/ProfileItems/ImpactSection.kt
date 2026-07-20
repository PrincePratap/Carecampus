package org.parowings.screens.common.ProfileItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
 fun ImpactSection() {
    Column {
        Text(
            text = "Your impact this year",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val months = listOf("Feb", "Mar", "Apr", "May", "Jun", "Jul")
                    val heights = listOf(0.4f, 0.6f, 0.8f, 0.5f, 0.7f, 1f)
                    val colors = listOf(PrimaryGreen, PrimaryGreen, PrimaryOrange, PrimaryGreen, PrimaryGreen, PrimaryOrange)

                    months.forEachIndexed { index, month ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .fillMaxHeight(heights[index])
                                    .background(colors[index], RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = month, fontSize = 10.sp, color = TextGray)
                        }
                    }
                }
            }
        }
    }
}