package com.mtah.todolist.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mtah.todolist.R
import com.mtah.todolist.backend.models.Priority
import com.mtah.todolist.backend.models.ToDo
import kotlinx.android.synthetic.main.layout_row.view.*

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var dataList = emptyList<ToDo>()

    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_row, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemView.item_title_tv.text = item.title
        holder.itemView.item_desc.text = item.description
        holder.itemView.row_bg.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }

        when (item.priority) {
            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green))
            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.yellow))
            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red))
        }
    }

    fun setData(aList: List<ToDo>) {
        this.dataList = aList
        notifyDataSetChanged()
    }
}