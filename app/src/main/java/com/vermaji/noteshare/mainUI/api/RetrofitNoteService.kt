package com.vermaji.noteshare.mainUI.api

import com.vermaji.noteshare.mainUI.homeFragment.models.NoteResponse
import com.vermaji.noteshare.mainUI.uploadFragment.models.CategoriesResponse
import com.vermaji.noteshare.network.Endpoints
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private val retrofit = Retrofit.Builder()
    .baseUrl(Endpoints.baseurl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface NoteApiService{

    @GET(Endpoints.getAllCategories)
    fun getAllCategories():Call<CategoriesResponse>


    @POST(Endpoints.topDownloads)
    fun topDownloads():Call<NoteResponse>

    @Multipart
    @POST(Endpoints.uploadFile)
    fun uploadFile(@Part form: MultipartBody):Call<String>
}

object RetrofitNoteService{
    val retrofitService:NoteApiService = retrofit.create(NoteApiService::class.java)
}