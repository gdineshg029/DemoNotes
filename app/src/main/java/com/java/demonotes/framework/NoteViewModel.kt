package com.java.demonotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.java.core.data.Note
import com.java.core.repository.NoteRepository
import com.java.core.usecase.*
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
    val removed = MutableLiveData<Boolean>()


    val currentNote = MutableLiveData<Note?>()

    fun savedNote(note:Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id:Long) = coroutineScope.launch {
        val note = useCases.getNote(id)
        currentNote.postValue(note)
    }

    fun deleteNote(note:Note){
        coroutineScope.launch {
            useCases.removeNote(note)
            removed.postValue(true)
        }
    }



}