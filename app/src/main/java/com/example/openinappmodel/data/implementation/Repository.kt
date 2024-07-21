package com.example.openinappmodel.data.implementation

import com.example.openinappmodel.data.services.ApiService
import com.example.openinappmodel.domain.entities.ApiResponse
import com.example.openinappmodel.domain.entities.LinkEntity
import com.github.mikephil.charting.data.Entry
import retrofit2.Response

class Repository (
    private val apiService: ApiService
) {

    suspend fun get(token: String) : Response<ApiResponse> {
        return apiService.get("Bearer $token")
    }

    suspend fun getRecentLinks(token: String): List<LinkEntity>? {
        val body = get(token).body()
        val data = body?.data

        return data?.recentLinks
    }
    suspend fun getTopLinks(token: String): List<LinkEntity>? {
        val body = get(token).body()
        val data = body?.data

        return data?.topLinks
    }

    suspend fun getChartData(token: String): List<Entry>? {
        val body = get(token).body()
        val data = body?.data

        return data?.urlChart
    }
}