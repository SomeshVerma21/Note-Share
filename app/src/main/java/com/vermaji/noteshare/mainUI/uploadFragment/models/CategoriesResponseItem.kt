package com.vermaji.noteshare.mainUI.uploadFragment.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponseItem(
    @Json(name = "category")
    val category: String,
    @Json(name = "subcategory")
    val subcategory: List<String>

)