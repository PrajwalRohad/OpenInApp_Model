package com.example.openinappmodel.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.openinappmodel.data.implementation.Repository
import com.example.openinappmodel.domain.entities.LinkEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LinksViewModel(
    private val repository: Repository
) : ViewModel() {

    fun get(token: String) = liveData(Dispatchers.IO) {
        val apiResponse = repository.get(token).body()
        emit(apiResponse)
    }

    fun getRecentLinks(token: String) = liveData(Dispatchers.IO) {
        val links = repository.getRecentLinks(token)
        emit(links)
    }
    fun getTopLinks(token: String) = liveData(Dispatchers.IO) {
        val links = repository.getTopLinks(token)
        emit(links)
    }
    fun getChartData(token: String) = liveData(Dispatchers.IO) {
        val list = repository.getChartData(token)
        emit(list)
    }
}