package com.mtah.todolist.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mtah.todolist.R
import com.mtah.todolist.backend.ToDoViewModel
import com.mtah.todolist.backend.models.Priority
import com.mtah.todolist.backend.models.ToDo
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    var viewModel: ToDoViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(requireActivity()).get(ToDoViewModel::class.java)


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_add) {
            insertTask()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertTask(){
        val title = title_et.text.toString()
        val priority = priorities_spinner.selectedItem.toString()
        val description = description_et.text.toString()

        if (isValidData(title, description)){
//            val newTask = ToDo(title, getPriority(priority),description)
//            viewModel.insert(newTask)
            Toast.makeText(requireContext(), "New task added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Please enter both title and description.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getPriority(priority: String): Priority {
        return when(priority) {
            "Low Priority" -> Priority.LOW
            "Medium Priority" -> Priority.MEDIUM
            "High Priority" -> Priority.HIGH
            else -> Priority.LOW
        }
    }

    private fun isValidData(title: String, description: String): Boolean{
        return title.isNotBlank() and description.isNotBlank()
    }
}
