package org.parowings.screens.userProfile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.ChevronRight
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

object Settings : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        SettingsScreen(onBackClick = { navigator.pop() })
    }
}

@Composable
fun SettingsScreen(onBackClick: () -> Unit = {}) {
    var darkMode by remember { mutableStateOf(false) }
    var pushNotifications by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            SettingsTopBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            SettingsItem(
                icon = Icons.Outlined.DarkMode,
                label = "Dark mode",
                trailing = {
                    Switch(
                        checked = darkMode,
                        onCheckedChange = { darkMode = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = PrimaryGreen,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f),
                            uncheckedBorderColor = Color.Transparent
                        )
                    )
                }
            )

            SettingsItem(
                icon = Icons.Outlined.Translate,
                label = "Language",
                trailing = {
                    Text(
                        text = "English",
                        color = TextGray,
                        fontSize = 14.sp
                    )
                }
            )

            SettingsItem(
                icon = Icons.Outlined.NotificationsNone,
                label = "Push notifications",
                trailing = {
                    Switch(
                        checked = pushNotifications,
                        onCheckedChange = { pushNotifications = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = PrimaryGreen,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f),
                            uncheckedBorderColor = Color.Transparent
                        )
                    )
                }
            )

            SettingsItem(
                icon = Icons.Outlined.Shield,
                label = "Privacy",
                showChevron = true
            )

            SettingsItem(
                icon = Icons.Outlined.Lock,
                label = "Security",
                showChevron = true
            )

            SettingsItem(
                icon = Icons.Outlined.HelpOutline,
                label = "Help and support",
                showChevron = true
            )

            SettingsItem(
                icon = Icons.Outlined.StarBorder,
                label = "Rate app",
                showChevron = true
            )

            SettingsItem(
                icon = Icons.Outlined.Share,
                label = "Share app",
                showChevron = true
            )

            SettingsItem(
                icon = Icons.Outlined.Info,
                label = "About",
                showChevron = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = { /* Handle logout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, EmergencyRed),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = EmergencyRed)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Log out",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsPreview() {
    SettingsScreen()
}

@Composable
fun SettingsTopBar(onBackClick: () -> Unit) {
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
            text = "Settings",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    label: String,
    trailing: @Composable (() -> Unit)? = null,
    showChevron: Boolean = false,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier.weight(1f)
            )
            if (trailing != null) {
                trailing()
            } else if (showChevron) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
