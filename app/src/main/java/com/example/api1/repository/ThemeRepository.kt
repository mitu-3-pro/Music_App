package com.example.api1.repository

import com.example.api1.model.ThemeResponse
import com.example.api1.network.RetrofitClient
import retrofit2.Response

class ThemeRepository {

    suspend fun getThemes(
        applicationId: Int = 103,
        page: Int = 0,
        languages: Int = 287
    ): Response<ThemeResponse> {
        return RetrofitClient.api.getThemes(applicationId, page, languages)
    }
}