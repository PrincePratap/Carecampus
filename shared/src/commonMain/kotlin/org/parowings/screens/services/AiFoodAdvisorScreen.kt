package org.parowings.screens.services

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

@Composable
fun AiFoodAdvisorScreen(
    onBackClick: () -> Unit = {}
) {
    var petType by remember { mutableStateOf("Dog") }
    var petWeight by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("Moderate") }
    var age by remember { mutableStateOf("") }
    var showResults by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("AI Food Advisor", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundLight
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (!showResults) {
                AdvisorForm(
                    petType = petType,
                    onPetTypeChange = { petType = it },
                    petWeight = petWeight,
                    onWeightChange = { petWeight = it },
                    activityLevel = activityLevel,
                    onActivityLevelChange = { activityLevel = it },
                    age = age,
                    onAgeChange = { age = it },
                    onGenerate = { showResults = true }
                )
            } else {
                AdvisorResults(
                    onReset = { showResults = false }
                )
            }
        }
    }
}

@Composable
private fun AdvisorForm(
    petType: String,
    onPetTypeChange: (String) -> Unit,
    petWeight: String,
    onWeightChange: (String) -> Unit,
    activityLevel: String,
    onActivityLevelChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    onGenerate: () -> Unit
) {
    Column {
        Text(
            text = "Tell us about your pet",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        Text(
            text = "Paro AI will calculate the ideal feeding plan.",
            fontSize = 14.sp,
            color = TextGray
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Pet Type", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf("Dog", "Cat", "Other").forEach { type ->
                val isSelected = petType == type
                Surface(
                    onClick = { onPetTypeChange(type) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) PrimaryGreen else Color.White,
                    border = if (!isSelected) BorderStroke(1.dp, Color(0xFFE2E8F0)) else null
                ) {
                    Text(
                        text = type,
                        modifier = Modifier.padding(vertical = 12.dp),
                        textAlign = TextAlign.Center,
                        color = if (isSelected) Color.White else TextDark,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Weight (kg)", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                OutlinedTextField(
                    value = petWeight,
                    onValueChange = onWeightChange,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = { Text("e.g. 12") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color(0xFFE2E8F0)
                    )
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("Age (years)", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                OutlinedTextField(
                    value = age,
                    onValueChange = onAgeChange,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = { Text("e.g. 3") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color(0xFFE2E8F0)
                    )
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Activity Level", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
        Column(modifier = Modifier.padding(top = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Low (Senior/Lazy)", "Moderate (Typical)", "High (Active/Working)").forEach { level ->
                val isSelected = activityLevel.contains(level.take(3))
                Surface(
                    onClick = { onActivityLevelChange(level) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) PrimaryGreen.copy(alpha = 0.05f) else Color.White,
                    border = BorderStroke(1.dp, if (isSelected) PrimaryGreen else Color(0xFFE2E8F0))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(selectedColor = PrimaryGreen)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = level, color = TextDark, fontSize = 14.sp)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Button(
            onClick = onGenerate,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Icon(Icons.Outlined.AutoAwesome, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Generate Feeding Plan", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun AdvisorResults(onReset: () -> Unit) {
    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFFE0F2F1),
            border = BorderStroke(1.dp, Color(0xFFB2DFDB))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Restaurant, null, tint = Color(0xFF00796B))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Daily Caloric Need", fontWeight = FontWeight.Bold, color = Color(0xFF00796B))
                }
                Text(
                    text = "840 - 920 kcal",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = "Based on a 12kg active adult dog",
                    fontSize = 12.sp,
                    color = Color(0xFF00796B).copy(alpha = 0.8f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Recommended Schedule", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextDark)
        Spacer(modifier = Modifier.height(12.dp))
        
        FeedingTimeItem("Morning", "8:00 AM", "450 kcal (Dry Food + Topper)")
        FeedingTimeItem("Evening", "7:00 PM", "450 kcal (Dry Food)")
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Lightbulb, null, tint = PrimaryOrange, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Paro AI Tip", fontWeight = FontWeight.Bold, color = TextDark)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "For active dogs, ensure they have access to fresh water throughout the day. Consider adding a small amount of wet food in the morning to increase hydration.",
                    fontSize = 13.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedButton(
            onClick = onReset,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, PrimaryGreen),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen)
        ) {
            Text("Recalculate", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FeedingTimeItem(timeLabel: String, time: String, detail: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).clip(CircleShape).background(PrimaryGreen.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Schedule, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(timeLabel, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                Spacer(modifier = Modifier.width(8.dp))
                Text(time, fontSize = 13.sp, color = TextGray)
            }
            Text(detail, fontSize = 13.sp, color = TextGray)
        }
    }
}
