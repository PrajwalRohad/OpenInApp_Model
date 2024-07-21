package com.example.openinappmodel.domain.entities

import com.github.mikephil.charting.data.Entry
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("status") val status: Boolean,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("today_clicks") val todayClicks: Int,
    @SerializedName("top_source") val topSource: String,
    @SerializedName("top_location") val topLocation: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("data") val data: ApiData
)

data class ApiData (
    @SerializedName("recent_links") val recentLinks: List<LinkEntity>,
    @SerializedName("top_links") val topLinks: List<LinkEntity>,
    @SerializedName("overall_url_chart") val urlChart: List<Entry>
)