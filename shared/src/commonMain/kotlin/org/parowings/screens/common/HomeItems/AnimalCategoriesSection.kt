package org.parowings.screens.common.HomeItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.TextDark

data class AnimalCategory(
    val label: String,
    val icon: ImageVector
)

@Composable
fun AnimalCategoriesSection(
    selectedCategory: String = "Dogs",
    onCategoryClick: (String) -> Unit = {}
) {

    val categories = listOf(
        AnimalCategory("Dogs", Icons.Outlined.Pets),
        AnimalCategory("Cats", Icons.Outlined.Pets),
        AnimalCategory("Birds", Icons.Outlined.Flight),
        AnimalCategory("Cows", Icons.Outlined.Pets)
    )

    Column {
        Text(
            text = "Animal categories",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(categories) { category ->
                CategoryChip(
                    label = category.label,
                    icon = category.icon,
                    isSelected = selectedCategory == category.label,
                    onClick = {
                        onCategoryClick(category.label)
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) PrimaryGreen else Color.White,
        border = if (isSelected) null
        else BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) Color.White else TextDark,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = label,
                color = if (isSelected) Color.White else TextDark,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}