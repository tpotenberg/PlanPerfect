package com.example.planperfect.fragments

import com.applandeo.materialcalendarview.CalendarDay

data class Task(
    val id: String = "",
    val name: String = "",
    val time: String = "",
    //val date: String = "",
    val iconResId: Int = 0
)

data class fbTask(
    var fbTaskId: String? = null,
    var fbYear: String? = null,
    var fbMonth: String? = null,
    var fbDay: String? = null,
    var fbTaskName: String? = null,
    var fbIconResId: String? = null,
    var fbCalendarDays: String? = null
)