package org.parowings.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.screens.common.HomeItems.AnimalCategoriesSection
import org.parowings.screens.common.HomeItems.EmergencySOSBanner
import org.parowings.screens.common.HomeItems.HeaderSection
import org.parowings.screens.common.HomeItems.LatestRescueCasesSection
import org.parowings.screens.common.HomeItems.QuickActionsSection
import org.parowings.screens.common.HomeItems.SearchBar
import org.parowings.theming.BackgroundLight
import org.parowings.theming.EmergencyRed
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.PrimaryOrange
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableStateOf("Dogs") }

    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = {}
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            HeaderSection()
            Spacer(modifier = Modifier.height(24.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(24.dp))
            EmergencySOSBanner()
            Spacer(modifier = Modifier.height(32.dp))
            QuickActionsSection(
                onReportClick = {
                },
                onAdoptClick = {
                },
                onDonateClick = {
                },
                onMoreClick = {
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            AnimalCategoriesSection(
                selectedCategory = selectedCategory,
                onCategoryClick = { category ->
                    selectedCategory = category

                    when (category) {
                        "Dogs" -> {}
                        "Cats" -> {}
                        "Birds" -> {}
                        "Others" -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            LatestRescueCasesSection()
            Spacer(modifier = Modifier.height(100.dp)) // Extra space for bottom bar
        }
    }
}


















