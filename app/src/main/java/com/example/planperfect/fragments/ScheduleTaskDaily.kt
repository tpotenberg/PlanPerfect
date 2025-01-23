package com.example.planperfect.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.planperfect.R

class ScheduleTaskDaily(
    context: Context,
    private val onTaskScheduled: (Task) -> Unit // Callback for returning the scheduled task
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_task_daily)

        // Get references to UI elements
        val taskNameInput: EditText = findViewById(R.id.task_name_input)
        val taskTimeInput: EditText = findViewById(R.id.task_time_input)
        val addButton: Button = findViewById(R.id.add_task_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)

        // Handle Add Button Click
        addButton.setOnClickListener {
            val taskName = taskNameInput.text.toString().trim()
            val taskTime = taskTimeInput.text.toString().trim()

            if (taskName.isEmpty() || taskTime.isEmpty()) {
                Toast.makeText(context, "Please enter both task name and time.", Toast.LENGTH_SHORT).show()
            } else {
                // Pass the new task back via callback
                onTaskScheduled(Task(taskName, taskTime, R.drawable.check_bold))
                dismiss()
            }
        }

        // Handle Cancel Button Click
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}