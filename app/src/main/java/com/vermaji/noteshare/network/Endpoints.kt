package com.vermaji.noteshare.network

class Endpoints {
    companion object{
        const val baseurl = "http://192.168.255.66:8080"

        const val loginUser = "/api/v1/user/login"
        const val registerUser = "/api/v1/user/register"
        const val getAllCategories = "/api/v1/notes/getallcategories"
        const val topDownloads = "/api/v1/notes/topDownloads"
        const val noteDetailsById = "/api/v1/notes/getnotedetails"

        const val uploadFile = "/api/v1/notes/upload"
    }
}