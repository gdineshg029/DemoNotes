package com.java.core.usecase

import com.java.core.data.Note
import com.java.core.repository.NoteRepository

class RemoveNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note:Note) = noteRepository.removeNote(note)
}