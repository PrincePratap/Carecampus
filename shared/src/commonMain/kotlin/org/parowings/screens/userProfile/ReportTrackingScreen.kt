package org.parowings.screens.userProfile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.theming.*

object ReportTracking : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ReportTrackingScreen(onBackClick = { navigator.pop() })
    }
}

@Composable
fun ReportTrackingScreen(onBackClick: () -> Unit = {}) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            ReportTrackingTopBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Summary Card
            CompactReportSummaryCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            // Timeline Section
            Text(
                text = "Rescue timeline",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextGray,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            TimelineSection(
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Action Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(28.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Contact NGO",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ReportTrackingTopBar(onBackClick: () -> Unit) {
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
            text = "Report tracking",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )

        Surface(
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.size(48.dp).align(Alignment.CenterEnd),
            shadowElevation = 1.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Share",
                    modifier = Modifier.size(20.dp),
                    tint = TextDark
                )
            }
        }
    }
}

@Composable
fun CompactReportSummaryCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFE3D1B4),
                modifier = Modifier.size(64.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Pets,
                        contentDescription = null,
                        tint = Color.DarkGray.copy(alpha = 0.6f),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "RPT-20481",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = "Dog · 14 Jul 2026 · 6:42 PM",
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
                        text = "Lajpat Nagar, Delhi",
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = EmergencyRed.copy(alpha = 0.1f)
            ) {
                Text(
                    text = "Critical",
                    color = EmergencyRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun TimelineSection(modifier: Modifier = Modifier) {
    val steps = listOf(
        "Submitted" to true,
        "NGO accepted" to true,
        "Rescuers assigned" to true,
        "On the way" to true,
        "Animal picked up" to true,
        "Treatment started" to true,
        "Rescue completed" to false
    )

    Column(modifier = modifier) {
        steps.forEachIndexed { index, step ->
            TimelineItem(
                title = step.first,
                isCompleted = step.second,
                isLast = index == steps.size - 1,
                stepNumber = if (!step.second) 7 else null
            )
        }
    }
}

@Composable
fun TimelineItem(
    title: String,
    isCompleted: Boolean,
    isLast: Boolean,
    stepNumber: Int? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(
                        color = if (isCompleted) PrimaryGreen else PrimaryOrange.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                } else if (stepNumber != null) {
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(2.dp),
                        shape = CircleShape,
                        color = PrimaryOrange
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = stepNumber.toString(),
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(if (isCompleted) PrimaryGreen else Color.LightGray.copy(alpha = 0.3f))
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Text(
                text = if (isCompleted) "Completed" else "In progress now",
                fontSize = 13.sp,
                color = TextGray
            )
        }
    }
}
