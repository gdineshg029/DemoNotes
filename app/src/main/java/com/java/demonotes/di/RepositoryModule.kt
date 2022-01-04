package com.java.demonotes.di

import com.java.core.repository.NoteDataSource
import com.java.core.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesNoteRepository(noteDataSource: NoteDataSource) = NoteRepository(noteDataSource)

}