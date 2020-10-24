package com.mtah.todolist.backend

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mtah.todolist.backend.models.ToDo
import com.mtah.todolist.backend.persistence.ToDoDatabase
import com.mtah.todolist.backend.persistence.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val toDoRepository: ToDoRepository

    val TAG = "ToDoViewModel"

    private var allData: LiveData<List<ToDo>>

    init {
        toDoRepository = ToDoRepository(toDoDao)
        allData = toDoRepository.allData
    }

    fun insert(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        toDoRepository.insert(toDo)
    }


    fun delete(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        toDoRepository.delete(toDo)
    }

    fun getAll(): LiveData<List<ToDo>> {
        return toDoRepository.allData
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        toDoRepository.deleteAll()
    }

    fun update(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        Log.i(TAG, "viewModel coroutine update")
        toDoRepository.update(toDo)
    }

}