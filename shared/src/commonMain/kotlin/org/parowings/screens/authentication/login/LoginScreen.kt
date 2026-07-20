package org.parowings.screens.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*
import org.parowings.screens.common.ProfileItems.EditField

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
    onGuestLoginClick: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+\$".toRegex()

    Scaffold(
        containerColor = BackgroundGray
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // Logo
            Surface(
                modifier = Modifier.size(64.dp),
                color = PrimaryGreen,
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Pets,
                        contentDescription = "Logo",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Welcome Text
            Text(
                text = "Welcome back",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Text(
                text = "Log in to keep helping animals near you.",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Input Fields
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
                    imeAction = ImeAction.Next
                )
                if (emailError != null) {
                    Text(
                        text = emailError!!,
                        color = ErrorRed,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column {
                EditField(
                    placeholder = "Password",
                    value = password,
                    onValueChange = { 
                        password = it
                        passwordError = null
                    },
                    leadingIcon = Icons.Outlined.Lock,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
                if (passwordError != null) {
                    Text(
                        text = passwordError!!,
                        color = ErrorRed,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Forgot Password
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Key,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Forgot password?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen,
                    modifier = Modifier.clickable { onForgotPasswordClick() }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Log in Button
            Button(
                onClick = {
                    emailError = null
                    passwordError = null
                    
                    if (email.isEmpty()) emailError = "Email cannot be empty"
                    else if (!email.matches(emailRegex)) emailError = "Invalid email format"
                    
                    if (password.isEmpty()) passwordError = "Password cannot be empty"
                    else if (password.length < 8) passwordError = "Minimum 8 characters required"

                    if (emailError == null && passwordError == null) {
                        onLoginClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Log in",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = DividerColor)
                Text(
                    text = "or continue with",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 14.sp,
                    color = TextGray
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = DividerColor)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Social Buttons
            SocialLoginButton(
                text = "Continue with Google",
                icon = Icons.Outlined.AccountCircle,
                onClick = onGoogleLoginClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            SocialLoginButton(
                text = "Continue as guest",
                icon = Icons.Outlined.Person,
                onClick = onGuestLoginClick
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "New to Paro Wings? ", color = TextGray, fontSize = 14.sp)
                Text(
                    text = "Create an account",
                    color = PrimaryGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onCreateAccountClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SocialLoginButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
        }
    }
}
