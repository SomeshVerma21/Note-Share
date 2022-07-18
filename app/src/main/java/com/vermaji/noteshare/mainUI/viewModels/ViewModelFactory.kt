package com.vermaji.noteshare.mainUI.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vermaji.noteshare.mainUI.home.notesDetails.NoteDetailsViewModel
import com.vermaji.noteshare.mainUI.home.notesDetails.repo.NoteDetailsRepo

class NoteDetailsVMFactory(private val noteDetailsRepo: NoteDetailsRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailsViewModel::class.java))
            return NoteDetailsViewModel(noteDetailsRepo = noteDetailsRepo) as T
        else
            throw IllegalArgumentException("Unknown view model class ")
    }

}