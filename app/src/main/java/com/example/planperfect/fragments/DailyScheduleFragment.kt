package com.example.planperfect.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planperfect.R

class DailyScheduleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val taskList = ArrayList<Task>()//mutableListOf<Task>()
    private var selectedDate: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_daily_schedule)

        val view = inflater.inflate(R.layout.fragment_daily_schedule, container, false)
        selectedDate = arguments?.getString("selectedDate")

        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        dateTextView.text = selectedDate ?: "Unknown Date"  // Prevent null display


        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.daily_schedule_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter(taskList)
        recyclerView.adapter = adapter

       // taskList.add(Task("010", "TestTask", "TestTime",1))

        // Schedule Task Button
        val scheduleTaskButton: Button = view.findViewById(R.id.schedule_task_button)
        scheduleTaskButton.setOnClickListener {
            openScheduleTaskDialog()
        }
        return view
    }

    private fun openScheduleTaskDialog() {
        // Show the existing schedule task dialog
        ScheduleTaskDaily(requireContext()) { task ->
            taskList.add(task)
            adapter.notifyItemInserted(taskList.size - 1)  // More efficient than notifyDataSetChanged()
            Toast.makeText(requireContext(), "Task added successfully", Toast.LENGTH_SHORT).show()
        }.show()
    }
}
