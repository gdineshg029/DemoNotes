package com.java.demonotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.java.demonotes.R
import com.java.demonotes.framework.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListFragment : Fragment(),ListAction {

    private val listViewModel:ListViewModel by viewModels()

    @Inject
    lateinit var notesListAdapter: NotesListAdapter

    private lateinit var rootView: View
    private lateinit var notesListView: RecyclerView
    private lateinit var addNote:FloatingActionButton
    private lateinit var loadingView: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesListView = rootView.findViewById(R.id.notesListView)
        addNote = rootView.findViewById(R.id.addNote)
        loadingView = rootView.findViewById(R.id.loadingView)

        addNote.setOnClickListener{
            goToNoteDetails()
        }

        initNoteRecyclerView()
        observeUpdates()
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getAllNotes()
    }

    private fun initNoteRecyclerView(){
        notesListAdapter.action = this
        notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = notesListAdapter
        }
    }

    private fun observeUpdates(){
        listViewModel.notes.observe(viewLifecycleOwner){
            loadingView.visibility =View.GONE
            notesListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(it.sortedBy { it.updateTime })
        }
    }
    private fun goToNoteDetails(id:Long = 0L){
        val action = ListFragmentDirections.actionGoToNoteFragment(id)
        Navigation.findNavController(notesListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }

}