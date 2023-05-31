package com.example.intermediate_baru.API

import com.example.intermediate_baru.Model.LoginResponse
import com.example.intermediate_baru.Model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {
    @FormUrlEncoded
    @POST("login")
    fun login (
        @Field("email")email:String,
        @Field("password")password:String,
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register (
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String,

    ): Call<RegisterResponse>
}