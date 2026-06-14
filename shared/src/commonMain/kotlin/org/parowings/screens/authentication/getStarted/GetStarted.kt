package org.parowings.screens.authentication.getStarted

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.parowings.screens.authentication.login.Login
import org.parowings.screens.authentication.signup.SignUp


object GetStarted : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        GetStartedScreen(
            onLoginClick ={
                navigator.push(Login)
            },
            onSignUpClick = {
                navigator.push(SignUp)

            }
        )
    }

}