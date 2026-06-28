package org.parowings.screens.adoption.petAdoption

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.parowings.common.adoption.AdoptionRepository
import org.parowings.common.adoption.AdoptionResponse
import org.parowings.common.adoption.MyAdoptionResponse
import org.parowings.common.data.remote.Result

class PetAdoptionViewModel(
    private val repository: AdoptionRepository
) : ViewModel() {

    private val _adoptionListState = MutableStateFlow(AdoptionListUiState())
    val adoptionListState: StateFlow<AdoptionListUiState> = _adoptionListState.asStateFlow()

    private val _selectedAdoptionState = MutableStateFlow(AdoptionDetailUiState())
    val selectedAdoptionState: StateFlow<AdoptionDetailUiState> = _selectedAdoptionState.asStateFlow()

    private val _myAdoptionsState = MutableStateFlow(MyAdoptionsUiState())
    val myAdoptionsState: StateFlow<MyAdoptionsUiState> = _myAdoptionsState.asStateFlow()

    fun getAdoptions(city: String? = null, animalType: String? = null) {
        _adoptionListState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = repository.getAdoptions(city, animalType)) {
                is Result.Success -> _adoptionListState.update {
                    it.copy(
                        isLoading = false,
                        adoptionList = result.data,
                        error = null
                    )
                }

                is Result.Error -> _adoptionListState.update {
                    it.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun getAdoptionById(id: String) {
        _selectedAdoptionState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = repository.getAdoptionById(id)) {
                is Result.Success -> _selectedAdoptionState.update {
                    it.copy(
                        isLoading = false,
                        selectedAdoption = result.data,
                        error = null
                    )
                }

                is Result.Error -> _selectedAdoptionState.update {
                    it.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun getMyAdoptions(ownerId: String) {
        _myAdoptionsState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = repository.getMyAdoptions(ownerId)) {
                is Result.Success -> _myAdoptionsState.update {
                    it.copy(
                        isLoading = false,
                        ownerId = result.data.ownerId,
                        totalPosts = result.data.totalPosts,
                        adoptionList = result.data.adoptions,
                        error = null
                    )
                }

                is Result.Error -> _myAdoptionsState.update {
                    it.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}

data class AdoptionListUiState(
    val isLoading: Boolean = false,
    val adoptionList: List<AdoptionResponse> = emptyList(),
    val error: String? = null
)

data class AdoptionDetailUiState(
    val isLoading: Boolean = false,
    val selectedAdoption: AdoptionResponse? = null,
    val error: String? = null
)

data class MyAdoptionsUiState(
    val isLoading: Boolean = false,
    val ownerId: String = "",
    val totalPosts: Int = 0,
    val adoptionList: List<MyAdoptionResponse> = emptyList(),
    val error: String? = null
)
