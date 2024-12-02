package com.nibm.projectmanagementsystem.ui.Authentication.SignIn

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nibm.projectmanagementsystem.R
import com.nibm.projectmanagementsystem.databinding.ActivitySignInBinding
import com.nibm.projectmanagementsystem.ui.Authentication.SignUp.SignUpActivity
import com.nibm.projectmanagementsystem.ui.Home.HomeActivity

class SignInActivity : Activity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.signupView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

        binding.BtnSignIn.setOnClickListener {
            val email = binding.EmailAddress.text.toString().trim()
            val password = binding.Password.text.toString().trim()

           if(email.isNotEmpty() && password.isNotEmpty())
           {
               signInWithEmail(email, password)
           }

        }
        }

    fun signInWithEmail(email: String, password: String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   navigateHomeActivity()
                } else {
                    Toast.makeText(this, "Check your email or password.", Toast.LENGTH_SHORT).show()
                }
            }    

    }
    
    fun navigateHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    }


