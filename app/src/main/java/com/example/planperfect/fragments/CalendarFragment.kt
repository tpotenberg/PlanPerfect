package com.example.planperfect.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.example.planperfect.R
import com.example.planperfect.databinding.FragmentCalendarBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planperfect.databinding.FragmentHomeBinding
import com.example.planperfect.utils.adapter.TaskAdapter
import com.example.planperfect.utils.model.ToDoData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.util.*

class CalendarFragment : Fragment() {
    private val TAG = "CalendarFragment"
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var calendarView: CalendarView
    private var events: MutableMap<String, MutableList<String>> = mutableMapOf()
    //private lateinit var database: DatabaseReference
    //private var frag: ToDoDialogFragment? = null
    //private lateinit var auth: FirebaseAuth
    //private lateinit var authId: String

    //private lateinit var taskAdapter: TaskAdapter
    //private lateinit var toDoItemList: MutableList<ToDoData>
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        // setContentView(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendar)
        val scheduleTaskButton: Button = view.findViewById(R.id.schedule_task_button)

        val calendarDays: MutableList<CalendarDay> = ArrayList()

        addTaskToCalendar(2024, Calendar.DECEMBER, 25, "Christmas Day", R.drawable.candycane, calendarDays)
        addTaskToCalendar(2024, Calendar.DECEMBER, 31, "New Year's Eve", R.drawable.glass_flute, calendarDays)

