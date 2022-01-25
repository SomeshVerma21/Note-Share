package com.vermaji.notebook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_book_table")
data class NoteProperty(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    @ColumnInfo(name="imgSrc")
    val imgSrc:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name = "price")
    val price:String
)