package com.example.intermediate_baru.Model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class LoginResponse(
	@field:SerializedName("loginResult")
	val loginResult: User,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)


