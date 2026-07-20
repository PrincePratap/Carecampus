package org.parowings.screens.userProfile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.theming.*

object MyReports : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MyReportsScreen(onBackClick = { navigator.pop() })
    }
}

@Composable
fun MyReportsScreen(onBackClick: () -> Unit = {}) {
    val navigator = LocalNavigator.currentOrThrow
    var selectedFilter by remember { mutableStateOf("All") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            MyReportsTopBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ReportFilterChips(
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it },
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Mock data based on the images
                val reports = listOf(
                    ReportData(
                        id = "RPT-20481",
                        animalType = "Dog",
                        dateTime = "14 Jul 2026 · 6:42 PM",
                        location = "Lajpat Nagar, Delhi",
                        severity = "Critical",
                        statusText = "Rescue completed",
                        currentStep = 7,
                        totalSteps = 7,
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFE3D1B4),
                        status = "Completed"
                    ),
                    ReportData(
                        id = "RPT-20463",
                        animalType = "Cat",
                        dateTime = "10 Jul 2026 · 11:05 AM",
                        location = "Saket Market, Delhi",
                        severity = "Moderate",
                        statusText = "Animal picked up",
                        currentStep = 5,
                        totalSteps = 7,
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFF5D19E),
                        status = "In progress"
                    ),
                    ReportData(
                        id = "RPT-20411",
                        animalType = "Bird",
                        dateTime = "2 Jul 2026 · 4:20 PM",
                        location = "Nehru Park, Delhi",
                        severity = "Low",
                        statusText = "Rescue completed",
                        currentStep = 7,
                        totalSteps = 7,
                        icon = Icons.Outlined.FlutterDash,
                        iconBgColor = Color(0xFFB4D6A4),
                        status = "Completed"
                    ),
                    ReportData(
                        id = "RPT-20388",
                        animalType = "Cow",
                        dateTime = "28 Jun 2026 · 9:14 AM",
                        location = "Ring Road, Delhi",
                        severity = "Moderate",
                        statusText = "Rescuer assigned",
                        currentStep = 3,
                        totalSteps = 7,
                        icon = Icons.Outlined.Pets,
                        iconBgColor = Color(0xFFE3D1B4),
                        status = "In progress"
                    )
                )

                val filteredReports = when (selectedFilter) {
                    "In progress" -> reports.filter { it.status == "In progress" }
                    "Completed" -> reports.filter { it.status == "Completed" }
                    else -> reports
                }

                items(items = filteredReports) { report ->
                    Surface(onClick = { navigator.push(ReportTracking) }) {
                        ReportCard(report)
                    }
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

data class ReportData(
    val id: String,
    val animalType: String,
    val dateTime: String,
    val location: String,
    val severity: String,
    val statusText: String,
    val currentStep: Int,
    val totalSteps: Int,
    val icon: ImageVector,
    val iconBgColor: Color,
    val status: String
)

@Composable
fun MyReportsTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            onClick = onBackClick,
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.size(48.dp).align(Alignment.CenterStart),
            shadowElevation = 1.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp),
                    tint = TextDark
                )
            }
        }

        Text(
            text = "My reports",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )

        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.size(48.dp),
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier.size(24.dp),
                        tint = TextDark
                    )
                }
            }
            Surface(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.size(48.dp),
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp),
                        tint = TextDark
                    )
                }
            }
        }
    }
}

@Composable
fun ReportFilterChips(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            FilterChipItem(
                label = "All",
                isSelected = selectedFilter == "All",
                onClick = { onFilterSelected("All") }
            )
        }
        item {
            FilterChipItem(
                label = "In progress",
                icon = Icons.Default.LocalShipping,
                isSelected = selectedFilter == "In progress",
                onClick = { onFilterSelected("In progress") }
            )
        }
        item {
            FilterChipItem(
                label = "Completed",
                icon = Icons.Default.CheckCircle,
                isSelected = selectedFilter == "Completed",
                onClick = { onFilterSelected("Completed") }
            )
        }
    }
}

@Composable
fun FilterChipItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector? = null
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) PrimaryGreen else Color.White,
        modifier = Modifier.height(44.dp),
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = if (isSelected) Color.White else PrimaryGreen
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = label,
                fontSize = 15.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else TextGray
            )
        }
    }
}

@Composable
fun ReportCard(report: ReportData) {
    val severityColor = when (report.severity) {
        "Critical" -> EmergencyRed
        "Moderate" -> PrimaryOrange
        "Low" -> PrimaryGreen
        else -> TextGray
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = report.iconBgColor,
                    modifier = Modifier.size(64.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = report.icon,
                            contentDescription = null,
                            tint = Color.DarkGray.copy(alpha = 0.6f),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = report.id,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Text(
                        text = "${report.animalType} · ${report.dateTime}",
                        fontSize = 13.sp,
                        color = TextGray,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = report.location,
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = severityColor.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = report.severity,
                        color = severityColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = report.statusText,
                    fontSize = 13.sp,
                    color = TextDark,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Step ${report.currentStep}/${report.totalSteps}",
                    fontSize = 12.sp,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { report.currentStep.toFloat() / report.totalSteps.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = PrimaryGreen,
                trackColor = Color.LightGray.copy(alpha = 0.3f),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ReportActionButton(
                    text = "Track rescue",
                    icon = Icons.Outlined.Route,
                    modifier = Modifier.weight(1f)
                )
                ReportActionButton(
                    text = "Contact NGO",
                    icon = Icons.Outlined.ChatBubbleOutline,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(10.dp))
            
            ReportActionButton(
                text = "Share",
                icon = Icons.Outlined.Share,
                modifier = Modifier.wrapContentWidth()
            )
        }
    }
}

@Composable
fun ReportActionButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        modifier = modifier.height(44.dp),
        shadowElevation = 0.5.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = TextDark
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
        }
    }
}
