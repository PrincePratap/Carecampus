package org.parowings.screens.userProfile.achievements

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

enum class AchievementTab {
    Badges, Leaderboard, Rewards
}

data class BadgeItem(val name: String, val icon: ImageVector, val color: Color, val isLocked: Boolean = false)
data class Player(val rank: String, val initials: String, val name: String, val points: String, val isTop: Boolean, val isSelf: Boolean = false)
data class RewardHistoryItem(val title: String, val date: String, val points: String, val pointsColor: Color)

@Composable
fun AchievementsScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(AchievementTab.Badges) }

    Scaffold(
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AchievementHeaderSection(onBackClick)
            
            StatsSection()
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AchievementTabSelector(selectedTab) { selectedTab = it }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Box(modifier = Modifier.weight(1f).padding(horizontal = 24.dp)) {
                when (selectedTab) {
                    AchievementTab.Badges -> BadgesContent()
                    AchievementTab.Leaderboard -> LeaderboardContent()
                    AchievementTab.Rewards -> RewardsContent()
                }
            }
        }
    }
}

@Composable
private fun AchievementHeaderSection(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF00A651), PrimaryGreen)
                )
            )
            .padding(bottom = 32.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White.copy(alpha = 0.2f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "Achievements",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(40.dp))
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { 0.75f },
                        modifier = Modifier.size(70.dp),
                        color = Color.White,
                        strokeWidth = 4.dp,
                        trackColor = Color.White.copy(alpha = 0.3f),
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "12",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "LEVEL",
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = "Gold Volunteer",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "2,480 pts • 320 pts to Level 13",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LinearProgressIndicator(
                        progress = { 0.7f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.3f)
                    )
                }
            }
        }
    }
}

@Composable
private fun StatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .offset(y = (-15).dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AchievementStatCard("14", "Rescues", modifier = Modifier.weight(1f))
        AchievementStatCard("52", "Reports", modifier = Modifier.weight(1f))
        AchievementStatCard("3", "Adoptions", modifier = Modifier.weight(1f))
        AchievementStatCard("11", "Donations", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun AchievementStatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Text(text = label, fontSize = 10.sp, color = TextGray)
        }
    }
}

