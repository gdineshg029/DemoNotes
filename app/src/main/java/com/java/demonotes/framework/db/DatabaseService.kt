package com.java.demonotes.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NoteEntity::class),version = 1)
abstract class DatabaseService :RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object{
        const val DATABASE_NAME = "note.db"
    }
}