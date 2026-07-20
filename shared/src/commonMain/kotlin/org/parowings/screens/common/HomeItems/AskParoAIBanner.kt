package org.parowings.screens.common.HomeItems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.PrimaryGreen

@Composable
fun AskParoAIBanner(
    onBannerClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { onBannerClick() })
            .fillMaxWidth()
            .height(110.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(PrimaryGreen, Color(0xFF38A370))),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = 0.2f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.AutoAwesome, contentDescription = null, tint = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Ask Paro AI", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    text = "\"My dog is vomiting\" — get instant guidance",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White)
        }
    }
}
