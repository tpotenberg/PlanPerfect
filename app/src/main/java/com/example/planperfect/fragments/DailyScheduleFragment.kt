package com.example.planperfect.fragments

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planperfect.R

class DailyScheduleFragment : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_daily_schedule)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.daily_schedule_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(taskList)
        recyclerView.adapter = adapter

        // Schedule Task Button
        val scheduleTaskButton: Button = findViewById(R.id.schedule_task_button)
        scheduleTaskButton.setOnClickListener {
            openScheduleTaskDialog()
        }
    }

    private fun openScheduleTaskDialog() {
        // Show the existing schedule task dialog
        ScheduleTaskDaily(this) { task ->
            taskList.add(task)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()
        }.show()
    }
}
