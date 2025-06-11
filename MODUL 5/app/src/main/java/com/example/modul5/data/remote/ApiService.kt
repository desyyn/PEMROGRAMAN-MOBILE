package com.example.modul5.data.remote

import com.example.modul5.data.model.ApiResponse
import com.example.modul5.data.model.Constellation
import retrofit2.http.GET

interface ApiService {
    @GET("constellation")
    suspend fun getConstellations(): ApiResponse<List<Constellation>>
}
