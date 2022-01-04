package com.java.demonotes.di

import com.java.demonotes.presentation.NotesListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class NoteListModel {

    @ActivityRetainedScoped
    @Provides
    fun providesNotesListAdapter():NotesListAdapter{
        return NotesListAdapter(arrayListOf())
    }

}