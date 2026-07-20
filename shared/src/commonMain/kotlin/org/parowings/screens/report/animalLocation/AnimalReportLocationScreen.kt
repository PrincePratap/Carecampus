package org.parowings.screens.report.animalLocation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.theming.BackgroundLight
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray
import androidx.compose.runtime.*


@Composable
fun AnimalReportLocationScreen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    val navigator = LocalNavigator.currentOrThrow
    var locationText by remember { mutableStateOf("Near Modern School, Lajpat Nagar") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Companion.CenterVertically
            ) {
                IconButton(
                    onClick = { navigator.pop() },
                    modifier = Modifier.Companion
                        .size(44.dp)
                        .background(Color.Companion.White, RoundedCornerShape(12.dp))
                        .border(
                            1.dp,
                            Color(0xFFEEEEEE),
                            androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextDark,
                        modifier = Modifier.Companion.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.Companion.weight(1f))
                Text(
                    text = "Report an animal",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Companion.ExtraBold,
                    color = TextDark
                )
                Spacer(modifier = Modifier.Companion.weight(1.3f))
            }
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            // Progress Bar
            LinearProgressIndicator(
                progress = { 1.0f },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = PrimaryGreen,
                trackColor = Color(0xFFE0E0E0)
            )

            Spacer(modifier = Modifier.Companion.height(12.dp))

            Text(
                text = "Step 3 of 3 • Location",
                fontSize = 13.sp,
                fontWeight = FontWeight.Companion.Medium,
                color = TextGray
            )

            Spacer(modifier = Modifier.Companion.height(24.dp))

            // Map Placeholder
            Box(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(24.dp))
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Companion.Center
            ) {
                // Simple map drawing representation
                Column(modifier = Modifier.Companion.fillMaxSize()) {
                    Box(
                        modifier = Modifier.Companion.fillMaxWidth().weight(1f)
                            .background(Color(0xFFDCEDC8).copy(alpha = 0.5f))
                    )
                    Box(
                        modifier = Modifier.Companion.fillMaxWidth().height(20.dp)
                            .background(Color(0xFFD1E5B9))
                    )
                    Box(
                        modifier = Modifier.Companion.fillMaxWidth().weight(1f)
                            .background(Color(0xFFDCEDC8).copy(alpha = 0.5f))
                    )
                }

                // Map Pin
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE53935),
                    modifier = Modifier.Companion.size(36.dp),
                    shadowElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Companion.Center) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = Color.Companion.White,
                            modifier = Modifier.Companion.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.Companion.height(24.dp))

            // Use Current Location Button
            OutlinedButton(
                onClick = { /* Get current location */ },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(56.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Companion.White,
                    contentColor = TextDark
                ),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE))
            ) {
                Icon(
                    imageVector = Icons.Default.MyLocation,
                    contentDescription = null,
                    modifier = Modifier.Companion.size(20.dp)
                )
                Spacer(modifier = Modifier.Companion.width(8.dp))
                Text(
                    "Use current location",
                    fontWeight = FontWeight.Companion.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.Companion.height(16.dp))

            // Location Input Field
            Surface(
                modifier = Modifier.Companion.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                color = Color.Companion.White,
                shadowElevation = 1.dp
            ) {
                TextField(
                    value = locationText,
                    onValueChange = { locationText = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = TextGray.copy(alpha = 0.4f),
                            modifier = Modifier.Companion.size(20.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Companion.Transparent,
                        unfocusedContainerColor = Color.Companion.Transparent,
                        disabledContainerColor = Color.Companion.Transparent,
                        focusedIndicatorColor = Color.Companion.Transparent,
                        unfocusedIndicatorColor = Color.Companion.Transparent,
                    ),
                    modifier = Modifier.Companion.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.Companion.weight(1f))

            // Submit Button
            Button(
                onClick = { onContinueClick() },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 8.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryOrange
                )
            ) {
                Row(
                    verticalAlignment = Alignment.Companion.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Submit report",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Companion.Bold,
                        color = Color.Companion.White
                    )
                    Spacer(modifier = Modifier.Companion.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send,
                        contentDescription = null,
                        tint = Color.Companion.White,
                        modifier = Modifier.Companion.size(20.dp)
                    )
                }
            }
        }
    }
}
