package com.vermaji.noteshare.mainUI.home.notesDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import com.vermaji.noteshare.mainUI.home.notesDetails.models.NoteDetails
import com.vermaji.noteshare.mainUI.home.notesDetails.repo.NoteDetailsRepo
import com.vermaji.noteshare.mainUI.utils.Constant
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteDetailsViewModel(private val noteDetailsRepo: NoteDetailsRepo) : ViewModel() {

    fun loadNoteDetails(noteId:Int){
        viewModelScope.launch {
            noteDetailsRepo.setStatus(Constant.STATUS_START)
            val call = RetrofitNoteService.retrofitService.getNoteDetailsById(noteId = noteId)
            call.enqueue(object : Callback<NoteDetails>{
                override fun onResponse(call: Call<NoteDetails>, response: Response<NoteDetails>) {
                    noteDetailsRepo.setStatus(Constant.STATUS_SUCCESS)
                    if (response.isSuccessful && response.body() != null){
                        noteDetailsRepo.setNoteData(response.body()!!)
                    }else
                        noteDetailsRepo.setStatus(Constant.STATUS_EMPTY_DATA)
                }

                override fun onFailure(call: Call<NoteDetails>, t: Throwable) {
                    noteDetailsRepo.setStatus(Constant.STATUS_FAILED)
                }

            })
        }
    }
}