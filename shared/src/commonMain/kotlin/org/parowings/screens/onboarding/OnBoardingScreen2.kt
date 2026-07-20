package org.parowings.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.TextDark
import org.parowings.theming.BackgroundLight

object OnBoardingScreen2 : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundLight
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))

                // Illustration Placeholder
                Image(
                    imageVector = Icons.Outlined.MedicalServices,
                    contentDescription = "Rescue Illustration",
                    modifier = Modifier.size(240.dp)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Title
                Text(
                    text = "Report Animal Rescue",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = "Quickly report injured or lost animals with location and photos.",
                    fontSize = 16.sp,
                    color = TextDark.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = { navigator.pop() },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text("Back")
                    }

                    Button(
                        onClick = { navigator.push(OnBoardingScreen3) },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }
}
