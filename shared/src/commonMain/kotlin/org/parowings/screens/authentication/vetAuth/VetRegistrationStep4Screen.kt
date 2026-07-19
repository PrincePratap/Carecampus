package org.parowings.screens.authentication.vetAuth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.parowings.theming.*

@Composable
fun VetRegistrationStep4Screen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var specialization by remember { mutableStateOf("") }

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

            VetRegistrationProgress(progress = 4f / 6f)

            Spacer(modifier = Modifier.height(16.dp))

            VetRegistrationStepText(step = 4, totalSteps = 6, title = "Specialization")

            Spacer(modifier = Modifier.height(24.dp))

            VetInputField(
                value = specialization,
                onValueChange = { specialization = it },
                placeholder = "Specialization",
                leadingIcon = Icons.Outlined.HealthAndSafety
            )
            Spacer(modifier = Modifier.height(24.dp))

            VetDocumentUploadField(
                title = "Upload clinic photo",
                subtitle = "PNG or JPG up to 5MB",
                icon = Icons.Outlined.AddPhotoAlternate
            )

            Spacer(modifier = Modifier.height(32.dp))

            VetContinueButton(onClick = onContinueClick)
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun VetRegistrationStep4Preview() {
    VetRegistrationStep4Screen()
}
