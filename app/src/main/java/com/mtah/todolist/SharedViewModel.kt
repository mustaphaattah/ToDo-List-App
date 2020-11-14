package com.mtah.todolist

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mtah.todolist.backend.models.Priority
import com.mtah.todolist.backend.models.ToDo

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun isEmptyDatabase(toDos: List<ToDo>) {
        emptyDatabase.value = toDos.isEmpty()
    }

    val spinnerListener: AdapterView.OnItemSelectedListener  = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position) {
                0 -> (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))
                1 -> (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))
                2 -> (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

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