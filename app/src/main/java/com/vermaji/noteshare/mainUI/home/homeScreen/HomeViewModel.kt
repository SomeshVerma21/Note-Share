package com.vermaji.noteshare.mainUI.home.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import com.vermaji.noteshare.mainUI.home.homeScreen.models.NoteResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val topDownloads = MutableLiveData<NoteResponse>()
    val _topDownloads:MutableLiveData<NoteResponse>
        get() = topDownloads

    init {
        loadTopNotes()
    }
    private fun loadTopNotes(){
        viewModelScope.launch {
            val call = RetrofitNoteService.retrofitService.topDownloads()
            call.enqueue(object : Callback<NoteResponse> {
                override fun onResponse(
                    call: Call<NoteResponse>,
                    response: Response<NoteResponse>
                ) {
                    if (response.isSuccessful)
                        topDownloads.value = response.body()
                }

                override fun onFailure(call: Call<NoteResponse>, t: Throwable) {

                }
            })
        }
    }
}