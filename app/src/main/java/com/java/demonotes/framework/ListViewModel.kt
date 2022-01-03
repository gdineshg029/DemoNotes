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

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val repository = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        addNote = AddNote(repository),
        getAllNotes = GetAllNotes(repository),
        getNote = GetNote(repository),
        removeNote = RemoveNote(repository)
    )

    val notes = MutableLiveData<List<Note>>()

    fun getAllNotes(){
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()
            notes.postValue(noteList)
        }
    }
}