package com.mtah.todolist.backend.models

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun priorityToString(priority: Priority): String {
        return priority.toString()
    }

    @TypeConverter
    fun stringToPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}