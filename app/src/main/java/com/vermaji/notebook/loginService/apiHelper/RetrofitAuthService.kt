package com.vermaji.notebook.loginService.apiHelper

import com.vermaji.notebook.loginService.models.LoginUser
import com.vermaji.notebook.loginService.models.NewUser
import com.vermaji.notebook.loginService.models.Response
import com.vermaji.notebook.network.Endpoints
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl(Endpoints.baseurl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface AuthApiService{
    @POST(Endpoints.loginUser)
    fun loginUser(@Body user: LoginUser):Call<Response>

    @POST(Endpoints.registerUser)
    fun registerUser(@Body user:NewUser):Call<Response>
}

object RetrofitAuthApi{
    val retrofitService:AuthApiService = retrofit.create(AuthApiService::class.java)
}

