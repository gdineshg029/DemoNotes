package com.java.demonotes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.java.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    val title:String,
    val content:String,
    @ColumnInfo(name="creation_date")
    val creationTime:Long,
    @ColumnInfo(name = "update_time")
    val updateTime:Long,
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L
){
    companion object{
        fun fromNote(note:Note)= NoteEntity(
            title = note.title,
            content = note.content,
            creationTime = note.creationTime,
            updateTime = note.updateTime,
            id=note.id
        )

        fun toNote(noteEntity: NoteEntity) = Note(
            title = noteEntity.title,
            content = noteEntity.content,
            creationTime = noteEntity.creationTime,
            updateTime = noteEntity.updateTime,
            id=noteEntity.id
        )


        fun toNoteList(list: List<NoteEntity>):List<Note> {
            return list.map {
                toNote(it)
            }
        }

    }

    fun toNote() = Note(title,content,creationTime,updateTime,id)

}
