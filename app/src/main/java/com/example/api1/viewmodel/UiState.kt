package com.example.api1.viewmodel

import com.example.api1.model.CategoryItem

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<CategoryItem>) : UiState()
    data class Error(val message: String) : UiState()
}