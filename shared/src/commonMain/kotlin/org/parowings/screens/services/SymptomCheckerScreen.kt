package org.parowings.screens.services

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
fun SymptomCheckerScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedSymptoms by remember { mutableStateOf(setOf<String>()) }
    var showAnalysis by remember { mutableStateOf(false) }

    val commonSymptoms = listOf(
        "Vomiting", "Diarrhea", "Lethargy", "Loss of appetite",
        "Coughing", "Itching", "Limping", "Sneezing", "Fever"
    )

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("AI Symptom Checker", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
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
            if (!showAnalysis) {
                Text(
                    text = "What symptoms is your pet showing?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = "Select all that apply for a preliminary AI analysis.",
                    fontSize = 14.sp,
                    color = TextGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    commonSymptoms.forEach { symptom ->
                        val isSelected = selectedSymptoms.contains(symptom)
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                selectedSymptoms = if (isSelected) {
                                    selectedSymptoms - symptom
                                } else {
                                    selectedSymptoms + symptom
                                }
                            },
                            label = { Text(symptom) },
                            shape = RoundedCornerShape(20.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PrimaryGreen,
                                selectedLabelColor = Color.White
                            ),

                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Additional details (Optional)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth().height(120.dp).padding(top = 8.dp),
                    placeholder = { Text("Describe when it started, frequency, etc.", fontSize = 14.sp) },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color(0xFFE2E8F0)
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { if (selectedSymptoms.isNotEmpty()) showAnalysis = true },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                    enabled = selectedSymptoms.isNotEmpty()
                ) {
                    Icon(Icons.Outlined.Analytics, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Analyze Symptoms", fontWeight = FontWeight.Bold)
                }
            } else {
                AnalysisResults(
                    symptoms = selectedSymptoms.toList(),
                    onReset = { showAnalysis = false }
                )
            }
        }
    }
}

@Composable
private fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
//    androidx.compose.foundation.layout.FlowRow(
//        modifier = modifier,
//        horizontalArrangement = horizontalArrangement,
//        verticalArrangement = verticalArrangement,
//        content = content
//    )
}

@Composable
private fun AnalysisResults(symptoms: List<String>, onReset: () -> Unit) {
    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFFFFF3F3),
            border = BorderStroke(1.dp, Color(0xFFFFCDD2))
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(48.dp).clip(CircleShape).background(EmergencyRed.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.Warning, null, tint = EmergencyRed)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Potential Urgency", fontWeight = FontWeight.Bold, color = EmergencyRed)
                    Text("Moderate - Schedule a vet visit", fontSize = 14.sp, color = TextDark)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Possible Causes", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextDark)
        Text("Based on: ${symptoms.joinToString(", ")}", fontSize = 12.sp, color = TextGray)

        Spacer(modifier = Modifier.height(12.dp))

        CauseItem("Dietary Indiscretion", "Commonly caused by eating something unusual or spoiled food.")
        CauseItem("Mild Gastritis", "Inflammation of the stomach lining, often self-limiting but needs monitoring.")

        Spacer(modifier = Modifier.height(24.dp))

        Text("Next Steps", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextDark)
        Spacer(modifier = Modifier.height(12.dp))

        StepItem("1", "Monitor water intake to prevent dehydration.")
        StepItem("2", "Withhold food for 12 hours, then offer a bland diet.")
        StepItem("3", "Contact your vet if symptoms persist beyond 24 hours.")

        Spacer(modifier = Modifier.height(32.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFE8F5E9)
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Info, null, tint = PrimaryGreen)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "This is an AI-powered estimation and not a professional medical diagnosis.",
                    fontSize = 12.sp,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Medium
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
            Text("Start Over", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun CauseItem(title: String, description: String) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
            Text(description, fontSize = 13.sp, color = TextGray)
        }
    }
}

@Composable
private fun StepItem(number: String, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier.size(24.dp),
            shape = CircleShape,
            color = PrimaryGreen.copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(number, color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, fontSize = 14.sp, color = TextDark)
    }
}
