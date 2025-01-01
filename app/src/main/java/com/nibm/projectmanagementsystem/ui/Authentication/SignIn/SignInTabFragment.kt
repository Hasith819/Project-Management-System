package com.nibm.projectmanagementsystem.ui.Authentication.SignIn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nibm.projectmanagementsystem.databinding.FragmentSignInTabBinding
import com.nibm.projectmanagementsystem.ui.Home.HomeActivity

class SignInTabFragment : Fragment() {

    private lateinit var binding: FragmentSignInTabBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInTabBinding.inflate(inflater, container, false)

        // Handle SignIn button click
        binding.BtnSignIn.setOnClickListener {
            val email = binding.EmailAddress.text.toString().trim()
            val password = binding.Password.text.toString().trim()

            // Check if email and password are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmail(email, password)
            } else {
                Toast.makeText(activity, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun signInWithEmail(email: String, password: String) {
        // Sign in with Firebase Authentication
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    navigateHomeActivity()
                } else {
                    Toast.makeText(activity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateHomeActivity() {
        // Navigate to HomeActivity after successful login
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Close the current fragment so the user can't go back
    }
}
