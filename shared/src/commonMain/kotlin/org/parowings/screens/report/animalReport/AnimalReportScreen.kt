package org.parowings.screens.report.animalReport

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.BackgroundLight
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
fun AnimalReportScreen (
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
)
 {
        var selectedAnimal by remember { mutableStateOf("Dog") }

        val animalTypes = listOf(
            AnimalType("Dog", "🐶"),
            AnimalType("Cat", "🐱"),
            AnimalType("Bird", "🐦"),
            AnimalType("Cow", "🐮")
        )

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {  },
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
                    progress = { 0.33f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(CircleShape),
                    color = PrimaryGreen,
                    trackColor = Color(0xFFE0E0E0)
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "Step 1 of 3 • Photo",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextGray
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Clear photos help rescuers assess the animal's condition quickly.",
                    fontSize = 16.sp,
                    color = TextGray,
                    lineHeight = 22.sp
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Photo Upload Area (Dashed border style)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.White, RoundedCornerShape(28.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFEEEEEE),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .clickable { /* Upload Photo */ },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Icon placeholder (Add Photo Icon)
                        Icon(
                            imageVector = Icons.Outlined.PhotoLibrary,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = TextGray.copy(alpha = 0.4f)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Take or upload a photo",
                            color = TextGray,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Camera and Gallery Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = TextDark
                        ),
                        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                    ) {
                        Icon(Icons.Outlined.AddAPhoto, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Camera", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                    
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = TextDark
                        ),
                        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                    ) {
                        Icon(Icons.Outlined.PhotoLibrary, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Gallery", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Text(
                    text = "Animal type",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(animalTypes) { type ->
                        val isSelected = selectedAnimal == type.name
                        AnimalTypeChip(
                            name = type.name,
                            icon = type.icon,
                            isSelected = isSelected,
                            onClick = { selectedAnimal = type.name }
                        )
                    }
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


data class AnimalType(val name: String, val icon: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalTypeChip(
    name: String,
    icon: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) PrimaryGreen else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFEEEEEE)),
        modifier = Modifier.height(44.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = icon, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) Color.White else TextDark
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
 fun AnimalReportPreview() {
    MaterialTheme {
        AnimalReportScreen()
    }
}


