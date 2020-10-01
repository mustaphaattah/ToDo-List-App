package com.mtah.todolist.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mtah.todolist.R
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        view.homeLayout.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_updateFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

}
