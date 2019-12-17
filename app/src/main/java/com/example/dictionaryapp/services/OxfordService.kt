package com.example.dictionaryapp.services


import com.example.dictionaryapp.model.word.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface OxfordService {

    @Headers("app_id: 2d47d39d", "app_key: 8002a226e11909946e0d2502f016bf40")
    @GET("api/v2/entries/{idioma}/{word}?fields=definitions&strictMatch=false")
    fun getWord(@Path("word") word: String, @Path("idioma") idioma: String): Call<Example>?
}