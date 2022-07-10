package com.vermaji.noteshare.mainUI.home.notesDetails.models

data class Data(
    val category: String,
    val desc: String,
    val userName:String,
    val downloads: Int,
    val fileUrl: String,
    val id: Int,
    val likesCount: Int,
    val name: String,
    val price: String,
    val subCategory: String,
    val tags: List<String>,
    val uploader_id: Int
)