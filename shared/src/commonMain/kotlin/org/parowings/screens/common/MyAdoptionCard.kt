package org.parowings.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.common.adoption.MyAdoptionResponse
import org.parowings.screens.pet.myPets.PetCardContainer
import org.parowings.theming.AppYellow
import kotlin.text.ifEmpty

@Composable
 fun MyAdoptionCard(
    adoption: MyAdoptionResponse,
    onClick: () -> Unit
) {
    PetCardContainer {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF3F4F6)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = adoption.animalName.take(1).ifEmpty { "P" },
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = adoption.animalName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = adoption.animalType,
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = adoption.city,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = adoption.adoptionStatus,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppYellow
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${adoption.gender} • ${adoption.breed}",
                fontSize = 11.sp,
                color = Color.Gray
            )

            Text(
                text = "Vaccinated: ${adoption.vaccinated} | Sterilized: ${adoption.sterilized}",
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
    }
}