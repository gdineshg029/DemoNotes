package com.java.demonotes.framework

import com.java.core.usecase.*

data class UseCases(
    val addNote:AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)
