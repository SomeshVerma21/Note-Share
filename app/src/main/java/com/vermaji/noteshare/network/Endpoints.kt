package com.vermaji.noteshare.network

class Endpoints {
    companion object{
        const val baseurl = "http://192.168.185.66:8080"

        const val loginUser = "/api/v1/user/login"
        const val registerUser = "/api/v1/user/register"
        const val uploadNote = "/api/v1/notes/uploadfile"
        const val getAllCategories = "/api/v1/notes/getallcategories"
    }
}