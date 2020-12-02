package com.mtah.todolist.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mtah.todolist.R
import com.mtah.todolist.SharedViewModel
import com.mtah.todolist.backend.ToDoViewModel
import com.mtah.todolist.backend.models.ToDo
import com.mtah.todolist.databinding.FragmentUpdateBinding
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment(){

    private val args by navArgs<UpdateFragmentArgs>()
    private val sharedViewModel: SharedViewModel by viewModels()
    private val toDoViewModel: ToDoViewModel by viewModels()

    private var updateBinding: FragmentUpdateBinding? = null
    private val binding get() = updateBinding!!

    private val TAG = "UpdateFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.args = args
        setHasOptionsMenu(true)

        binding.prioritiesSpinner.onItemSelectedListener = sharedViewModel.spinnerListener

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_save -> updateItem()
            R.id.option_delete -> deleteConfirmation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = title_et.text.toString()
        val description = description_et.text.toString()
        val priority = priorities_spinner.selectedItem.toString()

        val isValid = sharedViewModel.isValidData(title, description)

        if (isValid) {
            val updatedItem = ToDo(args.todoItem.id, title, sharedViewModel.getPriority(priority), description)
            toDoViewModel.update(updatedItem)
            Toast.makeText(requireContext(), "Successfully changed!", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "updateItem: To do updated!")
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteConfirmation(){
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setMessage("Are you sure you want to delete this?")
            .setTitle("Delete ${args.todoItem.title}")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                toDoViewModel.delete(args.todoItem)
                Toast.makeText(
                    requireContext(),
                    "Deleted!",
                    Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }
            .setNegativeButton("No") { _, _ -> }

        alertBuilder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        updateBinding = null
    }

}
