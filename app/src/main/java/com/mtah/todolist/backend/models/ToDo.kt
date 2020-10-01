package com.mtah.todolist.backend.models

data class ToDo (
    var priority: Priority,
    var title: String,
    var description: String
) {
    var id: Int = 0
}