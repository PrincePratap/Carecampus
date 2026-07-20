package org.parowings.screens.authentication.forgotPassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*
import org.parowings.screens.common.ProfileItems.EditField

@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit = {},
    onSendCodeClick: (String) -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+\$".toRegex()

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Surface(
                    onClick = onBackClick,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    modifier = Modifier.size(44.dp),
                    shadowElevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(20.dp),
                            tint = TextDark
                        )
                    }
                }
                Text(
                    text = "Forgot password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Key Icon
            Surface(
                modifier = Modifier.size(80.dp),
                color = PrimaryGreen.copy(alpha = 0.1f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Key,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Description
            Text(
                text = "Enter the email linked to your account and we'll send you a 6-digit code to reset your password.",
                fontSize = 15.sp,
                color = TextGray,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            Column {
                EditField(
                    placeholder = "Email address",
                    value = email,
                    onValueChange = { 
                        email = it
                        emailError = null
                    },
                    leadingIcon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
                emailError?.let {
                    Text(
                        text = it,
                        color = ErrorRed,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Send Reset Code Button
            Button(
                onClick = { 
                    if (email.isEmpty()) {
                        emailError = "Email is required"
                    } else if (!email.matches(emailRegex)) {
                        emailError = "Invalid email format"
                    } else {
                        onSendCodeClick(email)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Send reset code",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
