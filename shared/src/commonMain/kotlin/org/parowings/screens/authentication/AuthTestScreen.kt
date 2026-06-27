package org.parowings.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.parowings.common.authentication.AuthViewModel

@Composable
fun AuthTestScreen(
    viewModel: AuthViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val isLoading = uiState.isLoading
    val message = uiState.message
    val error = uiState.error

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    viewModel.checkAuth()
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Test API")
                }

                if (isLoading) {
                    Text(text = "Loading...", fontWeight = FontWeight.Bold)
                }

                message?.let {
                    Text(text = it, fontWeight = FontWeight.Bold)
                }

                error?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
