package org.parowings.common.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.parowings.common.data.remote.Result
import org.parowings.common.data.local.UserSettings
import org.parowings.common.data.local.UserSettingsRepository

class AuthViewModel(
     val repository: AuthRepository,
     private val userSettingsRepository: UserSettingsRepository
) {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    init {
        checkAuth()
    }

    fun checkAuth() {
        isLoading = true
        message = null
        error = null
        _uiState.value = AuthUiState(isLoading = true)

        CoroutineScope(Dispatchers.Main).launch {
            when (val result = repository.checkAuth()) {
                is Result.Success -> {
                    message = result.data
                    _uiState.value = AuthUiState(message = result.data)
                }
                is Result.Error -> {
                    error = result.message
                    _uiState.value = AuthUiState(error = result.message)
                }
            }
            isLoading = false
        }
    }

    suspend fun googleLogin(request: GoogleLoginRequest) {
        _state.value = AuthState.Loading

        try {
            when (val result = repository.googleLogin(request)) {
                is Result.Success -> {
                    val response = result.data
                    val settings = UserSettings(
                        userId = response.user.id,
                        fullName = response.user.fullName,
                        email = response.user.email,
                        photoUrl = response.user.photoUrl,
                        accessToken = response.accessToken
                    )
                    withContext(Dispatchers.IO) {
                        userSettingsRepository.saveUserSettings(settings)
                    }
                    _state.value = AuthState.Success(settings)
                }
                is Result.Error -> {
                    _state.value = AuthState.Error(result.message)
                    throw IllegalStateException(result.message)
                }
            }
        } catch (e: Exception) {
            _state.value = AuthState.Error(e.message ?: "Unknown error")
            throw e
        }
    }
}

data class AuthUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null
)

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userSettings: UserSettings) : AuthState()
    data class Error(val message: String) : AuthState()
}
