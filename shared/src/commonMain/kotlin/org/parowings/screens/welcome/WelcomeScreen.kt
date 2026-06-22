package org.parowings.screens.welcome

import androidx.compose.foundation.layout.width
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import parowings.shared.generated.resources.Res
import parowings.shared.generated.resources.google



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Color Palette ---
val BackgroundWhite = Color(0xFFFAFAFA)
val DarkButtonColor = Color(0xFF221E2F)
val LightButtonColor = Color(0xFFCDCCE0)
val DarkText = Color(0xFF1D1B20)
val SubtitleText = Color(0xFF4A4A4A)
val LogoRed = Color(0xFFE04F33)



@Composable
fun WelcomeScreen(onGoogleClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkText)
            .padding(horizontal = 24.dp)
    ) {

        // Background Decorative Scribbles / Doodles
        BackgroundDoodles(modifier = Modifier.fillMaxSize())

        // Main Content Layer
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Top Section: Logo and Headers
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                // App Logo Placeholder
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(LogoRed, shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    // Replace with your actual vector/image resource if available
                    Text(
                        text = "S",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Welcome\nto Superlist",
                    color = DarkText,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 46.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(120.dp))

                // Taglines
                Text(
                    text = "Made for teams.",
                    color = SubtitleText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Designed for people.",
                    color = SubtitleText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            // Bottom Section: Authentication Buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Google Button
                AuthButton(
                    text = "Continue with Google",
                    backgroundColor = DarkButtonColor,
                    contentColor = Color.White,
                    iconResId = Res.drawable.google, // Replace with actual Google icon vector
                    onClick = onGoogleClick
                )

                // Apple Button
                AuthButton(
                    text = "Sign in with Apple",
                    backgroundColor = DarkButtonColor,
                    contentColor = Color.White,
                    iconResId = Res.drawable.google // Replace with actual Apple icon vector
                )

                // Email Button
                AuthButton(
                    text = "Continue with email",
                    backgroundColor = LightButtonColor,
                    contentColor = DarkText,
                    iconResId = Res.drawable.google
                )
            }
        }
    }
}

@Composable
fun AuthButton(
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    iconResId: DrawableResource? = null,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
    ) {
        iconResId?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Procedurally draws a few of the faint geometric doodles
 * seen in the background of the reference image.
 */
@Composable
fun BackgroundDoodles(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = 3.dp.toPx()
        val doodleColor = Color(0xFFE2E2E2)

        // Large Circle on Left Center
        drawCircle(
            color = doodleColor,
            radius = 60.dp.toPx(),
            center = Offset(x = 30.dp.toPx(), y = size.height * 0.45f),
            style = Stroke(width = strokeWidth)
        )

        // Small Circle Top Right
        drawCircle(
            color = doodleColor,
            radius = 20.dp.toPx(),
            center = Offset(x = size.width - 50.dp.toPx(), y = size.height * 0.38f),
            style = Stroke(width = strokeWidth)
        )

        // Cross (X) Marks
        drawLine(
            color = doodleColor,
            start = Offset(x = 90.dp.toPx(), y = size.height * 0.38f),
            end = Offset(x = 110.dp.toPx(), y = size.height * 0.41f),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = doodleColor,
            start = Offset(x = 110.dp.toPx(), y = size.height * 0.38f),
            end = Offset(x = 90.dp.toPx(), y = size.height * 0.41f),
            strokeWidth = strokeWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen()
    }
}