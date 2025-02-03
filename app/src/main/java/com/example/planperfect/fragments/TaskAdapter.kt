package com.example.planperfect.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planperfect.R



class TaskAdapter(private val tasks: ArrayList<Task>)
    : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.task_name)
        val taskTime: TextView = itemView.findViewById(R.id.task_time)
        val taskIcon: ImageView = itemView.findViewById(R.id.task_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskTime.text = task.time
        holder.taskIcon.setImageResource(task.iconResId)

       // Log.d("RecyclerView", "Binding task: ${task.name} at ${task.time}")
    }

    override fun getItemCount(): Int  {
        return tasks.size
    }

}