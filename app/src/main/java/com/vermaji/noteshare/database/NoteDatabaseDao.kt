package com.vermaji.noteshare.database

import androidx.room.*

@Dao
interface NoteDatabaseDao {
    @Insert
    fun insert(note:NoteProperty)
    @Update
    fun update(note: NoteProperty)
    @Query("select * from NOTE_BOOK_TABLE")
    fun getAll():MutableList<NoteProperty>?
    @Query("delete from note_book_table")
    fun clear()

}