package com.vermaji.notebook.loginService.models



data class NewUser(
    val id:Long,

    val firstname:String,

    val lastname:String,

    val email:String,

    val password:String,

    val isemailverified:String?
)