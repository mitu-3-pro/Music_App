package com.example.api1.model

data class ThemeResponse(
    val status: Boolean,
    val error: Int,
    val message: String,
    val data: List<CategoryItem>
)