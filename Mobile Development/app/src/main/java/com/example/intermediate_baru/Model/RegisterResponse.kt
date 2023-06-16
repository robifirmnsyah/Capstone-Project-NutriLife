package com.example.intermediate_baru.Model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class RegisterResponse(
	@field:SerializedName("error")
	val error: String? = null,
)
