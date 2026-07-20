package org.parowings.screens.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.*

sealed class ChatMessage {
    data class User(val text: String) : ChatMessage()
    data class Assistant(
        val possibleCauses: String,
        val firstAid: String,
        val seeVetNow: String
    ) : ChatMessage()
}

@Composable
fun AiAssistantScreen(
    onBackClick: () -> Unit = {}
) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf<ChatMessage>(
            ChatMessage.User("My dog is vomiting."),
            ChatMessage.Assistant(
                possibleCauses = "dietary indiscretion, motion sickness, or a mild stomach upset.",
                firstAid = "withhold food for 6-8 hours, offer small sips of water, watch for repeat episodes.",
                seeVetNow = "vomiting persists past 24 hours, blood is present, or your dog seems lethargic."
            )
        )
    }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            AiAssistantTopBar(onBackClick)
        },
        bottomBar = {
            ChatInputBar(
                value = inputText,
                onValueChange = { inputText = it },
                onSendClick = {
                    if (inputText.isNotBlank()) {
                        messages.add(ChatMessage.User(inputText))
                        inputText = ""
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(messages) { message ->
                when (message) {
                    is ChatMessage.User -> UserMessageBubble(message.text)
                    is ChatMessage.Assistant -> AssistantMessageBubble(message)
                }
            }
        }
    }
}

@Composable
fun AiAssistantTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
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

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = PrimaryGreen
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Paro AI Assistant",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = "Always available",
                    fontSize = 11.sp,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.size(44.dp))
    }
}

@Composable
fun UserMessageBubble(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Surface(
            color = PrimaryGreen,
            shape = RoundedCornerShape(20.dp, 20.dp, 4.dp, 20.dp),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun AssistantMessageBubble(message: ChatMessage.Assistant) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.widthIn(max = 300.dp),
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                AssistantSection("Possible causes: ", message.possibleCauses)
                Spacer(modifier = Modifier.height(16.dp))
                AssistantSection("First aid: ", message.firstAid)
                Spacer(modifier = Modifier.height(16.dp))
                AssistantSection("See a vet now if: ", message.seeVetNow)
                
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This is general guidance, not a diagnosis.",
                    fontSize = 12.sp,
                    color = Color(0xFFC4C4C4)
                )
            }
        }
    }
}

@Composable
fun AssistantSection(label: String, content: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = TextDark)) {
                append(label)
            }
            withStyle(style = SpanStyle(color = TextDark)) {
                append(content)
            }
        },
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
}

@Composable
fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text("Describe what's happening...", color = Color(0xFFC4C4C4), fontSize = 14.sp) },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF1F1F1).copy(alpha = 0.5f),
                    unfocusedContainerColor = Color(0xFFF1F1F1).copy(alpha = 0.5f),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Surface(
                onClick = onSendClick,
                shape = RoundedCornerShape(12.dp),
                color = PrimaryGreen,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
