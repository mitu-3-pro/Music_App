package com.example.api1.network

import com.example.api1.model.ThemeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("getallthemes")
    suspend fun getThemes(
        @Query("Application_Id") applicationId: Int = 103,
        @Query("page") page: Int = 0,
        @Query("languages") languages: Int = 287
    ): Response<ThemeResponse>
}