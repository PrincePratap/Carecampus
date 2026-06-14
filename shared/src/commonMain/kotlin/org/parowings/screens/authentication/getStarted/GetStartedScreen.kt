package org.parowings.screens.authentication.getStarted

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define the custom colors from the image
val BrandPurple = Color(0xFF7265FF)
val BrandYellow = Color(0xFFFFD147)
val CircleCream = Color(0xFFEBE7D5)

@Composable
fun GetStartedScreen(
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}

    ) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BrandPurple
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Title Text
            Text(
                text = "Let's get started!",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // Illustration Placeholder
            // In a real app, replace this Box with an Image() or LottieAnimation()
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .background(CircleCream, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Mimicking the sitting character with a simple placeholder icon or text
                Text(
                    text = "🎨", // Placeholder for the artist illustration
                    fontSize = 120.sp
                )

                // Small grey dots within the circle to match the design
                Box(
                    modifier = Modifier
                        .offset(x = 40.dp, y = (-50).dp)
                        .size(30.dp)
                        .background(Color.LightGray.copy(alpha = 0.6f), CircleShape)
                )
            }

            // Bottom Section: Button and Login Link
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onSignUpClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandYellow),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Text with clickable part
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.8f))) {
                        append("Already have an account? ")
                    }
                    withStyle(style = SpanStyle(color = BrandYellow, fontWeight = FontWeight.Bold)) {
                        append("Log In")
                    }
                }

                Text(
                    text = annotatedString,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        onLoginClick()
                    }

                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun GetStartedPreview() {
    MaterialTheme {
        GetStartedScreen()
    }
}