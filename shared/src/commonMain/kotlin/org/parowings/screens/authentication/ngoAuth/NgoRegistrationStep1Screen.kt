package org.parowings.screens.authentication.ngoAuth

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

@Composable
fun NgoRegistrationStep1Screen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var ngoName by remember { mutableStateOf("") }
    var registrationNumber by remember { mutableStateOf("") }
    var officialEmail by remember { mutableStateOf("") }
    var officialPhone by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }

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
                    text = "NGO registration",
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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Progress Bar
            LinearProgressIndicator(
                progress = { 0.2f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = PrimaryGreen,
                trackColor = PrimaryGreen.copy(alpha = 0.1f),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Step Text
            Row {
                Text(
                    text = "Step ",
                    fontSize = 13.sp,
                    color = TextGray
                )
                Text(
                    text = "1 ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
                Text(
                    text = "of 5 · Organization details",
                    fontSize = 13.sp,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Input Fields
            NgoInputField1(
                value = ngoName,
                onValueChange = { ngoName = it },
                placeholder = "NGO name",
                leadingIcon = Icons.Outlined.Business
            )
            Spacer(modifier = Modifier.height(16.dp))

            NgoInputField1(
                value = registrationNumber,
                onValueChange = { registrationNumber = it },
                placeholder = "Registration number",
                leadingIcon = Icons.Outlined.Badge
            )
            Spacer(modifier = Modifier.height(16.dp))

            NgoInputField1(
                value = officialEmail,
                onValueChange = { officialEmail = it },
                placeholder = "Official email",
                leadingIcon = Icons.Outlined.Email
            )
            Spacer(modifier = Modifier.height(16.dp))

            NgoInputField1(
                value = officialPhone,
                onValueChange = { officialPhone = it },
                placeholder = "Official phone number",
                leadingIcon = Icons.Outlined.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))

            NgoInputField1(
                value = website,
                onValueChange = { website = it },
                placeholder = "Website (optional)",
                leadingIcon = Icons.Outlined.Language
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = onContinueClick,
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
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun NgoInputField1(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector
) {
    Surface(
        modifier = Modifier
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
                // Using Text here for visual placeholder as per requested design
                Text(
                    text = value,
                    color = TextDark,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NgoRegistrationStep1ScreenPreview() {
    NgoRegistrationStep1Screen()
}
