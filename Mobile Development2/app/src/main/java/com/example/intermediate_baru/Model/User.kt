package com.example.intermediate_baru.Model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class User(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)
