package com.vermaji.noteshare.mainUI.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vermaji.noteshare.database.NoteDatabaseDao

class ViewModelFactory(private val dataSource:NoteDatabaseDao
            ,private val application: Application):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java))
            return NoteViewModel(dataSource, application) as T
        else
            throw IllegalArgumentException("Unknown viewmodel class ")
    }

}