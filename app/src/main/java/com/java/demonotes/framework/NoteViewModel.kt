package com.java.demonotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.java.core.data.Note
import com.java.core.repository.NoteRepository
import com.java.core.usecase.AddNote
import com.java.core.usecase.GetAllNotes
import com.java.core.usecase.GetNote
import com.java.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        addNote = AddNote(repository),
        getNote = GetNote(repository),
        getAllNotes = GetAllNotes(repository),
        removeNote = RemoveNote(repository)
    )

    val saved = MutableLiveData<Boolean>()

    fun savedNote(note:Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }



}