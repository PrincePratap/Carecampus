package org.parowings.screens.report.animalCondition

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.report.animalLocation.AnimalReportLocationScreen
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.BackgroundLight
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.EmergencyRed

@Composable
fun AnimalReportConditionScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var breed by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var emergencyLevel by remember { mutableStateOf("Moderate") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextDark,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Report an animal",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark
                )
                Spacer(modifier = Modifier.weight(1.3f))
            }
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            // Progress Bar
            LinearProgressIndicator(
                progress = { 0.66f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = PrimaryGreen,
                trackColor = Color(0xFFE0E0E0)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Step 2 of 3 • Condition",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Breed Input
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                TextField(
                    value = breed,
                    onValueChange = { breed = it },
                    placeholder = { Text("Breed (if known)", color = TextGray.copy(alpha = 0.6f)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Pets,
                            contentDescription = null,
                            tint = TextGray.copy(alpha = 0.4f),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = TextDark,
                            modifier = Modifier.size(14.dp).rotate(45f)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Description Input
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(
                            "Describe what you see — injuries, behavior, surroundings",
                            color = TextGray.copy(alpha = 0.6f),
                            fontSize = 15.sp,
                            lineHeight = 20.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Description,
                            contentDescription = null,
                            tint = TextGray.copy(alpha = 0.4f),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = TextDark,
                            modifier = Modifier.size(14.dp).rotate(45f)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Emergency level",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                EmergencyChip(
                    label = "Low",
                    isSelected = emergencyLevel == "Low",
                    onClick = { emergencyLevel = "Low" },
                    modifier = Modifier.weight(1f)
                )
                EmergencyChip(
                    label = "Moderate",
                    isSelected = emergencyLevel == "Moderate",
                    onClick = { emergencyLevel = "Moderate" },
                    icon = Icons.Outlined.WarningAmber,
                    selectedColor = PrimaryOrange,
                    modifier = Modifier.weight(1.2f)
                )
                EmergencyChip(
                    label = "Critical",
                    isSelected = emergencyLevel == "Critical",
                    onClick = { emergencyLevel = "Critical" },
                    selectedColor = EmergencyRed,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Continue Button
            Button(
                onClick = { onContinueClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Continue",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    selectedColor: Color = PrimaryGreen
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) selectedColor else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFEEEEEE)),
        modifier = modifier.height(44.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null && isSelected) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = label,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) Color.White else (if (label == "Critical") EmergencyRed else TextGray)
            )
        }
    }
}


