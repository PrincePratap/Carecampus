package org.parowings.screens.userProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.theming.*
import org.parowings.screens.common.ProfileItems.EditField

object EditProfile : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        EditProfileScreen(onBackClick = { navigator.pop() })
    }
}

@Composable
fun EditProfileScreen(
    onBackClick: () -> Unit = {},
    onSaveChanges: () -> Unit = {}
) {
    var fullName by rememberSaveable { mutableStateOf("Prince Rathi") }
    var email by rememberSaveable { mutableStateOf("prince.rathi@email.com") }
    var phone by rememberSaveable { mutableStateOf("+91 98765 43210") }
    var address by rememberSaveable { mutableStateOf("123 Rescue Way") }
    var city by rememberSaveable { mutableStateOf("New Delhi") }
    var state by rememberSaveable { mutableStateOf("Delhi") }
    var postalCode by rememberSaveable { mutableStateOf("110001") }
    var aboutMe by rememberSaveable { mutableStateOf("Animal lover based in Delhi. Always ready to help a stray in need.") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            EditProfileTopBar(onBackClick = onBackClick)
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
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Picture
            Box(contentAlignment = Alignment.BottomEnd) {
                Surface(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = PrimaryGreen
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "PR",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Surface(
                    modifier = Modifier.size(32.dp).offset(x = 4.dp, y = 4.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.PhotoCamera,
                            contentDescription = "Change photo",
                            tint = TextGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Change photo",
                color = PrimaryGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Form Fields
            EditField(
                placeholder = "Full Name",
                value = fullName,
                onValueChange = { fullName = it },
                leadingIcon = Icons.Outlined.Person
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            EditField(
                placeholder = "Email",
                value = email,
                onValueChange = { email = it },
                leadingIcon = Icons.Outlined.Email
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            EditField(
                placeholder = "Phone Number",
                value = phone,
                onValueChange = { phone = it },
                leadingIcon = Icons.Outlined.Phone
            )

            Spacer(modifier = Modifier.height(12.dp))

            EditField(
                placeholder = "Address",
                value = address,
                onValueChange = { address = it },
                leadingIcon = Icons.Outlined.LocationOn
            )

            Spacer(modifier = Modifier.height(12.dp))

            EditField(
                placeholder = "City",
                value = city,
                onValueChange = { city = it },
                leadingIcon = Icons.Outlined.LocationCity
            )

            Spacer(modifier = Modifier.height(12.dp))

            EditField(
                placeholder = "State",
                value = state,
                onValueChange = { state = it },
                leadingIcon = Icons.Outlined.Public
            )

            Spacer(modifier = Modifier.height(12.dp))

            EditField(
                placeholder = "Postal Code",
                value = postalCode,
                onValueChange = { postalCode = it },
                leadingIcon = Icons.Outlined.PinDrop
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            EditField(
                placeholder = "About Me",
                value = aboutMe,
                onValueChange = { aboutMe = it },
                leadingIcon = Icons.Outlined.Description,
                isMultiline = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryGreen),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onSaveChanges,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Save Changes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        }
    }
}

@Composable
fun EditProfileTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            onClick = onBackClick,
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.size(48.dp).align(Alignment.CenterStart),
            shadowElevation = 1.dp
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
            text = "Edit profile",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
    }
}
