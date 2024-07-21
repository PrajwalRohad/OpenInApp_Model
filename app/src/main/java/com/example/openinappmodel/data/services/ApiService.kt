package com.example.openinappmodel.data.services

import com.example.openinappmodel.domain.entities.ApiResponse
import com.example.openinappmodel.domain.entities.LinkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("v1/dashboardNew")
    suspend fun get(@Header("Authorization") token: String) : Response<ApiResponse>

}