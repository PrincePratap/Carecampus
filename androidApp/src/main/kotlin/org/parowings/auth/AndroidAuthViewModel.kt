package org.parowings.auth

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.parowings.common.authentication.AuthViewModel
import org.parowings.common.authentication.GoogleLoginRequest
import org.parowings.common.data.local.UserSettingsRepository

class AndroidAuthViewModel(
    private val manager: GoogleAuthManager,
    private val authViewModel: AuthViewModel,
    private val userSettingsRepository: UserSettingsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun getSignInIntent(activity: Activity) = manager.getSignInIntent(activity)

    fun handleSignInResult(data: Intent?) {
        _state.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val user = manager.handleSignInResult(data)
                authViewModel.googleLogin(
                    GoogleLoginRequest(
                        firebaseUid = user.uid,
                        fullName = user.displayName ?: "",
                        email = user.email ?: "",
                        photoUrl = user.photoUrl ?: ""
                    )
                )
                _state.value = AuthState.Success(user)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signOut(activity: Activity) {
        _state.value = AuthState.Loading
        viewModelScope.launch {
            try {
                manager.signOut(activity)
                userSettingsRepository.clearUserSettings()
                _state.value = AuthState.Idle
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Sign-out failed")
            }
        }
    }
}
