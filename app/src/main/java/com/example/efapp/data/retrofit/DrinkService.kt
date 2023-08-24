package com.example.efapp.data.retrofit

import com.example.efapp.data.response.ListApiResponse
import retrofit2.http.GET

interface DrinkService {

    @GET("00bf72a5-1e7d-4ec8-898f-4898c88668e7")
    suspend fun getAllDrinks(): ListApiResponse
}