package com.example.planperfect.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planperfect.R

//test data for debugging
private val tasks = listOf(
    Task("Test Meeting", "9:00 AM", 12),
    Task("Test Workout", "5:00 PM", 42)
)

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

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
    }

    override fun getItemCount(): Int = tasks.size

}