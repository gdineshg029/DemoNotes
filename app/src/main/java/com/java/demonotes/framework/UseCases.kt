package com.java.demonotes.framework

import com.java.core.usecase.AddNote
import com.java.core.usecase.GetAllNotes
import com.java.core.usecase.GetNote
import com.java.core.usecase.RemoveNote

data class UseCases(
    val addNote:AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)
