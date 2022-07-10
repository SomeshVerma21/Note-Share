package com.vermaji.noteshare.mainUI.home.homeScreen.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoteResponse(
    @Json(name = "list")
    val data: List<Data>,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: String
)