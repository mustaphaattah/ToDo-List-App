package com.mtah.todolist.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mtah.todolist.R
import com.mtah.todolist.SharedViewModel
import com.mtah.todolist.backend.models.Priority
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment(){

    val args by navArgs<UpdateFragmentArgs>()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        setHasOptionsMenu(true)

        view.title_et.setText(args.todoItem.title)
        view.description_et.setText(args.todoItem.description)
        view.priorities_spinner.setSelection(prioritySelection(args.todoItem.priority))
        view.priorities_spinner.onItemSelectedListener = sharedViewModel.spinnerListener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun prioritySelection(priority: Priority): Int{
        return when (priority) {
            Priority.LOW -> 0
            Priority.MEDIUM -> 1
            Priority.HIGH -> 2
        }
    }
}
