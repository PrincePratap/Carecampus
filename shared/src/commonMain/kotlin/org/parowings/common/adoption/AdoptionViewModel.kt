package org.parowings.common.adoption

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.parowings.common.data.remote.Result

class AdoptionViewModel(
    private val repository: AdoptionRepository
) : ViewModel()
{

    private val _uiState = MutableStateFlow(AdoptionUiState())
    val uiState: StateFlow<AdoptionUiState> = _uiState.asStateFlow()

    private val _state = MutableStateFlow<AdoptionState>(AdoptionState.Idle)
    val state: StateFlow<AdoptionState> = _state.asStateFlow()



    var isLoading by mutableStateOf(false)
        private set

    var adoption by mutableStateOf<AdoptionResponse?>(null)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun createAdoption(request: AdoptionRequest) {
        isLoading = true
        adoption = null
        error = null
        _uiState.value = AdoptionUiState(isLoading = true)
        _state.value = AdoptionState.Loading

        viewModelScope.launch {
            when (val result = repository.createAdoption(request)) {
                is Result.Success -> {
                    adoption = result.data
                    _uiState.value = AdoptionUiState(adoption = result.data)
                    _state.value = AdoptionState.Success(result.data)
                }
                is Result.Error -> {
                    error = result.message
                    _uiState.value = AdoptionUiState(error = result.message)
                    _state.value = AdoptionState.Error(result.message)
                }
            }
            isLoading = false
        }
    }


}

data class AdoptionUiState(
    val isLoading: Boolean = false,
    val adoption: AdoptionResponse? = null,
    val adoptionList: List<AdoptionResponse> = emptyList(),
    val error: String? = null
)

sealed class AdoptionState {
    object Idle : AdoptionState()
    object Loading : AdoptionState()
    data class Success(val adoption: AdoptionResponse) : AdoptionState()
    data class SuccessList(val adoptionList: List<AdoptionResponse>) : AdoptionState()
    data class Error(val message: String) : AdoptionState()
}
