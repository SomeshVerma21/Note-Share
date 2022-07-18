package com.vermaji.noteshare.mainUI.home.notesDetails.repo

import com.vermaji.noteshare.mainUI.home.notesDetails.models.NoteDetails

interface NoteDetailsRepo {

    fun getNoteData():NoteDetails?

    fun setNoteData(noteDetails: NoteDetails)

    fun getStatus():String?

    fun setStatus(status:String)
}