package com.java.core.usecase

import com.java.core.repository.NoteRepository

class GetAllNotes(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}