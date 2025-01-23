package com.example.planperfect.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planperfect.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        // Floating Action Button to Add a Task
        val addTaskFab: FloatingActionButton = findViewById(R.id.add_task_fab)
        addTaskFab.setOnClickListener {
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
