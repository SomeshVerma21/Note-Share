package com.vermaji.noteshare.loginService.models

data class Response(
    val status:String,
    val message:String,
    val data:UserLoginOP?
)