package org.parowings.screens.authentication.login



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
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
import org.parowings.screens.authentication.getStarted.BrandPurple
import org.parowings.screens.authentication.getStarted.BrandYellow

// Defining custom colors based on the image

val SoftGrayBackground = Color(0xFFF5F5F5)
val IllustrationCircle = Color(0xFFEBE7D5)

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandPurple)
    ) {
        // --- Top Header Section ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Right Arrow Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    color = BrandYellow,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Next",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Illustration Placeholder
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(160.dp)
                    .background(IllustrationCircle, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // In a real app, use an Image() here.
                // Using an emoji as a visual placeholder for the artist.
                Text("🎨", fontSize = 80.sp)

                // Decorative small dots inside the circle
                Box(
                    modifier = Modifier
                        .offset(x = 40.dp, y = (-30).dp)
                        .size(15.dp)
                        .background(Color.Gray.copy(alpha = 0.3f), CircleShape)
                )
            }
        }

        // --- Bottom Form Section ---
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 320.dp), // Leaves room for the header
            color = Color.White,
            shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Email Field
                LoginInputField(label = "Email Adress", value = "sheva88228@mail.com")

                Spacer(modifier = Modifier.height(20.dp))

                // Password Field
                LoginInputField(
                    label = "Password",
                    value = "••••••••••••••",
                    isPassword = true
                )

                // Forgot Password Link
                Text(
                    text = "Forgot Password?",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Log In Button
                Button(
                    onClick = { onLoginClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandYellow),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Log In",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Or", color = Color.Black, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(24.dp))

                // Social Login Icons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SocialLoginItem("G") // Google
                    Spacer(modifier = Modifier.width(20.dp))
                    SocialLoginItem("") // Apple
                    Spacer(modifier = Modifier.width(20.dp))
                    SocialLoginItem("f") // Facebook
                }

                Spacer(modifier = Modifier.weight(1f))

                // Footer
                val footerText = buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(style = SpanStyle(color = BrandYellow, fontWeight = FontWeight.Bold)) {
                        append("Sign Up")
                    }
                }
                Text(text = footerText, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun LoginInputField(label: String, value: String, isPassword: Boolean = false) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            color = SoftGrayBackground,
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = value, color = Color.DarkGray, fontSize = 16.sp)
                if (isPassword) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Show Password",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SocialLoginItem(symbol: String) {
    Surface(
        modifier = Modifier.size(60.dp),
        color = SoftGrayBackground,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = symbol, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun LoginPreview() {
    MaterialTheme {
        LoginScreen()
    }
}