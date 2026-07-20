package org.parowings.screens.report.reportSuccess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.BackgroundLight
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray



@Composable
fun ReportSuccessScreen(
    onBackToHome: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Success Icon Box
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        color = PrimaryGreen,
                        shape = RoundedCornerShape(36.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = "Success",
                    tint = Color.White,
                    modifier = Modifier.size(54.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "Report sent",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = "Nearby rescuers have been notified. You'll get updates as help is on the way.",
                fontSize = 17.sp,
                color = TextGray,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Back to home button
            Button(
                onClick = onBackToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(29.dp)
            ) {
                Text(
                    text = "Back to home",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
