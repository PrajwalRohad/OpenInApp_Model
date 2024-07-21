package com.example.openinappmodel.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.openinappmodel.data.implementation.Repository

class LinksViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LinksViewModel(repository) as T
    }
}