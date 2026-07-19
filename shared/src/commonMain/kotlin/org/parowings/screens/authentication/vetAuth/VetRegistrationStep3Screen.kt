package org.parowings.screens.authentication.vetAuth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

@Composable
fun VetRegistrationStep3Screen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var licenseNumber by remember { mutableStateOf("") }
    var yearsOfExperience by remember { mutableStateOf("") }

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

            VetRegistrationProgress(progress = 3f / 6f)

            Spacer(modifier = Modifier.height(16.dp))

            VetRegistrationStepText(step = 3, totalSteps = 6, title = "Medical license")

            Spacer(modifier = Modifier.height(24.dp))

            VetInputField(
                value = licenseNumber,
                onValueChange = { licenseNumber = it },
                placeholder = "Veterinary license number",
                leadingIcon = Icons.Outlined.Badge
            )
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = yearsOfExperience,
                onValueChange = { yearsOfExperience = it },
                placeholder = "Years of experience",
                leadingIcon = Icons.Outlined.CalendarToday
            )
            Spacer(modifier = Modifier.height(24.dp))

            VetDocumentUploadField(
                title = "Upload medical license",
                subtitle = "PDF or image up to 10MB"
            )

            Spacer(modifier = Modifier.height(32.dp))

            VetContinueButton(onClick = onContinueClick)
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun VetDocumentUploadField(
    title: String,
    subtitle: String,
    icon: ImageVector = Icons.Outlined.CloudUpload,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val dashWidth = 8f
            val dashGap = 8f
            drawRoundRect(
                color = TextGray.copy(alpha = 0.3f),
                style = Stroke(
                    width = 2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)
                ),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(16.dp.toPx())
            )
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextGray
            )
        }
    }
}
