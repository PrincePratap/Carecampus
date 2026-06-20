package org.parowings.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.koin.core.context.startKoin
import org.parowings.di.appModule
import org.parowings.screens.authentication.AuthTest
import org.parowings.screens.authentication.getStarted.GetStarted

@Composable
@Preview
fun App() {



    MaterialTheme {

        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigator(screen = AuthTest) { navigator ->

                    SlideTransition(navigator)

                }

            }
        }
    }
}