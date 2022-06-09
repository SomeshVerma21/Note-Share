package com.vermaji.noteshare.mainUI.home.uploadFragment

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import com.vermaji.noteshare.mainUI.home.uploadFragment.models.CategoriesResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.Files
import java.nio.file.Paths

class UploadViewModel: ViewModel() {
    private val categories = MutableLiveData<CategoriesResponse>()
    val _categories:LiveData<CategoriesResponse>
        get() = categories
    init {
        loadCategories()
    }
    private fun loadCategories(){
        Log.d("Uploading","calling network")
        viewModelScope.launch {
            val call = RetrofitNoteService.retrofitService.getAllCategories()
            call.enqueue(object : Callback<CategoriesResponse>{
                override fun onResponse(
                    call: Call<CategoriesResponse>,
                    response: Response<CategoriesResponse>
                ) {
                    if (response.isSuccessful){
                        Log.d("Uploading file",response.body()?.size.toString())
                        categories.value = response.body()
                    }else{
                        Log.d("Uploading file","Server error")
                    }
                }

                override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                    Log.d("Uploading file",t.message.toString())
                }

            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun uploadFile(uri:Uri){
        val buffer = Files.readAllBytes(Paths.get(uri.toString()))
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file",uri.toFile().name,
            RequestBody.create(MediaType.parse("application/pdf"),
                buffer))
            .addFormDataPart("id","12")
            .addFormDataPart("name","book")
            .addFormDataPart("desc","fjlsd flsdfj")
            .addFormDataPart("userId","sdfdsf")
            .addFormDataPart("category","learn")
            .addFormDataPart("subCategory","hh")
            .addFormDataPart("tags","tag1")
            .addFormDataPart("tags","tag2")
            .build();
        viewModelScope.launch {
            val api = RetrofitNoteService.retrofitService.uploadFile(requestBody)
            val call = api.enqueue(object :Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("uploadViewModel",response.body().toString())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("uploadViewModel",t.message.toString())
                }
            })
        }
    }
}