        calendarView.setCalendarDays(calendarDays)

        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format(Locale.getDefault(), "%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format(Locale.getDefault(), "%02d", calendarDay.calendar.get(Calendar.MONTH) + 1) // Months are 0-indexed
                val year = calendarDay.calendar.get(Calendar.YEAR)

                val key = "$day-$month-$year"
                if (events.containsKey(key)) {
                    val taskList = events[key]?.joinToString(", ") ?: "No tasks"
                    Toast.makeText(context, taskList, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Not busy", Toast.LENGTH_SHORT).show()
                }
            }
        })


        calendarView.setOnPreviousPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                val month = String.format(Locale.getDefault(), "%02d", calendarView.currentPageDate.get(Calendar.MONTH) + 1) // Months are 0-indexed
                val year = calendarView.currentPageDate.get(Calendar.YEAR)
                Toast.makeText(context/*baseContext*/, "$month-$year", Toast.LENGTH_SHORT).show()
            }
        })
        scheduleTaskButton.setOnClickListener {
            showScheduleTaskDialog(calendarDays)
        }
    }

    private fun addTaskToCalendar(
        year: Int,
        month: Int,
        day: Int,
        taskName: String,
        iconResId: Int,
        calendarDays: MutableList<CalendarDay>
    ) {
        val key = String.format("%02d-%02d-%d", day, month + 1, year) // Format date
        events.getOrPut(key) { mutableListOf() }.add(taskName)

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val calendarDay = CalendarDay(calendar)
        calendarDay.imageResource = iconResId
        calendarDays.add(calendarDay)
    }


    private fun showScheduleTaskDialog(calendarDays: MutableList<CalendarDay>) {
        val dialogView = LayoutInflater.from(this.context).inflate(R.layout.dialog_schedule_task, null)
        val taskNameEditText: EditText = dialogView.findViewById(R.id.task_name_edit_text)
        val taskDatePicker: DatePicker = dialogView.findViewById(R.id.task_date_picker)
        val taskTimePicker: TimePicker = dialogView.findViewById(R.id.task_time_picker)
        val iconSpinner: Spinner = dialogView.findViewById(R.id.icon_spinner)

        val icons = arrayOf(
            R.drawable.airplane, R.drawable.atom, R.drawable.baby_bottle_outline, R.drawable.baby_carriage, R.drawable.balloon, R.drawable.baseball, R.drawable.basketball, R.drawable.beach, R.drawable.bee_flower, R.drawable.billiards, R.drawable.bone, R.drawable.book_open_variant, R.drawable.bookshelf, R.drawable.candycane, R.drawable.car_hatchback, R.drawable.card_account_phone, R.drawable.cards_playing_outline, R.drawable.cat, R.drawable.check_bold, R.drawable.chess_pawn, R.drawable.church, R.drawable.clover, R.drawable.cross, R.drawable.doctor, R.drawable.dog, R.drawable.egg_easter, R.drawable.email_open_heart_outline, R.drawable.face_man_shimmer, R.drawable.face_woman_shimmer, R.drawable.firework, R.drawable.fish, R.drawable.food, R.drawable.food_off, R.drawable.football, R.drawable.forest, R.drawable.gavel, R.drawable.gift, R.drawable.glass_flute, R.drawable.golf, R.drawable.grill, R.drawable.gym, R.drawable.halloween, R.drawable.heart_multiple, R.drawable.island, R.drawable.laptop, R.drawable.leaf_maple, R.drawable.meditation, R.drawable.menorah, R.drawable.mosque, R.drawable.om, R.drawable.ornament, R.drawable.party_popper, R.drawable.phone_clock, R.drawable.puzzle, R.drawable.ring, R.drawable.sail_boat, R.drawable.saxophone, R.drawable.school, R.drawable.shoe_ballet, R.drawable.slot_machine, R.drawable.snowflake, R.drawable.snowman, R.drawable.soccer, R.drawable.sprout_outline, R.drawable.star_crescent, R.drawable.star_david, R.drawable.stocking, R.drawable.tooth, R.drawable.tractor_variant, R.drawable.turkey, R.drawable.turtle, R.drawable.urgent, R.drawable.volleyball, R.drawable.weather_sunny, R.drawable.work
        )

        val iconNames = arrayOf(
            "Airplane", "Atom", "Baby Bottle", "Baby Carriage", "Balloon", "Baseball", "Basketball", "Beach", "Spring Flower", "Billiards", "Bone", "Open Book", "Bookshelf", "Candy Cane", "Car", "Video Call", "Playing Cards", "Cat", "Check", "Chess Pawn", "Church", "Four Leaf Clover", "Cross", "Doctor", "Dog", "Easter Egg", "Valentine Card", "Man Self Care", "Woman Self Care", "Firework", "Fish", "Food", "No Food", "Football", "Forest", "Gavel", "Gift", "Champagne Glass", "Golf", "Grill", "Gym", "Halloween", "Valentine Candy", "Island", "Laptop", "Fall Leaf", "Meditation", "Menorah", "Mosque", "Om", "Christmas Ornament", "Party Popper", "Scheduled Phone Call", "Puzzle Piece", "Ring", "Sail Boat", "Saxophone", "Graduation Cap", "Ballet Shoes", "Slot Machine", "Snowflake", "Snowman", "Soccer Ball", "Plant", "Islamic Crescent", "Star of David", "Christmas Stocking", "Tooth", "Tractor", "Turkey", "Turtle", "Urgent", "Volleyball", "Sun", "Briefcase"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            iconNames
        )

        iconSpinner.adapter = adapter

        AlertDialog.Builder(this.context)
            .setTitle("Schedule Task")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val taskName = taskNameEditText.text.toString()
                val day = taskDatePicker.dayOfMonth
                val month = taskDatePicker.month
                val year = taskDatePicker.year
                val hour = taskTimePicker.hour
                val minute = taskTimePicker.minute

                val selectedIconIndex = iconSpinner.selectedItemPosition
                if (selectedIconIndex < 0 || selectedIconIndex >= icons.size) {
                    Toast.makeText(context, "Invalid icon selection", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val selectedIcon = icons[selectedIconIndex]
                val timeFormatted = String.format("%02d:%02d", hour, minute)
                val taskWithTime = "$taskName at $timeFormatted"
                addTaskToCalendar(year, month, day, taskWithTime, selectedIcon, calendarDays)

                calendarView.setCalendarDays(calendarDays)

                Toast.makeText(this.context, "Task scheduled", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }
}

