package com.java.demonotes.di

import com.java.core.repository.NoteDataSource
import com.java.demonotes.framework.RoomNoteDataSource
import com.java.demonotes.framework.db.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDataSourceModule {

    @Singleton
    @Provides
    fun providesNoteDataSource(noteDao: NoteDao):NoteDataSource{
        return RoomNoteDataSource(noteDao)
    }

}