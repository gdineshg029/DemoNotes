package com.java.core.usecase

import com.java.core.data.Note
import com.java.core.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}