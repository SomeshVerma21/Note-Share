package com.vermaji.noteshare.mainUI.api

import com.vermaji.noteshare.mainUI.uploadFragment.models.CategoriesResponse
import com.vermaji.noteshare.network.Endpoints
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl(Endpoints.baseurl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface NoteApiService{

    @GET(Endpoints.getAllCategories)
    fun getAllCategories():Call<CategoriesResponse>

    @POST(Endpoints.uploadNote)
    fun uploadNote():Boolean
}

object RetrofitNoteService{
    val retrofitService:NoteApiService = retrofit.create(NoteApiService::class.java)
}