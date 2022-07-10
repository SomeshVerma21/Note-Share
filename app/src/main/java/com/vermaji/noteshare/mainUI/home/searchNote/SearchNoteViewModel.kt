package com.vermaji.noteshare.mainUI.home.searchNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import kotlinx.coroutines.launch

class SearchNoteViewModel : ViewModel() {

    private fun searchForNotes(name:String){
        viewModelScope.launch {
            
        }
    }
}