package org.parowings.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.parowings.screens.welcome.ErrorFallbackScreen
import org.parowings.screens.welcome.Welcome

@Composable
fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                val errorMessage = remember { mutableStateOf<String?>(null) }
                Navigator(Welcome) { navigator ->
                    SlideTransition(navigator)
                }

                errorMessage.value?.let { message ->
                    ErrorFallbackScreen(message = message)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MaterialTheme {
        ErrorFallbackScreen(message = "Preview")
    }
}