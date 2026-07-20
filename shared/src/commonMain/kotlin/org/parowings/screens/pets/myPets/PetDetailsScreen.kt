package org.parowings.screens.pets.myPets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

@Composable
fun PetDetailsScreen(
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Section with Green Background
            PetDetailsHeader(onBackClick)

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .offset(y = (-40.dp))
            ) {
                // Top Info Card (Overlapping header)
                PetTopInfoCard()

                Spacer(modifier = Modifier.height(20.dp))

                // Tabs Section (Overview, Health, etc.)
                PetTabsRow()

                Spacer(modifier = Modifier.height(20.dp))

                // Overview Card with Pet Details
                PetOverviewCard()

                Spacer(modifier = Modifier.height(32.dp))

                // Weight Tracker Section
                WeightTrackerSection()

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun PetDetailsHeader(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                color = PrimaryGreen,
                shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
            )
            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White.copy(alpha = 0.2f),
                contentColor = Color.White
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Surface(
                onClick = { /* Edit action */ },
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White.copy(alpha = 0.2f),
                contentColor = Color.White
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // Central Pet Icon
        Icon(
            imageVector = Icons.Outlined.Pets,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun PetTopInfoCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Surface(
                        color = Color(0xFF3B5998), // Custom blue for name tag
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "Bruno",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Labrador Retriever · 2 yrs · Male · 24 kg",
                        color = TextGray,
                        fontSize = 14.sp
                    )
                }

                // "Healthy" badge
                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = PrimaryGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Healthy",
                            color = PrimaryGreen,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Status badges row
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatusBadge(label = "Vaccinated", icon = Icons.Outlined.MedicalServices, color = PrimaryGreen)
                StatusBadge(label = "Sterilized", icon = Icons.Outlined.CheckCircleOutline, color = PrimaryGreen)
            }
            Spacer(modifier = Modifier.height(8.dp))
            StatusBadge(
                label = "Next vaccine: Aug 12",
                icon = Icons.Outlined.CalendarToday,
                color = PrimaryOrange,
                bgColor = Color(0xFFFFF3E0)
            )

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = Color(0xFFF0F0F0), thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Quick Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionBtn(Icons.Outlined.FavoriteBorder, "Log health", Color(0xFFE8F5E9), PrimaryGreen)
                ActionBtn(Icons.Outlined.CalendarToday, "Schedule", Color(0xFFFFF3E0), PrimaryOrange)
                ActionBtn(Icons.Outlined.ChatBubbleOutline, "Ask vet", Color(0xFFF3E5F5), Color(0xFF9C27B0))
                ActionBtn(Icons.Outlined.Share, "Share", Color(0xFFFFEBEE), Color(0xFFE91E63))
            }
        }
    }
}

@Composable
fun StatusBadge(
    label: String,
    icon: ImageVector,
    color: Color,
    bgColor: Color = color.copy(alpha = 0.12f)
) {
    Surface(
        color = bgColor,
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ActionBtn(icon: ImageVector, label: String, bgColor: Color, iconColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(bgColor, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextGray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PetTabsRow() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.6f)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TabBtn("Overview", Icons.Outlined.GridView, isSelected = true)
            TabBtn("Health", Icons.Outlined.FavoriteBorder, isSelected = false)
            TabBtn("Schedule", Icons.Outlined.CalendarToday, isSelected = false)
            TabBtn("Docs", Icons.Outlined.Description, isSelected = false)
        }
    }
}

@Composable
fun RowScope.TabBtn(label: String, icon: ImageVector, isSelected: Boolean) {
    Surface(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) Color.White else Color.Transparent,
        shadowElevation = if (isSelected) 2.dp else 0.dp
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) PrimaryGreen else TextGray,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                color = if (isSelected) PrimaryGreen else TextGray,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
        }
    }
}

@Composable
fun PetOverviewCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = Color.White,
        shadowElevation = 0.5.dp
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                OverviewItem(modifier = Modifier.weight(1f), label = "BREED", value = "Labrador Retriever")
                OverviewItem(modifier = Modifier.weight(1f), label = "WEIGHT", value = "24 kg")
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OverviewItem(modifier = Modifier.weight(1f), label = "DATE OF BIRTH", value = "14 Mar 2024")
                OverviewItem(modifier = Modifier.weight(1f), label = "COLOR", value = "Golden")
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OverviewItem(modifier = Modifier.weight(1f), label = "MICROCHIP ID", value = "PW-MC-88213")
                OverviewItem(modifier = Modifier.weight(1f), label = "OWNER", value = "Prince Rathi")
            }
        }
    }
}

@Composable
fun OverviewItem(modifier: Modifier, label: String, value: String) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = TextGray.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            color = TextDark,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WeightTrackerSection() {
    Column {
        Text(
            text = "Weight tracker",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            color = Color.White,
            shadowElevation = 0.5.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .height(140.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                ChartBar(modifier = Modifier.weight(1f), month = "Apr", value = 0.5f, color = PrimaryGreen)
                ChartBar(modifier = Modifier.weight(1f), month = "May", value = 0.65f, color = PrimaryGreen)
                ChartBar(modifier = Modifier.weight(1f), month = "Jun", value = 0.75f, color = PrimaryGreen)
                ChartBar(modifier = Modifier.weight(1f), month = "Jul · 24kg", value = 0.9f, color = PrimaryOrange)
            }
        }
    }
}

@Composable
fun ChartBar(modifier: Modifier, month: String, value: Float, color: Color) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(value)
                .background(color, RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = month,
            fontSize = 11.sp,
            color = TextGray,
            fontWeight = FontWeight.Medium
        )
    }
}
