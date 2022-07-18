package com.vermaji.noteshare.mainUI.home.notesDetails.repo

import androidx.lifecycle.MutableLiveData
import com.vermaji.noteshare.mainUI.home.notesDetails.models.NoteDetails

class NoteDetailRepoImpl : NoteDetailsRepo{
    val status:MutableLiveData<String>
        get() = status

    private val noteDetails: MutableLiveData<NoteDetails?>
        get() {
            return noteDetails
        }

    override fun getNoteData(): NoteDetails? {
        return noteDetails.value
    }

    override fun setNoteData(noteDetails: NoteDetails) {
        this.noteDetails.value = noteDetails
    }

    override fun getStatus(): String? {
        return status.value
    }

    override fun setStatus(status: String){
        this.status.value = status
    }

}