package com.vermaji.noteshare.mainUI.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vermaji.noteshare.database.NoteDatabaseDao
import com.vermaji.noteshare.database.NoteProperty
import kotlinx.coroutines.launch

class NoteViewModel(val database: NoteDatabaseDao,application: Application): AndroidViewModel(application) {

    var list:MutableList<NoteProperty>? = null

    init {

    }
    fun insert(noteProperty: NoteProperty)
    {
        viewModelScope.launch {
            database.insert(noteProperty)

        }
    }
    fun checkData()
    {
        val data:MutableList<NoteProperty>? = database.getAll()
        if (data != null) {
            for (note in data)
                Log.i("view Model ",note.id.toString())
            list = data
        }else
            Log.i("view model","database is empty")
    }
    fun deleteAll()
    {
        database.clear()
    }
    override fun onCleared() {
        super.onCleared()
    }
}