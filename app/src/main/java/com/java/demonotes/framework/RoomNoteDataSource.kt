package com.java.demonotes.framework

import android.content.Context
import com.java.core.data.Note
import com.java.core.repository.NoteDataSource
import com.java.demonotes.framework.db.DatabaseService
import com.java.demonotes.framework.db.NoteEntity

class RoomNoteDataSource(
    context: Context
) :NoteDataSource{

    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id).toNote()

    override suspend fun getAll(): List<Note> = NoteEntity.toNoteList(noteDao.getAllNoteEntities())

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))

}