package com.example.planperfect.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planperfect.R
import com.example.planperfect.databinding.FragmentHomeBinding
//import com.example.planperfect.utils.adapter.TaskAdapter
//import com.example.planperfect.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.database
import com.google.firebase.database.*
import com.google.type.Date

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var authId: String
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        binding.profileName.text = auth.currentUser?.displayName

        binding.calendarPlus.setOnClickListener {

            navController.navigate(R.id.action_homeFragment_to_calendarFragment)

        }

        binding.btnSettings.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_settingsFragment)
        }

        binding.btnHelp.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_helpFragment)
        }

        binding.btnAbout.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_aboutFragment)
        }
    }



    private fun init(view : View) {

        auth = FirebaseAuth.getInstance()
        authId = auth.currentUser!!.uid
        //database = Firebase.database.reference.child("Tasks").child(authId)
        database = FirebaseDatabase.getInstance().reference

        navController = Navigation.findNavController(view)

        // binding.mainRecyclerView.setHasFixedSize(true)
        // binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)

    }

}