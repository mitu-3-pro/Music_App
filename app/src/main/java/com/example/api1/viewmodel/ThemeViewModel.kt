package com.example.api1.viewmodel

import androidx.lifecycle.*
import com.example.api1.repository.ThemeRepository
import kotlinx.coroutines.launch

class ThemeViewModel : ViewModel() {

    private val repository = ThemeRepository()

    private val _uiState = MutableLiveData<UiState>()

    val uiState: LiveData<UiState>
        get() = _uiState

    fun fetchThemes() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.getThemes()

                if (response.isSuccessful && response.body() != null) {
                    _uiState.value = UiState.Success(response.body()!!.data)
                } else {
                    _uiState.value = UiState.Error("API Error: ${response.code()}")
                }

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
                e.printStackTrace()
            }
        }
    }
}