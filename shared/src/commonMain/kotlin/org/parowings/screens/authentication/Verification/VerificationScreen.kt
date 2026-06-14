package org.parowings.screens.authentication.Verification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import org.parowings.theming.DarkText
import org.parowings.theming.PrimaryOrange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Verification",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkText
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = DarkText)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Heading
            Text(
                text = "Enter your",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Text(
                text = "Verification Code",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(32.dp))

            // OTP Input Boxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val otpCode = listOf("5", "3", "0", "|") // Pipe represents the cursor
                otpCode.forEach { char ->
                    OtpBox(text = char)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Timer
            Text(
                text = "04:59",
                color = PrimaryOrange,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description Text with highlighted email
            val descriptionText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = DarkText)) {
                    append("We send verification code to your email ")
                }
                withStyle(style = SpanStyle(color = PrimaryOrange)) {
                    append("john*****@gmail.com")
                }
                withStyle(style = SpanStyle(color = DarkText)) {
                    append(". You can check your inbox.")
                }
            }
            Text(
                text = descriptionText,
                lineHeight = 22.sp,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Resend link
            Text(
                text = "I didn't received the code? Send again",
                color = PrimaryOrange,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Verify Button
            Button(
                onClick = { /* Handle verify */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
            ) {
                Text(
                    text = "Verify",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun OtpBox(text: String) {
    Surface(
        modifier = Modifier.size(width = 72.dp, height = 64.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, PrimaryOrange),
        color = Color.White
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (text == "|") Color.LightGray else PrimaryOrange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationPreview() {
    MaterialTheme {
        VerificationScreen()
    }
}