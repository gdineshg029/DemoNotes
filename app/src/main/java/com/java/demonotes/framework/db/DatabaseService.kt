package com.java.demonotes.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NoteEntity::class),version = 1)
abstract class DatabaseService :RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object{
        private const val DATABASE_NAME = "note.db"

        private var instance: DatabaseService? = null

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}