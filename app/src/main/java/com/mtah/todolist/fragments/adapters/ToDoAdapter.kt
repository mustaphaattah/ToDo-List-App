package com.mtah.todolist.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtah.todolist.backend.models.ToDo
import com.mtah.todolist.databinding.LayoutRowBinding

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    var dataList = emptyList<ToDo>()

    class ToDoViewHolder(private val binding: LayoutRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(toDo: ToDo){
            binding.toDo = toDo
            binding.executePendingBindings()
        }

        companion object {
            fun from (parent: ViewGroup): ToDoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutRowBinding.inflate(layoutInflater, parent, false)
                return ToDoViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(aList: List<ToDo>) {
        this.dataList = aList
        notifyDataSetChanged()
    }
}