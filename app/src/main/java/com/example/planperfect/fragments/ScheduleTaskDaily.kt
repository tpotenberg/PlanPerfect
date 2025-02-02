package com.example.planperfect.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.annotation.DrawableRes
import com.example.planperfect.R
import java.util.Calendar

class ScheduleTaskDaily(
    context: Context,
    private val onTaskScheduled: (Task) -> Unit // Callback for returning the scheduled task
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_schedule_task)

        // Get references to UI elements
        val taskNameInput: EditText = findViewById(R.id.task_name_edit_text)
        val datePicker: DatePicker = findViewById(R.id.task_date_picker)
        val timePicker: TimePicker = findViewById(R.id.task_time_picker)
        val iconSpinner: Spinner = findViewById(R.id.icon_spinner)

        val icons = arrayOf(
            R.drawable.airplane, R.drawable.atom, R.drawable.baby_bottle_outline, R.drawable.baby_carriage, R.drawable.balloon, R.drawable.baseball, R.drawable.basketball, R.drawable.beach, R.drawable.bee_flower, R.drawable.billiards, R.drawable.bone, R.drawable.book_open_variant, R.drawable.bookshelf, R.drawable.candycane, R.drawable.car_hatchback, R.drawable.card_account_phone, R.drawable.cards_playing_outline, R.drawable.cat, R.drawable.check_bold, R.drawable.chess_pawn, R.drawable.church, R.drawable.clover, R.drawable.cross, R.drawable.doctor, R.drawable.dog, R.drawable.egg_easter, R.drawable.email_open_heart_outline, R.drawable.face_man_shimmer, R.drawable.face_woman_shimmer, R.drawable.firework, R.drawable.fish, R.drawable.food, R.drawable.food_off, R.drawable.football, R.drawable.forest, R.drawable.gavel, R.drawable.gift, R.drawable.glass_flute, R.drawable.golf, R.drawable.grill, R.drawable.gym, R.drawable.halloween, R.drawable.heart_multiple, R.drawable.island, R.drawable.laptop, R.drawable.leaf_maple, R.drawable.meditation, R.drawable.menorah, R.drawable.mosque, R.drawable.om, R.drawable.ornament, R.drawable.party_popper, R.drawable.phone_clock, R.drawable.puzzle, R.drawable.ring, R.drawable.sail_boat, R.drawable.saxophone, R.drawable.school, R.drawable.shoe_ballet, R.drawable.slot_machine, R.drawable.snowflake, R.drawable.snowman, R.drawable.soccer, R.drawable.sprout_outline, R.drawable.star_crescent, R.drawable.star_david, R.drawable.stocking, R.drawable.tooth, R.drawable.tractor_variant, R.drawable.turkey, R.drawable.turtle, R.drawable.urgent, R.drawable.volleyball, R.drawable.weather_sunny, R.drawable.work
        )

        val iconNames = arrayOf(
            "Airplane", "Atom", "Baby Bottle", "Baby Carriage", "Balloon", "Baseball", "Basketball", "Beach", "Spring Flower", "Billiards", "Bone", "Open Book", "Bookshelf", "Candy Cane", "Car", "Video Call", "Playing Cards", "Cat", "Check", "Chess Pawn", "Church", "Four Leaf Clover", "Cross", "Doctor", "Dog", "Easter Egg", "Valentine Card", "Man Self Care", "Woman Self Care", "Firework", "Fish", "Food", "No Food", "Football", "Forest", "Gavel", "Gift", "Champagne Glass", "Golf", "Grill", "Gym", "Halloween", "Valentine Candy", "Island", "Laptop", "Fall Leaf", "Meditation", "Menorah", "Mosque", "Om", "Christmas Ornament", "Party Popper", "Scheduled Phone Call", "Puzzle Piece", "Ring", "Sail Boat", "Saxophone", "Graduation Cap", "Ballet Shoes", "Slot Machine", "Snowflake", "Snowman", "Soccer Ball", "Plant", "Islamic Crescent", "Star of David", "Christmas Stocking", "Tooth", "Tractor", "Turkey", "Turtle", "Urgent", "Volleyball", "Sun", "Briefcase"
        )

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            iconNames
        )

        iconSpinner.adapter = adapter

        // Dialog behavior
        setOnCancelListener {
            dismiss() // Dismiss the dialog when canceled
        }

        setOnDismissListener {
            // Validate and submit the task when dismissed
            val taskName = taskNameInput.text.toString().trim()

            if (taskName.isNotEmpty()) {
                val calendar = Calendar.getInstance()
                calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                calendar.set(Calendar.MINUTE, timePicker.minute)

                // Create and pass the task
                val selectedIcon = icons[iconSpinner.selectedItemPosition]
                onTaskScheduled(
                    Task(
                        name = taskName,
                        time = calendar.time.toString(),
                        iconResId = selectedIcon
                    )
                )
            } else {
                Toast.makeText(context, "Task name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}