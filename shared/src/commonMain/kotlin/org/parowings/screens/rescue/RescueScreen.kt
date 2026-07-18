package org.parowings.screens.rescue

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Check
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
import org.parowings.theming.BackgroundGray
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
fun RescueScreenPreview() {
    MaterialTheme {
        RescueScreen()
    }
}

private val PrimaryGreen = Color(0xFF008A45)
private val EmergencyRed = Color(0xFFFF5B5B)
private val PendingBg = Color(0xFFFFEAEA)
private val AcceptedBg = Color(0xFFE8F5E9)
private val IconBgBeige = Color(0xFFE5D7C5)
private val IconBgGreen = Color(0xFFC8E6C9)
private val MapBackground = Color(0xFFE1EDDC)

@Composable
fun RescueScreen(
    onRescueClick: (RescueCase) -> Unit = {},
    onFilterClick: () -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize().background(BackgroundGray)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(16.dp))
            RescueHeader(onFilterClick = onFilterClick)
            
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
            ) {
                item {
                    MapViewSection()
                    Spacer(modifier = Modifier.height(24.dp))
                    RescueFilterChips()
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                items(sampleRescues) { rescue ->
                    RescueCaseItem(rescue = rescue, onClick = { onRescueClick(rescue) })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        
        // Add FAB in screen
        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 100.dp)
                .size(56.dp),
            containerColor = PrimaryOrange,
            contentColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Rescue")
        }
    }
}

@Composable
private fun RescueHeader(onFilterClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Rescue map",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        Surface(
            modifier = Modifier
                .size(44.dp)
                .clickable { onFilterClick() },
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Tune, contentDescription = "Filter", tint = TextDark, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun MapViewSection() {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MapBackground)
    ) {
        // Mock Map Grid Lines
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidthVal = 8.dp.toPx()
            val roadColor = Color.White.copy(alpha = 0.5f)
            
            // Vertical Roads
            drawLine(roadColor, start = androidx.compose.ui.geometry.Offset(size.width * 0.3f, 0f), end = androidx.compose.ui.geometry.Offset(size.width * 0.25f, size.height), strokeWidth = strokeWidthVal)
            drawLine(roadColor, start = androidx.compose.ui.geometry.Offset(size.width * 0.7f, 0f), end = androidx.compose.ui.geometry.Offset(size.width * 0.75f, size.height), strokeWidth = strokeWidthVal)
            
            // Horizontal Roads
            drawLine(roadColor, start = androidx.compose.ui.geometry.Offset(0f, size.height * 0.3f), end = androidx.compose.ui.geometry.Offset(size.width, size.height * 0.25f), strokeWidth = strokeWidthVal)
            drawLine(roadColor, start = androidx.compose.ui.geometry.Offset(0f, size.height * 0.7f), end = androidx.compose.ui.geometry.Offset(size.width, size.height * 0.75f), strokeWidth = strokeWidthVal)
        }

        // Map Markers
        MapMarker(Modifier.align(Alignment.Center).offset(x = (-40).dp, y = (-20).dp), EmergencyRed, Icons.Outlined.Pets)
        MapMarker(Modifier.align(Alignment.Center).offset(x = 40.dp, y = (-60).dp), PrimaryGreen, Icons.Rounded.Check)
        MapMarker(Modifier.align(Alignment.Center).offset(x = 20.dp, y = 30.dp), PrimaryOrange, Icons.Outlined.Flight)

        // My Location Button
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(40.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.MyLocation, contentDescription = "My Location", tint = PrimaryGreen, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun MapMarker(modifier: Modifier, color: Color, icon: ImageVector) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        Surface(
            modifier = Modifier.size(36.dp),
            shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 2.dp),
            color = color,
            shadowElevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun RescueFilterChips() {
    val filters = listOf(
        FilterItem("All", Icons.Outlined.Explore, PrimaryGreen),
        FilterItem("Emergency", Icons.Outlined.WarningAmber, EmergencyRed),
        FilterItem("Dog", Icons.Outlined.Pets, TextGray),
        FilterItem("Cat", Icons.Outlined.Pets, TextGray)
    )
    var selectedFilter by remember { mutableStateOf("All") }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(filters) { item ->
            val isSelected = item.label == selectedFilter
            Surface(
                onClick = { selectedFilter = item.label },
                color = if (isSelected) PrimaryGreen else Color.White,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.White else item.color,
                        modifier = Modifier.size(18.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = item.label,
                        color = if (isSelected) Color.White else TextGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

private data class FilterItem(val label: String, val icon: ImageVector, val color: Color)

@Composable
private fun RescueCaseItem(rescue: RescueCase, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = RoundedCornerShape(16.dp),
                color = rescue.iconBgColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(rescue.icon, contentDescription = null, tint = rescue.iconColor, modifier = Modifier.size(24.dp))
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = rescue.title,
                        fontWeight = FontWeight.Bold,
                        color = TextDark,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = rescue.statusBgColor
                    ) {
                        Text(
                            text = rescue.status,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = rescue.statusColor
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(6.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.AccessTime, contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${rescue.timeAgo} · ${rescue.distance}", color = TextGray, fontSize = 13.sp)
                }
                
                if (rescue.volunteerInfo != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.NearMe, contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = rescue.volunteerInfo, color = TextGray, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

data class RescueCase(
    val title: String,
    val timeAgo: String,
    val distance: String,
    val status: String,
    val statusColor: Color,
    val statusBgColor: Color,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBgColor: Color,
    val volunteerInfo: String? = null
)

private val sampleRescues = listOf(
    RescueCase(
        title = "Injured street dog",
        timeAgo = "12 min ago",
        distance = "1.2 km away",
        status = "Pending",
        statusColor = EmergencyRed,
        statusBgColor = PendingBg,
        icon = Icons.Outlined.Pets,
        iconColor = Color(0xFF8D6E63),
        iconBgColor = IconBgBeige
    ),
    RescueCase(
        title = "Bird with wing injury",
        timeAgo = "30 min ago",
        distance = "0.8 km",
        status = "Accepted",
        statusColor = PrimaryGreen,
        statusBgColor = AcceptedBg,
        icon = Icons.Outlined.Flight,
        iconColor = PrimaryGreen,
        iconBgColor = IconBgGreen,
        volunteerInfo = "Volunteer en route"
    ),
    RescueCase(
        title = "Stray cow, roadside",
        timeAgo = "Yesterday",
        distance = "3.1 km away",
        status = "Completed",
        statusColor = PrimaryGreen,
        statusBgColor = AcceptedBg,
        icon = Icons.Outlined.Pets,
        iconColor = Color(0xFF8D6E63),
        iconBgColor = IconBgBeige
    )
)
