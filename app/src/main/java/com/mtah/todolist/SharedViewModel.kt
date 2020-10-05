package com.mtah.todolist

import android.app.Application
import android.graphics.Color.red
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.fragment.findNavController
import com.mtah.todolist.backend.models.Priority
import kotlinx.android.synthetic.main.fragment_add.*

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val spinnerListener: AdapterView.OnItemSelectedListener  = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            TODO("Not yet implemented")
            when (position) {
                0 -> (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))
                1 -> (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))
                2 -> (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
//            TODO("Not yet implemented")
        }

    }
    fun getPriority(priority: String): Priority {
        return when(priority) {
            "Low Priority" -> Priority.LOW
            "Medium Priority" -> Priority.MEDIUM
            "High Priority" -> Priority.HIGH
            else -> Priority.LOW
        }
    }

    fun isValidData(title: String, description: String): Boolean{
        return title.isNotBlank() and description.isNotBlank()
    }
}