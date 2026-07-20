package org.parowings.screens.pets.addPets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

@Composable
fun AddPetScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    var petName by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var microchip by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            AddPetTopBar(onBackClick = onBackClick)
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
            Spacer(modifier = Modifier.height(16.dp))
            
            // Add Photo Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .clickable { /* Handle photo upload */ },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val dashWidth = 10f
                    val dashGap = 10f
                    drawRoundRect(
                        color = TextGray.copy(alpha = 0.2f),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)
                        ),
                        cornerRadius = CornerRadius(24.dp.toPx())
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Outlined.AddPhotoAlternate,
                        contentDescription = null,
                        tint = TextGray.copy(alpha = 0.6f),
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Add pet photo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Text(
                        text = "PNG or JPG up to 5MB",
                        fontSize = 13.sp,
                        color = TextGray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Form Fields
            AddPetTextField(
                value = petName,
                onValueChange = { petName = it },
                label = "Pet name",
                icon = Icons.Outlined.Pets
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    AddPetTextField(
                        value = species,
                        onValueChange = { species = it },
                        label = "Species",
                        icon = Icons.Outlined.SentimentSatisfiedAlt
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    AddPetTextField(
                        value = breed,
                        onValueChange = { breed = it },
                        label = "Breed",
                        icon = Icons.Outlined.LocalOffer
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    AddPetTextField(
                        value = gender,
                        onValueChange = { gender = it },
                        label = "Gender"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    AddPetTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = "Age",
                        icon = Icons.Outlined.CalendarToday
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    AddPetTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = "Weight (kg)",
                        icon = Icons.Outlined.MonitorWeight
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    AddPetTextField(
                        value = color,
                        onValueChange = { color = it },
                        label = "Color",
                        icon = Icons.Outlined.Palette
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AddPetTextField(
                value = dob,
                onValueChange = { dob = it },
                label = "Date of birth",
                icon = Icons.Outlined.Cake
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AddPetTextField(
                value = microchip,
                onValueChange = { microchip = it },
                label = "Microchip number (optional)",
                icon = Icons.Outlined.QrCodeScanner
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AddPetTextField(
                value = notes,
                onValueChange = { notes = it },
                label = "Medical conditions, allergies, or notes",
                icon = Icons.Outlined.Description,
                singleLine = false,
                minLines = 4
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Save pet",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Check,
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
fun AddPetTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            onClick = onBackClick,
            shape = CircleShape,
            color = Color.White,
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.CenterStart),
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
            text = "Add a pet",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
    }
}

@Composable
fun AddPetTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector? = null,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = label, color = TextGray.copy(alpha = 0.8f), fontSize = 15.sp) },
            leadingIcon = if (icon != null) {
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = TextGray.copy(alpha = 0.4f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = PrimaryGreen
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = singleLine,
            minLines = minLines,
            textStyle = LocalTextStyle.current.copy(fontSize = 15.sp, color = TextDark)
        )
    }
}
