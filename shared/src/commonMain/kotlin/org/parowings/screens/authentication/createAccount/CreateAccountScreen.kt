package org.parowings.screens.authentication.createAccount

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun CreateAccountScreen(
    onBackClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var selectedRole by rememberSaveable { mutableStateOf("Citizen") }

    var fullNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var phoneError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    var confirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+\$".toRegex()

    Scaffold(
        containerColor = BackgroundGray,
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
                    modifier = Modifier.size(48.dp),
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
                    text = "Create account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Form Fields
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Column {
                    EditField(
                        placeholder = "Full Name",
                        value = fullName,
                        onValueChange = { 
                            fullName = it
                            fullNameError = null
                        },
                        leadingIcon = Icons.Outlined.Person,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                    fullNameError?.let { ErrorText(it) }
                }

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
                    emailError?.let { ErrorText(it) }
                }

                Column {
                    EditField(
                        placeholder = "Phone number",
                        value = phoneNumber,
                        onValueChange = { 
                            if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                                phoneNumber = it
                                phoneError = null
                            }
                        },
                        leadingIcon = Icons.Outlined.Phone,
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                    phoneError?.let { ErrorText(it) }
                }

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
                        imeAction = ImeAction.Next
                    )
                    passwordError?.let { ErrorText(it) }
                }

                Column {
                    EditField(
                        placeholder = "Confirm Password",
                        value = confirmPassword,
                        onValueChange = { 
                            confirmPassword = it
                            confirmPasswordError = null
                        },
                        leadingIcon = Icons.Outlined.Lock,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                    confirmPasswordError?.let { ErrorText(it) }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Role Selection (Preserving existing UI)
            Text(
                text = "I'm joining as a",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                RoleCard(
                    role = "Citizen",
                    icon = Icons.Outlined.Person,
                    isSelected = selectedRole == "Citizen",
                    onClick = { selectedRole = "Citizen" },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                RoleCard(
                    role = "NGO",
                    icon = Icons.Outlined.Apartment,
                    isSelected = selectedRole == "NGO",
                    onClick = { selectedRole = "NGO" },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                RoleCard(
                    role = "Rescuer",
                    icon = Icons.Outlined.LocalHospital,
                    isSelected = selectedRole == "Rescuer",
                    onClick = { selectedRole = "Rescuer" },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                RoleCard(
                    role = "Veterinarian",
                    icon = Icons.Outlined.MedicalServices,
                    isSelected = selectedRole == "Veterinarian",
                    onClick = { selectedRole = "Veterinarian" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Create Account Button
            Button(
                onClick = {
                    fullNameError = if (fullName.isEmpty()) "Full name is required" else null
                    emailError = when {
                        email.isEmpty() -> "Email is required"
                        !email.matches(emailRegex) -> "Invalid email format"
                        else -> null
                    }
                    phoneError = if (phoneNumber.isEmpty()) "Phone number is required" else null
                    passwordError = when {
                        password.isEmpty() -> "Password is required"
                        password.length < 8 -> "Minimum 8 characters required"
                        else -> null
                    }
                    confirmPasswordError = when {
                        confirmPassword.isEmpty() -> "Confirm password is required"
                        confirmPassword != password -> "Passwords do not match"
                        else -> null
                    }

                    if (fullNameError == null && emailError == null && phoneError == null && 
                        passwordError == null && confirmPasswordError == null) {
                        onCreateAccountClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Create account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLoginClick() }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account? ", color = TextGray, fontSize = 14.sp)
                Text(
                    text = "Login",
                    color = PrimaryGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        color = ErrorRed,
        fontSize = 12.sp,
        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
    )
}

@Composable
fun RoleCard(
    role: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) SurfaceVariant else Color.White,
        border = if (isSelected) BorderStroke(1.dp, PrimaryGreen) else null,
        shadowElevation = if (isSelected) 0.dp else 1.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) PrimaryGreen else TextGray,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = role,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
        }
    }
}
