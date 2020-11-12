package com.mtah.todolist.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtah.todolist.R
import com.mtah.todolist.SharedViewModel
import com.mtah.todolist.backend.ToDoViewModel
import com.mtah.todolist.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val adapter = ToDoAdapter()
    private val viewModel: ToDoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // data binding
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.sharedViewModel = sharedViewModel
        setHasOptionsMenu(true)

        //recyclerview setup
        recyclerViewInit()

        viewModel.getAll().observe(viewLifecycleOwner, {
            sharedViewModel.isEmptyDatabase(it)
            adapter.setData(it)
        })

        return binding.root
    }

    private fun recyclerViewInit() {
        val recyclerView = binding.todoRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_delete_all -> deleteConfirmation()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteConfirmation(){
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setMessage("Are you sure you want to delete all data?")
            .setTitle("Delete all data")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteAll()
                Toast.makeText(
                    requireContext(),
                    "All data deleted!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("No") { _, _ -> }

        alertBuilder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }

}
