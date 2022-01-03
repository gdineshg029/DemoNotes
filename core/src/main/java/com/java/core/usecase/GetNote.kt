package com.java.core.usecase

import com.java.core.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id:Long) = noteRepository.getNote(id)
}