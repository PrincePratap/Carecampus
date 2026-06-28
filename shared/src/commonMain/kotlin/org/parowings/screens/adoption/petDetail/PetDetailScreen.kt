package org.parowings.screens.adoption.petDetail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.common.adoption.AdoptionResponse
import org.parowings.theming.DarkButton

private val DetailBackground = Color(0xFFF4F7FB)
private val DetailCardBackground = Color.White
private val DetailMutedText = Color(0xFF64748B)

@Composable
@Preview(showBackground = true)
fun PetDetailScreen(
    adoption: AdoptionResponse? = null,
    isLoading: Boolean = false,
    error: String? = null,
    onBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DetailBackground)
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 16.sp
                    )
                }
            }

            adoption != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }

                        Text(
                            text = "Adoption Details",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.width(48.dp))
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(220.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(112.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE5E7EB)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = adoption.animalName.take(1).ifEmpty { "P" },
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DetailMutedText
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = adoption.photoUrl,
                                fontSize = 12.sp,
                                color = DetailMutedText
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                        color = DetailCardBackground
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = adoption.animalName,
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = null,
                                            tint = DetailMutedText,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = "${adoption.city}, ${adoption.state}",
                                            color = DetailMutedText,
                                            fontSize = 14.sp
                                        )
                                    }
                                }

                                StatusChip(adoption.adoptionStatus)
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            InfoRow("Animal Type", adoption.animalType)
                            InfoRow("Breed", adoption.breed)
                            InfoRow("Gender", adoption.gender)
                            InfoRow("Age", adoption.age.toString())
                            InfoRow("Vaccinated", adoption.vaccinated.toYesNo())
                            InfoRow("Sterilized", adoption.sterilized.toYesNo())
                            InfoRow("Owner Name", adoption.ownerName)
                            InfoRow("Owner ID", adoption.ownerId)
                            InfoRow("Contact Number", adoption.contactNumber)
                            InfoRow("Created At", adoption.createdAt)
                            InfoRow("Adoption ID", adoption.id)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Description",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = adoption.description,
                                color = DetailMutedText,
                                lineHeight = 20.sp,
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = onBack,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = DarkButton),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    text = "Back",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(bottom = 14.dp)) {
        Text(
            text = label,
            color = DetailMutedText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Divider(modifier = Modifier.padding(top = 12.dp), color = Color(0xFFE5E7EB))
    }
}

@Composable
private fun StatusChip(status: String) {
    Surface(
        color = Color(0xFFE0F2FE),
        shape = RoundedCornerShape(999.dp)
    ) {
        Text(
            text = status,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            color = Color(0xFF0369A1),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

private fun Boolean.toYesNo(): String = if (this) "Yes" else "No"
