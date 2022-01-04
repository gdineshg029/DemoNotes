package com.java.demonotes.di

import com.java.core.repository.NoteRepository
import com.java.core.usecase.AddNote
import com.java.core.usecase.GetAllNotes
import com.java.core.usecase.GetNote
import com.java.core.usecase.RemoveNote
import com.java.demonotes.framework.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun providesUseCases(repository : NoteRepository) = UseCases(
        addNote = AddNote(repository),
        getNote = GetNote(repository),
        getAllNotes = GetAllNotes(repository),
        removeNote = RemoveNote(repository)
    )


}