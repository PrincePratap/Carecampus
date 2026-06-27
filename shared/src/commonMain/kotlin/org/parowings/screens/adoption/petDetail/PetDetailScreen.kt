package org.parowings.screens.adoption.petDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.DarkButton
import org.parowings.theming.LightBlueBg
import org.parowings.theming.SecondaryText

// --- Custom Colors ---
val ChipBg = Color(0xFFE9F1F7)


@Preview(showBackground = true)
@Composable
fun PetDetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueBg)
    ) {
        // --- Top Section: Image & Navigation ---
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Dog Image Container
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for Mikka's Image
                Box(
                    modifier = Modifier
                        .size(260.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
            }

            // Carousel Indicators
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 32.dp)
                    .align(Alignment.Start),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.White))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.DarkGray))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.DarkGray))
            }
        }

        // --- Bottom Section: Info Card ---
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.55f),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                // Name and Paw Heart Icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Mikka",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = SecondaryText,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "2 kms away",
                                color = SecondaryText,
                                fontSize = 14.sp
                            )
                        }
                    }
                    // Custom Paw Icon placeholder
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = LightBlueBg,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Stats Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoChip(label = "Sex", value = "Male", modifier = Modifier.weight(1f))
                    InfoChip(label = "Age", value = "1 year", modifier = Modifier.weight(1f))
                    InfoChip(label = "Weight", value = "10 kg", modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // About Section
                Text(
                    text = "About:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "There are some dogs that are naturally very intelligent. They do not need to repeat the command 100 times, because they grasp everything on the fly.",
                    color = SecondaryText,
                    lineHeight = 20.sp,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Adopt Button
                Button(
                    onClick = { /* Handle adoption */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkButton),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Adopt me",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun InfoChip(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(ChipBg)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, fontSize = 14.sp, color = Color.Black)
        Text(text = value, fontSize = 14.sp, color = SecondaryText)
    }
}

