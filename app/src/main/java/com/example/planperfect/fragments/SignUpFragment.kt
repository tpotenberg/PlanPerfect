package com.example.planperfect.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.planperfect.R
import com.example.planperfect.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest


class SignUpFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        init(view)

        binding.textViewSignIn.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.nextBtn.setOnClickListener {
            val userName = binding.userEt.text.toString().trim()
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passEt.text.toString().trim()
            val verifyPass = binding.verifyPassEt.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty() && verifyPass.isNotEmpty()) {
                if (pass == verifyPass) {

                    registerUser(userName, email, pass)

                } else {
                    Toast.makeText(context, "Password is not same", Toast.LENGTH_SHORT).show()
                }
            } else
                Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun registerUser(userName: String, email: String, pass: String) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {

                val user = mAuth.currentUser

                val profileUpdates = userProfileChangeRequest {
                    displayName = userName
                    //photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
                }

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_signUpFragment_to_homeFragment)
                        }
                    }

            } else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        mAuth = FirebaseAuth.getInstance()
    }
}