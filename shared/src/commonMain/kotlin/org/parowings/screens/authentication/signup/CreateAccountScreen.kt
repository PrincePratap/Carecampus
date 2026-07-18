package org.parowings.screens.authentication.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.BackgroundGray
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

private val PrimaryGreen = Color(0xFF008A45)
private val LightGreen = Color(0xFFE8F5E9)

@Composable
fun CreateAccountScreen(
    onBackClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Citizen") }

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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Input Fields
            AuthInputField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full name",
                icon = Icons.Outlined.Person
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthInputField(
                value = email,
                onValueChange = { email = it },
                label = "Email address",
                icon = Icons.Outlined.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthInputField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone number",
                icon = Icons.Outlined.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthInputField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                icon = Icons.Outlined.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Role Selection
            Text(
                text = "I'm joining as a",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 2x2 Grid for Roles
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
                onClick = onCreateAccountClick,
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
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AuthInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier.weight(1f)) {
                if (value.isEmpty()) {
                    Text(text = label, color = TextGray, fontSize = 15.sp)
                }
                Text(text = value, color = TextDark, fontSize = 15.sp)
            }
        }
    }
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
        color = if (isSelected) LightGreen else Color.White,
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