@Composable
private fun AchievementTabSelector(selectedTab: AchievementTab, onTabSelected: (AchievementTab) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            AchievementTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab
                Surface(
                    modifier = Modifier.weight(1f),
                    onClick = { onTabSelected(tab) },
                    color = if (isSelected) PrimaryGreen.copy(alpha = 0.1f) else Color.Transparent,
                    shape = RoundedCornerShape(8.dp),
                    border = if (isSelected) BorderStroke(1.dp, PrimaryGreen.copy(alpha = 0.2f)) else null
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when(tab) {
                                AchievementTab.Badges -> Icons.Outlined.Shield
                                AchievementTab.Leaderboard -> Icons.Outlined.BarChart
                                AchievementTab.Rewards -> Icons.Outlined.CardGiftcard
                            },
                            contentDescription = null,
                            tint = if (isSelected) PrimaryGreen else TextGray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = tab.name,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) PrimaryGreen else TextGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BadgesContent() {
    Column {
        // Search bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, null, tint = TextGray, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Search badges...", color = TextGray, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text("12/20", color = TextGray, fontSize = 12.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Categories
        val categories = listOf("All", "Rescue", "Pet care", "Community")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { category ->
                val isSelected = category == "All"
                Surface(
                    color = if (isSelected) PrimaryGreen else Color.White,
                    shape = RoundedCornerShape(20.dp),
                    border = if (!isSelected) BorderStroke(1.dp, BackgroundLight) else null,
                    shadowElevation = if (!isSelected) 1.dp else 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (category == "Rescue") {
                            Icon(Icons.Outlined.LocalActivity, null, tint = if (isSelected) Color.White else TextGray, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                        } else if (category == "Pet care") {
                            Icon(Icons.Outlined.Pets, null, tint = if (isSelected) Color.White else TextGray, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = category,
                            color = if (isSelected) Color.White else TextDark,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Grid
        val badges = listOf(
            BadgeItem("First rescue", Icons.Outlined.Info, PrimaryGreen),
            BadgeItem("First adoption", Icons.Outlined.FavoriteBorder, PrimaryOrange),
            BadgeItem("Animal Guardian", Icons.Outlined.Shield, Color(0xFF00ACC1)),
            BadgeItem("Top donor", Icons.Outlined.VolunteerActivism, Color(0xFF7E57C2)),
            BadgeItem("Pet parent", Icons.Outlined.Pets, Color(0xFF689F38)),
            BadgeItem("Rescue Hero", Icons.Outlined.MedicalServices, Color(0xFFEF5350)),
            BadgeItem("Volunteer", Icons.Outlined.Groups, Color(0xFF43A047)),
            BadgeItem("Community Star", Icons.Outlined.AutoAwesome, Color(0xFFCBD5E0), isLocked = true)
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(badges) { badge ->
                AchievementBadgeCard(badge)
            }
        }
    }
}

@Composable
private fun AchievementBadgeCard(badge: BadgeItem) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = if (badge.isLocked) Color(0xFFF1F5F9) else badge.color
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = badge.icon,
                            contentDescription = null,
                            tint = if (badge.isLocked) Color(0xFF94A3B8) else Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                if (badge.isLocked) {
                    Surface(
                        modifier = Modifier.size(18.dp).offset(x = 4.dp, y = 4.dp),
                        color = Color.White,
                        shape = CircleShape,
                        shadowElevation = 1.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Outlined.Lock, null, tint = Color(0xFF94A3B8), modifier = Modifier.size(10.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = badge.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (badge.isLocked) TextGray else TextDark,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun LeaderboardContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            val players = listOf(
                Player("1", "AS", "Ananya Sharma", "3,120 pts", true),
                Player("2", "RV", "Rahul Verma", "2,850 pts", false),
                Player("PR", "PR", "Prince Rathi (You)", "2,480 pts", false, isSelf = true),
                Player("4", "MI", "Meera Iyer", "2,210 pts", false),
                Player("5", "KM", "Karan Malhotra", "1,990 pts", false)
            )
            
            items(players) { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (player.isSelf) PrimaryGreen.copy(alpha = 0.05f) else Color.Transparent)
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.width(24.dp), contentAlignment = Alignment.Center) {
                        if (player.rank == "1") {
                            Icon(Icons.Outlined.EmojiEvents, null, tint = Color(0xFFFFD600), modifier = Modifier.size(18.dp))
                        } else if (player.rank == "2") {
                            Icon(Icons.Outlined.EmojiEvents, null, tint = Color(0xFFB0BEC5), modifier = Modifier.size(18.dp))
                        } else if (player.isSelf) {
                            Icon(Icons.Outlined.EmojiEvents, null, tint = Color(0xFFCC8E35), modifier = Modifier.size(18.dp))
                        } else {
                            Text(player.rank, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextGray)
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Surface(
                        modifier = Modifier.size(36.dp),
                        shape = CircleShape,
                        color = PrimaryGreen
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(player.initials, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Text(
                        text = player.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Text(
                        text = player.points,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreen
                    )
                }
            }
        }
    }
}

@Composable
private fun RewardsContent() {
    val rewards = listOf(
        RewardHistoryItem("Redeemed: Paro Wings tote bag", "8 Jul 2026", "-500", EmergencyRed),
        RewardHistoryItem("Bonus: Gold volunteer rank up", "1 Jul 2026", "+400", PrimaryGreen),
        RewardHistoryItem("Redeemed: 10% off pet supplies", "15 Jun 2026", "-250", EmergencyRed),
        RewardHistoryItem("Bonus: 50 reports milestone", "2 Jun 2026", "+300", PrimaryGreen)
    )
    
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(rewards) { reward ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = reward.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextDark)
                        Text(text = reward.date, fontSize = 12.sp, color = TextGray)
                    }
                    Text(
                        text = reward.points,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = reward.pointsColor
                    )
                }
            }
        }
    }
}
