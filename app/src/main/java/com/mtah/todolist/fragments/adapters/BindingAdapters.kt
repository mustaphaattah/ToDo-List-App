package com.mtah.todolist.fragments.adapters

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mtah.todolist.R
import com.mtah.todolist.backend.models.Priority
import com.mtah.todolist.backend.models.ToDo
import com.mtah.todolist.fragments.HomeFragmentDirections

class BindingAdapters {

    companion object {
        // navigate To Add Fragment
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_homeFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when (emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority) {
            when (priority) {
                Priority.LOW -> view.setSelection(0)
                Priority.MEDIUM -> view.setSelection(1)
                Priority.HIGH -> view.setSelection(2)
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority) {
            when (priority) {
                Priority.LOW -> cardView.setBackgroundColor(cardView.context.getColor(R.color.green))
                Priority.MEDIUM -> cardView.setBackgroundColor(cardView.context.getColor(R.color.yellow))
                Priority.HIGH -> cardView.setBackgroundColor(cardView.context.getColor(R.color.red))
            }
        }

        @BindingAdapter("android:navigateToUpdateFragment")
        @JvmStatic
        fun navigateToUpdateFragment(view: ConstraintLayout, currentItem: ToDo) {
            view.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
    }
}