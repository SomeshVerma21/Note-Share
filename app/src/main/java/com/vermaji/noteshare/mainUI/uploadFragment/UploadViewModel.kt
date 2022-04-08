package com.vermaji.noteshare.mainUI.uploadFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import com.vermaji.noteshare.mainUI.uploadFragment.models.CategoriesResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
}