package com.example.intermediate_baru.Model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PredicResponse(

	@field:SerializedName("nutrient_info")
	val nutrientInfo: ArrayList<NutrientInfoItem>,

	@field:SerializedName("recommended_foods_and_info")
	val recommendedFoodsAndInfo: ArrayList<RecommendedFoodsAndInfoItem>,

	@field:SerializedName("prediction")
	val prediction: String,

	@field:SerializedName("recommended_recipes")
	val recommendedRecipes: ArrayList<RecommendedRecipesItem>
) : Parcelable

@Parcelize
data class NutrientInfo(

	@field:SerializedName("Protein(g)")
	val proteinG: Int,

	@field:SerializedName("Karbohidrat(g)")
	val karbohidratG: Int,

	@field:SerializedName("Keterangan")
	val keterangan: String,

	@field:SerializedName("Kalori")
	val kalori: Int,

	@field:SerializedName("Ukuran")
	val ukuran: String,

	@field:SerializedName("Lemak(g)")
	val lemakG: Int
) : Parcelable

@Parcelize
data class NutrientInfoItem(

	@field:SerializedName("Protein(g)")
	val proteinG: Int,

	@field:SerializedName("Karbohidrat(g)")
	val karbohidratG: Int,

	@field:SerializedName("Keterangan")
	val keterangan: String,

	@field:SerializedName("Kalori")
	val kalori: Int,

	@field:SerializedName("Ukuran")
	val ukuran: String,

	@field:SerializedName("Lemak(g)")
	val lemakG: Int
) : Parcelable

@Parcelize
data class RecommendedFoodsAndInfoItem(

	@field:SerializedName("nutrient_info")
	val nutrientInfo: NutrientInfo,

	@field:SerializedName("food")
	val food: String
) : Parcelable

@Parcelize
data class RecommendedRecipesItem(

	@field:SerializedName("kalori")
	val kalori: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("steps")
	val steps: List<String>
) : Parcelable
