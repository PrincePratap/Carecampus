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
fun VetRegistrationStep2Screen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var clinicName by remember { mutableStateOf("") }
    var clinicAddress by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }

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

            VetRegistrationProgress(progress = 2f / 6f)

            Spacer(modifier = Modifier.height(16.dp))

            VetRegistrationStepText(step = 2, totalSteps = 6, title = "Clinic information")

            Spacer(modifier = Modifier.height(24.dp))

            VetInputField(
                value = clinicName,
                onValueChange = { clinicName = it },
                placeholder = "Clinic / hospital name",
                leadingIcon = Icons.Outlined.Apartment
            )
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = clinicAddress,
                onValueChange = { clinicAddress = it },
                placeholder = "Clinic address",
                leadingIcon = Icons.Outlined.LocationOn
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                VetInputField(
                    value = city,
                    onValueChange = { city = it },
                    placeholder = "City",
                    leadingIcon = Icons.Outlined.Map,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                VetInputField(
                    value = state,
                    onValueChange = { state = it },
                    placeholder = "State",
                    leadingIcon = Icons.Outlined.Map,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            VetInputField(
                value = pinCode,
                onValueChange = { pinCode = it },
                placeholder = "PIN code",
                leadingIcon = Icons.Outlined.Tag
            )

            Spacer(modifier = Modifier.height(32.dp))

            VetContinueButton(onClick = onContinueClick)
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
