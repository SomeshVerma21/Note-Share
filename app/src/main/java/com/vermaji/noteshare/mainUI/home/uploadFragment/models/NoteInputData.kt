package com.vermaji.noteshare.mainUI.home.uploadFragment.models

data class NoteInputData(
    val id:String,

    val title:String,

    val desc:String,

    val userId:String,

    val category:String,

    val subCategory:String,

    val language:String,

    val tags:List<String>,

    val userName:String,

    val uploadTime:String
)
