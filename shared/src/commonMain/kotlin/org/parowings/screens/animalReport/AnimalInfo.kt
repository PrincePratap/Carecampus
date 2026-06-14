package org.parowings.screens.animalReport



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.screens.common.FormSection

// Defining specific colors from the image
val PrimaryPurple = Color(0xFFE0E0E0)
val LightGrayBorder = Color(0xFFE0E0E0)
val PlaceholderGray = Color(0xFF9E9E9E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalInfo() {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { /* Handle Cancel */ },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(end = 8.dp),
                    border = BorderStroke(1.dp, LightGrayBorder)
                ) {
                    Text("Cancel", color = Color.Black)
                }
                Button(
                    onClick = { /* Handle Save */ },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple)
                ) {
                    Text("Save", color = Color.White)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Event Name
            FormSection(label = "Event name") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Enter event name", color = PlaceholderGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            // Event Date
            FormSection(label = "Event date") {
                OutlinedTextField(
                    value = "July 9, 2025",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = PlaceholderGray) },
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = LightGrayBorder)
                )
            }

            // Time
            FormSection(label = "Time") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TimeDropdown(modifier = Modifier.weight(1f), time = "09:00am")
                    Text("—", color = PlaceholderGray)
                    TimeDropdown(modifier = Modifier.weight(1f), time = "10:00am")
                }
            }

            // Add Guests
            FormSection(label = "Add guests") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Add guests", color = PlaceholderGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null, tint = PlaceholderGray) },
                    trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = LightGrayBorder)
                )
            }

            // Meeting Link
            FormSection(label = "Meeting link") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Enter meeting link", color = PlaceholderGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    leadingIcon = { Icon(Icons.Outlined.Link, contentDescription = null, tint = PlaceholderGray) },
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = LightGrayBorder)
                )
            }

            // Description
            FormSection(label = "Description") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Enter description", color = PlaceholderGray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = LightGrayBorder)
                )
            }

            // Meeting Tag
            FormSection(label = "Meeting tag") {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TagCircle(Color(0xFFE3F2FD)) // Blue
                    TagCircle(Color(0xFFF1F8E9)) // Green
                    TagCircle(Color(0xFFFFF8E1), isSelected = true) // Yellow/Orange
                    TagCircle(Color(0xFFEDE7F6)) // Purple
                    TagCircle(Color(0xFFF5F5F5)) // Grey
                }
            }
        }
    }
}



@Composable
fun TimeDropdown(modifier: Modifier = Modifier, time: String) {
    OutlinedCard(
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, LightGrayBorder),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = time, fontSize = 14.sp)
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}

@Composable
fun TagCircle(color: Color, isSelected: Boolean = false) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color, CircleShape)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color(0xFFF57C00) else Color.Transparent,
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CreateEventPreview() {
    MaterialTheme {
        AnimalInfo()
    }
}