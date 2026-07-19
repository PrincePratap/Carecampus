package org.parowings.screens.services

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

@Composable
fun AiPetCareScreen(
    onBackClick: () -> Unit = {},
    onServiceClick: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Surface(
                    onClick = onBackClick,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    modifier = Modifier.size(44.dp),
                    shadowElevation = 2.dp
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
                    text = "AI pet care",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Get instant AI-powered guidance for your pet. Always confirm medical decisions with a licensed veterinarian.",
                fontSize = 14.sp,
                color = TextGray,
                lineHeight = 20.sp
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            AiServiceCard(
                title = "AI medicine scanner",
                description = "Snap a medicine label for dosage & warnings",
                icon = Icons.Outlined.CenterFocusWeak,
                iconBgColor = Color(0xFFE8F5E9),
                iconTint = PrimaryGreen,
                onClick = { onServiceClick("scanner") }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AiServiceCard(
                title = "AI symptom checker",
                description = "Select symptoms for possible causes & first aid",
                icon = Icons.Outlined.HealthAndSafety,
                iconBgColor = Color(0xFFFFEBEE),
                iconTint = Color(0xFFFF5B5B),
                onClick = { onServiceClick("symptoms") }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AiServiceCard(
                title = "AI breed detector",
                description = "Upload a photo to identify breed & traits",
                icon = Icons.Outlined.Pets,
                iconBgColor = Color(0xFFFFF3E0),
                iconTint = PrimaryOrange,
                onClick = { onServiceClick("breed") }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AiServiceCard(
                title = "AI food advisor",
                description = "Get a daily feeding plan tailored to your pet",
                icon = Icons.Outlined.Restaurant,
                iconBgColor = Color(0xFFE0F2F1),
                iconTint = Color(0xFF009688),
                onClick = { onServiceClick("food") }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AiServiceCard(
                title = "Ask Paro AI assistant",
                description = "Chat about any pet concern, anytime",
                icon = Icons.Outlined.AutoAwesome,
                iconBgColor = PrimaryGreen,
                iconTint = Color.White,
                onClick = { onServiceClick("assistant") }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AiServiceCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = iconBgColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
            }
            
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
