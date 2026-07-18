package org.parowings.screens.community

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.BackgroundGray
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

private val PrimaryGreen = Color(0xFF008A45)
private val PrimaryOrange = Color(0xFFFF7A00)
private val CardGreenTint = Color(0xFFACCFA9)

@Composable
fun CommunityScreen(
    onPostClick: (Post) -> Unit = {},
    onCreatePostClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundGray,
        topBar = {
            CommunityHeader(onEditClick = onCreatePostClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HashtagFilters()

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(samplePosts) { post ->
                    CommunityPostCard(post = post, onClick = { onPostClick(post) })
                }
                item { Spacer(modifier = Modifier.height(80.dp)) } // Space for bottom bar
            }
        }
    }
}

@Composable
private fun CommunityHeader(onEditClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Community",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        Surface(
            modifier = Modifier
                .size(44.dp)
                .clickable { onEditClick() },
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.EditNote,
                    contentDescription = "Create Post",
                    tint = TextDark,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun HashtagFilters() {
    val filters = listOf("#RescueStories", "#AdoptDontShop", "#Volunteers", "#HelpNeeded")
    var selectedFilter by remember { mutableStateOf(filters[0]) }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selectedFilter
            Surface(
                onClick = { selectedFilter = filter },
                color = if (isSelected) PrimaryGreen else Color.White,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(40.dp),
                border = if (!isSelected) BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)) else null
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = filter,
                        color = if (isSelected) Color.White else TextGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun CommunityPostCard(post: Post, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // User Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(post.userColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (post.userIcon != null) {
                        Icon(post.userIcon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text = post.userInitials,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = post.userName, fontWeight = FontWeight.Bold, color = TextDark, fontSize = 15.sp)
                    Text(text = post.time, color = TextGray, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Image Content
            if (post.hasImage) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(CardGreenTint.copy(alpha = 0.6f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Pets,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Text Content
            Text(
                text = post.content,
                color = TextDark,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InteractionItem(icon = Icons.Outlined.FavoriteBorder, count = post.likes)
                Spacer(modifier = Modifier.width(16.dp))
                InteractionItem(icon = Icons.Outlined.ChatBubbleOutline, count = post.comments)
                Spacer(modifier = Modifier.width(16.dp))
                InteractionItem(
                    icon = if (post.isEvent) Icons.Outlined.CalendarToday else Icons.Outlined.Share,
                    label = if (post.isEvent) "Event" else "Share"
                )
            }
        }
    }
}

@Composable
private fun InteractionItem(icon: ImageVector, count: Int? = null, label: String? = null) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = TextGray, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = count?.toString() ?: label ?: "",
            color = TextGray,
            fontSize = 13.sp
        )
    }
}

data class Post(
    val userName: String,
    val userInitials: String = "",
    val userColor: Color,
    val userIcon: ImageVector? = null,
    val time: String,
    val content: String,
    val hasImage: Boolean = false,
    val likes: Int,
    val comments: Int,
    val isEvent: Boolean = false
)

private val samplePosts = listOf(
    Post(
        userName = "Anjali Kapoor",
        userInitials = "AK",
        userColor = Color(0xFF006D35),
        time = "3 hours ago",
        content = "Found this little guy limping on the highway today. Two hours later, thanks to Paro Wings volunteers, he's safe at the shelter.",
        hasImage = true,
        likes = 214,
        comments = 38
    ),
    Post(
        userName = "Delhi Paws NGO",
        userColor = Color(0xFFFF7A00),
        userIcon = Icons.Outlined.Apartment,
        time = "Yesterday",
        content = "Volunteer drive this Sunday at Nehru Park — join us for vaccinations and sterilization camp sign-ups.",
        likes = 96,
        comments = 12,
        isEvent = true
    )
)
