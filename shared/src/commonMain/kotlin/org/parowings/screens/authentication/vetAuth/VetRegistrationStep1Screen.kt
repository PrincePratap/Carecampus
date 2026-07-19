package org.parowings.screens.authentication.vetAuth

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
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
import org.parowings.theming.*

@Composable
fun VetRegistrationStep1Screen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var doctorName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var createPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            VetRegistrationTopBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            VetRegistrationProgress(progress = 1f / 6f)

            Spacer(modifier = Modifier.height(16.dp))

            VetRegistrationStepText(step = 1, totalSteps = 6, title = "Personal details")

            Spacer(modifier = Modifier.height(24.dp))

            VetInputField(
                value = doctorName,
                onValueChange = { doctorName = it },
                placeholder = "Doctor name",
                leadingIcon = Icons.Outlined.Person
            )
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = emailAddress,
                onValueChange = { emailAddress = it },
                placeholder = "Email address",
                leadingIcon = Icons.Outlined.Email
            )
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = "Phone number",
                leadingIcon = Icons.Outlined.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = createPassword,
                onValueChange = { createPassword = it },
                placeholder = "Create password",
                leadingIcon = Icons.Outlined.Lock
            )
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirm password",
                leadingIcon = Icons.Outlined.Lock
            )

            Spacer(modifier = Modifier.height(32.dp))

            VetContinueButton(onClick = onContinueClick)
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun VetRegistrationTopBar(onBackClick: () -> Unit) {
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
            text = "Veterinarian\nregistration",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark,
            modifier = Modifier.align(Alignment.Center),
            lineHeight = 24.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun VetRegistrationProgress(progress: Float) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp),
        color = PrimaryGreen,
        trackColor = PrimaryGreen.copy(alpha = 0.1f),
        strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
    )
}

@Composable
fun VetRegistrationStepText(step: Int, totalSteps: Int, title: String) {
    Row {
        Text(
            text = "Step ",
            fontSize = 13.sp,
            color = TextGray
        )
        Text(
            text = "$step ",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryGreen
        )
        Text(
            text = "of $totalSteps · $title",
            fontSize = 13.sp,
            color = TextGray
        )
    }
}

@Composable
fun VetContinueButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
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
                text = "Continue",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun VetInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
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
                imageVector = leadingIcon,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier.weight(1f)) {
                if (value.isEmpty()) {
                    Text(text = placeholder, color = TextGray, fontSize = 15.sp)
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = TextDark,
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
