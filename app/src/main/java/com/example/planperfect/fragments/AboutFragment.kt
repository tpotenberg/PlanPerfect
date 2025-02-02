package com.example.planperfect.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.planperfect.databinding.FragmentAboutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AboutFragment : Fragment() {

    private val TAG = "AboutFragment"
    private lateinit var binding: FragmentAboutBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_aboutFragment_to_homeFragment)
        }

    }

    private fun init(view : View) {
        navController = Navigation.findNavController(view)
    }

}