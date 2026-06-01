package com.example.api1.model

import java.io.Serializable

data class CategoryItem(
    val Id: Int,
    val Cat_Name: String,
    val Category_image: String?,
    val CreatedAt: String?,
    val themes_total_count: Int,
    val themes: List<ThemeItem>
) : Serializable