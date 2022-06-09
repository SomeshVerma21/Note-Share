package com.vermaji.noteshare.mainUI.home.homeScreen.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "category")
    val category: String,
    @Json(name = "comments")
    val comments: Any?,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "downloads")
    val downloads: Int,
    @Json(name = "fileUrl")
    val fileUrl: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "likesCount")
    val likesCount: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "subCategory")
    val subCategory: String,
    @Json(name = "tags")
    val tags: List<String>,
    @Json(name = "userId")
    val userId: String
)