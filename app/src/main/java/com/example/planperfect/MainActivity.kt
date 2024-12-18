package com.example.planperfect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.CalendarDay
import com.example.planperfect.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var calendarView: CalendarView
    private var events: MutableMap<String, String> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calendarView = findViewById(R.id.calendar)

        val calendars: ArrayList<CalendarDay> = ArrayList()
        val calendar = Calendar.getInstance()

        calendar.set(2024, Calendar.DECEMBER, 17)
        val calendarDay = CalendarDay(calendar)
        calendarDay.imageResource = R.drawable.check
        calendars.add(calendarDay)
        events["12-17-2024"] = "Busy"

        calendarView.setCalendarDays(calendars)

        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format(Locale.getDefault(), "%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format(Locale.getDefault(), "%02d", calendarDay.calendar.get(Calendar.MONTH) + 1) // Months are 0-indexed
                val year = calendarDay.calendar.get(Calendar.YEAR)
                if (events.containsKey("$day-$month-$year")) {
                    Toast.makeText(baseContext, events["$day-$month-$year"], Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Not busy", Toast.LENGTH_SHORT).show()
                }
            }
        })

        calendarView.setOnPreviousPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                val month = String.format(Locale.getDefault(), "%02d", calendarView.currentPageDate.get(Calendar.MONTH) + 1) // Months are 0-indexed
                val year = calendarView.currentPageDate.get(Calendar.YEAR)
                Toast.makeText(baseContext, "$month-$year", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
