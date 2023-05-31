package com.example.intermediate_baru.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val  BASE_URL ="https://story-api.dicoding.dev/v1/"

    val instance : APIService by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(APIService::class.java)
    }
}