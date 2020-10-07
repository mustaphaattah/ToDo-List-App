package com.mtah.todolist.fragments

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtah.todolist.backend.models.ToDo

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoVIewHolder>() {

    val data = listOf<ToDo>()
    class ToDoVIewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoVIewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ToDoVIewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}