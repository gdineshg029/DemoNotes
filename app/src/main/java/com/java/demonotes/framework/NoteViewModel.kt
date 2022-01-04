package com.java.demonotes.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.java.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases : UseCases

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