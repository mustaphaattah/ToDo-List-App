package com.mtah.todolist.backend.persistence

import androidx.lifecycle.LiveData
import com.mtah.todolist.backend.models.ToDo

class ToDoRepository(private val toDoDao: ToDoDao) {

    val allData: LiveData<List<ToDo>> = toDoDao.getAll()

    suspend fun insert(toDo: ToDo) {
        toDoDao.insert(toDo)
    }

    suspend fun delete(toDo: ToDo) {
        toDoDao.delete(toDo)
    }

    suspend fun deleteAll() {
        toDoDao.deleteAll()
    }
}