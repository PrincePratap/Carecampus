package org.parowings.screens.userProfile.emergencyContacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

/**
 * Preview for the Emergency Contacts Screen.
 */
@Composable
fun EmergencyContactsScreenPreview() {
    MaterialTheme {
        EmergencyContactsScreen()
    }
}

@Composable
fun EmergencyContactsScreen(
    onBackClick: () -> Unit = {},
    onAddContactClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            EmergencyContactsTopBar(onBackClick = onBackClick, onAddClick = onAddContactClick)
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

            EmergencyContactItem(
                name = "Dr. Anil Kapoor",
                role = "Family veterinarian",
                phone = "+91 98100 22334",
                icon = Icons.Outlined.MedicalServices
            )
            Spacer(modifier = Modifier.height(16.dp))

            EmergencyContactItem(
                name = "Paws of Delhi NGO",
                role = "Rescue helpline",
                phone = "+91 11 4567 8901",
                icon = Icons.Outlined.Apartment
            )
            Spacer(modifier = Modifier.height(16.dp))

            EmergencyContactItem(
                name = "Rhea Rathi",
                role = "Family member",
                phone = "+91 99887 66554",
                icon = Icons.Outlined.Person
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun EmergencyContactsTopBar(onBackClick: () -> Unit, onAddClick: () -> Unit) {
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
            text = "Emergency contacts",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )

        Surface(
            onClick = onAddClick,
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.size(48.dp).align(Alignment.CenterEnd),
            shadowElevation = 1.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(24.dp),
                    tint = TextDark
                )
            }
        }
    }
}

@Composable
fun EmergencyContactItem(
    name: String,
    role: String,
    phone: String,
    icon: ImageVector
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon with light green background
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = PrimaryGreen.copy(alpha = 0.1f),
                modifier = Modifier.size(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = "$role · $phone",
                    fontSize = 13.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
            }
            
            // Action Buttons: Call and Delete
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    modifier = Modifier.size(40.dp),
                    shadowElevation = 1.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Call",
                            tint = TextDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(10.dp))
                
                Surface(
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    modifier = Modifier.size(40.dp),
                    shadowElevation = 1.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = EmergencyRed,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
