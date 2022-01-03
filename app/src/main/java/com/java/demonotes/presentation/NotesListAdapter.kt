package com.java.demonotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.java.core.data.Note
import com.java.demonotes.R
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesListAdapter(var notes:ArrayList<Note>,val action: ListAction) : RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
      holder.bind(note = notes[position])
    }

    override fun getItemCount() = notes.size

    fun updateNotes(newNotes:List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val noteLayout:CardView = itemView.findViewById(R.id.noteLayout)
        val title:TextView = itemView.findViewById(R.id.title)
        val content:TextView = itemView.findViewById(R.id.content)
        val lastUpdated:TextView = itemView.findViewById(R.id.date)

        fun bind(note : Note){
            note?.let{ note->
                title.text = note.title
                content.text = note.content
                lastUpdated.text = convertToDateString(note.updateTime)
                noteLayout.setOnClickListener{
                    action.onClick(note.id)
                }
            }
        }
    }

    fun convertToDateString(time:Long):String{
        val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
        val resultDate = Date(time)
        return sdf.format(resultDate)
    }



}