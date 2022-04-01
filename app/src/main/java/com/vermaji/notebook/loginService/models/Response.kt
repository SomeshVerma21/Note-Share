package com.vermaji.notebook.loginService.models

data class Response(
    val status:String,
    val message:String,
    val data:UserLoginOP?
)