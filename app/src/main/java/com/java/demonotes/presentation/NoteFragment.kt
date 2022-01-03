package com.java.demonotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L)

    private val id:Long by navArgs()
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
                }else {
                    Navigation.findNavController(it).popBackStack()
                }
            }
        }

        observeViewModel()
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
    }

}