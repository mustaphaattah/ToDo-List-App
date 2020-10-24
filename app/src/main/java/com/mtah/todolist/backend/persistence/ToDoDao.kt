package com.mtah.todolist.backend.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mtah.todolist.backend.models.ToDo

@Dao
interface ToDoDao {

        @Insert
        suspend fun insert(toDo: ToDo)

        @Delete
        suspend fun delete(toDo: ToDo)

        @Query("DELETE FROM todo_table")
        suspend fun deleteAll()

        @Query("SELECT * FROM todo_table ORDER BY id ASC")
        fun getAll(): LiveData<List<ToDo>>

        @Update
        suspend fun update(toDo: ToDo)

}