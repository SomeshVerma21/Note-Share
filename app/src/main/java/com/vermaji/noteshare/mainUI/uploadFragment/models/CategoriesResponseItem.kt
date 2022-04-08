package com.vermaji.noteshare.mainUI.uploadFragment.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponseItem(
    @Json(name = "mainCategory")
    val mainCategory: String,
    @Json(name = "subCategories")
    val subCategories: List<String>
)