package com.mtah.todolist.backend.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDo (
    var title: String,
    var priority: Priority,
    var description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}