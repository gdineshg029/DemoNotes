package com.java.demonotes.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.java.core.data.Note
import com.java.demonotes.R
import com.java.demonotes.framework.NoteViewModel
import com.java.demonotes.showMessage

/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L)

    private val args: NoteFragmentArgs by navArgs()
    private lateinit var rootview : View
    private lateinit var checkButton:FloatingActionButton
    private lateinit var titleView:EditText
    private lateinit var contentView:EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_note, container, false)
        return rootview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        rootview?.apply {
            checkButton = findViewById(R.id.checkButton)
            titleView = findViewById(R.id.titleView)
            contentView = findViewById(R.id.contentView)
        }


        checkButton.apply {
            setOnClickListener {
                if (titleView.text.toString()!="" || contentView.text.toString() != ""){
                    val time = System.currentTimeMillis()
                    currentNote.title = titleView.text.toString()
                    currentNote.content = contentView.text.toString()
                    currentNote.updateTime = time
                    if(currentNote.id == 0L){
                        currentNote.creationTime = time
                    }
                    viewModel.savedNote(currentNote)
                    /*else{
                        viewModel.updateNote(currentNote)
                    }*/

                }else {
                    Navigation.findNavController(it).popBackStack()
                }
            }
        }

        observeViewModel()
        getCurrentNote()
    }

    private fun getCurrentNote(){
        if(args.noteIF != 0L)
            viewModel.getNote(args.noteIF)
    }
    private fun observeViewModel(){
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it) {
                context?.showMessage("Done!!")
                Navigation.findNavController(titleView).popBackStack()
            } else {
                context?.showMessage("Failed to save!!!")
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner){
            it?.let{note->
                titleView.setText(note.title,TextView.BufferType.EDITABLE)
                contentView.setText(note.content,TextView.BufferType.EDITABLE)
                currentNote = note
            }
        }

        viewModel.removed.observe(viewLifecycleOwner){
            if(it){
                context?.showMessage("Successfully removed!!")
                Navigation.findNavController(titleView).popBackStack()
            }else{
                context?.showMessage("Failed to remove!!")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote->{
                if(context != null && currentNote.id != 0L){
                 AlertDialog.Builder(requireContext())
                     .setTitle("Delete Note")
                     .setMessage("Sre you sure you to delete the note ?")
                     .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i -> viewModel.deleteNote(currentNote) })
                     .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->  dialogInterface.dismiss()})
                     .create()
                     .show()
                }
            }
        }
        return true
    }

}