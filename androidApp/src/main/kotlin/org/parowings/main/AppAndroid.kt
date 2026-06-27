package org.parowings.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator


import cafe.adriel.voyager.transitions.SlideTransition

import org.parowings.screens.welcome.Welcome


@Composable
fun AppAndroid(
    onRequestSignIn: () -> Unit = {}
) {
    MaterialTheme {
        Scaffold { innerPadding ->

            Box(
                modifier = Modifier.padding(innerPadding)
            ) {

                Navigator(Welcome) { navigator ->
                    SlideTransition(navigator)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    AppAndroid()
}