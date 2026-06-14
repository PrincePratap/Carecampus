package org.parowings.screens.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.screens.authentication.getStarted.BrandPurple
import org.parowings.screens.authentication.getStarted.BrandYellow

// Custom Colors from the image

val InputBackground = Color(0xFFF5F5F5)
val SocialIconBg = Color(0xFFF8F8F8)

@Composable
fun SignUpScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandPurple)
    ) {
        // --- Header Section ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Surface(
                    modifier = Modifier.size(36.dp),
                    color = BrandYellow,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Illustration Placeholder
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(Color(0xFFEBE7D5), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // In a real app, use Image() here.
                // Using emoji as a placeholder for the preview.
                Text("🎨", fontSize = 60.sp)
            }
        }

        // --- Form Section ---
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp), // Adjust based on header height
            color = Color.White,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomInputField(label = "Full name", value = "Taras Shevchenko")
                Spacer(modifier = Modifier.height(20.dp))

                CustomInputField(label = "Email adress", value = "sheva88228@mail.com")
                Spacer(modifier = Modifier.height(20.dp))

                CustomInputField(
                    label = "Password",
                    value = "••••••••••••••",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Sign Up Button
                Button(
                    onClick = { /* Handle Sign Up */ },
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
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Or", color = Color.Black, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(24.dp))

                // Social Icons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SocialButton("G") // Google Placeholder
                    Spacer(modifier = Modifier.width(20.dp))
                    SocialButton("") // Apple Placeholder
                    Spacer(modifier = Modifier.width(20.dp))
                    SocialButton("f") // Facebook Placeholder
                }

                Spacer(modifier = Modifier.weight(1f))

                // Footer Text
                val footerText = buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(style = SpanStyle(color = BrandYellow, fontWeight = FontWeight.Bold)) {
                        append("Log In")
                    }
                }
                Text(text = footerText, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun CustomInputField(label: String, value: String, isPassword: Boolean = false) {
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
            color = InputBackground,
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
                        Icons.Default.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun SocialButton(symbol: String) {
    Surface(
        modifier = Modifier.size(64.dp),
        color = SocialIconBg,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = symbol, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    MaterialTheme {
        SignUpScreen()
    }
}