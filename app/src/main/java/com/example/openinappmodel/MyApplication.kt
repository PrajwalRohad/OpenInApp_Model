package com.example.openinappmodel

import android.app.Application
import android.content.Context
import com.example.openinappmodel.data.implementation.Repository
import com.example.openinappmodel.data.services.ApiConfig
import retrofit2.Retrofit

class MyApplication : Application(){

    companion object {

        lateinit var repository: Repository
        lateinit var appContext : Context

    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        repository = Repository(ApiConfig.RetrofitInstance.api)



    }
}