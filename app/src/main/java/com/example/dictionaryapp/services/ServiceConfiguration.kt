package com.example.dictionaryapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceConfiguration {

    fun getService() : OxfordService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://od-api.oxforddictionaries.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(OxfordService::class.java)
    }
}