package com.example.intermediate_baru.API

import androidx.annotation.RawRes
import com.example.intermediate_baru.Model.LoginResponse
import com.example.intermediate_baru.Model.PredicResponse
import com.example.intermediate_baru.Model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface APIService {
    @FormUrlEncoded
    @POST("/auth/login")
    fun login (
        @Field("email")email:String,
        @Field("password")password:String,
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/auth/register")
    fun register (
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String,

    ): Call<RegisterResponse>

    @Multipart
    @POST("pred/karbo")
    fun pred_karbo (
        @Part file: MultipartBody.Part
    ): Call<PredicResponse>

}