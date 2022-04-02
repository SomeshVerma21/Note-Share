package com.vermaji.noteshare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteProperty::class],version = 1,exportSchema = false)
abstract  class NoteDatabase : RoomDatabase() {
    abstract val noteDatabaseDao: NoteDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        fun getInstence(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteDatabase::class.java,
                            "note_database"
                    )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
} ///